package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.zzy;

public final class zzbsj extends zzy {
    public static final Parcelable.Creator<zzbsj> CREATOR = new zzbsk();
    final boolean zzgtr;
    final DataHolder zzgxp;

    public zzbsj(DataHolder dataHolder, boolean z) {
        this.zzgxp = dataHolder;
        this.zzgtr = z;
    }

    /* access modifiers changed from: protected */
    public final void zzaj(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, (Parcelable) this.zzgxp, i, false);
        zzbgo.zza(parcel, 3, this.zzgtr);
        zzbgo.zzai(parcel, zze);
    }
}
