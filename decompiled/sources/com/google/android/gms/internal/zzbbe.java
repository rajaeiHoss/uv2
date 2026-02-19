package com.google.android.gms.internal;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface zzbbe extends IInterface {

    public static abstract class zza extends zzew implements zzbbe {
        public static zzbbe zzaj(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.cast.framework.media.internal.IFetchBitmapTask");
            return queryLocalInterface instanceof zzbbe ? (zzbbe) queryLocalInterface : new zzbbf(iBinder);
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            throw new NoSuchMethodError();
        }
    }

    Bitmap zzm(Uri uri) throws RemoteException;
}
