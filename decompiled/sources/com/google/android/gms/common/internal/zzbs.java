package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.internal.zzbgm;

public final class zzbs implements Parcelable.Creator<zzbr> {
    public final /* synthetic */ zzbr createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        Account account = null;
        GoogleSignInAccount googleSignInAccount = null;
        int i = 0;
        int i2 = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i3 = 65535 & readInt;
            if (i3 == 1) {
                i = zzbgm.zzg(parcel, readInt);
            } else if (i3 == 2) {
                account = (Account) zzbgm.zza(parcel, readInt, Account.CREATOR);
            } else if (i3 == 3) {
                i2 = zzbgm.zzg(parcel, readInt);
            } else if (i3 != 4) {
                zzbgm.zzb(parcel, readInt);
            } else {
                googleSignInAccount = (GoogleSignInAccount) zzbgm.zza(parcel, readInt, GoogleSignInAccount.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzbr(i, account, i2, googleSignInAccount);
    }

    public final /* synthetic */ zzbr[] newArray(int i) {
        return new zzbr[i];
    }
}
