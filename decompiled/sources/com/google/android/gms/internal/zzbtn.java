package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.drive.DriveId;
import java.util.ArrayList;

public final class zzbtn implements Parcelable.Creator<zzbtm> {
    public final zzbtm createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        DriveId driveId = null;
        ArrayList<DriveId> arrayList = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 2) {
                driveId = (DriveId) zzbgm.zza(parcel, readInt, DriveId.CREATOR);
            } else if (i != 3) {
                zzbgm.zzb(parcel, readInt);
            } else {
                arrayList = zzbgm.zzc(parcel, readInt, DriveId.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzbtm(driveId, arrayList);
    }

    public final zzbtm[] newArray(int i) {
        return new zzbtm[i];
    }
}
