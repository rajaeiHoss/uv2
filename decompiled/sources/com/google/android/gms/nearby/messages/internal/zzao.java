package com.google.android.gms.nearby.messages.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.nearby.messages.MessageListener;

final /* synthetic */ class zzao implements zzbd {
    private final zzci zzgvg;

    zzao(zzci zzci) {
        this.zzgvg = zzci;
    }

    public final void zza(zzah zzah, zzci zzci) throws RemoteException {
        zzah.zza((zzci<zzn<Status>>) zzci, (zzci<MessageListener>) this.zzgvg);
    }
}
