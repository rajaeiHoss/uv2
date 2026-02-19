package com.google.android.gms.internal;

import android.app.Activity;
import android.app.Application;
import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import com.google.android.gms.ads.internal.zzbt;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Iterator;

@zzabh
public final class zzgr implements Application.ActivityLifecycleCallbacks, View.OnAttachStateChangeListener, ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener {
    private static final long zzaxh = ((Long) zzlc.zzio().zzd(zzoi.zzbqm)).longValue();
    private final Context mApplicationContext;
    private zzake zzavf = new zzake(zzaxh);
    private final WindowManager zzavz;
    private final PowerManager zzawa;
    private final KeyguardManager zzawb;
    private boolean zzawi = false;
    private BroadcastReceiver zzawj;
    private final Rect zzawm;
    private Application zzaxi;
    private WeakReference<ViewTreeObserver> zzaxj;
    private WeakReference<View> zzaxk;
    private zzgw zzaxl;
    private int zzaxm = -1;
    private final HashSet<zzgv> zzaxn = new HashSet<>();
    private final DisplayMetrics zzaxo;

    public zzgr(Context context, View view) {
        Context applicationContext = context.getApplicationContext();
        this.mApplicationContext = applicationContext;
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        this.zzavz = windowManager;
        this.zzawa = (PowerManager) applicationContext.getSystemService("power");
        this.zzawb = (KeyguardManager) context.getSystemService("keyguard");
        if (applicationContext instanceof Application) {
            this.zzaxi = (Application) applicationContext;
            this.zzaxl = new zzgw((Application) applicationContext, this);
        }
        this.zzaxo = context.getResources().getDisplayMetrics();
        Rect rect = new Rect();
        this.zzawm = rect;
        rect.right = windowManager.getDefaultDisplay().getWidth();
        rect.bottom = windowManager.getDefaultDisplay().getHeight();
        WeakReference<View> weakReference = this.zzaxk;
        View view2 = weakReference != null ? (View) weakReference.get() : null;
        if (view2 != null) {
            view2.removeOnAttachStateChangeListener(this);
            zzf(view2);
        }
        this.zzaxk = new WeakReference<>(view);
        if (view != null) {
            if (zzbt.zzen().isAttachedToWindow(view)) {
                zze(view);
            }
            view.addOnAttachStateChangeListener(this);
        }
    }

    private final Rect zza(Rect rect) {
        return new Rect(zzn(rect.left), zzn(rect.top), zzn(rect.right), zzn(rect.bottom));
    }

    private final void zza(Activity activity, int i) {
        Window window;
        if (this.zzaxk != null && (window = activity.getWindow()) != null) {
            View peekDecorView = window.peekDecorView();
            View view = (View) this.zzaxk.get();
            if (view != null && peekDecorView != null && view.getRootView() == peekDecorView.getRootView()) {
                this.zzaxm = i;
            }
        }
    }

    private final void zzat() {
        zzbt.zzel();
        zzaij.zzdfn.post(new zzgs(this));
    }

