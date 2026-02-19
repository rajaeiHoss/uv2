package com.google.android.gms.auth.account;

import android.accounts.Account;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzew;
import com.google.android.gms.internal.zzex;

public abstract class zzb extends zzew implements zza {
    public zzb() {
        attachInterface(this, "com.google.android.gms.auth.account.IWorkAccountCallback");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i == 1) {
            zzd((Account) zzex.zza(parcel, Account.CREATOR));
        } else if (i != 2) {
            return false;
        } else {
            zzat(zzex.zza(parcel));
        }
        return true;
    }
}
