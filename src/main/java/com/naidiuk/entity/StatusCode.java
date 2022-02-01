package com.naidiuk.entity;

public enum StatusCode {
    OK ("200 OK"),
    NOT_FOUND ("404 Not Found");

    private final String title;

    StatusCode(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
