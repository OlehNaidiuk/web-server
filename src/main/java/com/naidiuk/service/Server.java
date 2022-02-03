package com.naidiuk.service;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader serverIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     OutputStream serverOut = clientSocket.getOutputStream()) {
                    while (!serverIn.ready());
                    System.out.println();
                    ServerService serverService = new ServerService();
                    serverService.readClientRequest(serverIn);
                    serverService.sendServerResponse(serverOut);
                } catch (IOException e) {
                    throw new RuntimeException("Failed to connect");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Server failed to start") ;
        }
    }
}
