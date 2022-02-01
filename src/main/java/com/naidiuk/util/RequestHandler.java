package com.naidiuk.util;

import com.naidiuk.entity.HTTPMethod;
import com.naidiuk.entity.Request;

import java.util.HashMap;
import java.util.Map;

public class RequestHandler {
    private HTTPMethod httpMethod;
    private String uri;
    private String protocolVersion;
    private Map<String, String> headers = new HashMap<>();

    public void setHttpMethod(Request request) {
        String[] startLine = getStartLine(request);
        httpMethod = HTTPMethod.valueOf(startLine[0]);
    }

    public void setUri(Request request) {
        String[] startLine = getStartLine(request);
        uri = startLine[1];
    }

    public void setProtocolVersion(Request request) {
        String[] startLine = getStartLine(request);
        protocolVersion = startLine[2];
    }

    public void setHeaders(Request request) {
        String[] clientRequestLines = getRequestHeaders(request);
        for (int i = 1; i < clientRequestLines.length; i++) {
            String[] keyAndValue = clientRequestLines[i].split(":", 2);
            headers.put(keyAndValue[0], keyAndValue[1]);
        }
    }

    public HTTPMethod getMethod() {
        return httpMethod;
    }

    public String getUri() {
        return uri;
    }

    public String getProtocolVersion() {
        return protocolVersion;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    private String[] getStartLine(Request request) {
        String clientRequest = request.getRequest();
        String[] clientRequestLines = clientRequest.split("\\R+");
        return clientRequestLines[0].split("\\s");
    }

    private String[] getRequestHeaders(Request request) {
        String clientRequest = request.getRequest();
        return clientRequest.split("\\R+");
    }
}
