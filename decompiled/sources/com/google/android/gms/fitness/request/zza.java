package com.google.android.gms.fitness.request;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.fitness.data.BleDevice;

public final class zza extends zzaf {
    private final zzci<BleScanCallback> zzhnp;

    private zza(zzci<BleScanCallback> zzci) {
        this.zzhnp = (zzci) zzbq.checkNotNull(zzci);
    }

    /* synthetic */ zza(zzci zzci, zzb zzb) {
        this(zzci);
    }

    public final void onDeviceFound(BleDevice bleDevice) throws RemoteException {
        this.zzhnp.zza(new zzb(this, bleDevice));
    }

    public final void onScanStopped() throws RemoteException {
        this.zzhnp.zza(new zzc(this));
    }

    public final void release() {
        this.zzhnp.clear();
    }
}
