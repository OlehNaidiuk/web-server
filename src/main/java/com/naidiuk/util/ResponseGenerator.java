package com.naidiuk.util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class ResponseGenerator {
    private String statusLine;
    private String contentLength;
    private byte[] body;

    public String getStatusLine() {
        return statusLine;
    }

    public void setStatusLine(RequestHandler requestHandler, ServerFiles serverFiles) {
        statusLine = requestHandler.getProtocolVersion() + " " + serverFiles.getStatusCode().getTitle();
    }

    public String getContentLength() {
        return contentLength;
    }

    public void setContentLength(ServerFiles serverFiles) {
        int charsCounter = 0;
        try (BufferedReader reader = Files.newBufferedReader(Path.of(serverFiles.getFilePath()))){
            while (reader.ready()) {
                String line = reader.readLine();
                charsCounter += line.length();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        contentLength = "Content-Length: " + charsCounter;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(ServerFiles serverFiles) {
        try (InputStream in = Files.newInputStream(Path.of(serverFiles.getFilePath()))) {
            body = new byte[10240];
            while (in.available() > 0) {
                body = in.readAllBytes();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
