package com.google.android.gms.nearby.messages.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.nearby.messages.Distance;
import java.util.Arrays;
import java.util.Locale;

public final class zze extends zzbgl implements Distance {
    public static final Parcelable.Creator<zze> CREATOR = new zzf();
    private int accuracy;
    private int zzehz;
    private double zzkcw;

    public zze(int i, double d) {
        this(1, 1, Double.NaN);
    }

    zze(int i, int i2, double d) {
        this.zzehz = i;
        this.accuracy = i2;
        this.zzkcw = d;
    }

    public final int compareTo(Distance distance) {
        if (!Double.isNaN(getMeters()) || !Double.isNaN(distance.getMeters())) {
            return Double.compare(getMeters(), distance.getMeters());
        }
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zze)) {
            return false;
        }
        zze zze = (zze) obj;
        return getAccuracy() == zze.getAccuracy() && compareTo((Distance) zze) == 0;
    }

    public final int getAccuracy() {
        return this.accuracy;
    }

    public final double getMeters() {
        return this.zzkcw;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(getAccuracy()), Double.valueOf(getMeters())});
    }

    public final String toString() {
        Locale locale = Locale.US;
        Object[] objArr = new Object[2];
        objArr[0] = Double.valueOf(this.zzkcw);
        objArr[1] = this.accuracy != 1 ? "UNKNOWN" : "LOW";
        return String.format(locale, "(%.1fm, %s)", objArr);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 1, this.zzehz);
        zzbgo.zzc(parcel, 2, this.accuracy);
        zzbgo.zza(parcel, 3, this.zzkcw);
        zzbgo.zzai(parcel, zze);
    }
}
