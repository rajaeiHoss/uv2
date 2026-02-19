package com.hjq.http.listener;

public interface OnUpdateListener<T> extends OnHttpListener<T> {

    /* renamed from: com.hjq.http.listener.OnUpdateListener$-CC  reason: invalid class name */
    public final /* synthetic */ class CC {
        public static void $default$onByte(OnUpdateListener onUpdateListener, long j, long j2) {
        }
    }

    void onByte(long j, long j2);

    void onProgress(int i);
}
