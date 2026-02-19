package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzdf;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;

final class zzepr extends zzepo {
    private final Context mApplicationContext;
    private TaskCompletionSource<PendingDynamicLinkData> zzejm;

    public zzepr(Context context, TaskCompletionSource<PendingDynamicLinkData> taskCompletionSource) {
        this.mApplicationContext = context;
        this.zzejm = taskCompletionSource;
    }

    public final void zza(Status status, zzepi zzepi) {
        Bundle bundle;
        zzdf.zza(status, zzepi == null ? null : new PendingDynamicLinkData(zzepi), this.zzejm);
        if (zzepi != null && (bundle = zzepi.zzcdw().getBundle("scionData")) != null && bundle.keySet() != null) {
            try {
                AppMeasurement instance = AppMeasurement.getInstance(this.mApplicationContext);
                for (String str : bundle.keySet()) {
                    instance.logEventInternal("fdl", str, bundle.getBundle(str));
                }
            } catch (NoClassDefFoundError unused) {
            }
        }
    }
}
