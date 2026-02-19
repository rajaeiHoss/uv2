package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.internal.zzbgm;
import java.util.ArrayList;
import java.util.List;

public final class zzau implements Parcelable.Creator<SessionInsertRequest> {
    public final /* synthetic */ SessionInsertRequest createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        Session session = null;
        ArrayList<DataSet> arrayList = null;
        ArrayList<DataPoint> arrayList2 = null;
        IBinder iBinder = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 1) {
                session = (Session) zzbgm.zza(parcel, readInt, Session.CREATOR);
            } else if (i == 2) {
                arrayList = zzbgm.zzc(parcel, readInt, DataSet.CREATOR);
            } else if (i == 3) {
                arrayList2 = zzbgm.zzc(parcel, readInt, DataPoint.CREATOR);
            } else if (i != 4) {
                zzbgm.zzb(parcel, readInt);
            } else {
                iBinder = zzbgm.zzr(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new SessionInsertRequest(session, (List<DataSet>) arrayList, (List<DataPoint>) arrayList2, iBinder);
    }

    public final /* synthetic */ SessionInsertRequest[] newArray(int i) {
        return new SessionInsertRequest[i];
    }
}
