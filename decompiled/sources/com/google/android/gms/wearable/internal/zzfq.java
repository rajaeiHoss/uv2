package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;

public final class zzfq extends zzbgl {
    public static final Parcelable.Creator<zzfq> CREATOR = new zzfr();
    public final int statusCode;
    public final zzay zzlth;

    public zzfq(int i, zzay zzay) {
        this.statusCode = i;
        this.zzlth = zzay;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 2, this.statusCode);
        zzbgo.zza(parcel, 3, (Parcelable) this.zzlth, i, false);
        zzbgo.zzai(parcel, zze);
    }
}
