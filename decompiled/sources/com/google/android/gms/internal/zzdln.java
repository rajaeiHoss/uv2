package com.google.android.gms.internal;

import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;

public final class zzdln extends zzbgl {
    public static final Parcelable.Creator<zzdln> CREATOR = new zzdlo();
    public final Rect zzliq;

    public zzdln() {
        this.zzliq = new Rect();
    }

    public zzdln(Rect rect) {
        this.zzliq = rect;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, (Parcelable) this.zzliq, i, false);
        zzbgo.zzai(parcel, zze);
    }
}
