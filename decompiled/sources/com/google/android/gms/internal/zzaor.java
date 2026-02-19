package com.google.android.gms.internal;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.webkit.DownloadListener;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.gms.ads.internal.gmsg.zzt;
import com.google.android.gms.ads.internal.overlay.zzc;
import com.google.android.gms.ads.internal.overlay.zzd;
import com.google.android.gms.ads.internal.zzbm;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.ads.internal.zzv;
import com.google.android.gms.common.util.zzs;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

@zzabh
final class zzaor extends WebView implements ViewTreeObserver.OnGlobalLayoutListener, DownloadListener, zzaof {
    private String zzalv;
    private final zzv zzanp;
    private final zzala zzapq;
    private zzakn zzavk;
    private final WindowManager zzavz;
    private final DisplayMetrics zzaxo;
    private final zzcv zzbyz;
    private int zzcly = -1;
    private int zzclz = -1;
    private int zzcmb = -1;
    private int zzcmc = -1;
    private final zziu zzcrg;
    private String zzcwj = "";
    private Boolean zzdds;
    private zzot zzdnt;
    private final zzapz zzdqm;
    private final zzbm zzdqn;
    private final float zzdqo;
    private zzaog zzdqp;
    private zzd zzdqq;
    private zzaqa zzdqr;
    private boolean zzdqs;
    private boolean zzdqt;
    private boolean zzdqu;
    private boolean zzdqv;
    private int zzdqw;
    private boolean zzdqx = true;
    private boolean zzdqy = false;
    private zzaou zzdqz;
    private boolean zzdra;
    private boolean zzdrb;
    private zzpt zzdrc;
    private int zzdrd;
    /* access modifiers changed from: private */
    public int zzdre;
    private zzot zzdrf;
    private zzot zzdrg;
    private zzou zzdrh;
    private WeakReference<View.OnClickListener> zzdri;
    private zzd zzdrj;
    private boolean zzdrk;
    private Map<String, zzany> zzdrl;

