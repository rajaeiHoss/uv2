package com.google.firebase.auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzb implements Parcelable.Creator<ActionCodeSettings> {
    public final ActionCodeSettings createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        boolean z = false;
        boolean z2 = false;
        int i = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    str = zzbgm.zzq(parcel, readInt);
                    break;
                case 2:
                    str2 = zzbgm.zzq(parcel, readInt);
                    break;
                case 3:
                    str3 = zzbgm.zzq(parcel, readInt);
                    break;
                case 4:
                    str4 = zzbgm.zzq(parcel, readInt);
                    break;
                case 5:
                    z = zzbgm.zzc(parcel, readInt);
                    break;
                case 6:
                    str5 = zzbgm.zzq(parcel, readInt);
                    break;
                case 7:
                    z2 = zzbgm.zzc(parcel, readInt);
                    break;
                case 8:
                    str6 = zzbgm.zzq(parcel, readInt);
                    break;
                case 9:
                    i = zzbgm.zzg(parcel, readInt);
                    break;
                default:
                    zzbgm.zzb(parcel, readInt);
                    break;
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new ActionCodeSettings(str, str2, str3, str4, z, str5, z2, str6, i);
    }

    public final ActionCodeSettings[] newArray(int i) {
        return new ActionCodeSettings[i];
    }
}
