package com.google.android.gms.internal;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

public final class zzbbf extends zzev implements zzbbe {
    zzbbf(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.cast.framework.media.internal.IFetchBitmapTask");
    }

    public final Bitmap zzm(Uri uri) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) uri);
        Parcel zza = zza(1, zzbc);
        Bitmap bitmap = (Bitmap) zzex.zza(zza, Bitmap.CREATOR);
        zza.recycle();
        return bitmap;
    }
}
