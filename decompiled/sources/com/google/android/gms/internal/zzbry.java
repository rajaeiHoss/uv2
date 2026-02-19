package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.drive.zzc;

public final class zzbry implements Parcelable.Creator<zzbrx> {
    public final zzbrx createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        zzc zzc = null;
        boolean z = false;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 2) {
                zzc = (zzc) zzbgm.zza(parcel, readInt, zzc.CREATOR);
            } else if (i != 3) {
                zzbgm.zzb(parcel, readInt);
            } else {
                z = zzbgm.zzc(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzbrx(zzc, z);
    }

    public final zzbrx[] newArray(int i) {
        return new zzbrx[i];
    }
}
