package com.naidiuk.entity;

import com.naidiuk.util.RequestParser;

import java.util.HashMap;
import java.util.Map;

public class Request {
    private String request;
    private final RequestParser requestParser = new RequestParser();

    public void setRequest(String request) {
        this.request = request;
    }

    public String getRequest() {
        return request;
    }

    public HttpMethod getHttpMethod() {
        String[] startLine = requestParser.parseStartLine(request);
        if (startLine[0].equals("GET") || startLine[0].equals("PUT") || startLine[0].equals("POST")) {
            return HttpMethod.valueOf(startLine[0]);
        } else {
            throw new EnumConstantNotPresentException(HttpMethod.class, "Enum constant not present");
        }
    }

    public String getUri() {
        String[] startLine = requestParser.parseStartLine(request);
        return startLine[1];
    }

    public String getProtocolVersion() {
        String[] startLine = requestParser.parseStartLine(request);
        return startLine[2];
    }

    public Map<String, String> getHeaders() {
        String[] requestLines = requestParser.parseRequest(request);
        Map<String, String> headers = new HashMap<>();
        for (int i = 1; i < requestLines.length; i++) {
            String[] keyValue = requestLines[i].split(":", 2);
            headers.put(keyValue[0], keyValue[1]);
        }
        return headers;
    }
}
