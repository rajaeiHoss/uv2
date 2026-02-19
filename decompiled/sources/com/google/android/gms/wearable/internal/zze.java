package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zze implements Parcelable.Creator<zzd> {
    public final zzd createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        IBinder iBinder = null;
        IntentFilter[] intentFilterArr = null;
        String str = null;
        String str2 = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 2) {
                iBinder = zzbgm.zzr(parcel, readInt);
            } else if (i == 3) {
                intentFilterArr = (IntentFilter[]) zzbgm.zzb(parcel, readInt, IntentFilter.CREATOR);
            } else if (i == 4) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (i != 5) {
                zzbgm.zzb(parcel, readInt);
            } else {
                str2 = zzbgm.zzq(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzd(iBinder, intentFilterArr, str, str2);
    }

    public final zzd[] newArray(int i) {
        return new zzd[i];
    }
}