    private final void zze(View view) {
        ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            this.zzaxj = new WeakReference<>(viewTreeObserver);
            viewTreeObserver.addOnScrollChangedListener(this);
            viewTreeObserver.addOnGlobalLayoutListener(this);
        }
        if (this.zzawj == null) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.SCREEN_ON");
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            intentFilter.addAction("android.intent.action.USER_PRESENT");
            this.zzawj = new zzgt(this);
            zzbt.zzfk().zza(this.mApplicationContext, this.zzawj, intentFilter);
        }
        Application application = this.zzaxi;
        if (application != null) {
            try {
                application.registerActivityLifecycleCallbacks(this.zzaxl);
            } catch (Exception e) {
                zzahw.zzb("Error registering activity lifecycle callbacks.", e);
            }
        }
    }

    private final void zzf(View view) {
        try {
            WeakReference<ViewTreeObserver> weakReference = this.zzaxj;
            if (weakReference != null) {
                ViewTreeObserver viewTreeObserver = (ViewTreeObserver) weakReference.get();
                if (viewTreeObserver != null && viewTreeObserver.isAlive()) {
                    viewTreeObserver.removeOnScrollChangedListener(this);
                    viewTreeObserver.removeGlobalOnLayoutListener(this);
                }
                this.zzaxj = null;
            }
        } catch (Exception e) {
            zzahw.zzb("Error while unregistering listeners from the last ViewTreeObserver.", e);
        }
        try {
            ViewTreeObserver viewTreeObserver2 = view.getViewTreeObserver();
            if (viewTreeObserver2.isAlive()) {
                viewTreeObserver2.removeOnScrollChangedListener(this);
                viewTreeObserver2.removeGlobalOnLayoutListener(this);
            }
        } catch (Exception e2) {
            zzahw.zzb("Error while unregistering listeners from the ViewTreeObserver.", e2);
        }
        if (this.zzawj != null) {
            try {
                zzbt.zzfk().zza(this.mApplicationContext, this.zzawj);
            } catch (IllegalStateException e3) {
                zzahw.zzb("Failed trying to unregister the receiver", e3);
            } catch (Exception e4) {
                zzbt.zzep().zza(e4, "ActiveViewUnit.stopScreenStatusMonitoring");
            }
            this.zzawj = null;
        }
        Application application = this.zzaxi;
        if (application != null) {
            try {
                application.unregisterActivityLifecycleCallbacks(this.zzaxl);
            } catch (Exception e5) {
                zzahw.zzb("Error registering activity lifecycle callbacks.", e5);
            }
        }
    }

    /* access modifiers changed from: private */
    public final void zzm(int i) {
        WeakReference<View> weakReference;
        boolean z;
        boolean z2;
        int i2 = i;
        if (this.zzaxn.size() != 0 && (weakReference = this.zzaxk) != null) {
            View view = (View) weakReference.get();
            boolean z3 = i2 == 1;
            boolean z4 = view == null;
            Rect rect = new Rect();
            Rect rect2 = new Rect();
            Rect rect3 = new Rect();
            Rect rect4 = new Rect();
            int[] iArr = new int[2];
            int[] iArr2 = new int[2];
            if (view != null) {
                boolean globalVisibleRect = view.getGlobalVisibleRect(rect2);
                boolean localVisibleRect = view.getLocalVisibleRect(rect3);
                view.getHitRect(rect4);
                try {
                    view.getLocationOnScreen(iArr);
                    view.getLocationInWindow(iArr2);
                } catch (Exception e) {
                    zzahw.zzb("Failure getting view location.", e);
                }
                rect.left = iArr[0];
                rect.top = iArr[1];
                rect.right = rect.left + view.getWidth();
                rect.bottom = rect.top + view.getHeight();
                z2 = globalVisibleRect;
                z = localVisibleRect;
            } else {
                z2 = false;
                z = false;
            }
            int windowVisibility = view != null ? view.getWindowVisibility() : 8;
            int i3 = this.zzaxm;
            if (i3 != -1) {
                windowVisibility = i3;
            }
            boolean z5 = !z4 && zzbt.zzel().zza(view, this.zzawa, this.zzawb) && z2 && z && windowVisibility == 0;
            if (z3 && !this.zzavf.tryAcquire() && z5 == this.zzawi) {
                return;
            }
            if (z5 || this.zzawi || i2 != 1) {
                zzgu zzgu = new zzgu(zzbt.zzes().elapsedRealtime(), this.zzawa.isScreenOn(), view != null ? zzbt.zzen().isAttachedToWindow(view) : false, view != null ? view.getWindowVisibility() : 8, zza(this.zzawm), zza(rect), zza(rect2), z2, zza(rect3), z, zza(rect4), this.zzaxo.density, z5);
                Iterator<zzgv> it = this.zzaxn.iterator();
                while (it.hasNext()) {
                    it.next().zza(zzgu);
                }
                this.zzawi = z5;
            }
        }
    }

    private final int zzn(int i) {
        return (int) (((float) i) / this.zzaxo.density);
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
        zza(activity, 0);
        zzm(3);
        zzat();
    }

    public final void onActivityDestroyed(Activity activity) {
        zzm(3);
        zzat();
    }

    public final void onActivityPaused(Activity activity) {
        zza(activity, 4);
        zzm(3);
        zzat();
    }

    public final void onActivityResumed(Activity activity) {
        zza(activity, 0);
        zzm(3);
        zzat();
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        zzm(3);
        zzat();
    }

    public final void onActivityStarted(Activity activity) {
        zza(activity, 0);
        zzm(3);
        zzat();
    }

    public final void onActivityStopped(Activity activity) {
        zzm(3);
        zzat();
    }

    public final void onGlobalLayout() {
        zzm(2);
        zzat();
    }

    public final void onScrollChanged() {
        zzm(1);
    }

    public final void onViewAttachedToWindow(View view) {
        this.zzaxm = -1;
        zze(view);
        zzm(3);
    }

    public final void onViewDetachedFromWindow(View view) {
        this.zzaxm = -1;
        zzm(3);
        zzat();
        zzf(view);
    }

    public final void zza(zzgv zzgv) {
        this.zzaxn.add(zzgv);
        zzm(3);
    }

    public final void zzb(zzgv zzgv) {
        this.zzaxn.remove(zzgv);
    }

    public final void zzgm() {
        zzm(4);
    }
}
