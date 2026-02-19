package com.hjq.http;

import com.hjq.http.config.ILogStrategy;
import com.hjq.http.config.IRequestHandler;
import com.hjq.http.config.IRequestInterceptor;
import com.hjq.http.config.IRequestServer;
import com.hjq.http.config.LogStrategy;
import com.hjq.http.config.RequestServer;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import okhttp3.OkHttpClient;

public final class EasyConfig {
    private static volatile EasyConfig sConfig;
    private OkHttpClient mClient;
    private IRequestHandler mHandler;
    private HashMap<String, String> mHeaders;
    private IRequestInterceptor mInterceptor;
    private boolean mLogEnabled = true;
    private ILogStrategy mLogStrategy;
    private String mLogTag = "EasyHttp";
    private HashMap<String, Object> mParams;
    private int mRetryCount;
    private long mRetryTime = 2000;
    private IRequestServer mServer;

    public static EasyConfig getInstance() {
        if (sConfig != null) {
            return sConfig;
        }
        throw new IllegalStateException("You haven't initialized the configuration yet");
    }

    private static void setInstance(EasyConfig easyConfig) {
        sConfig = easyConfig;
    }

    public static EasyConfig with(OkHttpClient okHttpClient) {
        return new EasyConfig(okHttpClient);
    }

    private EasyConfig(OkHttpClient okHttpClient) {
        this.mClient = okHttpClient;
        this.mParams = new HashMap<>();
        this.mHeaders = new HashMap<>();
    }

    public EasyConfig setServer(String str) {
        return setServer((IRequestServer) new RequestServer(str));
    }

    public EasyConfig setServer(IRequestServer iRequestServer) {
        this.mServer = iRequestServer;
        return this;
    }

    public EasyConfig setHandler(IRequestHandler iRequestHandler) {
        this.mHandler = iRequestHandler;
        return this;
    }

    public EasyConfig setInterceptor(IRequestInterceptor iRequestInterceptor) {
        this.mInterceptor = iRequestInterceptor;
        return this;
    }

    public EasyConfig setClient(OkHttpClient okHttpClient) {
        this.mClient = okHttpClient;
        if (okHttpClient != null) {
            return this;
        }
        throw new IllegalArgumentException("The OkHttp client object cannot be empty");
    }

    public EasyConfig setParams(HashMap<String, Object> hashMap) {
        if (hashMap == null) {
            hashMap = new HashMap<>();
        }
        this.mParams = hashMap;
        return this;
    }

    public EasyConfig setHeaders(HashMap<String, String> hashMap) {
        if (hashMap == null) {
            hashMap = new HashMap<>();
        }
        this.mHeaders = hashMap;
        return this;
    }

    public EasyConfig addHeader(String str, String str2) {
        if (!(str == null || str2 == null)) {
            this.mHeaders.put(str, str2);
        }
        return this;
    }

    public EasyConfig removeHeader(String str) {
        if (str != null) {
            this.mHeaders.remove(str);
        }
        return this;
    }

    public EasyConfig addParam(String str, String str2) {
        if (!(str == null || str2 == null)) {
            this.mParams.put(str, str2);
        }
        return this;
    }

    public EasyConfig removeParam(String str) {
        if (str != null) {
            this.mParams.remove(str);
        }
        return this;
    }

    public EasyConfig setLogStrategy(ILogStrategy iLogStrategy) {
        this.mLogStrategy = iLogStrategy;
        return this;
    }

    public EasyConfig setLogEnabled(boolean z) {
        this.mLogEnabled = z;
        return this;
    }

    public EasyConfig setLogTag(String str) {
        this.mLogTag = str;
        return this;
    }

    public EasyConfig setRetryCount(int i) {
        if (i >= 0) {
            this.mRetryCount = i;
            return this;
        }
        throw new IllegalArgumentException("The number of retries must be greater than 0");
    }

    public EasyConfig setRetryTime(long j) {
        if (j >= 0) {
            this.mRetryTime = j;
            return this;
        }
        throw new IllegalArgumentException("The retry time must be greater than 0");
    }

    public IRequestServer getServer() {
        return this.mServer;
    }

    public IRequestHandler getHandler() {
        return this.mHandler;
    }

    public IRequestInterceptor getInterceptor() {
        return this.mInterceptor;
    }

    public OkHttpClient getClient() {
        return this.mClient;
    }

    public HashMap<String, Object> getParams() {
        return this.mParams;
    }

    public HashMap<String, String> getHeaders() {
        return this.mHeaders;
    }

    public ILogStrategy getLogStrategy() {
        return this.mLogStrategy;
    }

    public boolean isLogEnabled() {
        return this.mLogEnabled && this.mLogStrategy != null;
    }

    public String getLogTag() {
        return this.mLogTag;
    }

    public int getRetryCount() {
        return this.mRetryCount;
    }

    public long getRetryTime() {
        return this.mRetryTime;
    }

    public void into() {
        if (this.mClient == null) {
            throw new IllegalArgumentException("The OkHttp client object cannot be empty");
        } else if (this.mServer == null) {
            throw new IllegalArgumentException("The host configuration cannot be empty");
        } else if (this.mHandler != null) {
            try {
                new URL(this.mServer.getHost() + this.mServer.getPath());
                if (this.mLogStrategy == null) {
                    this.mLogStrategy = new LogStrategy();
                }
                setInstance(this);
            } catch (MalformedURLException unused) {
                throw new IllegalArgumentException("The configured host path url address is not correct");
            }
        } else {
            throw new IllegalArgumentException("The object being processed by the request cannot be empty");
        }
    }
}
