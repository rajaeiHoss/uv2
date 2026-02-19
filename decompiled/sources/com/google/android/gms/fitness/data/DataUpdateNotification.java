package com.google.android.gms.fitness.data;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzbgq;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class DataUpdateNotification extends zzbgl {
    public static final String ACTION = "com.google.android.gms.fitness.DATA_UPDATE_NOTIFICATION";
    public static final Parcelable.Creator<DataUpdateNotification> CREATOR = new zzn();
    public static final String EXTRA_DATA_UPDATE_NOTIFICATION = "vnd.google.fitness.data_udpate_notification";
    public static final int OPERATION_DELETE = 2;
    public static final int OPERATION_INSERT = 1;
    public static final int OPERATION_UPDATE = 3;
    private final DataType zzhhj;
    private final DataSource zzhhk;
    private final long zzhjr;
    private final long zzhjs;
    private final int zzhjt;

    public DataUpdateNotification(long j, long j2, int i, DataSource dataSource, DataType dataType) {
        this.zzhjr = j;
        this.zzhjs = j2;
        this.zzhjt = i;
        this.zzhhk = dataSource;
        this.zzhhj = dataType;
    }

    public static DataUpdateNotification getDataUpdateNotification(Intent intent) {
        return (DataUpdateNotification) zzbgq.zza(intent, EXTRA_DATA_UPDATE_NOTIFICATION, CREATOR);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DataUpdateNotification)) {
            return false;
        }
        DataUpdateNotification dataUpdateNotification = (DataUpdateNotification) obj;
        return this.zzhjr == dataUpdateNotification.zzhjr && this.zzhjs == dataUpdateNotification.zzhjs && this.zzhjt == dataUpdateNotification.zzhjt && zzbg.equal(this.zzhhk, dataUpdateNotification.zzhhk) && zzbg.equal(this.zzhhj, dataUpdateNotification.zzhhj);
    }

    public DataSource getDataSource() {
        return this.zzhhk;
    }

    public DataType getDataType() {
        return this.zzhhj;
    }

    public int getOperationType() {
        return this.zzhjt;
    }

    public long getUpdateEndTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzhjs, TimeUnit.NANOSECONDS);
    }

    public long getUpdateStartTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzhjr, TimeUnit.NANOSECONDS);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Long.valueOf(this.zzhjr), Long.valueOf(this.zzhjs), Integer.valueOf(this.zzhjt), this.zzhhk, this.zzhhj});
    }

    public String toString() {
        return zzbg.zzx(this).zzg("updateStartTimeNanos", Long.valueOf(this.zzhjr)).zzg("updateEndTimeNanos", Long.valueOf(this.zzhjs)).zzg("operationType", Integer.valueOf(this.zzhjt)).zzg("dataSource", this.zzhhk).zzg("dataType", this.zzhhj).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, this.zzhjr);
        zzbgo.zza(parcel, 2, this.zzhjs);
        zzbgo.zzc(parcel, 3, getOperationType());
        zzbgo.zza(parcel, 4, (Parcelable) getDataSource(), i, false);
        zzbgo.zza(parcel, 5, (Parcelable) getDataType(), i, false);
        zzbgo.zzai(parcel, zze);
    }
}
