package com.naidiuk;

import com.naidiuk.service.WebServer;

public class Start {
    public static void main(String[] args) {
        WebServer webServer = new WebServer();
        webServer.start();
    }
}
