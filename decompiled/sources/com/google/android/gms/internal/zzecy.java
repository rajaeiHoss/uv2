package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.firebase.crash.FirebaseCrash;
import java.util.concurrent.ExecutorService;

public final class zzecy {
    private final AppMeasurement zzmvj;

    private zzecy(AppMeasurement appMeasurement) {
        this.zzmvj = appMeasurement;
    }

    public static zzecy zzez(Context context) {
        try {
            return new zzecy(AppMeasurement.getInstance(context));
        } catch (NoClassDefFoundError e) {
            String valueOf = String.valueOf(e);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 50);
            sb.append("Unable to log event, missing measurement library: ");
            sb.append(valueOf);
            Log.w("FirebaseCrashAnalytics", sb.toString());
            return null;
        }
    }

    public final void zza(Context context, ExecutorService executorService, FirebaseCrash.zza zza) {
        this.zzmvj.registerOnMeasurementEventListener(new zzecx(context, executorService, zza));
    }

    public final void zza(boolean z, long j) {
        Bundle bundle = new Bundle();
        bundle.putInt(AppMeasurement.Param.FATAL, z ? 1 : 0);
        bundle.putLong(AppMeasurement.Param.TIMESTAMP, j);
        this.zzmvj.logEventInternal(AppMeasurement.CRASH_ORIGIN, AppMeasurement.Event.APP_EXCEPTION, bundle);
    }
}
