package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.drive.zzv;
import java.util.ArrayList;

public final class zzbrj implements Parcelable.Creator<zzbri> {
    public final zzbri createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        ArrayList<zzv> arrayList = null;
        int i = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i2 = 65535 & readInt;
            if (i2 == 2) {
                arrayList = zzbgm.zzc(parcel, readInt, zzv.CREATOR);
            } else if (i2 != 3) {
                zzbgm.zzb(parcel, readInt);
            } else {
                i = zzbgm.zzg(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzbri(arrayList, i);
    }

    public final zzbri[] newArray(int i) {
        return new zzbri[i];
    }
}
