package com.google.android.gms.tagmanager;

import com.google.android.gms.common.util.zze;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

final class zzdh implements zzek {
    private final zze zzata;
    private final String zzdxe;
    private final long zzedo = 900000;
    private final int zzedp = 5;
    private double zzedq = ((double) Math.min(1, 5));
    private long zzedr;
    private final Object zzeds = new Object();
    private final long zzkqu = 5000;

    public zzdh(int i, int i2, long j, long j2, String str, zze zze) {
        this.zzdxe = str;
        this.zzata = zze;
    }

    public final boolean zzaas() {
        synchronized (this.zzeds) {
            long currentTimeMillis = this.zzata.currentTimeMillis();
            long j = this.zzedr;
            if (currentTimeMillis - j < this.zzkqu) {
                String str = this.zzdxe;
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 34);
                sb.append("Excessive ");
                sb.append(str);
                sb.append(" detected; call ignored.");
                zzdj.zzcz(sb.toString());
                return false;
            }
            double d = this.zzedq;
            int i = this.zzedp;
            if (d < ((double) i)) {
                double d2 = ((double) (currentTimeMillis - j)) / ((double) this.zzedo);
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
            String str2 = this.zzdxe;
            StringBuilder sb2 = new StringBuilder(String.valueOf(str2).length() + 34);
            sb2.append("Excessive ");
            sb2.append(str2);
            sb2.append(" detected; call ignored.");
            zzdj.zzcz(sb2.toString());
            return false;
        }
    }
}
