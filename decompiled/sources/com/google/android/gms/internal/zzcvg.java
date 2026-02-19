package com.google.android.gms.internal;

import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.nearby.messages.StatusCallback;
import com.google.android.gms.nearby.messages.internal.zzy;

public final class zzcvg extends zzy {
    private final zzci<StatusCallback> zzkes;

    public zzcvg(zzci<StatusCallback> zzci) {
        this.zzkes = zzci;
    }

    public final void onPermissionChanged(boolean z) {
        this.zzkes.zza(new zzcvh(this, z));
    }
}
