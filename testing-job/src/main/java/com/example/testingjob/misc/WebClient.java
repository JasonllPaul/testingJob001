package com.example.testingjob.misc;

import org.sonarsource.scanner.api.internal.shaded.okhttp.OkHttpClient;
import org.sonarsource.scanner.api.internal.shaded.okhttp.Request;
import org.sonarsource.scanner.api.internal.shaded.okhttp.RequestBody;
import org.sonarsource.scanner.api.internal.shaded.okhttp.Response;

import java.io.IOException;

public class WebClient {


    /**
     *
     * @param method type of method to use
     * @param url url to consume the service
     * @param body body and parameters of the service
     * @return
     * @throws IOException
     */
    public Response makeRequest(String method, String url, RequestBody body) throws IOException {
        Response response;

        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            Request request = new Request.Builder()
                    .url(url)
                    .method(method, body)
                    .build();
            response = client.newCall(request).execute();

            return response;
        } catch (Exception e) {
            return null;
        }
    }

}
