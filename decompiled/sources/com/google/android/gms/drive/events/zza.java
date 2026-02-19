package com.google.android.gms.drive.events;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.internal.zzbgm;

public final class zza implements Parcelable.Creator<ChangeEvent> {
    public final /* synthetic */ ChangeEvent createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        DriveId driveId = null;
        int i = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i2 = 65535 & readInt;
            if (i2 == 2) {
                driveId = (DriveId) zzbgm.zza(parcel, readInt, DriveId.CREATOR);
            } else if (i2 != 3) {
                zzbgm.zzb(parcel, readInt);
            } else {
                i = zzbgm.zzg(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new ChangeEvent(driveId, i);
    }

    public final /* synthetic */ ChangeEvent[] newArray(int i) {
        return new ChangeEvent[i];
    }
}
