package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.awareness.fence.FenceState;
import java.util.ArrayList;

public final class zzbkj extends FenceState {
    public static final Parcelable.Creator<zzbkj> CREATOR = new zzbkk();
    private int zzgnm;
    private long zzgnn;
    private String zzgno;
    private int zzgnp;
    private ArrayList<zzbjp> zzgnq;

    public zzbkj(int i, long j, String str, int i2) {
        this(i, 0, str, 0, (ArrayList<zzbjp>) null);
    }

    public zzbkj(int i, long j, String str, int i2, ArrayList<zzbjp> arrayList) {
        this.zzgnm = i;
        this.zzgnn = j;
        this.zzgno = str;
        this.zzgnp = i2;
        this.zzgnq = arrayList;
    }

    public final int getCurrentState() {
        return this.zzgnm;
    }

    public final String getFenceKey() {
        return this.zzgno;
    }

    public final long getLastFenceUpdateTimeMillis() {
        return this.zzgnn;
    }

    public final int getPreviousState() {
        return this.zzgnp;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 2, this.zzgnm);
        zzbgo.zza(parcel, 3, this.zzgnn);
        zzbgo.zza(parcel, 4, this.zzgno, false);
        zzbgo.zzc(parcel, 5, this.zzgnp);
        zzbgo.zzc(parcel, 6, this.zzgnq, false);
        zzbgo.zzai(parcel, zze);
    }
}
