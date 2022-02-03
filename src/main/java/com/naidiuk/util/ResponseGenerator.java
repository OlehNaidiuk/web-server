package com.naidiuk.util;

import com.naidiuk.entity.Request;
import com.naidiuk.entity.StatusCode;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class ResponseGenerator {
    public void generateResponse(BufferedOutputStream sender, Request request) {
        try {
            sender.write(getStartLine(request).getBytes());
            sender.write(getContentLength(request).getBytes());
            sender.write('\n');
            sender.write('\n');
            BufferedInputStream fromFile = new BufferedInputStream(getStreamFromFile(request));
            byte[] buffer = new byte[10240];
            while (fromFile.available() > 0) {
                int realBytes = fromFile.read(buffer);
                sender.write(buffer, 0, realBytes);
            }
            sender.flush();
        } catch (IOException e) {
            throw new RuntimeException("Failed to send response");
        }
    }

    private String getStartLine(Request request) {
        return request.getProtocolVersion() + " " + getStatusCode(request);
    }

    private String getContentLength(Request request) {
        int content = 0;
        try (InputStream inputStream = Files.newInputStream(Path.of(ServerFiles.getPage(request.getUri())))){
            while (inputStream.available() > 0) {
                byte[] bytes = inputStream.readAllBytes();
                content = bytes.length;
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read the file");
        }
        return "Content-Length: " + content;
    }

    private InputStream getStreamFromFile(Request request) {
        try {
            return Files.newInputStream(Path.of(ServerFiles.getPage(request.getUri())));
        } catch (IOException e) {
            throw new RuntimeException("File doesn't exist");
        }
    }

    private String getStatusCode(Request request) {
        if (ServerFiles.getPage(request.getUri()).equals("resources/404.html")) {
            return StatusCode.NOT_FOUND.getTitle();
        }
        return StatusCode.OK.getTitle();
    }
}
