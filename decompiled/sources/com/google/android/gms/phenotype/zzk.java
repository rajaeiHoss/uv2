package com.google.android.gms.phenotype;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzk implements Parcelable.Creator<zzi> {
    public final /* synthetic */ zzi createFromParcel(Parcel parcel) {
        Parcel parcel2 = parcel;
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        String str2 = null;
        byte[] bArr = null;
        long j = 0;
        double d = 0.0d;
        boolean z = false;
        int i = 0;
        int i2 = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    str = zzbgm.zzq(parcel2, readInt);
                    break;
                case 3:
                    j = zzbgm.zzi(parcel2, readInt);
                    break;
                case 4:
                    z = zzbgm.zzc(parcel2, readInt);
                    break;
                case 5:
                    d = zzbgm.zzn(parcel2, readInt);
                    break;
                case 6:
                    str2 = zzbgm.zzq(parcel2, readInt);
                    break;
                case 7:
                    bArr = zzbgm.zzt(parcel2, readInt);
                    break;
                case 8:
                    i = zzbgm.zzg(parcel2, readInt);
                    break;
                case 9:
                    i2 = zzbgm.zzg(parcel2, readInt);
                    break;
                default:
                    zzbgm.zzb(parcel2, readInt);
                    break;
            }
        }
        zzbgm.zzaf(parcel2, zzd);
        return new zzi(str, j, z, d, str2, bArr, i, i2);
    }

    public final /* synthetic */ zzi[] newArray(int i) {
        return new zzi[i];
    }
}
