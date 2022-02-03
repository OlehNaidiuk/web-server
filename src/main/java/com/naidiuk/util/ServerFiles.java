package com.naidiuk.util;

import java.nio.file.Files;
import java.nio.file.Path;

public class ServerFiles {
    private static final String DIRECTORY = "resources";

    public static String getPage(String uri) {
        String pathToPage = DIRECTORY + uri;
        if (Files.exists(Path.of(pathToPage))) {
            return pathToPage;
        }
        return DIRECTORY + "/404.html";
    }
}
