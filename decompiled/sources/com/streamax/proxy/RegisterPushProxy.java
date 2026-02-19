package com.streamax.proxy;

import android.content.Context;
import com.streamax.client.DevInfoBean;
import com.streamax.client.MyApp;
import com.streamax.client.WebService;
import com.streamax.proxy.RegisterPushMode;

public class RegisterPushProxy {
    public static final int RegisterPushMulti = 1;
    public static final int RegisterPushSingle = 0;

    public static boolean registerPush(int i, WebService webservice, DevInfoBean devInfoBean, MyApp myApp, Context context) {
        if (i == 0) {
            return new RegisterPushMode.RegisterPushSingle().registerPush(webservice, devInfoBean, myApp, context);
        }
        if (i == 1) {
            return new RegisterPushMode.RegisterPushMulti().registerPush(webservice, devInfoBean, myApp, context);
        }
        return false;
    }
}
