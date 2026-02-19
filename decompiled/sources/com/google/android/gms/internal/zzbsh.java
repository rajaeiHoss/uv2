package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;

public final class zzbsh extends zzbgl {
    public static final Parcelable.Creator<zzbsh> CREATOR = new zzbsi();
    private ParcelFileDescriptor zzgxo;

    public zzbsh(ParcelFileDescriptor parcelFileDescriptor) {
        this.zzgxo = parcelFileDescriptor;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, (Parcelable) this.zzgxo, i | 1, false);
        zzbgo.zzai(parcel, zze);
    }
}
