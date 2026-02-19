package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.fitness.data.zzae;
import com.google.android.gms.internal.zzbgm;
import java.util.ArrayList;

public final class zzh implements Parcelable.Creator<SessionReadResult> {
    public final /* synthetic */ SessionReadResult createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        ArrayList<Session> arrayList = null;
        ArrayList<zzae> arrayList2 = null;
        Status status = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 1) {
                arrayList = zzbgm.zzc(parcel, readInt, Session.CREATOR);
            } else if (i == 2) {
                arrayList2 = zzbgm.zzc(parcel, readInt, zzae.CREATOR);
            } else if (i != 3) {
                zzbgm.zzb(parcel, readInt);
            } else {
                status = (Status) zzbgm.zza(parcel, readInt, Status.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new SessionReadResult(arrayList, arrayList2, status);
    }

    public final /* synthetic */ SessionReadResult[] newArray(int i) {
        return new SessionReadResult[i];
    }
}
