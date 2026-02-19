package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.fido.u2f.api.common.RegisterRequestParams;
import com.google.android.gms.fido.u2f.api.common.SignRequestParams;

public final class zzbvz extends zzev implements zzbvy {
    zzbvz(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fido.u2f.internal.regular.IU2fAppService");
    }

    public final void zza(zzbvw zzbvw, RegisterRequestParams registerRequestParams) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzbvw);
        zzex.zza(zzbc, (Parcelable) registerRequestParams);
        zzb(1, zzbc);
    }

    public final void zza(zzbvw zzbvw, SignRequestParams signRequestParams) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzbvw);
        zzex.zza(zzbc, (Parcelable) signRequestParams);
        zzb(2, zzbc);
    }
}
