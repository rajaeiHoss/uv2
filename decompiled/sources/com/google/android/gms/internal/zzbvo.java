package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.fido.fido2.api.common.BrowserMakeCredentialOptions;
import com.google.android.gms.fido.fido2.api.common.BrowserPublicKeyCredentialRequestOptions;

public final class zzbvo extends zzev implements zzbvn {
    zzbvo(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fido.fido2.internal.privileged.IFido2PrivilegedService");
    }

    public final void zza(zzbvl zzbvl, BrowserMakeCredentialOptions browserMakeCredentialOptions) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzbvl);
        zzex.zza(zzbc, (Parcelable) browserMakeCredentialOptions);
        zzb(1, zzbc);
    }

    public final void zza(zzbvl zzbvl, BrowserPublicKeyCredentialRequestOptions browserPublicKeyCredentialRequestOptions) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzbvl);
        zzex.zza(zzbc, (Parcelable) browserPublicKeyCredentialRequestOptions);
        zzb(2, zzbc);
    }
}
