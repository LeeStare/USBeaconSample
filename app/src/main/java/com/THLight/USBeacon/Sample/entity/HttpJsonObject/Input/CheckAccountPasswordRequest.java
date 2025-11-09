package com.THLight.USBeacon.Sample.entity.HttpJsonObject.Input;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public class CheckAccountPasswordRequest {

    public JSONObject json = new JSONObject();
    public RequestBody body;
    public Request request;
    public CheckAccountPasswordRequest(String url, String account, String password){

        try {
            json.put("account", account);
            json.put("password", password);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        // 封裝成 RequestBody
        this.body = RequestBody.create(
                json.toString(),
                MediaType.parse("application/json; charset=utf-8")
        );

        this.request = new Request.Builder()
                .url(url + "/check_account_exist")
                .post(body)
                .build();
    }
}
