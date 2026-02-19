package com.google.android.gms.plus.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.internal.zzaq;
import com.google.android.gms.common.internal.zzar;
import com.google.android.gms.internal.zzev;
import com.google.android.gms.internal.zzex;
import java.util.List;

public final class zzg extends zzev implements zzf {
    zzg(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.plus.internal.IPlusService");
    }

    public final String getAccountName() throws RemoteException {
        Parcel zza = zza(5, zzbc());
        String readString = zza.readString();
        zza.recycle();
        return readString;
    }

    public final zzaq zza(zzb zzb, int i, int i2, int i3, String str) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzb);
        zzbc.writeInt(i);
        zzbc.writeInt(i2);
        zzbc.writeInt(-1);
        zzbc.writeString(str);
        Parcel zza = zza(16, zzbc);
        zzaq zzal = zzar.zzal(zza.readStrongBinder());
        zza.recycle();
        return zzal;
    }

    public final void zza(zzb zzb) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzb);
        zzb(19, zzbc);
    }

    public final void zza(zzb zzb, List<String> list) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzb);
        zzbc.writeStringList(list);
        zzb(34, zzbc);
    }

    public final void zzbem() throws RemoteException {
        zzb(6, zzbc());
    }
}
