package com.google.android.gms.internal;

import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

public final class zzcvm extends zzev implements zzcvl {
    zzcvm(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.panorama.internal.IPanoramaService");
    }

    public final void zza(zzcvj zzcvj, Uri uri, Bundle bundle, boolean z) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzcvj);
        zzex.zza(zzbc, (Parcelable) uri);
        zzex.zza(zzbc, (Parcelable) bundle);
        zzex.zza(zzbc, z);
        zzc(1, zzbc);
    }
}
