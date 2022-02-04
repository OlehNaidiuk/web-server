package com.naidiuk.service;

import com.naidiuk.entity.Request;
import com.naidiuk.util.RequestParser;
import com.naidiuk.util.ResponseWriter;

import java.io.*;

public class ClientHandler {
    private final BufferedReader serverInput;
    private final BufferedOutputStream serverOutput;

    public ClientHandler(BufferedReader serverInput, BufferedOutputStream serverOutput) {
        this.serverInput = serverInput;
        this.serverOutput = serverOutput;
    }

    public void sendResponse() {
        ResponseWriter responseWriter = new ResponseWriter();
        RequestParser requestParser = new RequestParser();
        Request request = requestParser.parseRequest(serverInput);
        responseWriter.writeResponse(serverOutput, request);
    }
}
