package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DataSourcesResult extends zzbgl implements Result {
    public static final Parcelable.Creator<DataSourcesResult> CREATOR = new zzd();
    private final Status zzefs;
    private final List<DataSource> zzhnx;

    public DataSourcesResult(List<DataSource> list, Status status) {
        this.zzhnx = Collections.unmodifiableList(list);
        this.zzefs = status;
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof DataSourcesResult) {
                DataSourcesResult dataSourcesResult = (DataSourcesResult) obj;
                if (this.zzefs.equals(dataSourcesResult.zzefs) && zzbg.equal(this.zzhnx, dataSourcesResult.zzhnx)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public List<DataSource> getDataSources() {
        return this.zzhnx;
    }

    public List<DataSource> getDataSources(DataType dataType) {
        ArrayList arrayList = new ArrayList();
        for (DataSource next : this.zzhnx) {
            if (next.getDataType().equals(dataType)) {
                arrayList.add(next);
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    public Status getStatus() {
        return this.zzefs;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzefs, this.zzhnx});
    }

    public String toString() {
        return zzbg.zzx(this).zzg("status", this.zzefs).zzg("dataSources", this.zzhnx).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 1, getDataSources(), false);
        zzbgo.zza(parcel, 2, (Parcelable) getStatus(), i, false);
        zzbgo.zzai(parcel, zze);
    }
}
