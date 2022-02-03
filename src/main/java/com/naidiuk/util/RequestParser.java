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
        try {
            String[] startLineParts = serverInput.readLine().split(" ");
            Map<String, String> headers = new HashMap<>();
            while (!(serverInput.readLine().equals(""))) {
                String[] keyValue = serverInput.readLine().split(":", 2);
                headers.put(keyValue[0], keyValue[1]);
            }
            request.setHttpMethod(HttpMethod.valueOf(startLineParts[0]));
            request.setUri(startLineParts[1]);
            request.setHttpProtocolVersion(startLineParts[2]);
            request.setHeaders(headers);
        } catch (IOException e) {
            throw new RuntimeException("Can't read request");
        }
        return request;
    }
}
