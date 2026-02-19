package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.internal.zzbgm;
import java.util.ArrayList;

public final class zzi implements Parcelable.Creator<SessionStopResult> {
    public final /* synthetic */ SessionStopResult createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        Status status = null;
        ArrayList<Session> arrayList = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 2) {
                status = (Status) zzbgm.zza(parcel, readInt, Status.CREATOR);
            } else if (i != 3) {
                zzbgm.zzb(parcel, readInt);
            } else {
                arrayList = zzbgm.zzc(parcel, readInt, Session.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new SessionStopResult(status, arrayList);
    }

    public final /* synthetic */ SessionStopResult[] newArray(int i) {
        return new SessionStopResult[i];
    }
}
