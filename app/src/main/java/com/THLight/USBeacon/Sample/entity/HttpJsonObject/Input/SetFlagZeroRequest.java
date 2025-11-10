package com.THLight.USBeacon.Sample.entity.HttpJsonObject.Input;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public class SetFlagZeroRequest {

    public JSONObject json = new JSONObject();
    public RequestBody body;
    public Request request;
    public SetFlagZeroRequest(String url, String account){

        try {
            json.put("account", account);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        // 封裝成 RequestBody
        this.body = RequestBody.create(
                json.toString(),
                MediaType.parse("application/json; charset=utf-8")
        );

        this.request = new Request.Builder()
                .url(url + "/class/setFlagZero")
                .post(body)
                .build();
    }
}
