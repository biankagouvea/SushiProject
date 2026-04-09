package com.sushi.controller;
import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sushi.config.DataPathResolver;

@RestController
@RequestMapping("/api/commandes")
@CrossOrigin(origins = "http://localhost:4200")
/**
 * Contrôleur REST de gestion des commandes.
 * Les commandes sont stockées dans un fichier JSON local.
 */
public class CommandeController {

    private static final String FILE_NAME = "commandes.json";
    private final ObjectMapper mapper = new ObjectMapper();

    private File getOrCreateCommandesFile() throws IOException {
        File file = DataPathResolver.resolveDataFile(FILE_NAME);

        if (file.exists() && file.length() > 2) {
            return file;
        }

        mapper.writerWithDefaultPrettyPrinter().writeValue(file, new ArrayList<>());

        return file;
    }

    /**
     * Crée une commande et la persiste en JSON.
     *
     * @param data payload commande
     * @return message de résultat
     */
    @PostMapping
    public Map<String, Object> createCommande(@RequestBody Map<String, Object> data) {

        try {
            File file = getOrCreateCommandesFile();

            List<Map<String, Object>> commandes;

            if (file.exists() && file.length() > 2) {
                commandes = mapper.readValue(
                        file,
                        new TypeReference<List<Map<String, Object>>>() {}
                );
            } else {
                commandes = new ArrayList<>();
            }

            String userEmail = toText(data.get("userEmail")).trim().toLowerCase();

            if (userEmail.isEmpty()) {
                return Map.of("message", "Utilisateur requis", "success", false);
            }

            Map<String, Object> normalized = normalizeCommande(data, commandes.size() + 1);
            commandes.add(normalized);

            mapper.writerWithDefaultPrettyPrinter().writeValue(file, commandes);

            System.out.println("✔ Saved to: " + file.getAbsolutePath());

            return Map.of(
                    "message", "OK",
                    "success", true,
                    "id", normalized.get("id")
            );

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Map.of("message", "Erreur serveur", "success", false);
    }

    /**
     * Retourne la liste des commandes normalisées.
     *
     * @return commandes
     */
    @GetMapping
    public List<Map<String, Object>> getCommandes() {

        try {
            File file = getOrCreateCommandesFile();

            List<Map<String, Object>> commandes = mapper.readValue(
                    file,
                    new TypeReference<List<Map<String, Object>>>() {}
            );

            List<Map<String, Object>> normalized = new ArrayList<>();

            int generatedId = 1;

            for (Map<String, Object> c : commandes) {
                normalized.add(normalizeCommande(c, generatedId));
                generatedId++;
            }

            return normalized;

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Retourne les commandes d'un utilisateur identifié par son email.
     *
     * @param email email utilisateur
     * @return commandes associées à cet utilisateur
     */
    @GetMapping("/user/{email}")
    public List<Map<String, Object>> getCommandesUtilisateur(@PathVariable String email) {
        String emailRecherche = toText(email).trim().toLowerCase();
        List<Map<String, Object>> toutesLesCommandes = getCommandes();
        List<Map<String, Object>> resultat = new ArrayList<>();

        for (Map<String, Object> commande : toutesLesCommandes) {
            String commandeEmail = toText(commande.get("userEmail")).trim().toLowerCase();

            if (emailRecherche.equals(commandeEmail)) {
                resultat.add(commande);
            }
        }

        return resultat;
    }

    private Map<String, Object> normalizeCommande(Map<String, Object> data, int generatedId) {
        Map<String, Object> c = new LinkedHashMap<>();

        int id = parseInt(data.get("id"), generatedId);
        String userEmail = toText(data.get("userEmail")).trim().toLowerCase();
        String nomClient = toText(data.get("nomClient"));

        if (nomClient.isEmpty()) {
            nomClient = toText(data.get("nom"));
        }

        String adresse = toText(data.get("adresse"));
        String date = toText(data.get("date"));

        if (date.isEmpty()) {
            date = LocalDateTime.now().toString();
        }

        List<Map<String, Object>> items = normalizeItems(data.get("items"));

        double total = parseDouble(data.get("total"), -1);

        if (total < 0) {
            total = computeTotal(items);
        }

        c.put("id", id);
        c.put("userEmail", userEmail);
        c.put("nomClient", nomClient);
        c.put("nom", nomClient);
        c.put("adresse", adresse);
        c.put("date", date);
        c.put("total", total);
        c.put("items", items);

        return c;
    }

    private List<Map<String, Object>> normalizeItems(Object rawItems) {
        List<Map<String, Object>> result = new ArrayList<>();

        if (!(rawItems instanceof List<?> list)) {
            return result;
        }

        for (Object o : list) {
            if (!(o instanceof Map<?, ?> item)) {
                continue;
            }

            Map<String, Object> cleanItem = new LinkedHashMap<>();

            String nom = toText(item.get("nom"));
            int quantite = parseInt(item.get("quantite"), parseInt(item.get("qty"), 0));
            double prix = parseDouble(item.get("prix"), 0);
            int sushiId = parseInt(item.get("sushiId"), parseInt(item.get("id"), 0));

            cleanItem.put("sushiId", sushiId);
            cleanItem.put("nom", nom);
            cleanItem.put("prix", prix);
            cleanItem.put("quantite", quantite);
            cleanItem.put("qty", quantite);

            result.add(cleanItem);
        }

        return result;
    }

    private int parseInt(Object value, int defaultValue) {
        if (value == null) {
            return defaultValue;
        }

        try {
            return Integer.parseInt(value.toString());
        } catch (Exception e) {
            return defaultValue;
        }
    }

    private double parseDouble(Object value, double defaultValue) {
        if (value == null) {
            return defaultValue;
        }

        try {
            return Double.parseDouble(value.toString());
        } catch (Exception e) {
            return defaultValue;
        }
    }

    private String toText(Object value) {
        if (value == null) {
            return "";
        }

        return value.toString();
    }

    private double computeTotal(List<Map<String, Object>> items) {
        double total = 0;

        for (Map<String, Object> item : items) {
            double prix = parseDouble(item.get("prix"), 0);
            int qty = parseInt(item.get("quantite"), 0);
            total += prix * qty;
        }

        return total;
    }
}

    
