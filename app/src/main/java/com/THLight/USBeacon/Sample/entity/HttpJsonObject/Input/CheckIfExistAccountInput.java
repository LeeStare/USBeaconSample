package com.THLight.USBeacon.Sample.entity.HttpJsonObject.Input;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public class CheckIfExistAccountInput {

    public JSONObject json = new JSONObject();
    public RequestBody body;
    public Request request;
    public CheckIfExistAccountInput(String url, String id, String password){

        try {
            json.put("id", id);
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
                .url(url)
                .post(body)
                .build();
    }
}
