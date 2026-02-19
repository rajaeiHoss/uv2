package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.cast.framework.CastOptions;
import com.google.android.gms.cast.framework.media.CastMediaOptions;
import com.google.android.gms.cast.framework.media.zzd;
import com.google.android.gms.cast.framework.zzab;
import com.google.android.gms.cast.framework.zzh;
import com.google.android.gms.cast.framework.zzj;
import com.google.android.gms.cast.framework.zzl;
import com.google.android.gms.cast.framework.zzr;
import com.google.android.gms.cast.framework.zzt;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzbbe;
import java.util.Map;

public final class zzbai extends zzev implements zzbah {
    zzbai(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.cast.framework.internal.ICastDynamiteModule");
    }

    public final zzd zza(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3, CastMediaOptions castMediaOptions) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) iObjectWrapper);
        zzex.zza(zzbc, (IInterface) iObjectWrapper2);
        zzex.zza(zzbc, (IInterface) iObjectWrapper3);
        zzex.zza(zzbc, (Parcelable) castMediaOptions);
        Parcel zza = zza(4, zzbc);
        zzd zzai = zzd.zza.zzai(zza.readStrongBinder());
        zza.recycle();
        return zzai;
    }

    public final zzj zza(IObjectWrapper iObjectWrapper, CastOptions castOptions, zzbaj zzbaj, Map map) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) iObjectWrapper);
        zzex.zza(zzbc, (Parcelable) castOptions);
        zzex.zza(zzbc, (IInterface) zzbaj);
        zzbc.writeMap(map);
        Parcel zza = zza(1, zzbc);
        zzj zzae = zzj.zza.zzae(zza.readStrongBinder());
        zza.recycle();
        return zzae;
    }

    public final zzl zza(CastOptions castOptions, IObjectWrapper iObjectWrapper, zzh zzh) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) castOptions);
        zzex.zza(zzbc, (IInterface) iObjectWrapper);
        zzex.zza(zzbc, (IInterface) zzh);
        Parcel zza = zza(3, zzbc);
        zzl zzaf = zzl.zza.zzaf(zza.readStrongBinder());
        zza.recycle();
        return zzaf;
    }

    public final zzt zza(String str, String str2, zzab zzab) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeString(str);
        zzbc.writeString(str2);
        zzex.zza(zzbc, (IInterface) zzab);
        Parcel zza = zza(2, zzbc);
        zzt zzah = zzt.zza.zzah(zza.readStrongBinder());
        zza.recycle();
        return zzah;
    }

    public final zzbbe zza(IObjectWrapper iObjectWrapper, zzbbg zzbbg, int i, int i2, boolean z, long j, int i3, int i4, int i5) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) iObjectWrapper);
        zzex.zza(zzbc, (IInterface) zzbbg);
        zzbc.writeInt(i);
        zzbc.writeInt(i2);
        zzex.zza(zzbc, z);
        zzbc.writeLong(j);
        zzbc.writeInt(i3);
        zzbc.writeInt(i4);
        zzbc.writeInt(i5);
        Parcel zza = zza(6, zzbc);
        zzbbe zzaj = zzbbe.zza.zzaj(zza.readStrongBinder());
        zza.recycle();
        return zzaj;
    }

    public final zzr zzc(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) iObjectWrapper);
        zzex.zza(zzbc, (IInterface) iObjectWrapper2);
        zzex.zza(zzbc, (IInterface) iObjectWrapper3);
        Parcel zza = zza(5, zzbc);
        zzr zzag = zzr.zza.zzag(zza.readStrongBinder());
        zza.recycle();
        return zzag;
    }
}
