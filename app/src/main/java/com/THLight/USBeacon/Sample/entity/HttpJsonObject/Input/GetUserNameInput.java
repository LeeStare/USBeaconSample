package com.THLight.USBeacon.Sample.entity.HttpJsonObject.Input;

import okhttp3.Request;

public class GetUserNameInput {

    public Request request;

    public GetUserNameInput(String url, String getInfo){
        this.request = new Request.Builder()
                .url(url+ "/get_user_name?id=" + getInfo)
                .get()
                .build();
    }
}
