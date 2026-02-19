package com.google.android.gms.ads.internal.overlay;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.core.view.ViewCompat;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.common.util.zzs;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzaij;
import com.google.android.gms.internal.zzaip;
import com.google.android.gms.internal.zzaof;
import com.google.android.gms.internal.zzlc;
import com.google.android.gms.internal.zzoi;
import com.google.android.gms.internal.zzyr;
import java.util.Collections;
import java.util.Map;

@zzabh
public class zzd extends zzyr implements zzw {
    private static int zzcmk = Color.argb(0, 0, 0, 0);
    protected final Activity mActivity;
    private zzaof zzcct;
    AdOverlayInfoParcel zzcml;
    private zzi zzcmm;
    private zzo zzcmn;
    private boolean zzcmo = false;
    private FrameLayout zzcmp;
    private WebChromeClient.CustomViewCallback zzcmq;
    private boolean zzcmr = false;
    private boolean zzcms = false;
    private zzh zzcmt;
    private boolean zzcmu = false;
    int zzcmv = 0;
    private final Object zzcmw = new Object();
    private Runnable zzcmx;
    private boolean zzcmy;
    private boolean zzcmz;
    private boolean zzcna = false;
    private boolean zzcnb = false;
    private boolean zzcnc = true;

    public zzd(Activity activity) {
        this.mActivity = activity;
    }

    private final void zznk() {
        if (this.mActivity.isFinishing() && !this.zzcna) {
            this.zzcna = true;
            zzaof zzaof = this.zzcct;
            if (zzaof != null) {
                zzaof.zzag(this.zzcmv);
                synchronized (this.zzcmw) {
                    if (!this.zzcmy && this.zzcct.zzuh()) {
                        this.zzcmx = new zzf(this);
                        zzaij.zzdfn.postDelayed(this.zzcmx, ((Long) zzlc.zzio().zzd(zzoi.zzbpk)).longValue());
                        return;
                    }
                }
            }
            zznl();
        }
    }

    private final void zznn() {
        this.zzcct.zznn();
    }

