package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;

public final class zzbsv extends zzbgl {
    public static final Parcelable.Creator<zzbsv> CREATOR = new zzbsw();
    private String zzayo;
    private ParcelFileDescriptor zzgxt;
    private IBinder zzgxu;

    zzbsv(ParcelFileDescriptor parcelFileDescriptor, IBinder iBinder, String str) {
        this.zzgxt = parcelFileDescriptor;
        this.zzgxu = iBinder;
        this.zzayo = str;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, (Parcelable) this.zzgxt, i | 1, false);
        zzbgo.zza(parcel, 3, this.zzgxu, false);
        zzbgo.zza(parcel, 4, this.zzayo, false);
        zzbgo.zzai(parcel, zze);
    }
}
