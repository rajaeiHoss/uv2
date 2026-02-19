package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.cast.ApplicationMetadata;

public abstract class zzbee extends zzew implements zzbed {
    public zzbee() {
        attachInterface(this, "com.google.android.gms.cast.internal.ICastDeviceControllerListener");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        switch (i) {
            case 1:
                zzbk(parcel.readInt());
                break;
            case 2:
                zza((ApplicationMetadata) zzex.zza(parcel, ApplicationMetadata.CREATOR), parcel.readString(), parcel.readString(), zzex.zza(parcel));
                break;
            case 3:
                zzbf(parcel.readInt());
                break;
            case 4:
                zza(parcel.readString(), parcel.readDouble(), zzex.zza(parcel));
                break;
            case 5:
                zzr(parcel.readString(), parcel.readString());
                break;
            case 6:
                zza(parcel.readString(), parcel.createByteArray());
                break;
            case 7:
                zzbm(parcel.readInt());
                break;
            case 8:
                zzbl(parcel.readInt());
                break;
            case 9:
                onApplicationDisconnected(parcel.readInt());
                break;
            case 10:
                zza(parcel.readString(), parcel.readLong(), parcel.readInt());
                break;
            case 11:
                zzb(parcel.readString(), parcel.readLong());
                break;
            case 12:
                zzb((zzbdd) zzex.zza(parcel, zzbdd.CREATOR));
                break;
            case 13:
                zzb((zzbdx) zzex.zza(parcel, zzbdx.CREATOR));
                break;
            default:
                return false;
        }
        return true;
    }
}
