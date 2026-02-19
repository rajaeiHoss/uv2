package com.hjq.http.config;

import androidx.lifecycle.LifecycleOwner;
import java.lang.reflect.Type;
import okhttp3.Request;
import okhttp3.Response;

public interface IRequestHandler {
    Object readCache(LifecycleOwner lifecycleOwner, IRequestApi iRequestApi, Type type) throws Throwable;

    Exception requestFail(LifecycleOwner lifecycleOwner, IRequestApi iRequestApi, Exception exc);

    Request requestStart(LifecycleOwner lifecycleOwner, IRequestApi iRequestApi, Request.Builder builder);

    Object requestSucceed(LifecycleOwner lifecycleOwner, IRequestApi iRequestApi, Response response, Type type) throws Exception;

    boolean writeCache(LifecycleOwner lifecycleOwner, IRequestApi iRequestApi, Response response, Object obj) throws Throwable;

    /* renamed from: com.hjq.http.config.IRequestHandler$-CC  reason: invalid class name */
    public final /* synthetic */ class CC {
        public static Object $default$readCache(IRequestHandler iRequestHandler, LifecycleOwner lifecycleOwner, IRequestApi iRequestApi, Type type) throws Throwable {
            return null;
        }

        public static boolean $default$writeCache(IRequestHandler iRequestHandler, LifecycleOwner lifecycleOwner, IRequestApi iRequestApi, Response response, Object obj) throws Throwable {
            return false;
        }

        public static Request $default$requestStart(IRequestHandler _this, LifecycleOwner lifecycleOwner, IRequestApi iRequestApi, Request.Builder builder) {
            return builder.build();
        }
    }
}
