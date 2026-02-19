package com.google.android.gms.cast.framework;

import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzew;
import com.google.android.gms.internal.zzex;

public abstract class zzy extends zzew implements zzx {
    public zzy() {
        attachInterface(this, "com.google.android.gms.cast.framework.ISessionManagerListener");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        switch (i) {
            case 1:
                IObjectWrapper zzady = zzady();
                parcel2.writeNoException();
                zzex.zza(parcel2, (IInterface) zzady);
                break;
            case 2:
                zzu(IObjectWrapper.zza.zzaq(parcel.readStrongBinder()));
                break;
            case 3:
                zzc(IObjectWrapper.zza.zzaq(parcel.readStrongBinder()), parcel.readString());
                break;
            case 4:
                zze(IObjectWrapper.zza.zzaq(parcel.readStrongBinder()), parcel.readInt());
                break;
            case 5:
                zzv(IObjectWrapper.zza.zzaq(parcel.readStrongBinder()));
                break;
            case 6:
                zzf(IObjectWrapper.zza.zzaq(parcel.readStrongBinder()), parcel.readInt());
                break;
            case 7:
                zzd(IObjectWrapper.zza.zzaq(parcel.readStrongBinder()), parcel.readString());
                break;
            case 8:
                zza(IObjectWrapper.zza.zzaq(parcel.readStrongBinder()), zzex.zza(parcel));
                break;
            case 9:
                zzg(IObjectWrapper.zza.zzaq(parcel.readStrongBinder()), parcel.readInt());
                break;
            case 10:
                zzh(IObjectWrapper.zza.zzaq(parcel.readStrongBinder()), parcel.readInt());
                break;
            case 11:
                zzadx();
                parcel2.writeNoException();
                parcel2.writeInt(12211278);
                break;
            default:
                return false;
        }
        parcel2.writeNoException();
        return true;
    }
}
