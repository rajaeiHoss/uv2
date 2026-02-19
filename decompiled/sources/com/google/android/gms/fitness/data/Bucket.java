package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzfmk;
import com.google.android.gms.measurement.AppMeasurement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.jivesoftware.smackx.workgroup.packet.SessionID;

public class Bucket extends zzbgl implements ReflectedParcelable {
    public static final Parcelable.Creator<Bucket> CREATOR = new zze();
    public static final int TYPE_ACTIVITY_SEGMENT = 4;
    public static final int TYPE_ACTIVITY_TYPE = 3;
    public static final int TYPE_SESSION = 2;
    public static final int TYPE_TIME = 1;
    private final long zzhhl;
    private final long zzhhm;
    private final Session zzhhs;
    private final int zzhia;
    private final List<DataSet> zzhib;
    private final int zzhic;
    private boolean zzhid;

    Bucket(long j, long j2, Session session, int i, List<DataSet> list, int i2, boolean z) {
        this.zzhid = false;
        this.zzhhl = j;
        this.zzhhm = j2;
        this.zzhhs = session;
        this.zzhia = i;
        this.zzhib = list;
        this.zzhic = i2;
        this.zzhid = z;
    }

    public Bucket(RawBucket rawBucket, List<DataSource> list) {
        this(rawBucket.zzhhl, rawBucket.zzhhm, rawBucket.zzhhs, rawBucket.zzhlk, zza(rawBucket.zzhib, list), rawBucket.zzhic, rawBucket.zzhid);
    }

    private static List<DataSet> zza(Collection<RawDataSet> collection, List<DataSource> list) {
        ArrayList arrayList = new ArrayList(collection.size());
        for (RawDataSet dataSet : collection) {
            arrayList.add(new DataSet(dataSet, list));
        }
        return arrayList;
    }

    public static String zzda(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? "bug" : "segment" : AppMeasurement.Param.TYPE : SessionID.ELEMENT_NAME : "time" : "unknown";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Bucket)) {
            return false;
        }
        Bucket bucket = (Bucket) obj;
        return this.zzhhl == bucket.zzhhl && this.zzhhm == bucket.zzhhm && this.zzhia == bucket.zzhia && zzbg.equal(this.zzhib, bucket.zzhib) && this.zzhic == bucket.zzhic && this.zzhid == bucket.zzhid;
    }

    public String getActivity() {
        return zzfmk.getName(this.zzhia);
    }

    public final int getActivityType() {
        return this.zzhia;
    }

    public int getBucketType() {
        return this.zzhic;
    }

    public DataSet getDataSet(DataType dataType) {
        for (DataSet next : this.zzhib) {
            if (next.getDataType().equals(dataType)) {
                return next;
            }
        }
        return null;
    }

    public List<DataSet> getDataSets() {
        return this.zzhib;
    }

    public long getEndTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzhhm, TimeUnit.MILLISECONDS);
    }

    public Session getSession() {
        return this.zzhhs;
    }

    public long getStartTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzhhl, TimeUnit.MILLISECONDS);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Long.valueOf(this.zzhhl), Long.valueOf(this.zzhhm), Integer.valueOf(this.zzhia), Integer.valueOf(this.zzhic)});
    }

    public String toString() {
        return zzbg.zzx(this).zzg("startTime", Long.valueOf(this.zzhhl)).zzg("endTime", Long.valueOf(this.zzhhm)).zzg("activity", Integer.valueOf(this.zzhia)).zzg("dataSets", this.zzhib).zzg("bucketType", zzda(this.zzhic)).zzg("serverHasMoreData", Boolean.valueOf(this.zzhid)).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, this.zzhhl);
        zzbgo.zza(parcel, 2, this.zzhhm);
        zzbgo.zza(parcel, 3, (Parcelable) getSession(), i, false);
        zzbgo.zzc(parcel, 4, this.zzhia);
        zzbgo.zzc(parcel, 5, getDataSets(), false);
        zzbgo.zzc(parcel, 6, getBucketType());
        zzbgo.zza(parcel, 7, zzarm());
        zzbgo.zzai(parcel, zze);
    }

    public final boolean zza(Bucket bucket) {
        return this.zzhhl == bucket.zzhhl && this.zzhhm == bucket.zzhhm && this.zzhia == bucket.zzhia && this.zzhic == bucket.zzhic;
    }

    public final boolean zzarm() {
        if (this.zzhid) {
            return true;
        }
        for (DataSet zzarm : this.zzhib) {
            if (zzarm.zzarm()) {
                return true;
            }
        }
        return false;
    }
}
