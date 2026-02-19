package com.google.android.gms.internal;

import com.google.android.gms.common.api.internal.zzcq;
import com.google.android.gms.tasks.OnFailureListener;

final class zzcoo implements OnFailureListener {
    private /* synthetic */ zzcq zzjxb;
    private /* synthetic */ zzcon zzjxc;

    zzcoo(zzcon zzcon, zzcq zzcq) {
        this.zzjxc = zzcon;
        this.zzjxb = zzcq;
    }

    public final void onFailure(Exception exc) {
        synchronized (this.zzjxc) {
            this.zzjxc.zzjwz.remove(this.zzjxb.zzakx());
        }
    }
}
