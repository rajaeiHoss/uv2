package com.google.android.gms.nearby.messages.internal;

import android.os.RemoteException;
import android.app.PendingIntent;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.api.internal.zzn;

final /* synthetic */ class zzaq implements zzbd {
    private final PendingIntent zzkdn;

    zzaq(PendingIntent pendingIntent) {
        this.zzkdn = pendingIntent;
    }

    public final void zza(zzah zzah, zzci zzci) throws RemoteException {
        zzah.zza((zzci<zzn<Status>>) zzci, this.zzkdn);
    }
}
