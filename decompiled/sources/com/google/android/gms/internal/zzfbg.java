package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.common.internal.zzbq;
import com.google.firebase.storage.zzu;
import java.util.concurrent.Executor;

public final class zzfbg {
    private static boolean zzciw = false;
    private final Handler mHandler;
    private final Executor zzkou;

    public zzfbg(Executor executor) {
        this.zzkou = executor;
        this.mHandler = executor == null ? new Handler(Looper.getMainLooper()) : null;
    }

    public final void zzx(Runnable runnable) {
        zzbq.checkNotNull(runnable);
        Handler handler = this.mHandler;
        if (handler == null) {
            Executor executor = this.zzkou;
            if (executor != null) {
                executor.execute(runnable);
            } else {
                zzu.zzw(runnable);
            }
        } else {
            handler.post(runnable);
        }
    }
}
