package com.google.android.gms.internal;

import android.os.ParcelFileDescriptor;
import com.google.android.gms.common.api.Status;

final class zzceo extends zzceq {
    private /* synthetic */ zzcem zzipn;

    zzceo(zzcem zzcem) {
        this.zzipn = zzcem;
    }

    public final void zzb(Status status, ParcelFileDescriptor parcelFileDescriptor) {
        this.zzipn.setResult(new zzcep(this, parcelFileDescriptor, status));
    }
}
