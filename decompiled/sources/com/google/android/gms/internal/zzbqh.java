package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.events.OpenFileCallback;

final /* synthetic */ class zzbqh implements zzbqc {
    private final zzbqg zzgwg;
    private final Status zzgwh;

    zzbqh(zzbqg zzbqg, Status status) {
        this.zzgwg = zzbqg;
        this.zzgwh = status;
    }

    public final void accept(Object obj) {
        this.zzgwg.zza(this.zzgwh, (OpenFileCallback) obj);
    }
}
