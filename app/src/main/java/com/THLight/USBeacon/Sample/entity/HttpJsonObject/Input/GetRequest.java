package com.THLight.USBeacon.Sample.entity.HttpJsonObject.Input;

import okhttp3.Request;

public class GetRequest {

    public Request request;

    public GetRequest(String url, String getInfo){
        this.request = new Request.Builder()
                .url(url + getInfo)
                .get()
                .build();
    }
}
