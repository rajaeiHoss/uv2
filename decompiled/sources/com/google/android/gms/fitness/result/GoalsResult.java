package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.data.Goal;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.List;

public class GoalsResult extends zzbgl implements Result {
    public static final Parcelable.Creator<GoalsResult> CREATOR = new zzf();
    private final Status zzefs;
    private final List<Goal> zzhpx;

    public GoalsResult(Status status, List<Goal> list) {
        this.zzefs = status;
        this.zzhpx = list;
    }

    public List<Goal> getGoals() {
        return this.zzhpx;
    }

    public Status getStatus() {
        return this.zzefs;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, (Parcelable) getStatus(), i, false);
        zzbgo.zzc(parcel, 2, getGoals(), false);
        zzbgo.zzai(parcel, zze);
    }
}
