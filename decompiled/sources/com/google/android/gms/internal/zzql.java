package com.google.android.gms.internal;

import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.internal.zzbt;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

@zzabh
public final class zzql extends zzrc implements View.OnClickListener, View.OnTouchListener, ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener {
    static final String[] zzbzo = {NativeAppInstallAd.ASSET_MEDIA_VIDEO, NativeContentAd.ASSET_MEDIA_VIDEO};
    private final Object mLock = new Object();
    private zzpv zzbyi;
    private View zzbzt;
    private Point zzbzv;
    private Point zzbzw;
    private WeakReference<zzgr> zzbzx;
    private final WeakReference<View> zzcaa;
    private final Map<String, WeakReference<View>> zzcab;
    private final Map<String, WeakReference<View>> zzcac;
    private final Map<String, WeakReference<View>> zzcad;

    public zzql(View view, HashMap<String, View> hashMap, HashMap<String, View> hashMap2) {
        HashMap hashMap3 = new HashMap();
        this.zzcab = hashMap3;
        HashMap hashMap4 = new HashMap();
        this.zzcac = hashMap4;
        HashMap hashMap5 = new HashMap();
        this.zzcad = hashMap5;
        this.zzbzv = new Point();
        this.zzbzw = new Point();
        this.zzbzx = new WeakReference<>((zzgr) null);
        zzbt.zzfg();
        zzaml.zza(view, (ViewTreeObserver.OnGlobalLayoutListener) this);
        zzbt.zzfg();
        zzaml.zza(view, (ViewTreeObserver.OnScrollChangedListener) this);
        view.setOnTouchListener(this);
        view.setOnClickListener(this);
        this.zzcaa = new WeakReference<>(view);
        zzi(hashMap);
        hashMap5.putAll(hashMap3);
        zzj(hashMap2);
        hashMap5.putAll(hashMap4);
        zzoi.initialize(view.getContext());
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x003a, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.gms.internal.zzpz r7) {
        /*
            r6 = this;
            java.lang.Object r0 = r6.mLock
            monitor-enter(r0)
            java.lang.String[] r1 = zzbzo     // Catch:{ all -> 0x003b }
            int r2 = r1.length     // Catch:{ all -> 0x003b }
            r3 = 0
        L_0x0007:
            if (r3 >= r2) goto L_0x001f
            r4 = r1[r3]     // Catch:{ all -> 0x003b }
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r5 = r6.zzcad     // Catch:{ all -> 0x003b }
            java.lang.Object r4 = r5.get(r4)     // Catch:{ all -> 0x003b }
            java.lang.ref.WeakReference r4 = (java.lang.ref.WeakReference) r4     // Catch:{ all -> 0x003b }
            if (r4 == 0) goto L_0x001c
            java.lang.Object r1 = r4.get()     // Catch:{ all -> 0x003b }
            android.view.View r1 = (android.view.View) r1     // Catch:{ all -> 0x003b }
            goto L_0x0020
        L_0x001c:
            int r3 = r3 + 1
            goto L_0x0007
        L_0x001f:
            r1 = 0
        L_0x0020:
            boolean r2 = r1 instanceof android.widget.FrameLayout     // Catch:{ all -> 0x003b }
            if (r2 != 0) goto L_0x0029
            r7.zzks()     // Catch:{ all -> 0x003b }
            monitor-exit(r0)     // Catch:{ all -> 0x003b }
            return
        L_0x0029:
            com.google.android.gms.internal.zzqn r2 = new com.google.android.gms.internal.zzqn     // Catch:{ all -> 0x003b }
            r2.<init>(r6, r1)     // Catch:{ all -> 0x003b }
            boolean r3 = r7 instanceof com.google.android.gms.internal.zzpu     // Catch:{ all -> 0x003b }
            if (r3 == 0) goto L_0x0036
            r7.zzb((android.view.View) r1, (com.google.android.gms.internal.zzpt) r2)     // Catch:{ all -> 0x003b }
            goto L_0x0039
        L_0x0036:
            r7.zza((android.view.View) r1, (com.google.android.gms.internal.zzpt) r2)     // Catch:{ all -> 0x003b }
        L_0x0039:
            monitor-exit(r0)     // Catch:{ all -> 0x003b }
            return
        L_0x003b:
            r7 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x003b }
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzql.zza(com.google.android.gms.internal.zzpz):void");
    }

    /* access modifiers changed from: private */
    public final boolean zza(String[] strArr) {
        for (String str : strArr) {
            if (this.zzcab.get(str) != null) {
                return true;
            }
        }
        for (String str2 : strArr) {
            if (this.zzcac.get(str2) != null) {
                return false;
            }
        }
        return false;
    }

    private final void zzi(Map<String, View> map) {
        for (Map.Entry next : map.entrySet()) {
            String str = (String) next.getKey();
            View view = (View) next.getValue();
            if (view != null) {
                this.zzcab.put(str, new WeakReference(view));
                if (!NativeAd.ASSET_ADCHOICES_CONTAINER_VIEW.equals(str)) {
                    view.setOnTouchListener(this);
                    view.setClickable(true);
                    view.setOnClickListener(this);
                }
            }
        }
    }

    private final void zzj(Map<String, View> map) {
        for (Map.Entry next : map.entrySet()) {
            View view = (View) next.getValue();
            if (view != null) {
                this.zzcac.put((String) next.getKey(), new WeakReference(view));
                view.setOnTouchListener(this);
            }
        }
    }

    private final void zzk(View view) {
        synchronized (this.mLock) {
            zzpv zzpv = this.zzbyi;
            if (zzpv != null) {
                if (zzpv instanceof zzpu) {
                    zzpv = ((zzpu) zzpv).zzkp();
                }
                if (zzpv != null) {
                    zzpv.zzk(view);
                }
            }
        }
    }

    private final int zzt(int i) {
        int zzb;
        synchronized (this.mLock) {
            zzlc.zzij();
            zzb = zzako.zzb(this.zzbyi.getContext(), i);
        }
        return zzb;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0089, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onClick(android.view.View r9) {
        /*
            r8 = this;
            java.lang.Object r0 = r8.mLock
            monitor-enter(r0)
            com.google.android.gms.internal.zzpv r1 = r8.zzbyi     // Catch:{ all -> 0x008a }
            if (r1 != 0) goto L_0x0009
            monitor-exit(r0)     // Catch:{ all -> 0x008a }
            return
        L_0x0009:
            java.lang.ref.WeakReference<android.view.View> r1 = r8.zzcaa     // Catch:{ all -> 0x008a }
            java.lang.Object r1 = r1.get()     // Catch:{ all -> 0x008a }
            r7 = r1
            android.view.View r7 = (android.view.View) r7     // Catch:{ all -> 0x008a }
            if (r7 != 0) goto L_0x0016
            monitor-exit(r0)     // Catch:{ all -> 0x008a }
            return
        L_0x0016:
            android.os.Bundle r5 = new android.os.Bundle     // Catch:{ all -> 0x008a }
            r5.<init>()     // Catch:{ all -> 0x008a }
            java.lang.String r1 = "x"
            android.graphics.Point r2 = r8.zzbzv     // Catch:{ all -> 0x008a }
            int r2 = r2.x     // Catch:{ all -> 0x008a }
            int r2 = r8.zzt(r2)     // Catch:{ all -> 0x008a }
            float r2 = (float) r2     // Catch:{ all -> 0x008a }
            r5.putFloat(r1, r2)     // Catch:{ all -> 0x008a }
            java.lang.String r1 = "y"
            android.graphics.Point r2 = r8.zzbzv     // Catch:{ all -> 0x008a }
            int r2 = r2.y     // Catch:{ all -> 0x008a }
            int r2 = r8.zzt(r2)     // Catch:{ all -> 0x008a }
            float r2 = (float) r2     // Catch:{ all -> 0x008a }
            r5.putFloat(r1, r2)     // Catch:{ all -> 0x008a }
            java.lang.String r1 = "start_x"
            android.graphics.Point r2 = r8.zzbzw     // Catch:{ all -> 0x008a }
            int r2 = r2.x     // Catch:{ all -> 0x008a }
            int r2 = r8.zzt(r2)     // Catch:{ all -> 0x008a }
            float r2 = (float) r2     // Catch:{ all -> 0x008a }
            r5.putFloat(r1, r2)     // Catch:{ all -> 0x008a }
            java.lang.String r1 = "start_y"
            android.graphics.Point r2 = r8.zzbzw     // Catch:{ all -> 0x008a }
            int r2 = r2.y     // Catch:{ all -> 0x008a }
            int r2 = r8.zzt(r2)     // Catch:{ all -> 0x008a }
            float r2 = (float) r2     // Catch:{ all -> 0x008a }
            r5.putFloat(r1, r2)     // Catch:{ all -> 0x008a }
            android.view.View r1 = r8.zzbzt     // Catch:{ all -> 0x008a }
            if (r1 == 0) goto L_0x0081
            boolean r1 = r1.equals(r9)     // Catch:{ all -> 0x008a }
            if (r1 == 0) goto L_0x0081
            com.google.android.gms.internal.zzpv r2 = r8.zzbyi     // Catch:{ all -> 0x008a }
            boolean r1 = r2 instanceof com.google.android.gms.internal.zzpu     // Catch:{ all -> 0x008a }
            if (r1 == 0) goto L_0x007c
            com.google.android.gms.internal.zzpu r2 = (com.google.android.gms.internal.zzpu) r2     // Catch:{ all -> 0x008a }
            com.google.android.gms.internal.zzpv r1 = r2.zzkp()     // Catch:{ all -> 0x008a }
            if (r1 == 0) goto L_0x0088
            com.google.android.gms.internal.zzpv r1 = r8.zzbyi     // Catch:{ all -> 0x008a }
            com.google.android.gms.internal.zzpu r1 = (com.google.android.gms.internal.zzpu) r1     // Catch:{ all -> 0x008a }
            com.google.android.gms.internal.zzpv r2 = r1.zzkp()     // Catch:{ all -> 0x008a }
            java.lang.String r4 = "1007"
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r6 = r8.zzcad     // Catch:{ all -> 0x008a }
        L_0x0077:
            r3 = r9
            r2.zza(r3, r4, r5, r6, r7)     // Catch:{ all -> 0x008a }
            goto L_0x0088
        L_0x007c:
            java.lang.String r4 = "1007"
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r6 = r8.zzcad     // Catch:{ all -> 0x008a }
            goto L_0x0077
        L_0x0081:
            com.google.android.gms.internal.zzpv r1 = r8.zzbyi     // Catch:{ all -> 0x008a }
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r2 = r8.zzcad     // Catch:{ all -> 0x008a }
            r1.zza(r9, r2, r5, r7)     // Catch:{ all -> 0x008a }
        L_0x0088:
            monitor-exit(r0)     // Catch:{ all -> 0x008a }
            return
        L_0x008a:
            r9 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x008a }
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzql.onClick(android.view.View):void");
    }

    public final void onGlobalLayout() {
        View view;
        synchronized (this.mLock) {
            if (!(this.zzbyi == null || (view = (View) this.zzcaa.get()) == null)) {
                this.zzbyi.zzc(view, this.zzcad);
            }
        }
    }

    public final void onScrollChanged() {
        View view;
        synchronized (this.mLock) {
            if (!(this.zzbyi == null || (view = (View) this.zzcaa.get()) == null)) {
                this.zzbyi.zzc(view, this.zzcad);
            }
        }
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        synchronized (this.mLock) {
            if (this.zzbyi == null) {
                return false;
            }
            View view2 = (View) this.zzcaa.get();
            if (view2 == null) {
                return false;
            }
            int[] iArr = new int[2];
            view2.getLocationOnScreen(iArr);
            Point point = new Point((int) (motionEvent.getRawX() - ((float) iArr[0])), (int) (motionEvent.getRawY() - ((float) iArr[1])));
            this.zzbzv = point;
            if (motionEvent.getAction() == 0) {
                this.zzbzw = point;
            }
            MotionEvent obtain = MotionEvent.obtain(motionEvent);
            obtain.setLocation((float) point.x, (float) point.y);
            this.zzbyi.zzd(obtain);
            obtain.recycle();
            return false;
        }
    }

    public final void unregisterNativeAd() {
        synchronized (this.mLock) {
            this.zzbzt = null;
            this.zzbyi = null;
            this.zzbzv = null;
            this.zzbzw = null;
        }
    }

    /* JADX WARNING: type inference failed for: r2v18, types: [android.view.View] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.gms.dynamic.IObjectWrapper r9) {
        /*
            r8 = this;
            java.lang.Object r0 = r8.mLock
            monitor-enter(r0)
            r1 = 0
            r8.zzk(r1)     // Catch:{ all -> 0x0178 }
            java.lang.Object r9 = com.google.android.gms.dynamic.zzn.zzy(r9)     // Catch:{ all -> 0x0178 }
            boolean r2 = r9 instanceof com.google.android.gms.internal.zzpz     // Catch:{ all -> 0x0178 }
            if (r2 != 0) goto L_0x0016
            java.lang.String r9 = "Not an instance of native engine. This is most likely a transient error"
            com.google.android.gms.internal.zzahw.zzcz(r9)     // Catch:{ all -> 0x0178 }
            monitor-exit(r0)     // Catch:{ all -> 0x0178 }
            return
        L_0x0016:
            com.google.android.gms.internal.zzpz r9 = (com.google.android.gms.internal.zzpz) r9     // Catch:{ all -> 0x0178 }
            boolean r2 = r9.zzkn()     // Catch:{ all -> 0x0178 }
            if (r2 != 0) goto L_0x0025
            java.lang.String r9 = "Your account must be enabled to use this feature. Talk to your account manager to request this feature for your account."
            com.google.android.gms.internal.zzahw.e(r9)     // Catch:{ all -> 0x0178 }
            monitor-exit(r0)     // Catch:{ all -> 0x0178 }
            return
        L_0x0025:
            java.lang.ref.WeakReference<android.view.View> r2 = r8.zzcaa     // Catch:{ all -> 0x0178 }
            java.lang.Object r2 = r2.get()     // Catch:{ all -> 0x0178 }
            r7 = r2
            android.view.View r7 = (android.view.View) r7     // Catch:{ all -> 0x0178 }
            com.google.android.gms.internal.zzpv r2 = r8.zzbyi     // Catch:{ all -> 0x0178 }
            if (r2 == 0) goto L_0x004d
            if (r7 == 0) goto L_0x004d
            com.google.android.gms.internal.zzny<java.lang.Boolean> r2 = com.google.android.gms.internal.zzoi.zzbsn     // Catch:{ all -> 0x0178 }
            com.google.android.gms.internal.zzog r3 = com.google.android.gms.internal.zzlc.zzio()     // Catch:{ all -> 0x0178 }
            java.lang.Object r2 = r3.zzd(r2)     // Catch:{ all -> 0x0178 }
            java.lang.Boolean r2 = (java.lang.Boolean) r2     // Catch:{ all -> 0x0178 }
            boolean r2 = r2.booleanValue()     // Catch:{ all -> 0x0178 }
            if (r2 == 0) goto L_0x004d
            com.google.android.gms.internal.zzpv r2 = r8.zzbyi     // Catch:{ all -> 0x0178 }
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r3 = r8.zzcad     // Catch:{ all -> 0x0178 }
            r2.zzb(r7, r3)     // Catch:{ all -> 0x0178 }
        L_0x004d:
            java.lang.Object r2 = r8.mLock     // Catch:{ all -> 0x0178 }
            monitor-enter(r2)     // Catch:{ all -> 0x0178 }
            com.google.android.gms.internal.zzpv r3 = r8.zzbyi     // Catch:{ all -> 0x0175 }
            boolean r4 = r3 instanceof com.google.android.gms.internal.zzpz     // Catch:{ all -> 0x0175 }
            if (r4 != 0) goto L_0x0058
        L_0x0056:
            monitor-exit(r2)     // Catch:{ all -> 0x0175 }
            goto L_0x0094
        L_0x0058:
            com.google.android.gms.internal.zzpz r3 = (com.google.android.gms.internal.zzpz) r3     // Catch:{ all -> 0x0175 }
            java.lang.ref.WeakReference<android.view.View> r4 = r8.zzcaa     // Catch:{ all -> 0x0175 }
            java.lang.Object r4 = r4.get()     // Catch:{ all -> 0x0175 }
            android.view.View r4 = (android.view.View) r4     // Catch:{ all -> 0x0175 }
            if (r3 == 0) goto L_0x0056
            android.content.Context r5 = r3.getContext()     // Catch:{ all -> 0x0175 }
            if (r5 == 0) goto L_0x0056
            if (r4 == 0) goto L_0x0056
            com.google.android.gms.internal.zzagu r5 = com.google.android.gms.ads.internal.zzbt.zzfh()     // Catch:{ all -> 0x0175 }
            android.content.Context r4 = r4.getContext()     // Catch:{ all -> 0x0175 }
            boolean r4 = r5.zzs(r4)     // Catch:{ all -> 0x0175 }
            if (r4 == 0) goto L_0x0056
            com.google.android.gms.internal.zzagt r3 = r3.zzku()     // Catch:{ all -> 0x0175 }
            if (r3 == 0) goto L_0x0084
            r4 = 0
            r3.zzw(r4)     // Catch:{ all -> 0x0175 }
        L_0x0084:
            java.lang.ref.WeakReference<com.google.android.gms.internal.zzgr> r4 = r8.zzbzx     // Catch:{ all -> 0x0175 }
            java.lang.Object r4 = r4.get()     // Catch:{ all -> 0x0175 }
            com.google.android.gms.internal.zzgr r4 = (com.google.android.gms.internal.zzgr) r4     // Catch:{ all -> 0x0175 }
            if (r4 == 0) goto L_0x0056
            if (r3 == 0) goto L_0x0056
            r4.zzb(r3)     // Catch:{ all -> 0x0175 }
            goto L_0x0056
        L_0x0094:
            com.google.android.gms.internal.zzpv r2 = r8.zzbyi     // Catch:{ all -> 0x0178 }
            boolean r3 = r2 instanceof com.google.android.gms.internal.zzpu     // Catch:{ all -> 0x0178 }
            if (r3 == 0) goto L_0x00aa
            com.google.android.gms.internal.zzpu r2 = (com.google.android.gms.internal.zzpu) r2     // Catch:{ all -> 0x0178 }
            boolean r2 = r2.zzko()     // Catch:{ all -> 0x0178 }
            if (r2 == 0) goto L_0x00aa
            com.google.android.gms.internal.zzpv r2 = r8.zzbyi     // Catch:{ all -> 0x0178 }
            com.google.android.gms.internal.zzpu r2 = (com.google.android.gms.internal.zzpu) r2     // Catch:{ all -> 0x0178 }
            r2.zzc(r9)     // Catch:{ all -> 0x0178 }
            goto L_0x00b6
        L_0x00aa:
            r8.zzbyi = r9     // Catch:{ all -> 0x0178 }
            boolean r2 = r9 instanceof com.google.android.gms.internal.zzpu     // Catch:{ all -> 0x0178 }
            if (r2 == 0) goto L_0x00b6
            r2 = r9
            com.google.android.gms.internal.zzpu r2 = (com.google.android.gms.internal.zzpu) r2     // Catch:{ all -> 0x0178 }
            r2.zzc(r1)     // Catch:{ all -> 0x0178 }
        L_0x00b6:
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r2 = r8.zzcad     // Catch:{ all -> 0x0178 }
            java.lang.String r3 = "1098"
            java.lang.Object r2 = r2.get(r3)     // Catch:{ all -> 0x0178 }
            java.lang.ref.WeakReference r2 = (java.lang.ref.WeakReference) r2     // Catch:{ all -> 0x0178 }
            if (r2 != 0) goto L_0x00c8
            java.lang.String r1 = "Ad choices asset view is not provided."
            com.google.android.gms.internal.zzahw.zzcz(r1)     // Catch:{ all -> 0x0178 }
            goto L_0x0104
        L_0x00c8:
            java.lang.Object r2 = r2.get()     // Catch:{ all -> 0x0178 }
            android.view.View r2 = (android.view.View) r2     // Catch:{ all -> 0x0178 }
            boolean r3 = r2 instanceof android.view.ViewGroup     // Catch:{ all -> 0x0178 }
            if (r3 == 0) goto L_0x00d5
            r1 = r2
            android.view.ViewGroup r1 = (android.view.ViewGroup) r1     // Catch:{ all -> 0x0178 }
        L_0x00d5:
            if (r1 == 0) goto L_0x0104
            r2 = 1
            android.view.View r2 = r9.zza((android.view.View.OnClickListener) r8, (boolean) r2)     // Catch:{ all -> 0x0178 }
            r8.zzbzt = r2     // Catch:{ all -> 0x0178 }
            if (r2 == 0) goto L_0x0104
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r2 = r8.zzcad     // Catch:{ all -> 0x0178 }
            java.lang.String r3 = "1007"
            java.lang.ref.WeakReference r4 = new java.lang.ref.WeakReference     // Catch:{ all -> 0x0178 }
            android.view.View r5 = r8.zzbzt     // Catch:{ all -> 0x0178 }
            r4.<init>(r5)     // Catch:{ all -> 0x0178 }
            r2.put(r3, r4)     // Catch:{ all -> 0x0178 }
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r2 = r8.zzcab     // Catch:{ all -> 0x0178 }
            java.lang.String r3 = "1007"
            java.lang.ref.WeakReference r4 = new java.lang.ref.WeakReference     // Catch:{ all -> 0x0178 }
            android.view.View r5 = r8.zzbzt     // Catch:{ all -> 0x0178 }
            r4.<init>(r5)     // Catch:{ all -> 0x0178 }
            r2.put(r3, r4)     // Catch:{ all -> 0x0178 }
            r1.removeAllViews()     // Catch:{ all -> 0x0178 }
            android.view.View r2 = r8.zzbzt     // Catch:{ all -> 0x0178 }
            r1.addView(r2)     // Catch:{ all -> 0x0178 }
        L_0x0104:
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r3 = r8.zzcab     // Catch:{ all -> 0x0178 }
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r4 = r8.zzcac     // Catch:{ all -> 0x0178 }
            r1 = r9
            r2 = r7
            r5 = r8
            r6 = r8
            r1.zza((android.view.View) r2, (java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>>) r3, (java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>>) r4, (android.view.View.OnTouchListener) r5, (android.view.View.OnClickListener) r6)     // Catch:{ all -> 0x0178 }
            android.os.Handler r1 = com.google.android.gms.internal.zzaij.zzdfn     // Catch:{ all -> 0x0178 }
            com.google.android.gms.internal.zzqm r2 = new com.google.android.gms.internal.zzqm     // Catch:{ all -> 0x0178 }
            r2.<init>(r8, r9)     // Catch:{ all -> 0x0178 }
            r1.post(r2)     // Catch:{ all -> 0x0178 }
            r8.zzk(r7)     // Catch:{ all -> 0x0178 }
            com.google.android.gms.internal.zzpv r9 = r8.zzbyi     // Catch:{ all -> 0x0178 }
            r9.zzi(r7)     // Catch:{ all -> 0x0178 }
            java.lang.Object r9 = r8.mLock     // Catch:{ all -> 0x0178 }
            monitor-enter(r9)     // Catch:{ all -> 0x0178 }
            com.google.android.gms.internal.zzpv r1 = r8.zzbyi     // Catch:{ all -> 0x0172 }
            boolean r2 = r1 instanceof com.google.android.gms.internal.zzpz     // Catch:{ all -> 0x0172 }
            if (r2 != 0) goto L_0x012c
        L_0x012a:
            monitor-exit(r9)     // Catch:{ all -> 0x0172 }
            goto L_0x0170
        L_0x012c:
            com.google.android.gms.internal.zzpz r1 = (com.google.android.gms.internal.zzpz) r1     // Catch:{ all -> 0x0172 }
            java.lang.ref.WeakReference<android.view.View> r2 = r8.zzcaa     // Catch:{ all -> 0x0172 }
            java.lang.Object r2 = r2.get()     // Catch:{ all -> 0x0172 }
            android.view.View r2 = (android.view.View) r2     // Catch:{ all -> 0x0172 }
            if (r1 == 0) goto L_0x012a
            android.content.Context r3 = r1.getContext()     // Catch:{ all -> 0x0172 }
            if (r3 == 0) goto L_0x012a
            if (r2 == 0) goto L_0x012a
            com.google.android.gms.internal.zzagu r3 = com.google.android.gms.ads.internal.zzbt.zzfh()     // Catch:{ all -> 0x0172 }
            android.content.Context r4 = r2.getContext()     // Catch:{ all -> 0x0172 }
            boolean r3 = r3.zzs(r4)     // Catch:{ all -> 0x0172 }
            if (r3 == 0) goto L_0x012a
            java.lang.ref.WeakReference<com.google.android.gms.internal.zzgr> r3 = r8.zzbzx     // Catch:{ all -> 0x0172 }
            java.lang.Object r3 = r3.get()     // Catch:{ all -> 0x0172 }
            com.google.android.gms.internal.zzgr r3 = (com.google.android.gms.internal.zzgr) r3     // Catch:{ all -> 0x0172 }
            if (r3 != 0) goto L_0x0168
            com.google.android.gms.internal.zzgr r3 = new com.google.android.gms.internal.zzgr     // Catch:{ all -> 0x0172 }
            android.content.Context r4 = r2.getContext()     // Catch:{ all -> 0x0172 }
            r3.<init>(r4, r2)     // Catch:{ all -> 0x0172 }
            java.lang.ref.WeakReference r2 = new java.lang.ref.WeakReference     // Catch:{ all -> 0x0172 }
            r2.<init>(r3)     // Catch:{ all -> 0x0172 }
            r8.zzbzx = r2     // Catch:{ all -> 0x0172 }
        L_0x0168:
            com.google.android.gms.internal.zzagt r1 = r1.zzku()     // Catch:{ all -> 0x0172 }
            r3.zza((com.google.android.gms.internal.zzgv) r1)     // Catch:{ all -> 0x0172 }
            goto L_0x012a
        L_0x0170:
            monitor-exit(r0)     // Catch:{ all -> 0x0178 }
            return
        L_0x0172:
            r1 = move-exception
            monitor-exit(r9)     // Catch:{ all -> 0x0172 }
            throw r1     // Catch:{ all -> 0x0178 }
        L_0x0175:
            r9 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0175 }
            throw r9     // Catch:{ all -> 0x0178 }
        L_0x0178:
            r9 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0178 }
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzql.zza(com.google.android.gms.dynamic.IObjectWrapper):void");
    }
}