    private final void zzs(boolean z) {
        int intValue = ((Integer) zzlc.zzio().zzd(zzoi.zzbvb)).intValue();
        zzp zzp = new zzp();
        zzp.size = 50;
        zzp.paddingLeft = z ? intValue : 0;
        zzp.paddingRight = z ? 0 : intValue;
        zzp.paddingTop = 0;
        zzp.paddingBottom = intValue;
        this.zzcmn = new zzo(this.mActivity, zzp, this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(10);
        layoutParams.addRule(z ? 11 : 9);
        zza(z, this.zzcml.zzcnp);
        this.zzcmt.addView(this.zzcmn, layoutParams);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00c4, code lost:
        if (r1.mActivity.getResources().getConfiguration().orientation == 1) goto L_0x00c6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00e5, code lost:
        if (r1.mActivity.getResources().getConfiguration().orientation == 2) goto L_0x00c6;
     */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x024e  */
    /* JADX WARNING: Removed duplicated region for block: B:109:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x004e  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0050  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0094  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00a0  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00a5  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00aa  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0111  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x011a  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x011f  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0131 A[SYNTHETIC, Splitter:B:58:0x0131] */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x01fe  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x0229  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzt(boolean r19) throws com.google.android.gms.ads.internal.overlay.zzg {
        /*
            r18 = this;
            r1 = r18
            boolean r0 = r1.zzcmz
            r2 = 1
            if (r0 != 0) goto L_0x000c
            android.app.Activity r0 = r1.mActivity
            r0.requestWindowFeature(r2)
        L_0x000c:
            android.app.Activity r0 = r1.mActivity
            android.view.Window r0 = r0.getWindow()
            if (r0 == 0) goto L_0x0252
            boolean r3 = com.google.android.gms.common.util.zzs.isAtLeastN()
            if (r3 == 0) goto L_0x003e
            com.google.android.gms.internal.zzny<java.lang.Boolean> r3 = com.google.android.gms.internal.zzoi.zzbuz
            com.google.android.gms.internal.zzog r4 = com.google.android.gms.internal.zzlc.zzio()
            java.lang.Object r3 = r4.zzd(r3)
            java.lang.Boolean r3 = (java.lang.Boolean) r3
            boolean r3 = r3.booleanValue()
            if (r3 == 0) goto L_0x003e
            com.google.android.gms.ads.internal.zzbt.zzel()
            android.app.Activity r3 = r1.mActivity
            android.content.res.Resources r4 = r3.getResources()
            android.content.res.Configuration r4 = r4.getConfiguration()
            boolean r3 = com.google.android.gms.internal.zzaij.zza((android.app.Activity) r3, (android.content.res.Configuration) r4)
            goto L_0x003f
        L_0x003e:
            r3 = 1
        L_0x003f:
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r4 = r1.zzcml
            com.google.android.gms.ads.internal.zzap r4 = r4.zzcnu
            r5 = 0
            if (r4 == 0) goto L_0x0050
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r4 = r1.zzcml
            com.google.android.gms.ads.internal.zzap r4 = r4.zzcnu
            boolean r4 = r4.zzaqq
            if (r4 == 0) goto L_0x0050
            r4 = 1
            goto L_0x0051
        L_0x0050:
            r4 = 0
        L_0x0051:
            boolean r6 = r1.zzcms
            if (r6 == 0) goto L_0x0057
            if (r4 == 0) goto L_0x008d
        L_0x0057:
            if (r3 == 0) goto L_0x008d
            r3 = 1024(0x400, float:1.435E-42)
            r0.setFlags(r3, r3)
            com.google.android.gms.internal.zzny<java.lang.Boolean> r3 = com.google.android.gms.internal.zzoi.zzbpl
            com.google.android.gms.internal.zzog r4 = com.google.android.gms.internal.zzlc.zzio()
            java.lang.Object r3 = r4.zzd(r3)
            java.lang.Boolean r3 = (java.lang.Boolean) r3
            boolean r3 = r3.booleanValue()
            if (r3 == 0) goto L_0x008d
            boolean r3 = com.google.android.gms.common.util.zzs.zzanv()
            if (r3 == 0) goto L_0x008d
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r3 = r1.zzcml
            com.google.android.gms.ads.internal.zzap r3 = r3.zzcnu
            if (r3 == 0) goto L_0x008d
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r3 = r1.zzcml
            com.google.android.gms.ads.internal.zzap r3 = r3.zzcnu
            boolean r3 = r3.zzaqv
            if (r3 == 0) goto L_0x008d
            android.view.View r3 = r0.getDecorView()
            r4 = 4098(0x1002, float:5.743E-42)
            r3.setSystemUiVisibility(r4)
        L_0x008d:
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r3 = r1.zzcml
            com.google.android.gms.internal.zzaof r3 = r3.zzcnm
            r4 = 0
            if (r3 == 0) goto L_0x009d
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r3 = r1.zzcml
            com.google.android.gms.internal.zzaof r3 = r3.zzcnm
            com.google.android.gms.internal.zzapu r3 = r3.zzua()
            goto L_0x009e
        L_0x009d:
            r3 = r4
        L_0x009e:
            if (r3 == 0) goto L_0x00a5
            boolean r3 = r3.zzfz()
            goto L_0x00a6
        L_0x00a5:
            r3 = 0
        L_0x00a6:
            r1.zzcmu = r5
            if (r3 == 0) goto L_0x00e8
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r6 = r1.zzcml
            int r6 = r6.orientation
            com.google.android.gms.internal.zzaip r7 = com.google.android.gms.ads.internal.zzbt.zzen()
            int r7 = r7.zzrg()
            if (r6 != r7) goto L_0x00ca
            android.app.Activity r6 = r1.mActivity
            android.content.res.Resources r6 = r6.getResources()
            android.content.res.Configuration r6 = r6.getConfiguration()
            int r6 = r6.orientation
            if (r6 != r2) goto L_0x00c7
        L_0x00c6:
            r5 = 1
        L_0x00c7:
            r1.zzcmu = r5
            goto L_0x00e8
        L_0x00ca:
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r6 = r1.zzcml
            int r6 = r6.orientation
            com.google.android.gms.internal.zzaip r7 = com.google.android.gms.ads.internal.zzbt.zzen()
            int r7 = r7.zzrh()
            if (r6 != r7) goto L_0x00e8
            android.app.Activity r6 = r1.mActivity
            android.content.res.Resources r6 = r6.getResources()
            android.content.res.Configuration r6 = r6.getConfiguration()
            int r6 = r6.orientation
            r7 = 2
            if (r6 != r7) goto L_0x00c7
            goto L_0x00c6
        L_0x00e8:
            boolean r5 = r1.zzcmu
            r6 = 46
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>(r6)
            java.lang.String r6 = "Delay onShow to next orientation change: "
            r7.append(r6)
            r7.append(r5)
            java.lang.String r5 = r7.toString()
            com.google.android.gms.internal.zzahw.zzby(r5)
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r5 = r1.zzcml
            int r5 = r5.orientation
            r1.setRequestedOrientation(r5)
            com.google.android.gms.internal.zzaip r5 = com.google.android.gms.ads.internal.zzbt.zzen()
            boolean r0 = r5.zza((android.view.Window) r0)
            if (r0 == 0) goto L_0x0116
            java.lang.String r0 = "Hardware acceleration on the AdActivity window enabled."
            com.google.android.gms.internal.zzahw.zzby(r0)
        L_0x0116:
            boolean r0 = r1.zzcms
            if (r0 != 0) goto L_0x011f
            com.google.android.gms.ads.internal.overlay.zzh r0 = r1.zzcmt
            r5 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            goto L_0x0123
        L_0x011f:
            com.google.android.gms.ads.internal.overlay.zzh r0 = r1.zzcmt
            int r5 = zzcmk
        L_0x0123:
            r0.setBackgroundColor(r5)
            android.app.Activity r0 = r1.mActivity
            com.google.android.gms.ads.internal.overlay.zzh r5 = r1.zzcmt
            r0.setContentView(r5)
            r1.zzcmz = r2
            if (r19 == 0) goto L_0x01fe
            com.google.android.gms.internal.zzaol r6 = com.google.android.gms.ads.internal.zzbt.zzem()     // Catch:{ Exception -> 0x01f0 }
            android.app.Activity r7 = r1.mActivity     // Catch:{ Exception -> 0x01f0 }
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r0 = r1.zzcml     // Catch:{ Exception -> 0x01f0 }
            com.google.android.gms.internal.zzaof r0 = r0.zzcnm     // Catch:{ Exception -> 0x01f0 }
            if (r0 == 0) goto L_0x0147
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r0 = r1.zzcml     // Catch:{ Exception -> 0x01f0 }
            com.google.android.gms.internal.zzaof r0 = r0.zzcnm     // Catch:{ Exception -> 0x01f0 }
            com.google.android.gms.internal.zzaqa r0 = r0.zzty()     // Catch:{ Exception -> 0x01f0 }
            r8 = r0
            goto L_0x0148
        L_0x0147:
            r8 = r4
        L_0x0148:
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r0 = r1.zzcml     // Catch:{ Exception -> 0x01f0 }
            com.google.android.gms.internal.zzaof r0 = r0.zzcnm     // Catch:{ Exception -> 0x01f0 }
            if (r0 == 0) goto L_0x0158
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r0 = r1.zzcml     // Catch:{ Exception -> 0x01f0 }
            com.google.android.gms.internal.zzaof r0 = r0.zzcnm     // Catch:{ Exception -> 0x01f0 }
            java.lang.String r0 = r0.zztz()     // Catch:{ Exception -> 0x01f0 }
            r9 = r0
            goto L_0x0159
        L_0x0158:
            r9 = r4
        L_0x0159:
            r10 = 1
            r12 = 0
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r0 = r1.zzcml     // Catch:{ Exception -> 0x01f0 }
            com.google.android.gms.internal.zzala r13 = r0.zzatz     // Catch:{ Exception -> 0x01f0 }
            r14 = 0
            r15 = 0
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r0 = r1.zzcml     // Catch:{ Exception -> 0x01f0 }
            com.google.android.gms.internal.zzaof r0 = r0.zzcnm     // Catch:{ Exception -> 0x01f0 }
            if (r0 == 0) goto L_0x0172
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r0 = r1.zzcml     // Catch:{ Exception -> 0x01f0 }
            com.google.android.gms.internal.zzaof r0 = r0.zzcnm     // Catch:{ Exception -> 0x01f0 }
            com.google.android.gms.ads.internal.zzv r0 = r0.zzbo()     // Catch:{ Exception -> 0x01f0 }
            r16 = r0
            goto L_0x0174
        L_0x0172:
            r16 = r4
        L_0x0174:
            com.google.android.gms.internal.zziu r17 = com.google.android.gms.internal.zziu.zzhp()     // Catch:{ Exception -> 0x01f0 }
            r11 = r3
            com.google.android.gms.internal.zzaof r0 = r6.zza(r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17)     // Catch:{ Exception -> 0x01f0 }
            r1.zzcct = r0     // Catch:{ Exception -> 0x01f0 }
            com.google.android.gms.internal.zzapu r5 = r0.zzua()
            r6 = 0
            r7 = 0
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r0 = r1.zzcml
            com.google.android.gms.ads.internal.gmsg.zzb r8 = r0.zzcnn
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r0 = r1.zzcml
            com.google.android.gms.ads.internal.overlay.zzt r9 = r0.zzcnr
            r10 = 1
            r11 = 0
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r0 = r1.zzcml
            com.google.android.gms.internal.zzaof r0 = r0.zzcnm
            if (r0 == 0) goto L_0x01a1
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r0 = r1.zzcml
            com.google.android.gms.internal.zzaof r0 = r0.zzcnm
            com.google.android.gms.internal.zzapu r0 = r0.zzua()
            com.google.android.gms.ads.internal.zzw r4 = r0.zzun()
        L_0x01a1:
            r12 = r4
            r13 = 0
            r14 = 0
            r5.zza(r6, r7, r8, r9, r10, r11, r12, r13, r14)
            com.google.android.gms.internal.zzaof r0 = r1.zzcct
            com.google.android.gms.internal.zzapu r0 = r0.zzua()
            com.google.android.gms.internal.zzapv r4 = com.google.android.gms.ads.internal.overlay.zze.zzcnd
            r0.zza((com.google.android.gms.internal.zzapv) r4)
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r0 = r1.zzcml
            java.lang.String r0 = r0.url
            if (r0 == 0) goto L_0x01c2
            com.google.android.gms.internal.zzaof r0 = r1.zzcct
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r4 = r1.zzcml
            java.lang.String r4 = r4.url
            r0.loadUrl(r4)
            goto L_0x01da
        L_0x01c2:
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r0 = r1.zzcml
            java.lang.String r0 = r0.zzcnq
            if (r0 == 0) goto L_0x01e8
            com.google.android.gms.internal.zzaof r4 = r1.zzcct
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r0 = r1.zzcml
            java.lang.String r5 = r0.zzcno
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r0 = r1.zzcml
            java.lang.String r6 = r0.zzcnq
            r9 = 0
            java.lang.String r7 = "text/html"
            java.lang.String r8 = "UTF-8"
            r4.loadDataWithBaseURL(r5, r6, r7, r8, r9)
        L_0x01da:
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r0 = r1.zzcml
            com.google.android.gms.internal.zzaof r0 = r0.zzcnm
            if (r0 == 0) goto L_0x0209
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r0 = r1.zzcml
            com.google.android.gms.internal.zzaof r0 = r0.zzcnm
            r0.zzb((com.google.android.gms.ads.internal.overlay.zzd) r1)
            goto L_0x0209
        L_0x01e8:
            com.google.android.gms.ads.internal.overlay.zzg r0 = new com.google.android.gms.ads.internal.overlay.zzg
            java.lang.String r2 = "No URL or HTML to display in ad overlay."
            r0.<init>(r2)
            throw r0
        L_0x01f0:
            r0 = move-exception
            java.lang.String r2 = "Error obtaining webview."
            com.google.android.gms.internal.zzahw.zzb(r2, r0)
            com.google.android.gms.ads.internal.overlay.zzg r0 = new com.google.android.gms.ads.internal.overlay.zzg
            java.lang.String r2 = "Could not obtain webview for the overlay."
            r0.<init>(r2)
            throw r0
        L_0x01fe:
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r0 = r1.zzcml
            com.google.android.gms.internal.zzaof r0 = r0.zzcnm
            r1.zzcct = r0
            android.app.Activity r4 = r1.mActivity
            r0.setContext(r4)
        L_0x0209:
            com.google.android.gms.internal.zzaof r0 = r1.zzcct
            r0.zza((com.google.android.gms.ads.internal.overlay.zzd) r1)
            com.google.android.gms.internal.zzaof r0 = r1.zzcct
            android.view.ViewParent r0 = r0.getParent()
            if (r0 == 0) goto L_0x0225
            boolean r4 = r0 instanceof android.view.ViewGroup
            if (r4 == 0) goto L_0x0225
            android.view.ViewGroup r0 = (android.view.ViewGroup) r0
            com.google.android.gms.internal.zzaof r4 = r1.zzcct
            android.view.View r4 = r4.getView()
            r0.removeView(r4)
        L_0x0225:
            boolean r0 = r1.zzcms
            if (r0 == 0) goto L_0x022e
            com.google.android.gms.internal.zzaof r0 = r1.zzcct
            r0.zzul()
        L_0x022e:
            com.google.android.gms.ads.internal.overlay.zzh r0 = r1.zzcmt
            com.google.android.gms.internal.zzaof r4 = r1.zzcct
            android.view.View r4 = r4.getView()
            r5 = -1
            r0.addView(r4, r5, r5)
            if (r19 != 0) goto L_0x0243
            boolean r0 = r1.zzcmu
            if (r0 != 0) goto L_0x0243
            r18.zznn()
        L_0x0243:
            r1.zzs(r3)
            com.google.android.gms.internal.zzaof r0 = r1.zzcct
            boolean r0 = r0.zzub()
            if (r0 == 0) goto L_0x0251
            r1.zza((boolean) r3, (boolean) r2)
        L_0x0251:
            return
        L_0x0252:
            com.google.android.gms.ads.internal.overlay.zzg r0 = new com.google.android.gms.ads.internal.overlay.zzg
            java.lang.String r2 = "Invalid activity, no window available."
            r0.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.overlay.zzd.zzt(boolean):void");
    }

    public final void close() {
        this.zzcmv = 2;
        this.mActivity.finish();
    }

    public final void onActivityResult(int i, int i2, Intent intent) {
    }

    public final void onBackPressed() {
        this.zzcmv = 0;
    }

    public void onCreate(Bundle bundle) {
        this.mActivity.requestWindowFeature(1);
        this.zzcmr = bundle != null ? bundle.getBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", false) : false;
        try {
            AdOverlayInfoParcel zzc = AdOverlayInfoParcel.zzc(this.mActivity.getIntent());
            this.zzcml = zzc;
            if (zzc != null) {
                if (zzc.zzatz.zzdja > 7500000) {
                    this.zzcmv = 3;
                }
                if (this.mActivity.getIntent() != null) {
                    this.zzcnc = this.mActivity.getIntent().getBooleanExtra("shouldCallOnOverlayOpened", true);
                }
                if (this.zzcml.zzcnu != null) {
                    this.zzcms = this.zzcml.zzcnu.zzaqp;
                } else {
                    this.zzcms = false;
                }
                if (((Boolean) zzlc.zzio().zzd(zzoi.zzbrz)).booleanValue() && this.zzcms && this.zzcml.zzcnu.zzaqu != -1) {
                    new zzj(this, (zzf) null).zzqj();
                }
                if (bundle == null) {
                    if (this.zzcml.zzcnl != null && this.zzcnc) {
                        this.zzcml.zzcnl.zzcg();
                    }
                    if (!(this.zzcml.zzcns == 1 || this.zzcml.zzcnk == null)) {
                        this.zzcml.zzcnk.onAdClicked();
                    }
                }
                zzh zzh = new zzh(this.mActivity, this.zzcml.zzcnt, this.zzcml.zzatz.zzcu);
                this.zzcmt = zzh;
                zzh.setId(1000);
                int i = this.zzcml.zzcns;
                if (i == 1) {
                    zzt(false);
                } else if (i == 2) {
                    this.zzcmm = new zzi(this.zzcml.zzcnm);
                    zzt(false);
                } else if (i == 3) {
                    zzt(true);
                } else {
                    throw new zzg("Could not determine ad overlay type.");
                }
            } else {
                throw new zzg("Could not get info for ad overlay.");
            }
        } catch (zzg e) {
            zzahw.zzcz(e.getMessage());
            this.zzcmv = 3;
            this.mActivity.finish();
        }
    }

    public final void onDestroy() {
        zzaof zzaof = this.zzcct;
        if (zzaof != null) {
            this.zzcmt.removeView(zzaof.getView());
        }
        zznk();
    }

    public final void onPause() {
        zzng();
        if (this.zzcml.zzcnl != null) {
            this.zzcml.zzcnl.onPause();
        }
        if (!((Boolean) zzlc.zzio().zzd(zzoi.zzbva)).booleanValue() && this.zzcct != null && (!this.mActivity.isFinishing() || this.zzcmm == null)) {
            zzbt.zzen();
            zzaip.zzh(this.zzcct);
        }
        zznk();
    }

    public final void onRestart() {
    }

    public final void onResume() {
        if (this.zzcml.zzcnl != null) {
            this.zzcml.zzcnl.onResume();
        }
        if (!((Boolean) zzlc.zzio().zzd(zzoi.zzbva)).booleanValue()) {
            zzaof zzaof = this.zzcct;
            if (zzaof == null || zzaof.isDestroyed()) {
                zzahw.zzcz("The webview does not exist. Ignoring action.");
                return;
            }
            zzbt.zzen();
            zzaip.zzi(this.zzcct);
        }
    }

    public final void onSaveInstanceState(Bundle bundle) {
        bundle.putBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", this.zzcmr);
    }

    public final void onStart() {
        if (((Boolean) zzlc.zzio().zzd(zzoi.zzbva)).booleanValue()) {
            zzaof zzaof = this.zzcct;
            if (zzaof == null || zzaof.isDestroyed()) {
                zzahw.zzcz("The webview does not exist. Ignoring action.");
                return;
            }
            zzbt.zzen();
            zzaip.zzi(this.zzcct);
        }
    }

    public final void onStop() {
        if (((Boolean) zzlc.zzio().zzd(zzoi.zzbva)).booleanValue() && this.zzcct != null && (!this.mActivity.isFinishing() || this.zzcmm == null)) {
            zzbt.zzen();
            zzaip.zzh(this.zzcct);
        }
        zznk();
    }

    public final void setRequestedOrientation(int i) {
        if (this.mActivity.getApplicationInfo().targetSdkVersion >= ((Integer) zzlc.zzio().zzd(zzoi.zzbvx)).intValue()) {
            if (this.mActivity.getApplicationInfo().targetSdkVersion <= ((Integer) zzlc.zzio().zzd(zzoi.zzbvy)).intValue()) {
                if (Build.VERSION.SDK_INT >= ((Integer) zzlc.zzio().zzd(zzoi.zzbvz)).intValue()) {
                    if (Build.VERSION.SDK_INT <= ((Integer) zzlc.zzio().zzd(zzoi.zzbwa)).intValue()) {
                        return;
                    }
                }
            }
        }
        this.mActivity.setRequestedOrientation(i);
    }

    public final void zza(View view, WebChromeClient.CustomViewCallback customViewCallback) {
        FrameLayout frameLayout = new FrameLayout(this.mActivity);
        this.zzcmp = frameLayout;
        frameLayout.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        this.zzcmp.addView(view, -1, -1);
        this.mActivity.setContentView(this.zzcmp);
        this.zzcmz = true;
        this.zzcmq = customViewCallback;
        this.zzcmo = true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0014, code lost:
        r0 = r6.zzcml;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(boolean r7, boolean r8) {
        /*
            r6 = this;
            com.google.android.gms.internal.zzny<java.lang.Boolean> r0 = com.google.android.gms.internal.zzoi.zzbpm
            com.google.android.gms.internal.zzog r1 = com.google.android.gms.internal.zzlc.zzio()
            java.lang.Object r0 = r1.zzd(r0)
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x0026
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r0 = r6.zzcml
            if (r0 == 0) goto L_0x0026
            com.google.android.gms.ads.internal.zzap r0 = r0.zzcnu
            if (r0 == 0) goto L_0x0026
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r0 = r6.zzcml
            com.google.android.gms.ads.internal.zzap r0 = r0.zzcnu
            boolean r0 = r0.zzaqw
            if (r0 == 0) goto L_0x0026
            r0 = 1
            goto L_0x0027
        L_0x0026:
            r0 = 0
        L_0x0027:
            if (r7 == 0) goto L_0x003b
            if (r8 == 0) goto L_0x003b
            if (r0 == 0) goto L_0x003b
            com.google.android.gms.internal.zzyn r3 = new com.google.android.gms.internal.zzyn
            com.google.android.gms.internal.zzaof r4 = r6.zzcct
            java.lang.String r5 = "useCustomClose"
            r3.<init>(r4, r5)
            java.lang.String r4 = "Custom close has been disabled for interstitial ads in this ad slot."
            r3.zzbm(r4)
        L_0x003b:
            com.google.android.gms.ads.internal.overlay.zzo r3 = r6.zzcmn
            if (r3 == 0) goto L_0x0048
            if (r8 == 0) goto L_0x0044
            if (r0 != 0) goto L_0x0044
            goto L_0x0045
        L_0x0044:
            r1 = 0
        L_0x0045:
            r3.zza(r7, r1)
        L_0x0048:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.overlay.zzd.zza(boolean, boolean):void");
    }

    public final void zzbd() {
        this.zzcmz = true;
    }

    public final void zzk(IObjectWrapper iObjectWrapper) {
        if (((Boolean) zzlc.zzio().zzd(zzoi.zzbuz)).booleanValue() && zzs.isAtLeastN()) {
            zzbt.zzel();
            if (zzaij.zza(this.mActivity, (Configuration) zzn.zzy(iObjectWrapper))) {
                this.mActivity.getWindow().addFlags(1024);
                this.mActivity.getWindow().clearFlags(2048);
                return;
            }
            this.mActivity.getWindow().addFlags(2048);
            this.mActivity.getWindow().clearFlags(1024);
        }
    }

    public final void zzng() {
        AdOverlayInfoParcel adOverlayInfoParcel = this.zzcml;
        if (adOverlayInfoParcel != null && this.zzcmo) {
            setRequestedOrientation(adOverlayInfoParcel.orientation);
        }
        if (this.zzcmp != null) {
            this.mActivity.setContentView(this.zzcmt);
            this.zzcmz = true;
            this.zzcmp.removeAllViews();
            this.zzcmp = null;
        }
        WebChromeClient.CustomViewCallback customViewCallback = this.zzcmq;
        if (customViewCallback != null) {
            customViewCallback.onCustomViewHidden();
            this.zzcmq = null;
        }
        this.zzcmo = false;
    }

    public final void zznh() {
        this.zzcmv = 1;
        this.mActivity.finish();
    }

    public final boolean zzni() {
        this.zzcmv = 0;
        zzaof zzaof = this.zzcct;
        if (zzaof == null) {
            return true;
        }
        boolean zzuf = zzaof.zzuf();
        if (!zzuf) {
            this.zzcct.zza("onbackblocked", Collections.<String, Object>emptyMap());
        }
        return zzuf;
    }

    public final void zznj() {
        this.zzcmt.removeView(this.zzcmn);
        zzs(true);
    }

    /* access modifiers changed from: package-private */
    public final void zznl() {
        if (!this.zzcnb) {
            this.zzcnb = true;
            zzaof zzaof = this.zzcct;
            if (zzaof != null) {
                this.zzcmt.removeView(zzaof.getView());
                zzi zzi = this.zzcmm;
                if (zzi != null) {
                    this.zzcct.setContext(zzi.zzaiq);
                    this.zzcct.zzah(false);
                    this.zzcmm.parent.addView(this.zzcct.getView(), this.zzcmm.index, this.zzcmm.zzcng);
                    this.zzcmm = null;
                } else if (this.mActivity.getApplicationContext() != null) {
                    this.zzcct.setContext(this.mActivity.getApplicationContext());
                }
                this.zzcct = null;
            }
            AdOverlayInfoParcel adOverlayInfoParcel = this.zzcml;
            if (adOverlayInfoParcel != null && adOverlayInfoParcel.zzcnl != null) {
                this.zzcml.zzcnl.zzcf();
            }
        }
    }

    public final void zznm() {
        if (this.zzcmu) {
            this.zzcmu = false;
            zznn();
        }
    }

    public final void zzno() {
        this.zzcmt.zzcnf = true;
    }

    public final void zznp() {
        synchronized (this.zzcmw) {
            this.zzcmy = true;
            if (this.zzcmx != null) {
                zzaij.zzdfn.removeCallbacks(this.zzcmx);
                zzaij.zzdfn.post(this.zzcmx);
            }
        }
    }
}
