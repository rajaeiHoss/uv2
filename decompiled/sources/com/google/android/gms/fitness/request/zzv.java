package com.google.android.gms.fitness.request;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.zzbgm;

public final class zzv implements Parcelable.Creator<DataUpdateListenerRegistrationRequest> {
    public final /* synthetic */ DataUpdateListenerRegistrationRequest createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        DataSource dataSource = null;
        DataType dataType = null;
        PendingIntent pendingIntent = null;
        IBinder iBinder = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 1) {
                dataSource = (DataSource) zzbgm.zza(parcel, readInt, DataSource.CREATOR);
            } else if (i == 2) {
                dataType = (DataType) zzbgm.zza(parcel, readInt, DataType.CREATOR);
            } else if (i == 3) {
                pendingIntent = (PendingIntent) zzbgm.zza(parcel, readInt, PendingIntent.CREATOR);
            } else if (i != 4) {
                zzbgm.zzb(parcel, readInt);
            } else {
                iBinder = zzbgm.zzr(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new DataUpdateListenerRegistrationRequest(dataSource, dataType, pendingIntent, iBinder);
    }

    public final /* synthetic */ DataUpdateListenerRegistrationRequest[] newArray(int i) {
        return new DataUpdateListenerRegistrationRequest[i];
    }
}
