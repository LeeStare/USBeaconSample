package com.THLight.USBeacon.Sample.entity.HttpJsonObject.Input;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public class ClassCreateRequest {
    public JSONObject json = new JSONObject();
    public RequestBody body;
    public Request request;
    public ClassCreateRequest(String url, String className, String classroom, String day, String time, int quantity, int flag){

        try {
            json.put("className", className);
            json.put("classroom", classroom);
            json.put("day", day);
            json.put("time", time);
            json.put("quantity", quantity);
            json.put("flag", flag);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        // 封裝成 RequestBody
        this.body = RequestBody.create(
                json.toString(),
                MediaType.parse("application/json; charset=utf-8")
        );

        this.request = new Request.Builder()
                .url(url + "/class_create")
                .post(body)
                .build();
    }
}
