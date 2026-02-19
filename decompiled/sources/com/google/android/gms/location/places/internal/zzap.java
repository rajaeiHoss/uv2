package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Collections;
import java.util.List;

public final class zzap extends zzbgl {
    public static final Parcelable.Creator<zzap> CREATOR = new zzf();
    private int zziyo;
    private int zziyp;
    private int zziyq;
    private int zziyr;
    private int zziys;
    private int zziyt;
    private List<zzao> zziyu;

    zzap(int i, int i2, int i3, int i4, int i5, int i6, List<zzao> list) {
        this.zziyo = i;
        this.zziyp = i2;
        this.zziyq = i3;
        this.zziyr = i4;
        this.zziys = i5;
        this.zziyt = i6;
        this.zziyu = Collections.unmodifiableList(list);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 1, this.zziyo);
        zzbgo.zzc(parcel, 2, this.zziyp);
        zzbgo.zzc(parcel, 3, this.zziyq);
        zzbgo.zzc(parcel, 4, this.zziyr);
        zzbgo.zzc(parcel, 5, this.zziys);
        zzbgo.zzc(parcel, 6, this.zziyt);
        zzbgo.zzc(parcel, 7, this.zziyu, false);
        zzbgo.zzai(parcel, zze);
    }
}
