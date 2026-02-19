package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;

final class zzas extends zzbj {
    private /* synthetic */ ConnectionResult zzfxw;
    private /* synthetic */ zzar zzfxx;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzas(zzar zzar, zzbh zzbh, ConnectionResult connectionResult) {
        super(zzbh);
        this.zzfxx = zzar;
        this.zzfxw = connectionResult;
    }

    public final void zzajj() {
        this.zzfxx.zzfxt.zze(this.zzfxw);
    }
}
