package com.google.android.gms.internal;

import android.net.Uri;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzqu extends zzev implements zzqs {
    zzqu(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.formats.client.INativeAdImage");
    }

    public final double getScale() throws RemoteException {
        Parcel zza = zza(3, zzbc());
        double readDouble = zza.readDouble();
        zza.recycle();
        return readDouble;
    }

    public final Uri getUri() throws RemoteException {
        Parcel zza = zza(2, zzbc());
        Uri uri = (Uri) zzex.zza(zza, Uri.CREATOR);
        zza.recycle();
        return uri;
    }

    public final IObjectWrapper zzkb() throws RemoteException {
        Parcel zza = zza(1, zzbc());
        IObjectWrapper zzaq = IObjectWrapper.zza.zzaq(zza.readStrongBinder());
        zza.recycle();
        return zzaq;
    }
}
