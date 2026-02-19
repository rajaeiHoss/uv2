package com.google.android.gms.internal;

import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.nearby.messages.internal.zzq;

public final class zzcvd extends zzq {
    private final zzci<zzn<Status>> zzkes;
    private boolean zzkeu = false;

    public zzcvd(zzci<zzn<Status>> zzci) {
        this.zzkes = zzci;
    }

    public final synchronized void zzap(Status status) throws RemoteException {
        if (!this.zzkeu) {
            this.zzkes.zza(new zzcve(this, status));
            this.zzkeu = true;
            return;
        }
        String valueOf = String.valueOf(status);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 28);
        sb.append("Received multiple statuses: ");
        sb.append(valueOf);
        Log.wtf("NearbyMessagesCallbackWrapper", sb.toString(), new Exception());
    }
}
