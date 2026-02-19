package com.google.android.gms.internal;

import android.content.Context;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@zzabh
public final class zzaec {
    /* access modifiers changed from: private */
    public WeakHashMap<Context, zzaee> zzcza = new WeakHashMap<>();

    public final Future<zzaea> zzo(Context context) {
        return zzaid.zza((ExecutorService) zzaid.zzdfi, new zzaed(this, context));
    }
}
