package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.fido.fido2.api.common.MakeCredentialOptions;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialRequestOptions;

public final class zzbvu extends zzev implements zzbvt {
    zzbvu(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fido.fido2.internal.regular.IFido2AppService");
    }

    public final void zza(zzbvr zzbvr, MakeCredentialOptions makeCredentialOptions) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzbvr);
        zzex.zza(zzbc, (Parcelable) makeCredentialOptions);
        zzb(1, zzbc);
    }

    public final void zza(zzbvr zzbvr, PublicKeyCredentialRequestOptions publicKeyCredentialRequestOptions) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzbvr);
        zzex.zza(zzbc, (Parcelable) publicKeyCredentialRequestOptions);
        zzb(2, zzbc);
    }
}
