package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.drive.zzv;
import java.util.List;

public final class zzbri extends zzbgl {
    public static final Parcelable.Creator<zzbri> CREATOR = new zzbrj();
    private int zzccj;
    private List<zzv> zzgww;

    public zzbri(List<zzv> list, int i) {
        this.zzgww = list;
        this.zzccj = i;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 2, this.zzgww, false);
        zzbgo.zzc(parcel, 3, this.zzccj);
        zzbgo.zzai(parcel, zze);
    }
}
