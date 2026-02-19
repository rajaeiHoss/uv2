package com.google.android.gms.fitness.request;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzas implements Parcelable.Creator<zzar> {
    public final /* synthetic */ zzar createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        IBinder iBinder = null;
        PendingIntent pendingIntent = null;
        IBinder iBinder2 = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 1) {
                iBinder = zzbgm.zzr(parcel, readInt);
            } else if (i == 2) {
                pendingIntent = (PendingIntent) zzbgm.zza(parcel, readInt, PendingIntent.CREATOR);
            } else if (i != 3) {
                zzbgm.zzb(parcel, readInt);
            } else {
                iBinder2 = zzbgm.zzr(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzar(iBinder, pendingIntent, iBinder2);
    }

    public final /* synthetic */ zzar[] newArray(int i) {
        return new zzar[i];
    }
}
