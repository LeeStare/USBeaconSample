package com.THLight.USBeacon.Sample.entity.HttpJsonObject;

import java.util.ArrayList;

public class ApiHelper {
    public interface BooleanCallback {
        void onResult(boolean result);
    }

    public interface StringCallback {
        void onResult(String result);
    }

    public interface ListCallback {
        void onResult(ArrayList<String> data);
    }
}
