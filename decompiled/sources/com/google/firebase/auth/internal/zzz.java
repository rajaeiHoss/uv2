package com.google.firebase.auth.internal;

import com.google.android.gms.common.api.internal.zzl;

final class zzz implements zzl {
    private /* synthetic */ zzy zzmuj;

    zzz(zzy zzy) {
        this.zzmuj = zzy;
    }

    public final void zzbj(boolean z) {
        if (z) {
            boolean unused = this.zzmuj.zzmui = true;
            this.zzmuj.cancel();
            return;
        }
        boolean unused2 = this.zzmuj.zzmui = false;
        if (this.zzmuj.zzbur()) {
            this.zzmuj.zzmuh.zzbun();
        }
    }
}
