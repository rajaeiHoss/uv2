package com.google.android.gms.internal;

import android.app.Activity;
import android.app.Application;
import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import java.lang.ref.WeakReference;

public final class zzdu implements Application.ActivityLifecycleCallbacks, View.OnAttachStateChangeListener, ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener {
    private static final Handler zzaju = new Handler(Looper.getMainLooper());
    private final zzdm zzagq;
    private Application zzaih;
    private final Context zzajv;
    private final PowerManager zzajw;
    private final KeyguardManager zzajx;
    private BroadcastReceiver zzajy;
    private WeakReference<ViewTreeObserver> zzajz;
    private WeakReference<View> zzaka;
    private zzda zzakb;
    private boolean zzakc = false;
    private int zzakd = -1;
    private long zzake = -3;

    public zzdu(zzdm zzdm, View view) {
        this.zzagq = zzdm;
        Context context = zzdm.zzaiq;
        this.zzajv = context;
        this.zzajw = (PowerManager) context.getSystemService("power");
        this.zzajx = (KeyguardManager) context.getSystemService("keyguard");
        if (context instanceof Application) {
            this.zzaih = (Application) context;
            this.zzakb = new zzda((Application) context, this);
        }
        zzd(view);
    }

    private final void zza(Activity activity, int i) {
        Window window;
        if (this.zzaka != null && (window = activity.getWindow()) != null) {
            View peekDecorView = window.peekDecorView();
            View view = (View) this.zzaka.get();
            if (view != null && peekDecorView != null && view.getRootView() == peekDecorView.getRootView()) {
                this.zzakd = i;
            }
        }
    }

