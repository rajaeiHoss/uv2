package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.drive.DriveId;

public final class zzbta implements Parcelable.Creator<zzbsz> {
    public final zzbsz createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        int i = 0;
        DriveId driveId = null;
        int i2 = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i3 = 65535 & readInt;
            if (i3 == 2) {
                driveId = (DriveId) zzbgm.zza(parcel, readInt, DriveId.CREATOR);
            } else if (i3 == 3) {
                i = zzbgm.zzg(parcel, readInt);
            } else if (i3 != 4) {
                zzbgm.zzb(parcel, readInt);
            } else {
                i2 = zzbgm.zzg(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzbsz(driveId, i, i2);
    }

    public final zzbsz[] newArray(int i) {
        return new zzbsz[i];
    }
}
