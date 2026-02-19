package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.cast.zzbl;
import com.google.android.gms.common.api.internal.zzca;
import java.util.List;

public final class zzbeg extends zzev implements zzbef {
    zzbeg(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.cast.internal.ICastService");
    }

    public final void zza(zzca zzca, String[] strArr, String str, List<zzbl> list) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzca);
        zzbc.writeStringArray(strArr);
        zzbc.writeString(str);
        zzbc.writeTypedList(list);
        zzc(2, zzbc);
    }
}
