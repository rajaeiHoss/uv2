package com.google.android.gms.fitness.request;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.zzt;
import com.google.android.gms.fitness.data.zzu;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzbzt;
import com.google.android.gms.internal.zzbzu;
import com.google.android.gms.internal.zzcfs;
import com.google.android.gms.location.LocationRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class zzao extends zzbgl {
    public static final Parcelable.Creator<zzao> CREATOR = new zzap();
    private final PendingIntent zzekk;
    private DataType zzhhj;
    private DataSource zzhhk;
    private final long zzhlr;
    private final int zzhls;
    private final zzbzt zzhnu;
    private zzt zzhoz;
    private final long zzhpa;
    private final long zzhpb;
    private final List<LocationRequest> zzhpc;
    private final long zzhpd;
    private final List<zzcfs> zzhpe;

    zzao(DataSource dataSource, DataType dataType, IBinder iBinder, int i, int i2, long j, long j2, PendingIntent pendingIntent, long j3, int i3, List<LocationRequest> list, long j4, IBinder iBinder2) {
        this.zzhhk = dataSource;
        this.zzhhj = dataType;
        this.zzhoz = iBinder == null ? null : zzu.zzar(iBinder);
        this.zzhlr = j == 0 ? (long) i : j;
        this.zzhpb = j3;
        this.zzhpa = j2 == 0 ? (long) i2 : j2;
        this.zzhpc = list;
        this.zzekk = pendingIntent;
        this.zzhls = i3;
        this.zzhpe = Collections.emptyList();
        this.zzhpd = j4;
        this.zzhnu = zzbzu.zzba(iBinder2);
    }

    private zzao(DataSource dataSource, DataType dataType, zzt zzt, PendingIntent pendingIntent, long j, long j2, long j3, int i, List<LocationRequest> list, List<zzcfs> list2, long j4, zzbzt zzbzt) {
        this.zzhhk = dataSource;
        this.zzhhj = dataType;
        this.zzhoz = zzt;
        this.zzekk = pendingIntent;
        this.zzhlr = j;
        this.zzhpb = j2;
        this.zzhpa = j3;
        this.zzhls = i;
        this.zzhpc = null;
        this.zzhpe = list2;
        this.zzhpd = j4;
        this.zzhnu = zzbzt;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public zzao(com.google.android.gms.fitness.request.SensorRequest r19, com.google.android.gms.fitness.data.zzt r20, android.app.PendingIntent r21, com.google.android.gms.internal.zzbzt r22) {
        /*
            r18 = this;
            r0 = r19
            r1 = r18
            r4 = r20
            r5 = r21
            r17 = r22
            com.google.android.gms.fitness.data.DataSource r2 = r19.getDataSource()
            com.google.android.gms.fitness.data.DataType r3 = r19.getDataType()
            java.util.concurrent.TimeUnit r6 = java.util.concurrent.TimeUnit.MICROSECONDS
            long r6 = r0.getSamplingRate(r6)
            java.util.concurrent.TimeUnit r8 = java.util.concurrent.TimeUnit.MICROSECONDS
            long r8 = r0.getFastestRate(r8)
            java.util.concurrent.TimeUnit r10 = java.util.concurrent.TimeUnit.MICROSECONDS
            long r10 = r0.getMaxDeliveryLatency(r10)
            int r12 = r19.getAccuracyMode()
            java.util.List r14 = java.util.Collections.emptyList()
            long r15 = r19.zzasi()
            r13 = 0
            r1.<init>(r2, r3, r4, r5, r6, r8, r10, r12, r13, r14, r15, r17)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.fitness.request.zzao.<init>(com.google.android.gms.fitness.request.SensorRequest, com.google.android.gms.fitness.data.zzt, android.app.PendingIntent, com.google.android.gms.internal.zzbzt):void");
    }

    public final boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof zzao) {
                zzao zzao = (zzao) obj;
                if (zzbg.equal(this.zzhhk, zzao.zzhhk) && zzbg.equal(this.zzhhj, zzao.zzhhj) && this.zzhlr == zzao.zzhlr && this.zzhpb == zzao.zzhpb && this.zzhpa == zzao.zzhpa && this.zzhls == zzao.zzhls && zzbg.equal(this.zzhpc, zzao.zzhpc)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzhhk, this.zzhhj, this.zzhoz, Long.valueOf(this.zzhlr), Long.valueOf(this.zzhpb), Long.valueOf(this.zzhpa), Integer.valueOf(this.zzhls), this.zzhpc});
    }

    public final String toString() {
        return String.format("SensorRegistrationRequest{type %s source %s interval %s fastest %s latency %s}", new Object[]{this.zzhhj, this.zzhhk, Long.valueOf(this.zzhlr), Long.valueOf(this.zzhpb), Long.valueOf(this.zzhpa)});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, (Parcelable) this.zzhhk, i, false);
        zzbgo.zza(parcel, 2, (Parcelable) this.zzhhj, i, false);
        zzt zzt = this.zzhoz;
        IBinder iBinder = null;
        zzbgo.zza(parcel, 3, zzt == null ? null : zzt.asBinder(), false);
        zzbgo.zzc(parcel, 4, 0);
        zzbgo.zzc(parcel, 5, 0);
        zzbgo.zza(parcel, 6, this.zzhlr);
        zzbgo.zza(parcel, 7, this.zzhpa);
        zzbgo.zza(parcel, 8, (Parcelable) this.zzekk, i, false);
        zzbgo.zza(parcel, 9, this.zzhpb);
        zzbgo.zzc(parcel, 10, this.zzhls);
        zzbgo.zzc(parcel, 11, this.zzhpc, false);
        zzbgo.zza(parcel, 12, this.zzhpd);
        zzbzt zzbzt = this.zzhnu;
        if (zzbzt != null) {
            iBinder = zzbzt.asBinder();
        }
        zzbgo.zza(parcel, 13, iBinder, false);
        zzbgo.zzai(parcel, zze);
    }
}
