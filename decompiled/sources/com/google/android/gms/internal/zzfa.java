package com.google.android.gms.internal;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.auth.AccountChangeEventsRequest;
import com.google.android.gms.auth.AccountChangeEventsResponse;

public final class zzfa extends zzev implements zzey {
    zzfa(IBinder iBinder) {
        super(iBinder, "com.google.android.auth.IAuthManagerService");
    }

    public final Bundle zza(Account account) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) account);
        Parcel zza = zza(7, zzbc);
        Bundle bundle = (Bundle) zzex.zza(zza, Bundle.CREATOR);
        zza.recycle();
        return bundle;
    }

    public final Bundle zza(Account account, String str, Bundle bundle) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) account);
        zzbc.writeString(str);
        zzex.zza(zzbc, (Parcelable) bundle);
        Parcel zza = zza(5, zzbc);
        Bundle bundle2 = (Bundle) zzex.zza(zza, Bundle.CREATOR);
        zza.recycle();
        return bundle2;
    }

    public final Bundle zza(String str, Bundle bundle) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeString(str);
        zzex.zza(zzbc, (Parcelable) bundle);
        Parcel zza = zza(2, zzbc);
        Bundle bundle2 = (Bundle) zzex.zza(zza, Bundle.CREATOR);
        zza.recycle();
        return bundle2;
    }

    public final AccountChangeEventsResponse zza(AccountChangeEventsRequest accountChangeEventsRequest) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) accountChangeEventsRequest);
        Parcel zza = zza(3, zzbc);
        AccountChangeEventsResponse accountChangeEventsResponse = (AccountChangeEventsResponse) zzex.zza(zza, AccountChangeEventsResponse.CREATOR);
        zza.recycle();
        return accountChangeEventsResponse;
    }

    public final Bundle zzp(String str) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeString(str);
        Parcel zza = zza(8, zzbc);
        Bundle bundle = (Bundle) zzex.zza(zza, Bundle.CREATOR);
        zza.recycle();
        return bundle;
    }
}
