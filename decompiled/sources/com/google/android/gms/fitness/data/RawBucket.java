package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public final class RawBucket extends zzbgl {
    public static final Parcelable.Creator<RawBucket> CREATOR = new zzy();
    public final long zzhhl;
    public final long zzhhm;
    public final Session zzhhs;
    public final List<RawDataSet> zzhib;
    public final int zzhic;
    public final boolean zzhid;
    public final int zzhlk;

    public RawBucket(long j, long j2, Session session, int i, List<RawDataSet> list, int i2, boolean z) {
        this.zzhhl = j;
        this.zzhhm = j2;
        this.zzhhs = session;
        this.zzhlk = i;
        this.zzhib = list;
        this.zzhic = i2;
        this.zzhid = z;
    }

    public RawBucket(Bucket bucket, List<DataSource> list) {
        this.zzhhl = bucket.getStartTime(TimeUnit.MILLISECONDS);
        this.zzhhm = bucket.getEndTime(TimeUnit.MILLISECONDS);
        this.zzhhs = bucket.getSession();
        this.zzhlk = bucket.getActivityType();
        this.zzhic = bucket.getBucketType();
        this.zzhid = bucket.zzarm();
        List<DataSet> dataSets = bucket.getDataSets();
        this.zzhib = new ArrayList(dataSets.size());
        for (DataSet rawDataSet : dataSets) {
            this.zzhib.add(new RawDataSet(rawDataSet, list));
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RawBucket)) {
            return false;
        }
        RawBucket rawBucket = (RawBucket) obj;
        return this.zzhhl == rawBucket.zzhhl && this.zzhhm == rawBucket.zzhhm && this.zzhlk == rawBucket.zzhlk && zzbg.equal(this.zzhib, rawBucket.zzhib) && this.zzhic == rawBucket.zzhic && this.zzhid == rawBucket.zzhid;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Long.valueOf(this.zzhhl), Long.valueOf(this.zzhhm), Integer.valueOf(this.zzhic)});
    }

    public final String toString() {
        return zzbg.zzx(this).zzg("startTime", Long.valueOf(this.zzhhl)).zzg("endTime", Long.valueOf(this.zzhhm)).zzg("activity", Integer.valueOf(this.zzhlk)).zzg("dataSets", this.zzhib).zzg("bucketType", Integer.valueOf(this.zzhic)).zzg("serverHasMoreData", Boolean.valueOf(this.zzhid)).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, this.zzhhl);
        zzbgo.zza(parcel, 2, this.zzhhm);
        zzbgo.zza(parcel, 3, (Parcelable) this.zzhhs, i, false);
        zzbgo.zzc(parcel, 4, this.zzhlk);
        zzbgo.zzc(parcel, 5, this.zzhib, false);
        zzbgo.zzc(parcel, 6, this.zzhic);
        zzbgo.zza(parcel, 7, this.zzhid);
        zzbgo.zzai(parcel, zze);
    }
}
