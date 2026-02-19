package com.google.android.gms.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.fido.u2f.api.common.RegisterRequestParams;
import com.google.android.gms.fido.u2f.api.common.SignRequestParams;

public interface zzbvy extends IInterface {
    void zza(zzbvw zzbvw, RegisterRequestParams registerRequestParams) throws RemoteException;

    void zza(zzbvw zzbvw, SignRequestParams signRequestParams) throws RemoteException;
}
