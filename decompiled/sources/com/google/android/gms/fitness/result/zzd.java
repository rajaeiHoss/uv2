package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.internal.zzbgm;
import java.util.ArrayList;

public final class zzd implements Parcelable.Creator<DataSourcesResult> {
    public final /* synthetic */ DataSourcesResult createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        ArrayList<DataSource> arrayList = null;
        Status status = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 1) {
                arrayList = zzbgm.zzc(parcel, readInt, DataSource.CREATOR);
            } else if (i != 2) {
                zzbgm.zzb(parcel, readInt);
            } else {
                status = (Status) zzbgm.zza(parcel, readInt, Status.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new DataSourcesResult(arrayList, status);
    }

    public final /* synthetic */ DataSourcesResult[] newArray(int i) {
        return new DataSourcesResult[i];
    }
}
