package com.google.android.gms.vision.barcode;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

public final class zzi implements Parcelable.Creator<Barcode.GeoPoint> {
    public final /* synthetic */ Barcode.GeoPoint createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        double d = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        double d2 = 0.0d;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 2) {
                d = zzbgm.zzn(parcel, readInt);
            } else if (i != 3) {
                zzbgm.zzb(parcel, readInt);
            } else {
                d2 = zzbgm.zzn(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new Barcode.GeoPoint(d, d2);
    }

    public final /* synthetic */ Barcode.GeoPoint[] newArray(int i) {
        return new Barcode.GeoPoint[i];
    }
}
