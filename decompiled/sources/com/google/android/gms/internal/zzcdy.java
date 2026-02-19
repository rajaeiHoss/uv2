package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzcdy implements Parcelable.Creator<zzcdx> {
    public final zzcdx createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        String str2 = null;
        String[] strArr = null;
        int[] iArr = null;
        byte[] bArr = null;
        int i = 0;
        boolean z = false;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    str = zzbgm.zzq(parcel, readInt);
                    break;
                case 4:
                    str2 = zzbgm.zzq(parcel, readInt);
                    break;
                case 5:
                    strArr = zzbgm.zzaa(parcel, readInt);
                    break;
                case 6:
                    i = zzbgm.zzg(parcel, readInt);
                    break;
                case 7:
                    bArr = zzbgm.zzt(parcel, readInt);
                    break;
                case 8:
                    iArr = zzbgm.zzw(parcel, readInt);
                    break;
                case 9:
                    z = zzbgm.zzc(parcel, readInt);
                    break;
                default:
                    zzbgm.zzb(parcel, readInt);
                    break;
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzcdx(str, str2, strArr, iArr, i, bArr, z);
    }

    public final zzcdx[] newArray(int i) {
        return new zzcdx[i];
    }
}
