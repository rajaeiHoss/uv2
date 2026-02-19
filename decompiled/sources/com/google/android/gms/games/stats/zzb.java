package com.google.android.gms.games.stats;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzb implements Parcelable.Creator<zza> {
    public final /* synthetic */ zza createFromParcel(Parcel parcel) {
        Parcel parcel2 = parcel;
        int zzd = zzbgm.zzd(parcel);
        Bundle bundle = null;
        float f = 0.0f;
        float f2 = 0.0f;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        float f3 = 0.0f;
        float f4 = 0.0f;
        float f5 = 0.0f;
        float f6 = 0.0f;
        float f7 = 0.0f;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    f = zzbgm.zzl(parcel2, readInt);
                    break;
                case 2:
                    f2 = zzbgm.zzl(parcel2, readInt);
                    break;
                case 3:
                    i = zzbgm.zzg(parcel2, readInt);
                    break;
                case 4:
                    i2 = zzbgm.zzg(parcel2, readInt);
                    break;
                case 5:
                    i3 = zzbgm.zzg(parcel2, readInt);
                    break;
                case 6:
                    f3 = zzbgm.zzl(parcel2, readInt);
                    break;
                case 7:
                    f4 = zzbgm.zzl(parcel2, readInt);
                    break;
                case 8:
                    bundle = zzbgm.zzs(parcel2, readInt);
                    break;
                case 9:
                    f5 = zzbgm.zzl(parcel2, readInt);
                    break;
                case 10:
                    f6 = zzbgm.zzl(parcel2, readInt);
                    break;
                case 11:
                    f7 = zzbgm.zzl(parcel2, readInt);
                    break;
                default:
                    zzbgm.zzb(parcel2, readInt);
                    break;
            }
        }
        zzbgm.zzaf(parcel2, zzd);
        return new zza(f, f2, i, i2, i3, f3, f4, bundle, f5, f6, f7);
    }

    public final /* synthetic */ zza[] newArray(int i) {
        return new zza[i];
    }
}
