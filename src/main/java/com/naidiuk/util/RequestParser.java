package com.naidiuk.util;

public class RequestParser {
    public String[] parseRequest(String request) {
        return request.split("\\R+");
    }

    public String[] parseStartLine(String request) {
        String[] requestLines = parseRequest(request);
        return requestLines[0].split("\\s");
    }
}
