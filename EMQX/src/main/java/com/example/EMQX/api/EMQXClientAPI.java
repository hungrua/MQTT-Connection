package com.example.EMQX.api;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import okhttp3.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EMQXClientAPI {
    public static void main(String[] args) {
        try {
            String username = "5a5372f7f89068f4";
            String password = "Adz9C2wEnOHKglMDFpJlBKKGyb81foTk10ePT5rD6T5J";
            String clientId = "Trong_Huong";
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("http://localhost:18083/api/v5/clients/"+clientId)
                    .header("Content-Type", "application/json")
                    .header("Authorization", Credentials.basic(username, password))
                    .build();
            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();
            System.out.println(responseBody);
//            Gson gson = new Gson();
//            JsonObject jsonObject = gson.fromJson(responseBody, JsonObject.class);
//            JsonObject meta = jsonObject.getAsJsonObject("meta");
//            int count = meta.get("count").getAsInt();
//            System.out.println("Number of connections: " + count);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
