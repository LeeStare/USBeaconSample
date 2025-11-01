package com.THLight.USBeacon.Sample.service;

import androidx.annotation.NonNull;

import com.THLight.USBeacon.Sample.entity.HttpJsonObject.ApiHelper;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.*;

public class ApiService {
    private static final String BASE_URL = "https://usbeaconfastapi.onrender.com";
    private final OkHttpClient client = new OkHttpClient();

    public void checkIfExistAccount(String id, String password, ApiHelper.BooleanCallback callback) {
        JSONObject json = new JSONObject();
        try {
            json.put("id", id);
            json.put("password", password);
        } catch (Exception e) {
            //e.printStackTrace();
        }

        RequestBody body = RequestBody.create(
                json.toString(),
                MediaType.parse("application/json; charset=utf-8")
        );

        Request request = new Request.Builder()
                .url(BASE_URL + "/check_account")
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onResult(false);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                try {
                    JSONObject res = new JSONObject(response.body().string());
                    boolean exist = res.optBoolean("exist", false);
                    callback.onResult(exist);
                } catch (Exception e) {
                    //e.printStackTrace();
                    callback.onResult(false);
                }
            }
        });
    }
}
