package com.naidiuk.util;

import com.naidiuk.entity.Request;
import com.naidiuk.entity.Response;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;

public class ResponseWriter {
    public void writeResponse(BufferedOutputStream serverOutput, Request request) {
        ResponseGenerator responseGenerator = new ResponseGenerator();
        Response response = responseGenerator.generateResponse(request);
        String startLine = response.getHttpProtocolVersion()
                + " "
                + response.getHttpStatus();
        try {
            serverOutput.write(startLine.getBytes());
            serverOutput.write(response.getContentLength().getBytes());
            serverOutput.write('\n');
            serverOutput.write('\n');
            BufferedInputStream streamFromFile = response.getStreamFromFile();
            byte[] buffer = new byte[10240];
            while (streamFromFile.available() > 0) {
                int realBytes = streamFromFile.read(buffer);
                serverOutput.write(buffer, 0, realBytes);
            }
            serverOutput.flush();
        } catch (IOException e) {
            throw new RuntimeException("Failed to send response");
        }
    }
}
