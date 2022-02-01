package com.naidiuk.entity;

import java.io.BufferedReader;
import java.io.IOException;

public class Request {
    private String request;

    public void setRequest(BufferedReader serverIn) {
        StringBuilder clientRequest = new StringBuilder();

        try {
            while (serverIn.ready()) {
                clientRequest.append(serverIn.readLine());
                clientRequest.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        request = clientRequest.toString();
    }

    public String getRequest() {
        return request;
    }
}
