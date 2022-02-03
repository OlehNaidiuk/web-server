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

    public void sendServerResponse() {
        ResponseWriter responseWriter = new ResponseWriter();
        Request request = createRequest();
        responseWriter.writeResponse(serverOutput, request);
    }

    private Request createRequest() {
        RequestParser requestParser = new RequestParser();
        return requestParser.parseRequest(serverInput);
    }
}
