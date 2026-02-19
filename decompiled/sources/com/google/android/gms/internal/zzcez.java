package com.google.android.gms.internal;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;

public final class zzcez implements Parcelable.Creator<zzcey> {
    public final zzcey createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        Account[] accountArr = null;
        int i = 0;
        boolean z = false;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i2 = 65535 & readInt;
            if (i2 == 2) {
                i = zzbgm.zzg(parcel, readInt);
            } else if (i2 == 3) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (i2 == 4) {
                accountArr = (Account[]) zzbgm.zzb(parcel, readInt, Account.CREATOR);
            } else if (i2 != 5) {
                zzbgm.zzb(parcel, readInt);
            } else {
                z = zzbgm.zzc(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzcey(i, str, accountArr, z);
    }

    public final zzcey[] newArray(int i) {
        return new zzcey[i];
    }
}
