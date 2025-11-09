package com.THLight.USBeacon.Sample.service;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;

import com.THLight.USBeacon.Sample.entity.HttpJsonObject.ApiHelper;
import com.THLight.USBeacon.Sample.entity.HttpJsonObject.Input.CheckAccountPasswordRequest;
import com.THLight.USBeacon.Sample.entity.HttpJsonObject.Input.CreateUserRequest;
import com.THLight.USBeacon.Sample.entity.HttpJsonObject.Input.GetRequest;
import com.THLight.USBeacon.Sample.entity.HttpJsonObject.Input.GetUserNameRequest;
import com.THLight.USBeacon.Sample.entity.HttpJsonObject.Input.SetFlagZeroRequest;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.*;

public class ApiService {
    private static final String BASE_URL = "https://usbeaconfastapi.onrender.com";
    private final OkHttpClient client = new OkHttpClient();

    // user_Api
    public void checkAccountPassword(String account, String password, ApiHelper.BooleanCallback callback) {
        CheckAccountPasswordRequest request = new CheckAccountPasswordRequest(BASE_URL, account, password);

        client.newCall(request.request).enqueue(new Callback() {
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

    public void checkAccountExist(String account, ApiHelper.BooleanCallback callback) {
        GetRequest request = new GetRequest(BASE_URL+ "/check_account_exist?account=", account);

        client.newCall(request.request).enqueue(new Callback() {
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

    public void getUserName(String account, ApiHelper.StringCallback callback) {  //回傳使用者名稱
        GetUserNameRequest request = new GetUserNameRequest(BASE_URL, account);

        client.newCall(request.request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

                Log.e("API", "連線失敗: " + e.getMessage());
                callback.onResult("");
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                try {
                    String responseBody = response.body().string();
                    JSONObject res = new JSONObject(responseBody);
                    String userName = res.optString("user_name", "");
                    callback.onResult(userName);
                } catch (Exception e) {
                    Log.e("API", "解析錯誤: " + e.getMessage());
                    callback.onResult("");
                }
            }
        });
    }

    public void createUser(String account, String password, String user_name, String phone_number, ApiHelper.BooleanCallback callback) {
        try {
            CreateUserRequest request = new CreateUserRequest(BASE_URL, account, password, user_name, phone_number);
            client.newCall(request.request).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    Log.e("API", "連線失敗: " + e.getMessage());
                    new Handler(Looper.getMainLooper()).post(() -> callback.onResult(false));
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    try (ResponseBody responseBody = response.body()) {
                        String responseText = responseBody.string();
                        JSONObject res = new JSONObject(responseText);
                        boolean success = res.optBoolean("success", false);
                        new Handler(Looper.getMainLooper()).post(() -> callback.onResult(success));
                    } catch (Exception e) {
                        Log.e("API", "解析錯誤: " + e.getMessage());
                        new Handler(Looper.getMainLooper()).post(() -> callback.onResult(false));
                    }
                }
            });
        } catch (Exception e) {
            callback.onResult(false);
        }
    }

    // class_Api
    public void getClassName(ApiHelper.StringCallback callback) {  //回傳flag=1的課程
        GetRequest input = new GetRequest(BASE_URL, "/get_class_name");

        client.newCall(input.request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

                Log.e("API", "連線失敗: " + e.getMessage());
                callback.onResult("");
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                try {
                    String responseBody = response.body().string();
                    JSONObject res = new JSONObject(responseBody);
                    String userName = res.optString("className", "");
                    callback.onResult(userName);
                } catch (Exception e) {
                    Log.e("API", "解析錯誤: " + e.getMessage());
                    callback.onResult("");
                }
            }
        });
    }

    public void setFlagZero(String user, ApiHelper.BooleanCallback callback) {
        SetFlagZeroRequest request = new SetFlagZeroRequest(BASE_URL, user);

        client.newCall(request.request).enqueue(new Callback() {
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

                Log.e("API", "連線失敗: " + e.getMessage());
                new Handler(Looper.getMainLooper()).post(() -> callback.onResult(false));
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                try (ResponseBody responseBody = response.body()) {
                    String responseText = responseBody.string();
                    JSONObject res = new JSONObject(responseText);
                    boolean success = res.optBoolean("success", false);
                    new Handler(Looper.getMainLooper()).post(() -> callback.onResult(success));
                } catch (Exception e) {
                    Log.e("API", "解析錯誤: " + e.getMessage());
                    new Handler(Looper.getMainLooper()).post(() -> callback.onResult(false));
                }
            }
        });
    }
}
