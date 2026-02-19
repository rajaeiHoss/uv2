package com.google.android.gms.cast.framework.media;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzev;
import com.google.android.gms.internal.zzex;

public final class zzc extends zzev implements zzb {
    zzc(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.cast.framework.media.IImagePicker");
    }

    public final WebImage onPickImage(MediaMetadata mediaMetadata, int i) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) mediaMetadata);
        zzbc.writeInt(i);
        Parcel zza = zza(1, zzbc);
        WebImage webImage = (WebImage) zzex.zza(zza, WebImage.CREATOR);
        zza.recycle();
        return webImage;
    }

    public final WebImage zza(MediaMetadata mediaMetadata, ImageHints imageHints) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) mediaMetadata);
        zzex.zza(zzbc, (Parcelable) imageHints);
        Parcel zza = zza(4, zzbc);
        WebImage webImage = (WebImage) zzex.zza(zza, WebImage.CREATOR);
        zza.recycle();
        return webImage;
    }

    public final int zzadx() throws RemoteException {
        Parcel zza = zza(3, zzbc());
        int readInt = zza.readInt();
        zza.recycle();
        return readInt;
    }

    public final IObjectWrapper zzafg() throws RemoteException {
        Parcel zza = zza(2, zzbc());
        IObjectWrapper zzaq = IObjectWrapper.zza.zzaq(zza.readStrongBinder());
        zza.recycle();
        return zzaq;
    }
}
