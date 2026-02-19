package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.query.internal.FilterHolder;

public final class zzbtd implements Parcelable.Creator<zzbtc> {
    public final zzbtc createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        String[] strArr = null;
        DriveId driveId = null;
        FilterHolder filterHolder = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 2) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (i == 3) {
                strArr = zzbgm.zzaa(parcel, readInt);
            } else if (i == 4) {
                driveId = (DriveId) zzbgm.zza(parcel, readInt, DriveId.CREATOR);
            } else if (i != 5) {
                zzbgm.zzb(parcel, readInt);
            } else {
                filterHolder = (FilterHolder) zzbgm.zza(parcel, readInt, FilterHolder.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzbtc(str, strArr, driveId, filterHolder);
    }

    public final zzbtc[] newArray(int i) {
        return new zzbtc[i];
    }
}
