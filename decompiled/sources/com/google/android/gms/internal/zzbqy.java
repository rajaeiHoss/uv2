package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;

public final class zzbqy extends zzbgl {
    public static final Parcelable.Creator<zzbqy> CREATOR = new zzbqz();
    final IBinder zzgwo;

    zzbqy(IBinder iBinder) {
        this.zzgwo = iBinder;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, this.zzgwo, false);
        zzbgo.zzai(parcel, zze);
    }
}
