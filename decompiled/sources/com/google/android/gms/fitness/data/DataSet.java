package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzbwm;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class DataSet extends zzbgl implements ReflectedParcelable {
    public static final Parcelable.Creator<DataSet> CREATOR = new zzi();
    private final int versionCode;
    private final DataSource zzhhk;
    private boolean zzhid;
    private final List<DataPoint> zzhim;
    private final List<DataSource> zzhin;

    DataSet(int i, DataSource dataSource, List<RawDataPoint> list, List<DataSource> list2, boolean z) {
        this.zzhid = false;
        this.versionCode = i;
        this.zzhhk = dataSource;
        this.zzhid = z;
        this.zzhim = new ArrayList(list.size());
        this.zzhin = i < 2 ? Collections.singletonList(dataSource) : list2;
        for (RawDataPoint dataPoint : list) {
            this.zzhim.add(new DataPoint(this.zzhin, dataPoint));
        }
    }

    private DataSet(DataSource dataSource) {
        this.zzhid = false;
        this.versionCode = 3;
        DataSource dataSource2 = (DataSource) zzbq.checkNotNull(dataSource);
        this.zzhhk = dataSource2;
        this.zzhim = new ArrayList();
        ArrayList arrayList = new ArrayList();
        this.zzhin = arrayList;
        arrayList.add(dataSource2);
    }

    public DataSet(RawDataSet rawDataSet, List<DataSource> list) {
        this.zzhid = false;
        this.versionCode = 3;
        this.zzhhk = list.get(rawDataSet.zzhll);
        this.zzhin = list;
        this.zzhid = rawDataSet.zzhid;
        List<RawDataPoint> list2 = rawDataSet.zzhln;
        this.zzhim = new ArrayList(list2.size());
        for (RawDataPoint dataPoint : list2) {
            this.zzhim.add(new DataPoint(this.zzhin, dataPoint));
        }
    }

    public static DataSet create(DataSource dataSource) {
        zzbq.checkNotNull(dataSource, "DataSource should be specified");
        return new DataSet(dataSource);
    }

    private final void zza(DataPoint dataPoint) {
        this.zzhim.add(dataPoint);
        DataSource originalDataSource = dataPoint.getOriginalDataSource();
        if (originalDataSource != null && !this.zzhin.contains(originalDataSource)) {
            this.zzhin.add(originalDataSource);
        }
    }

    private List<RawDataPoint> zzart() {
        return zzac(this.zzhin);
    }

    public static void zzb(DataPoint dataPoint) throws IllegalArgumentException {
        String zza = zzbwm.zza(dataPoint, zzf.zzhie);
        if (zza != null) {
            String valueOf = String.valueOf(dataPoint);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 20);
            sb.append("Invalid data point: ");
            sb.append(valueOf);
            Log.w("Fitness", sb.toString());
            throw new IllegalArgumentException(zza);
        }
    }

    public final void add(DataPoint dataPoint) {
        DataSource dataSource = dataPoint.getDataSource();
        zzbq.zzb(dataSource.getStreamIdentifier().equals(this.zzhhk.getStreamIdentifier()), "Conflicting data sources found %s vs %s", dataSource, this.zzhhk);
        dataPoint.zzars();
        zzb(dataPoint);
        zza(dataPoint);
    }

    public final void addAll(Iterable<DataPoint> iterable) {
        for (DataPoint add : iterable) {
            add(add);
        }
    }

    public final DataPoint createDataPoint() {
        return DataPoint.create(this.zzhhk);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DataSet)) {
            return false;
        }
        DataSet dataSet = (DataSet) obj;
        return zzbg.equal(this.zzhhk, dataSet.zzhhk) && zzbg.equal(this.zzhim, dataSet.zzhim) && this.zzhid == dataSet.zzhid;
    }

    public final List<DataPoint> getDataPoints() {
        return Collections.unmodifiableList(this.zzhim);
    }

    public final DataSource getDataSource() {
        return this.zzhhk;
    }

    public final DataType getDataType() {
        return this.zzhhk.getDataType();
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzhhk});
    }

    public final boolean isEmpty() {
        return this.zzhim.isEmpty();
    }

    public final String toString() {
        List<RawDataPoint> zzart = zzart();
        Object[] objArr = new Object[2];
        objArr[0] = this.zzhhk.toDebugString();
        Object obj = zzart;
        if (this.zzhim.size() >= 10) {
            obj = String.format("%d data points, first 5: %s", new Object[]{Integer.valueOf(this.zzhim.size()), zzart.subList(0, 5)});
        }
        objArr[1] = obj;
        return String.format("DataSet{%s %s}", objArr);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, (Parcelable) getDataSource(), i, false);
        zzbgo.zzd(parcel, 3, zzart(), false);
        zzbgo.zzc(parcel, 4, this.zzhin, false);
        zzbgo.zza(parcel, 5, this.zzhid);
        zzbgo.zzc(parcel, 1000, this.versionCode);
        zzbgo.zzai(parcel, zze);
    }

    /* access modifiers changed from: package-private */
    public final List<RawDataPoint> zzac(List<DataSource> list) {
        ArrayList arrayList = new ArrayList(this.zzhim.size());
        for (DataPoint rawDataPoint : this.zzhim) {
            arrayList.add(new RawDataPoint(rawDataPoint, list));
        }
        return arrayList;
    }

    public final boolean zzarm() {
        return this.zzhid;
    }

    public final void zzb(Iterable<DataPoint> iterable) {
        for (DataPoint zza : iterable) {
            zza(zza);
        }
    }
}
