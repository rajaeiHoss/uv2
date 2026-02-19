package com.hjq.http.body;

import androidx.lifecycle.LifecycleOwner;
import com.hjq.http.EasyLog;
import com.hjq.http.EasyUtils;
import com.hjq.http.body.ProgressBody;
import com.hjq.http.lifecycle.HttpLifecycleManager;
import com.hjq.http.listener.OnUpdateListener;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

public class ProgressBody extends RequestBody {
    /* access modifiers changed from: private */
    public final LifecycleOwner mLifecycleOwner;
    /* access modifiers changed from: private */
    public final OnUpdateListener<?> mListener;
    private final RequestBody mRequestBody;
    /* access modifiers changed from: private */
    public long mTotalByte;
    /* access modifiers changed from: private */
    public long mUpdateByte;
    /* access modifiers changed from: private */
    public int mUpdateProgress;

    public ProgressBody(RequestBody requestBody, LifecycleOwner lifecycleOwner, OnUpdateListener<?> onUpdateListener) {
        this.mRequestBody = requestBody;
        this.mLifecycleOwner = lifecycleOwner;
        this.mListener = onUpdateListener;
    }

    public MediaType contentType() {
        return this.mRequestBody.contentType();
    }

    public long contentLength() throws IOException {
        return this.mRequestBody.contentLength();
    }

    public void writeTo(BufferedSink bufferedSink) throws IOException {
        this.mTotalByte = contentLength();
        BufferedSink buffer = Okio.buffer((Sink) new WrapperSink(bufferedSink));
        this.mRequestBody.writeTo(buffer);
        buffer.flush();
    }

    private class WrapperSink extends ForwardingSink {
        public WrapperSink(Sink sink) {
            super(sink);
        }

        public void write(Buffer buffer, long j) throws IOException {
            super.write(buffer, j);
            ProgressBody progressBody = ProgressBody.this;
            long unused = progressBody.mUpdateByte = progressBody.mUpdateByte + j;
            EasyUtils.post(new Runnable() {
                public final void run() {
                    ProgressBody.WrapperSink.this.lambda$write$0$ProgressBody$WrapperSink();
                }
            });
        }

        public /* synthetic */ void lambda$write$0$ProgressBody$WrapperSink() {
            if (ProgressBody.this.mListener != null && HttpLifecycleManager.isLifecycleActive(ProgressBody.this.mLifecycleOwner)) {
                ProgressBody.this.mListener.onByte(ProgressBody.this.mTotalByte, ProgressBody.this.mUpdateByte);
            }
            int progressProgress = EasyUtils.getProgressProgress(ProgressBody.this.mTotalByte, ProgressBody.this.mUpdateByte);
            if (progressProgress != ProgressBody.this.mUpdateProgress) {
                int unused = ProgressBody.this.mUpdateProgress = progressProgress;
                if (ProgressBody.this.mListener != null && HttpLifecycleManager.isLifecycleActive(ProgressBody.this.mLifecycleOwner)) {
                    ProgressBody.this.mListener.onProgress(progressProgress);
                }
                EasyLog.print("正在进行上传，总字节：" + ProgressBody.this.mTotalByte + "，已上传：" + ProgressBody.this.mUpdateByte + "，进度：" + progressProgress + "%");
            }
        }
    }
}
