package com.hjq.http.request;

import android.content.ContentResolver;
import android.net.Uri;
import androidx.lifecycle.LifecycleOwner;
import com.hjq.http.EasyLog;
import com.hjq.http.EasyUtils;
import com.hjq.http.callback.DownloadCallback;
import com.hjq.http.config.IRequestApi;
import com.hjq.http.config.IRequestServer;
import com.hjq.http.config.RequestApi;
import com.hjq.http.config.RequestServer;
import com.hjq.http.lifecycle.HttpLifecycleManager;
import com.hjq.http.listener.OnDownloadListener;
import com.hjq.http.listener.OnHttpListener;
import com.hjq.http.model.BodyType;
import com.hjq.http.model.CallProxy;
import com.hjq.http.model.FileContentResolver;
import com.hjq.http.model.FileWrapper;
import com.hjq.http.model.HttpHeaders;
import com.hjq.http.model.HttpMethod;
import com.hjq.http.model.HttpParams;
import com.hjq.http.model.ResponseClass;
import java.io.File;
import okhttp3.Request;

public final class DownloadRequest extends BaseRequest<DownloadRequest> {
    private CallProxy mCallProxy;
    private FileWrapper mFile;
    private OnDownloadListener mListener;
    private String mMd5;
    private HttpMethod mMethod = HttpMethod.GET;

    public DownloadRequest(LifecycleOwner lifecycleOwner) {
        super(lifecycleOwner);
    }

    public DownloadRequest method(HttpMethod httpMethod) {
        this.mMethod = httpMethod;
        return this;
    }

    public DownloadRequest url(String str) {
        server((IRequestServer) new RequestServer(str));
        api((IRequestApi) new RequestApi(""));
        return this;
    }

    public DownloadRequest file(String str) {
        return file(new File(str));
    }

    public DownloadRequest file(File file) {
        if (file instanceof FileContentResolver) {
            return file((FileContentResolver) file);
        }
        this.mFile = new FileWrapper(file);
        return this;
    }

    public DownloadRequest file(ContentResolver contentResolver, Uri uri) {
        return file(new FileContentResolver(contentResolver, uri));
    }

    public DownloadRequest file(FileContentResolver fileContentResolver) {
        this.mFile = fileContentResolver;
        return this;
    }

    public DownloadRequest md5(String str) {
        this.mMd5 = str;
        return this;
    }

    public DownloadRequest listener(OnDownloadListener onDownloadListener) {
        this.mListener = onDownloadListener;
        return this;
    }

    /* renamed from: com.hjq.http.request.DownloadRequest$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$hjq$http$model$HttpMethod;

        static {
            int[] iArr = new int[com.hjq.http.model.HttpMethod.values().length];
            $SwitchMap$com$hjq$http$model$HttpMethod = iArr;
            try {
                iArr[com.hjq.http.model.HttpMethod.GET.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[com.hjq.http.model.HttpMethod.POST.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public Request createRequest(String str, String str2, HttpParams httpParams, HttpHeaders httpHeaders, BodyType bodyType) {
        int i = AnonymousClass1.$SwitchMap$com$hjq$http$model$HttpMethod[this.mMethod.ordinal()];
        if (i == 1) {
            return new GetRequest(getLifecycleOwner()).createRequest(str, str2, httpParams, httpHeaders, bodyType);
        }
        if (i == 2) {
            return new PostRequest(getLifecycleOwner()).createRequest(str, str2, httpParams, httpHeaders, bodyType);
        }
        throw new IllegalStateException("method nonsupport");
    }

    public DownloadRequest start() {
        long delayMillis = getDelayMillis();
        if (delayMillis > 0) {
            EasyLog.print("RequestDelay", String.valueOf(delayMillis));
        }
        final StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        EasyUtils.postDelayed(new Runnable() {
            public void run() {
                DownloadRequest.this.lambda$start$0$DownloadRequest(stackTrace);
            }
        }, delayMillis);
        return this;
    }

    public /* synthetic */ void lambda$start$0$DownloadRequest(StackTraceElement[] stackTraceElementArr) {
        if (!HttpLifecycleManager.isLifecycleActive(getLifecycleOwner())) {
            EasyLog.print("宿主已被销毁，请求无法进行");
            return;
        }
        EasyLog.print(stackTraceElementArr);
        this.mCallProxy = new CallProxy(createCall());
        new DownloadCallback(this).setFile(this.mFile).setMd5(this.mMd5).setListener(this.mListener).setCall(this.mCallProxy).start();
    }

    public DownloadRequest stop() {
        CallProxy callProxy = this.mCallProxy;
        if (callProxy != null) {
            callProxy.cancel();
        }
        return this;
    }

    public void request(OnHttpListener<?> onHttpListener) {
        throw new IllegalStateException("Call the start method");
    }

    public <Bean> Bean execute(ResponseClass<Bean> responseClass) {
        throw new IllegalStateException("Call the start method");
    }

    public DownloadRequest cancel() {
        throw new IllegalStateException("Call the start method");
    }

    /* access modifiers changed from: protected */
    public String getRequestMethod() {
        return String.valueOf(this.mMethod);
    }
}
