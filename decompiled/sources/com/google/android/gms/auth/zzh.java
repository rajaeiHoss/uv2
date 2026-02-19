package com.google.android.gms.auth;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.internal.zzez;
import java.io.IOException;

final class zzh implements zzj<Bundle> {
    private /* synthetic */ Account zzehs;

    zzh(Account account) {
        this.zzehs = account;
    }

    public final Bundle zzac(IBinder iBinder) throws RemoteException, IOException, GoogleAuthException {
        return (Bundle) zzd.zzp(zzez.zza(iBinder).zza(this.zzehs));
    }
}
