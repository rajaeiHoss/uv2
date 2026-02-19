package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;

public class DailyTotalResult extends zzbgl implements Result {
    public static final Parcelable.Creator<DailyTotalResult> CREATOR = new zzb();
    private final Status zzefs;
    private final DataSet zzhlq;

    DailyTotalResult(Status status, DataSet dataSet) {
        this.zzefs = status;
        this.zzhlq = dataSet;
    }

    private DailyTotalResult(DataSet dataSet, Status status) {
        this.zzefs = status;
        this.zzhlq = dataSet;
    }

    public static DailyTotalResult zza(Status status, DataType dataType) {
        return new DailyTotalResult(DataSet.create(new DataSource.Builder().setDataType(dataType).setType(1).build()), status);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof DailyTotalResult) {
                DailyTotalResult dailyTotalResult = (DailyTotalResult) obj;
                if (this.zzefs.equals(dailyTotalResult.zzefs) && zzbg.equal(this.zzhlq, dailyTotalResult.zzhlq)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public Status getStatus() {
        return this.zzefs;
    }

    public DataSet getTotal() {
        return this.zzhlq;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzefs, this.zzhlq});
    }

    public String toString() {
        return zzbg.zzx(this).zzg("status", this.zzefs).zzg("dataPoint", this.zzhlq).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, (Parcelable) getStatus(), i, false);
        zzbgo.zza(parcel, 2, (Parcelable) getTotal(), i, false);
        zzbgo.zzai(parcel, zze);
    }
}
