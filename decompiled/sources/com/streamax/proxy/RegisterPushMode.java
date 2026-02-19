package com.streamax.proxy;

import android.content.Context;
import com.streamax.client.DevInfoBean;
import com.streamax.client.MyApp;
import com.streamax.client.WebService;

public interface RegisterPushMode {
    boolean registerPush(WebService webservice, DevInfoBean devInfoBean, MyApp myApp, Context context);

    public static class RegisterPushSingle implements RegisterPushMode {
        public boolean registerPush(WebService webservice, DevInfoBean devInfoBean, MyApp myApp, Context context) {
            return webservice.Android_Add(myApp.mRegId, devInfoBean.mRemark, devInfoBean.mDevName, MyApp.username, "" + MyApp.loginType, "" + devInfoBean.mDevId);
        }
    }

    public static class RegisterPushMulti implements RegisterPushMode {
        public boolean registerPush(WebService webservice, DevInfoBean devInfoBean, MyApp myApp, Context context) {
            return webservice.Android_AddForMultiApp(myApp.mRegId, devInfoBean.mRemark, devInfoBean.mDevName, MyApp.username, "" + MyApp.loginType, "" + devInfoBean.mDevId, context.getPackageName());
        }
    }
}
