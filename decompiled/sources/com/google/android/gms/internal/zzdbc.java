package com.google.android.gms.internal;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

final class zzdbc implements zzdau {
    private final long zzedo;
    private final int zzedp;
    private double zzedq;
    private final Object zzeds;
    private long zzkth;

    public zzdbc() {
        this(60, 2000);
    }

    private zzdbc(int i, long j) {
        this.zzeds = new Object();
        this.zzedp = 60;
        this.zzedq = (double) 60;
        this.zzedo = 2000;
    }

    public final boolean zzaas() {
        synchronized (this.zzeds) {
            long currentTimeMillis = System.currentTimeMillis();
            double d = this.zzedq;
            int i = this.zzedp;
            if (d < ((double) i)) {
                double d2 = ((double) (currentTimeMillis - this.zzkth)) / ((double) this.zzedo);
                if (d2 > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                    this.zzedq = Math.min((double) i, d + d2);
                }
            }
            this.zzkth = currentTimeMillis;
            double d3 = this.zzedq;
            if (d3 >= 1.0d) {
                this.zzedq = d3 - 1.0d;
                return true;
            }
            zzdal.zzcz("No more tokens available.");
            return false;
        }
    }
}
