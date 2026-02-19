package com.google.android.gms.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.fido.fido2.api.common.BrowserMakeCredentialOptions;
import com.google.android.gms.fido.fido2.api.common.BrowserPublicKeyCredentialRequestOptions;

public interface zzbvn extends IInterface {
    void zza(zzbvl zzbvl, BrowserMakeCredentialOptions browserMakeCredentialOptions) throws RemoteException;

    void zza(zzbvl zzbvl, BrowserPublicKeyCredentialRequestOptions browserPublicKeyCredentialRequestOptions) throws RemoteException;
}
