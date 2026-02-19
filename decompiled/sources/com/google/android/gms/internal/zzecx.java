package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.firebase.crash.FirebaseCrash;
import java.util.concurrent.ExecutorService;

final class zzecx implements AppMeasurement.OnEventListener {
    private final Context mContext;
    private final FirebaseCrash.zza zzmvb;
    private final ExecutorService zzmvi;

    public zzecx(Context context, ExecutorService executorService, FirebaseCrash.zza zza) {
        this.mContext = context.getApplicationContext();
        this.zzmvi = executorService;
        this.zzmvb = zza;
    }

    public final void onEvent(String str, String str2, Bundle bundle, long j) {
        FirebaseCrash.zza zza;
        if (str != null && !str.equals(AppMeasurement.CRASH_ORIGIN) && (zza = this.zzmvb) != null) {
            this.zzmvi.submit(new zzecl(this.mContext, zza, str2, j, bundle));
        }
    }
}
