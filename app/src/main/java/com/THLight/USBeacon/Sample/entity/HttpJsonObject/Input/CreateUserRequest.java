package com.THLight.USBeacon.Sample.entity.HttpJsonObject.Input;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public class CreateUserRequest {
    public JSONObject json = new JSONObject();
    public RequestBody body;
    public Request request;
    public CreateUserRequest(String url, String account, String password, String userName, String phoneNumber){

        try {
            json.put("account", account);
            json.put("password", password);
            json.put("user_name", userName);
            json.put("phone_number", phoneNumber);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        // 封裝成 RequestBody
        this.body = RequestBody.create(
                json.toString(),
                MediaType.parse("application/json; charset=utf-8")
        );

        this.request = new Request.Builder()
                .url(url + "/register_user")
                .post(body)
                .build();
    }
}
