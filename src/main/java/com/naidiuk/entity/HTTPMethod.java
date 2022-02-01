package com.naidiuk.entity;

public enum HTTPMethod {
    GET("GET"),
    POST("POST"),
    PUT("PUT");

    private final String title;

    HTTPMethod(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
