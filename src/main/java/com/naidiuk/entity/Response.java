package com.naidiuk.entity;

import com.naidiuk.util.ResponseGenerator;

import java.io.BufferedOutputStream;
import java.io.IOException;

public class Response {
    private byte[] response;

    public void writeResponse(BufferedOutputStream serverOut, ResponseGenerator responseGenerator) {
        try {
            serverOut.write(responseGenerator.getStatusLine().getBytes());
            serverOut.write('\n');
            serverOut.write(responseGenerator.getContentLength().getBytes());
            serverOut.write('\n');
            serverOut.write('\n');
            serverOut.write('\n');
            serverOut.write(responseGenerator.getBody());
            serverOut.write('\n');
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
