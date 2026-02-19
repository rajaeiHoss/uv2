package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzbzn;
import com.google.android.gms.internal.zzbzo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SessionReadRequest extends zzbgl {
    public static final Parcelable.Creator<SessionReadRequest> CREATOR = new zzaw();
    private final String zzcrz;
    private final long zzhhl;
    private final long zzhhm;
    private final List<DataType> zzhhz;
    private final List<DataSource> zzhnx;
    private final boolean zzhoh;
    private final String zzhpj;
    private boolean zzhpk;
    private final List<String> zzhpl;
    private final zzbzn zzhpm;

    public static class Builder {
        /* access modifiers changed from: private */
        public String zzcrz;
        /* access modifiers changed from: private */
        public long zzhhl = 0;
        /* access modifiers changed from: private */
        public long zzhhm = 0;
        /* access modifiers changed from: private */
        public List<DataType> zzhhz = new ArrayList();
        /* access modifiers changed from: private */
        public List<DataSource> zzhnx = new ArrayList();
        /* access modifiers changed from: private */
        public boolean zzhoh = false;
        /* access modifiers changed from: private */
        public String zzhpj;
        /* access modifiers changed from: private */
        public List<String> zzhpl = new ArrayList();
        /* access modifiers changed from: private */
        public boolean zzhpn = false;

        public SessionReadRequest build() {
            long j = this.zzhhl;
            zzbq.zzb(j > 0, "Invalid start time: %s", Long.valueOf(j));
            long j2 = this.zzhhm;
            zzbq.zzb(j2 > 0 && j2 > this.zzhhl, "Invalid end time: %s", Long.valueOf(j2));
            return new SessionReadRequest(this);
        }

        public Builder enableServerQueries() {
            this.zzhoh = true;
            return this;
        }

        public Builder excludePackage(String str) {
            zzbq.checkNotNull(str, "Attempting to use a null package name");
            if (!this.zzhpl.contains(str)) {
                this.zzhpl.add(str);
            }
            return this;
        }

        public Builder read(DataSource dataSource) {
            zzbq.checkNotNull(dataSource, "Attempting to add a null data source");
            if (!this.zzhnx.contains(dataSource)) {
                this.zzhnx.add(dataSource);
            }
            return this;
        }

        public Builder read(DataType dataType) {
            zzbq.checkNotNull(dataType, "Attempting to use a null data type");
            if (!this.zzhhz.contains(dataType)) {
                this.zzhhz.add(dataType);
            }
            return this;
        }

        public Builder readSessionsFromAllApps() {
            this.zzhpn = true;
            return this;
        }

        public Builder setSessionId(String str) {
            this.zzcrz = str;
            return this;
        }

        public Builder setSessionName(String str) {
            this.zzhpj = str;
            return this;
        }

        public Builder setTimeInterval(long j, long j2, TimeUnit timeUnit) {
            this.zzhhl = timeUnit.toMillis(j);
            this.zzhhm = timeUnit.toMillis(j2);
            return this;
        }
    }

    private SessionReadRequest(Builder builder) {
        this(builder.zzhpj, builder.zzcrz, builder.zzhhl, builder.zzhhm, (List<DataType>) builder.zzhhz, (List<DataSource>) builder.zzhnx, builder.zzhpn, builder.zzhoh, (List<String>) builder.zzhpl, (zzbzn) null);
    }

    public SessionReadRequest(SessionReadRequest sessionReadRequest, zzbzn zzbzn) {
        this(sessionReadRequest.zzhpj, sessionReadRequest.zzcrz, sessionReadRequest.zzhhl, sessionReadRequest.zzhhm, sessionReadRequest.zzhhz, sessionReadRequest.zzhnx, sessionReadRequest.zzhpk, sessionReadRequest.zzhoh, sessionReadRequest.zzhpl, zzbzn);
    }

    SessionReadRequest(String str, String str2, long j, long j2, List<DataType> list, List<DataSource> list2, boolean z, boolean z2, List<String> list3, IBinder iBinder) {
        this.zzhpj = str;
        this.zzcrz = str2;
        this.zzhhl = j;
        this.zzhhm = j2;
        this.zzhhz = list;
        this.zzhnx = list2;
        this.zzhpk = z;
        this.zzhoh = z2;
        this.zzhpl = list3;
        this.zzhpm = zzbzo.zzay(iBinder);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    private SessionReadRequest(String str, String str2, long j, long j2, List<DataType> list, List<DataSource> list2, boolean z, boolean z2, List<String> list3, zzbzn zzbzn) {
        this(str, str2, j, j2, list, list2, z, z2, list3, zzbzn == null ? null : zzbzn.asBinder());
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof SessionReadRequest) {
                SessionReadRequest sessionReadRequest = (SessionReadRequest) obj;
                if (zzbg.equal(this.zzhpj, sessionReadRequest.zzhpj) && this.zzcrz.equals(sessionReadRequest.zzcrz) && this.zzhhl == sessionReadRequest.zzhhl && this.zzhhm == sessionReadRequest.zzhhm && zzbg.equal(this.zzhhz, sessionReadRequest.zzhhz) && zzbg.equal(this.zzhnx, sessionReadRequest.zzhnx) && this.zzhpk == sessionReadRequest.zzhpk && this.zzhpl.equals(sessionReadRequest.zzhpl) && this.zzhoh == sessionReadRequest.zzhoh) {
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

    public List<String> getExcludedPackages() {
        return this.zzhpl;
    }

    public String getSessionId() {
        return this.zzcrz;
    }

    public String getSessionName() {
        return this.zzhpj;
    }

    public long getStartTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzhhl, TimeUnit.MILLISECONDS);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzhpj, this.zzcrz, Long.valueOf(this.zzhhl), Long.valueOf(this.zzhhm)});
    }

    public boolean includeSessionsFromAllApps() {
        return this.zzhpk;
    }

    public String toString() {
        return zzbg.zzx(this).zzg("sessionName", this.zzhpj).zzg("sessionId", this.zzcrz).zzg("startTimeMillis", Long.valueOf(this.zzhhl)).zzg("endTimeMillis", Long.valueOf(this.zzhhm)).zzg("dataTypes", this.zzhhz).zzg("dataSources", this.zzhnx).zzg("sessionsFromAllApps", Boolean.valueOf(this.zzhpk)).zzg("excludedPackages", this.zzhpl).zzg("useServer", Boolean.valueOf(this.zzhoh)).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, getSessionName(), false);
        zzbgo.zza(parcel, 2, getSessionId(), false);
        zzbgo.zza(parcel, 3, this.zzhhl);
        zzbgo.zza(parcel, 4, this.zzhhm);
        zzbgo.zzc(parcel, 5, getDataTypes(), false);
        zzbgo.zzc(parcel, 6, getDataSources(), false);
        zzbgo.zza(parcel, 7, includeSessionsFromAllApps());
        zzbgo.zza(parcel, 8, this.zzhoh);
        zzbgo.zzb(parcel, 9, getExcludedPackages(), false);
        zzbzn zzbzn = this.zzhpm;
        zzbgo.zza(parcel, 10, zzbzn == null ? null : zzbzn.asBinder(), false);
        zzbgo.zzai(parcel, zze);
    }
}
