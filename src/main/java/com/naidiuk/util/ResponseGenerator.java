package com.naidiuk.util;

import com.naidiuk.entity.Request;
import com.naidiuk.entity.Response;
import com.naidiuk.entity.StatusCode;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class ResponseGenerator {
    public Response generateResponse(Request request) {
        Response response = new Response();
        String httpStatus = generateHttpStatus(request);
        String contentLength = generateContentLength(request);
        BufferedInputStream streamFromFile = generateStreamFromFile(request);
        response.setHttpProtocolVersion(request.getHttpProtocolVersion());
        response.setHttpStatus(httpStatus);
        response.setContentLength(contentLength);
        response.setStreamFromFile(streamFromFile);
        return response;
    }

    private String generateContentLength(Request request) {
        int content = 0;
        try (BufferedInputStream input =
                     new BufferedInputStream(Files.newInputStream(Path.of(ServerFiles.getPage(request))))) {
            byte[] buffer = new byte[4096];
            while (input.available() > 0) {
                int realBytes = input.read(buffer);
                content += realBytes;
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read the file");
        }
        return "Content-Length: " + content;
    }

    private BufferedInputStream generateStreamFromFile(Request request) {
        try {
            return new BufferedInputStream(Files.newInputStream(Path.of(ServerFiles.getPage(request))));
        } catch (IOException e) {
            throw new RuntimeException("Failed to read the file");
        }
    }

    private String generateHttpStatus(Request request) {
        if (ServerFiles.getPage(request).equals("src/main/resources/404.html")) {
            return StatusCode.NOT_FOUND.getTitle();
        }
        return StatusCode.OK.getTitle();
    }
}
