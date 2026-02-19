package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzg implements Parcelable.Creator<zzf> {
    public final /* synthetic */ zzf createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        boolean z = false;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 1) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (i != 2) {
                zzbgm.zzb(parcel, readInt);
            } else {
                z = zzbgm.zzc(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzf(str, z);
    }

    public final /* synthetic */ zzf[] newArray(int i) {
        return new zzf[i];
    }
}
