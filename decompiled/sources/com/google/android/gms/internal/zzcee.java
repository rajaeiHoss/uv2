package com.google.android.gms.internal;

import android.content.pm.PackageInfo;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.instantapps.LaunchData;
import java.util.List;

public abstract class zzcee extends zzew implements zzced {
    public zzcee() {
        attachInterface(this, "com.google.android.gms.instantapps.internal.IInstantAppsCallbacks");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i == 2) {
            zza((Status) zzex.zza(parcel, Status.CREATOR), (zzceh) zzex.zza(parcel, zzceh.CREATOR));
        } else if (i == 9) {
            zza((Status) zzex.zza(parcel, Status.CREATOR), (zzcfb) zzex.zza(parcel, zzcfb.CREATOR));
        } else if (i == 10) {
            zzee(parcel.readInt());
        } else if (i == 12) {
            zza((Status) zzex.zza(parcel, Status.CREATOR), parcel.readInt());
        } else if (i != 13) {
            switch (i) {
                case 18:
                    zza((Status) zzex.zza(parcel, Status.CREATOR), (PackageInfo) zzex.zza(parcel, PackageInfo.CREATOR));
                    break;
                case 19:
                    zza((Status) zzex.zza(parcel, Status.CREATOR), (LaunchData) zzex.zza(parcel, LaunchData.CREATOR));
                    break;
                case 20:
                    zza((Status) zzex.zza(parcel, Status.CREATOR), (List<zzcfi>) parcel.createTypedArrayList(zzcfi.CREATOR));
                    break;
                case 21:
                    zzb((Status) zzex.zza(parcel, Status.CREATOR), (ParcelFileDescriptor) zzex.zza(parcel, ParcelFileDescriptor.CREATOR));
                    break;
                case 22:
                    zza((Status) zzex.zza(parcel, Status.CREATOR), (BitmapTeleporter) zzex.zza(parcel, BitmapTeleporter.CREATOR));
                    break;
                default:
                    return false;
            }
        } else {
            zza((Status) zzex.zza(parcel, Status.CREATOR), (zzcey) zzex.zza(parcel, zzcey.CREATOR));
        }
        return true;
    }
}
