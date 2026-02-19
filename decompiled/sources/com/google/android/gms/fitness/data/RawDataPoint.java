package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzbwi;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public final class RawDataPoint extends zzbgl {
    public static final Parcelable.Creator<RawDataPoint> CREATOR = new zzz();
    private final long zzhig;
    private final long zzhih;
    private final Value[] zzhii;
    private final long zzhik;
    private final long zzhil;
    private final int zzhll;
    private final int zzhlm;

    public RawDataPoint(long j, long j2, Value[] valueArr, int i, int i2, long j3, long j4) {
        this.zzhig = j;
        this.zzhih = j2;
        this.zzhll = i;
        this.zzhlm = i2;
        this.zzhik = j3;
        this.zzhil = j4;
        this.zzhii = valueArr;
    }

    RawDataPoint(DataPoint dataPoint, List<DataSource> list) {
        this.zzhig = dataPoint.getTimestamp(TimeUnit.NANOSECONDS);
        this.zzhih = dataPoint.getStartTime(TimeUnit.NANOSECONDS);
        this.zzhii = dataPoint.zzaro();
        this.zzhll = zzbwi.zza(dataPoint.getDataSource(), list);
        this.zzhlm = zzbwi.zza(dataPoint.zzarp(), list);
        this.zzhik = dataPoint.zzarq();
        this.zzhil = dataPoint.zzarr();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RawDataPoint)) {
            return false;
        }
        RawDataPoint rawDataPoint = (RawDataPoint) obj;
        return this.zzhig == rawDataPoint.zzhig && this.zzhih == rawDataPoint.zzhih && Arrays.equals(this.zzhii, rawDataPoint.zzhii) && this.zzhll == rawDataPoint.zzhll && this.zzhlm == rawDataPoint.zzhlm && this.zzhik == rawDataPoint.zzhik;
    }

    public final long getTimestampNanos() {
        return this.zzhig;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Long.valueOf(this.zzhig), Long.valueOf(this.zzhih)});
    }

    public final String toString() {
        return String.format("RawDataPoint{%s@[%s, %s](%d,%d)}", new Object[]{Arrays.toString(this.zzhii), Long.valueOf(this.zzhih), Long.valueOf(this.zzhig), Integer.valueOf(this.zzhll), Integer.valueOf(this.zzhlm)});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, this.zzhig);
        zzbgo.zza(parcel, 2, this.zzhih);
        zzbgo.zza(parcel, 3, this.zzhii, i, false);
        zzbgo.zzc(parcel, 4, this.zzhll);
        zzbgo.zzc(parcel, 5, this.zzhlm);
        zzbgo.zza(parcel, 6, this.zzhik);
        zzbgo.zza(parcel, 7, this.zzhil);
        zzbgo.zzai(parcel, zze);
    }

    public final Value[] zzaro() {
        return this.zzhii;
    }

    public final long zzarq() {
        return this.zzhik;
    }

    public final long zzarr() {
        return this.zzhil;
    }

    public final long zzarz() {
        return this.zzhih;
    }

    public final int zzasa() {
        return this.zzhll;
    }

    public final int zzasb() {
        return this.zzhlm;
    }
}
