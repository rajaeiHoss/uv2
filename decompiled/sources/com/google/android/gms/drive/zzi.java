package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;

public final class zzi extends zzbgl {
    public static final Parcelable.Creator<zzi> CREATOR = new zzj();
    private long zzgpt;
    private long zzgpu;

    public zzi(long j, long j2) {
        this.zzgpt = j;
        this.zzgpu = j2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, this.zzgpt);
        zzbgo.zza(parcel, 3, this.zzgpu);
        zzbgo.zzai(parcel, zze);
    }
}
