package com.sushi.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sushi.model.Commande;
import com.sushi.model.Sushi;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SushiDAO {

    private final ObjectMapper mapper = new ObjectMapper();

    // =========================
    // GET SUSHIS
    // =========================
    public List<Sushi> getAllSushis() {
        try {
            InputStream is = getClass()
                .getClassLoader()
                .getResourceAsStream("data/sushis.json");

            return mapper.readValue(is, new TypeReference<List<Sushi>>() {});
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // =========================
    // SAVE COMMANDE
    // =========================
    public void saveCommande(Commande c) {

        try {
            File file = new File("src/main/resources/data/commandes.json");

            List<Commande> commandes;

            // 👉 如果文件存在 → 读取旧数据
            if (file.exists()) {
                commandes = mapper.readValue(
                        file,
                        new TypeReference<List<Commande>>() {}
                );
            } else {
                commandes = new ArrayList<>();
            }

            // 👉 添加新订单
            commandes.add(c);

            // 👉 写回 JSON
            mapper.writerWithDefaultPrettyPrinter()
                  .writeValue(file, commandes);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}