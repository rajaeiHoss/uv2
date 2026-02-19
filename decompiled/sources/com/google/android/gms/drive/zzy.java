package com.google.android.gms.drive;

import android.os.Parcel;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgl;

public abstract class zzy extends zzbgl {
    private volatile transient boolean zzgrl = false;

    public void writeToParcel(Parcel parcel, int i) {
        zzbq.checkState(!this.zzgrl);
        this.zzgrl = true;
        zzaj(parcel, i);
    }

    /* access modifiers changed from: protected */
    public abstract void zzaj(Parcel parcel, int i);
}
