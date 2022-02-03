package com.naidiuk.service;

import com.naidiuk.entity.Request;
import com.naidiuk.util.ResponseGenerator;

import java.io.*;

public class ServerService {
    private final Request request = new Request();
    private final ResponseGenerator responseGenerator = new ResponseGenerator();

    public void readClientRequest(BufferedReader serverIn) {
        StringBuilder clientRequest = new StringBuilder();

        try {
            while (serverIn.ready()) {
                clientRequest.append(serverIn.readLine()).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        request.setRequest(clientRequest.toString());
    }

    public void sendServerResponse(OutputStream serverOut) {
        BufferedOutputStream sender = new BufferedOutputStream(serverOut);
        responseGenerator.generateResponse(sender, request);
    }
}
