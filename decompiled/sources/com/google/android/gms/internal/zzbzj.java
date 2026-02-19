package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.fitness.request.SessionInsertRequest;
import com.google.android.gms.fitness.request.SessionReadRequest;
import com.google.android.gms.fitness.request.zzax;
import com.google.android.gms.fitness.request.zzaz;
import com.google.android.gms.fitness.request.zzbb;
import com.google.android.gms.fitness.request.zzbd;

public final class zzbzj extends zzev implements zzbzi {
    zzbzj(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.IGoogleFitSessionsApi");
    }

    public final void zza(SessionInsertRequest sessionInsertRequest) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) sessionInsertRequest);
        zzb(3, zzbc);
    }

    public final void zza(SessionReadRequest sessionReadRequest) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) sessionReadRequest);
        zzb(4, zzbc);
    }

    public final void zza(zzax zzax) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzax);
        zzb(5, zzbc);
    }

    public final void zza(zzaz zzaz) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzaz);
        zzb(1, zzbc);
    }

    public final void zza(zzbb zzbb) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzbb);
        zzb(2, zzbc);
    }

    public final void zza(zzbd zzbd) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzbd);
        zzb(6, zzbc);
    }
}
