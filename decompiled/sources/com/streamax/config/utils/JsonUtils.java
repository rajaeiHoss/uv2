package com.streamax.config.utils;

import com.streamax.config.constant.Constants;

public class JsonUtils {
    public static String getDownJsonString(String str) {
        return Constants.JsonStringPrefix + str + Constants.JsonSstringSuffix;
    }

    public static String getUpJsonString(String str) {
        return Constants.JsonStringPrefix + str + Constants.JsonSstringSuffix;
    }
}
