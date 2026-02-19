package com.google.android.gms.drive.events;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.internal.zzbgm;

public final class zzp implements Parcelable.Creator<zzo> {
    public final /* synthetic */ zzo createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        boolean z = false;
        DataHolder dataHolder = null;
        int i = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i2 = 65535 & readInt;
            if (i2 == 2) {
                dataHolder = (DataHolder) zzbgm.zza(parcel, readInt, DataHolder.CREATOR);
            } else if (i2 == 3) {
                z = zzbgm.zzc(parcel, readInt);
            } else if (i2 != 4) {
                zzbgm.zzb(parcel, readInt);
            } else {
                i = zzbgm.zzg(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzo(dataHolder, z, i);
    }

    public final /* synthetic */ zzo[] newArray(int i) {
        return new zzo[i];
    }
}
