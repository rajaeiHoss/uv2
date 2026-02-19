package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.internal.zzbgm;

public final class zzba implements Parcelable.Creator<zzaz> {
    public final /* synthetic */ zzaz createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        Session session = null;
        IBinder iBinder = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 1) {
                session = (Session) zzbgm.zza(parcel, readInt, Session.CREATOR);
            } else if (i != 2) {
                zzbgm.zzb(parcel, readInt);
            } else {
                iBinder = zzbgm.zzr(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzaz(session, iBinder);
    }

    public final /* synthetic */ zzaz[] newArray(int i) {
        return new zzaz[i];
    }
}
