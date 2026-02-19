package com.hjq.http.callback;

import com.hjq.http.EasyConfig;
import com.hjq.http.EasyLog;
import com.hjq.http.EasyUtils;
import com.hjq.http.lifecycle.HttpLifecycleManager;
import com.hjq.http.model.CallProxy;
import com.hjq.http.request.BaseRequest;
import java.io.IOException;
import java.net.SocketTimeoutException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public abstract class BaseCallback implements Callback {
    private final BaseRequest<?> mBaseRequest;
    private CallProxy mCall;
    private int mRetryCount;

    /* access modifiers changed from: protected */
    public abstract void onFailure(Exception exc);

    /* access modifiers changed from: protected */
    public abstract void onResponse(Response response) throws Exception;

    /* access modifiers changed from: protected */
    public abstract void onStart(Call call);

    public BaseCallback(BaseRequest<?> baseRequest) {
        this.mBaseRequest = baseRequest;
        HttpLifecycleManager.bind(baseRequest.getLifecycleOwner());
    }

    public BaseCallback setCall(CallProxy callProxy) {
        this.mCall = callProxy;
        return this;
    }

    public void start() {
        this.mCall.enqueue(this);
        onStart(this.mCall);
    }

    /* access modifiers changed from: protected */
    public CallProxy getCall() {
        return this.mCall;
    }

    public void onResponse(Call call, Response response) {
        try {
            onResponse(response);
        } catch (Exception e) {
            onFailure(e);
        } catch (Throwable th) {
            response.close();
            throw th;
        }
        response.close();
    }

    public void onFailure(Call call, IOException iOException) {
        if (!(iOException instanceof SocketTimeoutException) || this.mRetryCount >= EasyConfig.getInstance().getRetryCount()) {
            onFailure(iOException);
        } else {
            final Call retryCall = call;
            EasyUtils.postDelayed(new Runnable() {
                public void run() {
                    BaseCallback.this.lambda$onFailure$0$BaseCallback(retryCall);
                }
            }, EasyConfig.getInstance().getRetryTime());
        }
    }

    public /* synthetic */ void lambda$onFailure$0$BaseCallback(Call call) {
        if (!HttpLifecycleManager.isLifecycleActive(this.mBaseRequest.getLifecycleOwner())) {
            EasyLog.print("宿主已被销毁，无法对请求进行重试");
            return;
        }
        this.mRetryCount++;
        Call clone = call.clone();
        this.mCall.setCall(clone);
        clone.enqueue(this);
        EasyLog.print("请求超时，正在延迟重试，重试次数：" + this.mRetryCount + "/" + EasyConfig.getInstance().getRetryCount());
    }
}
