package com.hjq.http;

import androidx.lifecycle.LifecycleOwner;
import com.hjq.http.request.DeleteRequest;
import com.hjq.http.request.DownloadRequest;
import com.hjq.http.request.GetRequest;
import com.hjq.http.request.HeadRequest;
import com.hjq.http.request.OptionsRequest;
import com.hjq.http.request.PatchRequest;
import com.hjq.http.request.PostRequest;
import com.hjq.http.request.PutRequest;
import com.hjq.http.request.TraceRequest;
import okhttp3.Call;
import okhttp3.OkHttpClient;

public final class EasyHttp {
    public static GetRequest get(LifecycleOwner lifecycleOwner) {
        return new GetRequest(lifecycleOwner);
    }

    public static PostRequest post(LifecycleOwner lifecycleOwner) {
        return new PostRequest(lifecycleOwner);
    }

    public static HeadRequest head(LifecycleOwner lifecycleOwner) {
        return new HeadRequest(lifecycleOwner);
    }

    public static DeleteRequest delete(LifecycleOwner lifecycleOwner) {
        return new DeleteRequest(lifecycleOwner);
    }

    public static PutRequest put(LifecycleOwner lifecycleOwner) {
        return new PutRequest(lifecycleOwner);
    }

    public static PatchRequest patch(LifecycleOwner lifecycleOwner) {
        return new PatchRequest(lifecycleOwner);
    }

    public static OptionsRequest options(LifecycleOwner lifecycleOwner) {
        return new OptionsRequest(lifecycleOwner);
    }

    public static TraceRequest trace(LifecycleOwner lifecycleOwner) {
        return new TraceRequest(lifecycleOwner);
    }

    public static DownloadRequest download(LifecycleOwner lifecycleOwner) {
        return new DownloadRequest(lifecycleOwner);
    }

    public static void cancel(LifecycleOwner lifecycleOwner) {
        cancel((Object) String.valueOf(lifecycleOwner));
    }

    public static void cancel(Object obj) {
        if (obj != null) {
            OkHttpClient client = EasyConfig.getInstance().getClient();
            for (Call next : client.dispatcher().queuedCalls()) {
                if (obj.equals(next.request().tag())) {
                    next.cancel();
                }
            }
            for (Call next2 : client.dispatcher().runningCalls()) {
                if (obj.equals(next2.request().tag())) {
                    next2.cancel();
                }
            }
        }
    }

    public static void cancel() {
        OkHttpClient client = EasyConfig.getInstance().getClient();
        for (Call cancel : client.dispatcher().queuedCalls()) {
            cancel.cancel();
        }
        for (Call cancel2 : client.dispatcher().runningCalls()) {
            cancel2.cancel();
        }
    }
}
