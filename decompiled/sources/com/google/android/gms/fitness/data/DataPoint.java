package com.google.android.gms.fitness.data;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzbgq;
import com.google.android.gms.internal.zzbwh;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public final class DataPoint extends zzbgl implements ReflectedParcelable {
    public static final Parcelable.Creator<DataPoint> CREATOR = new zzh();
    private final DataSource zzhhk;
    private long zzhig;
    private long zzhih;
    private final Value[] zzhii;
    private DataSource zzhij;
    private long zzhik;
    private long zzhil;

    private DataPoint(DataSource dataSource) {
        this.zzhhk = (DataSource) zzbq.checkNotNull(dataSource, "Data source cannot be null");
        List<Field> fields = dataSource.getDataType().getFields();
        this.zzhii = new Value[fields.size()];
        int i = 0;
        for (Field format : fields) {
            this.zzhii[i] = new Value(format.getFormat());
            i++;
        }
    }

    DataPoint(DataSource dataSource, long j, long j2, Value[] valueArr, DataSource dataSource2, long j3, long j4) {
        this.zzhhk = dataSource;
        this.zzhij = dataSource2;
        this.zzhig = j;
        this.zzhih = j2;
        this.zzhii = valueArr;
        this.zzhik = j3;
        this.zzhil = j4;
    }

    private DataPoint(DataSource dataSource, DataSource dataSource2, RawDataPoint rawDataPoint) {
        this(dataSource, zza(Long.valueOf(rawDataPoint.getTimestampNanos()), 0), zza(Long.valueOf(rawDataPoint.zzarz()), 0), rawDataPoint.zzaro(), dataSource2, zza(Long.valueOf(rawDataPoint.zzarq()), 0), zza(Long.valueOf(rawDataPoint.zzarr()), 0));
    }

    DataPoint(List<DataSource> list, RawDataPoint rawDataPoint) {
        this(zzc(list, rawDataPoint.zzasa()), zzc(list, rawDataPoint.zzasb()), rawDataPoint);
    }

    public static DataPoint create(DataSource dataSource) {
        return new DataPoint(dataSource);
    }

    public static DataPoint extract(Intent intent) {
        if (intent == null) {
            return null;
        }
        return (DataPoint) zzbgq.zza(intent, "com.google.android.gms.fitness.EXTRA_DATA_POINT", CREATOR);
    }

    private static long zza(Long l, long j) {
        if (l != null) {
            return l.longValue();
        }
        return 0;
    }

    private static DataSource zzc(List<DataSource> list, int i) {
        if (i < 0 || i >= list.size()) {
            return null;
        }
        return list.get(i);
    }

    private final void zzdc(int i) {
        List<Field> fields = getDataType().getFields();
        int size = fields.size();
        zzbq.zzb(i == size, "Attempting to insert %s values, but needed %s: %s", Integer.valueOf(i), Integer.valueOf(size), fields);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DataPoint)) {
            return false;
        }
        DataPoint dataPoint = (DataPoint) obj;
        return zzbg.equal(this.zzhhk, dataPoint.zzhhk) && this.zzhig == dataPoint.zzhig && this.zzhih == dataPoint.zzhih && Arrays.equals(this.zzhii, dataPoint.zzhii) && zzbg.equal(getOriginalDataSource(), dataPoint.getOriginalDataSource());
    }

    public final DataSource getDataSource() {
        return this.zzhhk;
    }

    public final DataType getDataType() {
        return this.zzhhk.getDataType();
    }

    public final long getEndTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzhig, TimeUnit.NANOSECONDS);
    }

    public final DataSource getOriginalDataSource() {
        DataSource dataSource = this.zzhij;
        return dataSource != null ? dataSource : this.zzhhk;
    }

    public final long getStartTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzhih, TimeUnit.NANOSECONDS);
    }

    public final long getTimestamp(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzhig, TimeUnit.NANOSECONDS);
    }

    public final Value getValue(Field field) {
        return this.zzhii[getDataType().indexOf(field)];
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzhhk, Long.valueOf(this.zzhig), Long.valueOf(this.zzhih)});
    }

    public final DataPoint setFloatValues(float... fArr) {
        zzdc(fArr.length);
        for (int i = 0; i < fArr.length; i++) {
            this.zzhii[i].setFloat(fArr[i]);
        }
        return this;
    }

    public final DataPoint setIntValues(int... iArr) {
        zzdc(iArr.length);
        for (int i = 0; i < iArr.length; i++) {
            this.zzhii[i].setInt(iArr[i]);
        }
        return this;
    }

    public final DataPoint setTimeInterval(long j, long j2, TimeUnit timeUnit) {
        this.zzhih = timeUnit.toNanos(j);
        this.zzhig = timeUnit.toNanos(j2);
        return this;
    }

    public final DataPoint setTimestamp(long j, TimeUnit timeUnit) {
        this.zzhig = timeUnit.toNanos(j);
        if (DataType.TYPE_LOCATION_SAMPLE.equals(getDataType())) {
            if (timeUnit.convert(1, TimeUnit.MILLISECONDS) > 1) {
                Log.w("Fitness", "Storing location at more than millisecond granularity is not supported. Extra precision is lost as the value is converted to milliseconds.");
                this.zzhig = zzbwh.zza(this.zzhig, TimeUnit.NANOSECONDS, TimeUnit.MILLISECONDS);
            }
        }
        return this;
    }

    public final String toString() {
        Object[] objArr = new Object[7];
        objArr[0] = Arrays.toString(this.zzhii);
        objArr[1] = Long.valueOf(this.zzhih);
        objArr[2] = Long.valueOf(this.zzhig);
        objArr[3] = Long.valueOf(this.zzhik);
        objArr[4] = Long.valueOf(this.zzhil);
        objArr[5] = this.zzhhk.toDebugString();
        DataSource dataSource = this.zzhij;
        objArr[6] = dataSource != null ? dataSource.toDebugString() : "N/A";
        return String.format("DataPoint{%s@[%s, %s,raw=%s,insert=%s](%s %s)}", objArr);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, (Parcelable) getDataSource(), i, false);
        zzbgo.zza(parcel, 3, this.zzhig);
        zzbgo.zza(parcel, 4, this.zzhih);
        zzbgo.zza(parcel, 5, this.zzhii, i, false);
        zzbgo.zza(parcel, 6, (Parcelable) this.zzhij, i, false);
        zzbgo.zza(parcel, 7, this.zzhik);
        zzbgo.zza(parcel, 8, this.zzhil);
        zzbgo.zzai(parcel, zze);
    }

    public final Value[] zzaro() {
        return this.zzhii;
    }

    public final DataSource zzarp() {
        return this.zzhij;
    }

    public final long zzarq() {
        return this.zzhik;
    }

    public final long zzarr() {
        return this.zzhil;
    }

    public final void zzars() {
        zzbq.zzb(getDataType().getName().equals(getDataSource().getDataType().getName()), "Conflicting data types found %s vs %s", getDataType(), getDataType());
        zzbq.zzb(this.zzhig > 0, "Data point does not have the timestamp set: %s", this);
        zzbq.zzb(this.zzhih <= this.zzhig, "Data point with start time greater than end time found: %s", this);
    }

    public final Value zzdb(int i) {
        DataType dataType = getDataType();
        zzbq.zzb(i >= 0 && i < dataType.getFields().size(), "fieldIndex %s is out of range for %s", Integer.valueOf(i), dataType);
        return this.zzhii[i];
    }
}
