package com.google.android.gms.internal;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;

public final class zzatz implements Parcelable.Creator<zzatx> {
    public final zzatx createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        zzauc[] zzaucArr = null;
        String str = null;
        Account account = null;
        boolean z = false;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 1) {
                zzaucArr = (zzauc[]) zzbgm.zzb(parcel, readInt, zzauc.CREATOR);
            } else if (i == 2) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (i == 3) {
                z = zzbgm.zzc(parcel, readInt);
            } else if (i != 4) {
                zzbgm.zzb(parcel, readInt);
            } else {
                account = (Account) zzbgm.zza(parcel, readInt, Account.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzatx(zzaucArr, str, z, account);
    }

    public final zzatx[] newArray(int i) {
        return new zzatx[i];
    }
}
