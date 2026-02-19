package com.google.android.gms.internal;

import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Locale;

final class zzcju extends zzcli {
    static final Pair<String, Long> zzjlk = new Pair<>("", 0L);
    private SharedPreferences zzjll;
    public zzcjy zzjlm;
    public final zzcjx zzjln = new zzcjx(this, "last_upload", 0);
    public final zzcjx zzjlo = new zzcjx(this, "last_upload_attempt", 0);
    public final zzcjx zzjlp = new zzcjx(this, "backoff", 0);
    public final zzcjx zzjlq = new zzcjx(this, "last_delete_stale", 0);
    public final zzcjx zzjlr = new zzcjx(this, "midnight_offset", 0);
    public final zzcjx zzjls = new zzcjx(this, "first_open_time", 0);
    public final zzcjz zzjlt = new zzcjz(this, "app_instance_id", (String) null);
    private String zzjlu;
    private boolean zzjlv;
    private long zzjlw;
    private String zzjlx;
    private long zzjly;
    private final Object zzjlz = new Object();
    public final zzcjx zzjma = new zzcjx(this, "time_before_start", NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS);
    public final zzcjx zzjmb = new zzcjx(this, "session_timeout", 1800000);
    public final zzcjw zzjmc = new zzcjw(this, "start_new_session", true);
    public final zzcjx zzjmd = new zzcjx(this, "last_pause_time", 0);
    public final zzcjx zzjme = new zzcjx(this, "time_active", 0);
    public boolean zzjmf;

    zzcju(zzckj zzckj) {
        super(zzckj);
    }

    /* access modifiers changed from: private */
    public final SharedPreferences zzbbd() {
        zzwj();
        zzyk();
        return this.zzjll;
    }

    /* access modifiers changed from: package-private */
    public final void setMeasurementEnabled(boolean z) {
        zzwj();
        zzayp().zzbba().zzj("Setting measurementEnabled", Boolean.valueOf(z));
        SharedPreferences.Editor edit = zzbbd().edit();
        edit.putBoolean("measurement_enabled", z);
        edit.apply();
    }

    /* access modifiers changed from: protected */
    public final boolean zzazq() {
        return true;
    }

    /* access modifiers changed from: protected */
    public final void zzbap() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("com.google.android.gms.measurement.prefs", 0);
        this.zzjll = sharedPreferences;
        boolean z = sharedPreferences.getBoolean("has_been_opened", false);
        this.zzjmf = z;
        if (!z) {
            SharedPreferences.Editor edit = this.zzjll.edit();
            edit.putBoolean("has_been_opened", true);
            edit.apply();
        }
        this.zzjlm = new zzcjy(this, "health_monitor", Math.max(0, zzciz.zzjip.get().longValue()));
    }

    /* access modifiers changed from: package-private */
    public final String zzbbe() {
        zzwj();
        return zzbbd().getString("gmp_app_id", (String) null);
    }

    /* access modifiers changed from: package-private */
    public final String zzbbf() {
        synchronized (this.zzjlz) {
            if (Math.abs(zzxx().elapsedRealtime() - this.zzjly) >= 1000) {
                return null;
            }
            String str = this.zzjlx;
            return str;
        }
    }

    /* access modifiers changed from: package-private */
    public final Boolean zzbbg() {
        zzwj();
        if (!zzbbd().contains("use_service")) {
            return null;
        }
        return Boolean.valueOf(zzbbd().getBoolean("use_service", false));
    }

    /* access modifiers changed from: package-private */
    public final void zzbbh() {
        zzwj();
        zzayp().zzbba().log("Clearing collection preferences.");
        boolean contains = zzbbd().contains("measurement_enabled");
        boolean z = true;
        if (contains) {
            z = zzbs(true);
        }
        SharedPreferences.Editor edit = zzbbd().edit();
        edit.clear();
        edit.apply();
        if (contains) {
            setMeasurementEnabled(z);
        }
    }

    /* access modifiers changed from: protected */
    public final String zzbbi() {
        zzwj();
        String string = zzbbd().getString("previous_os_version", (String) null);
        zzayf().zzyk();
        String str = Build.VERSION.RELEASE;
        if (!TextUtils.isEmpty(str) && !str.equals(string)) {
            SharedPreferences.Editor edit = zzbbd().edit();
            edit.putString("previous_os_version", str);
            edit.apply();
        }
        return string;
    }

    /* access modifiers changed from: package-private */
    public final void zzbr(boolean z) {
        zzwj();
        zzayp().zzbba().zzj("Setting useService", Boolean.valueOf(z));
        SharedPreferences.Editor edit = zzbbd().edit();
        edit.putBoolean("use_service", z);
        edit.apply();
    }

    /* access modifiers changed from: package-private */
    public final boolean zzbs(boolean z) {
        zzwj();
        return zzbbd().getBoolean("measurement_enabled", z);
    }

    /* access modifiers changed from: package-private */
    public final Pair<String, Boolean> zzju(String str) {
        zzwj();
        long elapsedRealtime = zzxx().elapsedRealtime();
        if (this.zzjlu != null && elapsedRealtime < this.zzjlw) {
            return new Pair<>(this.zzjlu, Boolean.valueOf(this.zzjlv));
        }
        this.zzjlw = elapsedRealtime + zzayr().zza(str, zzciz.zzjio);
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(true);
        try {
            AdvertisingIdClient.Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(getContext());
            if (advertisingIdInfo != null) {
                this.zzjlu = advertisingIdInfo.getId();
                this.zzjlv = advertisingIdInfo.isLimitAdTrackingEnabled();
            }
            if (this.zzjlu == null) {
                this.zzjlu = "";
            }
        } catch (Throwable th) {
            zzayp().zzbaz().zzj("Unable to get advertising id", th);
            this.zzjlu = "";
        }
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(false);
        return new Pair<>(this.zzjlu, Boolean.valueOf(this.zzjlv));
    }

    /* access modifiers changed from: package-private */
    public final String zzjv(String str) {
        zzwj();
        String str2 = (String) zzju(str).first;
        MessageDigest zzeq = zzcno.zzeq("MD5");
        if (zzeq == null) {
            return null;
        }
        return String.format(Locale.US, "%032X", new Object[]{new BigInteger(1, zzeq.digest(str2.getBytes()))});
    }

    /* access modifiers changed from: package-private */
    public final void zzjw(String str) {
        zzwj();
        SharedPreferences.Editor edit = zzbbd().edit();
        edit.putString("gmp_app_id", str);
        edit.apply();
    }

    /* access modifiers changed from: package-private */
    public final void zzjx(String str) {
        synchronized (this.zzjlz) {
            this.zzjlx = str;
            this.zzjly = zzxx().elapsedRealtime();
        }
    }
}
