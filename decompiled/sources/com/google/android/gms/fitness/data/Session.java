package com.google.android.gms.fitness.data;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzbgq;
import com.google.android.gms.internal.zzfmk;
import com.google.android.gms.plus.PlusShare;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class Session extends zzbgl {
    public static final Parcelable.Creator<Session> CREATOR = new zzad();
    public static final String EXTRA_SESSION = "vnd.google.fitness.session";
    public static final String MIME_TYPE_PREFIX = "vnd.google.fitness.session/";
    private final String description;
    private final String name;
    private final long zzhhl;
    private final long zzhhm;
    private final int zzhia;
    private final zzb zzhiq;
    private final String zzhlo;
    private final Long zzhlp;

    public static class Builder {
        /* access modifiers changed from: private */
        public String description;
        /* access modifiers changed from: private */
        public String name = null;
        /* access modifiers changed from: private */
        public long zzhhl = 0;
        /* access modifiers changed from: private */
        public long zzhhm = 0;
        /* access modifiers changed from: private */
        public int zzhia = 4;
        /* access modifiers changed from: private */
        public String zzhlo;
        /* access modifiers changed from: private */
        public Long zzhlp;

        public Session build() {
            boolean z = true;
            zzbq.zza(this.zzhhl > 0, (Object) "Start time should be specified.");
            long j = this.zzhhm;
            if (j != 0 && j <= this.zzhhl) {
                z = false;
            }
            zzbq.zza(z, (Object) "End time should be later than start time.");
            if (this.zzhlo == null) {
                String str = this.name;
                if (str == null) {
                    str = "";
                }
                long j2 = this.zzhhl;
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 20);
                sb.append(str);
                sb.append(j2);
                this.zzhlo = sb.toString();
            }
            if (this.description == null) {
                this.description = "";
            }
            return new Session(this);
        }

        public Builder setActiveTime(long j, TimeUnit timeUnit) {
            this.zzhlp = Long.valueOf(timeUnit.toMillis(j));
            return this;
        }

        public Builder setActivity(String str) {
            this.zzhia = zzfmk.zzuc(str);
            return this;
        }

        public Builder setDescription(String str) {
            zzbq.zzb(str.length() <= 1000, "Session description cannot exceed %d characters", 1000);
            this.description = str;
            return this;
        }

        public Builder setEndTime(long j, TimeUnit timeUnit) {
            zzbq.zza(j >= 0, (Object) "End time should be positive.");
            this.zzhhm = timeUnit.toMillis(j);
            return this;
        }

        public Builder setIdentifier(String str) {
            zzbq.checkArgument(str != null && TextUtils.getTrimmedLength(str) > 0);
            this.zzhlo = str;
            return this;
        }

        public Builder setName(String str) {
            zzbq.zzb(str.length() <= 100, "Session name cannot exceed %d characters", 100);
            this.name = str;
            return this;
        }

        public Builder setStartTime(long j, TimeUnit timeUnit) {
            zzbq.zza(j > 0, (Object) "Start time should be positive.");
            this.zzhhl = timeUnit.toMillis(j);
            return this;
        }
    }

    public Session(long j, long j2, String str, String str2, String str3, int i, zzb zzb, Long l) {
        this.zzhhl = j;
        this.zzhhm = j2;
        this.name = str;
        this.zzhlo = str2;
        this.description = str3;
        this.zzhia = i;
        this.zzhiq = zzb;
        this.zzhlp = l;
    }

    private Session(Builder builder) {
        this(builder.zzhhl, builder.zzhhm, builder.name, builder.zzhlo, builder.description, builder.zzhia, (zzb) null, builder.zzhlp);
    }

    public static Session extract(Intent intent) {
        if (intent == null) {
            return null;
        }
        return (Session) zzbgq.zza(intent, EXTRA_SESSION, CREATOR);
    }

    public static String getMimeType(String str) {
        String valueOf = String.valueOf(str);
        return valueOf.length() != 0 ? MIME_TYPE_PREFIX.concat(valueOf) : new String(MIME_TYPE_PREFIX);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Session)) {
            return false;
        }
        Session session = (Session) obj;
        return this.zzhhl == session.zzhhl && this.zzhhm == session.zzhhm && zzbg.equal(this.name, session.name) && zzbg.equal(this.zzhlo, session.zzhlo) && zzbg.equal(this.description, session.description) && zzbg.equal(this.zzhiq, session.zzhiq) && this.zzhia == session.zzhia;
    }

    public long getActiveTime(TimeUnit timeUnit) {
        zzbq.zza(this.zzhlp != null, (Object) "Active time is not set");
        return timeUnit.convert(this.zzhlp.longValue(), TimeUnit.MILLISECONDS);
    }

    public String getActivity() {
        return zzfmk.getName(this.zzhia);
    }

    public String getAppPackageName() {
        zzb zzb = this.zzhiq;
        if (zzb == null) {
            return null;
        }
        return zzb.getPackageName();
    }

    public String getDescription() {
        return this.description;
    }

    public long getEndTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzhhm, TimeUnit.MILLISECONDS);
    }

    public String getIdentifier() {
        return this.zzhlo;
    }

    public String getName() {
        return this.name;
    }

    public long getStartTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzhhl, TimeUnit.MILLISECONDS);
    }

    public boolean hasActiveTime() {
        return this.zzhlp != null;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Long.valueOf(this.zzhhl), Long.valueOf(this.zzhhm), this.zzhlo});
    }

    public boolean isOngoing() {
        return this.zzhhm == 0;
    }

    public String toString() {
        return zzbg.zzx(this).zzg("startTime", Long.valueOf(this.zzhhl)).zzg("endTime", Long.valueOf(this.zzhhm)).zzg("name", this.name).zzg("identifier", this.zzhlo).zzg(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION, this.description).zzg("activity", Integer.valueOf(this.zzhia)).zzg("application", this.zzhiq).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, this.zzhhl);
        zzbgo.zza(parcel, 2, this.zzhhm);
        zzbgo.zza(parcel, 3, getName(), false);
        zzbgo.zza(parcel, 4, getIdentifier(), false);
        zzbgo.zza(parcel, 5, getDescription(), false);
        zzbgo.zzc(parcel, 7, this.zzhia);
        zzbgo.zza(parcel, 8, (Parcelable) this.zzhiq, i, false);
        zzbgo.zza(parcel, 9, this.zzhlp, false);
        zzbgo.zzai(parcel, zze);
    }
}
