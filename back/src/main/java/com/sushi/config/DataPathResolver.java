package com.sushi.config;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Resolveur centralise des fichiers de donnees JSON runtime.
 *
 * <p>Les fichiers sont stockes sous {@code <catalina.base>/sushi-data} quand
 * l'application tourne dans Tomcat, sinon sous {@code <user.home>/sushi-data}.</p>
 */
public final class DataPathResolver {

    private static final String DATA_DIR_NAME = "sushi-data";

    private DataPathResolver() {
    }

    /**
     * Retourne un fichier de donnees dans le repertoire runtime, en creant
     * automatiquement l'arborescence necessaire.
     *
     * @param fileName nom du fichier JSON (ex: users.json)
     * @return fichier resolu
     * @throws IOException en cas d'erreur d'acces disque
     */
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