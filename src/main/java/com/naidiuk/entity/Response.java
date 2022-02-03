package com.naidiuk.entity;

import java.io.BufferedInputStream;

public class Response {
    private String httpProtocolVersion;
    private String httpStatus;
    private String contentLength;
    private BufferedInputStream streamFromFile;

    public void setHttpProtocolVersion(String httpProtocolVersion) {
        this.httpProtocolVersion = httpProtocolVersion;
    }

    public void setHttpStatus(String httpStatus) {

        this.httpStatus = httpStatus;
    }

    public void setContentLength(String contentLength) {
        this.contentLength = contentLength;
    }

    public void setStreamFromFile(BufferedInputStream inputFromFile) {
        this.streamFromFile = inputFromFile;
    }

    public String getHttpProtocolVersion() {
        return httpProtocolVersion;
    }

    public String getHttpStatus() {
        return httpStatus;
    }

    public String getContentLength() {
        return contentLength;
    }

    public BufferedInputStream getStreamFromFile() {
        return streamFromFile;
    }


}
