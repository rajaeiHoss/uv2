package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.fitness.result.GoalsResult;

final class zzcal extends zzbyu {
    private /* synthetic */ zzcak zzhmo;

    zzcal(zzcak zzcak) {
        this.zzhmo = zzcak;
    }

    public final void zza(GoalsResult goalsResult) throws RemoteException {
        this.zzhmo.setResult(goalsResult);
    }
}
