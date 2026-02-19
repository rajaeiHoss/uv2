package com.google.android.gms.internal;

import android.app.Activity;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.internal.zzks;

final class zzlb extends zzks.zza<zzyq> {
    private /* synthetic */ Activity val$activity;
    private /* synthetic */ zzks zzbis;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzlb(zzks zzks, Activity activity) {
        zzks.super();
        this.zzbis = zzks;
        this.val$activity = activity;
    }

    public final zzyq zza(zzmb zzmb) throws RemoteException {
        return zzmb.createAdOverlay(zzn.zzz(this.val$activity));
    }

    public final zzyq zzif() throws RemoteException {
        zzyq zze = this.zzbis.zzbin.zze(this.val$activity);
        if (zze != null) {
            return zze;
        }
        zzks.zza(this.val$activity, "ad_overlay");
        return null;
    }
}
