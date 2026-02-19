package com.google.android.gms.vision.barcode;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;
import com.google.android.gms.vision.barcode.Barcode;

public final class zzn implements Parcelable.Creator<Barcode.WiFi> {
    public final /* synthetic */ Barcode.WiFi createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        String str2 = null;
        int i = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i2 = 65535 & readInt;
            if (i2 == 2) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (i2 == 3) {
                str2 = zzbgm.zzq(parcel, readInt);
            } else if (i2 != 4) {
                zzbgm.zzb(parcel, readInt);
            } else {
                i = zzbgm.zzg(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new Barcode.WiFi(str, str2, i);
    }

    public final /* synthetic */ Barcode.WiFi[] newArray(int i) {
        return new Barcode.WiFi[i];
    }
}
