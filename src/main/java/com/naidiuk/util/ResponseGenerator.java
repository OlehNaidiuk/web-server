package com.naidiuk.util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class ResponseGenerator {
    private String statusLine;
    private String contentLength;
    private String body;

    public String getStatusLine() {
        return statusLine;
    }

    public void setStatusLine(RequestHandler requestHandler, ServerFiles serverFiles) {
        statusLine = requestHandler.getProtocolVersion()
                + " "
                + serverFiles.getStatusCode().getTitle();
    }

    public String getContentLength() {
        return contentLength;
    }

    public void setContentLength(ServerFiles serverFiles) {
        int content = 0;
        try (InputStream inputStream = Files.newInputStream(Path.of(serverFiles.getFilePath()))){
            while (inputStream.available() > 0) {
                byte[] bytes = inputStream.readAllBytes();
                content = bytes.length;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        contentLength = "Content-Length: " + content;
    }

    public String getBody() {
        return body;
    }

    public void setBody(ServerFiles serverFiles) {
        try (InputStream in = Files.newInputStream(Path.of(serverFiles.getFilePath()))) {
            while (in.available() > 0) {
                byte[] bytes = in.readAllBytes();
                body = new String(bytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
