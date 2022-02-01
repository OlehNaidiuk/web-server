package com.naidiuk.entity;

import com.naidiuk.util.RequestHandler;
import com.naidiuk.util.ResponseGenerator;
import com.naidiuk.util.ServerFiles;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private Request request = new Request();
    private RequestHandler requestHandler = new RequestHandler();
    private Response response = new Response();
    private ResponseGenerator responseGenerator = new ResponseGenerator();
    private ServerFiles serverFiles = new ServerFiles();

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader serverIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     BufferedOutputStream serverOut = new BufferedOutputStream(clientSocket.getOutputStream())) {
                    while (!serverIn.ready()) ;
                    System.out.println();
                    request.setRequest(serverIn);
                    requestHandler.setHttpMethod(request);
                    requestHandler.setUri(request);
                    requestHandler.setProtocolVersion(request);
                    requestHandler.setHeaders(request);
                    serverFiles.setFilePathAndStatus(requestHandler);
                    responseGenerator.setStatusLine(requestHandler, serverFiles);
                    responseGenerator.setContentLength(serverFiles);
                    responseGenerator.setBody(serverFiles);
                    response.writeResponse(serverOut, responseGenerator);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
