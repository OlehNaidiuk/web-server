package com.naidiuk.util;

import com.naidiuk.entity.Request;

import java.nio.file.Files;
import java.nio.file.Path;

public class ServerFiles {
    private static final String DIRECTORY = "src/main/resources";

    public static String getPage(Request request) {
        String pathToPage = DIRECTORY + request.getUri();
        if (Files.isRegularFile(Path.of(pathToPage))) {
            return pathToPage;
        }
        return DIRECTORY + "/404.html";
    }
}
