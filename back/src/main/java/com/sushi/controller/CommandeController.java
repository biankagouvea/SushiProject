package com.sushi.controller;
import java.io.*;
import java.util.*;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/commandes")
@CrossOrigin(origins = "http://localhost:4200")
public class CommandeController {

    private final String FILE_PATH = "data/commandes.json";
    private final ObjectMapper mapper = new ObjectMapper();

    @PostMapping
    public Map<String, String> createCommande(@RequestBody Map<String, Object> data) {

        try {
            File file = new File("data/commandes.json");

       
            file.getParentFile().mkdirs();

            List<Map<String, Object>> commandes;

            if (file.exists()) {
                commandes = mapper.readValue(
                        file,
                        new TypeReference<List<Map<String, Object>>>() {}
                );
            } else {
                commandes = new ArrayList<>();
            }

            commandes.add(data);

            mapper.writeValue(file, commandes);

            System.out.println("✔ Saved to: " + file.getAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Map.of("message", "OK");
    }

    @GetMapping
    public List<Map<String, Object>> getCommandes() {

        try {
            File file = new File(FILE_PATH);

            if (!file.exists()) return new ArrayList<>();

            return mapper.readValue(
                    file,
                    new TypeReference<List<Map<String, Object>>>() {}
            );

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}

    
