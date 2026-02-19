package com.google.android.gms.tagmanager;

import android.os.Bundle;
import com.google.android.gms.measurement.AppMeasurement;
import java.util.Map;

final class zzbg extends zzco {
    private /* synthetic */ AppMeasurement zzkpk;

    zzbg(AppMeasurement appMeasurement) {
        this.zzkpk = appMeasurement;
    }

    public final void logEventInternalNoInterceptor(String str, String str2, Bundle bundle, long j) {
        this.zzkpk.logEventInternalNoInterceptor(str, str2, bundle, j);
    }

    public final void zza(zzch zzch) {
        this.zzkpk.registerOnMeasurementEventListener(new zzbi(this, zzch));
    }

    public final void zza(zzck zzck) {
        this.zzkpk.setEventInterceptor(new zzbh(this, zzck));
    }

    public final Map<String, Object> zzbgl() {
        return this.zzkpk.getUserProperties(true);
    }
}
