package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.zzck;
import com.google.android.gms.common.internal.zzaq;
import com.google.android.gms.drive.events.ListenerToken;

public final class zzblv implements ListenerToken {
    private final zzck zzgso;
    private zzaq zzgsp = null;

    public zzblv(zzck zzck) {
        this.zzgso = zzck;
    }

    public final boolean cancel() {
        zzaq zzaq = this.zzgsp;
        if (zzaq == null) {
            return false;
        }
        try {
            zzaq.cancel();
            return true;
        } catch (RemoteException unused) {
            return false;
        }
    }

    public final void zza(zzaq zzaq) {
        this.zzgsp = zzaq;
    }

    public final zzck zzaqi() {
        return this.zzgso;
    }
}
