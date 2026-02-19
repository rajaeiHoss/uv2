package com.google.android.gms.internal;

import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.zzbq;
import java.util.Locale;

public final class zzbei {
    private static boolean zzfnn = false;
    private String mTag;
    private final boolean zzfno;
    private boolean zzfnp;
    private boolean zzfnq;
    private String zzfnr;

    public zzbei(String str) {
        this(str, false);
    }

    private zzbei(String str, boolean z) {
        zzbq.zzh(str, "The log tag cannot be null or empty.");
        this.mTag = str;
        this.zzfno = str.length() <= 23;
        this.zzfnp = false;
        this.zzfnq = false;
    }

    private final boolean zzbn() {
        if (!this.zzfnp) {
            return this.zzfno && Log.isLoggable(this.mTag, 3);
        }
        return true;
    }

    private String zzg(String str, Object... objArr) {
        if (objArr.length != 0) {
            str = String.format(Locale.ROOT, str, objArr);
        }
        if (TextUtils.isEmpty(this.zzfnr)) {
            return str;
        }
        String valueOf = String.valueOf(this.zzfnr);
        String valueOf2 = String.valueOf(str);
        return valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
    }

    public final void zza(Throwable th, String str, Object... objArr) {
        Log.e(this.mTag, zzg(str, objArr), th);
    }

    public final void zzb(String str, Object... objArr) {
        if (zzbn()) {
            Log.d(this.mTag, zzg(str, objArr));
        }
    }

    public final void zzb(Throwable th, String str, Object... objArr) {
        if (zzbn()) {
            Log.d(this.mTag, zzg(str, objArr), th);
        }
    }

    public final void zzbh(boolean z) {
        this.zzfnp = true;
    }

    public final void zzc(String str, Object... objArr) {
        Log.e(this.mTag, zzg(str, objArr));
    }

    public final void zzc(Throwable th, String str, Object... objArr) {
        Log.w(this.mTag, zzg(str, objArr), th);
    }

    public final void zze(String str, Object... objArr) {
        Log.i(this.mTag, zzg(str, objArr));
    }

    public final void zzf(String str, Object... objArr) {
        Log.w(this.mTag, zzg(str, objArr));
    }

    public final void zzga(String str) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            str2 = null;
        } else {
            str2 = String.format("[%s] ", new Object[]{str});
        }
        this.zzfnr = str2;
    }
}
