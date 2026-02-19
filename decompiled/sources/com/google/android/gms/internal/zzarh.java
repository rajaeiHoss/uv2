package com.google.android.gms.internal;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.zzk;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zze;

public class zzarh {
    private final zzark zzdyp;

    protected zzarh(zzark zzark) {
        zzbq.checkNotNull(zzark);
        this.zzdyp = zzark;
    }

    private final void zza(int i, String str, Object obj, Object obj2, Object obj3) {
        zzark zzark = this.zzdyp;
        zzatd zzym = zzark != null ? zzark.zzym() : null;
        String str2 = zzast.zzebn.get();
        if (zzym != null) {
            if (Log.isLoggable(str2, i)) {
                Log.println(i, str2, zzatd.zzc(str, obj, obj2, obj3));
            }
            if (i >= 5) {
                zzym.zzb(i, str, obj, obj2, obj3);
            }
        } else if (Log.isLoggable(str2, i)) {
            Log.println(i, str2, zzc(str, obj, obj2, obj3));
        }
    }

    protected static String zzc(String str, Object obj, Object obj2, Object obj3) {
        String str2 = "";
        if (str == null) {
            str = str2;
        }
        String zzm = zzm(obj);
        String zzm2 = zzm(obj2);
        String zzm3 = zzm(obj3);
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(str)) {
            sb.append(str);
            str2 = ": ";
        }
        String str3 = ", ";
        if (!TextUtils.isEmpty(zzm)) {
            sb.append(str2);
            sb.append(zzm);
            str2 = str3;
        }
        if (!TextUtils.isEmpty(zzm2)) {
            sb.append(str2);
            sb.append(zzm2);
        } else {
            str3 = str2;
        }
        if (!TextUtils.isEmpty(zzm3)) {
            sb.append(str3);
            sb.append(zzm3);
        }
        return sb.toString();
    }

    private static String zzm(Object obj) {
        return obj == null ? "" : obj instanceof String ? (String) obj : obj instanceof Boolean ? obj == Boolean.TRUE ? "true" : "false" : obj instanceof Throwable ? ((Throwable) obj).toString() : obj.toString();
    }

    public static boolean zzqk() {
        return Log.isLoggable(zzast.zzebn.get(), 2);
    }

    /* access modifiers changed from: protected */
    public final Context getContext() {
        return this.zzdyp.getContext();
    }

    public final void zza(String str, Object obj) {
        zza(2, str, obj, (Object) null, (Object) null);
    }

    public final void zza(String str, Object obj, Object obj2) {
        zza(2, str, obj, obj2, (Object) null);
    }

    public final void zza(String str, Object obj, Object obj2, Object obj3) {
        zza(3, str, obj, obj2, obj3);
    }

    public final void zzb(String str, Object obj) {
        zza(3, str, obj, (Object) null, (Object) null);
    }

    public final void zzb(String str, Object obj, Object obj2) {
        zza(3, str, obj, obj2, (Object) null);
    }

    public final void zzb(String str, Object obj, Object obj2, Object obj3) {
        zza(5, str, obj, obj2, obj3);
    }

    public final void zzc(String str, Object obj) {
        zza(4, str, obj, (Object) null, (Object) null);
    }

    public final void zzc(String str, Object obj, Object obj2) {
        zza(5, str, obj, obj2, (Object) null);
    }

    public final void zzd(String str, Object obj) {
        zza(5, str, obj, (Object) null, (Object) null);
    }

    public final void zzd(String str, Object obj, Object obj2) {
        zza(6, str, obj, obj2, (Object) null);
    }

    public final void zze(String str, Object obj) {
        zza(6, str, obj, (Object) null, (Object) null);
    }

    public final void zzea(String str) {
        zza(2, str, (Object) null, (Object) null, (Object) null);
    }

    public final void zzeb(String str) {
        zza(3, str, (Object) null, (Object) null, (Object) null);
    }

    public final void zzec(String str) {
        zza(4, str, (Object) null, (Object) null, (Object) null);
    }

    public final void zzed(String str) {
        zza(5, str, (Object) null, (Object) null, (Object) null);
    }

    public final void zzee(String str) {
        zza(6, str, (Object) null, (Object) null, (Object) null);
    }

    public final zzark zzxw() {
        return this.zzdyp;
    }

    /* access modifiers changed from: protected */
    public final zze zzxx() {
        return this.zzdyp.zzxx();
    }

    /* access modifiers changed from: protected */
    public final zzatd zzxy() {
        return this.zzdyp.zzxy();
    }

    /* access modifiers changed from: protected */
    public final zzasl zzxz() {
        return this.zzdyp.zzxz();
    }

    /* access modifiers changed from: protected */
    public final zzk zzya() {
        return this.zzdyp.zzya();
    }

    public final GoogleAnalytics zzyb() {
        return this.zzdyp.zzyn();
    }

    /* access modifiers changed from: protected */
    public final zzaqz zzyc() {
        return this.zzdyp.zzyc();
    }

    /* access modifiers changed from: protected */
    public final zzasq zzyd() {
        return this.zzdyp.zzyd();
    }

    /* access modifiers changed from: protected */
    public final zzatu zzye() {
        return this.zzdyp.zzye();
    }

    /* access modifiers changed from: protected */
    public final zzath zzyf() {
        return this.zzdyp.zzyf();
    }

    /* access modifiers changed from: protected */
    public final zzasc zzyg() {
        return this.zzdyp.zzyq();
    }

    /* access modifiers changed from: protected */
    public final zzaqy zzyh() {
        return this.zzdyp.zzyp();
    }

    /* access modifiers changed from: protected */
    public final zzarv zzyi() {
        return this.zzdyp.zzyi();
    }

    /* access modifiers changed from: protected */
    public final zzasp zzyj() {
        return this.zzdyp.zzyj();
    }
}
