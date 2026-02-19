package com.google.android.gms.instantapps;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zza implements Parcelable.Creator<InstantAppIntentData> {
    public final /* synthetic */ InstantAppIntentData createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        Intent intent = null;
        String str = null;
        int i = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i2 = 65535 & readInt;
            if (i2 == 1) {
                intent = (Intent) zzbgm.zza(parcel, readInt, Intent.CREATOR);
            } else if (i2 == 2) {
                i = zzbgm.zzg(parcel, readInt);
            } else if (i2 != 3) {
                zzbgm.zzb(parcel, readInt);
            } else {
                str = zzbgm.zzq(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new InstantAppIntentData(intent, i, str);
    }

    public final /* synthetic */ InstantAppIntentData[] newArray(int i) {
        return new InstantAppIntentData[i];
    }
}
