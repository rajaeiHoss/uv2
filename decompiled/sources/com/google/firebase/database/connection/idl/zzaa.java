package com.google.firebase.database.connection.idl;

import android.os.RemoteException;
import com.google.android.gms.internal.zzefo;

final class zzaa implements zzefo {
    private /* synthetic */ zzah zzncz;

    zzaa(zzah zzah) {
        this.zzncz = zzah;
    }

    public final void zzbc(String str, String str2) {
        try {
            this.zzncz.zzbc(str, str2);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
