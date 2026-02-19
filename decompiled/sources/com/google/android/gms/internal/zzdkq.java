package com.google.android.gms.internal;

import android.content.Context;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class zzdkq {
    private static Integer zzldu = 0;
    private static Integer zzldv = 1;
    private final Context mContext;
    private final ExecutorService zzimc;

    public zzdkq(Context context) {
        this(context, Executors.newSingleThreadExecutor());
    }

    private zzdkq(Context context, ExecutorService executorService) {
        this.mContext = context;
        this.zzimc = executorService;
    }
}
