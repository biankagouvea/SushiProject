package com.sushi.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sushi.config.DataPathResolver;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
/**
 * Contrôleur REST pour l'inscription et la connexion des utilisateurs.
 * Les comptes sont persistés dans un fichier JSON local.
 */
public class AuthController {

    private static final String FILE_NAME = "users.json";
    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * Inscrit un nouvel utilisateur à partir d'un email et d'un mot de passe.
     *
     * @param data payload JSON contenant email et password
     * @return résultat d'inscription
     */
    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody Map<String, Object> data) {
        try {
            String email = toText(data.get("email")).trim().toLowerCase();
            String password = toText(data.get("password"));
            boolean isAdmin = Boolean.parseBoolean(toText(data.getOrDefault("isAdmin", "false")));

            if (email.isEmpty() || password.isEmpty()) {
                return Map.of("message", "Email et mot de passe requis", "success", false);
            }

            List<Map<String, Object>> users = readUsers();

            boolean exists = users.stream()
                    .anyMatch(u -> email.equals(toText(u.get("email")).trim().toLowerCase()));

            if (exists) {
                return Map.of("message", "Email deja utilise", "success", false);
            }

            int nextId = users.stream()
                    .mapToInt(u -> parseInt(u.get("id"), 0))
                    .max()
                    .orElse(0) + 1;

            Map<String, Object> user = new LinkedHashMap<>();
            user.put("id", nextId);
            user.put("email", email);
            user.put("password", hashPassword(password));
            user.put("isAdmin", isAdmin);

            users.add(user);
            writeUsers(users);

            return Map.of("message", "Inscription reussie", "success", true);
        } catch (Exception e) {
            e.printStackTrace();
            return Map.of("message", "Erreur serveur", "success", false);
        }
    }

    /**
     * Connecte un utilisateur existant.
     *
     * @param data payload JSON contenant email et password
     * @return résultat de connexion avec token si succès
     */
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, Object> data) {
        try {
            String email = toText(data.get("email")).trim().toLowerCase();
            String password = toText(data.get("password"));

            if (email.isEmpty() || password.isEmpty()) {
                return Map.of("message", "Email et mot de passe requis", "success", false);
            }

            String passwordHash = hashPassword(password);
            List<Map<String, Object>> users = readUsers();

            Optional<Map<String, Object>> user = users.stream()
                    .filter(u -> email.equals(toText(u.get("email")).trim().toLowerCase())
                            && passwordHash.equals(toText(u.get("password"))))
                    .findFirst();

            if (user.isEmpty()) {
                return Map.of("message", "Identifiants invalides", "success", false);
            }

            String token = UUID.randomUUID().toString();
            boolean isAdmin = Boolean.parseBoolean(toText(user.get().getOrDefault("isAdmin", "false")));

            return Map.of(
                    "message", "Connexion reussie",
                    "success", true,
                    "token", token,
                    "email", email,
                    "isAdmin", isAdmin
            );
        } catch (Exception e) {
            e.printStackTrace();
            return Map.of("message", "Erreur serveur", "success", false);
        }
    }

    private List<Map<String, Object>> readUsers() throws Exception {
        File file = getOrCreateUsersFile();
        return mapper.readValue(file, new TypeReference<List<Map<String, Object>>>() {});
    }

    private void writeUsers(List<Map<String, Object>> users) throws Exception {
        File file = getOrCreateUsersFile();
        mapper.writerWithDefaultPrettyPrinter().writeValue(file, users);
    }

    private File getOrCreateUsersFile() throws Exception {
        File file = DataPathResolver.resolveDataFile(FILE_NAME);

        if (!file.exists() || file.length() == 0) {
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, new ArrayList<>());
            return file;
        }

        // If the file exists but contains invalid JSON, reset it to an empty list.
        try {
            mapper.readValue(file, new TypeReference<List<Map<String, Object>>>() {});
        } catch (Exception ignored) {
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, new ArrayList<>());
        }

        return file;
    }

    private int parseInt(Object value, int defaultValue) {
        try {
            return Integer.parseInt(String.valueOf(value));
        } catch (Exception e) {
            return defaultValue;
        }
    }

    private String toText(Object value) {
        return value == null ? "" : value.toString();
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();

            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (Exception e) {
            return password;
        }
    }
}