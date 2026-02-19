package com.google.android.gms.tagmanager;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzev;
import java.util.Map;

public final class zzcg extends zzev implements zzce {
    zzcg(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.tagmanager.ICustomEvaluatorProxy");
    }

    public final void zzf(String str, Map map) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeString(str);
        zzbc.writeMap(map);
        zzb(1, zzbc);
    }

    public final String zzg(String str, Map map) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeString(str);
        zzbc.writeMap(map);
        Parcel zza = zza(2, zzbc);
        String readString = zza.readString();
        zza.recycle();
        return readString;
    }
}
