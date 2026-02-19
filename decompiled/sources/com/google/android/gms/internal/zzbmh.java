package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.drive.DriveFile;

public final class zzbmh implements Parcelable.Creator<zzbmg> {
    public final zzbmg createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        int i = DriveFile.MODE_WRITE_ONLY;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            if ((65535 & readInt) != 2) {
                zzbgm.zzb(parcel, readInt);
            } else {
                i = zzbgm.zzg(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzbmg(i);
    }

    public final zzbmg[] newArray(int i) {
        return new zzbmg[i];
    }
}
