package com.google.android.gms.internal;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.os.PowerManager;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.measurement.AppMeasurement;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzabh
public final class zzfv implements ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener {
    private final Context mApplicationContext;
    private Object mLock = new Object();
    private boolean zzarz = false;
    private zzake zzavf;
    private final WeakReference<zzahd> zzavv;
    private WeakReference<ViewTreeObserver> zzavw;
    private final zzhf zzavx;
    protected final zzft zzavy;
    private final WindowManager zzavz;
    private final PowerManager zzawa;
    private final KeyguardManager zzawb;
    private final DisplayMetrics zzawc;
    private zzgc zzawd;
    private boolean zzawe;
    private boolean zzawf = false;
    private boolean zzawg;
    private boolean zzawh;
    private boolean zzawi;
    private BroadcastReceiver zzawj;
    private final HashSet<zzfs> zzawk = new HashSet<>();
    private final HashSet<zzgq> zzawl = new HashSet<>();
    private final Rect zzawm;
    private final zzfy zzawn;
    private float zzawo;

    public zzfv(Context context, zzko zzko, zzahd zzahd, zzala zzala, zzhf zzhf) {
        Context context2 = context;
        zzko zzko2 = zzko;
        zzahd zzahd2 = zzahd;
        Rect rect = new Rect();
        this.zzawm = rect;
        this.zzavv = new WeakReference<>(zzahd2);
        this.zzavx = zzhf;
        this.zzavw = new WeakReference<>((ViewTreeObserver) null);
        this.zzawg = true;
        this.zzawi = false;
        this.zzavf = new zzake(200);
        this.zzavy = new zzft(UUID.randomUUID().toString(), zzala, zzko2.zzbia, zzahd2.zzdch, zzahd.zzfz(), zzko2.zzbid);
        WindowManager windowManager = (WindowManager) context2.getSystemService("window");
        this.zzavz = windowManager;
        this.zzawa = (PowerManager) context.getApplicationContext().getSystemService("power");
        this.zzawb = (KeyguardManager) context2.getSystemService("keyguard");
        this.mApplicationContext = context2;
        zzfy zzfy = new zzfy(this, new Handler());
        this.zzawn = zzfy;
        context.getContentResolver().registerContentObserver(Settings.System.CONTENT_URI, true, zzfy);
        this.zzawc = context.getResources().getDisplayMetrics();
        Display defaultDisplay = windowManager.getDefaultDisplay();
        rect.right = defaultDisplay.getWidth();
        rect.bottom = defaultDisplay.getHeight();
        zzgb();
    }

    private final boolean isScreenOn() {
        return Build.VERSION.SDK_INT >= 20 ? this.zzawa.isInteractive() : this.zzawa.isScreenOn();
    }

    private static int zza(int i, DisplayMetrics displayMetrics) {
        return (int) (((float) i) / displayMetrics.density);
    }

