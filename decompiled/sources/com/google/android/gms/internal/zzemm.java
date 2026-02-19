package com.google.android.gms.internal;

import java.io.PrintWriter;
import java.io.StringWriter;

public final class zzemm {
    private final String prefix;
    private final zzemn zzmzw;
    private final String zznnp;

    public zzemm(zzemn zzemn, String str) {
        this(zzemn, str, (String) null);
    }

    public zzemm(zzemn zzemn, String str, String str2) {
        this.zzmzw = zzemn;
        this.zznnp = str;
        this.prefix = str2;
    }

    private final String zzj(String str, Object... objArr) {
        if (objArr.length > 0) {
            str = String.format(str, objArr);
        }
        String str2 = this.prefix;
        if (str2 == null) {
            return str;
        }
        StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 3 + String.valueOf(str).length());
        sb.append(str2);
        sb.append(" - ");
        sb.append(str);
        return sb.toString();
    }

    private static String zzk(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        zzdyq.zza(th, new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    public final void info(String str) {
        this.zzmzw.zzb(zzemo.INFO, this.zznnp, zzj(str, new Object[0]), System.currentTimeMillis());
    }

    public final void zzb(String str, Throwable th, Object... objArr) {
        if (zzcbu()) {
            String zzj = zzj(str, objArr);
            if (th != null) {
                String zzk = zzk(th);
                StringBuilder sb = new StringBuilder(String.valueOf(zzj).length() + 1 + String.valueOf(zzk).length());
                sb.append(zzj);
                sb.append("\n");
                sb.append(zzk);
                zzj = sb.toString();
            }
            this.zzmzw.zzb(zzemo.DEBUG, this.zznnp, zzj, System.currentTimeMillis());
        }
    }

    public final boolean zzcbu() {
        return this.zzmzw.zzbyh().ordinal() <= zzemo.DEBUG.ordinal();
    }

    public final void zze(String str, Throwable th) {
        String zzj = zzj(str, new Object[0]);
        String zzk = zzk(th);
        StringBuilder sb = new StringBuilder(String.valueOf(zzj).length() + 1 + String.valueOf(zzk).length());
        sb.append(zzj);
        sb.append("\n");
        sb.append(zzk);
        this.zzmzw.zzb(zzemo.ERROR, this.zznnp, sb.toString(), System.currentTimeMillis());
    }

    public final void zzf(String str, Throwable th) {
        this.zzmzw.zzb(zzemo.WARN, this.zznnp, zzj(str, new Object[0]), System.currentTimeMillis());
    }
}
