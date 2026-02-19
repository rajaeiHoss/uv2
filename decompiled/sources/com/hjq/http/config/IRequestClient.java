package com.hjq.http.config;

import com.hjq.http.EasyConfig;
import okhttp3.OkHttpClient;

public interface IRequestClient {
    OkHttpClient getClient();

    /* renamed from: com.hjq.http.config.IRequestClient$-CC  reason: invalid class name */
    public final /* synthetic */ class CC {
        public static OkHttpClient $default$getClient(IRequestClient _this) {
            return EasyConfig.getInstance().getClient();
        }
    }
}
