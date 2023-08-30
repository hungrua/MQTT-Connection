package com.example.EMQX.api;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import okhttp3.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EMQXClientAPI {
    private String username = "5a5372f7f89068f4";
    private String password = "Adz9C2wEnOHKglMDFpJlBKKGyb81foTk10ePT5rD6T5J";

    public String checkExistClient(String clientId) {
        try {

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("http://localhost:18083/api/v5/clients/"+clientId)
                    .header("Content-Type", "application/json")
                    .header("Authorization", Credentials.basic(this.username, this.password))
                    .build();
            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(responseBody,JsonObject.class);
            try{
                String messageCode = jsonObject.get("code").getAsString();
                return messageCode;
            }
            catch (Exception e){
                JsonObject meta = jsonObject.getAsJsonObject("meta");
                int count = meta.get("count").getAsInt();
                return count+"";
            }

        } catch (IOException e) {
            return null;
        }
    }

}
