package com.hjq.http.listener;

import java.io.File;

public interface OnDownloadListener {

    /* renamed from: com.hjq.http.listener.OnDownloadListener$-CC  reason: invalid class name */
    public final /* synthetic */ class CC {
        public static void $default$onByte(OnDownloadListener onDownloadListener, File file, long j, long j2) {
        }
    }

    void onByte(File file, long j, long j2);

    void onComplete(File file);

    void onEnd(File file);

    void onError(File file, Exception exc);

    void onProgress(File file, int i);

    void onStart(File file);
}
