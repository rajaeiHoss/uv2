package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzayu extends zzbgl {
    public static final Parcelable.Creator<zzayu> CREATOR = new zzayv();
    private final int zzerd;
    private final int zzere;

    public zzayu(int i, int i2) {
        this.zzerd = i;
        this.zzere = i2;
    }

    public final String toString() {
        String num = Integer.toString(this.zzerd);
        String num2 = Integer.toString(this.zzere);
        StringBuilder sb = new StringBuilder(String.valueOf(num).length() + 41 + String.valueOf(num2).length());
        sb.append("ConnectionState = ");
        sb.append(num);
        sb.append(" NetworkMeteredState = ");
        sb.append(num2);
        return sb.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 2, this.zzerd);
        zzbgo.zzc(parcel, 3, this.zzere);
        zzbgo.zzai(parcel, zze);
    }
}
