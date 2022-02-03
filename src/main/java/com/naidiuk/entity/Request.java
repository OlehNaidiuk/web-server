package com.naidiuk.entity;

import java.util.Map;

public class Request {
    private HttpMethod httpMethod;
    private String uri;
    private String httpProtocolVersion;
    private Map<String, String> headers;

    public void setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setHttpProtocolVersion(String httpProtocolVersion) {
        this.httpProtocolVersion = httpProtocolVersion;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public String getUri() {
        return uri;
    }

    public String getHttpProtocolVersion() {
        return httpProtocolVersion;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }
}
