package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.fitness.data.Bucket;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Device;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzbyk;
import com.google.android.gms.internal.zzbyl;
import com.streamax.config.constant.Constants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DataReadRequest extends zzbgl {
    public static final Parcelable.Creator<DataReadRequest> CREATOR = new zzn();
    public static final int NO_LIMIT = 0;
    private final int limit;
    private final long zzhhl;
    private final long zzhhm;
    private final List<DataType> zzhhz;
    private final int zzhic;
    private final List<DataSource> zzhnx;
    private final List<DataType> zzhoc;
    private final List<DataSource> zzhod;
    private final long zzhoe;
    private final DataSource zzhof;
    private final boolean zzhog;
    private final boolean zzhoh;
    private final zzbyk zzhoi;
    private final List<Device> zzhoj;
    private final List<Integer> zzhok;

    public static class Builder {
        /* access modifiers changed from: private */
        public int limit = 0;
        /* access modifiers changed from: private */
        public long zzhhl;
        /* access modifiers changed from: private */
        public long zzhhm;
        /* access modifiers changed from: private */
        public List<DataType> zzhhz = new ArrayList();
        /* access modifiers changed from: private */
        public int zzhic = 0;
        /* access modifiers changed from: private */
        public List<DataSource> zzhnx = new ArrayList();
        /* access modifiers changed from: private */
        public List<DataType> zzhoc = new ArrayList();
        /* access modifiers changed from: private */
        public List<DataSource> zzhod = new ArrayList();
        /* access modifiers changed from: private */
        public long zzhoe = 0;
        /* access modifiers changed from: private */
        public DataSource zzhof;
        private boolean zzhog = false;
        /* access modifiers changed from: private */
        public boolean zzhoh = false;
        /* access modifiers changed from: private */
        public final List<Device> zzhoj = new ArrayList();
        /* access modifiers changed from: private */
        public final List<Integer> zzhok = new ArrayList();

        public Builder addFilteredDataQualityStandard(int i) {
            zzbq.checkArgument(this.zzhoj.isEmpty(), "Cannot add data quality standard filter when filtering by device.");
            this.zzhok.add(Integer.valueOf(i));
            return this;
        }

        public Builder aggregate(DataSource dataSource, DataType dataType) {
            zzbq.checkNotNull(dataSource, "Attempting to add a null data source");
            zzbq.zza(!this.zzhnx.contains(dataSource), (Object) "Cannot add the same data source for aggregated and detailed");
            DataType dataType2 = dataSource.getDataType();
            List<DataType> aggregatesForInput = DataType.getAggregatesForInput(dataType2);
            zzbq.zzb(!aggregatesForInput.isEmpty(), "Unsupported input data type specified for aggregation: %s", dataType2);
            zzbq.zzb(aggregatesForInput.contains(dataType), "Invalid output aggregate data type specified: %s -> %s", dataType2, dataType);
            if (!this.zzhod.contains(dataSource)) {
                this.zzhod.add(dataSource);
            }
            return this;
        }

        public Builder aggregate(DataType dataType, DataType dataType2) {
            zzbq.checkNotNull(dataType, "Attempting to use a null data type");
            zzbq.zza(!this.zzhhz.contains(dataType), (Object) "Cannot add the same data type as aggregated and detailed");
            List<DataType> aggregatesForInput = DataType.getAggregatesForInput(dataType);
            zzbq.zzb(!aggregatesForInput.isEmpty(), "Unsupported input data type specified for aggregation: %s", dataType);
            zzbq.zzb(aggregatesForInput.contains(dataType2), "Invalid output aggregate data type specified: %s -> %s", dataType, dataType2);
            if (!this.zzhoc.contains(dataType)) {
                this.zzhoc.add(dataType);
            }
            return this;
        }

        public Builder bucketByActivitySegment(int i, TimeUnit timeUnit) {
            int i2 = this.zzhic;
            zzbq.zzb(i2 == 0, "Bucketing strategy already set to %s", Integer.valueOf(i2));
            zzbq.zzb(i > 0, "Must specify a valid minimum duration for an activity segment: %d", Integer.valueOf(i));
            this.zzhic = 4;
            this.zzhoe = timeUnit.toMillis((long) i);
            return this;
        }

        public Builder bucketByActivitySegment(int i, TimeUnit timeUnit, DataSource dataSource) {
            int i2 = this.zzhic;
            zzbq.zzb(i2 == 0, "Bucketing strategy already set to %s", Integer.valueOf(i2));
            zzbq.zzb(i > 0, "Must specify a valid minimum duration for an activity segment: %d", Integer.valueOf(i));
            zzbq.checkArgument(dataSource != null, "Invalid activity data source specified");
            zzbq.zzb(dataSource.getDataType().equals(DataType.TYPE_ACTIVITY_SEGMENT), "Invalid activity data source specified: %s", dataSource);
            this.zzhof = dataSource;
            this.zzhic = 4;
            this.zzhoe = timeUnit.toMillis((long) i);
            return this;
        }

        public Builder bucketByActivityType(int i, TimeUnit timeUnit) {
            int i2 = this.zzhic;
            zzbq.zzb(i2 == 0, "Bucketing strategy already set to %s", Integer.valueOf(i2));
            zzbq.zzb(i > 0, "Must specify a valid minimum duration for an activity segment: %d", Integer.valueOf(i));
            this.zzhic = 3;
            this.zzhoe = timeUnit.toMillis((long) i);
            return this;
        }

        public Builder bucketByActivityType(int i, TimeUnit timeUnit, DataSource dataSource) {
            int i2 = this.zzhic;
            zzbq.zzb(i2 == 0, "Bucketing strategy already set to %s", Integer.valueOf(i2));
            zzbq.zzb(i > 0, "Must specify a valid minimum duration for an activity segment: %d", Integer.valueOf(i));
            zzbq.checkArgument(dataSource != null, "Invalid activity data source specified");
            zzbq.zzb(dataSource.getDataType().equals(DataType.TYPE_ACTIVITY_SEGMENT), "Invalid activity data source specified: %s", dataSource);
            this.zzhof = dataSource;
            this.zzhic = 3;
            this.zzhoe = timeUnit.toMillis((long) i);
            return this;
        }

        public Builder bucketBySession(int i, TimeUnit timeUnit) {
            int i2 = this.zzhic;
            zzbq.zzb(i2 == 0, "Bucketing strategy already set to %s", Integer.valueOf(i2));
            zzbq.zzb(i > 0, "Must specify a valid minimum duration for an activity segment: %d", Integer.valueOf(i));
            this.zzhic = 2;
            this.zzhoe = timeUnit.toMillis((long) i);
            return this;
        }

        public Builder bucketByTime(int i, TimeUnit timeUnit) {
            int i2 = this.zzhic;
            zzbq.zzb(i2 == 0, "Bucketing strategy already set to %s", Integer.valueOf(i2));
            zzbq.zzb(i > 0, "Must specify a valid minimum duration for an activity segment: %d", Integer.valueOf(i));
            this.zzhic = 1;
            this.zzhoe = timeUnit.toMillis((long) i);
            return this;
        }

        public DataReadRequest build() {
            boolean z = false;
            zzbq.zza(!this.zzhnx.isEmpty() || !this.zzhhz.isEmpty() || !this.zzhod.isEmpty() || !this.zzhoc.isEmpty(), (Object) "Must add at least one data source (aggregated or detailed)");
            long j = this.zzhhl;
            zzbq.zza(j > 0, "Invalid start time: %s", Long.valueOf(j));
            long j2 = this.zzhhm;
            zzbq.zza(j2 > 0 && j2 > this.zzhhl, "Invalid end time: %s", Long.valueOf(j2));
            boolean z2 = this.zzhod.isEmpty() && this.zzhoc.isEmpty();
            if ((z2 && this.zzhic == 0) || (!z2 && this.zzhic != 0)) {
                z = true;
            }
            zzbq.zza(z, (Object) "Must specify a valid bucketing strategy while requesting aggregation");
            return new DataReadRequest(this);
        }

        public Builder enableServerQueries() {
            this.zzhoh = true;
            return this;
        }

        public Builder read(DataSource dataSource) {
            zzbq.checkNotNull(dataSource, "Attempting to add a null data source");
            zzbq.checkArgument(!this.zzhod.contains(dataSource), "Cannot add the same data source as aggregated and detailed");
            if (!this.zzhnx.contains(dataSource)) {
                this.zzhnx.add(dataSource);
            }
            return this;
        }

        public Builder read(DataType dataType) {
            zzbq.checkNotNull(dataType, "Attempting to use a null data type");
            zzbq.zza(!this.zzhoc.contains(dataType), (Object) "Cannot add the same data type as aggregated and detailed");
            if (!this.zzhhz.contains(dataType)) {
                this.zzhhz.add(dataType);
            }
            return this;
        }

        public Builder setLimit(int i) {
            zzbq.zzb(i > 0, "Invalid limit %d is specified", Integer.valueOf(i));
            this.limit = i;
            return this;
        }

        public Builder setTimeRange(long j, long j2, TimeUnit timeUnit) {
            this.zzhhl = timeUnit.toMillis(j);
            this.zzhhm = timeUnit.toMillis(j2);
            return this;
        }
    }

    private DataReadRequest(Builder builder) {
        this((List<DataType>) builder.zzhhz, (List<DataSource>) builder.zzhnx, builder.zzhhl, builder.zzhhm, (List<DataType>) builder.zzhoc, (List<DataSource>) builder.zzhod, builder.zzhic, builder.zzhoe, builder.zzhof, builder.limit, false, builder.zzhoh, (zzbyk) null, (List<Device>) builder.zzhoj, (List<Integer>) builder.zzhok);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public DataReadRequest(com.google.android.gms.fitness.request.DataReadRequest r22, com.google.android.gms.internal.zzbyk r23) {
        /*
            r21 = this;
            r0 = r22
            r1 = r21
            r17 = r23
            java.util.List<com.google.android.gms.fitness.data.DataType> r2 = r0.zzhhz
            java.util.List<com.google.android.gms.fitness.data.DataSource> r3 = r0.zzhnx
            long r4 = r0.zzhhl
            long r6 = r0.zzhhm
            java.util.List<com.google.android.gms.fitness.data.DataType> r8 = r0.zzhoc
            java.util.List<com.google.android.gms.fitness.data.DataSource> r9 = r0.zzhod
            int r10 = r0.zzhic
            long r11 = r0.zzhoe
            com.google.android.gms.fitness.data.DataSource r13 = r0.zzhof
            int r14 = r0.limit
            boolean r15 = r0.zzhog
            r20 = r1
            boolean r1 = r0.zzhoh
            r16 = r1
            java.util.List<com.google.android.gms.fitness.data.Device> r1 = r0.zzhoj
            r18 = r1
            java.util.List<java.lang.Integer> r0 = r0.zzhok
            r19 = r0
            r1 = r20
            r1.<init>((java.util.List<com.google.android.gms.fitness.data.DataType>) r2, (java.util.List<com.google.android.gms.fitness.data.DataSource>) r3, (long) r4, (long) r6, (java.util.List<com.google.android.gms.fitness.data.DataType>) r8, (java.util.List<com.google.android.gms.fitness.data.DataSource>) r9, (int) r10, (long) r11, (com.google.android.gms.fitness.data.DataSource) r13, (int) r14, (boolean) r15, (boolean) r16, (com.google.android.gms.internal.zzbyk) r17, (java.util.List<com.google.android.gms.fitness.data.Device>) r18, (java.util.List<java.lang.Integer>) r19)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.fitness.request.DataReadRequest.<init>(com.google.android.gms.fitness.request.DataReadRequest, com.google.android.gms.internal.zzbyk):void");
    }

    DataReadRequest(List<DataType> list, List<DataSource> list2, long j, long j2, List<DataType> list3, List<DataSource> list4, int i, long j3, DataSource dataSource, int i2, boolean z, boolean z2, IBinder iBinder, List<Device> list5, List<Integer> list6) {
        this.zzhhz = list;
        this.zzhnx = list2;
        this.zzhhl = j;
        this.zzhhm = j2;
        this.zzhoc = list3;
        this.zzhod = list4;
        this.zzhic = i;
        this.zzhoe = j3;
        this.zzhof = dataSource;
        this.limit = i2;
        this.zzhog = z;
        this.zzhoh = z2;
        this.zzhoi = iBinder == null ? null : zzbyl.zzat(iBinder);
        this.zzhoj = list5 == null ? Collections.emptyList() : list5;
        this.zzhok = list6 == null ? Collections.emptyList() : list6;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    private DataReadRequest(List<DataType> list, List<DataSource> list2, long j, long j2, List<DataType> list3, List<DataSource> list4, int i, long j3, DataSource dataSource, int i2, boolean z, boolean z2, zzbyk zzbyk, List<Device> list5, List<Integer> list6) {
        this(list, list2, j, j2, list3, list4, i, j3, dataSource, i2, z, z2, zzbyk == null ? null : zzbyk.asBinder(), list5, list6);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof DataReadRequest) {
                DataReadRequest dataReadRequest = (DataReadRequest) obj;
                if (this.zzhhz.equals(dataReadRequest.zzhhz) && this.zzhnx.equals(dataReadRequest.zzhnx) && this.zzhhl == dataReadRequest.zzhhl && this.zzhhm == dataReadRequest.zzhhm && this.zzhic == dataReadRequest.zzhic && this.zzhod.equals(dataReadRequest.zzhod) && this.zzhoc.equals(dataReadRequest.zzhoc) && zzbg.equal(this.zzhof, dataReadRequest.zzhof) && this.zzhoe == dataReadRequest.zzhoe && this.zzhoh == dataReadRequest.zzhoh && this.limit == dataReadRequest.limit && this.zzhog == dataReadRequest.zzhog && zzbg.equal(this.zzhoi, dataReadRequest.zzhoi) && zzbg.equal(this.zzhoj, dataReadRequest.zzhoj) && zzbg.equal(this.zzhok, dataReadRequest.zzhok)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public DataSource getActivityDataSource() {
        return this.zzhof;
    }

    public List<DataSource> getAggregatedDataSources() {
        return this.zzhod;
    }

    public List<DataType> getAggregatedDataTypes() {
        return this.zzhoc;
    }

    public long getBucketDuration(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzhoe, TimeUnit.MILLISECONDS);
    }

    public int getBucketType() {
        return this.zzhic;
    }

    public List<DataSource> getDataSources() {
        return this.zzhnx;
    }

    public List<DataType> getDataTypes() {
        return this.zzhhz;
    }

    public long getEndTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzhhm, TimeUnit.MILLISECONDS);
    }

    public List<Integer> getFilteredDataQualityStandards() {
        return this.zzhok;
    }

    public int getLimit() {
        return this.limit;
    }

    public long getStartTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzhhl, TimeUnit.MILLISECONDS);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.zzhic), Long.valueOf(this.zzhhl), Long.valueOf(this.zzhhm)});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DataReadRequest{");
        if (!this.zzhhz.isEmpty()) {
            for (DataType zzary : this.zzhhz) {
                sb.append(zzary.zzary());
                sb.append(" ");
            }
        }
        if (!this.zzhnx.isEmpty()) {
            for (DataSource debugString : this.zzhnx) {
                sb.append(debugString.toDebugString());
                sb.append(" ");
            }
        }
        if (this.zzhic != 0) {
            sb.append("bucket by ");
            sb.append(Bucket.zzda(this.zzhic));
            if (this.zzhoe > 0) {
                sb.append(" >");
                sb.append(this.zzhoe);
                sb.append("ms");
            }
            sb.append(": ");
        }
        if (!this.zzhoc.isEmpty()) {
            for (DataType zzary2 : this.zzhoc) {
                sb.append(zzary2.zzary());
                sb.append(" ");
            }
        }
        if (!this.zzhod.isEmpty()) {
            for (DataSource debugString2 : this.zzhod) {
                sb.append(debugString2.toDebugString());
                sb.append(" ");
            }
        }
        sb.append(String.format("(%tF %tT - %tF %tT)", new Object[]{Long.valueOf(this.zzhhl), Long.valueOf(this.zzhhl), Long.valueOf(this.zzhhm), Long.valueOf(this.zzhhm)}));
        if (this.zzhof != null) {
            sb.append("activities: ");
            sb.append(this.zzhof.toDebugString());
        }
        if (!this.zzhok.isEmpty()) {
            sb.append("quality: ");
            for (Integer intValue : this.zzhok) {
                sb.append(DataSource.zzdd(intValue.intValue()));
                sb.append(" ");
            }
        }
        if (this.zzhoh) {
            sb.append(" +server");
        }
        sb.append(Constants.JsonSstringSuffix);
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 1, getDataTypes(), false);
        zzbgo.zzc(parcel, 2, getDataSources(), false);
        zzbgo.zza(parcel, 3, this.zzhhl);
        zzbgo.zza(parcel, 4, this.zzhhm);
        zzbgo.zzc(parcel, 5, getAggregatedDataTypes(), false);
        zzbgo.zzc(parcel, 6, getAggregatedDataSources(), false);
        zzbgo.zzc(parcel, 7, getBucketType());
        zzbgo.zza(parcel, 8, this.zzhoe);
        zzbgo.zza(parcel, 9, (Parcelable) getActivityDataSource(), i, false);
        zzbgo.zzc(parcel, 10, getLimit());
        zzbgo.zza(parcel, 12, this.zzhog);
        zzbgo.zza(parcel, 13, this.zzhoh);
        zzbyk zzbyk = this.zzhoi;
        zzbgo.zza(parcel, 14, zzbyk == null ? null : zzbyk.asBinder(), false);
        zzbgo.zzc(parcel, 16, this.zzhoj, false);
        zzbgo.zza(parcel, 17, getFilteredDataQualityStandards(), false);
        zzbgo.zzai(parcel, zze);
    }
}
