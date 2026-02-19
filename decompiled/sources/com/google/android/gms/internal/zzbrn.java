package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.zza;

public abstract class zzbrn extends zzew implements zzbrm {
    public zzbrn() {
        attachInterface(this, "com.google.android.gms.drive.internal.IDriveServiceCallbacks");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        switch (i) {
            case 1:
                zza((zzbsb) zzex.zza(parcel, zzbsb.CREATOR));
                break;
            case 2:
                zza((zzbsj) zzex.zza(parcel, zzbsj.CREATOR));
                break;
            case 3:
                zza((zzbsd) zzex.zza(parcel, zzbsd.CREATOR));
                break;
            case 4:
                zza((zzbso) zzex.zza(parcel, zzbso.CREATOR));
                break;
            case 5:
                zza((zzbrx) zzex.zza(parcel, zzbrx.CREATOR));
                break;
            case 6:
                onError((Status) zzex.zza(parcel, Status.CREATOR));
                break;
            case 7:
                onSuccess();
                break;
            case 8:
                zza((zzbsl) zzex.zza(parcel, zzbsl.CREATOR));
                break;
            case 9:
                zza((zzbsx) zzex.zza(parcel, zzbsx.CREATOR));
                break;
            case 11:
                zza((zzbsn) zzex.zza(parcel, zzbsn.CREATOR), zzbvg.zzap(parcel.readStrongBinder()));
                break;
            case 12:
                zza((zzbst) zzex.zza(parcel, zzbst.CREATOR));
                break;
            case 13:
                zza((zzbsq) zzex.zza(parcel, zzbsq.CREATOR));
                break;
            case 14:
                zza((zzbrz) zzex.zza(parcel, zzbrz.CREATOR));
                break;
            case 15:
                zzbl(zzex.zza(parcel));
                break;
            case 16:
                zza((zzbsh) zzex.zza(parcel, zzbsh.CREATOR));
                break;
            case 17:
                zza((zza) zzex.zza(parcel, zza.CREATOR));
                break;
            case 18:
                zza((zzbrv) zzex.zza(parcel, zzbrv.CREATOR));
                break;
            case 20:
                zza((zzbri) zzex.zza(parcel, zzbri.CREATOR));
                break;
            case 21:
                zza((zzbtp) zzex.zza(parcel, zzbtp.CREATOR));
                break;
            case 22:
                zza((zzbsv) zzex.zza(parcel, zzbsv.CREATOR));
                break;
            default:
                return false;
        }
        parcel2.writeNoException();
        return true;
    }
}
