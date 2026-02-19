package com.google.android.gms.nearby.messages.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzi implements Parcelable.Creator<zzh> {
    public final /* synthetic */ zzh createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        IBinder iBinder = null;
        String str = null;
        ClientAppContext clientAppContext = null;
        int i = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i2 = 65535 & readInt;
            if (i2 == 1) {
                i = zzbgm.zzg(parcel, readInt);
            } else if (i2 == 2) {
                iBinder = zzbgm.zzr(parcel, readInt);
            } else if (i2 == 3) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (i2 != 4) {
                zzbgm.zzb(parcel, readInt);
            } else {
                clientAppContext = (ClientAppContext) zzbgm.zza(parcel, readInt, ClientAppContext.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzh(i, iBinder, str, clientAppContext);
    }

    public final /* synthetic */ zzh[] newArray(int i) {
        return new zzh[i];
    }
}
