package com.THLight.USBeacon.Sample.service;

import android.util.Log;

import androidx.annotation.NonNull;

import com.THLight.USBeacon.Sample.entity.HttpJsonObject.ApiHelper;
import com.THLight.USBeacon.Sample.entity.HttpJsonObject.Input.CheckIfExistAccountInput;
import com.THLight.USBeacon.Sample.entity.HttpJsonObject.Input.GetInput;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.*;

public class ApiService {
    private static final String BASE_URL = "https://usbeaconfastapi.onrender.com";
    private final OkHttpClient client = new OkHttpClient();

    public void checkIfExistAccount(String id, String password, ApiHelper.BooleanCallback callback) {
        CheckIfExistAccountInput input = new CheckIfExistAccountInput(BASE_URL, id, password);

        client.newCall(input.request).enqueue(new Callback() {
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
                    callback.onResult(false);
                }
            }
        });
    }

    public void getUserName(String id, ApiHelper.StringCallback callback) {  //回傳使用者名稱
        GetInput input = new GetInput(BASE_URL, id);

        client.newCall(input.request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

                Log.e("API", "連線失敗: " + e.getMessage());
                callback.onResult("");
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                try {
                    String responsBody = response.body().string();
                    JSONObject res = new JSONObject(responsBody);
                    String userName = res.optString("user_name", "");
                    callback.onResult(userName);
                } catch (Exception e) {
                    Log.e("API", "解析錯誤: " + e.getMessage());
                    callback.onResult("");
                }
            }
        });
    }
}
