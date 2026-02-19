package com.google.android.gms.people.protomodel;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzg implements Parcelable.Creator<zzh> {
    public final /* synthetic */ zzh createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        Integer num = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 2) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (i != 3) {
                zzbgm.zzb(parcel, readInt);
            } else {
                num = zzbgm.zzh(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzh(str, num);
    }

    public final /* synthetic */ zzh[] newArray(int i) {
        return new zzh[i];
    }
}
