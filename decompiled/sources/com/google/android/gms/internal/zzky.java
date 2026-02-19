package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import android.widget.FrameLayout;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.internal.zzks;

final class zzky extends zzks.zza<zzqw> {
    private /* synthetic */ Context val$context;
    private /* synthetic */ zzks zzbis;
    private /* synthetic */ FrameLayout zzbit;
    private /* synthetic */ FrameLayout zzbiu;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzky(zzks zzks, FrameLayout frameLayout, FrameLayout frameLayout2, Context context) {
        zzks.super();
        this.zzbis = zzks;
        this.zzbit = frameLayout;
        this.zzbiu = frameLayout2;
        this.val$context = context;
    }

    public final zzqw zza(zzmb zzmb) throws RemoteException {
        return zzmb.createNativeAdViewDelegate(zzn.zzz(this.zzbit), zzn.zzz(this.zzbiu));
    }

    public final zzqw zzif() throws RemoteException {
        zzqw zzb = this.zzbis.zzbil.zzb(this.val$context, this.zzbit, this.zzbiu);
        if (zzb != null) {
            return zzb;
        }
        zzks.zza(this.val$context, "native_ad_view_delegate");
        return new zznk();
    }
}
