package com.streamax.client;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

public final class CommonUtilities {
    public static final String API_KEY = "AIzaSyAJTHA-DXON_dJ5HXPMOowrgvpYnoybBH8";
    public static final String DISPLAY_MESSAGE_ACTION = "com.streamax.gcm.DISPLAY_MESSAGE";
    public static final String EXTRA_MESSAGE = "message";
    public static final String SENDER_ID = "918132393478";
    public static final String SERVER_URL = "http://180.188.196.70:8080/gcm-demo/";
    static final String TAG = "CommonUtilities";

    static void displayMessage(Context context, String str) {
        Intent intent = new Intent(DISPLAY_MESSAGE_ACTION);
        Log.v(TAG, "[CommonUtilities]message:" + str);
        intent.putExtra(EXTRA_MESSAGE, str);
        context.sendBroadcast(intent);
    }
}
