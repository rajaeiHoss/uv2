package com.google.android.gms.internal;

import com.google.android.gms.drive.events.OpenFileCallback;

final /* synthetic */ class zzbqi implements zzbqc {
    private final zzbsb zzgwi;

    zzbqi(zzbsb zzbsb) {
        this.zzgwi = zzbsb;
    }

    public final void accept(Object obj) {
        zzbsb zzbsb = this.zzgwi;
        ((OpenFileCallback) obj).onProgress(zzbsb.zzgxf, zzbsb.zzgxg);
    }
}
