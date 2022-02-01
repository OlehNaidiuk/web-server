package com.naidiuk.util;

import com.naidiuk.entity.StatusCode;

import java.nio.file.Files;
import java.nio.file.Path;

public class ServerFiles {
    private static final String DIRECTORY = "D:/Programming/IdeaProjects/web-server/src/main/resources";
    private String filePath;
    private StatusCode statusCode;

    public void setFilePathAndStatus(RequestHandler requestHandler) {
        String pathToPage = DIRECTORY + requestHandler.getUri();
        if (Files.exists(Path.of(pathToPage))) {
            statusCode = StatusCode.OK;
            this.filePath = pathToPage;
        } else {
            statusCode = StatusCode.NOT_FOUND;
            this.filePath = DIRECTORY + "/404.html";
        }
    }

    public String getFilePath() {
        return filePath;
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }
}
