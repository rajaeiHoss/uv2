package com.google.firebase.database.connection.idl;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzew;
import com.google.android.gms.internal.zzex;
import java.util.List;

public abstract class zzx extends zzew implements zzw {
    public zzx() {
        attachInterface(this, "com.google.firebase.database.connection.idl.IPersistentConnectionDelegate");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        switch (i) {
            case 1:
                zza((List<String>) parcel.createStringArrayList(), IObjectWrapper.zza.zzaq(parcel.readStrongBinder()), zzex.zza(parcel), parcel.readLong());
                break;
            case 2:
                zza((List<String>) parcel.createStringArrayList(), (List<zzak>) parcel.createTypedArrayList(zzak.CREATOR), IObjectWrapper.zza.zzaq(parcel.readStrongBinder()), parcel.readLong());
                break;
            case 3:
                zzbwt();
                break;
            case 4:
                onDisconnect();
                break;
            case 5:
                zzcs(zzex.zza(parcel));
                break;
            case 6:
                zzah(IObjectWrapper.zza.zzaq(parcel.readStrongBinder()));
                break;
            default:
                return false;
        }
        parcel2.writeNoException();
        return true;
    }
}
