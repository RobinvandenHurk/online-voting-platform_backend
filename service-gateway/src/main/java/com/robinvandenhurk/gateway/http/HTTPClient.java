package com.robinvandenhurk.gateway.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Author:    Robin van den Hurk
 * Date:      15/03/2021
 * File name: HTTPService
 */

public class HTTPClient {

    public HTTPResponseData send(HTTPRequestData data) {
        HTTPResponseData response;
        try {
            HttpURLConnection connection = createConnection(data);

            if (data.getBodyLength() > 0) {
                applyBodyData(connection, data);
            }

            response = getResponse(connection);
        } catch (Exception e) {
            response = new HTTPResponseData(e);
        }

        return response;
    }

    private void applyBodyData(HttpURLConnection connection, HTTPRequestData data) throws IOException {
        OutputStream os = connection.getOutputStream();
        byte[] input = data.getBody().getBytes(StandardCharsets.UTF_8);
        os.write(input, 0, input.length);
    }

    private HttpURLConnection createConnection(HTTPRequestData data) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) data.getUrl().openConnection();

        connection.setRequestMethod(data.getMethod());
        buildHeaders(connection, data.getHeaders());
        connection.setDoOutput(true);
        connection.setUseCaches(false);

        return connection;
    }

    private void buildHeaders(HttpURLConnection connection, HashMap<String, String> headers) {
        Iterator<Map.Entry<String, String>> iterator = headers.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();

            connection.setRequestProperty(entry.getKey(), entry.getValue());

            iterator.remove();
        }
    }

    private HTTPResponseData getResponse(HttpURLConnection connection) {
        HTTPResponseData response = null;
        int responseCode = -1;

        try {
            responseCode = connection.getResponseCode();
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder responseBody = new StringBuilder();
            String responseLine;

            while ((responseLine = br.readLine()) != null) {
                responseBody.append(responseLine.trim());
            }

            response = new HTTPResponseData(responseCode, responseBody.toString());
        } catch (IOException e) {
            if (responseCode > 0) {
                response = new HTTPResponseData(responseCode);
            } else {
                response = new HTTPResponseData(e);
            }
        }

        return response;
    }

}
