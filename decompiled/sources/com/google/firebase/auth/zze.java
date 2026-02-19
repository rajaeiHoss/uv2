package com.google.firebase.auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;
import com.google.android.gms.internal.zzeci;

public final class zze implements Parcelable.Creator<zzd> {
    public final zzd createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        String str2 = null;
        String str3 = null;
        zzeci zzeci = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 1) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (i == 2) {
                str2 = zzbgm.zzq(parcel, readInt);
            } else if (i == 3) {
                str3 = zzbgm.zzq(parcel, readInt);
            } else if (i != 4) {
                zzbgm.zzb(parcel, readInt);
            } else {
                zzeci = (zzeci) zzbgm.zza(parcel, readInt, zzeci.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzd(str, str2, str3, zzeci);
    }

    public final zzd[] newArray(int i) {
        return new zzd[i];
    }
}
