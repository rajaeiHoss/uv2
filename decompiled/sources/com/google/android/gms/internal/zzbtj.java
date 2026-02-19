package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.events.zzt;

public final class zzbtj implements Parcelable.Creator<zzbti> {
    public final zzbti createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        DriveId driveId = null;
        zzt zzt = null;
        int i = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i2 = 65535 & readInt;
            if (i2 == 2) {
                driveId = (DriveId) zzbgm.zza(parcel, readInt, DriveId.CREATOR);
            } else if (i2 == 3) {
                i = zzbgm.zzg(parcel, readInt);
            } else if (i2 != 4) {
                zzbgm.zzb(parcel, readInt);
            } else {
                zzt = (zzt) zzbgm.zza(parcel, readInt, zzt.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzbti(driveId, i, zzt);
    }

    public final zzbti[] newArray(int i) {
        return new zzbti[i];
    }
}
