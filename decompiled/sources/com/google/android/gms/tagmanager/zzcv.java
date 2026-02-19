package com.google.android.gms.tagmanager;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzdah;
import com.google.android.gms.internal.zzdai;
import com.google.android.gms.internal.zzev;
import com.google.android.gms.internal.zzex;

public final class zzcv extends zzev implements zzct {
    zzcv(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.tagmanager.ITagManagerServiceProvider");
    }

    public final zzdah getService(IObjectWrapper iObjectWrapper, zzcn zzcn, zzce zzce) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) iObjectWrapper);
        zzex.zza(zzbc, (IInterface) zzcn);
        zzex.zza(zzbc, (IInterface) zzce);
        Parcel zza = zza(1, zzbc);
        zzdah zzbr = zzdai.zzbr(zza.readStrongBinder());
        zza.recycle();
        return zzbr;
    }
}
