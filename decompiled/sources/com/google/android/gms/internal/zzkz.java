package com.google.android.gms.internal;

import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.internal.zzks;
import java.util.HashMap;

final class zzkz extends zzks.zza<zzrb> {
    private /* synthetic */ zzks zzbis;
    private /* synthetic */ View zzbiv;
    private /* synthetic */ HashMap zzbiw;
    private /* synthetic */ HashMap zzbix;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzkz(zzks zzks, View view, HashMap hashMap, HashMap hashMap2) {
        zzks.super();
        this.zzbis = zzks;
        this.zzbiv = view;
        this.zzbiw = hashMap;
        this.zzbix = hashMap2;
    }

    public final zzrb zza(zzmb zzmb) throws RemoteException {
        return zzmb.createNativeAdViewHolderDelegate(zzn.zzz(this.zzbiv), zzn.zzz(this.zzbiw), zzn.zzz(this.zzbix));
    }

    public final zzrb zzif() throws RemoteException {
        zzrb zzb = this.zzbis.zzbio.zzb(this.zzbiv, this.zzbiw, this.zzbix);
        if (zzb != null) {
            return zzb;
        }
        zzks.zza(this.zzbiv.getContext(), "native_ad_view_holder_delegate");
        return new zznl();
    }
}
