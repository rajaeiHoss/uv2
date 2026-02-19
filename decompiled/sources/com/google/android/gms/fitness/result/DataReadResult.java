package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbi;
import com.google.android.gms.fitness.data.Bucket;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.RawBucket;
import com.google.android.gms.fitness.data.RawDataSet;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class DataReadResult extends zzbgl implements Result {
    public static final Parcelable.Creator<DataReadResult> CREATOR = new zzc();
    private final Status zzefs;
    private final List<DataSet> zzhib;
    private final List<DataSource> zzhin;
    private final List<Bucket> zzhpv;
    private int zzhpw;

    DataReadResult(List<RawDataSet> list, Status status, List<RawBucket> list2, int i, List<DataSource> list3) {
        this.zzefs = status;
        this.zzhpw = i;
        this.zzhin = list3;
        this.zzhib = new ArrayList(list.size());
        for (RawDataSet dataSet : list) {
            this.zzhib.add(new DataSet(dataSet, list3));
        }
        this.zzhpv = new ArrayList(list2.size());
        for (RawBucket bucket : list2) {
            this.zzhpv.add(new Bucket(bucket, list3));
        }
    }

    private DataReadResult(List<DataSet> list, List<Bucket> list2, Status status) {
        this.zzhib = list;
        this.zzefs = status;
        this.zzhpv = list2;
        this.zzhpw = 1;
        this.zzhin = new ArrayList();
    }

    public static DataReadResult zza(Status status, List<DataType> list, List<DataSource> list2) {
        ArrayList arrayList = new ArrayList();
        for (DataSource create : list2) {
            arrayList.add(DataSet.create(create));
        }
        for (DataType dataType : list) {
            arrayList.add(DataSet.create(new DataSource.Builder().setDataType(dataType).setType(1).setName("Default").build()));
        }
        return new DataReadResult(arrayList, Collections.emptyList(), status);
    }

    private static void zza(DataSet dataSet, List<DataSet> list) {
        for (DataSet next : list) {
            if (next.getDataSource().equals(dataSet.getDataSource())) {
                next.zzb((Iterable<DataPoint>) dataSet.getDataPoints());
                return;
            }
        }
        list.add(dataSet);
    }

    private List<RawBucket> zzask() {
        ArrayList arrayList = new ArrayList(this.zzhpv.size());
        for (Bucket rawBucket : this.zzhpv) {
            arrayList.add(new RawBucket(rawBucket, this.zzhin));
        }
        return arrayList;
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof DataReadResult) {
                DataReadResult dataReadResult = (DataReadResult) obj;
                if (this.zzefs.equals(dataReadResult.zzefs) && zzbg.equal(this.zzhib, dataReadResult.zzhib) && zzbg.equal(this.zzhpv, dataReadResult.zzhpv)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public List<Bucket> getBuckets() {
        return this.zzhpv;
    }

    public DataSet getDataSet(DataSource dataSource) {
        for (DataSet next : this.zzhib) {
            if (dataSource.equals(next.getDataSource())) {
                return next;
            }
        }
        return DataSet.create(dataSource);
    }

    public DataSet getDataSet(DataType dataType) {
        for (DataSet next : this.zzhib) {
            if (dataType.equals(next.getDataType())) {
                return next;
            }
        }
        return DataSet.create(new DataSource.Builder().setDataType(dataType).setType(1).build());
    }

    public List<DataSet> getDataSets() {
        return this.zzhib;
    }

    public Status getStatus() {
        return this.zzefs;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzefs, this.zzhib, this.zzhpv});
    }

    public String toString() {
        Object obj;
        Object obj2;
        zzbi zzg = zzbg.zzx(this).zzg("status", this.zzefs);
        if (this.zzhib.size() > 5) {
            int size = this.zzhib.size();
            StringBuilder sb = new StringBuilder(21);
            sb.append(size);
            sb.append(" data sets");
            obj = sb.toString();
        } else {
            obj = this.zzhib;
        }
        zzbi zzg2 = zzg.zzg("dataSets", obj);
        if (this.zzhpv.size() > 5) {
            int size2 = this.zzhpv.size();
            StringBuilder sb2 = new StringBuilder(19);
            sb2.append(size2);
            sb2.append(" buckets");
            obj2 = sb2.toString();
        } else {
            obj2 = this.zzhpv;
        }
        return zzg2.zzg("buckets", obj2).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        ArrayList arrayList = new ArrayList(this.zzhib.size());
        for (DataSet rawDataSet : this.zzhib) {
            arrayList.add(new RawDataSet(rawDataSet, this.zzhin));
        }
        zzbgo.zzd(parcel, 1, arrayList, false);
        zzbgo.zza(parcel, 2, (Parcelable) getStatus(), i, false);
        zzbgo.zzd(parcel, 3, zzask(), false);
        zzbgo.zzc(parcel, 5, this.zzhpw);
        zzbgo.zzc(parcel, 6, this.zzhin, false);
        zzbgo.zzai(parcel, zze);
    }

    public final int zzasj() {
        return this.zzhpw;
    }

    public final void zzb(DataReadResult dataReadResult) {
        for (DataSet zza : dataReadResult.getDataSets()) {
            zza(zza, this.zzhib);
        }
        for (Bucket next : dataReadResult.getBuckets()) {
            Iterator<Bucket> it = this.zzhpv.iterator();
            while (true) {
                if (!it.hasNext()) {
                    this.zzhpv.add(next);
                    break;
                }
                Bucket next2 = it.next();
                if (next2.zza(next)) {
                    for (DataSet zza2 : next.getDataSets()) {
                        zza(zza2, next2.getDataSets());
                    }
                }
            }
        }
    }
}
