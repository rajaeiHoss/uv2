package com.google.android.gms.drive.metadata.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;

public final class zzq extends zzbgl {
    public static final Parcelable.Creator<zzq> CREATOR = new zzr();
    final String zzgpw;
    final long zzgpx;
    final int zzgpy;

    public zzq(String str, long j, int i) {
        this.zzgpw = str;
        this.zzgpx = j;
        this.zzgpy = i;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, this.zzgpw, false);
        zzbgo.zza(parcel, 3, this.zzgpx);
        zzbgo.zzc(parcel, 4, this.zzgpy);
        zzbgo.zzai(parcel, zze);
    }
}
