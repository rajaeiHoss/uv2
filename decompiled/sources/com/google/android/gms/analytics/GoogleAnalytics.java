package com.google.android.gms.analytics;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.internal.zzark;
import com.google.android.gms.internal.zzast;
import com.google.android.gms.internal.zzatb;
import com.google.android.gms.internal.zzatc;
import com.google.android.gms.internal.zzatq;
import com.google.android.gms.internal.zzats;
import com.google.android.gms.internal.zzatu;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class GoogleAnalytics extends zza {
    private static List<Runnable> zzduj = new ArrayList();
    private boolean zzarf;
    private Set<zza> zzduk = new HashSet();
    private boolean zzdul;
    private boolean zzdum;
    private volatile boolean zzdun;
    private boolean zzduo;

    interface zza {
        void zzm(Activity activity);

        void zzn(Activity activity);
    }

    class zzb implements Application.ActivityLifecycleCallbacks {
        zzb() {
        }

        public final void onActivityCreated(Activity activity, Bundle bundle) {
        }

        public final void onActivityDestroyed(Activity activity) {
        }

        public final void onActivityPaused(Activity activity) {
        }

        public final void onActivityResumed(Activity activity) {
        }

        public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        public final void onActivityStarted(Activity activity) {
            GoogleAnalytics.this.zzk(activity);
        }

        public final void onActivityStopped(Activity activity) {
            GoogleAnalytics.this.zzl(activity);
        }
    }

    public GoogleAnalytics(zzark zzark) {
        super(zzark);
    }

    public static GoogleAnalytics getInstance(Context context) {
        return zzark.zzbl(context).zzyn();
    }

    public static void zzvw() {
        synchronized (GoogleAnalytics.class) {
            List<Runnable> list = zzduj;
            if (list != null) {
                for (Runnable run : list) {
                    run.run();
                }
                zzduj = null;
            }
        }
    }

    public final void dispatchLocalHits() {
        zzvr().zzyc().zzxs();
    }

    public final void enableAutoActivityReports(Application application) {
        if (!this.zzdul) {
            application.registerActivityLifecycleCallbacks(new zzb());
            this.zzdul = true;
        }
    }

    public final boolean getAppOptOut() {
        return this.zzdun;
    }

    @Deprecated
    public final Logger getLogger() {
        return zzatc.getLogger();
    }

    public final void initialize() {
        zzatu zzye = zzvr().zzye();
        zzye.zzabm();
        if (zzye.zzabn()) {
            setDryRun(zzye.zzabo());
        }
        zzye.zzabm();
        this.zzarf = true;
    }

    public final boolean isDryRunEnabled() {
        return this.zzdum;
    }

    public final boolean isInitialized() {
        return this.zzarf;
    }

    public final Tracker newTracker(int i) {
        Tracker tracker;
        zzats zzats;
        synchronized (this) {
            tracker = new Tracker(zzvr(), (String) null, (zzatb) null);
            if (i > 0 && (zzats = (zzats) new zzatq(zzvr()).zzav(i)) != null) {
                tracker.zza(zzats);
            }
            tracker.initialize();
        }
        return tracker;
    }

    public final Tracker newTracker(String str) {
        Tracker tracker;
        synchronized (this) {
            tracker = new Tracker(zzvr(), str, (zzatb) null);
            tracker.initialize();
        }
        return tracker;
    }

    public final void reportActivityStart(Activity activity) {
        if (!this.zzdul) {
            zzk(activity);
        }
    }

    public final void reportActivityStop(Activity activity) {
        if (!this.zzdul) {
            zzl(activity);
        }
    }

    public final void setAppOptOut(boolean z) {
        this.zzdun = z;
        if (this.zzdun) {
            zzvr().zzyc().zzxr();
        }
    }

    public final void setDryRun(boolean z) {
        this.zzdum = z;
    }

    public final void setLocalDispatchPeriod(int i) {
        zzvr().zzyc().setLocalDispatchPeriod(i);
    }

    @Deprecated
    public final void setLogger(Logger logger) {
        zzatc.setLogger(logger);
        if (!this.zzduo) {
            String str = zzast.zzebn.get();
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 112);
            sb.append("GoogleAnalytics.setLogger() is deprecated. To enable debug logging, please run:\nadb shell setprop log.tag.");
            sb.append(str);
            sb.append(" DEBUG");
            Log.i(zzast.zzebn.get(), sb.toString());
            this.zzduo = true;
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(zza zza2) {
        this.zzduk.add(zza2);
        Context context = zzvr().getContext();
        if (context instanceof Application) {
            enableAutoActivityReports((Application) context);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzb(zza zza2) {
        this.zzduk.remove(zza2);
    }

    /* access modifiers changed from: package-private */
    public final void zzk(Activity activity) {
        for (zza zzm : this.zzduk) {
            zzm.zzm(activity);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzl(Activity activity) {
        for (zza zzn : this.zzduk) {
            zzn.zzn(activity);
        }
    }
}
