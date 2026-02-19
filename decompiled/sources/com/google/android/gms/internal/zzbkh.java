package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

public final class zzbkh extends zzbgl {
    public static final Parcelable.Creator<zzbkh> CREATOR = new zzbkv();
    private int type;
    private List<String> zzgnl;

    zzbkh(int i, List<String> list) {
        this.type = i;
        this.zzgnl = list;
    }

    public static zzbkh zza(int i, List<String> list) {
        return new zzbkh(i, list);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 2, this.type);
        zzbgo.zzb(parcel, 3, this.zzgnl, false);
        zzbgo.zzai(parcel, zze);
    }
}
