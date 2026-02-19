package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzbzt;
import com.google.android.gms.internal.zzbzu;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class DataUpdateRequest extends zzbgl {
    public static final Parcelable.Creator<DataUpdateRequest> CREATOR = new zzz();
    private final long zzhhl;
    private final long zzhhm;
    private final DataSet zzhlq;
    private final zzbzt zzhnu;

    public static class Builder {
        /* access modifiers changed from: private */
        public long zzhhl;
        /* access modifiers changed from: private */
        public long zzhhm;
        /* access modifiers changed from: private */
        public DataSet zzhlq;

        /* JADX WARNING: Code restructure failed: missing block: B:5:0x003d, code lost:
            r9 = (r2 > 0 ? 1 : (r2 == 0 ? 0 : -1));
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.android.gms.fitness.request.DataUpdateRequest build() {
            /*
                r11 = this;
                long r0 = r11.zzhhl
                java.lang.String r2 = "Must set a non-zero value for startTimeMillis/startTime"
                com.google.android.gms.common.internal.zzbq.zza((long) r0, (java.lang.Object) r2)
                long r0 = r11.zzhhm
                java.lang.String r2 = "Must set a non-zero value for endTimeMillis/endTime"
                com.google.android.gms.common.internal.zzbq.zza((long) r0, (java.lang.Object) r2)
                com.google.android.gms.fitness.data.DataSet r0 = r11.zzhlq
                java.lang.String r1 = "Must set the data set"
                com.google.android.gms.common.internal.zzbq.checkNotNull(r0, r1)
                com.google.android.gms.fitness.data.DataSet r0 = r11.zzhlq
                java.util.List r0 = r0.getDataPoints()
                java.util.Iterator r0 = r0.iterator()
            L_0x001f:
                boolean r1 = r0.hasNext()
                if (r1 == 0) goto L_0x0089
                java.lang.Object r1 = r0.next()
                com.google.android.gms.fitness.data.DataPoint r1 = (com.google.android.gms.fitness.data.DataPoint) r1
                java.util.concurrent.TimeUnit r2 = java.util.concurrent.TimeUnit.MILLISECONDS
                long r2 = r1.getStartTime(r2)
                java.util.concurrent.TimeUnit r4 = java.util.concurrent.TimeUnit.MILLISECONDS
                long r4 = r1.getEndTime(r4)
                r1 = 0
                r6 = 1
                int r7 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
                if (r7 > 0) goto L_0x0060
                r7 = 0
                int r9 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
                if (r9 == 0) goto L_0x0049
                long r7 = r11.zzhhl
                int r10 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
                if (r10 < 0) goto L_0x0060
            L_0x0049:
                if (r9 == 0) goto L_0x0051
                long r7 = r11.zzhhm
                int r9 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
                if (r9 > 0) goto L_0x0060
            L_0x0051:
                long r7 = r11.zzhhm
                int r9 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
                if (r9 > 0) goto L_0x0060
                long r7 = r11.zzhhl
                int r9 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
                if (r9 >= 0) goto L_0x005e
                goto L_0x0060
            L_0x005e:
                r7 = 0
                goto L_0x0061
            L_0x0060:
                r7 = 1
            L_0x0061:
                r7 = r7 ^ r6
                r8 = 4
                java.lang.Object[] r8 = new java.lang.Object[r8]
                java.lang.Long r2 = java.lang.Long.valueOf(r2)
                r8[r1] = r2
                java.lang.Long r1 = java.lang.Long.valueOf(r4)
                r8[r6] = r1
                r1 = 2
                long r2 = r11.zzhhl
                java.lang.Long r2 = java.lang.Long.valueOf(r2)
                r8[r1] = r2
                r1 = 3
                long r2 = r11.zzhhm
                java.lang.Long r2 = java.lang.Long.valueOf(r2)
                r8[r1] = r2
                java.lang.String r1 = "Data Point's startTimeMillis %d, endTimeMillis %d should lie between timeRange provided in the request. StartTimeMillis %d, EndTimeMillis: %d"
                com.google.android.gms.common.internal.zzbq.zza(r7, r1, r8)
                goto L_0x001f
            L_0x0089:
                com.google.android.gms.fitness.request.DataUpdateRequest r0 = new com.google.android.gms.fitness.request.DataUpdateRequest
                r1 = 0
                r0.<init>((com.google.android.gms.fitness.request.DataUpdateRequest.Builder) r11)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.fitness.request.DataUpdateRequest.Builder.build():com.google.android.gms.fitness.request.DataUpdateRequest");
        }

        public Builder setDataSet(DataSet dataSet) {
            zzbq.checkNotNull(dataSet, "Must set the data set");
            this.zzhlq = dataSet;
            return this;
        }

        public Builder setTimeInterval(long j, long j2, TimeUnit timeUnit) {
            zzbq.zzb(j > 0, "Invalid start time :%d", Long.valueOf(j));
            zzbq.zzb(j2 >= j, "Invalid end time :%d", Long.valueOf(j2));
            this.zzhhl = timeUnit.toMillis(j);
            this.zzhhm = timeUnit.toMillis(j2);
            return this;
        }
    }

    public DataUpdateRequest(long j, long j2, DataSet dataSet, IBinder iBinder) {
        this.zzhhl = j;
        this.zzhhm = j2;
        this.zzhlq = dataSet;
        this.zzhnu = zzbzu.zzba(iBinder);
    }

    private DataUpdateRequest(Builder builder) {
        this(builder.zzhhl, builder.zzhhm, builder.zzhlq, (IBinder) null);
    }

    public DataUpdateRequest(DataUpdateRequest dataUpdateRequest, IBinder iBinder) {
        this(dataUpdateRequest.zzhhl, dataUpdateRequest.zzhhm, dataUpdateRequest.getDataSet(), iBinder);
    }

    public boolean equals(Object obj) {
        if (obj != this) {
            if (obj instanceof DataUpdateRequest) {
                DataUpdateRequest dataUpdateRequest = (DataUpdateRequest) obj;
                if (this.zzhhl == dataUpdateRequest.zzhhl && this.zzhhm == dataUpdateRequest.zzhhm && zzbg.equal(this.zzhlq, dataUpdateRequest.zzhlq)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public IBinder getCallbackBinder() {
        zzbzt zzbzt = this.zzhnu;
        if (zzbzt == null) {
            return null;
        }
        return zzbzt.asBinder();
    }

    public DataSet getDataSet() {
        return this.zzhlq;
    }

    public long getEndTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzhhm, TimeUnit.MILLISECONDS);
    }

    public long getStartTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzhhl, TimeUnit.MILLISECONDS);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Long.valueOf(this.zzhhl), Long.valueOf(this.zzhhm), this.zzhlq});
    }

    public String toString() {
        return zzbg.zzx(this).zzg("startTimeMillis", Long.valueOf(this.zzhhl)).zzg("endTimeMillis", Long.valueOf(this.zzhhm)).zzg("dataSet", this.zzhlq).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, this.zzhhl);
        zzbgo.zza(parcel, 2, this.zzhhm);
        zzbgo.zza(parcel, 3, (Parcelable) getDataSet(), i, false);
        zzbgo.zza(parcel, 4, getCallbackBinder(), false);
        zzbgo.zzai(parcel, zze);
    }

    public final long zzabi() {
        return this.zzhhl;
    }

    public final long zzasg() {
        return this.zzhhm;
    }
}
