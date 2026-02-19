package com.google.android.gms.internal;

import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.common.util.zzs;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@zzabh
public final class zzqj extends zzqx implements View.OnClickListener, View.OnTouchListener, ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener {
    private static String[] zzbzo = {NativeAppInstallAd.ASSET_MEDIA_VIDEO, NativeContentAd.ASSET_MEDIA_VIDEO, "3010"};
    private final Object mLock = new Object();
    private FrameLayout zzamk;
    private zzpv zzbyi;
    private final FrameLayout zzbzp;
    private View zzbzq;
    private final boolean zzbzr;
    private Map<String, WeakReference<View>> zzbzs = Collections.synchronizedMap(new HashMap());
    private View zzbzt;
    private boolean zzbzu = false;
    private Point zzbzv = new Point();
    private Point zzbzw = new Point();
    private WeakReference<zzgr> zzbzx = new WeakReference<zzgr>(null);

    public zzqj(FrameLayout frameLayout, FrameLayout frameLayout2) {
        this.zzbzp = frameLayout;
        this.zzamk = frameLayout2;
        zzbt.zzfg();
        zzaml.zza((View) frameLayout, (ViewTreeObserver.OnGlobalLayoutListener) this);
        zzbt.zzfg();
        zzaml.zza((View) frameLayout, (ViewTreeObserver.OnScrollChangedListener) this);
        frameLayout.setOnTouchListener(this);
        frameLayout.setOnClickListener(this);
        if (frameLayout2 != null && zzs.zzanx()) {
            frameLayout2.setElevation(Float.MAX_VALUE);
        }
        zzoi.initialize(frameLayout.getContext());
        this.zzbzr = ((Boolean) zzlc.zzio().zzd(zzoi.zzbsv)).booleanValue();
    }

