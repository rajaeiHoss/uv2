package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzcfg implements Parcelable.Creator<zzcff> {
    public final zzcff createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        String str2 = null;
        String str3 = null;
        byte[] bArr = null;
        long j = 0;
        int i = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    str = zzbgm.zzq(parcel, readInt);
                    break;
                case 2:
                    i = zzbgm.zzg(parcel, readInt);
                    break;
                case 3:
                    str2 = zzbgm.zzq(parcel, readInt);
                    break;
                case 4:
                    str3 = zzbgm.zzq(parcel, readInt);
                    break;
                case 5:
                    j = zzbgm.zzi(parcel, readInt);
                    break;
                case 6:
                    bArr = zzbgm.zzt(parcel, readInt);
                    break;
                default:
                    zzbgm.zzb(parcel, readInt);
                    break;
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzcff(str, i, str2, str3, j, bArr);
    }

    public final zzcff[] newArray(int i) {
        return new zzcff[i];
    }
}
