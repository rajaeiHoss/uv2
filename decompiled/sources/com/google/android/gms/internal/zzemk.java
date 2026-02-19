package com.google.android.gms.internal;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class zzemk implements zzemn {
    private final Set<String> zznnn;
    private final zzemo zznno;

    public zzemk(zzemo zzemo, List<String> list) {
        if (list != null) {
            this.zznnn = new HashSet(list);
        } else {
            this.zznnn = null;
        }
        this.zznno = zzemo;
    }

    /* access modifiers changed from: protected */
    public String zza(zzemo zzemo, String str, String str2, long j) {
        String date = new Date(j).toString();
        String valueOf = String.valueOf(zzemo);
        StringBuilder sb = new StringBuilder(String.valueOf(date).length() + 6 + String.valueOf(valueOf).length() + String.valueOf(str).length() + String.valueOf(str2).length());
        sb.append(date);
        sb.append(" [");
        sb.append(valueOf);
        sb.append("] ");
        sb.append(str);
        sb.append(": ");
        sb.append(str2);
        return sb.toString();
    }

    public final void zzb(zzemo zzemo, String str, String str2, long j) {
        if (zzemo.ordinal() >= this.zznno.ordinal() && (this.zznnn == null || zzemo.ordinal() > zzemo.DEBUG.ordinal() || this.zznnn.contains(str))) {
            String zza = zza(zzemo, str, str2, j);
            int i = zzeml.zznct[zzemo.ordinal()];
            if (i == 1) {
                zzbf(str, zza);
            } else if (i == 2) {
                zzbg(str, zza);
            } else if (i == 3) {
                zzbh(str, zza);
            } else if (i == 4) {
                zzbi(str, zza);
            } else {
                throw new RuntimeException("Should not reach here!");
            }
        }
    }

    /* access modifiers changed from: protected */
    public void zzbf(String str, String str2) {
        System.err.println(str2);
    }

    /* access modifiers changed from: protected */
    public void zzbg(String str, String str2) {
        System.out.println(str2);
    }

    /* access modifiers changed from: protected */
    public void zzbh(String str, String str2) {
        System.out.println(str2);
    }

    /* access modifiers changed from: protected */
    public void zzbi(String str, String str2) {
        System.out.println(str2);
    }

    public final zzemo zzbyh() {
        return this.zznno;
    }
}
