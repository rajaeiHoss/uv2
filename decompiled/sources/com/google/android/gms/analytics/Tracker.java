package com.google.android.gms.analytics;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzari;
import com.google.android.gms.internal.zzark;
import com.google.android.gms.internal.zzatb;
import com.google.android.gms.internal.zzats;
import com.google.android.gms.internal.zzatt;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import org.jivesoftware.smackx.GroupChatInvitation;

public class Tracker extends zzari {
    private final Map<String, String> zzbwu;
    private boolean zzdvv;
    private final Map<String, String> zzdvw = new HashMap();
    /* access modifiers changed from: private */
    public final zzatb zzdvx;
    /* access modifiers changed from: private */
    public final zza zzdvy;
    private ExceptionReporter zzdvz;
    /* access modifiers changed from: private */
    public zzats zzdwa;

    class zza extends zzari implements GoogleAnalytics.zza {
        private boolean zzdwj;
        private int zzdwk;
        private long zzdwl = -1;
        private boolean zzdwm;
        private long zzdwn;

        protected zza(zzark zzark) {
            super(zzark);
        }

        private final void zzwm() {
            if (this.zzdwl >= 0 || this.zzdwj) {
                zzyb().zza(Tracker.this.zzdvy);
            } else {
                zzyb().zzb(Tracker.this.zzdvy);
            }
        }

        public final void enableAutoActivityTracking(boolean z) {
            this.zzdwj = z;
            zzwm();
        }

        public final void setSessionTimeout(long j) {
            this.zzdwl = j;
            zzwm();
        }

        public final void zzm(Activity activity) {
            String str;
            if (this.zzdwk == 0) {
                if (zzxx().elapsedRealtime() >= this.zzdwn + Math.max(1000, this.zzdwl)) {
                    this.zzdwm = true;
                }
            }
            this.zzdwk++;
            if (this.zzdwj) {
                Intent intent = activity.getIntent();
                if (intent != null) {
                    Tracker.this.setCampaignParamsOnNextHit(intent.getData());
                }
                HashMap hashMap = new HashMap();
                hashMap.put("&t", "screenview");
                Tracker tracker = Tracker.this;
                if (tracker.zzdwa != null) {
                    zzats zzk = Tracker.this.zzdwa;
                    str = activity.getClass().getCanonicalName();
                    String str2 = zzk.zzeex.get(str);
                    if (str2 != null) {
                        str = str2;
                    }
                } else {
                    str = activity.getClass().getCanonicalName();
                }
                tracker.set("&cd", str);
                if (TextUtils.isEmpty((CharSequence) hashMap.get("&dr"))) {
                    zzbq.checkNotNull(activity);
                    Intent intent2 = activity.getIntent();
                    String str3 = null;
                    if (intent2 != null) {
                        String stringExtra = intent2.getStringExtra("android.intent.extra.REFERRER_NAME");
                        if (!TextUtils.isEmpty(stringExtra)) {
                            str3 = stringExtra;
                        }
                    }
                    if (!TextUtils.isEmpty(str3)) {
                        hashMap.put("&dr", str3);
                    }
                }
                Tracker.this.send(hashMap);
            }
        }

        public final void zzn(Activity activity) {
            int i = this.zzdwk - 1;
            this.zzdwk = i;
            int max = Math.max(0, i);
            this.zzdwk = max;
            if (max == 0) {
                this.zzdwn = zzxx().elapsedRealtime();
            }
        }

        /* access modifiers changed from: protected */
        public final void zzwk() {
        }

        public final synchronized boolean zzwl() {
            boolean z;
            z = this.zzdwm;
            this.zzdwm = false;
            return z;
        }
    }

    Tracker(zzark zzark, String str, zzatb zzatb) {
        super(zzark);
        HashMap hashMap = new HashMap();
        this.zzbwu = hashMap;
        if (str != null) {
            hashMap.put("&tid", str);
        }
        hashMap.put("useSecure", "1");
        hashMap.put("&a", Integer.toString(new Random().nextInt(Integer.MAX_VALUE) + 1));
        this.zzdvx = new zzatb("tracking", zzxx());
        this.zzdvy = new zza(zzark);
    }

    private static String zza(Map.Entry<String, String> entry) {
        String key = entry.getKey();
        if (!(key.startsWith("&") && key.length() >= 2)) {
            return null;
        }
        return entry.getKey().substring(1);
    }

    private static void zzb(Map<String, String> map, Map<String, String> map2) {
        zzbq.checkNotNull(map2);
        if (map != null) {
            for (Map.Entry next : map.entrySet()) {
                String zza2 = zza((Map.Entry<String, String>) next);
                if (zza2 != null) {
                    map2.put(zza2, (String) next.getValue());
                }
            }
        }
    }

