package com.google.android.gms.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.fido.fido2.api.common.MakeCredentialOptions;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialRequestOptions;

public interface zzbvt extends IInterface {
    void zza(zzbvr zzbvr, MakeCredentialOptions makeCredentialOptions) throws RemoteException;

    void zza(zzbvr zzbvr, PublicKeyCredentialRequestOptions publicKeyCredentialRequestOptions) throws RemoteException;
}
