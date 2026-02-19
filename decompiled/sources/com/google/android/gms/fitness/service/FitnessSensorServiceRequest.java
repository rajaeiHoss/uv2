package com.google.android.gms.fitness.service;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.zzt;
import com.google.android.gms.fitness.data.zzu;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class FitnessSensorServiceRequest extends zzbgl {
    public static final Parcelable.Creator<FitnessSensorServiceRequest> CREATOR = new zzb();
    public static final int UNSPECIFIED = -1;
    private final DataSource zzhhk;
    private final zzt zzhoz;
    private final long zzhqc;
    private final long zzhqd;

    FitnessSensorServiceRequest(DataSource dataSource, IBinder iBinder, long j, long j2) {
        this.zzhhk = dataSource;
        this.zzhoz = zzu.zzar(iBinder);
        this.zzhqc = j;
        this.zzhqd = j2;
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof FitnessSensorServiceRequest) {
                FitnessSensorServiceRequest fitnessSensorServiceRequest = (FitnessSensorServiceRequest) obj;
                if (zzbg.equal(this.zzhhk, fitnessSensorServiceRequest.zzhhk) && this.zzhqc == fitnessSensorServiceRequest.zzhqc && this.zzhqd == fitnessSensorServiceRequest.zzhqd) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public long getBatchInterval(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzhqd, TimeUnit.MICROSECONDS);
    }

    public DataSource getDataSource() {
        return this.zzhhk;
    }

    public SensorEventDispatcher getDispatcher() {
        return new zzc(this.zzhoz);
    }

    public long getSamplingRate(TimeUnit timeUnit) {
        long j = this.zzhqc;
        if (j == -1) {
            return -1;
        }
        return timeUnit.convert(j, TimeUnit.MICROSECONDS);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzhhk, Long.valueOf(this.zzhqc), Long.valueOf(this.zzhqd)});
    }

    public String toString() {
        return String.format("FitnessSensorServiceRequest{%s}", new Object[]{this.zzhhk});
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, (Parcelable) getDataSource(), i, false);
        zzbgo.zza(parcel, 2, this.zzhoz.asBinder(), false);
        zzbgo.zza(parcel, 3, this.zzhqc);
        zzbgo.zza(parcel, 4, this.zzhqd);
        zzbgo.zzai(parcel, zze);
    }
}
