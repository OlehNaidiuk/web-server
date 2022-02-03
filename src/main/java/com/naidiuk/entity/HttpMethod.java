package com.naidiuk.entity;

public enum HttpMethod {
    GET("GET"),
    POST("POST"),
    PUT("PUT");

    private final String title;

    HttpMethod(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
