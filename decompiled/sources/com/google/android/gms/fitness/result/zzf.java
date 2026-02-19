package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.data.Goal;
import com.google.android.gms.internal.zzbgm;
import java.util.ArrayList;

public final class zzf implements Parcelable.Creator<GoalsResult> {
    public final /* synthetic */ GoalsResult createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        Status status = null;
        ArrayList<Goal> arrayList = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 1) {
                status = (Status) zzbgm.zza(parcel, readInt, Status.CREATOR);
            } else if (i != 2) {
                zzbgm.zzb(parcel, readInt);
            } else {
                arrayList = zzbgm.zzc(parcel, readInt, Goal.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new GoalsResult(status, arrayList);
    }

    public final /* synthetic */ GoalsResult[] newArray(int i) {
        return new GoalsResult[i];
    }
}
