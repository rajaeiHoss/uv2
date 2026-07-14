package com.streamax.config.utils;

import com.streamax.config.constant.Constants;

public class JsonUtils {
    public static String getDownJsonString(String jsonBody) {
        return Constants.JsonStringPrefix + jsonBody + Constants.JsonSstringSuffix;
    }

    public static String getUpJsonString(String jsonBody) {
        return Constants.JsonStringPrefix + jsonBody + Constants.JsonSstringSuffix;
    }
}
