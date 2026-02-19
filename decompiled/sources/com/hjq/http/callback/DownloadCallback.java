package com.hjq.http.callback;

import android.text.TextUtils;
import com.hjq.http.EasyLog;
import com.hjq.http.EasyUtils;
import com.hjq.http.exception.MD5Exception;
import com.hjq.http.exception.NullBodyException;
import com.hjq.http.lifecycle.HttpLifecycleManager;
import com.hjq.http.listener.OnDownloadListener;
import com.hjq.http.model.FileWrapper;
import com.hjq.http.request.BaseRequest;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import okhttp3.Call;
import okhttp3.Response;
import okhttp3.ResponseBody;

public final class DownloadCallback extends BaseCallback {
    private static final String FILE_MD5_REGEX = "^[\\w]{32}$";
    private final BaseRequest<?> mBaseRequest;
    private long mDownloadByte;
    private int mDownloadProgress;
    private FileWrapper mFile;
    private OnDownloadListener mListener;
    private String mMd5;
    private long mTotalByte;

    public DownloadCallback(BaseRequest<?> baseRequest) {
        super(baseRequest);
        this.mBaseRequest = baseRequest;
    }

    public DownloadCallback setFile(FileWrapper fileWrapper) {
        this.mFile = fileWrapper;
        return this;
    }

    public DownloadCallback setMd5(String str) {
        this.mMd5 = str;
        return this;
    }

    public DownloadCallback setListener(OnDownloadListener onDownloadListener) {
        this.mListener = onDownloadListener;
        return this;
    }

    /* access modifiers changed from: protected */
    public void onStart(Call call) {
        EasyUtils.post(new Runnable() {
            public final void run() {
                DownloadCallback.this.lambda$onStart$0$DownloadCallback();
            }
        });
    }

    public /* synthetic */ void lambda$onStart$0$DownloadCallback() {
        if (this.mListener != null && HttpLifecycleManager.isLifecycleActive(this.mBaseRequest.getLifecycleOwner())) {
            this.mListener.onStart(this.mFile);
        }
    }

    /* access modifiers changed from: protected */
    public void onResponse(Response response) throws Exception {
        if (this.mMd5 == null) {
            String header = response.header("Content-MD5");
            if (!TextUtils.isEmpty(header) && header.matches(FILE_MD5_REGEX)) {
                this.mMd5 = header;
            }
        }
        File parentFile = this.mFile.getParentFile();
        if (parentFile != null) {
            FileWrapper.createFolder(parentFile);
        }
        ResponseBody body = response.body();
        if (body != null) {
            long contentLength = body.contentLength();
            this.mTotalByte = contentLength;
            if (contentLength < 0) {
                this.mTotalByte = 0;
            }
            if (TextUtils.isEmpty(this.mMd5) || !this.mFile.isFile() || !this.mMd5.equalsIgnoreCase(FileWrapper.getFileMd5(this.mFile.openInputStream()))) {
                this.mDownloadByte = 0;
                byte[] bArr = new byte[8192];
                InputStream byteStream = body.byteStream();
                OutputStream openOutputStream = this.mFile.openOutputStream();
                while (true) {
                    int read = byteStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    this.mDownloadByte += (long) read;
                    openOutputStream.write(bArr, 0, read);
                    EasyUtils.post(new Runnable() {
                        public final void run() {
                            DownloadCallback.this.lambda$onResponse$2$DownloadCallback();
                        }
                    });
                }
                EasyUtils.closeStream(byteStream);
                EasyUtils.closeStream(openOutputStream);
                String fileMd5 = FileWrapper.getFileMd5(this.mFile.openInputStream());
                if (TextUtils.isEmpty(this.mMd5) || this.mMd5.equalsIgnoreCase(fileMd5)) {
                    EasyUtils.post(new Runnable() {
                        public final void run() {
                            DownloadCallback.this.lambda$onResponse$3$DownloadCallback();
                        }
                    });
                    return;
                }
                throw new MD5Exception("MD5 verify failure", fileMd5);
            }
            EasyUtils.post(new Runnable() {
                public final void run() {
                    DownloadCallback.this.lambda$onResponse$1$DownloadCallback();
                }
            });
            return;
        }
        throw new NullBodyException("The response body is empty");
    }

    public /* synthetic */ void lambda$onResponse$1$DownloadCallback() {
        if (this.mListener != null && HttpLifecycleManager.isLifecycleActive(this.mBaseRequest.getLifecycleOwner())) {
            this.mListener.onComplete(this.mFile);
            this.mListener.onEnd(this.mFile);
        }
    }

    public /* synthetic */ void lambda$onResponse$2$DownloadCallback() {
        if (this.mListener != null && HttpLifecycleManager.isLifecycleActive(this.mBaseRequest.getLifecycleOwner())) {
            this.mListener.onByte(this.mFile, this.mTotalByte, this.mDownloadByte);
            int progressProgress = EasyUtils.getProgressProgress(this.mTotalByte, this.mDownloadByte);
            if (progressProgress != this.mDownloadProgress) {
                this.mDownloadProgress = progressProgress;
                this.mListener.onProgress(this.mFile, progressProgress);
                EasyLog.print(this.mFile.getPath() + " 正在下载，总字节：" + this.mTotalByte + "，已下载：" + this.mDownloadByte + "，进度：" + progressProgress + " %");
            }
        }
    }

    public /* synthetic */ void lambda$onResponse$3$DownloadCallback() {
        if (this.mListener != null && HttpLifecycleManager.isLifecycleActive(this.mBaseRequest.getLifecycleOwner())) {
            this.mListener.onComplete(this.mFile);
            this.mListener.onEnd(this.mFile);
        }
    }

    /* access modifiers changed from: protected */
    public void onFailure(Exception exc) {
        Exception requestFail = this.mBaseRequest.getRequestHandler().requestFail(this.mBaseRequest.getLifecycleOwner(), this.mBaseRequest.getRequestApi(), exc);
        EasyLog.print((Throwable) requestFail);
        final Exception error = requestFail;
        EasyUtils.post(new Runnable() {
            public void run() {
                DownloadCallback.this.lambda$onFailure$4$DownloadCallback(error);
            }
        });
    }

    public /* synthetic */ void lambda$onFailure$4$DownloadCallback(Exception exc) {
        if (this.mListener != null && HttpLifecycleManager.isLifecycleActive(this.mBaseRequest.getLifecycleOwner())) {
            this.mListener.onError(this.mFile, exc);
            this.mListener.onEnd(this.mFile);
        }
    }
}
