package com.google.android.gms.internal;

import com.google.android.gms.common.api.internal.zzcl;
import com.google.android.gms.drive.events.OpenFileCallback;

final class zzbqk implements zzcl<OpenFileCallback> {
    private /* synthetic */ zzbqc zzgwk;

    zzbqk(zzbqg zzbqg, zzbqc zzbqc) {
        this.zzgwk = zzbqc;
    }

    public final void zzajh() {
    }

    public final void zzu(OpenFileCallback openFileCallback) {
        this.zzgwk.accept(openFileCallback);
    }
}
