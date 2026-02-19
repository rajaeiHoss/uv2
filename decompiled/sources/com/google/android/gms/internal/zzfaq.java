package com.google.android.gms.internal;

import com.google.firebase.remoteconfig.FirebaseRemoteConfigInfo;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

public final class zzfaq implements FirebaseRemoteConfigInfo {
    private long zzosq;
    private int zzosr;
    private FirebaseRemoteConfigSettings zzoss;

    public final FirebaseRemoteConfigSettings getConfigSettings() {
        return this.zzoss;
    }

    public final long getFetchTimeMillis() {
        return this.zzosq;
    }

    public final int getLastFetchStatus() {
        return this.zzosr;
    }

    public final void setConfigSettings(FirebaseRemoteConfigSettings firebaseRemoteConfigSettings) {
        this.zzoss = firebaseRemoteConfigSettings;
    }

    public final void zzcn(long j) {
        this.zzosq = j;
    }

    public final void zziy(int i) {
        this.zzosr = i;
    }
}
