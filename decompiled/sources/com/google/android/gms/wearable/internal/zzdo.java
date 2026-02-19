package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;

public final class zzdo extends zzbgl {
    public static final Parcelable.Creator<zzdo> CREATOR = new zzdp();
    public final int statusCode;
    public final ParcelFileDescriptor zzluj;

    public zzdo(int i, ParcelFileDescriptor parcelFileDescriptor) {
        this.statusCode = i;
        this.zzluj = parcelFileDescriptor;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 2, this.statusCode);
        zzbgo.zza(parcel, 3, (Parcelable) this.zzluj, i, false);
        zzbgo.zzai(parcel, zze);
    }
}
