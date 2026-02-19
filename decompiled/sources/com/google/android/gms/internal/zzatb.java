package com.google.android.gms.internal;

import com.google.android.gms.common.util.zze;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

public final class zzatb {
    private final zze zzata;
    private final String zzdxe;
    private final long zzedo;
    private final int zzedp;
    private double zzedq;
    private long zzedr;
    private final Object zzeds;

    private zzatb(int i, long j, String str, zze zze) {
        this.zzeds = new Object();
        this.zzedp = 60;
        this.zzedq = (double) 60;
        this.zzedo = 2000;
        this.zzdxe = str;
        this.zzata = zze;
    }

    public zzatb(String str, zze zze) {
        this(60, 2000, str, zze);
    }

    public final boolean zzaas() {
        synchronized (this.zzeds) {
            long currentTimeMillis = this.zzata.currentTimeMillis();
            double d = this.zzedq;
            int i = this.zzedp;
            if (d < ((double) i)) {
                double d2 = ((double) (currentTimeMillis - this.zzedr)) / ((double) this.zzedo);
                if (d2 > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                    this.zzedq = Math.min((double) i, d + d2);
                }
            }
            this.zzedr = currentTimeMillis;
            double d3 = this.zzedq;
            if (d3 >= 1.0d) {
                this.zzedq = d3 - 1.0d;
                return true;
            }
            String str = this.zzdxe;
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 34);
            sb.append("Excessive ");
            sb.append(str);
            sb.append(" detected; call ignored.");
            zzatc.zzcz(sb.toString());
            return false;
        }
    }
}
