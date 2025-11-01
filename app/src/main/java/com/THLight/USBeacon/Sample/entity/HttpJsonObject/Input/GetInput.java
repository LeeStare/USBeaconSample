package com.THLight.USBeacon.Sample.entity.HttpJsonObject.Input;

import okhttp3.Request;
import okhttp3.RequestBody;

public class GetInput {

    public Request request;
    public GetInput(String url, String getInfo){
        this.request = new Request.Builder()
                .url(url+ "/get_user_name?id=" + getInfo)
                .get()
                .build();
    }
}
