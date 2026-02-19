package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzbwi;
import java.util.Arrays;
import java.util.List;

public final class RawDataSet extends zzbgl {
    public static final Parcelable.Creator<RawDataSet> CREATOR = new zzaa();
    public final boolean zzhid;
    public final int zzhll;
    public final List<RawDataPoint> zzhln;

    public RawDataSet(int i, List<RawDataPoint> list, boolean z) {
        this.zzhll = i;
        this.zzhln = list;
        this.zzhid = z;
    }

    public RawDataSet(DataSet dataSet, List<DataSource> list) {
        this.zzhln = dataSet.zzac(list);
        this.zzhid = dataSet.zzarm();
        this.zzhll = zzbwi.zza(dataSet.getDataSource(), list);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RawDataSet)) {
            return false;
        }
        RawDataSet rawDataSet = (RawDataSet) obj;
        return this.zzhll == rawDataSet.zzhll && this.zzhid == rawDataSet.zzhid && zzbg.equal(this.zzhln, rawDataSet.zzhln);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.zzhll)});
    }

    public final String toString() {
        return String.format("RawDataSet{%s@[%s]}", new Object[]{Integer.valueOf(this.zzhll), this.zzhln});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 1, this.zzhll);
        zzbgo.zzc(parcel, 3, this.zzhln, false);
        zzbgo.zza(parcel, 4, this.zzhid);
        zzbgo.zzai(parcel, zze);
    }
}