    private final void zzat() {
        zzaju.post(new zzdv(this));
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0056, code lost:
        if (r4 == false) goto L_0x0059;
     */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0065  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0087  */
    /* JADX WARNING: Removed duplicated region for block: B:46:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzav() {
        /*
            r9 = this;
            java.lang.ref.WeakReference<android.view.View> r0 = r9.zzaka
            if (r0 != 0) goto L_0x0005
            return
        L_0x0005:
            java.lang.Object r0 = r0.get()
            android.view.View r0 = (android.view.View) r0
            r1 = 0
            if (r0 != 0) goto L_0x0015
            r2 = -3
            r9.zzake = r2
            r9.zzakc = r1
            return
        L_0x0015:
            android.graphics.Rect r2 = new android.graphics.Rect
            r2.<init>()
            boolean r2 = r0.getGlobalVisibleRect(r2)
            android.graphics.Rect r3 = new android.graphics.Rect
            r3.<init>()
            boolean r3 = r0.getLocalVisibleRect(r3)
            com.google.android.gms.internal.zzdm r4 = r9.zzagq
            boolean r4 = r4.zzal()
            r5 = 1
            if (r4 != 0) goto L_0x005b
            android.app.KeyguardManager r4 = r9.zzajx
            boolean r4 = r4.inKeyguardRestrictedInputMode()
            if (r4 == 0) goto L_0x0059
            android.app.Activity r4 = com.google.android.gms.internal.zzds.zzc(r0)
            if (r4 == 0) goto L_0x0055
            android.view.Window r4 = r4.getWindow()
            if (r4 != 0) goto L_0x0046
            r4 = 0
            goto L_0x004a
        L_0x0046:
            android.view.WindowManager$LayoutParams r4 = r4.getAttributes()
        L_0x004a:
            if (r4 == 0) goto L_0x0055
            int r4 = r4.flags
            r6 = 524288(0x80000, float:7.34684E-40)
            r4 = r4 & r6
            if (r4 == 0) goto L_0x0055
            r4 = 1
            goto L_0x0056
        L_0x0055:
            r4 = 0
        L_0x0056:
            if (r4 == 0) goto L_0x0059
            goto L_0x005b
        L_0x0059:
            r4 = 0
            goto L_0x005c
        L_0x005b:
            r4 = 1
        L_0x005c:
            int r6 = r0.getWindowVisibility()
            int r7 = r9.zzakd
            r8 = -1
            if (r7 == r8) goto L_0x0066
            r6 = r7
        L_0x0066:
            int r7 = r0.getVisibility()
            if (r7 != 0) goto L_0x0083
            boolean r0 = r0.isShown()
            if (r0 == 0) goto L_0x0083
            android.os.PowerManager r0 = r9.zzajw
            boolean r0 = r0.isScreenOn()
            if (r0 == 0) goto L_0x0083
            if (r4 == 0) goto L_0x0083
            if (r3 == 0) goto L_0x0083
            if (r2 == 0) goto L_0x0083
            if (r6 != 0) goto L_0x0083
            r1 = 1
        L_0x0083:
            boolean r0 = r9.zzakc
            if (r0 == r1) goto L_0x0094
            if (r1 == 0) goto L_0x008e
            long r2 = android.os.SystemClock.elapsedRealtime()
            goto L_0x0090
        L_0x008e:
            r2 = -2
        L_0x0090:
            r9.zzake = r2
            r9.zzakc = r1
        L_0x0094:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzdu.zzav():void");
    }

    private final void zze(View view) {
        ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            this.zzajz = new WeakReference<>(viewTreeObserver);
            viewTreeObserver.addOnScrollChangedListener(this);
            viewTreeObserver.addOnGlobalLayoutListener(this);
        }
        if (this.zzajy == null) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.SCREEN_ON");
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            intentFilter.addAction("android.intent.action.USER_PRESENT");
            zzdw zzdw = new zzdw(this);
            this.zzajy = zzdw;
            this.zzajv.registerReceiver(zzdw, intentFilter);
        }
        Application application = this.zzaih;
        if (application != null) {
            try {
                application.registerActivityLifecycleCallbacks(this.zzakb);
            } catch (Exception unused) {
            }
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(11:0|1|2|(3:4|(1:8)|9)|10|11|(1:13)|15|(3:17|18|19)|21|(3:23|24|26)(1:28)) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x001b */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0025 A[Catch:{ Exception -> 0x002c }] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0031 A[SYNTHETIC, Splitter:B:17:0x0031] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x003c A[SYNTHETIC, Splitter:B:23:0x003c] */
    /* JADX WARNING: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzf(android.view.View r4) {
        /*
            r3 = this;
            r0 = 0
            java.lang.ref.WeakReference<android.view.ViewTreeObserver> r1 = r3.zzajz     // Catch:{ Exception -> 0x001b }
            if (r1 == 0) goto L_0x001b
            java.lang.Object r1 = r1.get()     // Catch:{ Exception -> 0x001b }
            android.view.ViewTreeObserver r1 = (android.view.ViewTreeObserver) r1     // Catch:{ Exception -> 0x001b }
            if (r1 == 0) goto L_0x0019
            boolean r2 = r1.isAlive()     // Catch:{ Exception -> 0x001b }
            if (r2 == 0) goto L_0x0019
            r1.removeOnScrollChangedListener(r3)     // Catch:{ Exception -> 0x001b }
            r1.removeGlobalOnLayoutListener(r3)     // Catch:{ Exception -> 0x001b }
        L_0x0019:
            r3.zzajz = r0     // Catch:{ Exception -> 0x001b }
        L_0x001b:
            android.view.ViewTreeObserver r4 = r4.getViewTreeObserver()     // Catch:{ Exception -> 0x002c }
            boolean r1 = r4.isAlive()     // Catch:{ Exception -> 0x002c }
            if (r1 == 0) goto L_0x002d
            r4.removeOnScrollChangedListener(r3)     // Catch:{ Exception -> 0x002c }
            r4.removeGlobalOnLayoutListener(r3)     // Catch:{ Exception -> 0x002c }
            goto L_0x002d
        L_0x002c:
        L_0x002d:
            android.content.BroadcastReceiver r4 = r3.zzajy
            if (r4 == 0) goto L_0x0038
            android.content.Context r1 = r3.zzajv     // Catch:{ Exception -> 0x0036 }
            r1.unregisterReceiver(r4)     // Catch:{ Exception -> 0x0036 }
        L_0x0036:
            r3.zzajy = r0
        L_0x0038:
            android.app.Application r4 = r3.zzaih
            if (r4 == 0) goto L_0x0041
            com.google.android.gms.internal.zzda r0 = r3.zzakb     // Catch:{ Exception -> 0x0041 }
            r4.unregisterActivityLifecycleCallbacks(r0)     // Catch:{ Exception -> 0x0041 }
        L_0x0041:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzdu.zzf(android.view.View):void");
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
        zza(activity, 0);
        zzav();
    }

    public final void onActivityDestroyed(Activity activity) {
        zzav();
    }

    public final void onActivityPaused(Activity activity) {
        zza(activity, 4);
        zzav();
    }

    public final void onActivityResumed(Activity activity) {
        zza(activity, 0);
        zzav();
        zzat();
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        zzav();
    }

    public final void onActivityStarted(Activity activity) {
        zza(activity, 0);
        zzav();
    }

    public final void onActivityStopped(Activity activity) {
        zzav();
    }

    public final void onGlobalLayout() {
        zzav();
    }

    public final void onScrollChanged() {
        zzav();
    }

    public final void onViewAttachedToWindow(View view) {
        this.zzakd = -1;
        zze(view);
        zzav();
    }

    public final void onViewDetachedFromWindow(View view) {
        this.zzakd = -1;
        zzav();
        zzat();
        zzf(view);
    }

    public final long zzau() {
        if (this.zzake == -2 && this.zzaka.get() == null) {
            this.zzake = -3;
        }
        return this.zzake;
    }

    /* access modifiers changed from: package-private */
    public final void zzd(View view) {
        long j;
        WeakReference<View> weakReference = this.zzaka;
        View view2 = weakReference != null ? (View) weakReference.get() : null;
        if (view2 != null) {
            view2.removeOnAttachStateChangeListener(this);
            zzf(view2);
        }
        this.zzaka = new WeakReference<>(view);
        if (view != null) {
            if ((view.getWindowToken() == null && view.getWindowVisibility() == 8) ? false : true) {
                zze(view);
            }
            view.addOnAttachStateChangeListener(this);
            j = -2;
        } else {
            j = -3;
        }
        this.zzake = j;
    }
}