    private final void zzk(View view) {
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

    private final View zzkv() {
        if (this.zzbzs == null) {
            return null;
        }
        for (String str : zzbzo) {
            WeakReference weakReference = this.zzbzs.get(str);
            if (weakReference != null) {
                return (View) weakReference.get();
            }
        }
        return null;
    }

    private final void zzkw() {
        FrameLayout frameLayout;
        synchronized (this.mLock) {
            if (!this.zzbzr && this.zzbzu) {
                int measuredWidth = this.zzbzp.getMeasuredWidth();
                int measuredHeight = this.zzbzp.getMeasuredHeight();
                if (!(measuredWidth == 0 || measuredHeight == 0 || (frameLayout = this.zzamk) == null)) {
                    frameLayout.setLayoutParams(new FrameLayout.LayoutParams(measuredWidth, measuredHeight));
                    this.zzbzu = false;
                }
            }
        }
    }

    private final int zzt(int i) {
        zzlc.zzij();
        return zzako.zzb(this.zzbyi.getContext(), i);
    }

    public final void destroy() {
        synchronized (this.mLock) {
            FrameLayout frameLayout = this.zzamk;
            if (frameLayout != null) {
                frameLayout.removeAllViews();
            }
            this.zzamk = null;
            this.zzbzs = null;
            this.zzbzt = null;
            this.zzbyi = null;
            this.zzbzv = null;
            this.zzbzw = null;
            this.zzbzx = null;
            this.zzbzq = null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0082, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onClick(android.view.View r8) {
        /*
            r7 = this;
            java.lang.Object r0 = r7.mLock
            monitor-enter(r0)
            com.google.android.gms.internal.zzpv r1 = r7.zzbyi     // Catch:{ all -> 0x0083 }
            if (r1 != 0) goto L_0x0009
            monitor-exit(r0)     // Catch:{ all -> 0x0083 }
            return
        L_0x0009:
            android.os.Bundle r4 = new android.os.Bundle     // Catch:{ all -> 0x0083 }
            r4.<init>()     // Catch:{ all -> 0x0083 }
            java.lang.String r1 = "x"
            android.graphics.Point r2 = r7.zzbzv     // Catch:{ all -> 0x0083 }
            int r2 = r2.x     // Catch:{ all -> 0x0083 }
            int r2 = r7.zzt(r2)     // Catch:{ all -> 0x0083 }
            float r2 = (float) r2     // Catch:{ all -> 0x0083 }
            r4.putFloat(r1, r2)     // Catch:{ all -> 0x0083 }
            java.lang.String r1 = "y"
            android.graphics.Point r2 = r7.zzbzv     // Catch:{ all -> 0x0083 }
            int r2 = r2.y     // Catch:{ all -> 0x0083 }
            int r2 = r7.zzt(r2)     // Catch:{ all -> 0x0083 }
            float r2 = (float) r2     // Catch:{ all -> 0x0083 }
            r4.putFloat(r1, r2)     // Catch:{ all -> 0x0083 }
            java.lang.String r1 = "start_x"
            android.graphics.Point r2 = r7.zzbzw     // Catch:{ all -> 0x0083 }
            int r2 = r2.x     // Catch:{ all -> 0x0083 }
            int r2 = r7.zzt(r2)     // Catch:{ all -> 0x0083 }
            float r2 = (float) r2     // Catch:{ all -> 0x0083 }
            r4.putFloat(r1, r2)     // Catch:{ all -> 0x0083 }
            java.lang.String r1 = "start_y"
            android.graphics.Point r2 = r7.zzbzw     // Catch:{ all -> 0x0083 }
            int r2 = r2.y     // Catch:{ all -> 0x0083 }
            int r2 = r7.zzt(r2)     // Catch:{ all -> 0x0083 }
            float r2 = (float) r2     // Catch:{ all -> 0x0083 }
            r4.putFloat(r1, r2)     // Catch:{ all -> 0x0083 }
            android.view.View r1 = r7.zzbzt     // Catch:{ all -> 0x0083 }
            if (r1 == 0) goto L_0x0078
            boolean r1 = r1.equals(r8)     // Catch:{ all -> 0x0083 }
            if (r1 == 0) goto L_0x0078
            com.google.android.gms.internal.zzpv r1 = r7.zzbyi     // Catch:{ all -> 0x0083 }
            boolean r2 = r1 instanceof com.google.android.gms.internal.zzpu     // Catch:{ all -> 0x0083 }
            if (r2 == 0) goto L_0x0071
            com.google.android.gms.internal.zzpu r1 = (com.google.android.gms.internal.zzpu) r1     // Catch:{ all -> 0x0083 }
            com.google.android.gms.internal.zzpv r1 = r1.zzkp()     // Catch:{ all -> 0x0083 }
            if (r1 == 0) goto L_0x0081
            com.google.android.gms.internal.zzpv r1 = r7.zzbyi     // Catch:{ all -> 0x0083 }
            com.google.android.gms.internal.zzpu r1 = (com.google.android.gms.internal.zzpu) r1     // Catch:{ all -> 0x0083 }
            com.google.android.gms.internal.zzpv r1 = r1.zzkp()     // Catch:{ all -> 0x0083 }
            java.lang.String r3 = "1007"
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r5 = r7.zzbzs     // Catch:{ all -> 0x0083 }
            android.widget.FrameLayout r6 = r7.zzbzp     // Catch:{ all -> 0x0083 }
        L_0x006c:
            r2 = r8
            r1.zza(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x0083 }
            goto L_0x0081
        L_0x0071:
            java.lang.String r3 = "1007"
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r5 = r7.zzbzs     // Catch:{ all -> 0x0083 }
            android.widget.FrameLayout r6 = r7.zzbzp     // Catch:{ all -> 0x0083 }
            goto L_0x006c
        L_0x0078:
            com.google.android.gms.internal.zzpv r1 = r7.zzbyi     // Catch:{ all -> 0x0083 }
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r2 = r7.zzbzs     // Catch:{ all -> 0x0083 }
            android.widget.FrameLayout r3 = r7.zzbzp     // Catch:{ all -> 0x0083 }
            r1.zza(r8, r2, r4, r3)     // Catch:{ all -> 0x0083 }
        L_0x0081:
            monitor-exit(r0)     // Catch:{ all -> 0x0083 }
            return
        L_0x0083:
            r8 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0083 }
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzqj.onClick(android.view.View):void");
    }

    public final void onGlobalLayout() {
        synchronized (this.mLock) {
            zzkw();
            zzpv zzpv = this.zzbyi;
            if (zzpv != null) {
                zzpv.zzc(this.zzbzp, this.zzbzs);
            }
        }
    }

    public final void onScrollChanged() {
        synchronized (this.mLock) {
            zzpv zzpv = this.zzbyi;
            if (zzpv != null) {
                zzpv.zzc(this.zzbzp, this.zzbzs);
            }
            zzkw();
        }
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        synchronized (this.mLock) {
            if (this.zzbyi == null) {
                return false;
            }
            int[] iArr = new int[2];
            this.zzbzp.getLocationOnScreen(iArr);
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

    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        	at java.util.ArrayList.rangeCheck(ArrayList.java:659)
        	at java.util.ArrayList.get(ArrayList.java:435)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:698)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:698)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:598)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x00ff A[Catch:{ Exception -> 0x0175 }] */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x0146 A[Catch:{ Exception -> 0x0175 }] */
    public final void zza(com.google.android.gms.dynamic.IObjectWrapper r12) {
        /*
            r11 = this;
            java.lang.Object r0 = r11.mLock
            monitor-enter(r0)
            r1 = 0
            r11.zzk(r1)     // Catch:{ all -> 0x0213 }
            java.lang.Object r12 = com.google.android.gms.dynamic.zzn.zzy(r12)     // Catch:{ all -> 0x0213 }
            boolean r2 = r12 instanceof com.google.android.gms.internal.zzpz     // Catch:{ all -> 0x0213 }
            if (r2 != 0) goto L_0x0016
            java.lang.String r12 = "Not an instance of native engine. This is most likely a transient error"
            com.google.android.gms.internal.zzahw.zzcz(r12)     // Catch:{ all -> 0x0213 }
            monitor-exit(r0)     // Catch:{ all -> 0x0213 }
            return
        L_0x0016:
            boolean r2 = r11.zzbzr     // Catch:{ all -> 0x0213 }
            r3 = 0
            if (r2 != 0) goto L_0x002c
            android.widget.FrameLayout r2 = r11.zzamk     // Catch:{ all -> 0x0213 }
            if (r2 == 0) goto L_0x002c
            android.widget.FrameLayout$LayoutParams r4 = new android.widget.FrameLayout$LayoutParams     // Catch:{ all -> 0x0213 }
            r4.<init>(r3, r3)     // Catch:{ all -> 0x0213 }
            r2.setLayoutParams(r4)     // Catch:{ all -> 0x0213 }
            android.widget.FrameLayout r2 = r11.zzbzp     // Catch:{ all -> 0x0213 }
            r2.requestLayout()     // Catch:{ all -> 0x0213 }
        L_0x002c:
            r2 = 1
            r11.zzbzu = r2     // Catch:{ all -> 0x0213 }
            com.google.android.gms.internal.zzpz r12 = (com.google.android.gms.internal.zzpz) r12     // Catch:{ all -> 0x0213 }
            com.google.android.gms.internal.zzpv r4 = r11.zzbyi     // Catch:{ all -> 0x0213 }
            if (r4 == 0) goto L_0x0050
            com.google.android.gms.internal.zzny<java.lang.Boolean> r4 = com.google.android.gms.internal.zzoi.zzbsn     // Catch:{ all -> 0x0213 }
            com.google.android.gms.internal.zzog r5 = com.google.android.gms.internal.zzlc.zzio()     // Catch:{ all -> 0x0213 }
            java.lang.Object r4 = r5.zzd(r4)     // Catch:{ all -> 0x0213 }
            java.lang.Boolean r4 = (java.lang.Boolean) r4     // Catch:{ all -> 0x0213 }
            boolean r4 = r4.booleanValue()     // Catch:{ all -> 0x0213 }
            if (r4 == 0) goto L_0x0050
            com.google.android.gms.internal.zzpv r4 = r11.zzbyi     // Catch:{ all -> 0x0213 }
            android.widget.FrameLayout r5 = r11.zzbzp     // Catch:{ all -> 0x0213 }
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r6 = r11.zzbzs     // Catch:{ all -> 0x0213 }
            r4.zzb(r5, r6)     // Catch:{ all -> 0x0213 }
        L_0x0050:
            com.google.android.gms.internal.zzpv r4 = r11.zzbyi     // Catch:{ all -> 0x0213 }
            boolean r5 = r4 instanceof com.google.android.gms.internal.zzpz     // Catch:{ all -> 0x0213 }
            if (r5 == 0) goto L_0x0088
            com.google.android.gms.internal.zzpz r4 = (com.google.android.gms.internal.zzpz) r4     // Catch:{ all -> 0x0213 }
            if (r4 == 0) goto L_0x0088
            android.content.Context r5 = r4.getContext()     // Catch:{ all -> 0x0213 }
            if (r5 == 0) goto L_0x0088
            com.google.android.gms.internal.zzagu r5 = com.google.android.gms.ads.internal.zzbt.zzfh()     // Catch:{ all -> 0x0213 }
            android.widget.FrameLayout r6 = r11.zzbzp     // Catch:{ all -> 0x0213 }
            android.content.Context r6 = r6.getContext()     // Catch:{ all -> 0x0213 }
            boolean r5 = r5.zzs(r6)     // Catch:{ all -> 0x0213 }
            if (r5 == 0) goto L_0x0088
            com.google.android.gms.internal.zzagt r4 = r4.zzku()     // Catch:{ all -> 0x0213 }
            if (r4 == 0) goto L_0x0079
            r4.zzw(r3)     // Catch:{ all -> 0x0213 }
        L_0x0079:
            java.lang.ref.WeakReference<com.google.android.gms.internal.zzgr> r5 = r11.zzbzx     // Catch:{ all -> 0x0213 }
            java.lang.Object r5 = r5.get()     // Catch:{ all -> 0x0213 }
            com.google.android.gms.internal.zzgr r5 = (com.google.android.gms.internal.zzgr) r5     // Catch:{ all -> 0x0213 }
            if (r5 == 0) goto L_0x0088
            if (r4 == 0) goto L_0x0088
            r5.zzb(r4)     // Catch:{ all -> 0x0213 }
        L_0x0088:
            com.google.android.gms.internal.zzpv r4 = r11.zzbyi     // Catch:{ all -> 0x0213 }
            boolean r5 = r4 instanceof com.google.android.gms.internal.zzpu     // Catch:{ all -> 0x0213 }
            if (r5 == 0) goto L_0x009e
            com.google.android.gms.internal.zzpu r4 = (com.google.android.gms.internal.zzpu) r4     // Catch:{ all -> 0x0213 }
            boolean r4 = r4.zzko()     // Catch:{ all -> 0x0213 }
            if (r4 == 0) goto L_0x009e
            com.google.android.gms.internal.zzpv r4 = r11.zzbyi     // Catch:{ all -> 0x0213 }
            com.google.android.gms.internal.zzpu r4 = (com.google.android.gms.internal.zzpu) r4     // Catch:{ all -> 0x0213 }
            r4.zzc(r12)     // Catch:{ all -> 0x0213 }
            goto L_0x00aa
        L_0x009e:
            r11.zzbyi = r12     // Catch:{ all -> 0x0213 }
            boolean r4 = r12 instanceof com.google.android.gms.internal.zzpu     // Catch:{ all -> 0x0213 }
            if (r4 == 0) goto L_0x00aa
            r4 = r12
            com.google.android.gms.internal.zzpu r4 = (com.google.android.gms.internal.zzpu) r4     // Catch:{ all -> 0x0213 }
            r4.zzc(r1)     // Catch:{ all -> 0x0213 }
        L_0x00aa:
            android.widget.FrameLayout r4 = r11.zzamk     // Catch:{ all -> 0x0213 }
            if (r4 != 0) goto L_0x00b0
            monitor-exit(r0)     // Catch:{ all -> 0x0213 }
            return
        L_0x00b0:
            com.google.android.gms.internal.zzny<java.lang.Boolean> r4 = com.google.android.gms.internal.zzoi.zzbsn     // Catch:{ all -> 0x0213 }
            com.google.android.gms.internal.zzog r5 = com.google.android.gms.internal.zzlc.zzio()     // Catch:{ all -> 0x0213 }
            java.lang.Object r4 = r5.zzd(r4)     // Catch:{ all -> 0x0213 }
            java.lang.Boolean r4 = (java.lang.Boolean) r4     // Catch:{ all -> 0x0213 }
            boolean r4 = r4.booleanValue()     // Catch:{ all -> 0x0213 }
            if (r4 == 0) goto L_0x00c7
            android.widget.FrameLayout r4 = r11.zzamk     // Catch:{ all -> 0x0213 }
            r4.setClickable(r3)     // Catch:{ all -> 0x0213 }
        L_0x00c7:
            android.widget.FrameLayout r4 = r11.zzamk     // Catch:{ all -> 0x0213 }
            r4.removeAllViews()     // Catch:{ all -> 0x0213 }
            boolean r4 = r12.zzkm()     // Catch:{ all -> 0x0213 }
            if (r4 == 0) goto L_0x00ef
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r5 = r11.zzbzs     // Catch:{ all -> 0x0213 }
            if (r5 == 0) goto L_0x00ef
            java.lang.String r6 = "1098"
            java.lang.Object r5 = r5.get(r6)     // Catch:{ all -> 0x0213 }
            java.lang.ref.WeakReference r5 = (java.lang.ref.WeakReference) r5     // Catch:{ all -> 0x0213 }
            if (r5 == 0) goto L_0x00e7
            java.lang.Object r5 = r5.get()     // Catch:{ all -> 0x0213 }
            android.view.View r5 = (android.view.View) r5     // Catch:{ all -> 0x0213 }
            goto L_0x00e8
        L_0x00e7:
            r5 = r1
        L_0x00e8:
            boolean r6 = r5 instanceof android.view.ViewGroup     // Catch:{ all -> 0x0213 }
            if (r6 == 0) goto L_0x00ef
            android.view.ViewGroup r5 = (android.view.ViewGroup) r5     // Catch:{ all -> 0x0213 }
            goto L_0x00f0
        L_0x00ef:
            r5 = r1
        L_0x00f0:
            if (r4 == 0) goto L_0x00f5
            if (r5 == 0) goto L_0x00f5
            goto L_0x00f6
        L_0x00f5:
            r2 = 0
        L_0x00f6:
            android.view.View r4 = r12.zza((android.view.View.OnClickListener) r11, (boolean) r2)     // Catch:{ all -> 0x0213 }
            r11.zzbzt = r4     // Catch:{ all -> 0x0213 }
            r10 = -1
            if (r4 == 0) goto L_0x0137
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r4 = r11.zzbzs     // Catch:{ all -> 0x0213 }
            if (r4 == 0) goto L_0x010f
            java.lang.String r6 = "1007"
            java.lang.ref.WeakReference r7 = new java.lang.ref.WeakReference     // Catch:{ all -> 0x0213 }
            android.view.View r8 = r11.zzbzt     // Catch:{ all -> 0x0213 }
            r7.<init>(r8)     // Catch:{ all -> 0x0213 }
            r4.put(r6, r7)     // Catch:{ all -> 0x0213 }
        L_0x010f:
            if (r2 == 0) goto L_0x011a
            r5.removeAllViews()     // Catch:{ all -> 0x0213 }
            android.view.View r2 = r11.zzbzt     // Catch:{ all -> 0x0213 }
            r5.addView(r2)     // Catch:{ all -> 0x0213 }
            goto L_0x0137
        L_0x011a:
            android.content.Context r2 = r12.getContext()     // Catch:{ all -> 0x0213 }
            com.google.android.gms.ads.formats.AdChoicesView r4 = new com.google.android.gms.ads.formats.AdChoicesView     // Catch:{ all -> 0x0213 }
            r4.<init>(r2)     // Catch:{ all -> 0x0213 }
            android.widget.FrameLayout$LayoutParams r2 = new android.widget.FrameLayout$LayoutParams     // Catch:{ all -> 0x0213 }
            r2.<init>(r10, r10)     // Catch:{ all -> 0x0213 }
            r4.setLayoutParams(r2)     // Catch:{ all -> 0x0213 }
            android.view.View r2 = r11.zzbzt     // Catch:{ all -> 0x0213 }
            r4.addView(r2)     // Catch:{ all -> 0x0213 }
            android.widget.FrameLayout r2 = r11.zzamk     // Catch:{ all -> 0x0213 }
            if (r2 == 0) goto L_0x0137
            r2.addView(r4)     // Catch:{ all -> 0x0213 }
        L_0x0137:
            android.widget.FrameLayout r5 = r11.zzbzp     // Catch:{ all -> 0x0213 }
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r6 = r11.zzbzs     // Catch:{ all -> 0x0213 }
            r7 = 0
            r4 = r12
            r8 = r11
            r9 = r11
            r4.zza((android.view.View) r5, (java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>>) r6, (java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>>) r7, (android.view.View.OnTouchListener) r8, (android.view.View.OnClickListener) r9)     // Catch:{ all -> 0x0213 }
            boolean r2 = r11.zzbzr     // Catch:{ all -> 0x0213 }
            if (r2 == 0) goto L_0x0170
            android.view.View r2 = r11.zzbzq     // Catch:{ all -> 0x0213 }
            if (r2 != 0) goto L_0x015f
            android.view.View r2 = new android.view.View     // Catch:{ all -> 0x0213 }
            android.widget.FrameLayout r4 = r11.zzbzp     // Catch:{ all -> 0x0213 }
            android.content.Context r4 = r4.getContext()     // Catch:{ all -> 0x0213 }
            r2.<init>(r4)     // Catch:{ all -> 0x0213 }
            r11.zzbzq = r2     // Catch:{ all -> 0x0213 }
            android.widget.FrameLayout$LayoutParams r4 = new android.widget.FrameLayout$LayoutParams     // Catch:{ all -> 0x0213 }
            r4.<init>(r10, r3)     // Catch:{ all -> 0x0213 }
            r2.setLayoutParams(r4)     // Catch:{ all -> 0x0213 }
        L_0x015f:
            android.widget.FrameLayout r2 = r11.zzbzp     // Catch:{ all -> 0x0213 }
            android.view.View r3 = r11.zzbzq     // Catch:{ all -> 0x0213 }
            android.view.ViewParent r3 = r3.getParent()     // Catch:{ all -> 0x0213 }
            if (r2 == r3) goto L_0x0170
            android.widget.FrameLayout r2 = r11.zzbzp     // Catch:{ all -> 0x0213 }
            android.view.View r3 = r11.zzbzq     // Catch:{ all -> 0x0213 }
            r2.addView(r3)     // Catch:{ all -> 0x0213 }
        L_0x0170:
            com.google.android.gms.internal.zzaof r1 = r12.zzkq()     // Catch:{ Exception -> 0x0175 }
            goto L_0x018a
        L_0x0175:
            r2 = move-exception
            com.google.android.gms.ads.internal.zzbt.zzen()     // Catch:{ all -> 0x0213 }
            boolean r3 = com.google.android.gms.internal.zzaip.zzrk()     // Catch:{ all -> 0x0213 }
            if (r3 == 0) goto L_0x0185
            java.lang.String r2 = "Privileged processes cannot create HTML overlays."
            com.google.android.gms.internal.zzahw.zzcz(r2)     // Catch:{ all -> 0x0213 }
            goto L_0x018a
        L_0x0185:
            java.lang.String r3 = "Error obtaining overlay."
            com.google.android.gms.internal.zzahw.zzb(r3, r2)     // Catch:{ all -> 0x0213 }
        L_0x018a:
            if (r1 == 0) goto L_0x0197
            android.widget.FrameLayout r2 = r11.zzamk     // Catch:{ all -> 0x0213 }
            if (r2 == 0) goto L_0x0197
            android.view.View r1 = r1.getView()     // Catch:{ all -> 0x0213 }
            r2.addView(r1)     // Catch:{ all -> 0x0213 }
        L_0x0197:
            java.lang.Object r1 = r11.mLock     // Catch:{ all -> 0x0213 }
            monitor-enter(r1)     // Catch:{ all -> 0x0213 }
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r2 = r11.zzbzs     // Catch:{ all -> 0x0210 }
            r12.zzh(r2)     // Catch:{ all -> 0x0210 }
            android.view.View r2 = r11.zzkv()     // Catch:{ all -> 0x0210 }
            boolean r3 = r2 instanceof android.widget.FrameLayout     // Catch:{ all -> 0x0210 }
            if (r3 != 0) goto L_0x01ac
            r12.zzks()     // Catch:{ all -> 0x0210 }
        L_0x01aa:
            monitor-exit(r1)     // Catch:{ all -> 0x0210 }
            goto L_0x01bd
        L_0x01ac:
            com.google.android.gms.internal.zzqk r3 = new com.google.android.gms.internal.zzqk     // Catch:{ all -> 0x0210 }
            r3.<init>(r11, r2)     // Catch:{ all -> 0x0210 }
            boolean r4 = r12 instanceof com.google.android.gms.internal.zzpu     // Catch:{ all -> 0x0210 }
            if (r4 == 0) goto L_0x01b9
            r12.zzb((android.view.View) r2, (com.google.android.gms.internal.zzpt) r3)     // Catch:{ all -> 0x0210 }
            goto L_0x01aa
        L_0x01b9:
            r12.zza((android.view.View) r2, (com.google.android.gms.internal.zzpt) r3)     // Catch:{ all -> 0x0210 }
            goto L_0x01aa
        L_0x01bd:
            android.widget.FrameLayout r12 = r11.zzbzp     // Catch:{ all -> 0x0213 }
            r11.zzk(r12)     // Catch:{ all -> 0x0213 }
            com.google.android.gms.internal.zzpv r12 = r11.zzbyi     // Catch:{ all -> 0x0213 }
            android.widget.FrameLayout r1 = r11.zzbzp     // Catch:{ all -> 0x0213 }
            r12.zzi(r1)     // Catch:{ all -> 0x0213 }
            com.google.android.gms.internal.zzpv r12 = r11.zzbyi     // Catch:{ all -> 0x0213 }
            boolean r1 = r12 instanceof com.google.android.gms.internal.zzpz     // Catch:{ all -> 0x0213 }
            if (r1 == 0) goto L_0x020e
            com.google.android.gms.internal.zzpz r12 = (com.google.android.gms.internal.zzpz) r12     // Catch:{ all -> 0x0213 }
            if (r12 == 0) goto L_0x020e
            android.content.Context r1 = r12.getContext()     // Catch:{ all -> 0x0213 }
            if (r1 == 0) goto L_0x020e
            com.google.android.gms.internal.zzagu r1 = com.google.android.gms.ads.internal.zzbt.zzfh()     // Catch:{ all -> 0x0213 }
            android.widget.FrameLayout r2 = r11.zzbzp     // Catch:{ all -> 0x0213 }
            android.content.Context r2 = r2.getContext()     // Catch:{ all -> 0x0213 }
            boolean r1 = r1.zzs(r2)     // Catch:{ all -> 0x0213 }
            if (r1 == 0) goto L_0x020e
            java.lang.ref.WeakReference<com.google.android.gms.internal.zzgr> r1 = r11.zzbzx     // Catch:{ all -> 0x0213 }
            java.lang.Object r1 = r1.get()     // Catch:{ all -> 0x0213 }
            com.google.android.gms.internal.zzgr r1 = (com.google.android.gms.internal.zzgr) r1     // Catch:{ all -> 0x0213 }
            if (r1 != 0) goto L_0x0207
            com.google.android.gms.internal.zzgr r1 = new com.google.android.gms.internal.zzgr     // Catch:{ all -> 0x0213 }
            android.widget.FrameLayout r2 = r11.zzbzp     // Catch:{ all -> 0x0213 }
            android.content.Context r2 = r2.getContext()     // Catch:{ all -> 0x0213 }
            android.widget.FrameLayout r3 = r11.zzbzp     // Catch:{ all -> 0x0213 }
            r1.<init>(r2, r3)     // Catch:{ all -> 0x0213 }
            java.lang.ref.WeakReference r2 = new java.lang.ref.WeakReference     // Catch:{ all -> 0x0213 }
            r2.<init>(r1)     // Catch:{ all -> 0x0213 }
            r11.zzbzx = r2     // Catch:{ all -> 0x0213 }
        L_0x0207:
            com.google.android.gms.internal.zzagt r12 = r12.zzku()     // Catch:{ all -> 0x0213 }
            r1.zza((com.google.android.gms.internal.zzgv) r12)     // Catch:{ all -> 0x0213 }
        L_0x020e:
            monitor-exit(r0)     // Catch:{ all -> 0x0213 }
            return
        L_0x0210:
            r12 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0210 }
            throw r12     // Catch:{ all -> 0x0213 }
        L_0x0213:
            r12 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0213 }
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzqj.zza(com.google.android.gms.dynamic.IObjectWrapper):void");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: android.view.View} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.dynamic.IObjectWrapper zzal(java.lang.String r4) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.mLock
            monitor-enter(r0)
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r1 = r3.zzbzs     // Catch:{ all -> 0x0020 }
            r2 = 0
            if (r1 != 0) goto L_0x000a
            monitor-exit(r0)     // Catch:{ all -> 0x0020 }
            return r2
        L_0x000a:
            java.lang.Object r4 = r1.get(r4)     // Catch:{ all -> 0x0020 }
            java.lang.ref.WeakReference r4 = (java.lang.ref.WeakReference) r4     // Catch:{ all -> 0x0020 }
            if (r4 != 0) goto L_0x0013
            goto L_0x001a
        L_0x0013:
            java.lang.Object r4 = r4.get()     // Catch:{ all -> 0x0020 }
            r2 = r4
            android.view.View r2 = (android.view.View) r2     // Catch:{ all -> 0x0020 }
        L_0x001a:
            com.google.android.gms.dynamic.IObjectWrapper r4 = com.google.android.gms.dynamic.zzn.zzz(r2)     // Catch:{ all -> 0x0020 }
            monitor-exit(r0)     // Catch:{ all -> 0x0020 }
            return r4
        L_0x0020:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0020 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzqj.zzal(java.lang.String):com.google.android.gms.dynamic.IObjectWrapper");
    }

    public final void zzb(IObjectWrapper iObjectWrapper, int i) {
        WeakReference<zzgr> weakReference;
        zzgr zzgr;
        if (!(!zzbt.zzfh().zzs(this.zzbzp.getContext()) || (weakReference = this.zzbzx) == null || (zzgr = (zzgr) weakReference.get()) == null)) {
            zzgr.zzgm();
        }
        zzkw();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0032, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzb(java.lang.String r4, com.google.android.gms.dynamic.IObjectWrapper r5) {
        /*
            r3 = this;
            java.lang.Object r5 = com.google.android.gms.dynamic.zzn.zzy(r5)
            android.view.View r5 = (android.view.View) r5
            java.lang.Object r0 = r3.mLock
            monitor-enter(r0)
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r1 = r3.zzbzs     // Catch:{ all -> 0x0033 }
            if (r1 != 0) goto L_0x000f
            monitor-exit(r0)     // Catch:{ all -> 0x0033 }
            return
        L_0x000f:
            if (r5 != 0) goto L_0x0015
            r1.remove(r4)     // Catch:{ all -> 0x0033 }
            goto L_0x0031
        L_0x0015:
            java.lang.ref.WeakReference r2 = new java.lang.ref.WeakReference     // Catch:{ all -> 0x0033 }
            r2.<init>(r5)     // Catch:{ all -> 0x0033 }
            r1.put(r4, r2)     // Catch:{ all -> 0x0033 }
            java.lang.String r1 = "1098"
            boolean r4 = r1.equals(r4)     // Catch:{ all -> 0x0033 }
            if (r4 == 0) goto L_0x0027
            monitor-exit(r0)     // Catch:{ all -> 0x0033 }
            return
        L_0x0027:
            r5.setOnTouchListener(r3)     // Catch:{ all -> 0x0033 }
            r4 = 1
            r5.setClickable(r4)     // Catch:{ all -> 0x0033 }
            r5.setOnClickListener(r3)     // Catch:{ all -> 0x0033 }
        L_0x0031:
            monitor-exit(r0)     // Catch:{ all -> 0x0033 }
            return
        L_0x0033:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0033 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzqj.zzb(java.lang.String, com.google.android.gms.dynamic.IObjectWrapper):void");
    }
}
