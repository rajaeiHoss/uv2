package com.google.android.gms.internal;

import com.google.android.gms.nearby.messages.StatusCallback;

final class zzcvh extends zzcvf<StatusCallback> {
    private /* synthetic */ boolean zzkev;

    zzcvh(zzcvg zzcvg, boolean z) {
        this.zzkev = z;
    }

    public final void zzu(StatusCallback statusCallback) {
        statusCallback.onPermissionChanged(this.zzkev);
    }
}
