package com.hjq.http.config;

import com.hjq.http.model.BodyType;
import com.hjq.http.model.CacheMode;

public interface IRequestServer extends IRequestHost, IRequestPath, IRequestClient, IRequestType, IRequestCache {
    CacheMode getMode();

    String getPath();

    BodyType getType();

    /* renamed from: com.hjq.http.config.IRequestServer$-CC  reason: invalid class name */
    public final /* synthetic */ class CC {
        public static String $default$getPath(IRequestServer iRequestServer) {
            return "";
        }

        public static BodyType $default$getType(IRequestServer _this) {
            return BodyType.FORM;
        }

        public static CacheMode $default$getMode(IRequestServer _this) {
            return CacheMode.DEFAULT;
        }
    }
}
