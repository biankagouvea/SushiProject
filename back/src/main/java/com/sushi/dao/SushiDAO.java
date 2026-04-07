package com.sushi.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sushi.model.Commande;
import com.sushi.model.Sushi;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SushiDAO {

    private final ObjectMapper mapper = new ObjectMapper();
    private static final String SUSHIS_FILE_PATH = "data/sushis.json";
    private static final String COMMANDES_FILE_PATH = "data/commandes.json";

    // =========================
    // GET SUSHIS
    // =========================
    public List<Sushi> getAllSushis() {
        try {
            File file = getOrCreateSushisFile();
            return mapper.readValue(file, new TypeReference<List<Sushi>>() {});
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Sushi getSushiById(int id) {
        List<Sushi> sushis = getAllSushis();

        Optional<Sushi> result = sushis.stream()
                .filter(s -> s.getId() == id)
                .findFirst();

        return result.orElse(null);
    }

    public Sushi createSushi(Sushi sushi) {
        try {
            List<Sushi> sushis = getAllSushis();

            int nextId = sushis.stream()
                    .mapToInt(Sushi::getId)
                    .max()
                    .orElse(0) + 1;

            sushi.setId(nextId);

            if (sushi.getStock() <= 0) {
                sushi.setStock(10);
            }

            sushis.add(sushi);
            saveAllSushis(sushis);
            return sushi;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean updateSushi(int id, Sushi updated) {
        try {
            List<Sushi> sushis = getAllSushis();

            for (int i = 0; i < sushis.size(); i++) {
                Sushi current = sushis.get(i);

                if (current.getId() == id) {
                    updated.setId(id);

                    if (updated.getStock() <= 0) {
                        updated.setStock(current.getStock());
                    }

                    sushis.set(i, updated);
                    saveAllSushis(sushis);
                    return true;
                }
            }

            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteSushi(int id) {
        try {
            List<Sushi> sushis = getAllSushis();
            boolean removed = sushis.removeIf(s -> s.getId() == id);

            if (!removed) {
                return false;
            }

            saveAllSushis(sushis);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // =========================
    // SAVE COMMANDE
    // =========================
    public void saveCommande(Commande c) {

        try {
            File file = new File(COMMANDES_FILE_PATH);

            file.getParentFile().mkdirs();

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

    private void saveAllSushis(List<Sushi> sushis) throws Exception {
        File file = new File(SUSHIS_FILE_PATH);
        file.getParentFile().mkdirs();
        mapper.writerWithDefaultPrettyPrinter().writeValue(file, sushis);
    }

    private File getOrCreateSushisFile() throws Exception {
        File file = new File(SUSHIS_FILE_PATH);

        if (file.exists()) {
            return file;
        }

        file.getParentFile().mkdirs();

        InputStream is = getClass()
                .getClassLoader()
                .getResourceAsStream("data/sushis.json");

        List<Sushi> defaults;

        if (is != null) {
            defaults = mapper.readValue(is, new TypeReference<List<Sushi>>() {});
        } else {
            defaults = new ArrayList<>();
        }

        mapper.writerWithDefaultPrettyPrinter().writeValue(file, defaults);
        return file;
    }
}