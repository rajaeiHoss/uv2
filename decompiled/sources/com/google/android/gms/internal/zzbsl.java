package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.zzy;

public final class zzbsl extends zzy {
    public static final Parcelable.Creator<zzbsl> CREATOR = new zzbsm();
    final DataHolder zzgxq;

    public zzbsl(DataHolder dataHolder) {
        this.zzgxq = dataHolder;
    }

    /* access modifiers changed from: protected */
    public final void zzaj(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, (Parcelable) this.zzgxq, i, false);
        zzbgo.zzai(parcel, zze);
    }

    public final DataHolder zzaqr() {
        return this.zzgxq;
    }
}
