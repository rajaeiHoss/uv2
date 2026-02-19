package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.tagmanager.zzcl;

final class zzdbq extends zzcl {
    final /* synthetic */ zzdbo zzkyv;

    zzdbq(zzdbo zzdbo) {
        this.zzkyv = zzdbo;
    }

    public final void interceptEvent(String str, String str2, Bundle bundle, long j) throws RemoteException {
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 4);
        sb.append(str);
        sb.append("+gtm");
        this.zzkyv.zzkvr.execute(new zzdbr(this, str2, bundle, sb.toString(), j, str));
    }
}