    private static void zzc(Map<String, String> map, Map<String, String> map2) {
        zzbq.checkNotNull(map2);
        if (map != null) {
            for (Map.Entry next : map.entrySet()) {
                String zza2 = zza((Map.Entry<String, String>) next);
                if (zza2 != null && !map2.containsKey(zza2)) {
                    map2.put(zza2, (String) next.getValue());
                }
            }
        }
    }

    public void enableAdvertisingIdCollection(boolean z) {
        this.zzdvv = z;
    }

    public void enableAutoActivityTracking(boolean z) {
        this.zzdvy.enableAutoActivityTracking(z);
    }

    public void enableExceptionReporting(boolean z) {
        String str;
        synchronized (this) {
            ExceptionReporter exceptionReporter = this.zzdvz;
            if ((exceptionReporter != null) != z) {
                if (z) {
                    ExceptionReporter exceptionReporter2 = new ExceptionReporter(this, Thread.getDefaultUncaughtExceptionHandler(), getContext());
                    this.zzdvz = exceptionReporter2;
                    Thread.setDefaultUncaughtExceptionHandler(exceptionReporter2);
                    str = "Uncaught exceptions will be reported to Google Analytics";
                } else {
                    Thread.setDefaultUncaughtExceptionHandler(exceptionReporter.zzvv());
                    str = "Uncaught exceptions will not be reported to Google Analytics";
                }
                zzea(str);
            }
        }
    }

    public String get(String str) {
        zzyk();
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (this.zzbwu.containsKey(str)) {
            return this.zzbwu.get(str);
        }
        if (str.equals("&ul")) {
            return zzatt.zza(Locale.getDefault());
        }
        if (str.equals("&cid")) {
            return zzyg().zzzp();
        }
        if (str.equals("&sr")) {
            return zzyj().zzaai();
        }
        if (str.equals("&aid")) {
            return zzyi().zzzd().getAppId();
        }
        if (str.equals("&an")) {
            return zzyi().zzzd().zzwn();
        }
        if (str.equals("&av")) {
            return zzyi().zzzd().zzwo();
        }
        if (str.equals("&aiid")) {
            return zzyi().zzzd().zzwp();
        }
        return null;
    }

    public void send(Map<String, String> map) {
        long currentTimeMillis = zzxx().currentTimeMillis();
        if (zzyb().getAppOptOut()) {
            zzeb("AppOptOut is set to true. Not sending Google Analytics hit");
            return;
        }
        boolean isDryRunEnabled = zzyb().isDryRunEnabled();
        HashMap hashMap = new HashMap();
        zzb(this.zzbwu, hashMap);
        zzb(map, hashMap);
        int i = 1;
        boolean zzd = zzatt.zzd(this.zzbwu.get("useSecure"), true);
        zzc(this.zzdvw, hashMap);
        this.zzdvw.clear();
        String str = (String) hashMap.get("t");
        if (TextUtils.isEmpty(str)) {
            zzxy().zzf(hashMap, "Missing hit type parameter");
            return;
        }
        String str2 = (String) hashMap.get("tid");
        if (TextUtils.isEmpty(str2)) {
            zzxy().zzf(hashMap, "Missing tracking id parameter");
            return;
        }
        boolean z = this.zzdvv;
        synchronized (this) {
            if ("screenview".equalsIgnoreCase(str) || "pageview".equalsIgnoreCase(str) || "appview".equalsIgnoreCase(str) || TextUtils.isEmpty(str)) {
                int parseInt = Integer.parseInt(this.zzbwu.get("&a")) + 1;
                if (parseInt < Integer.MAX_VALUE) {
                    i = parseInt;
                }
                this.zzbwu.put("&a", Integer.toString(i));
            }
        }
        zzya().zzd(new zzp(this, hashMap, z, str, currentTimeMillis, isDryRunEnabled, zzd, str2));
    }

    public void set(String str, String str2) {
        zzbq.checkNotNull(str, "Key should be non-null");
        if (!TextUtils.isEmpty(str)) {
            this.zzbwu.put(str, str2);
        }
    }

    public void setAnonymizeIp(boolean z) {
        set("&aip", zzatt.zzao(z));
    }

    public void setAppId(String str) {
        set("&aid", str);
    }

    public void setAppInstallerId(String str) {
        set("&aiid", str);
    }

    public void setAppName(String str) {
        set("&an", str);
    }

    public void setAppVersion(String str) {
        set("&av", str);
    }

