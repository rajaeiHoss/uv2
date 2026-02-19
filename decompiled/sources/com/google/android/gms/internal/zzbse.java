package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.drive.DriveId;

public final class zzbse implements Parcelable.Creator<zzbsd> {
    public final zzbsd createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        DriveId driveId = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            if ((65535 & readInt) != 2) {
                zzbgm.zzb(parcel, readInt);
            } else {
                driveId = (DriveId) zzbgm.zza(parcel, readInt, DriveId.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzbsd(driveId);
    }

    public final zzbsd[] newArray(int i) {
        return new zzbsd[i];
    }
}
