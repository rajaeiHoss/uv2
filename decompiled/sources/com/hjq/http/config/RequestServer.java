package com.hjq.http.config;

import com.hjq.http.annotation.HttpIgnore;
import com.hjq.http.config.IRequestClient;
import com.hjq.http.config.IRequestServer;
import com.hjq.http.model.BodyType;
import com.hjq.http.model.CacheMode;
import okhttp3.OkHttpClient;

public final class RequestServer implements IRequestServer {
    @HttpIgnore
    private final String mHost;
    @HttpIgnore
    private final String mPath;

    public /* synthetic */ OkHttpClient getClient() {
        return IRequestClient.CC.$default$getClient(this);
    }

    public /* synthetic */ CacheMode getMode() {
        return IRequestServer.CC.$default$getMode(this);
    }

    public /* synthetic */ BodyType getType() {
        return IRequestServer.CC.$default$getType(this);
    }

    public RequestServer(String str) {
        this(str, "");
    }

    public RequestServer(String str, String str2) {
        this.mHost = str;
        this.mPath = str2;
    }

    public String getHost() {
        return this.mHost;
    }

    public String getPath() {
        return this.mPath;
    }

    public String toString() {
        return this.mHost + this.mPath;
    }
}
