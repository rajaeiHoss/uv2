package com.google.firebase.database.connection.idl;

import android.os.RemoteException;
import com.google.android.gms.internal.zzeel;
import com.google.android.gms.internal.zzeev;

final class zzz implements zzeev {
    private /* synthetic */ zzq zzncy;

    zzz(IPersistentConnectionImpl iPersistentConnectionImpl, zzq zzq) {
        this.zzncy = zzq;
    }

    public final String zzbwq() {
        try {
            return this.zzncy.zzbwq();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public final boolean zzbwr() {
        try {
            return this.zzncy.zzbwr();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public final zzeel zzbws() {
        try {
            return zza.zza(this.zzncy.zzbxq());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
