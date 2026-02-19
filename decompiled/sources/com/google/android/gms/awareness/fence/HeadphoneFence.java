package com.google.android.gms.awareness.fence;

import com.google.android.gms.internal.zzbju;
import com.google.android.gms.internal.zzbke;

public final class HeadphoneFence {
    private HeadphoneFence() {
    }

    public static AwarenessFence during(int i) {
        return zzbke.zza(zzbju.zzco(i));
    }

    public static AwarenessFence pluggingIn() {
        return zzbke.zza(zzbju.zzaov());
    }

    public static AwarenessFence unplugging() {
        return zzbke.zza(zzbju.zzaow());
    }
}