    private final JSONObject zza(View view, Boolean bool) throws JSONException {
        View view2 = view;
        if (view2 == null) {
            return zzgg().put("isAttachedToWindow", false).put("isScreenOn", isScreenOn()).put("isVisible", false);
        }
        boolean isAttachedToWindow = zzbt.zzen().isAttachedToWindow(view2);
        int[] iArr = new int[2];
        int[] iArr2 = new int[2];
        try {
            view2.getLocationOnScreen(iArr);
            view2.getLocationInWindow(iArr2);
        } catch (Exception e) {
            zzahw.zzb("Failure getting view location.", e);
        }
        Rect rect = new Rect();
        rect.left = iArr[0];
        rect.top = iArr[1];
        rect.right = rect.left + view.getWidth();
        rect.bottom = rect.top + view.getHeight();
        Rect rect2 = new Rect();
        boolean globalVisibleRect = view2.getGlobalVisibleRect(rect2, (Point) null);
        Rect rect3 = new Rect();
        boolean localVisibleRect = view2.getLocalVisibleRect(rect3);
        Rect rect4 = new Rect();
        view2.getHitRect(rect4);
        JSONObject zzgg = zzgg();
        String str = "isVisible";
        JSONObject jSONObject = zzgg;
        zzgg.put("windowVisibility", view.getWindowVisibility()).put("isAttachedToWindow", isAttachedToWindow).put("viewBox", new JSONObject().put("top", zza(this.zzawm.top, this.zzawc)).put("bottom", zza(this.zzawm.bottom, this.zzawc)).put("left", zza(this.zzawm.left, this.zzawc)).put("right", zza(this.zzawm.right, this.zzawc))).put("adBox", new JSONObject().put("top", zza(rect.top, this.zzawc)).put("bottom", zza(rect.bottom, this.zzawc)).put("left", zza(rect.left, this.zzawc)).put("right", zza(rect.right, this.zzawc))).put("globalVisibleBox", new JSONObject().put("top", zza(rect2.top, this.zzawc)).put("bottom", zza(rect2.bottom, this.zzawc)).put("left", zza(rect2.left, this.zzawc)).put("right", zza(rect2.right, this.zzawc))).put("globalVisibleBoxVisible", globalVisibleRect).put("localVisibleBox", new JSONObject().put("top", zza(rect3.top, this.zzawc)).put("bottom", zza(rect3.bottom, this.zzawc)).put("left", zza(rect3.left, this.zzawc)).put("right", zza(rect3.right, this.zzawc))).put("localVisibleBoxVisible", localVisibleRect).put("hitBox", new JSONObject().put("top", zza(rect4.top, this.zzawc)).put("bottom", zza(rect4.bottom, this.zzawc)).put("left", zza(rect4.left, this.zzawc)).put("right", zza(rect4.right, this.zzawc))).put("screenDensity", (double) this.zzawc.density);
        Boolean valueOf = bool == null ? Boolean.valueOf(zzbt.zzel().zza(view2, this.zzawa, this.zzawb)) : bool;
        JSONObject jSONObject2 = jSONObject;
        jSONObject2.put(str, valueOf.booleanValue());
        return jSONObject2;
    }

