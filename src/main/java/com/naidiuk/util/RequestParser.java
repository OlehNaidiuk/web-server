package com.naidiuk.util;

import com.naidiuk.entity.HttpMethod;
import com.naidiuk.entity.Request;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RequestParser {
    public Request parseRequest(BufferedReader serverInput) {
        Request request = new Request();
        Map<String, String> headers = new HashMap<>();
        String startLine = "";
        String line;
        try {
            startLine = serverInput.readLine();
            while (!(line = serverInput.readLine()).equals("")) {
                String[] keyValue = line.split(":", 2);
                headers.put(keyValue[0], keyValue[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] startLineParts = startLine.split(" ");
        request.setHttpMethod(HttpMethod.valueOf(startLineParts[0]));
        request.setUri(startLineParts[1]);
        request.setHttpProtocolVersion(startLineParts[2]);
        request.setHeaders(headers);
        return request;
    }
}
