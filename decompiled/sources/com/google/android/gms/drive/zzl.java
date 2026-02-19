package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzl implements Parcelable.Creator<DriveId> {
    public final /* synthetic */ DriveId createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        long j = 0;
        long j2 = 0;
        String str = null;
        int i = -1;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i2 = 65535 & readInt;
            if (i2 == 2) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (i2 == 3) {
                j = zzbgm.zzi(parcel, readInt);
            } else if (i2 == 4) {
                j2 = zzbgm.zzi(parcel, readInt);
            } else if (i2 != 5) {
                zzbgm.zzb(parcel, readInt);
            } else {
                i = zzbgm.zzg(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new DriveId(str, j, j2, i);
    }

    public final /* synthetic */ DriveId[] newArray(int i) {
        return new DriveId[i];
    }
}
