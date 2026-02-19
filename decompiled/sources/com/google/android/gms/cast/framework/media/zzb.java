package com.google.android.gms.cast.framework.media;

import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzew;
import com.google.android.gms.internal.zzex;

public interface zzb extends IInterface {

    public static abstract class zza extends zzew implements zzb {
        public zza() {
            attachInterface(this, "com.google.android.gms.cast.framework.media.IImagePicker");
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            WebImage webImage;
            if (zza(i, parcel, parcel2, i2)) {
                return true;
            }
            if (i != 1) {
                if (i == 2) {
                    IObjectWrapper zzafg = zzafg();
                    parcel2.writeNoException();
                    zzex.zza(parcel2, (IInterface) zzafg);
                } else if (i == 3) {
                    int zzadx = zzadx();
                    parcel2.writeNoException();
                    parcel2.writeInt(zzadx);
                } else if (i != 4) {
                    return false;
                } else {
                    webImage = zza((MediaMetadata) zzex.zza(parcel, MediaMetadata.CREATOR), (ImageHints) zzex.zza(parcel, ImageHints.CREATOR));
                }
                return true;
            }
            webImage = onPickImage((MediaMetadata) zzex.zza(parcel, MediaMetadata.CREATOR), parcel.readInt());
            parcel2.writeNoException();
            zzex.zzb(parcel2, webImage);
            return true;
        }
    }

    WebImage onPickImage(MediaMetadata mediaMetadata, int i) throws RemoteException;

    WebImage zza(MediaMetadata mediaMetadata, ImageHints imageHints) throws RemoteException;

    int zzadx() throws RemoteException;

    IObjectWrapper zzafg() throws RemoteException;
}