    private zzaor(zzapz zzapz, zzaqa zzaqa, String str, boolean z, boolean z2, zzcv zzcv, zzala zzala, zzov zzov, zzbm zzbm, zzv zzv, zziu zziu) {
        super(zzapz);
        this.zzdqm = zzapz;
        this.zzdqr = zzaqa;
        this.zzalv = str;
        this.zzdqu = z;
        this.zzdqw = -1;
        this.zzbyz = zzcv;
        this.zzapq = zzala;
        this.zzdqn = zzbm;
        this.zzanp = zzv;
        WindowManager windowManager = (WindowManager) getContext().getSystemService("window");
        this.zzavz = windowManager;
        zzbt.zzel();
        DisplayMetrics zza = zzaij.zza(windowManager);
        this.zzaxo = zza;
        this.zzdqo = zza.density;
        this.zzcrg = zziu;
        setBackgroundColor(0);
        WebSettings settings = getSettings();
        settings.setAllowFileAccess(false);
        try {
            settings.setJavaScriptEnabled(true);
        } catch (NullPointerException e) {
            zzahw.zzb("Unable to enable Javascript.", e);
        }
        settings.setSavePassword(false);
        settings.setSupportMultipleWindows(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        if (Build.VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(2);
        }
        zzbt.zzel().zza((Context) zzapz, zzala.zzcu, settings);
        zzbt.zzen().zza(getContext(), settings);
        setDownloadListener(this);
        zzvd();
        if (zzs.zzant()) {
            addJavascriptInterface(zzaox.zzl(this), "googleAdsJsInterface");
        }
        removeJavascriptInterface("accessibility");
        removeJavascriptInterface("accessibilityTraversal");
        this.zzavk = new zzakn(this.zzdqm.zztj(), this, this, (ViewTreeObserver.OnScrollChangedListener) null);
        zzvh();
        zzou zzou = new zzou(new zzov(true, "make_wv", this.zzalv));
        this.zzdrh = zzou;
        zzou.zzjn().zzc(zzov);
        zzot zzb = zzoo.zzb(this.zzdrh.zzjn());
        this.zzdnt = zzb;
        this.zzdrh.zza("native:view_create", zzb);
        this.zzdrg = null;
        this.zzdrf = null;
        zzbt.zzen().zzat(zzapz);
    }

    private final void zza(Boolean bool) {
        synchronized (this) {
            this.zzdds = bool;
        }
        zzbt.zzep().zza(bool);
    }

    private final synchronized void zza(String str, ValueCallback<String> valueCallback) {
        if (!isDestroyed()) {
            evaluateJavascript(str, (ValueCallback<String>) null);
        } else {
            zzahw.zzcz("The webview is destroyed. Ignoring action.");
        }
    }

    private final void zzal(boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put("isVisible", z ? "1" : "0");
        zza("onAdVisibilityChanged", (Map<String, ?>) hashMap);
    }

    static zzaor zzb(Context context, zzaqa zzaqa, String str, boolean z, boolean z2, zzcv zzcv, zzala zzala, zzov zzov, zzbm zzbm, zzv zzv, zziu zziu) {
        Context context2 = context;
        return new zzaor(new zzapz(context), zzaqa, str, z, z2, zzcv, zzala, zzov, zzbm, zzv, zziu);
    }

    private final synchronized void zzdg(String str) {
        if (!isDestroyed()) {
            loadUrl(str);
        } else {
            zzahw.zzcz("The webview is destroyed. Ignoring action.");
        }
    }

    private final synchronized void zzdh(String str) {
        try {
            super.loadUrl(str);
        } catch (Exception | IncompatibleClassChangeError | NoClassDefFoundError | UnsatisfiedLinkError e) {
            zzbt.zzep().zza(e, "AdWebViewImpl.loadUrlUnsafe");
            zzahw.zzc("Could not call loadUrl. ", e);
        }
    }

    private final void zzdi(String str) {
        if (zzs.zzanv()) {
            if (zzpw() == null) {
                zzvb();
            }
            if (zzpw().booleanValue()) {
                zza(str, (ValueCallback<String>) null);
                return;
            }
            String valueOf = String.valueOf(str);
            zzdg(valueOf.length() != 0 ? "javascript:".concat(valueOf) : new String("javascript:"));
            return;
        }
        String valueOf2 = String.valueOf(str);
        zzdg(valueOf2.length() != 0 ? "javascript:".concat(valueOf2) : new String("javascript:"));
    }

    private final synchronized Boolean zzpw() {
        return this.zzdds;
    }

    private final synchronized void zzqc() {
        if (!this.zzdrk) {
            this.zzdrk = true;
            zzbt.zzep().zzqc();
        }
    }

    private final boolean zzva() {
        int i;
        int i2;
        boolean z = false;
        if (!this.zzdqp.zzfz() && !this.zzdqp.zzuo()) {
            return false;
        }
        zzlc.zzij();
        DisplayMetrics displayMetrics = this.zzaxo;
        int zzb = zzako.zzb(displayMetrics, displayMetrics.widthPixels);
        zzlc.zzij();
        DisplayMetrics displayMetrics2 = this.zzaxo;
        int zzb2 = zzako.zzb(displayMetrics2, displayMetrics2.heightPixels);
        Activity zztj = this.zzdqm.zztj();
        if (zztj == null || zztj.getWindow() == null) {
            i2 = zzb;
            i = zzb2;
        } else {
            zzbt.zzel();
            int[] zzf = zzaij.zzf(zztj);
            zzlc.zzij();
            int zzb3 = zzako.zzb(this.zzaxo, zzf[0]);
            zzlc.zzij();
            i = zzako.zzb(this.zzaxo, zzf[1]);
            i2 = zzb3;
        }
        int i3 = this.zzcly;
        if (i3 == zzb && this.zzclz == zzb2 && this.zzcmb == i2 && this.zzcmc == i) {
            return false;
        }
        if (!(i3 == zzb && this.zzclz == zzb2)) {
            z = true;
        }
        this.zzcly = zzb;
        this.zzclz = zzb2;
        this.zzcmb = i2;
        this.zzcmc = i;
        new zzyn(this).zza(zzb, zzb2, i2, i, this.zzaxo.density, this.zzavz.getDefaultDisplay().getRotation());
        return z;
    }

    private final synchronized void zzvb() {
        Boolean zzpw = zzbt.zzep().zzpw();
        this.zzdds = zzpw;
        if (zzpw == null) {
            try {
                evaluateJavascript("(function(){})()", (ValueCallback<String>) null);
                zza((Boolean) true);
            } catch (IllegalStateException unused) {
                zza((Boolean) false);
            }
        }
    }

    private final void zzvc() {
        zzoo.zza(this.zzdrh.zzjn(), this.zzdnt, "aeh2");
    }

    private final synchronized void zzvd() {
        if (!this.zzdqu) {
            if (!this.zzdqr.zzvl()) {
                if (Build.VERSION.SDK_INT < 18) {
                    zzahw.zzby("Disabling hardware acceleration on an AdView.");
                    zzve();
                    return;
                }
                zzahw.zzby("Enabling hardware acceleration on an AdView.");
                zzvf();
                return;
            }
        }
        zzahw.zzby("Enabling hardware acceleration on an overlay.");
        zzvf();
    }

    private final synchronized void zzve() {
        if (!this.zzdqv) {
            zzbt.zzen().zzy(this);
        }
        this.zzdqv = true;
    }

    private final synchronized void zzvf() {
        if (this.zzdqv) {
            zzbt.zzen().zzx(this);
        }
        this.zzdqv = false;
    }

    private final synchronized void zzvg() {
        this.zzdrl = null;
    }

    private final void zzvh() {
        zzov zzjn;
        zzou zzou = this.zzdrh;
        if (zzou != null && (zzjn = zzou.zzjn()) != null && zzbt.zzep().zzpv() != null) {
            zzbt.zzep().zzpv().zza(zzjn);
        }
    }

    public final synchronized void destroy() {
        zzvh();
        this.zzavk.zzrw();
        zzd zzd = this.zzdqq;
        if (zzd != null) {
            zzd.close();
            this.zzdqq.onDestroy();
            this.zzdqq = null;
        }
        this.zzdqp.reset();
        if (!this.zzdqt) {
            zzbt.zzff();
            zzanx.zzb((zzann) this);
            zzvg();
            this.zzdqt = true;
            zzahw.v("Initiating WebView self destruct sequence in 3...");
            zzahw.v("Loading blank page in WebView, 2...");
            zzdh("about:blank");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0013, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void evaluateJavascript(java.lang.String r2, android.webkit.ValueCallback<java.lang.String> r3) {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r0 = r1.isDestroyed()     // Catch:{ all -> 0x0019 }
            if (r0 == 0) goto L_0x0014
            java.lang.String r2 = "The webview is destroyed. Ignoring action."
            com.google.android.gms.internal.zzahw.zzcz(r2)     // Catch:{ all -> 0x0019 }
            if (r3 == 0) goto L_0x0012
            r2 = 0
            r3.onReceiveValue(r2)     // Catch:{ all -> 0x0019 }
        L_0x0012:
            monitor-exit(r1)
            return
        L_0x0014:
            super.evaluateJavascript(r2, r3)     // Catch:{ all -> 0x0019 }
            monitor-exit(r1)
            return
        L_0x0019:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzaor.evaluateJavascript(java.lang.String, android.webkit.ValueCallback):void");
    }

    /* access modifiers changed from: protected */
    public final void finalize() throws Throwable {
        try {
            synchronized (this) {
                if (!this.zzdqt) {
                    this.zzdqp.reset();
                    zzbt.zzff();
                    zzanx.zzb((zzann) this);
                    zzvg();
                    zzqc();
                }
            }
            super.finalize();
        } catch (Throwable th) {
            super.finalize();
            throw th;
        }
    }

    public final View.OnClickListener getOnClickListener() {
        return (View.OnClickListener) this.zzdri.get();
    }

    public final synchronized String getRequestId() {
        return this.zzcwj;
    }

    public final synchronized int getRequestedOrientation() {
        return this.zzdqw;
    }

    public final View getView() {
        return this;
    }

    public final WebView getWebView() {
        return this;
    }

    public final synchronized boolean isDestroyed() {
        return this.zzdqt;
    }

    public final synchronized void loadData(String str, String str2, String str3) {
        if (!isDestroyed()) {
            super.loadData(str, str2, str3);
        } else {
            zzahw.zzcz("The webview is destroyed. Ignoring action.");
        }
    }

    public final synchronized void loadDataWithBaseURL(String str, String str2, String str3, String str4, String str5) {
        if (!isDestroyed()) {
            super.loadDataWithBaseURL(str, str2, str3, str4, str5);
        } else {
            zzahw.zzcz("The webview is destroyed. Ignoring action.");
        }
    }

    public final synchronized void loadUrl(String str) {
        if (!isDestroyed()) {
            try {
                super.loadUrl(str);
            } catch (Exception | IncompatibleClassChangeError | NoClassDefFoundError e) {
                zzbt.zzep().zza(e, "AdWebViewImpl.loadUrl");
                zzahw.zzc("Could not call loadUrl. ", e);
            }
        } else {
            zzahw.zzcz("The webview is destroyed. Ignoring action.");
        }
    }

    /* access modifiers changed from: protected */
    public final synchronized void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isDestroyed()) {
            this.zzavk.onAttachedToWindow();
        }
        boolean z = this.zzdra;
        zzaog zzaog = this.zzdqp;
        if (zzaog != null && zzaog.zzuo()) {
            if (!this.zzdrb) {
                ViewTreeObserver.OnGlobalLayoutListener zzup = this.zzdqp.zzup();
                if (zzup != null) {
                    zzbt.zzfg();
                    zzaml.zza((View) this, zzup);
                }
                ViewTreeObserver.OnScrollChangedListener zzuq = this.zzdqp.zzuq();
                if (zzuq != null) {
                    zzbt.zzfg();
                    zzaml.zza((View) this, zzuq);
                }
                this.zzdrb = true;
            }
            zzva();
            z = true;
        }
        zzal(z);
    }

    /* access modifiers changed from: protected */
    public final void onDetachedFromWindow() {
        zzaog zzaog;
        synchronized (this) {
            if (!isDestroyed()) {
                this.zzavk.onDetachedFromWindow();
            }
            super.onDetachedFromWindow();
            if (this.zzdrb && (zzaog = this.zzdqp) != null && zzaog.zzuo() && getViewTreeObserver() != null && getViewTreeObserver().isAlive()) {
                ViewTreeObserver.OnGlobalLayoutListener zzup = this.zzdqp.zzup();
                if (zzup != null) {
                    zzbt.zzen().zza(getViewTreeObserver(), zzup);
                }
                ViewTreeObserver.OnScrollChangedListener zzuq = this.zzdqp.zzuq();
                if (zzuq != null) {
                    getViewTreeObserver().removeOnScrollChangedListener(zzuq);
                }
                this.zzdrb = false;
            }
        }
        zzal(false);
    }

    public final void onDownloadStart(String str, String str2, String str3, String str4, long j) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(Uri.parse(str), str4);
            zzbt.zzel();
            zzaij.zza(getContext(), intent);
        } catch (ActivityNotFoundException unused) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 51 + String.valueOf(str4).length());
            sb.append("Couldn't find an Activity to view url/mimetype: ");
            sb.append(str);
            sb.append(" / ");
            sb.append(str4);
            zzahw.zzby(sb.toString());
        }
    }

    /* access modifiers changed from: protected */
    public final void onDraw(Canvas canvas) {
        if (!isDestroyed()) {
            if (Build.VERSION.SDK_INT != 21 || !canvas.isHardwareAccelerated() || isAttachedToWindow()) {
                super.onDraw(canvas);
                zzaog zzaog = this.zzdqp;
                if (zzaog != null && zzaog.zzuy() != null) {
                    this.zzdqp.zzuy().zzdb();
                }
            }
        }
    }

    public final boolean onGenericMotionEvent(MotionEvent motionEvent) {
        if (((Boolean) zzlc.zzio().zzd(zzoi.zzbou)).booleanValue()) {
            float axisValue = motionEvent.getAxisValue(9);
            float axisValue2 = motionEvent.getAxisValue(10);
            if (motionEvent.getActionMasked() == 8) {
                if (axisValue > 0.0f && !canScrollVertically(-1)) {
                    return false;
                }
                if (axisValue < 0.0f && !canScrollVertically(1)) {
                    return false;
                }
                if (axisValue2 > 0.0f && !canScrollHorizontally(-1)) {
                    return false;
                }
                if (axisValue2 < 0.0f && !canScrollHorizontally(1)) {
                    return false;
                }
            }
        }
        return super.onGenericMotionEvent(motionEvent);
    }

    public final void onGlobalLayout() {
        boolean zzva = zzva();
        zzd zztw = zztw();
        if (zztw != null && zzva) {
            zztw.zznm();
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x00ff  */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x011b  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x013b  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x0140  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x019e A[SYNTHETIC, Splitter:B:97:0x019e] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:104:0x01b4=Splitter:B:104:0x01b4, B:52:0x00b8=Splitter:B:52:0x00b8} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void onMeasure(int r8, int r9) {
        /*
            r7 = this;
            monitor-enter(r7)
            boolean r0 = r7.isDestroyed()     // Catch:{ all -> 0x01b9 }
            r1 = 0
            if (r0 == 0) goto L_0x000d
            r7.setMeasuredDimension(r1, r1)     // Catch:{ all -> 0x01b9 }
            monitor-exit(r7)
            return
        L_0x000d:
            boolean r0 = r7.isInEditMode()     // Catch:{ all -> 0x01b9 }
            if (r0 != 0) goto L_0x01b4
            boolean r0 = r7.zzdqu     // Catch:{ all -> 0x01b9 }
            if (r0 != 0) goto L_0x01b4
            com.google.android.gms.internal.zzaqa r0 = r7.zzdqr     // Catch:{ all -> 0x01b9 }
            boolean r0 = r0.zzvm()     // Catch:{ all -> 0x01b9 }
            if (r0 == 0) goto L_0x0021
            goto L_0x01b4
        L_0x0021:
            com.google.android.gms.internal.zzaqa r0 = r7.zzdqr     // Catch:{ all -> 0x01b9 }
            boolean r0 = r0.zzvn()     // Catch:{ all -> 0x01b9 }
            if (r0 == 0) goto L_0x006d
            com.google.android.gms.internal.zzaou r0 = r7.zzth()     // Catch:{ all -> 0x01b9 }
            r1 = 0
            if (r0 == 0) goto L_0x0035
            float r0 = r0.getAspectRatio()     // Catch:{ all -> 0x01b9 }
            goto L_0x0036
        L_0x0035:
            r0 = 0
        L_0x0036:
            int r1 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r1 != 0) goto L_0x003f
            super.onMeasure(r8, r9)     // Catch:{ all -> 0x01b9 }
            monitor-exit(r7)
            return
        L_0x003f:
            int r8 = android.view.View.MeasureSpec.getSize(r8)     // Catch:{ all -> 0x01b9 }
            int r9 = android.view.View.MeasureSpec.getSize(r9)     // Catch:{ all -> 0x01b9 }
            float r1 = (float) r9     // Catch:{ all -> 0x01b9 }
            float r1 = r1 * r0
            int r1 = (int) r1     // Catch:{ all -> 0x01b9 }
            float r2 = (float) r8     // Catch:{ all -> 0x01b9 }
            float r2 = r2 / r0
            int r2 = (int) r2     // Catch:{ all -> 0x01b9 }
            if (r9 != 0) goto L_0x0058
            if (r2 == 0) goto L_0x0058
            float r9 = (float) r2     // Catch:{ all -> 0x01b9 }
            float r9 = r9 * r0
            int r1 = (int) r9     // Catch:{ all -> 0x01b9 }
            r9 = r2
            goto L_0x0060
        L_0x0058:
            if (r8 != 0) goto L_0x0060
            if (r1 == 0) goto L_0x0060
            float r8 = (float) r1     // Catch:{ all -> 0x01b9 }
            float r8 = r8 / r0
            int r2 = (int) r8     // Catch:{ all -> 0x01b9 }
            r8 = r1
        L_0x0060:
            int r8 = java.lang.Math.min(r1, r8)     // Catch:{ all -> 0x01b9 }
            int r9 = java.lang.Math.min(r2, r9)     // Catch:{ all -> 0x01b9 }
            r7.setMeasuredDimension(r8, r9)     // Catch:{ all -> 0x01b9 }
            monitor-exit(r7)
            return
        L_0x006d:
            com.google.android.gms.internal.zzaqa r0 = r7.zzdqr     // Catch:{ all -> 0x01b9 }
            boolean r0 = r0.isFluid()     // Catch:{ all -> 0x01b9 }
            if (r0 == 0) goto L_0x00bd
            com.google.android.gms.internal.zzny<java.lang.Boolean> r0 = com.google.android.gms.internal.zzoi.zzbsx     // Catch:{ all -> 0x01b9 }
            com.google.android.gms.internal.zzog r1 = com.google.android.gms.internal.zzlc.zzio()     // Catch:{ all -> 0x01b9 }
            java.lang.Object r0 = r1.zzd(r0)     // Catch:{ all -> 0x01b9 }
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x01b9 }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x01b9 }
            if (r0 != 0) goto L_0x00b8
            boolean r0 = com.google.android.gms.common.util.zzs.zzant()     // Catch:{ all -> 0x01b9 }
            if (r0 != 0) goto L_0x008e
            goto L_0x00b8
        L_0x008e:
            java.lang.String r0 = "/contentHeight"
            com.google.android.gms.internal.zzaos r1 = new com.google.android.gms.internal.zzaos     // Catch:{ all -> 0x01b9 }
            r1.<init>(r7)     // Catch:{ all -> 0x01b9 }
            r7.zza((java.lang.String) r0, (com.google.android.gms.ads.internal.gmsg.zzt<? super com.google.android.gms.internal.zzaof>) r1)     // Catch:{ all -> 0x01b9 }
            java.lang.String r0 = "(function() {  var height = -1;  if (document.body) {    height = document.body.offsetHeight;  } else if (document.documentElement) {    height = document.documentElement.offsetHeight;  }  var url = 'gmsg://mobileads.google.com/contentHeight?';  url += 'height=' + height;  try {    window.googleAdsJsInterface.notify(url);  } catch (e) {    var frame = document.getElementById('afma-notify-fluid');    if (!frame) {      frame = document.createElement('IFRAME');      frame.id = 'afma-notify-fluid';      frame.style.display = 'none';      var body = document.body || document.documentElement;      body.appendChild(frame);    }    frame.src = url;  }})();"
            r7.zzdi(r0)     // Catch:{ all -> 0x01b9 }
            android.util.DisplayMetrics r0 = r7.zzaxo     // Catch:{ all -> 0x01b9 }
            float r0 = r0.density     // Catch:{ all -> 0x01b9 }
            int r8 = android.view.View.MeasureSpec.getSize(r8)     // Catch:{ all -> 0x01b9 }
            int r1 = r7.zzdre     // Catch:{ all -> 0x01b9 }
            r2 = -1
            if (r1 == r2) goto L_0x00af
            float r9 = (float) r1     // Catch:{ all -> 0x01b9 }
            float r9 = r9 * r0
            int r9 = (int) r9     // Catch:{ all -> 0x01b9 }
            goto L_0x00b3
        L_0x00af:
            int r9 = android.view.View.MeasureSpec.getSize(r9)     // Catch:{ all -> 0x01b9 }
        L_0x00b3:
            r7.setMeasuredDimension(r8, r9)     // Catch:{ all -> 0x01b9 }
            monitor-exit(r7)
            return
        L_0x00b8:
            super.onMeasure(r8, r9)     // Catch:{ all -> 0x01b9 }
            monitor-exit(r7)
            return
        L_0x00bd:
            com.google.android.gms.internal.zzaqa r0 = r7.zzdqr     // Catch:{ all -> 0x01b9 }
            boolean r0 = r0.zzvl()     // Catch:{ all -> 0x01b9 }
            if (r0 == 0) goto L_0x00d2
            android.util.DisplayMetrics r8 = r7.zzaxo     // Catch:{ all -> 0x01b9 }
            int r8 = r8.widthPixels     // Catch:{ all -> 0x01b9 }
            android.util.DisplayMetrics r9 = r7.zzaxo     // Catch:{ all -> 0x01b9 }
            int r9 = r9.heightPixels     // Catch:{ all -> 0x01b9 }
            r7.setMeasuredDimension(r8, r9)     // Catch:{ all -> 0x01b9 }
            monitor-exit(r7)
            return
        L_0x00d2:
            int r0 = android.view.View.MeasureSpec.getMode(r8)     // Catch:{ all -> 0x01b9 }
            int r8 = android.view.View.MeasureSpec.getSize(r8)     // Catch:{ all -> 0x01b9 }
            int r2 = android.view.View.MeasureSpec.getMode(r9)     // Catch:{ all -> 0x01b9 }
            int r9 = android.view.View.MeasureSpec.getSize(r9)     // Catch:{ all -> 0x01b9 }
            r3 = 1073741824(0x40000000, float:2.0)
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r5 = 2147483647(0x7fffffff, float:NaN)
            if (r0 == r4) goto L_0x00f2
            if (r0 != r3) goto L_0x00ee
            goto L_0x00f2
        L_0x00ee:
            r0 = 2147483647(0x7fffffff, float:NaN)
            goto L_0x00f3
        L_0x00f2:
            r0 = r8
        L_0x00f3:
            if (r2 == r4) goto L_0x00f7
            if (r2 != r3) goto L_0x00f8
        L_0x00f7:
            r5 = r9
        L_0x00f8:
            com.google.android.gms.internal.zzaqa r2 = r7.zzdqr     // Catch:{ all -> 0x01b9 }
            int r2 = r2.widthPixels     // Catch:{ all -> 0x01b9 }
            r3 = 1
            if (r2 > r0) goto L_0x0108
            com.google.android.gms.internal.zzaqa r2 = r7.zzdqr     // Catch:{ all -> 0x01b9 }
            int r2 = r2.heightPixels     // Catch:{ all -> 0x01b9 }
            if (r2 <= r5) goto L_0x0106
            goto L_0x0108
        L_0x0106:
            r2 = 0
            goto L_0x0109
        L_0x0108:
            r2 = 1
        L_0x0109:
            com.google.android.gms.internal.zzny<java.lang.Boolean> r4 = com.google.android.gms.internal.zzoi.zzbvq     // Catch:{ all -> 0x01b9 }
            com.google.android.gms.internal.zzog r6 = com.google.android.gms.internal.zzlc.zzio()     // Catch:{ all -> 0x01b9 }
            java.lang.Object r4 = r6.zzd(r4)     // Catch:{ all -> 0x01b9 }
            java.lang.Boolean r4 = (java.lang.Boolean) r4     // Catch:{ all -> 0x01b9 }
            boolean r4 = r4.booleanValue()     // Catch:{ all -> 0x01b9 }
            if (r4 == 0) goto L_0x013c
            com.google.android.gms.internal.zzaqa r4 = r7.zzdqr     // Catch:{ all -> 0x01b9 }
            int r4 = r4.widthPixels     // Catch:{ all -> 0x01b9 }
            float r4 = (float) r4     // Catch:{ all -> 0x01b9 }
            float r6 = r7.zzdqo     // Catch:{ all -> 0x01b9 }
            float r4 = r4 / r6
            float r0 = (float) r0     // Catch:{ all -> 0x01b9 }
            float r0 = r0 / r6
            int r0 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r0 > 0) goto L_0x0138
            com.google.android.gms.internal.zzaqa r0 = r7.zzdqr     // Catch:{ all -> 0x01b9 }
            int r0 = r0.heightPixels     // Catch:{ all -> 0x01b9 }
            float r0 = (float) r0     // Catch:{ all -> 0x01b9 }
            float r4 = r7.zzdqo     // Catch:{ all -> 0x01b9 }
            float r0 = r0 / r4
            float r5 = (float) r5     // Catch:{ all -> 0x01b9 }
            float r5 = r5 / r4
            int r0 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r0 > 0) goto L_0x0138
            goto L_0x0139
        L_0x0138:
            r3 = 0
        L_0x0139:
            if (r2 == 0) goto L_0x013c
            r2 = r3
        L_0x013c:
            r0 = 8
            if (r2 == 0) goto L_0x019e
            android.util.DisplayMetrics r2 = r7.zzaxo     // Catch:{ all -> 0x01b9 }
            float r2 = r2.density     // Catch:{ all -> 0x01b9 }
            com.google.android.gms.internal.zzaqa r2 = r7.zzdqr     // Catch:{ all -> 0x01b9 }
            int r2 = r2.widthPixels     // Catch:{ all -> 0x01b9 }
            float r2 = (float) r2     // Catch:{ all -> 0x01b9 }
            float r3 = r7.zzdqo     // Catch:{ all -> 0x01b9 }
            float r2 = r2 / r3
            int r2 = (int) r2     // Catch:{ all -> 0x01b9 }
            com.google.android.gms.internal.zzaqa r3 = r7.zzdqr     // Catch:{ all -> 0x01b9 }
            int r3 = r3.heightPixels     // Catch:{ all -> 0x01b9 }
            float r3 = (float) r3     // Catch:{ all -> 0x01b9 }
            float r4 = r7.zzdqo     // Catch:{ all -> 0x01b9 }
            float r3 = r3 / r4
            int r3 = (int) r3     // Catch:{ all -> 0x01b9 }
            float r8 = (float) r8     // Catch:{ all -> 0x01b9 }
            float r8 = r8 / r4
            int r8 = (int) r8     // Catch:{ all -> 0x01b9 }
            float r9 = (float) r9     // Catch:{ all -> 0x01b9 }
            float r9 = r9 / r4
            int r9 = (int) r9     // Catch:{ all -> 0x01b9 }
            r4 = 103(0x67, float:1.44E-43)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x01b9 }
            r5.<init>(r4)     // Catch:{ all -> 0x01b9 }
            java.lang.String r4 = "Not enough space to show ad. Needs "
            r5.append(r4)     // Catch:{ all -> 0x01b9 }
            r5.append(r2)     // Catch:{ all -> 0x01b9 }
            java.lang.String r2 = "x"
            r5.append(r2)     // Catch:{ all -> 0x01b9 }
            r5.append(r3)     // Catch:{ all -> 0x01b9 }
            java.lang.String r2 = " dp, but only has "
            r5.append(r2)     // Catch:{ all -> 0x01b9 }
            r5.append(r8)     // Catch:{ all -> 0x01b9 }
            java.lang.String r8 = "x"
            r5.append(r8)     // Catch:{ all -> 0x01b9 }
            r5.append(r9)     // Catch:{ all -> 0x01b9 }
            java.lang.String r8 = " dp."
            r5.append(r8)     // Catch:{ all -> 0x01b9 }
            java.lang.String r8 = r5.toString()     // Catch:{ all -> 0x01b9 }
            com.google.android.gms.internal.zzahw.zzcz(r8)     // Catch:{ all -> 0x01b9 }
            int r8 = r7.getVisibility()     // Catch:{ all -> 0x01b9 }
            if (r8 == r0) goto L_0x0199
            r8 = 4
            r7.setVisibility(r8)     // Catch:{ all -> 0x01b9 }
        L_0x0199:
            r7.setMeasuredDimension(r1, r1)     // Catch:{ all -> 0x01b9 }
            monitor-exit(r7)
            return
        L_0x019e:
            int r8 = r7.getVisibility()     // Catch:{ all -> 0x01b9 }
            if (r8 == r0) goto L_0x01a7
            r7.setVisibility(r1)     // Catch:{ all -> 0x01b9 }
        L_0x01a7:
            com.google.android.gms.internal.zzaqa r8 = r7.zzdqr     // Catch:{ all -> 0x01b9 }
            int r8 = r8.widthPixels     // Catch:{ all -> 0x01b9 }
            com.google.android.gms.internal.zzaqa r9 = r7.zzdqr     // Catch:{ all -> 0x01b9 }
            int r9 = r9.heightPixels     // Catch:{ all -> 0x01b9 }
            r7.setMeasuredDimension(r8, r9)     // Catch:{ all -> 0x01b9 }
            monitor-exit(r7)
            return
        L_0x01b4:
            super.onMeasure(r8, r9)     // Catch:{ all -> 0x01b9 }
            monitor-exit(r7)
            return
        L_0x01b9:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzaor.onMeasure(int, int):void");
    }

    public final void onPause() {
        if (!isDestroyed()) {
            try {
                super.onPause();
            } catch (Exception e) {
                zzahw.zzb("Could not pause webview.", e);
            }
        }
    }

    public final void onResume() {
        if (!isDestroyed()) {
            try {
                super.onResume();
            } catch (Exception e) {
                zzahw.zzb("Could not resume webview.", e);
            }
        }
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.zzdqp.zzuo()) {
            synchronized (this) {
                zzpt zzpt = this.zzdrc;
                if (zzpt != null) {
                    zzpt.zzc(motionEvent);
                }
            }
        } else {
            zzcv zzcv = this.zzbyz;
            if (zzcv != null) {
                zzcv.zza(motionEvent);
            }
        }
        if (isDestroyed()) {
            return false;
        }
        return super.onTouchEvent(motionEvent);
    }

    public final void setContext(Context context) {
        this.zzdqm.setBaseContext(context);
        this.zzavk.zzi(this.zzdqm.zztj());
    }

    public final void setOnClickListener(View.OnClickListener onClickListener) {
        this.zzdri = new WeakReference<>(onClickListener);
        super.setOnClickListener(onClickListener);
    }

    public final synchronized void setRequestedOrientation(int i) {
        this.zzdqw = i;
        zzd zzd = this.zzdqq;
        if (zzd != null) {
            zzd.setRequestedOrientation(i);
        }
    }

    public final void setWebViewClient(WebViewClient webViewClient) {
        super.setWebViewClient(webViewClient);
        if (webViewClient instanceof zzaog) {
            this.zzdqp = (zzaog) webViewClient;
        }
    }

    public final void stopLoading() {
        if (!isDestroyed()) {
            try {
                super.stopLoading();
            } catch (Exception e) {
                zzahw.zzb("Could not stop loading webview.", e);
            }
        }
    }

    public final void zza(zzc zzc) {
        this.zzdqp.zza(zzc);
    }

    public final synchronized void zza(zzd zzd) {
        this.zzdqq = zzd;
    }

    public final synchronized void zza(zzaou zzaou) {
        if (this.zzdqz != null) {
            zzahw.e("Attempt to create multiple AdWebViewVideoControllers.");
        } else {
            this.zzdqz = zzaou;
        }
    }

    public final synchronized void zza(zzaqa zzaqa) {
        this.zzdqr = zzaqa;
        requestLayout();
    }

    public final void zza(zzgu zzgu) {
        synchronized (this) {
            this.zzdra = zzgu.zzakc;
        }
        zzal(zzgu.zzakc);
    }

    public final void zza(String str, zzt<? super zzaof> zzt) {
        zzaog zzaog = this.zzdqp;
        if (zzaog != null) {
            zzaog.zza(str, zzt);
        }
    }

    public final void zza(String str, Map<String, ?> map) {
        try {
            zza(str, zzbt.zzel().zzq(map));
        } catch (JSONException unused) {
            zzahw.zzcz("Could not convert parameters to JSON.");
        }
    }

    public final void zza(String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        String jSONObject2 = jSONObject.toString();
        StringBuilder sb = new StringBuilder();
        sb.append("(window.AFMA_ReceiveMessage || function() {})('");
        sb.append(str);
        sb.append("'");
        sb.append(",");
        sb.append(jSONObject2);
        sb.append(");");
        String valueOf = String.valueOf(sb.toString());
        zzahw.zzby(valueOf.length() != 0 ? "Dispatching AFMA event: ".concat(valueOf) : new String("Dispatching AFMA event: "));
        zzdi(sb.toString());
    }

    public final void zza(boolean z, int i) {
        this.zzdqp.zza(z, i);
    }

    public final void zza(boolean z, int i, String str) {
        this.zzdqp.zza(z, i, str);
    }

    public final void zza(boolean z, int i, String str, String str2) {
        this.zzdqp.zza(z, i, str, str2);
    }

    public final void zzag(int i) {
        if (i == 0) {
            zzoo.zza(this.zzdrh.zzjn(), this.zzdnt, "aebb2");
        }
        zzvc();
        if (this.zzdrh.zzjn() != null) {
            this.zzdrh.zzjn().zzf("close_type", String.valueOf(i));
        }
        HashMap hashMap = new HashMap(2);
        hashMap.put("closetype", String.valueOf(i));
        hashMap.put("version", this.zzapq.zzcu);
        zza("onhide", (Map<String, ?>) hashMap);
    }

    public final void zzag(boolean z) {
        this.zzdqp.zzag(z);
    }

    public final synchronized void zzah(boolean z) {
        boolean z2 = z != this.zzdqu;
        this.zzdqu = z;
        zzvd();
        if (z2) {
            new zzyn(this).zzbo(z ? "expanded" : "default");
        }
    }

    public final synchronized void zzai(boolean z) {
        zzd zzd = this.zzdqq;
        if (zzd != null) {
            zzd.zza(this.zzdqp.zzfz(), z);
        } else {
            this.zzdqs = z;
        }
    }

    public final synchronized void zzaj(boolean z) {
        this.zzdqx = z;
    }

    public final synchronized void zzak(boolean z) {
        zzd zzd;
        int i = this.zzdrd + (z ? 1 : -1);
        this.zzdrd = i;
        if (i <= 0 && (zzd = this.zzdqq) != null) {
            zzd.zznp();
        }
    }

    public final synchronized void zzb(zzd zzd) {
        this.zzdrj = zzd;
    }

    public final synchronized void zzb(zzpt zzpt) {
        this.zzdrc = zzpt;
    }

    public final void zzb(String str, zzt<? super zzaof> zzt) {
        zzaog zzaog = this.zzdqp;
        if (zzaog != null) {
            zzaog.zzb(str, zzt);
        }
    }

    public final void zzb(String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        String jSONObject2 = jSONObject.toString();
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 3 + String.valueOf(jSONObject2).length());
        sb.append(str);
        sb.append("(");
        sb.append(jSONObject2);
        sb.append(");");
        zzdi(sb.toString());
    }

    public final zzv zzbo() {
        return this.zzanp;
    }

    public final synchronized void zzc(String str, String str2, String str3) {
        if (!isDestroyed()) {
            if (((Boolean) zzlc.zzio().zzd(zzoi.zzbow)).booleanValue()) {
                str2 = zzapp.zzb(str2, zzapp.zzvi());
            }
            super.loadDataWithBaseURL(str, str2, "text/html", "UTF-8", str3);
            return;
        }
        zzahw.zzcz("The webview is destroyed. Ignoring action.");
    }

    public final synchronized void zzcp() {
        this.zzdqy = true;
        zzbm zzbm = this.zzdqn;
        if (zzbm != null) {
            zzbm.zzcp();
        }
    }

    public final synchronized void zzcq() {
        this.zzdqy = false;
        zzbm zzbm = this.zzdqn;
        if (zzbm != null) {
            zzbm.zzcq();
        }
    }

    public final synchronized void zzde(String str) {
        if (str == null) {
            str = "";
        }
        this.zzcwj = str;
    }

    public final void zznn() {
        if (this.zzdrf == null) {
            zzoo.zza(this.zzdrh.zzjn(), this.zzdnt, "aes2");
            zzot zzb = zzoo.zzb(this.zzdrh.zzjn());
            this.zzdrf = zzb;
            this.zzdrh.zza("native:view_show", zzb);
        }
        HashMap hashMap = new HashMap(1);
        hashMap.put("version", this.zzapq.zzcu);
        zza("onshow", (Map<String, ?>) hashMap);
    }

    public final void zzno() {
        zzd zztw = zztw();
        if (zztw != null) {
            zztw.zzno();
        }
    }

    public final zzane zztg() {
        return null;
    }

    public final synchronized zzaou zzth() {
        return this.zzdqz;
    }

    public final zzot zzti() {
        return this.zzdnt;
    }

    public final Activity zztj() {
        return this.zzdqm.zztj();
    }

    public final zzou zztk() {
        return this.zzdrh;
    }

    public final zzala zztl() {
        return this.zzapq;
    }

    public final int zztm() {
        return getMeasuredHeight();
    }

    public final int zztn() {
        return getMeasuredWidth();
    }

    public final void zztt() {
        zzvc();
        HashMap hashMap = new HashMap(1);
        hashMap.put("version", this.zzapq.zzcu);
        zza("onhide", (Map<String, ?>) hashMap);
    }

    public final void zztu() {
        HashMap hashMap = new HashMap(3);
        hashMap.put("app_muted", String.valueOf(zzbt.zzfj().zzdq()));
        hashMap.put("app_volume", String.valueOf(zzbt.zzfj().zzdp()));
        hashMap.put("device_volume", String.valueOf(zzaja.zzav(getContext())));
        zza("volume", (Map<String, ?>) hashMap);
    }

    public final Context zztv() {
        return this.zzdqm.zztv();
    }

    public final synchronized zzd zztw() {
        return this.zzdqq;
    }

    public final synchronized zzd zztx() {
        return this.zzdrj;
    }

    public final synchronized zzaqa zzty() {
        return this.zzdqr;
    }

    public final synchronized String zztz() {
        return this.zzalv;
    }

    public final /* synthetic */ zzapu zzua() {
        return this.zzdqp;
    }

    public final synchronized boolean zzub() {
        return this.zzdqs;
    }

    public final zzcv zzuc() {
        return this.zzbyz;
    }

    public final synchronized boolean zzud() {
        return this.zzdqu;
    }

    public final synchronized void zzue() {
        zzahw.v("Destroying WebView!");
        zzqc();
        zzaij.zzdfn.post(new zzaot(this));
    }

    public final synchronized boolean zzuf() {
        return this.zzdqx;
    }

    public final synchronized boolean zzug() {
        return this.zzdqy;
    }

    public final synchronized boolean zzuh() {
        return this.zzdrd > 0;
    }

    public final void zzui() {
        this.zzavk.zzrv();
    }

    public final void zzuj() {
        if (this.zzdrg == null) {
            zzot zzb = zzoo.zzb(this.zzdrh.zzjn());
            this.zzdrg = zzb;
            this.zzdrh.zza("native:view_load", zzb);
        }
    }

    public final synchronized zzpt zzuk() {
        return this.zzdrc;
    }

    public final void zzul() {
        setBackgroundColor(0);
    }

    public final void zzum() {
        zzahw.v("Cannot add text view to inner AdWebView");
    }
}
