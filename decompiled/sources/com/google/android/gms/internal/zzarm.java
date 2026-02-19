package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.common.internal.zzbq;

public final class zzarm {
    private final Context mApplicationContext;
    private final Context zzdzh;

    public zzarm(Context context) {
        zzbq.checkNotNull(context);
        Context applicationContext = context.getApplicationContext();
        zzbq.checkNotNull(applicationContext, "Application context can't be null");
        this.mApplicationContext = applicationContext;
        this.zzdzh = applicationContext;
    }

    public final Context getApplicationContext() {
        return this.mApplicationContext;
    }

    public final Context zzyl() {
        return this.zzdzh;
    }
}
