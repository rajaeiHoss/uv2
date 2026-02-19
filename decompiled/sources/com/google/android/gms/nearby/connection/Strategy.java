package com.google.android.gms.nearby.connection;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;
import java.util.Locale;

public final class Strategy extends zzbgl {
    public static final Parcelable.Creator<Strategy> CREATOR = new zzj();
    public static final Strategy P2P_CLUSTER = new Strategy(1, 3);
    public static final Strategy P2P_POINT_TO_POINT = new Strategy(1, 1);
    public static final Strategy P2P_STAR = new Strategy(1, 2);
    private final int zzjwv;
    private final int zzjww;

    Strategy(int i, int i2) {
        this.zzjwv = i;
        this.zzjww = i2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Strategy)) {
            return false;
        }
        Strategy strategy = (Strategy) obj;
        return this.zzjwv == strategy.zzjwv && this.zzjww == strategy.zzjww;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.zzjwv), Integer.valueOf(this.zzjww)});
    }

    public final String toString() {
        Locale locale = Locale.US;
        Object[] objArr = new Object[3];
        objArr[0] = P2P_CLUSTER.equals(this) ? "P2P_CLUSTER" : P2P_STAR.equals(this) ? "P2P_STAR" : P2P_POINT_TO_POINT.equals(this) ? "P2P_POINT_TO_POINT" : "UNKNOWN";
        objArr[1] = Integer.valueOf(this.zzjwv);
        objArr[2] = Integer.valueOf(this.zzjww);
        return String.format(locale, "Strategy(%s){connectionType=%d, topology=%d}", objArr);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 3, this.zzjwv);
        zzbgo.zzc(parcel, 4, this.zzjww);
        zzbgo.zzai(parcel, zze);
    }
}
