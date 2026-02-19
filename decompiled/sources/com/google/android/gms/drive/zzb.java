package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzb implements Parcelable.Creator<zza> {
    public final /* synthetic */ zza createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 2) {
                j = zzbgm.zzi(parcel, readInt);
            } else if (i == 3) {
                j2 = zzbgm.zzi(parcel, readInt);
            } else if (i != 4) {
                zzbgm.zzb(parcel, readInt);
            } else {
                j3 = zzbgm.zzi(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zza(j, j2, j3);
    }

    public final /* synthetic */ zza[] newArray(int i) {
        return new zza[i];
    }
}
