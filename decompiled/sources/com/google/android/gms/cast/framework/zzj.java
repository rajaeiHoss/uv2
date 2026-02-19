package com.google.android.gms.cast.framework;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzew;
import java.util.Map;

public interface zzj extends IInterface {

    public static abstract class zza extends zzew implements zzj {
        public static zzj zzae(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.cast.framework.ICastContext");
            return queryLocalInterface instanceof zzj ? (zzj) queryLocalInterface : new zzk(iBinder);
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            throw new NoSuchMethodError();
        }
    }

    boolean isAppVisible() throws RemoteException;

    void zza(zzf zzf) throws RemoteException;

    Bundle zzaef() throws RemoteException;

    zzv zzaeg() throws RemoteException;

    zzp zzaeh() throws RemoteException;

    IObjectWrapper zzaei() throws RemoteException;

    void zzb(zzf zzf) throws RemoteException;

    void zzd(String str, Map map) throws RemoteException;
}
