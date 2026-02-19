package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.common.util.zzf;
import java.util.Set;

@zzabh
public final class zzyd extends zzyn {
    private static Set<String> zzcky = zzf.zzb("top-left", "top-right", "top-center", "center", "bottom-left", "bottom-right", "bottom-center");
    private final Object mLock = new Object();
    private int zzalt = -1;
    private int zzalu = -1;
    private zzyo zzcco;
    private final zzaof zzcct;
    private final Activity zzckp;
    private String zzckz = "top-right";
    private boolean zzcla = true;
    private int zzclb = 0;
    private int zzclc = 0;
    private int zzcld = 0;
    private int zzcle = 0;
    private zzaqa zzclf;
    private ImageView zzclg;
    private LinearLayout zzclh;
    private PopupWindow zzcli;
    private RelativeLayout zzclj;
    private ViewGroup zzclk;

    public zzyd(zzaof zzaof, zzyo zzyo) {
        super(zzaof, "resize");
        this.zzcct = zzaof;
        this.zzckp = zzaof.zztj();
        this.zzcco = zzyo;
    }

    private final void zza(int i, int i2) {
        zzb(i, i2 - zzbt.zzel().zzh(this.zzckp)[0], this.zzalt, this.zzalu);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00b4, code lost:
        r5 = ((r9.zzclc + r9.zzcle) + r9.zzalu) - 50;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00c1, code lost:
        r5 = r9.zzclc + r9.zzcle;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00db, code lost:
        if (r0 < 0) goto L_0x00f4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00de, code lost:
        if ((r0 + 50) > r3) goto L_0x00f4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00e2, code lost:
        if (r5 < r1[0]) goto L_0x00f4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00e7, code lost:
        if ((r5 + 50) <= r1[1]) goto L_0x00ea;
     */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00f7 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00f9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int[] zzne() {
        /*
            r9 = this;
            com.google.android.gms.internal.zzaij r0 = com.google.android.gms.ads.internal.zzbt.zzel()
            android.app.Activity r1 = r9.zzckp
            int[] r0 = r0.zzg(r1)
            com.google.android.gms.internal.zzaij r1 = com.google.android.gms.ads.internal.zzbt.zzel()
            android.app.Activity r2 = r9.zzckp
            int[] r1 = r1.zzh(r2)
            r2 = 0
            r3 = r0[r2]
            r4 = 1
            r0 = r0[r4]
            int r5 = r9.zzalt
            r6 = 2
            r7 = 50
            if (r5 < r7) goto L_0x00ef
            if (r5 <= r3) goto L_0x0025
            goto L_0x00ef
        L_0x0025:
            int r8 = r9.zzalu
            if (r8 < r7) goto L_0x00ec
            if (r8 <= r0) goto L_0x002d
            goto L_0x00ec
        L_0x002d:
            if (r8 != r0) goto L_0x0035
            if (r5 != r3) goto L_0x0035
            java.lang.String r0 = "Cannot resize to a full-screen ad."
            goto L_0x00f1
        L_0x0035:
            boolean r0 = r9.zzcla
            if (r0 == 0) goto L_0x00ea
            java.lang.String r0 = r9.zzckz
            r0.hashCode()
            r5 = -1
            int r8 = r0.hashCode()
            switch(r8) {
                case -1364013995: goto L_0x007e;
                case -1012429441: goto L_0x0073;
                case -655373719: goto L_0x0068;
                case 1163912186: goto L_0x005d;
                case 1288627767: goto L_0x0052;
                case 1755462605: goto L_0x0047;
                default: goto L_0x0046;
            }
        L_0x0046:
            goto L_0x0088
        L_0x0047:
            java.lang.String r8 = "top-center"
            boolean r0 = r0.equals(r8)
            if (r0 != 0) goto L_0x0050
            goto L_0x0088
        L_0x0050:
            r5 = 5
            goto L_0x0088
        L_0x0052:
            java.lang.String r8 = "bottom-center"
            boolean r0 = r0.equals(r8)
            if (r0 != 0) goto L_0x005b
            goto L_0x0088
        L_0x005b:
            r5 = 4
            goto L_0x0088
        L_0x005d:
            java.lang.String r8 = "bottom-right"
            boolean r0 = r0.equals(r8)
            if (r0 != 0) goto L_0x0066
            goto L_0x0088
        L_0x0066:
            r5 = 3
            goto L_0x0088
        L_0x0068:
            java.lang.String r8 = "bottom-left"
            boolean r0 = r0.equals(r8)
            if (r0 != 0) goto L_0x0071
            goto L_0x0088
        L_0x0071:
            r5 = 2
            goto L_0x0088
        L_0x0073:
            java.lang.String r8 = "top-left"
            boolean r0 = r0.equals(r8)
            if (r0 != 0) goto L_0x007c
            goto L_0x0088
        L_0x007c:
            r5 = 1
            goto L_0x0088
        L_0x007e:
            java.lang.String r8 = "center"
            boolean r0 = r0.equals(r8)
            if (r0 != 0) goto L_0x0087
            goto L_0x0088
        L_0x0087:
            r5 = 0
        L_0x0088:
            int r0 = r9.zzclb
            switch(r5) {
                case 0: goto L_0x00c7;
                case 1: goto L_0x00be;
                case 2: goto L_0x00b1;
                case 3: goto L_0x00a9;
                case 4: goto L_0x009f;
                case 5: goto L_0x0095;
                default: goto L_0x008d;
            }
        L_0x008d:
            int r5 = r9.zzcld
            int r0 = r0 + r5
            int r5 = r9.zzalt
            int r0 = r0 + r5
            int r0 = r0 - r7
            goto L_0x00c1
        L_0x0095:
            int r5 = r9.zzcld
            int r0 = r0 + r5
            int r5 = r9.zzalt
            int r5 = r5 / r6
            int r0 = r0 + r5
            int r0 = r0 + -25
            goto L_0x00c1
        L_0x009f:
            int r5 = r9.zzcld
            int r0 = r0 + r5
            int r5 = r9.zzalt
            int r5 = r5 / r6
            int r0 = r0 + r5
            int r0 = r0 + -25
            goto L_0x00b4
        L_0x00a9:
            int r5 = r9.zzcld
            int r0 = r0 + r5
            int r5 = r9.zzalt
            int r0 = r0 + r5
            int r0 = r0 - r7
            goto L_0x00b4
        L_0x00b1:
            int r5 = r9.zzcld
            int r0 = r0 + r5
        L_0x00b4:
            int r5 = r9.zzclc
            int r8 = r9.zzcle
            int r5 = r5 + r8
            int r8 = r9.zzalu
            int r5 = r5 + r8
            int r5 = r5 - r7
            goto L_0x00db
        L_0x00be:
            int r5 = r9.zzcld
            int r0 = r0 + r5
        L_0x00c1:
            int r5 = r9.zzclc
            int r8 = r9.zzcle
            int r5 = r5 + r8
            goto L_0x00db
        L_0x00c7:
            int r5 = r9.zzcld
            int r0 = r0 + r5
            int r5 = r9.zzalt
            int r5 = r5 / r6
            int r0 = r0 + r5
            int r0 = r0 + -25
            int r5 = r9.zzclc
            int r8 = r9.zzcle
            int r5 = r5 + r8
            int r8 = r9.zzalu
            int r8 = r8 / r6
            int r5 = r5 + r8
            int r5 = r5 + -25
        L_0x00db:
            if (r0 < 0) goto L_0x00f4
            int r0 = r0 + r7
            if (r0 > r3) goto L_0x00f4
            r0 = r1[r2]
            if (r5 < r0) goto L_0x00f4
            int r5 = r5 + r7
            r0 = r1[r4]
            if (r5 <= r0) goto L_0x00ea
            goto L_0x00f4
        L_0x00ea:
            r0 = 1
            goto L_0x00f5
        L_0x00ec:
            java.lang.String r0 = "Height is too small or too large."
            goto L_0x00f1
        L_0x00ef:
            java.lang.String r0 = "Width is too small or too large."
        L_0x00f1:
            com.google.android.gms.internal.zzahw.zzcz(r0)
        L_0x00f4:
            r0 = 0
        L_0x00f5:
            if (r0 != 0) goto L_0x00f9
            r0 = 0
            return r0
        L_0x00f9:
            boolean r0 = r9.zzcla
            if (r0 == 0) goto L_0x010e
            int[] r0 = new int[r6]
            int r1 = r9.zzclb
            int r3 = r9.zzcld
            int r1 = r1 + r3
            r0[r2] = r1
            int r1 = r9.zzclc
            int r2 = r9.zzcle
            int r1 = r1 + r2
            r0[r4] = r1
            return r0
        L_0x010e:
            com.google.android.gms.internal.zzaij r0 = com.google.android.gms.ads.internal.zzbt.zzel()
            android.app.Activity r1 = r9.zzckp
            int[] r0 = r0.zzg(r1)
            com.google.android.gms.internal.zzaij r1 = com.google.android.gms.ads.internal.zzbt.zzel()
            android.app.Activity r3 = r9.zzckp
            int[] r1 = r1.zzh(r3)
            r0 = r0[r2]
            int r3 = r9.zzclb
            int r5 = r9.zzcld
            int r3 = r3 + r5
            int r5 = r9.zzclc
            int r7 = r9.zzcle
            int r5 = r5 + r7
            if (r3 >= 0) goto L_0x0132
            r3 = 0
            goto L_0x013a
        L_0x0132:
            int r7 = r9.zzalt
            int r8 = r3 + r7
            if (r8 <= r0) goto L_0x013a
            int r3 = r0 - r7
        L_0x013a:
            r0 = r1[r2]
            if (r5 >= r0) goto L_0x0141
            r5 = r1[r2]
            goto L_0x014d
        L_0x0141:
            int r0 = r9.zzalu
            int r7 = r5 + r0
            r8 = r1[r4]
            if (r7 <= r8) goto L_0x014d
            r1 = r1[r4]
            int r5 = r1 - r0
        L_0x014d:
            int[] r0 = new int[r6]
            r0[r2] = r3
            r0[r4] = r5
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzyd.zzne():int[]");
    }

    /* JADX WARNING: Removed duplicated region for block: B:119:0x02ab A[Catch:{ RuntimeException -> 0x02ca }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void execute(java.util.Map<java.lang.String, java.lang.String> r17) {
        /*
            r16 = this;
            r1 = r16
            r0 = r17
            java.lang.Object r2 = r1.mLock
            monitor-enter(r2)
            android.app.Activity r3 = r1.zzckp     // Catch:{ all -> 0x031e }
            if (r3 != 0) goto L_0x0012
            java.lang.String r0 = "Not an activity context. Cannot resize."
            r1.zzbm(r0)     // Catch:{ all -> 0x031e }
            monitor-exit(r2)     // Catch:{ all -> 0x031e }
            return
        L_0x0012:
            com.google.android.gms.internal.zzaof r3 = r1.zzcct     // Catch:{ all -> 0x031e }
            com.google.android.gms.internal.zzaqa r3 = r3.zzty()     // Catch:{ all -> 0x031e }
            if (r3 != 0) goto L_0x0021
            java.lang.String r0 = "Webview is not yet available, size is not set."
            r1.zzbm(r0)     // Catch:{ all -> 0x031e }
            monitor-exit(r2)     // Catch:{ all -> 0x031e }
            return
        L_0x0021:
            com.google.android.gms.internal.zzaof r3 = r1.zzcct     // Catch:{ all -> 0x031e }
            com.google.android.gms.internal.zzaqa r3 = r3.zzty()     // Catch:{ all -> 0x031e }
            boolean r3 = r3.zzvl()     // Catch:{ all -> 0x031e }
            if (r3 == 0) goto L_0x0034
            java.lang.String r0 = "Is interstitial. Cannot resize an interstitial."
            r1.zzbm(r0)     // Catch:{ all -> 0x031e }
            monitor-exit(r2)     // Catch:{ all -> 0x031e }
            return
        L_0x0034:
            com.google.android.gms.internal.zzaof r3 = r1.zzcct     // Catch:{ all -> 0x031e }
            boolean r3 = r3.zzud()     // Catch:{ all -> 0x031e }
            if (r3 == 0) goto L_0x0043
            java.lang.String r0 = "Cannot resize an expanded banner."
            r1.zzbm(r0)     // Catch:{ all -> 0x031e }
            monitor-exit(r2)     // Catch:{ all -> 0x031e }
            return
        L_0x0043:
            java.lang.String r3 = "width"
            java.lang.Object r3 = r0.get(r3)     // Catch:{ all -> 0x031e }
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3     // Catch:{ all -> 0x031e }
            boolean r3 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x031e }
            if (r3 != 0) goto L_0x0062
            com.google.android.gms.ads.internal.zzbt.zzel()     // Catch:{ all -> 0x031e }
            java.lang.String r3 = "width"
            java.lang.Object r3 = r0.get(r3)     // Catch:{ all -> 0x031e }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ all -> 0x031e }
            int r3 = com.google.android.gms.internal.zzaij.zzcj(r3)     // Catch:{ all -> 0x031e }
            r1.zzalt = r3     // Catch:{ all -> 0x031e }
        L_0x0062:
            java.lang.String r3 = "height"
            java.lang.Object r3 = r0.get(r3)     // Catch:{ all -> 0x031e }
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3     // Catch:{ all -> 0x031e }
            boolean r3 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x031e }
            if (r3 != 0) goto L_0x0081
            com.google.android.gms.ads.internal.zzbt.zzel()     // Catch:{ all -> 0x031e }
            java.lang.String r3 = "height"
            java.lang.Object r3 = r0.get(r3)     // Catch:{ all -> 0x031e }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ all -> 0x031e }
            int r3 = com.google.android.gms.internal.zzaij.zzcj(r3)     // Catch:{ all -> 0x031e }
            r1.zzalu = r3     // Catch:{ all -> 0x031e }
        L_0x0081:
            java.lang.String r3 = "offsetX"
            java.lang.Object r3 = r0.get(r3)     // Catch:{ all -> 0x031e }
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3     // Catch:{ all -> 0x031e }
            boolean r3 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x031e }
            if (r3 != 0) goto L_0x00a0
            com.google.android.gms.ads.internal.zzbt.zzel()     // Catch:{ all -> 0x031e }
            java.lang.String r3 = "offsetX"
            java.lang.Object r3 = r0.get(r3)     // Catch:{ all -> 0x031e }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ all -> 0x031e }
            int r3 = com.google.android.gms.internal.zzaij.zzcj(r3)     // Catch:{ all -> 0x031e }
            r1.zzcld = r3     // Catch:{ all -> 0x031e }
        L_0x00a0:
            java.lang.String r3 = "offsetY"
            java.lang.Object r3 = r0.get(r3)     // Catch:{ all -> 0x031e }
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3     // Catch:{ all -> 0x031e }
            boolean r3 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x031e }
            if (r3 != 0) goto L_0x00bf
            com.google.android.gms.ads.internal.zzbt.zzel()     // Catch:{ all -> 0x031e }
            java.lang.String r3 = "offsetY"
            java.lang.Object r3 = r0.get(r3)     // Catch:{ all -> 0x031e }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ all -> 0x031e }
            int r3 = com.google.android.gms.internal.zzaij.zzcj(r3)     // Catch:{ all -> 0x031e }
            r1.zzcle = r3     // Catch:{ all -> 0x031e }
        L_0x00bf:
            java.lang.String r3 = "allowOffscreen"
            java.lang.Object r3 = r0.get(r3)     // Catch:{ all -> 0x031e }
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3     // Catch:{ all -> 0x031e }
            boolean r3 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x031e }
            if (r3 != 0) goto L_0x00db
            java.lang.String r3 = "allowOffscreen"
            java.lang.Object r3 = r0.get(r3)     // Catch:{ all -> 0x031e }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ all -> 0x031e }
            boolean r3 = java.lang.Boolean.parseBoolean(r3)     // Catch:{ all -> 0x031e }
            r1.zzcla = r3     // Catch:{ all -> 0x031e }
        L_0x00db:
            java.lang.String r3 = "customClosePosition"
            java.lang.Object r0 = r0.get(r3)     // Catch:{ all -> 0x031e }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ all -> 0x031e }
            boolean r3 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x031e }
            if (r3 != 0) goto L_0x00eb
            r1.zzckz = r0     // Catch:{ all -> 0x031e }
        L_0x00eb:
            int r0 = r1.zzalt     // Catch:{ all -> 0x031e }
            r3 = 1
            r4 = 0
            if (r0 < 0) goto L_0x00f7
            int r0 = r1.zzalu     // Catch:{ all -> 0x031e }
            if (r0 < 0) goto L_0x00f7
            r0 = 1
            goto L_0x00f8
        L_0x00f7:
            r0 = 0
        L_0x00f8:
            if (r0 != 0) goto L_0x0101
            java.lang.String r0 = "Invalid width and height options. Cannot resize."
            r1.zzbm(r0)     // Catch:{ all -> 0x031e }
            monitor-exit(r2)     // Catch:{ all -> 0x031e }
            return
        L_0x0101:
            android.app.Activity r0 = r1.zzckp     // Catch:{ all -> 0x031e }
            android.view.Window r0 = r0.getWindow()     // Catch:{ all -> 0x031e }
            if (r0 == 0) goto L_0x0317
            android.view.View r5 = r0.getDecorView()     // Catch:{ all -> 0x031e }
            if (r5 != 0) goto L_0x0111
            goto L_0x0317
        L_0x0111:
            int[] r5 = r16.zzne()     // Catch:{ all -> 0x031e }
            if (r5 != 0) goto L_0x011e
            java.lang.String r0 = "Resize location out of screen or close button is not visible."
            r1.zzbm(r0)     // Catch:{ all -> 0x031e }
            monitor-exit(r2)     // Catch:{ all -> 0x031e }
            return
        L_0x011e:
            com.google.android.gms.internal.zzlc.zzij()     // Catch:{ all -> 0x031e }
            android.app.Activity r6 = r1.zzckp     // Catch:{ all -> 0x031e }
            int r7 = r1.zzalt     // Catch:{ all -> 0x031e }
            int r6 = com.google.android.gms.internal.zzako.zza((android.content.Context) r6, (int) r7)     // Catch:{ all -> 0x031e }
            com.google.android.gms.internal.zzlc.zzij()     // Catch:{ all -> 0x031e }
            android.app.Activity r7 = r1.zzckp     // Catch:{ all -> 0x031e }
            int r8 = r1.zzalu     // Catch:{ all -> 0x031e }
            int r7 = com.google.android.gms.internal.zzako.zza((android.content.Context) r7, (int) r8)     // Catch:{ all -> 0x031e }
            com.google.android.gms.internal.zzaof r8 = r1.zzcct     // Catch:{ all -> 0x031e }
            android.view.View r8 = r8.getView()     // Catch:{ all -> 0x031e }
            android.view.ViewParent r8 = r8.getParent()     // Catch:{ all -> 0x031e }
            if (r8 == 0) goto L_0x0310
            boolean r9 = r8 instanceof android.view.ViewGroup     // Catch:{ all -> 0x031e }
            if (r9 == 0) goto L_0x0310
            r9 = r8
            android.view.ViewGroup r9 = (android.view.ViewGroup) r9     // Catch:{ all -> 0x031e }
            com.google.android.gms.internal.zzaof r10 = r1.zzcct     // Catch:{ all -> 0x031e }
            android.view.View r10 = r10.getView()     // Catch:{ all -> 0x031e }
            r9.removeView(r10)     // Catch:{ all -> 0x031e }
            android.widget.PopupWindow r9 = r1.zzcli     // Catch:{ all -> 0x031e }
            if (r9 != 0) goto L_0x0181
            android.view.ViewGroup r8 = (android.view.ViewGroup) r8     // Catch:{ all -> 0x031e }
            r1.zzclk = r8     // Catch:{ all -> 0x031e }
            com.google.android.gms.ads.internal.zzbt.zzel()     // Catch:{ all -> 0x031e }
            com.google.android.gms.internal.zzaof r8 = r1.zzcct     // Catch:{ all -> 0x031e }
            android.view.View r8 = r8.getView()     // Catch:{ all -> 0x031e }
            android.graphics.Bitmap r8 = com.google.android.gms.internal.zzaij.zzr(r8)     // Catch:{ all -> 0x031e }
            android.widget.ImageView r9 = new android.widget.ImageView     // Catch:{ all -> 0x031e }
            android.app.Activity r10 = r1.zzckp     // Catch:{ all -> 0x031e }
            r9.<init>(r10)     // Catch:{ all -> 0x031e }
            r1.zzclg = r9     // Catch:{ all -> 0x031e }
            r9.setImageBitmap(r8)     // Catch:{ all -> 0x031e }
            com.google.android.gms.internal.zzaof r8 = r1.zzcct     // Catch:{ all -> 0x031e }
            com.google.android.gms.internal.zzaqa r8 = r8.zzty()     // Catch:{ all -> 0x031e }
            r1.zzclf = r8     // Catch:{ all -> 0x031e }
            android.view.ViewGroup r8 = r1.zzclk     // Catch:{ all -> 0x031e }
            android.widget.ImageView r9 = r1.zzclg     // Catch:{ all -> 0x031e }
            r8.addView(r9)     // Catch:{ all -> 0x031e }
            goto L_0x0184
        L_0x0181:
            r9.dismiss()     // Catch:{ all -> 0x031e }
        L_0x0184:
            android.widget.RelativeLayout r8 = new android.widget.RelativeLayout     // Catch:{ all -> 0x031e }
            android.app.Activity r9 = r1.zzckp     // Catch:{ all -> 0x031e }
            r8.<init>(r9)     // Catch:{ all -> 0x031e }
            r1.zzclj = r8     // Catch:{ all -> 0x031e }
            r8.setBackgroundColor(r4)     // Catch:{ all -> 0x031e }
            android.widget.RelativeLayout r8 = r1.zzclj     // Catch:{ all -> 0x031e }
            android.view.ViewGroup$LayoutParams r9 = new android.view.ViewGroup$LayoutParams     // Catch:{ all -> 0x031e }
            r9.<init>(r6, r7)     // Catch:{ all -> 0x031e }
            r8.setLayoutParams(r9)     // Catch:{ all -> 0x031e }
            com.google.android.gms.ads.internal.zzbt.zzel()     // Catch:{ all -> 0x031e }
            android.widget.RelativeLayout r8 = r1.zzclj     // Catch:{ all -> 0x031e }
            android.widget.PopupWindow r8 = com.google.android.gms.internal.zzaij.zza((android.view.View) r8, (int) r6, (int) r7, (boolean) r4)     // Catch:{ all -> 0x031e }
            r1.zzcli = r8     // Catch:{ all -> 0x031e }
            r8.setOutsideTouchable(r3)     // Catch:{ all -> 0x031e }
            android.widget.PopupWindow r8 = r1.zzcli     // Catch:{ all -> 0x031e }
            r8.setTouchable(r3)     // Catch:{ all -> 0x031e }
            android.widget.PopupWindow r8 = r1.zzcli     // Catch:{ all -> 0x031e }
            boolean r9 = r1.zzcla     // Catch:{ all -> 0x031e }
            if (r9 != 0) goto L_0x01b5
            r9 = 1
            goto L_0x01b6
        L_0x01b5:
            r9 = 0
        L_0x01b6:
            r8.setClippingEnabled(r9)     // Catch:{ all -> 0x031e }
            android.widget.RelativeLayout r8 = r1.zzclj     // Catch:{ all -> 0x031e }
            com.google.android.gms.internal.zzaof r9 = r1.zzcct     // Catch:{ all -> 0x031e }
            android.view.View r9 = r9.getView()     // Catch:{ all -> 0x031e }
            r10 = -1
            r8.addView(r9, r10, r10)     // Catch:{ all -> 0x031e }
            android.widget.LinearLayout r8 = new android.widget.LinearLayout     // Catch:{ all -> 0x031e }
            android.app.Activity r9 = r1.zzckp     // Catch:{ all -> 0x031e }
            r8.<init>(r9)     // Catch:{ all -> 0x031e }
            r1.zzclh = r8     // Catch:{ all -> 0x031e }
            android.widget.RelativeLayout$LayoutParams r8 = new android.widget.RelativeLayout$LayoutParams     // Catch:{ all -> 0x031e }
            com.google.android.gms.internal.zzlc.zzij()     // Catch:{ all -> 0x031e }
            android.app.Activity r9 = r1.zzckp     // Catch:{ all -> 0x031e }
            r11 = 50
            int r9 = com.google.android.gms.internal.zzako.zza((android.content.Context) r9, (int) r11)     // Catch:{ all -> 0x031e }
            com.google.android.gms.internal.zzlc.zzij()     // Catch:{ all -> 0x031e }
            android.app.Activity r12 = r1.zzckp     // Catch:{ all -> 0x031e }
            int r11 = com.google.android.gms.internal.zzako.zza((android.content.Context) r12, (int) r11)     // Catch:{ all -> 0x031e }
            r8.<init>(r9, r11)     // Catch:{ all -> 0x031e }
            java.lang.String r9 = r1.zzckz     // Catch:{ all -> 0x031e }
            int r11 = r9.hashCode()     // Catch:{ all -> 0x031e }
            r12 = 5
            r13 = 4
            r14 = 3
            r15 = 2
            switch(r11) {
                case -1364013995: goto L_0x0227;
                case -1012429441: goto L_0x021d;
                case -655373719: goto L_0x0213;
                case 1163912186: goto L_0x0209;
                case 1288627767: goto L_0x01ff;
                case 1755462605: goto L_0x01f5;
                default: goto L_0x01f4;
            }     // Catch:{ all -> 0x031e }
        L_0x01f4:
            goto L_0x0230
        L_0x01f5:
            java.lang.String r11 = "top-center"
            boolean r9 = r9.equals(r11)     // Catch:{ all -> 0x031e }
            if (r9 == 0) goto L_0x0230
            r10 = 1
            goto L_0x0230
        L_0x01ff:
            java.lang.String r11 = "bottom-center"
            boolean r9 = r9.equals(r11)     // Catch:{ all -> 0x031e }
            if (r9 == 0) goto L_0x0230
            r10 = 4
            goto L_0x0230
        L_0x0209:
            java.lang.String r11 = "bottom-right"
            boolean r9 = r9.equals(r11)     // Catch:{ all -> 0x031e }
            if (r9 == 0) goto L_0x0230
            r10 = 5
            goto L_0x0230
        L_0x0213:
            java.lang.String r11 = "bottom-left"
            boolean r9 = r9.equals(r11)     // Catch:{ all -> 0x031e }
            if (r9 == 0) goto L_0x0230
            r10 = 3
            goto L_0x0230
        L_0x021d:
            java.lang.String r11 = "top-left"
            boolean r9 = r9.equals(r11)     // Catch:{ all -> 0x031e }
            if (r9 == 0) goto L_0x0230
            r10 = 0
            goto L_0x0230
        L_0x0227:
            java.lang.String r11 = "center"
            boolean r9 = r9.equals(r11)     // Catch:{ all -> 0x031e }
            if (r9 == 0) goto L_0x0230
            r10 = 2
        L_0x0230:
            r9 = 9
            r11 = 10
            if (r10 == 0) goto L_0x0266
            r4 = 14
            if (r10 == r3) goto L_0x0262
            if (r10 == r15) goto L_0x025c
            r15 = 12
            if (r10 == r14) goto L_0x0255
            if (r10 == r13) goto L_0x0251
            r4 = 11
            if (r10 == r12) goto L_0x024d
            r8.addRule(r11)     // Catch:{ all -> 0x031e }
        L_0x0249:
            r8.addRule(r4)     // Catch:{ all -> 0x031e }
            goto L_0x026a
        L_0x024d:
            r8.addRule(r15)     // Catch:{ all -> 0x031e }
            goto L_0x0249
        L_0x0251:
            r8.addRule(r15)     // Catch:{ all -> 0x031e }
            goto L_0x0249
        L_0x0255:
            r8.addRule(r15)     // Catch:{ all -> 0x031e }
        L_0x0258:
            r8.addRule(r9)     // Catch:{ all -> 0x031e }
            goto L_0x026a
        L_0x025c:
            r4 = 13
            r8.addRule(r4)     // Catch:{ all -> 0x031e }
            goto L_0x026a
        L_0x0262:
            r8.addRule(r11)     // Catch:{ all -> 0x031e }
            goto L_0x0249
        L_0x0266:
            r8.addRule(r11)     // Catch:{ all -> 0x031e }
            goto L_0x0258
        L_0x026a:
            android.widget.LinearLayout r4 = r1.zzclh     // Catch:{ all -> 0x031e }
            com.google.android.gms.internal.zzye r9 = new com.google.android.gms.internal.zzye     // Catch:{ all -> 0x031e }
            r9.<init>(r1)     // Catch:{ all -> 0x031e }
            r4.setOnClickListener(r9)     // Catch:{ all -> 0x031e }
            android.widget.LinearLayout r4 = r1.zzclh     // Catch:{ all -> 0x031e }
            java.lang.String r9 = "Close button"
            r4.setContentDescription(r9)     // Catch:{ all -> 0x031e }
            android.widget.RelativeLayout r4 = r1.zzclj     // Catch:{ all -> 0x031e }
            android.widget.LinearLayout r9 = r1.zzclh     // Catch:{ all -> 0x031e }
            r4.addView(r9, r8)     // Catch:{ all -> 0x031e }
            android.widget.PopupWindow r4 = r1.zzcli     // Catch:{ RuntimeException -> 0x02ca }
            android.view.View r0 = r0.getDecorView()     // Catch:{ RuntimeException -> 0x02ca }
            com.google.android.gms.internal.zzlc.zzij()     // Catch:{ RuntimeException -> 0x02ca }
            android.app.Activity r8 = r1.zzckp     // Catch:{ RuntimeException -> 0x02ca }
            r9 = 0
            r10 = r5[r9]     // Catch:{ RuntimeException -> 0x02ca }
            int r8 = com.google.android.gms.internal.zzako.zza((android.content.Context) r8, (int) r10)     // Catch:{ RuntimeException -> 0x02ca }
            com.google.android.gms.internal.zzlc.zzij()     // Catch:{ RuntimeException -> 0x02ca }
            android.app.Activity r9 = r1.zzckp     // Catch:{ RuntimeException -> 0x02ca }
            r10 = r5[r3]     // Catch:{ RuntimeException -> 0x02ca }
            int r9 = com.google.android.gms.internal.zzako.zza((android.content.Context) r9, (int) r10)     // Catch:{ RuntimeException -> 0x02ca }
            r10 = 0
            r4.showAtLocation(r0, r10, r8, r9)     // Catch:{ RuntimeException -> 0x02ca }
            r0 = r5[r10]     // Catch:{ all -> 0x031e }
            r4 = r5[r3]     // Catch:{ all -> 0x031e }
            com.google.android.gms.internal.zzyo r8 = r1.zzcco     // Catch:{ all -> 0x031e }
            if (r8 == 0) goto L_0x02b2
            int r9 = r1.zzalt     // Catch:{ all -> 0x031e }
            int r10 = r1.zzalu     // Catch:{ all -> 0x031e }
            r8.zza(r0, r4, r9, r10)     // Catch:{ all -> 0x031e }
        L_0x02b2:
            com.google.android.gms.internal.zzaof r0 = r1.zzcct     // Catch:{ all -> 0x031e }
            com.google.android.gms.internal.zzaqa r4 = com.google.android.gms.internal.zzaqa.zzi(r6, r7)     // Catch:{ all -> 0x031e }
            r0.zza((com.google.android.gms.internal.zzaqa) r4)     // Catch:{ all -> 0x031e }
            r0 = 0
            r0 = r5[r0]     // Catch:{ all -> 0x031e }
            r3 = r5[r3]     // Catch:{ all -> 0x031e }
            r1.zza(r0, r3)     // Catch:{ all -> 0x031e }
            java.lang.String r0 = "resized"
            r1.zzbo(r0)     // Catch:{ all -> 0x031e }
            monitor-exit(r2)     // Catch:{ all -> 0x031e }
            return
        L_0x02ca:
            r0 = move-exception
            java.lang.String r3 = "Cannot show popup window: "
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x031e }
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ all -> 0x031e }
            int r4 = r0.length()     // Catch:{ all -> 0x031e }
            if (r4 == 0) goto L_0x02e0
            java.lang.String r0 = r3.concat(r0)     // Catch:{ all -> 0x031e }
            goto L_0x02e5
        L_0x02e0:
            java.lang.String r0 = new java.lang.String     // Catch:{ all -> 0x031e }
            r0.<init>(r3)     // Catch:{ all -> 0x031e }
        L_0x02e5:
            r1.zzbm(r0)     // Catch:{ all -> 0x031e }
            android.widget.RelativeLayout r0 = r1.zzclj     // Catch:{ all -> 0x031e }
            com.google.android.gms.internal.zzaof r3 = r1.zzcct     // Catch:{ all -> 0x031e }
            android.view.View r3 = r3.getView()     // Catch:{ all -> 0x031e }
            r0.removeView(r3)     // Catch:{ all -> 0x031e }
            android.view.ViewGroup r0 = r1.zzclk     // Catch:{ all -> 0x031e }
            if (r0 == 0) goto L_0x030e
            android.widget.ImageView r3 = r1.zzclg     // Catch:{ all -> 0x031e }
            r0.removeView(r3)     // Catch:{ all -> 0x031e }
            android.view.ViewGroup r0 = r1.zzclk     // Catch:{ all -> 0x031e }
            com.google.android.gms.internal.zzaof r3 = r1.zzcct     // Catch:{ all -> 0x031e }
            android.view.View r3 = r3.getView()     // Catch:{ all -> 0x031e }
            r0.addView(r3)     // Catch:{ all -> 0x031e }
            com.google.android.gms.internal.zzaof r0 = r1.zzcct     // Catch:{ all -> 0x031e }
            com.google.android.gms.internal.zzaqa r3 = r1.zzclf     // Catch:{ all -> 0x031e }
            r0.zza((com.google.android.gms.internal.zzaqa) r3)     // Catch:{ all -> 0x031e }
        L_0x030e:
            monitor-exit(r2)     // Catch:{ all -> 0x031e }
            return
        L_0x0310:
            java.lang.String r0 = "Webview is detached, probably in the middle of a resize or expand."
            r1.zzbm(r0)     // Catch:{ all -> 0x031e }
            monitor-exit(r2)     // Catch:{ all -> 0x031e }
            return
        L_0x0317:
            java.lang.String r0 = "Activity context is not ready, cannot get window or decor view."
            r1.zzbm(r0)     // Catch:{ all -> 0x031e }
            monitor-exit(r2)     // Catch:{ all -> 0x031e }
            return
        L_0x031e:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x031e }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzyd.execute(java.util.Map):void");
    }

    public final void zza(int i, int i2, boolean z) {
        synchronized (this.mLock) {
            this.zzclb = i;
            this.zzclc = i2;
            if (this.zzcli != null && z) {
                int[] zzne = zzne();
                if (zzne != null) {
                    PopupWindow popupWindow = this.zzcli;
                    zzlc.zzij();
                    int zza = zzako.zza((Context) this.zzckp, zzne[0]);
                    zzlc.zzij();
                    popupWindow.update(zza, zzako.zza((Context) this.zzckp, zzne[1]), this.zzcli.getWidth(), this.zzcli.getHeight());
                    zza(zzne[0], zzne[1]);
                } else {
                    zzm(true);
                }
            }
        }
    }

    public final void zzb(int i, int i2) {
        this.zzclb = i;
        this.zzclc = i2;
    }

    public final void zzm(boolean z) {
        synchronized (this.mLock) {
            PopupWindow popupWindow = this.zzcli;
            if (popupWindow != null) {
                popupWindow.dismiss();
                this.zzclj.removeView(this.zzcct.getView());
                ViewGroup viewGroup = this.zzclk;
                if (viewGroup != null) {
                    viewGroup.removeView(this.zzclg);
                    this.zzclk.addView(this.zzcct.getView());
                    this.zzcct.zza(this.zzclf);
                }
                if (z) {
                    zzbo("default");
                    zzyo zzyo = this.zzcco;
                    if (zzyo != null) {
                        zzyo.zzct();
                    }
                }
                this.zzcli = null;
                this.zzclj = null;
                this.zzclk = null;
                this.zzclh = null;
            }
        }
    }

    public final boolean zznf() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzcli != null;
        }
        return z;
    }
}
