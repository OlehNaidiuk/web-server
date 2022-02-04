package com.naidiuk.service;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader serverInput = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     BufferedOutputStream serverOutput = new BufferedOutputStream(clientSocket.getOutputStream())) {
                    ClientHandler clientHandler = new ClientHandler(serverInput, serverOutput);
                    clientHandler.sendResponse();
                } catch (IOException e) {
                    throw new RuntimeException("Failed to connect");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Server failed to start") ;
        }
    }
}
