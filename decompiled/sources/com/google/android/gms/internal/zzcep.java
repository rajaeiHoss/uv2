package com.google.android.gms.internal;

import android.os.ParcelFileDescriptor;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.instantapps.zzd;

final class zzcep implements zzd {
    private /* synthetic */ Status zzetp;
    private /* synthetic */ ParcelFileDescriptor zzipo;

    zzcep(zzceo zzceo, ParcelFileDescriptor parcelFileDescriptor, Status status) {
        this.zzipo = parcelFileDescriptor;
        this.zzetp = status;
    }

    public final ParcelFileDescriptor getFileDescriptor() {
        return this.zzipo;
    }

    public final Status getStatus() {
        return this.zzetp;
    }
}
