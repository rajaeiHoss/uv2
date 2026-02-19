package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import java.util.List;

public final class zzaug extends zzbgl implements Result {
    public static final Parcelable.Creator<zzaug> CREATOR = new zzauh();
    private Status zzefs;
    private List<zzauo> zzeft;
    @Deprecated
    private String[] zzefu;

    public zzaug() {
    }

    zzaug(Status status, List<zzauo> list, String[] strArr) {
        this.zzefs = status;
        this.zzeft = list;
        this.zzefu = strArr;
    }

    public final Status getStatus() {
        return this.zzefs;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, (Parcelable) this.zzefs, i, false);
        zzbgo.zzc(parcel, 2, this.zzeft, false);
        zzbgo.zza(parcel, 3, this.zzefu, false);
        zzbgo.zzai(parcel, zze);
    }
}
