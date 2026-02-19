package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzazb extends zzbgl {
    public static final Parcelable.Creator<zzazb> CREATOR = new zzazc();
    private final int zzeri;

    public zzazb(int i) {
        this.zzeri = i;
    }

    public final String toString() {
        int i = this.zzeri;
        return i == 1 ? "ScreenState: SCREEN_OFF" : i == 2 ? "ScreenState: SCREEN_ON" : "ScreenState: UNKNOWN";
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 2, this.zzeri);
        zzbgo.zzai(parcel, zze);
    }
}