    public void setCampaignParamsOnNextHit(Uri uri) {
        if (uri != null && !uri.isOpaque()) {
            String queryParameter = uri.getQueryParameter("referrer");
            if (!TextUtils.isEmpty(queryParameter)) {
                String valueOf = String.valueOf(queryParameter);
                Uri parse = Uri.parse(valueOf.length() != 0 ? "http://hostname/?".concat(valueOf) : new String("http://hostname/?"));
                String queryParameter2 = parse.getQueryParameter("utm_id");
                if (queryParameter2 != null) {
                    this.zzdvw.put("&ci", queryParameter2);
                }
                String queryParameter3 = parse.getQueryParameter("anid");
                if (queryParameter3 != null) {
                    this.zzdvw.put("&anid", queryParameter3);
                }
                String queryParameter4 = parse.getQueryParameter("utm_campaign");
                if (queryParameter4 != null) {
                    this.zzdvw.put("&cn", queryParameter4);
                }
                String queryParameter5 = parse.getQueryParameter("utm_content");
                if (queryParameter5 != null) {
                    this.zzdvw.put("&cc", queryParameter5);
                }
                String queryParameter6 = parse.getQueryParameter("utm_medium");
                if (queryParameter6 != null) {
                    this.zzdvw.put("&cm", queryParameter6);
                }
                String queryParameter7 = parse.getQueryParameter("utm_source");
                if (queryParameter7 != null) {
                    this.zzdvw.put("&cs", queryParameter7);
                }
                String queryParameter8 = parse.getQueryParameter("utm_term");
                if (queryParameter8 != null) {
                    this.zzdvw.put("&ck", queryParameter8);
                }
                String queryParameter9 = parse.getQueryParameter("dclid");
                if (queryParameter9 != null) {
                    this.zzdvw.put("&dclid", queryParameter9);
                }
                String queryParameter10 = parse.getQueryParameter("gclid");
                if (queryParameter10 != null) {
                    this.zzdvw.put("&gclid", queryParameter10);
                }
                String queryParameter11 = parse.getQueryParameter(FirebaseAnalytics.Param.ACLID);
                if (queryParameter11 != null) {
                    this.zzdvw.put("&aclid", queryParameter11);
                }
            }
        }
    }

    public void setClientId(String str) {
        set("&cid", str);
    }

    public void setEncoding(String str) {
        set("&de", str);
    }

    public void setHostname(String str) {
        set("&dh", str);
    }

    public void setLanguage(String str) {
        set("&ul", str);
    }

    public void setLocation(String str) {
        set("&dl", str);
    }

    public void setPage(String str) {
        set("&dp", str);
    }

    public void setReferrer(String str) {
        set("&dr", str);
    }

    public void setSampleRate(double d) {
        set("&sf", Double.toString(d));
    }

    public void setScreenColors(String str) {
        set("&sd", str);
    }

    public void setScreenName(String str) {
        set("&cd", str);
    }

    public void setScreenResolution(int i, int i2) {
        if (i >= 0 || i2 >= 0) {
            StringBuilder sb = new StringBuilder(23);
            sb.append(i);
            sb.append(GroupChatInvitation.ELEMENT_NAME);
            sb.append(i2);
            set("&sr", sb.toString());
            return;
        }
        zzed("Invalid width or height. The values should be non-negative.");
    }

    public void setSessionTimeout(long j) {
        this.zzdvy.setSessionTimeout(j * 1000);
    }

    public void setTitle(String str) {
        set("&dt", str);
    }

    public void setUseSecure(boolean z) {
        set("useSecure", zzatt.zzao(z));
    }

    public void setViewportSize(String str) {
        set("&vp", str);
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzats zzats) {
        zzea("Loading Tracker config values");
        this.zzdwa = zzats;
        boolean z = false;
        if (zzats.zzdub != null) {
            String str = this.zzdwa.zzdub;
            set("&tid", str);
            zza("trackingId loaded", str);
        }
        if (this.zzdwa.zzees >= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            String d = Double.toString(this.zzdwa.zzees);
            set("&sf", d);
            zza("Sample frequency loaded", d);
        }
        if (this.zzdwa.zzeet >= 0) {
            int i = this.zzdwa.zzeet;
            setSessionTimeout((long) i);
            zza("Session timeout loaded", Integer.valueOf(i));
        }
        if (this.zzdwa.zzeeu != -1) {
            boolean z2 = this.zzdwa.zzeeu == 1;
            enableAutoActivityTracking(z2);
            zza("Auto activity tracking loaded", Boolean.valueOf(z2));
        }
        if (this.zzdwa.zzeev != -1) {
            boolean z3 = this.zzdwa.zzeev == 1;
            if (z3) {
                set("&aip", "1");
            }
            zza("Anonymize ip loaded", Boolean.valueOf(z3));
        }
        if (this.zzdwa.zzeew == 1) {
            z = true;
        }
        enableExceptionReporting(z);
    }

    /* access modifiers changed from: protected */
    public final void zzwk() {
        this.zzdvy.initialize();
        String zzwn = zzye().zzwn();
        if (zzwn != null) {
            set("&an", zzwn);
        }
        String zzwo = zzye().zzwo();
        if (zzwo != null) {
            set("&av", zzwo);
        }
    }
}
