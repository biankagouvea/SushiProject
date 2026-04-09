package com.sushi.config;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class DataPathResolver {

    private static final String DATA_DIR_NAME = "sushi-data";

    private DataPathResolver() {
    }

    public static File resolveDataFile(String fileName) throws IOException {
        String catalinaBase = System.getProperty("catalina.base");
        Path baseDir;

        if (catalinaBase != null && !catalinaBase.isBlank()) {
            baseDir = Paths.get(catalinaBase, DATA_DIR_NAME);
        } else {
            baseDir = Paths.get(System.getProperty("user.home"), DATA_DIR_NAME);
        }

        Files.createDirectories(baseDir);

        Path dataFile = baseDir.resolve(fileName);

        if (dataFile.getParent() != null) {
            Files.createDirectories(dataFile.getParent());
        }

        return dataFile.toFile();
    }
}