package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzu implements Parcelable.Creator<TileOverlayOptions> {
    public final TileOverlayOptions createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        IBinder iBinder = null;
        boolean z = false;
        float f = 0.0f;
        boolean z2 = true;
        float f2 = 0.0f;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 2) {
                iBinder = zzbgm.zzr(parcel, readInt);
            } else if (i == 3) {
                z = zzbgm.zzc(parcel, readInt);
            } else if (i == 4) {
                f = zzbgm.zzl(parcel, readInt);
            } else if (i == 5) {
                z2 = zzbgm.zzc(parcel, readInt);
            } else if (i != 6) {
                zzbgm.zzb(parcel, readInt);
            } else {
                f2 = zzbgm.zzl(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new TileOverlayOptions(iBinder, z, f, z2, f2);
    }

    public final TileOverlayOptions[] newArray(int i) {
        return new TileOverlayOptions[i];
    }
}
