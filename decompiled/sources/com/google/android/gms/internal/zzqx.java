package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public abstract class zzqx extends zzew implements zzqw {
    public zzqx() {
        attachInterface(this, "com.google.android.gms.ads.internal.formats.client.INativeAdViewDelegate");
    }

    public static zzqw zzl(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeAdViewDelegate");
        return queryLocalInterface instanceof zzqw ? (zzqw) queryLocalInterface : new zzqy(iBinder);
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i == 1) {
            zzb(parcel.readString(), IObjectWrapper.zza.zzaq(parcel.readStrongBinder()));
        } else if (i == 2) {
            IObjectWrapper zzal = zzal(parcel.readString());
            parcel2.writeNoException();
            zzex.zza(parcel2, (IInterface) zzal);
            return true;
        } else if (i == 3) {
            zza(IObjectWrapper.zza.zzaq(parcel.readStrongBinder()));
        } else if (i == 4) {
            destroy();
        } else if (i != 5) {
            return false;
        } else {
            zzb(IObjectWrapper.zza.zzaq(parcel.readStrongBinder()), parcel.readInt());
        }
        parcel2.writeNoException();
        return true;
    }
}
