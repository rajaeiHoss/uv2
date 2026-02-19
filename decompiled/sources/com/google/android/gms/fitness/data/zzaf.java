package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzaf implements Parcelable.Creator<zzae> {
    public final /* synthetic */ zzae createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        Session session = null;
        DataSet dataSet = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 1) {
                session = (Session) zzbgm.zza(parcel, readInt, Session.CREATOR);
            } else if (i != 2) {
                zzbgm.zzb(parcel, readInt);
            } else {
                dataSet = (DataSet) zzbgm.zza(parcel, readInt, DataSet.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzae(session, dataSet);
    }

    public final /* synthetic */ zzae[] newArray(int i) {
        return new zzae[i];
    }
}
