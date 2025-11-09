package com.THLight.USBeacon.Sample.entity.HttpJsonObject.Input;

import okhttp3.Request;

public class GetUserNameRequest {

    public Request request;

    public GetUserNameRequest(String url, String getInfo){
        this.request = new Request.Builder()
                .url(url+ "/get_user_name?account=" + getInfo)
                .get()
                .build();
    }
}
