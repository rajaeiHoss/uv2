package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.zzk;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzi;
import java.lang.Thread;

public class zzark {
    private static volatile zzark zzdys;
    private final Context mContext;
    private final zze zzata = zzi.zzanq();
    private final Context zzdyt;
    private final zzasl zzdyu = new zzasl(this);
    private final zzatd zzdyv;
    private final zzk zzdyw;
    private final zzaqz zzdyx;
    private final zzasq zzdyy;
    private final zzatu zzdyz;
    private final zzath zzdza;
    private final GoogleAnalytics zzdzb;
    private final zzasc zzdzc;
    private final zzaqy zzdzd;
    private final zzarv zzdze;
    private final zzasp zzdzf;

    private zzark(zzarm zzarm) {
        Context applicationContext = zzarm.getApplicationContext();
        zzbq.checkNotNull(applicationContext, "Application context can't be null");
        Context zzyl = zzarm.zzyl();
        zzbq.checkNotNull(zzyl);
        this.mContext = applicationContext;
        this.zzdyt = zzyl;
        zzatd zzatd = new zzatd(this);
        zzatd.initialize();
        this.zzdyv = zzatd;
        zzatd zzxy = zzxy();
        String str = zzarj.VERSION;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 134);
        sb.append("Google Analytics ");
        sb.append(str);
        sb.append(" is starting up. To enable debug logging on a device run:\n  adb shell setprop log.tag.GAv4 DEBUG\n  adb logcat -s GAv4");
        zzxy.zzec(sb.toString());
        zzath zzath = new zzath(this);
        zzath.initialize();
        this.zzdza = zzath;
        zzatu zzatu = new zzatu(this);
        zzatu.initialize();
        this.zzdyz = zzatu;
        zzaqz zzaqz = new zzaqz(this, zzarm);
        zzasc zzasc = new zzasc(this);
        zzaqy zzaqy = new zzaqy(this);
        zzarv zzarv = new zzarv(this);
        zzasp zzasp = new zzasp(this);
        zzk zzbk = zzk.zzbk(applicationContext);
        zzbk.zza((Thread.UncaughtExceptionHandler) new zzarl(this));
        this.zzdyw = zzbk;
        GoogleAnalytics googleAnalytics = new GoogleAnalytics(this);
        zzasc.initialize();
        this.zzdzc = zzasc;
        zzaqy.initialize();
        this.zzdzd = zzaqy;
        zzarv.initialize();
        this.zzdze = zzarv;
        zzasp.initialize();
        this.zzdzf = zzasp;
        zzasq zzasq = new zzasq(this);
        zzasq.initialize();
        this.zzdyy = zzasq;
        zzaqz.initialize();
        this.zzdyx = zzaqz;
        googleAnalytics.initialize();
        this.zzdzb = googleAnalytics;
        zzaqz.start();
    }

    private static void zza(zzari zzari) {
        zzbq.checkNotNull(zzari, "Analytics service not created/initialized");
        zzbq.checkArgument(zzari.isInitialized(), "Analytics service not initialized");
    }

    public static zzark zzbl(Context context) {
        zzbq.checkNotNull(context);
        if (zzdys == null) {
            synchronized (zzark.class) {
                if (zzdys == null) {
                    zze zzanq = zzi.zzanq();
                    long elapsedRealtime = zzanq.elapsedRealtime();
                    zzark zzark = new zzark(new zzarm(context));
                    zzdys = zzark;
                    GoogleAnalytics.zzvw();
                    long elapsedRealtime2 = zzanq.elapsedRealtime() - elapsedRealtime;
                    long longValue = zzast.zzedb.get().longValue();
                    if (elapsedRealtime2 > longValue) {
                        zzark.zzxy().zzc("Slow initialization (ms)", Long.valueOf(elapsedRealtime2), Long.valueOf(longValue));
                    }
                }
            }
        }
        return zzdys;
    }

    public final Context getContext() {
        return this.mContext;
    }

    public final zze zzxx() {
        return this.zzata;
    }

    public final zzatd zzxy() {
        zza(this.zzdyv);
        return this.zzdyv;
    }

    public final zzasl zzxz() {
        return this.zzdyu;
    }

    public final zzk zzya() {
        zzbq.checkNotNull(this.zzdyw);
        return this.zzdyw;
    }

    public final zzaqz zzyc() {
        zza(this.zzdyx);
        return this.zzdyx;
    }

    public final zzasq zzyd() {
        zza(this.zzdyy);
        return this.zzdyy;
    }

    public final zzatu zzye() {
        zza(this.zzdyz);
        return this.zzdyz;
    }

    public final zzath zzyf() {
        zza(this.zzdza);
        return this.zzdza;
    }

    public final zzarv zzyi() {
        zza(this.zzdze);
        return this.zzdze;
    }

    public final zzasp zzyj() {
        return this.zzdzf;
    }

    public final Context zzyl() {
        return this.zzdyt;
    }

    public final zzatd zzym() {
        return this.zzdyv;
    }

    public final GoogleAnalytics zzyn() {
        zzbq.checkNotNull(this.zzdzb);
        zzbq.checkArgument(this.zzdzb.isInitialized(), "Analytics instance not initialized");
        return this.zzdzb;
    }

    public final zzath zzyo() {
        zzath zzath = this.zzdza;
        if (zzath == null || !zzath.isInitialized()) {
            return null;
        }
        return this.zzdza;
    }

    public final zzaqy zzyp() {
        zza(this.zzdzd);
        return this.zzdzd;
    }

    public final zzasc zzyq() {
        zza(this.zzdzc);
        return this.zzdzc;
    }
}
