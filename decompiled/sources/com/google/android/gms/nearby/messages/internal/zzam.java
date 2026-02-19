package com.google.android.gms.nearby.messages.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.nearby.messages.Message;

final /* synthetic */ class zzam implements zzbd {
    private final Message zzkdj;

    zzam(Message message) {
        this.zzkdj = message;
    }

    public final void zza(zzah zzah, zzci zzci) throws RemoteException {
        zzah.zza((zzci<zzn<Status>>) zzci, zzaf.zza(this.zzkdj));
    }
}
