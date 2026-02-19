package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.drive.DriveId;

public final class zzbrh implements Parcelable.Creator<zzbrg> {
    public final zzbrg createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        DriveId driveId = null;
        boolean z = false;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 2) {
                driveId = (DriveId) zzbgm.zza(parcel, readInt, DriveId.CREATOR);
            } else if (i != 3) {
                zzbgm.zzb(parcel, readInt);
            } else {
                z = zzbgm.zzc(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzbrg(driveId, z);
    }

    public final zzbrg[] newArray(int i) {
        return new zzbrg[i];
    }
}
