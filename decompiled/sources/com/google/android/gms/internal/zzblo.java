package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;

public abstract class zzblo extends zzew implements zzbln {
    public zzblo() {
        attachInterface(this, "com.google.android.gms.contextmanager.internal.IContextManagerPendingResult");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        switch (i) {
            case 1:
                zze((Status) zzex.zza(parcel, Status.CREATOR));
                break;
            case 2:
                zza((Status) zzex.zza(parcel, Status.CREATOR), (DataHolder) zzex.zza(parcel, DataHolder.CREATOR), (DataHolder) zzex.zza(parcel, DataHolder.CREATOR));
                break;
            case 3:
                zza((Status) zzex.zza(parcel, Status.CREATOR), (zzblr) zzex.zza(parcel, zzblr.CREATOR));
                break;
            case 5:
                zza((Status) zzex.zza(parcel, Status.CREATOR), (DataHolder) zzex.zza(parcel, DataHolder.CREATOR));
                break;
            case 6:
                zza((Status) zzex.zza(parcel, Status.CREATOR), (zzazd) zzex.zza(parcel, zzazd.CREATOR));
                break;
            case 7:
                zza((Status) zzex.zza(parcel, Status.CREATOR), (zzbkl) zzex.zza(parcel, zzbkl.CREATOR));
                break;
            case 8:
                zza((Status) zzex.zza(parcel, Status.CREATOR), (zzbkj) zzex.zza(parcel, zzbkj.CREATOR));
                break;
            default:
                return false;
        }
        parcel2.writeNoException();
        return true;
    }
}
