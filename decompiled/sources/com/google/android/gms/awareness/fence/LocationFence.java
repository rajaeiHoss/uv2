package com.google.android.gms.awareness.fence;

import com.google.android.gms.internal.zzbke;
import com.google.android.gms.internal.zzbku;

public final class LocationFence {
    private LocationFence() {
    }

    public static AwarenessFence entering(double d, double d2, double d3) {
        return zzbke.zza(zzbku.zza((int) (d * 1.0E7d), (int) (d2 * 1.0E7d), d3));
    }

    public static AwarenessFence exiting(double d, double d2, double d3) {
        return zzbke.zza(zzbku.zzb((int) (d * 1.0E7d), (int) (d2 * 1.0E7d), d3));
    }

    public static AwarenessFence in(double d, double d2, double d3, long j) {
        return zzbke.zza(zzbku.zza((int) (d * 1.0E7d), (int) (d2 * 1.0E7d), d3, j));
    }
}
