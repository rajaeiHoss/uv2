package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzaud implements Parcelable.Creator<zzauc> {
    public final zzauc createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        zzauj zzauj = null;
        byte[] bArr = null;
        int i = -1;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i2 = 65535 & readInt;
            if (i2 == 1) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (i2 == 3) {
                zzauj = (zzauj) zzbgm.zza(parcel, readInt, zzauj.CREATOR);
            } else if (i2 == 4) {
                i = zzbgm.zzg(parcel, readInt);
            } else if (i2 != 5) {
                zzbgm.zzb(parcel, readInt);
            } else {
                bArr = zzbgm.zzt(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzauc(str, zzauj, i, bArr);
    }

    public final zzauc[] newArray(int i) {
        return new zzauc[i];
    }
}
