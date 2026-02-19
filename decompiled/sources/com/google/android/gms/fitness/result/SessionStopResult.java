package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SessionStopResult extends zzbgl implements Result {
    public static final Parcelable.Creator<SessionStopResult> CREATOR = new zzi();
    private final Status zzefs;
    private final List<Session> zzhny;

    public SessionStopResult(Status status, List<Session> list) {
        this.zzefs = status;
        this.zzhny = Collections.unmodifiableList(list);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof SessionStopResult) {
                SessionStopResult sessionStopResult = (SessionStopResult) obj;
                if (this.zzefs.equals(sessionStopResult.zzefs) && zzbg.equal(this.zzhny, sessionStopResult.zzhny)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public List<Session> getSessions() {
        return this.zzhny;
    }

    public Status getStatus() {
        return this.zzefs;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzefs, this.zzhny});
    }

    public String toString() {
        return zzbg.zzx(this).zzg("status", this.zzefs).zzg("sessions", this.zzhny).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, (Parcelable) getStatus(), i, false);
        zzbgo.zzc(parcel, 3, getSessions(), false);
        zzbgo.zzai(parcel, zze);
    }
}
