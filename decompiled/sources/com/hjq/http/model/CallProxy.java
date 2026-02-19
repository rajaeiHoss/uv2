package com.hjq.http.model;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;
import okio.Timeout;

public final class CallProxy implements Call {
    private Call mCall;

    public CallProxy(Call call) {
        this.mCall = call;
    }

    public void setCall(Call call) {
        this.mCall = call;
    }

    public Request request() {
        Call call = this.mCall;
        if (call == null) {
            return null;
        }
        return call.request();
    }

    public Response execute() throws IOException {
        Call call = this.mCall;
        if (call == null) {
            return null;
        }
        return call.execute();
    }

    public void enqueue(Callback callback) {
        Call call = this.mCall;
        if (call != null) {
            call.enqueue(callback);
        }
    }

    public void cancel() {
        Call call = this.mCall;
        if (call != null) {
            call.cancel();
        }
    }

    public boolean isExecuted() {
        Call call = this.mCall;
        if (call == null) {
            return false;
        }
        return call.isExecuted();
    }

    public boolean isCanceled() {
        Call call = this.mCall;
        if (call == null) {
            return false;
        }
        return call.isCanceled();
    }

    public Timeout timeout() {
        Call call = this.mCall;
        if (call == null) {
            return null;
        }
        return call.timeout();
    }

    public Call clone() {
        Call call = this.mCall;
        if (call == null) {
            return null;
        }
        return call.clone();
    }
}
