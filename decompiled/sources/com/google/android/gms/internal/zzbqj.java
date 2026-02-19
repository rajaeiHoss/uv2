package com.google.android.gms.internal;

import com.google.android.gms.drive.events.OpenFileCallback;

final /* synthetic */ class zzbqj implements zzbqc {
    private final zzbqg zzgwg;
    private final zzbrx zzgwj;

    zzbqj(zzbqg zzbqg, zzbrx zzbrx) {
        this.zzgwg = zzbqg;
        this.zzgwj = zzbrx;
    }

    public final void accept(Object obj) {
        this.zzgwg.zza(this.zzgwj, (OpenFileCallback) obj);
    }
}
