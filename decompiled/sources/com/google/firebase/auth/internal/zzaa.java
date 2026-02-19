package com.google.firebase.auth.internal;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;

public final class zzaa implements Executor {
    private static zzaa zzmuk = new zzaa();
    private Handler mHandler = new Handler(Looper.getMainLooper());

    private zzaa() {
    }

    public static zzaa zzbus() {
        return zzmuk;
    }

    public final void execute(Runnable runnable) {
        this.mHandler.post(runnable);
    }
}
