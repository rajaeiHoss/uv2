package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.fitness.data.zzae;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SessionReadResult extends zzbgl implements Result {
    public static final Parcelable.Creator<SessionReadResult> CREATOR = new zzh();
    private final Status zzefs;
    private final List<Session> zzhny;
    private final List<zzae> zzhpz;

    public SessionReadResult(List<Session> list, List<zzae> list2, Status status) {
        this.zzhny = list;
        this.zzhpz = Collections.unmodifiableList(list2);
        this.zzefs = status;
    }

    public static SessionReadResult zzah(Status status) {
        return new SessionReadResult(new ArrayList(), new ArrayList(), status);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof SessionReadResult) {
                SessionReadResult sessionReadResult = (SessionReadResult) obj;
                if (this.zzefs.equals(sessionReadResult.zzefs) && zzbg.equal(this.zzhny, sessionReadResult.zzhny) && zzbg.equal(this.zzhpz, sessionReadResult.zzhpz)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public List<DataSet> getDataSet(Session session) {
        zzbq.zzb(this.zzhny.contains(session), "Attempting to read data for session %s which was not returned", session);
        ArrayList arrayList = new ArrayList();
        for (zzae next : this.zzhpz) {
            if (zzbg.equal(session, next.getSession())) {
                arrayList.add(next.getDataSet());
            }
        }
        return arrayList;
    }

    public List<DataSet> getDataSet(Session session, DataType dataType) {
        zzbq.zzb(this.zzhny.contains(session), "Attempting to read data for session %s which was not returned", session);
        ArrayList arrayList = new ArrayList();
        for (zzae next : this.zzhpz) {
            if (zzbg.equal(session, next.getSession()) && dataType.equals(next.getDataSet().getDataType())) {
                arrayList.add(next.getDataSet());
            }
        }
        return arrayList;
    }

    public List<Session> getSessions() {
        return this.zzhny;
    }

    public Status getStatus() {
        return this.zzefs;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzefs, this.zzhny, this.zzhpz});
    }

    public String toString() {
        return zzbg.zzx(this).zzg("status", this.zzefs).zzg("sessions", this.zzhny).zzg("sessionDataSets", this.zzhpz).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 1, getSessions(), false);
        zzbgo.zzc(parcel, 2, this.zzhpz, false);
        zzbgo.zza(parcel, 3, (Parcelable) getStatus(), i, false);
        zzbgo.zzai(parcel, zze);
    }
}
