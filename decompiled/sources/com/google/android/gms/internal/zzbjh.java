package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.data.DataHolder;

public final class zzbjh extends zzbgl {
    public static final Parcelable.Creator<zzbjh> CREATOR = new zzbji();
    private final int zzcc;
    private final long zzgmh;
    private final DataHolder zzgmq;
    private final DataHolder zzgmr;

    public zzbjh(int i, DataHolder dataHolder, long j, DataHolder dataHolder2) {
        this.zzcc = i;
        this.zzgmq = dataHolder;
        this.zzgmh = j;
        this.zzgmr = dataHolder2;
    }

    public final int getStatusCode() {
        return this.zzcc;
    }

    public final long getThrottleEndTimeMillis() {
        return this.zzgmh;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 2, this.zzcc);
        zzbgo.zza(parcel, 3, (Parcelable) this.zzgmq, i, false);
        zzbgo.zza(parcel, 4, this.zzgmh);
        zzbgo.zza(parcel, 5, (Parcelable) this.zzgmr, i, false);
        zzbgo.zzai(parcel, zze);
    }

    public final DataHolder zzaon() {
        return this.zzgmq;
    }

    public final DataHolder zzaoo() {
        return this.zzgmr;
    }

    public final void zzaop() {
        DataHolder dataHolder = this.zzgmq;
        if (dataHolder != null && !dataHolder.isClosed()) {
            this.zzgmq.close();
        }
    }

    public final void zzaoq() {
        DataHolder dataHolder = this.zzgmr;
        if (dataHolder != null && !dataHolder.isClosed()) {
            this.zzgmr.close();
        }
    }
}
