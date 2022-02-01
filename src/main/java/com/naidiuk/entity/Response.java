package com.naidiuk.entity;

import com.naidiuk.util.ResponseGenerator;

import java.io.IOException;
import java.io.OutputStream;

public class Response {
    private String response;

    public String getResponse() {
        return response;
    }

    public void setResponse(ResponseGenerator responseGenerator) {
        response = responseGenerator.getStatusLine() + "\n"
                + responseGenerator.getContentLength() + "\n"
                + "\n"
                + "\n"
                + responseGenerator.getBody() + "\n";
    }

    public void sendResponse(OutputStream serverOut) {
        try {
            serverOut.write(response.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
