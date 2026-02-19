package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.location.zzj;
import java.util.List;

public final class zzcfv implements Parcelable.Creator<zzcfu> {
    public final zzcfu createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        zzj zzj = zzcfu.zzitn;
        List<zzcfs> list = zzcfu.zzitm;
        String str = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 1) {
                zzj = (zzj) zzbgm.zza(parcel, readInt, zzj.CREATOR);
            } else if (i == 2) {
                list = zzbgm.zzc(parcel, readInt, zzcfs.CREATOR);
            } else if (i != 3) {
                zzbgm.zzb(parcel, readInt);
            } else {
                str = zzbgm.zzq(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzcfu(zzj, list, str);
    }

    public final zzcfu[] newArray(int i) {
        return new zzcfu[i];
    }
}
