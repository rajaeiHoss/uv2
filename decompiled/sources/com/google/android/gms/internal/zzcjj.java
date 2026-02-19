package com.google.android.gms.internal;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.measurement.AppMeasurement;

public final class zzcjj extends zzcli {
    /* access modifiers changed from: private */
    public long zzjft = -1;
    /* access modifiers changed from: private */
    public char zzjkg = 0;
    private String zzjkh;
    private final zzcjl zzjki = new zzcjl(this, 6, false, false);
    private final zzcjl zzjkj = new zzcjl(this, 6, true, false);
    private final zzcjl zzjkk = new zzcjl(this, 6, false, true);
    private final zzcjl zzjkl = new zzcjl(this, 5, false, false);
    private final zzcjl zzjkm = new zzcjl(this, 5, true, false);
    private final zzcjl zzjkn = new zzcjl(this, 5, false, true);
    private final zzcjl zzjko = new zzcjl(this, 4, false, false);
    private final zzcjl zzjkp = new zzcjl(this, 3, false, false);
    private final zzcjl zzjkq = new zzcjl(this, 2, false, false);

    zzcjj(zzckj zzckj) {
        super(zzckj);
    }

    static String zza(boolean z, String str, Object obj, Object obj2, Object obj3) {
        String str2 = "";
        if (str == null) {
            str = str2;
        }
        String zzb = zzb(z, obj);
        String zzb2 = zzb(z, obj2);
        String zzb3 = zzb(z, obj3);
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(str)) {
            sb.append(str);
            str2 = ": ";
        }
        String str3 = ", ";
        if (!TextUtils.isEmpty(zzb)) {
            sb.append(str2);
            sb.append(zzb);
            str2 = str3;
        }
        if (!TextUtils.isEmpty(zzb2)) {
            sb.append(str2);
            sb.append(zzb2);
        } else {
            str3 = str2;
        }
        if (!TextUtils.isEmpty(zzb3)) {
            sb.append(str3);
            sb.append(zzb3);
        }
        return sb.toString();
    }

    private static String zzb(boolean z, Object obj) {
        String className;
        String str = "";
        if (obj == null) {
            return str;
        }
        if (obj instanceof Integer) {
            obj = Long.valueOf((long) ((Integer) obj).intValue());
        }
        int i = 0;
        if (obj instanceof Long) {
            if (!z) {
                return String.valueOf(obj);
            }
            Long l = (Long) obj;
            if (Math.abs(l.longValue()) < 100) {
                return String.valueOf(obj);
            }
            if (String.valueOf(obj).charAt(0) == '-') {
                str = "-";
            }
            String valueOf = String.valueOf(Math.abs(l.longValue()));
            long round = Math.round(Math.pow(10.0d, (double) (valueOf.length() - 1)));
            long round2 = Math.round(Math.pow(10.0d, (double) valueOf.length()) - 1.0d);
            StringBuilder sb = new StringBuilder(str.length() + 43 + str.length());
            sb.append(str);
            sb.append(round);
            sb.append("...");
            sb.append(str);
            sb.append(round2);
            return sb.toString();
        } else if (obj instanceof Boolean) {
            return String.valueOf(obj);
        } else {
            if (!(obj instanceof Throwable)) {
                return obj instanceof zzcjm ? ((zzcjm) obj).zzgim : z ? "-" : String.valueOf(obj);
            }
            Throwable th = (Throwable) obj;
            StringBuilder sb2 = new StringBuilder(z ? th.getClass().getName() : th.toString());
            String zzjt = zzjt(AppMeasurement.class.getCanonicalName());
            String zzjt2 = zzjt(zzckj.class.getCanonicalName());
            StackTraceElement[] stackTrace = th.getStackTrace();
            int length = stackTrace.length;
            while (true) {
                if (i >= length) {
                    break;
                }
                StackTraceElement stackTraceElement = stackTrace[i];
                if (!stackTraceElement.isNativeMethod() && (className = stackTraceElement.getClassName()) != null) {
                    String zzjt3 = zzjt(className);
                    if (zzjt3.equals(zzjt) || zzjt3.equals(zzjt2)) {
                        sb2.append(": ");
                        sb2.append(stackTraceElement);
                    }
                }
                i++;
            }
            return sb2.toString();
        }
    }

    private final String zzbbb() {
        String str;
        synchronized (this) {
            if (this.zzjkh == null) {
                this.zzjkh = zzciz.zzjin.get();
            }
            str = this.zzjkh;
        }
        return str;
    }

    protected static Object zzjs(String str) {
        if (str == null) {
            return null;
        }
        return new zzcjm(str);
    }

    private static String zzjt(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int lastIndexOf = str.lastIndexOf(46);
        return lastIndexOf == -1 ? str : str.substring(0, lastIndexOf);
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    /* access modifiers changed from: protected */
    public final void zza(int i, boolean z, boolean z2, String str, Object obj, Object obj2, Object obj3) {
        String str2;
        if (!z && zzae(i)) {
            zzm(i, zza(false, str, obj, obj2, obj3));
        }
        if (!z2 && i >= 5) {
            zzbq.checkNotNull(str);
            zzcke zzbbp = this.zzjev.zzbbp();
            if (zzbbp == null) {
                str2 = "Scheduler not set. Not logging error/warn";
            } else if (!zzbbp.isInitialized()) {
                str2 = "Scheduler not initialized. Not logging error/warn";
            } else {
                if (i < 0) {
                    i = 0;
                }
                zzbbp.zzh(new zzcjk(this, i >= 9 ? 8 : i, str, obj, obj2, obj3));
                return;
            }
            zzm(6, str2);
        }
    }

    /* access modifiers changed from: protected */
    public final boolean zzae(int i) {
        return Log.isLoggable(zzbbb(), i);
    }

    public final /* bridge */ /* synthetic */ void zzaxz() {
        super.zzaxz();
    }

    public final /* bridge */ /* synthetic */ void zzaya() {
        super.zzaya();
    }

    public final /* bridge */ /* synthetic */ zzcia zzayb() {
        return super.zzayb();
    }

    public final /* bridge */ /* synthetic */ zzcih zzayc() {
        return super.zzayc();
    }

    public final /* bridge */ /* synthetic */ zzclk zzayd() {
        return super.zzayd();
    }

    public final /* bridge */ /* synthetic */ zzcje zzaye() {
        return super.zzaye();
    }

    public final /* bridge */ /* synthetic */ zzcir zzayf() {
        return super.zzayf();
    }

    public final /* bridge */ /* synthetic */ zzcme zzayg() {
        return super.zzayg();
    }

    public final /* bridge */ /* synthetic */ zzcma zzayh() {
        return super.zzayh();
    }

    public final /* bridge */ /* synthetic */ zzcjf zzayi() {
        return super.zzayi();
    }

    public final /* bridge */ /* synthetic */ zzcil zzayj() {
        return super.zzayj();
    }

    public final /* bridge */ /* synthetic */ zzcjh zzayk() {
        return super.zzayk();
    }

    public final /* bridge */ /* synthetic */ zzcno zzayl() {
        return super.zzayl();
    }

    public final /* bridge */ /* synthetic */ zzckd zzaym() {
        return super.zzaym();
    }

    public final /* bridge */ /* synthetic */ zzcnd zzayn() {
        return super.zzayn();
    }

    public final /* bridge */ /* synthetic */ zzcke zzayo() {
        return super.zzayo();
    }

    public final /* bridge */ /* synthetic */ zzcjj zzayp() {
        return super.zzayp();
    }

    public final /* bridge */ /* synthetic */ zzcju zzayq() {
        return super.zzayq();
    }

    public final /* bridge */ /* synthetic */ zzcik zzayr() {
        return super.zzayr();
    }

    /* access modifiers changed from: protected */
    public final boolean zzazq() {
        return false;
    }

    public final zzcjl zzbau() {
        return this.zzjki;
    }

    public final zzcjl zzbav() {
        return this.zzjkj;
    }

    public final zzcjl zzbaw() {
        return this.zzjkl;
    }

    public final zzcjl zzbax() {
        return this.zzjkn;
    }

    public final zzcjl zzbay() {
        return this.zzjko;
    }

    public final zzcjl zzbaz() {
        return this.zzjkp;
    }

    public final zzcjl zzbba() {
        return this.zzjkq;
    }

    public final String zzbbc() {
        Pair<String, Long> zzabh = zzayq().zzjlm.zzabh();
        if (zzabh == null || zzabh == zzcju.zzjlk) {
            return null;
        }
        String valueOf = String.valueOf(zzabh.second);
        String str = (String) zzabh.first;
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 1 + String.valueOf(str).length());
        sb.append(valueOf);
        sb.append(":");
        sb.append(str);
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public final void zzm(int i, String str) {
        Log.println(i, zzbbb(), str);
    }

    public final /* bridge */ /* synthetic */ void zzwj() {
        super.zzwj();
    }

    public final /* bridge */ /* synthetic */ zze zzxx() {
        return super.zzxx();
    }
}
