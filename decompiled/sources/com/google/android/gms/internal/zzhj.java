package com.google.android.gms.internal;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import com.google.android.gms.ads.internal.zzbt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

final class zzhj implements Application.ActivityLifecycleCallbacks {
    private Activity mActivity;
    private Context mContext;
    /* access modifiers changed from: private */
    public final Object mLock = new Object();
    private boolean zzarf = false;
    /* access modifiers changed from: private */
    public boolean zzayw = true;
    /* access modifiers changed from: private */
    public boolean zzayx = false;
    /* access modifiers changed from: private */
    public final List<zzhl> zzayy = new ArrayList();
    private final List<zzhy> zzayz = new ArrayList();
    private Runnable zzaza;
    private long zzazb;

    zzhj() {
    }

    private final void setActivity(Activity activity) {
        synchronized (this.mLock) {
            if (!activity.getClass().getName().startsWith("com.google.android.gms.ads")) {
                this.mActivity = activity;
            }
        }
    }

    public final Activity getActivity() {
        return this.mActivity;
    }

    public final Context getContext() {
        return this.mContext;
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public final void onActivityDestroyed(Activity activity) {
        synchronized (this.mLock) {
            Activity activity2 = this.mActivity;
            if (activity2 != null) {
                if (activity2.equals(activity)) {
                    this.mActivity = null;
                }
                Iterator<zzhy> it = this.zzayz.iterator();
                while (it.hasNext()) {
                    try {
                        if (it.next().zza(activity)) {
                            it.remove();
                        }
                    } catch (Exception e) {
                        zzbt.zzep().zza(e, "AppActivityTracker.ActivityListener.onActivityDestroyed");
                        zzahw.zzb("onActivityStateChangedListener threw exception.", e);
                    }
                }
            }
        }
    }

    public final void onActivityPaused(Activity activity) {
        setActivity(activity);
        synchronized (this.mLock) {
            for (zzhy onActivityPaused : this.zzayz) {
                try {
                    onActivityPaused.onActivityPaused(activity);
                } catch (Exception e) {
                    zzbt.zzep().zza(e, "AppActivityTracker.ActivityListener.onActivityPaused");
                    zzahw.zzb("onActivityStateChangedListener threw exception.", e);
                }
            }
        }
        this.zzayx = true;
        if (this.zzaza != null) {
            zzaij.zzdfn.removeCallbacks(this.zzaza);
        }
        Handler handler = zzaij.zzdfn;
        zzhk zzhk = new zzhk(this);
        this.zzaza = zzhk;
        handler.postDelayed(zzhk, this.zzazb);
    }

    public final void onActivityResumed(Activity activity) {
        setActivity(activity);
        this.zzayx = false;
        boolean z = !this.zzayw;
        this.zzayw = true;
        if (this.zzaza != null) {
            zzaij.zzdfn.removeCallbacks(this.zzaza);
        }
        synchronized (this.mLock) {
            for (zzhy onActivityResumed : this.zzayz) {
                try {
                    onActivityResumed.onActivityResumed(activity);
                } catch (Exception e) {
                    zzbt.zzep().zza(e, "AppActivityTracker.ActivityListener.onActivityResumed");
                    zzahw.zzb("onActivityStateChangedListener threw exception.", e);
                }
            }
            if (z) {
                for (zzhl zzh : this.zzayy) {
                    try {
                        zzh.zzh(true);
                    } catch (Exception e2) {
                        zzahw.zzb("OnForegroundStateChangedListener threw exception.", e2);
                    }
                }
            } else {
                zzahw.zzby("App is still foreground.");
            }
        }
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public final void onActivityStarted(Activity activity) {
        setActivity(activity);
    }

    public final void onActivityStopped(Activity activity) {
    }

    public final void zza(Application application, Context context) {
        if (!this.zzarf) {
            application.registerActivityLifecycleCallbacks(this);
            if (context instanceof Activity) {
                setActivity((Activity) context);
            }
            this.mContext = application;
            this.zzazb = ((Long) zzlc.zzio().zzd(zzoi.zzbpb)).longValue();
            this.zzarf = true;
        }
    }

    public final void zza(zzhl zzhl) {
        synchronized (this.mLock) {
            this.zzayy.add(zzhl);
        }
    }
}
