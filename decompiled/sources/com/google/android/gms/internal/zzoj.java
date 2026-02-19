package com.google.android.gms.internal;

import android.content.Context;
import java.util.concurrent.Callable;

final class zzoj implements Callable<Void> {
    private /* synthetic */ Context val$context;

    zzoj(Context context) {
        this.val$context = context;
    }

    public final Void call() throws Exception {
        zzlc.zzio().initialize(this.val$context);
        return null;
    }
}
