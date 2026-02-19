package com.google.android.gms.internal;

import com.google.android.gms.nearby.connection.PayloadCallback;

final class zzcpv extends zzcps<PayloadCallback> {
    private /* synthetic */ zzctr zzjyc;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcpv(zzcpt zzcpt, zzctr zzctr) {
        super();
        this.zzjyc = zzctr;
    }

    public final void zzu(PayloadCallback payloadCallback) {
        payloadCallback.onPayloadTransferUpdate(this.zzjyc.zzbde(), this.zzjyc.zzbdm());
    }
}
