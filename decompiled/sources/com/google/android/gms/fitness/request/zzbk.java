package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fitness.data.Subscription;
import com.google.android.gms.internal.zzbgm;

public final class zzbk implements Parcelable.Creator<zzbj> {
    public final /* synthetic */ zzbj createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        Subscription subscription = null;
        IBinder iBinder = null;
        boolean z = false;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 1) {
                subscription = (Subscription) zzbgm.zza(parcel, readInt, Subscription.CREATOR);
            } else if (i == 2) {
                z = zzbgm.zzc(parcel, readInt);
            } else if (i != 3) {
                zzbgm.zzb(parcel, readInt);
            } else {
                iBinder = zzbgm.zzr(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzbj(subscription, z, iBinder);
    }

    public final /* synthetic */ zzbj[] newArray(int i) {
        return new zzbj[i];
    }
}
