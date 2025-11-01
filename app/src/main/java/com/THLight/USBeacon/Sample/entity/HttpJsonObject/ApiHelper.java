package com.THLight.USBeacon.Sample.entity.HttpJsonObject;

public class ApiHelper {
    public interface BooleanCallback {
        void onResult(boolean result);
    }

    public interface IntCallback {
        void onResult(int result);
    }

    public interface StringCallback {
        void onResult(String result);
    }
}
