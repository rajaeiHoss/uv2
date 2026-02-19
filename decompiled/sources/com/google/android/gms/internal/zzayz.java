package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzayz extends zzbgl {
    public static final Parcelable.Creator<zzayz> CREATOR = new zzaza();
    private final int zzerg;
    private final double zzerh;

    public zzayz(int i, double d) {
        this.zzerg = i;
        this.zzerh = d;
    }

    public final String toString() {
        String num = Integer.toString(this.zzerg);
        double d = this.zzerh;
        StringBuilder sb = new StringBuilder(String.valueOf(num).length() + 69);
        sb.append("PowerConnectionState = ");
        sb.append(num);
        sb.append(" Battery Percentage = ");
        sb.append(d);
        return sb.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 2, this.zzerg);
        zzbgo.zza(parcel, 3, this.zzerh);
        zzbgo.zzai(parcel, zze);
    }
}
