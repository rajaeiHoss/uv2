package com.google.android.gms.vision.barcode;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;
import com.google.android.gms.vision.barcode.Barcode;

public final class zza implements Parcelable.Creator<Barcode.Address> {
    public final /* synthetic */ Barcode.Address createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        int i = 0;
        String[] strArr = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i2 = 65535 & readInt;
            if (i2 == 2) {
                i = zzbgm.zzg(parcel, readInt);
            } else if (i2 != 3) {
                zzbgm.zzb(parcel, readInt);
            } else {
                strArr = zzbgm.zzaa(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new Barcode.Address(i, strArr);
    }

    public final /* synthetic */ Barcode.Address[] newArray(int i) {
        return new Barcode.Address[i];
    }
}