    private static JSONObject zza(JSONObject jSONObject) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject2 = new JSONObject();
        jSONArray.put(jSONObject);
        jSONObject2.put("units", jSONArray);
        return jSONObject2;
    }

    private final void zza(JSONObject jSONObject, boolean z) {
        try {
            JSONObject zza = zza(jSONObject);
            ArrayList arrayList = new ArrayList(this.zzawl);
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                Object obj = arrayList.get(i);
                i++;
                ((zzgq) obj).zzb(zza, z);
            }
        } catch (Throwable th) {
            zzahw.zzb("Skipping active view message.", th);
        }
    }

    private final void zzgd() {
        zzgc zzgc = this.zzawd;
        if (zzgc != null) {
            zzgc.zza(this);
        }
    }

    private final void zzgf() {
        ViewTreeObserver viewTreeObserver = (ViewTreeObserver) this.zzavw.get();
        if (viewTreeObserver != null && viewTreeObserver.isAlive()) {
            viewTreeObserver.removeOnScrollChangedListener(this);
            viewTreeObserver.removeGlobalOnLayoutListener(this);
        }
    }

    private final JSONObject zzgg() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("afmaVersion", this.zzavy.zzfw()).put("activeViewJSON", this.zzavy.zzfx()).put(AppMeasurement.Param.TIMESTAMP, zzbt.zzes().elapsedRealtime()).put("adFormat", this.zzavy.zzfv()).put("hashCode", this.zzavy.zzfy()).put("isMraid", this.zzavy.zzfz()).put("isStopped", this.zzawf).put("isPaused", this.zzarz).put("isNative", this.zzavy.zzga()).put("isScreenOn", isScreenOn()).put("appMuted", zzbt.zzfj().zzdq()).put("appVolume", (double) zzbt.zzfj().zzdp()).put("deviceVolume", (double) this.zzawo);
        return jSONObject;
    }

    public final void onGlobalLayout() {
        zzl(2);
    }

    public final void onScrollChanged() {
        zzl(1);
    }

    public final void pause() {
        synchronized (this.mLock) {
            this.zzarz = true;
            zzl(3);
        }
    }

    public final void resume() {
        synchronized (this.mLock) {
            this.zzarz = false;
            zzl(3);
        }
    }

    public final void stop() {
        synchronized (this.mLock) {
            this.zzawf = true;
            zzl(3);
        }
    }

    public final void zza(zzgc zzgc) {
        synchronized (this.mLock) {
            this.zzawd = zzgc;
        }
    }

    public final void zza(zzgq zzgq) {
        if (this.zzawl.isEmpty()) {
            synchronized (this.mLock) {
                if (this.zzawj == null) {
                    IntentFilter intentFilter = new IntentFilter();
                    intentFilter.addAction("android.intent.action.SCREEN_ON");
                    intentFilter.addAction("android.intent.action.SCREEN_OFF");
                    this.zzawj = new zzfw(this);
                    zzbt.zzfk().zza(this.mApplicationContext, this.zzawj, intentFilter);
                }
            }
            zzl(3);
        }
        this.zzawl.add(zzgq);
        try {
            zzgq.zzb(zza(zza(this.zzavx.zzgh(), (Boolean) null)), false);
        } catch (JSONException e) {
            zzahw.zzb("Skipping measurement update for new client.", e);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzgq zzgq, Map<String, String> map) {
        String valueOf = String.valueOf(this.zzavy.zzfy());
        zzahw.zzby(valueOf.length() != 0 ? "Received request to untrack: ".concat(valueOf) : new String("Received request to untrack: "));
        zzb(zzgq);
    }

    public final void zzb(zzgq zzgq) {
        this.zzawl.remove(zzgq);
        zzgq.zzgl();
        if (this.zzawl.isEmpty()) {
            synchronized (this.mLock) {
                zzgf();
                synchronized (this.mLock) {
                    if (this.zzawj != null) {
                        try {
                            zzbt.zzfk().zza(this.mApplicationContext, this.zzawj);
                        } catch (IllegalStateException e) {
                            zzahw.zzb("Failed trying to unregister the receiver", e);
                        } catch (Exception e2) {
                            zzbt.zzep().zza(e2, "ActiveViewUnit.stopScreenStatusMonitoring");
                        }
                        this.zzawj = null;
                    }
                }
                this.mApplicationContext.getContentResolver().unregisterContentObserver(this.zzawn);
                int i = 0;
                this.zzawg = false;
                zzgd();
                ArrayList arrayList = new ArrayList(this.zzawl);
                int size = arrayList.size();
                while (i < size) {
                    Object obj = arrayList.get(i);
                    i++;
                    zzb((zzgq) obj);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean zze(Map<String, String> map) {
        if (map == null) {
            return false;
        }
        String str = map.get("hashCode");
        return !TextUtils.isEmpty(str) && str.equals(this.zzavy.zzfy());
    }

    /* access modifiers changed from: package-private */
    public final void zzf(Map<String, String> map) {
        zzl(3);
    }

    /* access modifiers changed from: package-private */
    public final void zzg(Map<String, String> map) {
        if (map.containsKey("isVisible")) {
            boolean z = "1".equals(map.get("isVisible")) || "true".equals(map.get("isVisible"));
            Iterator<zzfs> it = this.zzawk.iterator();
            while (it.hasNext()) {
                it.next().zza(this, z);
            }
        }
    }

    public final void zzgb() {
        this.zzawo = zzaja.zzav(this.mApplicationContext);
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0036 A[Catch:{ JSONException -> 0x0020, RuntimeException -> 0x0019 }] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x003b A[Catch:{ JSONException -> 0x0020, RuntimeException -> 0x0019 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzgc() {
        /*
            r5 = this;
            java.lang.Object r0 = r5.mLock
            monitor-enter(r0)
            boolean r1 = r5.zzawg     // Catch:{ all -> 0x0046 }
            if (r1 == 0) goto L_0x0044
            r1 = 1
            r5.zzawh = r1     // Catch:{ all -> 0x0046 }
            org.json.JSONObject r2 = r5.zzgg()     // Catch:{ JSONException -> 0x0020, RuntimeException -> 0x0019 }
            java.lang.String r3 = "doneReasonCode"
            java.lang.String r4 = "u"
            r2.put(r3, r4)     // Catch:{ JSONException -> 0x0020, RuntimeException -> 0x0019 }
            r5.zza((org.json.JSONObject) r2, (boolean) r1)     // Catch:{ JSONException -> 0x0020, RuntimeException -> 0x0019 }
            goto L_0x0024
        L_0x0019:
            r1 = move-exception
            java.lang.String r2 = "Failure while processing active view data."
        L_0x001c:
            com.google.android.gms.internal.zzahw.zzb(r2, r1)     // Catch:{ all -> 0x0046 }
            goto L_0x0024
        L_0x0020:
            r1 = move-exception
            java.lang.String r2 = "JSON failure while processing active view data."
            goto L_0x001c
        L_0x0024:
            java.lang.String r1 = "Untracking ad unit: "
            com.google.android.gms.internal.zzft r2 = r5.zzavy     // Catch:{ all -> 0x0046 }
            java.lang.String r2 = r2.zzfy()     // Catch:{ all -> 0x0046 }
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ all -> 0x0046 }
            int r3 = r2.length()     // Catch:{ all -> 0x0046 }
            if (r3 == 0) goto L_0x003b
            java.lang.String r1 = r1.concat(r2)     // Catch:{ all -> 0x0046 }
            goto L_0x0041
        L_0x003b:
            java.lang.String r2 = new java.lang.String     // Catch:{ all -> 0x0046 }
            r2.<init>(r1)     // Catch:{ all -> 0x0046 }
            r1 = r2
        L_0x0041:
            com.google.android.gms.internal.zzahw.zzby(r1)     // Catch:{ all -> 0x0046 }
        L_0x0044:
            monitor-exit(r0)     // Catch:{ all -> 0x0046 }
            return
        L_0x0046:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0046 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzfv.zzgc():void");
    }

    public final boolean zzge() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzawg;
        }
        return z;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x00cf, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzl(int r8) {
        /*
            r7 = this;
            java.lang.Object r0 = r7.mLock
            monitor-enter(r0)
            java.util.HashSet<com.google.android.gms.internal.zzgq> r1 = r7.zzawl     // Catch:{ all -> 0x00d0 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x00d0 }
        L_0x0009:
            boolean r2 = r1.hasNext()     // Catch:{ all -> 0x00d0 }
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L_0x001f
            java.lang.Object r2 = r1.next()     // Catch:{ all -> 0x00d0 }
            com.google.android.gms.internal.zzgq r2 = (com.google.android.gms.internal.zzgq) r2     // Catch:{ all -> 0x00d0 }
            boolean r2 = r2.zzgk()     // Catch:{ all -> 0x00d0 }
            if (r2 == 0) goto L_0x0009
            r1 = 1
            goto L_0x0020
        L_0x001f:
            r1 = 0
        L_0x0020:
            if (r1 == 0) goto L_0x00ce
            boolean r1 = r7.zzawg     // Catch:{ all -> 0x00d0 }
            if (r1 != 0) goto L_0x0028
            goto L_0x00ce
        L_0x0028:
            com.google.android.gms.internal.zzhf r1 = r7.zzavx     // Catch:{ all -> 0x00d0 }
            android.view.View r1 = r1.zzgh()     // Catch:{ all -> 0x00d0 }
            if (r1 == 0) goto L_0x0040
            com.google.android.gms.internal.zzaij r2 = com.google.android.gms.ads.internal.zzbt.zzel()     // Catch:{ all -> 0x00d0 }
            android.os.PowerManager r5 = r7.zzawa     // Catch:{ all -> 0x00d0 }
            android.app.KeyguardManager r6 = r7.zzawb     // Catch:{ all -> 0x00d0 }
            boolean r2 = r2.zza((android.view.View) r1, (android.os.PowerManager) r5, (android.app.KeyguardManager) r6)     // Catch:{ all -> 0x00d0 }
            if (r2 == 0) goto L_0x0040
            r2 = 1
            goto L_0x0041
        L_0x0040:
            r2 = 0
        L_0x0041:
            if (r1 == 0) goto L_0x0053
            if (r2 == 0) goto L_0x0053
            android.graphics.Rect r5 = new android.graphics.Rect     // Catch:{ all -> 0x00d0 }
            r5.<init>()     // Catch:{ all -> 0x00d0 }
            r6 = 0
            boolean r5 = r1.getGlobalVisibleRect(r5, r6)     // Catch:{ all -> 0x00d0 }
            if (r5 == 0) goto L_0x0053
            r5 = 1
            goto L_0x0054
        L_0x0053:
            r5 = 0
        L_0x0054:
            com.google.android.gms.internal.zzhf r6 = r7.zzavx     // Catch:{ all -> 0x00d0 }
            boolean r6 = r6.zzgi()     // Catch:{ all -> 0x00d0 }
            if (r6 == 0) goto L_0x0061
            r7.zzgc()     // Catch:{ all -> 0x00d0 }
            monitor-exit(r0)     // Catch:{ all -> 0x00d0 }
            return
        L_0x0061:
            if (r8 != r4) goto L_0x0071
            com.google.android.gms.internal.zzake r6 = r7.zzavf     // Catch:{ all -> 0x00d0 }
            boolean r6 = r6.tryAcquire()     // Catch:{ all -> 0x00d0 }
            if (r6 != 0) goto L_0x0071
            boolean r6 = r7.zzawi     // Catch:{ all -> 0x00d0 }
            if (r5 != r6) goto L_0x0071
            monitor-exit(r0)     // Catch:{ all -> 0x00d0 }
            return
        L_0x0071:
            if (r5 != 0) goto L_0x007b
            boolean r6 = r7.zzawi     // Catch:{ all -> 0x00d0 }
            if (r6 != 0) goto L_0x007b
            if (r8 != r4) goto L_0x007b
            monitor-exit(r0)     // Catch:{ all -> 0x00d0 }
            return
        L_0x007b:
            java.lang.Boolean r8 = java.lang.Boolean.valueOf(r2)     // Catch:{ JSONException -> 0x008b, RuntimeException -> 0x0089 }
            org.json.JSONObject r8 = r7.zza((android.view.View) r1, (java.lang.Boolean) r8)     // Catch:{ JSONException -> 0x008b, RuntimeException -> 0x0089 }
            r7.zza((org.json.JSONObject) r8, (boolean) r3)     // Catch:{ JSONException -> 0x008b, RuntimeException -> 0x0089 }
            r7.zzawi = r5     // Catch:{ JSONException -> 0x008b, RuntimeException -> 0x0089 }
            goto L_0x0091
        L_0x0089:
            r8 = move-exception
            goto L_0x008c
        L_0x008b:
            r8 = move-exception
        L_0x008c:
            java.lang.String r1 = "Active view update failed."
            com.google.android.gms.internal.zzahw.zza(r1, r8)     // Catch:{ all -> 0x00d0 }
        L_0x0091:
            com.google.android.gms.internal.zzhf r8 = r7.zzavx     // Catch:{ all -> 0x00d0 }
            com.google.android.gms.internal.zzhf r8 = r8.zzgj()     // Catch:{ all -> 0x00d0 }
            android.view.View r8 = r8.zzgh()     // Catch:{ all -> 0x00d0 }
            if (r8 == 0) goto L_0x00c9
            java.lang.ref.WeakReference<android.view.ViewTreeObserver> r1 = r7.zzavw     // Catch:{ all -> 0x00d0 }
            java.lang.Object r1 = r1.get()     // Catch:{ all -> 0x00d0 }
            android.view.ViewTreeObserver r1 = (android.view.ViewTreeObserver) r1     // Catch:{ all -> 0x00d0 }
            android.view.ViewTreeObserver r8 = r8.getViewTreeObserver()     // Catch:{ all -> 0x00d0 }
            if (r8 == r1) goto L_0x00c9
            r7.zzgf()     // Catch:{ all -> 0x00d0 }
            boolean r2 = r7.zzawe     // Catch:{ all -> 0x00d0 }
            if (r2 == 0) goto L_0x00ba
            if (r1 == 0) goto L_0x00c2
            boolean r1 = r1.isAlive()     // Catch:{ all -> 0x00d0 }
            if (r1 == 0) goto L_0x00c2
        L_0x00ba:
            r7.zzawe = r4     // Catch:{ all -> 0x00d0 }
            r8.addOnScrollChangedListener(r7)     // Catch:{ all -> 0x00d0 }
            r8.addOnGlobalLayoutListener(r7)     // Catch:{ all -> 0x00d0 }
        L_0x00c2:
            java.lang.ref.WeakReference r1 = new java.lang.ref.WeakReference     // Catch:{ all -> 0x00d0 }
            r1.<init>(r8)     // Catch:{ all -> 0x00d0 }
            r7.zzavw = r1     // Catch:{ all -> 0x00d0 }
        L_0x00c9:
            r7.zzgd()     // Catch:{ all -> 0x00d0 }
            monitor-exit(r0)     // Catch:{ all -> 0x00d0 }
            return
        L_0x00ce:
            monitor-exit(r0)     // Catch:{ all -> 0x00d0 }
            return
        L_0x00d0:
            r8 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00d0 }
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzfv.zzl(int):void");
    }
}
