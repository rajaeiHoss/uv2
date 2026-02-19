package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzbzt;
import com.google.android.gms.internal.zzbzu;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DataDeleteRequest extends zzbgl {
    public static final Parcelable.Creator<DataDeleteRequest> CREATOR = new zzj();
    private final long zzhhl;
    private final long zzhhm;
    private final List<DataType> zzhhz;
    private final zzbzt zzhnu;
    private final List<DataSource> zzhnx;
    private final List<Session> zzhny;
    private final boolean zzhnz;
    private final boolean zzhoa;

    public static class Builder {
        /* access modifiers changed from: private */
        public long zzhhl;
        /* access modifiers changed from: private */
        public long zzhhm;
        /* access modifiers changed from: private */
        public List<DataType> zzhhz = new ArrayList();
        /* access modifiers changed from: private */
        public List<DataSource> zzhnx = new ArrayList();
        /* access modifiers changed from: private */
        public List<Session> zzhny = new ArrayList();
        /* access modifiers changed from: private */
        public boolean zzhnz = false;
        /* access modifiers changed from: private */
        public boolean zzhoa = false;

        public Builder addDataSource(DataSource dataSource) {
            boolean z = true;
            zzbq.checkArgument(!this.zzhnz, "All data is already marked for deletion.  addDataSource() cannot be combined with deleteAllData()");
            if (dataSource == null) {
                z = false;
            }
            zzbq.checkArgument(z, "Must specify a valid data source");
            if (!this.zzhnx.contains(dataSource)) {
                this.zzhnx.add(dataSource);
            }
            return this;
        }

        public Builder addDataType(DataType dataType) {
            boolean z = true;
            zzbq.checkArgument(!this.zzhnz, "All data is already marked for deletion.  addDataType() cannot be combined with deleteAllData()");
            if (dataType == null) {
                z = false;
            }
            zzbq.checkArgument(z, "Must specify a valid data type");
            if (!this.zzhhz.contains(dataType)) {
                this.zzhhz.add(dataType);
            }
            return this;
        }

        public Builder addSession(Session session) {
            boolean z = true;
            zzbq.checkArgument(!this.zzhoa, "All sessions already marked for deletion.  addSession() cannot be combined with deleteAllSessions()");
            zzbq.checkArgument(session != null, "Must specify a valid session");
            if (session.getEndTime(TimeUnit.MILLISECONDS) <= 0) {
                z = false;
            }
            zzbq.checkArgument(z, "Cannot delete an ongoing session. Please stop the session prior to deleting it");
            this.zzhny.add(session);
            return this;
        }

        public DataDeleteRequest build() {
            long j = this.zzhhl;
            zzbq.zza(j > 0 && this.zzhhm > j, (Object) "Must specify a valid time interval");
            zzbq.zza((this.zzhnz || !this.zzhnx.isEmpty() || !this.zzhhz.isEmpty()) || (this.zzhoa || !this.zzhny.isEmpty()), (Object) "No data or session marked for deletion");
            if (!this.zzhny.isEmpty()) {
                for (Session next : this.zzhny) {
                    zzbq.zza(next.getStartTime(TimeUnit.MILLISECONDS) >= this.zzhhl && next.getEndTime(TimeUnit.MILLISECONDS) <= this.zzhhm, "Session %s is outside the time interval [%d, %d]", next, Long.valueOf(this.zzhhl), Long.valueOf(this.zzhhm));
                }
            }
            return new DataDeleteRequest(this);
        }

        public Builder deleteAllData() {
            zzbq.checkArgument(this.zzhhz.isEmpty(), "Specific data type already added for deletion. deleteAllData() will delete all data types and cannot be combined with addDataType()");
            zzbq.checkArgument(this.zzhnx.isEmpty(), "Specific data source already added for deletion. deleteAllData() will delete all data sources and cannot be combined with addDataSource()");
            this.zzhnz = true;
            return this;
        }

        public Builder deleteAllSessions() {
            zzbq.checkArgument(this.zzhny.isEmpty(), "Specific session already added for deletion. deleteAllData() will delete all sessions and cannot be combined with addSession()");
            this.zzhoa = true;
            return this;
        }

        public Builder setTimeInterval(long j, long j2, TimeUnit timeUnit) {
            zzbq.zzb(j > 0, "Invalid start time :%d", Long.valueOf(j));
            zzbq.zzb(j2 > j, "Invalid end time :%d", Long.valueOf(j2));
            this.zzhhl = timeUnit.toMillis(j);
            this.zzhhm = timeUnit.toMillis(j2);
            return this;
        }
    }

    DataDeleteRequest(long j, long j2, List<DataSource> list, List<DataType> list2, List<Session> list3, boolean z, boolean z2, IBinder iBinder) {
        this.zzhhl = j;
        this.zzhhm = j2;
        this.zzhnx = Collections.unmodifiableList(list);
        this.zzhhz = Collections.unmodifiableList(list2);
        this.zzhny = list3;
        this.zzhnz = z;
        this.zzhoa = z2;
        this.zzhnu = zzbzu.zzba(iBinder);
    }

    private DataDeleteRequest(long j, long j2, List<DataSource> list, List<DataType> list2, List<Session> list3, boolean z, boolean z2, zzbzt zzbzt) {
        this.zzhhl = j;
        this.zzhhm = j2;
        this.zzhnx = Collections.unmodifiableList(list);
        this.zzhhz = Collections.unmodifiableList(list2);
        this.zzhny = list3;
        this.zzhnz = z;
        this.zzhoa = z2;
        this.zzhnu = zzbzt;
    }

    private DataDeleteRequest(Builder builder) {
        this(builder.zzhhl, builder.zzhhm, (List<DataSource>) builder.zzhnx, (List<DataType>) builder.zzhhz, (List<Session>) builder.zzhny, builder.zzhnz, builder.zzhoa, (zzbzt) null);
    }

    public DataDeleteRequest(DataDeleteRequest dataDeleteRequest, zzbzt zzbzt) {
        this(dataDeleteRequest.zzhhl, dataDeleteRequest.zzhhm, dataDeleteRequest.zzhnx, dataDeleteRequest.zzhhz, dataDeleteRequest.zzhny, dataDeleteRequest.zzhnz, dataDeleteRequest.zzhoa, zzbzt);
    }

    public boolean deleteAllData() {
        return this.zzhnz;
    }

    public boolean deleteAllSessions() {
        return this.zzhoa;
    }

    public boolean equals(Object obj) {
        if (obj != this) {
            if (obj instanceof DataDeleteRequest) {
                DataDeleteRequest dataDeleteRequest = (DataDeleteRequest) obj;
                if (this.zzhhl == dataDeleteRequest.zzhhl && this.zzhhm == dataDeleteRequest.zzhhm && zzbg.equal(this.zzhnx, dataDeleteRequest.zzhnx) && zzbg.equal(this.zzhhz, dataDeleteRequest.zzhhz) && zzbg.equal(this.zzhny, dataDeleteRequest.zzhny) && this.zzhnz == dataDeleteRequest.zzhnz && this.zzhoa == dataDeleteRequest.zzhoa) {
                    return true;
                }
            }
            return false;
        }
        return true;
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

    public List<Session> getSessions() {
        return this.zzhny;
    }

    public long getStartTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzhhl, TimeUnit.MILLISECONDS);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Long.valueOf(this.zzhhl), Long.valueOf(this.zzhhm)});
    }

    public String toString() {
        return zzbg.zzx(this).zzg("startTimeMillis", Long.valueOf(this.zzhhl)).zzg("endTimeMillis", Long.valueOf(this.zzhhm)).zzg("dataSources", this.zzhnx).zzg("dateTypes", this.zzhhz).zzg("sessions", this.zzhny).zzg("deleteAllData", Boolean.valueOf(this.zzhnz)).zzg("deleteAllSessions", Boolean.valueOf(this.zzhoa)).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, this.zzhhl);
        zzbgo.zza(parcel, 2, this.zzhhm);
        zzbgo.zzc(parcel, 3, getDataSources(), false);
        zzbgo.zzc(parcel, 4, getDataTypes(), false);
        zzbgo.zzc(parcel, 5, getSessions(), false);
        zzbgo.zza(parcel, 6, deleteAllData());
        zzbgo.zza(parcel, 7, deleteAllSessions());
        zzbzt zzbzt = this.zzhnu;
        zzbgo.zza(parcel, 8, zzbzt == null ? null : zzbzt.asBinder(), false);
        zzbgo.zzai(parcel, zze);
    }
}
