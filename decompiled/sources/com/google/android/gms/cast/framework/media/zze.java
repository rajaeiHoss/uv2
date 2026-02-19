package com.google.android.gms.cast.framework.media;

import android.content.Intent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.internal.zzev;
import com.google.android.gms.internal.zzex;

public final class zze extends zzev implements zzd {
    zze(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.cast.framework.media.IMediaNotificationService");
    }

    public final IBinder onBind(Intent intent) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) intent);
        Parcel zza = zza(3, zzbc);
        IBinder readStrongBinder = zza.readStrongBinder();
        zza.recycle();
        return readStrongBinder;
    }

    public final void onCreate() throws RemoteException {
        zzb(1, zzbc());
    }

    public final void onDestroy() throws RemoteException {
        zzb(4, zzbc());
    }

    public final int onStartCommand(Intent intent, int i, int i2) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) intent);
        zzbc.writeInt(i);
        zzbc.writeInt(i2);
        Parcel zza = zza(2, zzbc);
        int readInt = zza.readInt();
        zza.recycle();
        return readInt;
    }
}
