package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.drive.zzc;

public final class zzbrx extends zzbgl {
    public static final Parcelable.Creator<zzbrx> CREATOR = new zzbry();
    final zzc zzgul;
    final boolean zzgxc;

    public zzbrx(zzc zzc, boolean z) {
        this.zzgul = zzc;
        this.zzgxc = z;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, (Parcelable) this.zzgul, i, false);
        zzbgo.zza(parcel, 3, this.zzgxc);
        zzbgo.zzai(parcel, zze);
    }

    public final zzc zzaqp() {
        return this.zzgul;
    }
}
