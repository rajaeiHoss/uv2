package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.fitness.result.DailyTotalResult;

final class zzcau extends zzbyi {
    private /* synthetic */ zzcat zzhmy;

    zzcau(zzcat zzcat) {
        this.zzhmy = zzcat;
    }

    public final void zza(DailyTotalResult dailyTotalResult) throws RemoteException {
        this.zzhmy.setResult(dailyTotalResult);
    }
}
