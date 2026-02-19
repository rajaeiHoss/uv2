package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzcxf;
import com.google.android.gms.safetynet.SafeBrowsingData;

final class zzcxr extends zzcwz {
    private /* synthetic */ zzcxf.zzf zzkkw;

    zzcxr(zzcxf.zzf zzf) {
        this.zzkkw = zzf;
    }

    public final void zza(Status status, SafeBrowsingData safeBrowsingData) {
        this.zzkkw.setResult(new zzcxf.zzi(status, safeBrowsingData));
    }
}
