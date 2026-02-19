package com.google.android.gms.tagmanager;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.measurement.AppMeasurement;

final class zzbi implements AppMeasurement.OnEventListener {
    private /* synthetic */ zzch zzkpm;

    zzbi(zzbg zzbg, zzch zzch) {
        this.zzkpm = zzch;
    }

    public final void onEvent(String str, String str2, Bundle bundle, long j) {
        try {
            this.zzkpm.onEvent(str, str2, bundle, j);
        } catch (RemoteException e) {
            throw new IllegalStateException(e);
        }
    }
}
