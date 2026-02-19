package com.google.android.gms.auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.streamax.config.constant.Constants;
import java.util.Arrays;

public class AccountChangeEvent extends zzbgl {
    public static final Parcelable.Creator<AccountChangeEvent> CREATOR = new zza();
    private int mVersion;
    private long zzehj;
    private String zzehk;
    private int zzehl;
    private int zzehm;
    private String zzehn;

    AccountChangeEvent(int i, long j, String str, int i2, int i3, String str2) {
        this.mVersion = i;
        this.zzehj = j;
        this.zzehk = (String) zzbq.checkNotNull(str);
        this.zzehl = i2;
        this.zzehm = i3;
        this.zzehn = str2;
    }

    public AccountChangeEvent(long j, String str, int i, int i2, String str2) {
        this.mVersion = 1;
        this.zzehj = j;
        this.zzehk = (String) zzbq.checkNotNull(str);
        this.zzehl = i;
        this.zzehm = i2;
        this.zzehn = str2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AccountChangeEvent) {
            AccountChangeEvent accountChangeEvent = (AccountChangeEvent) obj;
            return this.mVersion == accountChangeEvent.mVersion && this.zzehj == accountChangeEvent.zzehj && zzbg.equal(this.zzehk, accountChangeEvent.zzehk) && this.zzehl == accountChangeEvent.zzehl && this.zzehm == accountChangeEvent.zzehm && zzbg.equal(this.zzehn, accountChangeEvent.zzehn);
        }
        return false;
    }

    public String getAccountName() {
        return this.zzehk;
    }

    public String getChangeData() {
        return this.zzehn;
    }

    public int getChangeType() {
        return this.zzehl;
    }

    public int getEventIndex() {
        return this.zzehm;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.mVersion), Long.valueOf(this.zzehj), this.zzehk, Integer.valueOf(this.zzehl), Integer.valueOf(this.zzehm), this.zzehn});
    }

    public String toString() {
        int i = this.zzehl;
        String str = i != 1 ? i != 2 ? i != 3 ? i != 4 ? "UNKNOWN" : "RENAMED_TO" : "RENAMED_FROM" : "REMOVED" : "ADDED";
        String str2 = this.zzehk;
        String str3 = this.zzehn;
        int i2 = this.zzehm;
        StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 91 + str.length() + String.valueOf(str3).length());
        sb.append("AccountChangeEvent {accountName = ");
        sb.append(str2);
        sb.append(", changeType = ");
        sb.append(str);
        sb.append(", changeData = ");
        sb.append(str3);
        sb.append(", eventIndex = ");
        sb.append(i2);
        sb.append(Constants.JsonSstringSuffix);
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 1, this.mVersion);
        zzbgo.zza(parcel, 2, this.zzehj);
        zzbgo.zza(parcel, 3, this.zzehk, false);
        zzbgo.zzc(parcel, 4, this.zzehl);
        zzbgo.zzc(parcel, 5, this.zzehm);
        zzbgo.zza(parcel, 6, this.zzehn, false);
        zzbgo.zzai(parcel, zze);
    }
}
