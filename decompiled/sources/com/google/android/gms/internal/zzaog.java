package com.google.android.gms.internal;

import android.content.Context;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.core.app.NotificationCompat;
import androidx.core.view.ViewCompat;
import com.google.android.gms.ads.internal.gmsg.zza;
import com.google.android.gms.ads.internal.gmsg.zzaa;
import com.google.android.gms.ads.internal.gmsg.zzab;
import com.google.android.gms.ads.internal.gmsg.zzb;
import com.google.android.gms.ads.internal.gmsg.zzd;
import com.google.android.gms.ads.internal.gmsg.zzx;
import com.google.android.gms.ads.internal.gmsg.zzz;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.overlay.zzc;
import com.google.android.gms.ads.internal.overlay.zzl;
import com.google.android.gms.ads.internal.overlay.zzn;
import com.google.android.gms.ads.internal.overlay.zzt;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.iid.InstanceID;
import com.google.android.gms.search.SearchAuth;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@zzabh
public class zzaog extends WebViewClient implements zzapu {
    private static final String[] zzdpb = {"UNKNOWN", "HOST_LOOKUP", "UNSUPPORTED_AUTH_SCHEME", "AUTHENTICATION", "PROXY_AUTHENTICATION", "CONNECT", "IO", InstanceID.ERROR_TIMEOUT, "REDIRECT_LOOP", "UNSUPPORTED_SCHEME", "FAILED_SSL_HANDSHAKE", "BAD_URL", "FILE", "FILE_NOT_FOUND", "TOO_MANY_REQUESTS"};
    private static final String[] zzdpc = {"NOT_YET_VALID", "EXPIRED", "ID_MISMATCH", "UNTRUSTED", "DATE_INVALID", "INVALID"};
    private final Object mLock;
    protected zzagq zzaop;
    private boolean zzavq;
    private zzkf zzbgt;
    private zzb zzcbc;
    private zzx zzcck;
    private zzw zzccm;
    private zzyd zzccn;
    private zzyo zzcco;
    private zzt zzccr;
    private zzn zzccs;
    private zzaof zzcct;
    private final HashMap<String, List<com.google.android.gms.ads.internal.gmsg.zzt<? super zzaof>>> zzcou;
    private zzapv zzdpd;
    private zzapw zzdpe;
    private zzapx zzdpf;
    private boolean zzdpg;
    private boolean zzdph;
    private ViewTreeObserver.OnGlobalLayoutListener zzdpi;
    private ViewTreeObserver.OnScrollChangedListener zzdpj;
    private boolean zzdpk;
    private final zzym zzdpl;
    private zzapy zzdpm;
    private boolean zzdpn;
    private boolean zzdpo;
    private int zzdpp;
    private View.OnAttachStateChangeListener zzdpq;

    public zzaog(zzaof zzaof, boolean z) {
        this(zzaof, z, new zzym(zzaof, zzaof.zztv(), new zznu(zzaof.getContext())), (zzyd) null);
    }

    private zzaog(zzaof zzaof, boolean z, zzym zzym, zzyd zzyd) {
        this.zzcou = new HashMap<>();
        this.mLock = new Object();
        this.zzdpg = false;
        this.zzcct = zzaof;
        this.zzavq = z;
        this.zzdpl = zzym;
        this.zzccn = null;
    }

    /* access modifiers changed from: private */
    public final void zza(View view, zzagq zzagq, int i) {
        if (zzagq.zzpf() && i > 0) {
            zzagq.zzq(view);
            if (zzagq.zzpf()) {
                zzaij.zzdfn.postDelayed(new zzaoi(this, view, zzagq, i), 100);
            }
        }
    }

    private final void zza(AdOverlayInfoParcel adOverlayInfoParcel) {
        zzyd zzyd = this.zzccn;
        boolean zznf = zzyd != null ? zzyd.zznf() : false;
        zzbt.zzej();
        zzl.zza(this.zzcct.getContext(), adOverlayInfoParcel, !zznf);
        if (this.zzaop != null) {
            String str = adOverlayInfoParcel.url;
            if (str == null && adOverlayInfoParcel.zzcnj != null) {
                str = adOverlayInfoParcel.zzcnj.url;
            }
            this.zzaop.zzbv(str);
        }
    }

    private final void zzd(Context context, String str, String str2, String str3) {
        String str4;
        if (((Boolean) zzlc.zzio().zzd(zzoi.zzbqr)).booleanValue()) {
            Bundle bundle = new Bundle();
            bundle.putString(NotificationCompat.CATEGORY_ERROR, str);
            bundle.putString("code", str2);
            if (!TextUtils.isEmpty(str3)) {
                Uri parse = Uri.parse(str3);
                if (parse.getHost() != null) {
                    str4 = parse.getHost();
                    bundle.putString("host", str4);
                    zzbt.zzel().zza(context, this.zzcct.zztl().zzcu, "gmob-apps", bundle, true);
                }
            }
            str4 = "";
            bundle.putString("host", str4);
            zzbt.zzel().zza(context, this.zzcct.zztl().zzcu, "gmob-apps", bundle, true);
        }
    }

    private final WebResourceResponse zzdf(String str) throws IOException {
        HttpURLConnection httpURLConnection;
        String zzcn;
        String zzco;
        URL url = new URL(str);
        int i = 0;
        while (true) {
            i++;
            if (i <= 20) {
                URLConnection openConnection = url.openConnection();
                openConnection.setConnectTimeout(SearchAuth.StatusCodes.AUTH_DISABLED);
                openConnection.setReadTimeout(SearchAuth.StatusCodes.AUTH_DISABLED);
                if (openConnection instanceof HttpURLConnection) {
                    httpURLConnection = (HttpURLConnection) openConnection;
                    zzbt.zzel().zza(this.zzcct.getContext(), this.zzcct.zztl().zzcu, false, httpURLConnection);
                    zzaks zzaks = new zzaks();
                    zzaks.zza(httpURLConnection, (byte[]) null);
                    int responseCode = httpURLConnection.getResponseCode();
                    zzbt.zzel();
                    zzcn = zzaij.zzcn(httpURLConnection.getContentType());
                    zzbt.zzel();
                    zzco = zzaij.zzco(httpURLConnection.getContentType());
                    zzaks.zza(httpURLConnection, responseCode);
                    if (responseCode >= 300 && responseCode < 400) {
                        String headerField = httpURLConnection.getHeaderField("Location");
                        if (headerField != null) {
                            URL url2 = new URL(url, headerField);
                            String protocol = url2.getProtocol();
                            if (protocol == null) {
                                zzahw.zzcz("Protocol is null");
                                return null;
                            } else if (protocol.equals("http") || protocol.equals("https")) {
                                String valueOf = String.valueOf(headerField);
                                zzahw.zzby(valueOf.length() != 0 ? "Redirecting to ".concat(valueOf) : new String("Redirecting to "));
                                httpURLConnection.disconnect();
                                url = url2;
                            } else {
                                String valueOf2 = String.valueOf(protocol);
                                zzahw.zzcz(valueOf2.length() != 0 ? "Unsupported scheme: ".concat(valueOf2) : new String("Unsupported scheme: "));
                                return null;
                            }
                        } else {
                            throw new IOException("Missing Location header in redirect");
                        }
                    } else {
                        break;
                    }
                } else {
                    throw new IOException("Invalid protocol.");
                }
            } else {
                StringBuilder sb = new StringBuilder(32);
                sb.append("Too many redirects (20)");
                throw new IOException(sb.toString());
            }
        }
        return new WebResourceResponse(zzcn, zzco, httpURLConnection.getInputStream());
    }

    private final void zzh(Uri uri) {
        String path = uri.getPath();
        List<com.google.android.gms.ads.internal.gmsg.zzt<? super zzaof>> list = this.zzcou.get(path);
        if (list != null) {
            zzbt.zzel();
            Map<String, String> zzf = zzaij.zzf(uri);
            if (zzahw.zzae(2)) {
                String valueOf = String.valueOf(path);
                zzahw.v(valueOf.length() != 0 ? "Received GMSG: ".concat(valueOf) : new String("Received GMSG: "));
                for (String next : zzf.keySet()) {
                    String str = zzf.get(next);
                    StringBuilder sb = new StringBuilder(String.valueOf(next).length() + 4 + String.valueOf(str).length());
                    sb.append("  ");
                    sb.append(next);
                    sb.append(": ");
                    sb.append(str);
                    zzahw.v(sb.toString());
                }
            }
            for (com.google.android.gms.ads.internal.gmsg.zzt zza : list) {
                zza.zza(this.zzcct, zzf);
            }
            return;
        }
        String valueOf2 = String.valueOf(uri);
        StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 32);
        sb2.append("No GMSG handler found for GMSG: ");
        sb2.append(valueOf2);
        zzahw.v(sb2.toString());
    }

    private final void zzus() {
        if (this.zzdpq != null) {
            this.zzcct.getView().removeOnAttachStateChangeListener(this.zzdpq);
        }
    }

    private final void zzux() {
        zzapv zzapv = this.zzdpd;
        if (zzapv != null && ((this.zzdpn && this.zzdpp <= 0) || this.zzdpo)) {
            zzapv.zza(this.zzcct, !this.zzdpo);
            this.zzdpd = null;
        }
        this.zzcct.zzuj();
    }

    public final void onLoadResource(WebView webView, String str) {
        String valueOf = String.valueOf(str);
        zzahw.v(valueOf.length() != 0 ? "Loading resource: ".concat(valueOf) : new String("Loading resource: "));
        Uri parse = Uri.parse(str);
        if ("gmsg".equalsIgnoreCase(parse.getScheme()) && "mobileads.google.com".equalsIgnoreCase(parse.getHost())) {
            zzh(parse);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001d, code lost:
        if (r1 == null) goto L_0x0027;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001f, code lost:
        r1.zzf(r0.zzcct);
        r0.zzdpe = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0027, code lost:
        zzux();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002a, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0018, code lost:
        r0.zzdpn = true;
        r1 = r0.zzdpe;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onPageFinished(android.webkit.WebView r1, java.lang.String r2) {
        /*
            r0 = this;
            java.lang.Object r1 = r0.mLock
            monitor-enter(r1)
            com.google.android.gms.internal.zzaof r2 = r0.zzcct     // Catch:{ all -> 0x002b }
            boolean r2 = r2.isDestroyed()     // Catch:{ all -> 0x002b }
            if (r2 == 0) goto L_0x0017
            java.lang.String r2 = "Blank page loaded, 1..."
            com.google.android.gms.internal.zzahw.v(r2)     // Catch:{ all -> 0x002b }
            com.google.android.gms.internal.zzaof r2 = r0.zzcct     // Catch:{ all -> 0x002b }
            r2.zzue()     // Catch:{ all -> 0x002b }
            monitor-exit(r1)     // Catch:{ all -> 0x002b }
            return
        L_0x0017:
            monitor-exit(r1)     // Catch:{ all -> 0x002b }
            r1 = 1
            r0.zzdpn = r1
            com.google.android.gms.internal.zzapw r1 = r0.zzdpe
            if (r1 == 0) goto L_0x0027
            com.google.android.gms.internal.zzaof r2 = r0.zzcct
            r1.zzf(r2)
            r1 = 0
            r0.zzdpe = r1
        L_0x0027:
            r0.zzux()
            return
        L_0x002b:
            r2 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x002b }
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzaog.onPageFinished(android.webkit.WebView, java.lang.String):void");
    }

    public final void onReceivedError(WebView webView, int i, String str, String str2) {
        String str3;
        if (i < 0) {
            int i2 = (-i) - 1;
            String[] strArr = zzdpb;
            if (i2 < strArr.length) {
                str3 = strArr[i2];
                zzd(this.zzcct.getContext(), "http_err", str3, str2);
                super.onReceivedError(webView, i, str, str2);
            }
        }
        str3 = String.valueOf(i);
        zzd(this.zzcct.getContext(), "http_err", str3, str2);
        super.onReceivedError(webView, i, str, str2);
    }

    public final void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        String str;
        if (sslError != null) {
            int primaryError = sslError.getPrimaryError();
            if (primaryError >= 0) {
                String[] strArr = zzdpc;
                if (primaryError < strArr.length) {
                    str = strArr[primaryError];
                    zzd(this.zzcct.getContext(), "ssl_err", str, zzbt.zzen().zza(sslError));
                }
            }
            str = String.valueOf(primaryError);
            zzd(this.zzcct.getContext(), "ssl_err", str, zzbt.zzen().zza(sslError));
        }
        super.onReceivedSslError(webView, sslErrorHandler, sslError);
    }

    public final void reset() {
        zzagq zzagq = this.zzaop;
        if (zzagq != null) {
            zzagq.zzph();
            this.zzaop = null;
        }
        zzus();
        synchronized (this.mLock) {
            this.zzcou.clear();
            this.zzbgt = null;
            this.zzccs = null;
            this.zzdpd = null;
            this.zzdpe = null;
            this.zzcbc = null;
            this.zzdpg = false;
            this.zzavq = false;
            this.zzdph = false;
            this.zzdpk = false;
            this.zzccr = null;
            this.zzdpf = null;
            zzyd zzyd = this.zzccn;
            if (zzyd != null) {
                zzyd.zzm(true);
                this.zzccn = null;
            }
        }
    }

    public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
        zzik zza;
        try {
            String zzb = zzagx.zzb(str, this.zzcct.getContext());
            if (!zzb.equals(str)) {
                return zzdf(zzb);
            }
            zzin zzab = zzin.zzab(str);
            if (zzab != null && (zza = zzbt.zzer().zza(zzab)) != null && zza.zzhj()) {
                return new WebResourceResponse("", "", zza.zzhk());
            }
            if (zzaks.isEnabled()) {
                if (((Boolean) zzlc.zzio().zzd(zzoi.zzbqg)).booleanValue()) {
                    return zzdf(str);
                }
            }
            return null;
        } catch (Exception | NoClassDefFoundError e) {
            zzbt.zzep().zza(e, "AdWebViewClient.interceptRequest");
            return null;
        }
    }

    public boolean shouldOverrideKeyEvent(WebView webView, KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        if (keyCode == 79 || keyCode == 222) {
            return true;
        }
        switch (keyCode) {
            case 85:
            case 86:
            case 87:
            case 88:
            case 89:
            case 90:
            case 91:
                return true;
            default:
                switch (keyCode) {
                    case 126:
                    case 127:
                    case 128:
                    case 129:
                    case 130:
                        return true;
                    default:
                        return false;
                }
        }
    }

    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        String valueOf = String.valueOf(str);
        zzahw.v(valueOf.length() != 0 ? "AdWebView shouldOverrideUrlLoading: ".concat(valueOf) : new String("AdWebView shouldOverrideUrlLoading: "));
        Uri parse = Uri.parse(str);
        if (!"gmsg".equalsIgnoreCase(parse.getScheme()) || !"mobileads.google.com".equalsIgnoreCase(parse.getHost())) {
            if (this.zzdpg && webView == this.zzcct.getWebView()) {
                String scheme = parse.getScheme();
                if ("http".equalsIgnoreCase(scheme) || "https".equalsIgnoreCase(scheme)) {
                    if (this.zzbgt != null) {
                        if (((Boolean) zzlc.zzio().zzd(zzoi.zzboc)).booleanValue()) {
                            this.zzbgt.onAdClicked();
                            zzagq zzagq = this.zzaop;
                            if (zzagq != null) {
                                zzagq.zzbv(str);
                            }
                            this.zzbgt = null;
                        }
                    }
                    return super.shouldOverrideUrlLoading(webView, str);
                }
            }
            if (!this.zzcct.getWebView().willNotDraw()) {
                try {
                    zzcv zzuc = this.zzcct.zzuc();
                    if (zzuc != null && zzuc.zzb(parse)) {
                        parse = zzuc.zza(parse, this.zzcct.getContext(), this.zzcct.getView(), this.zzcct.zztj());
                    }
                } catch (zzcw unused) {
                    String valueOf2 = String.valueOf(str);
                    zzahw.zzcz(valueOf2.length() != 0 ? "Unable to append parameter to URL: ".concat(valueOf2) : new String("Unable to append parameter to URL: "));
                }
                zzw zzw = this.zzccm;
                if (zzw == null || zzw.zzcz()) {
                    zza(new zzc("android.intent.action.VIEW", parse.toString(), (String) null, (String) null, (String) null, (String) null, (String) null));
                } else {
                    this.zzccm.zzt(str);
                }
            } else {
                String valueOf3 = String.valueOf(str);
                zzahw.zzcz(valueOf3.length() != 0 ? "AdWebView unable to handle URL: ".concat(valueOf3) : new String("AdWebView unable to handle URL: "));
            }
        } else {
            zzh(parse);
        }
        return true;
    }

    public final void zza(int i, int i2, boolean z) {
        this.zzdpl.zzc(i, i2);
        zzyd zzyd = this.zzccn;
        if (zzyd != null) {
            zzyd.zza(i, i2, z);
        }
    }

    public final void zza(ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener onScrollChangedListener) {
        synchronized (this.mLock) {
            this.zzdph = true;
            this.zzcct.zzui();
            this.zzdpi = onGlobalLayoutListener;
            this.zzdpj = onScrollChangedListener;
        }
    }

    public final void zza(zzc zzc) {
        boolean zzud = this.zzcct.zzud();
        zza(new AdOverlayInfoParcel(zzc, (!zzud || this.zzcct.zzty().zzvl()) ? this.zzbgt : null, zzud ? null : this.zzccs, this.zzccr, this.zzcct.zztl()));
    }

    public final void zza(zzapv zzapv) {
        this.zzdpd = zzapv;
    }

    public final void zza(zzapw zzapw) {
        this.zzdpe = zzapw;
    }

    public final void zza(zzapx zzapx) {
        this.zzdpf = zzapx;
    }

    public final void zza(zzapy zzapy) {
        this.zzdpm = zzapy;
    }

    public final void zza(zzkf zzkf, zzn zzn, zzb zzb, zzt zzt, boolean z, zzx zzx, zzw zzw, zzyo zzyo, zzagq zzagq) {
        zzb zzb2 = zzb;
        zzx zzx2 = zzx;
        zzyo zzyo2 = zzyo;
        zzagq zzagq2 = zzagq;
        zzw zzw2 = zzw == null ? new zzw(this.zzcct.getContext(), zzagq2, (zzacl) null) : zzw;
        this.zzccn = new zzyd(this.zzcct, zzyo2);
        this.zzaop = zzagq2;
        zza("/appEvent", (com.google.android.gms.ads.internal.gmsg.zzt<? super zzaof>) new zza(zzb2));
        zza("/backButton", (com.google.android.gms.ads.internal.gmsg.zzt<? super zzaof>) zzd.zzcbn);
        zza("/refresh", (com.google.android.gms.ads.internal.gmsg.zzt<? super zzaof>) zzd.zzcbo);
        zza("/canOpenURLs", (com.google.android.gms.ads.internal.gmsg.zzt<? super zzaof>) zzd.zzcbe);
        zza("/canOpenIntents", (com.google.android.gms.ads.internal.gmsg.zzt<? super zzaof>) zzd.zzcbf);
        zza("/click", (com.google.android.gms.ads.internal.gmsg.zzt<? super zzaof>) zzd.zzcbg);
        zza("/close", (com.google.android.gms.ads.internal.gmsg.zzt<? super zzaof>) zzd.zzcbh);
        zza("/customClose", (com.google.android.gms.ads.internal.gmsg.zzt<? super zzaof>) zzd.zzcbi);
        zza("/instrument", (com.google.android.gms.ads.internal.gmsg.zzt<? super zzaof>) zzd.zzcbr);
        zza("/delayPageLoaded", (com.google.android.gms.ads.internal.gmsg.zzt<? super zzaof>) zzd.zzcbt);
        zza("/delayPageClosed", (com.google.android.gms.ads.internal.gmsg.zzt<? super zzaof>) zzd.zzcbu);
        zza("/getLocationInfo", (com.google.android.gms.ads.internal.gmsg.zzt<? super zzaof>) zzd.zzcbv);
        zza("/httpTrack", (com.google.android.gms.ads.internal.gmsg.zzt<? super zzaof>) zzd.zzcbj);
        zza("/log", (com.google.android.gms.ads.internal.gmsg.zzt<? super zzaof>) zzd.zzcbk);
        zza("/mraid", (com.google.android.gms.ads.internal.gmsg.zzt<? super zzaof>) new zzaa(zzw2, this.zzccn, zzyo2));
        zza("/mraidLoaded", (com.google.android.gms.ads.internal.gmsg.zzt<? super zzaof>) this.zzdpl);
        zza("/open", (com.google.android.gms.ads.internal.gmsg.zzt<? super zzaof>) new zzab(this.zzcct.getContext(), this.zzcct.zztl(), this.zzcct.zzuc(), zzt, zzkf, zzb, zzn, zzw2, this.zzccn));
        zza("/precache", (com.google.android.gms.ads.internal.gmsg.zzt<? super zzaof>) new zzaoc());
        zza("/touch", (com.google.android.gms.ads.internal.gmsg.zzt<? super zzaof>) zzd.zzcbm);
        zza("/video", (com.google.android.gms.ads.internal.gmsg.zzt<? super zzaof>) zzd.zzcbp);
        zza("/videoMeta", (com.google.android.gms.ads.internal.gmsg.zzt<? super zzaof>) zzd.zzcbq);
        if (zzbt.zzfh().zzq(this.zzcct.getContext())) {
            zza("/logScionEvent", (com.google.android.gms.ads.internal.gmsg.zzt<? super zzaof>) new zzz(this.zzcct.getContext()));
        }
        if (zzx2 != null) {
            zza("/setInterstitialProperties", (com.google.android.gms.ads.internal.gmsg.zzt<? super zzaof>) new com.google.android.gms.ads.internal.gmsg.zzw(zzx2));
        }
        this.zzbgt = zzkf;
        this.zzccs = zzn;
        this.zzcbc = zzb2;
        this.zzccr = zzt;
        this.zzccm = zzw2;
        this.zzcco = zzyo2;
        this.zzcck = zzx2;
        this.zzdpg = z;
    }

    public final void zza(String str, com.google.android.gms.ads.internal.gmsg.zzt<? super zzaof> zzt) {
        synchronized (this.mLock) {
            List list = this.zzcou.get(str);
            if (list == null) {
                list = new CopyOnWriteArrayList();
                this.zzcou.put(str, list);
            }
            list.add(zzt);
        }
    }

    public final void zza(String str, com.google.android.gms.common.util.zzt<com.google.android.gms.ads.internal.gmsg.zzt<? super zzaof>> zzt) {
        synchronized (this.mLock) {
            List<com.google.android.gms.ads.internal.gmsg.zzt<? super zzaof>> list = this.zzcou.get(str);
            if (list != null) {
                ArrayList arrayList = new ArrayList();
                for (com.google.android.gms.ads.internal.gmsg.zzt zzt2 : list) {
                    if (zzt.apply(zzt2)) {
                        arrayList.add(zzt2);
                    }
                }
                list.removeAll(arrayList);
            }
        }
    }

    public final void zza(boolean z, int i) {
        zzkf zzkf = (!this.zzcct.zzud() || this.zzcct.zzty().zzvl()) ? this.zzbgt : null;
        zzn zzn = this.zzccs;
        zzt zzt = this.zzccr;
        zzaof zzaof = this.zzcct;
        zza(new AdOverlayInfoParcel(zzkf, zzn, zzt, zzaof, z, i, zzaof.zztl()));
    }

    public final void zza(boolean z, int i, String str) {
        boolean zzud = this.zzcct.zzud();
        zzkf zzkf = (!zzud || this.zzcct.zzty().zzvl()) ? this.zzbgt : null;
        zzaok zzaok = zzud ? null : new zzaok(this.zzcct, this.zzccs);
        zzb zzb = this.zzcbc;
        zzt zzt = this.zzccr;
        zzaof zzaof = this.zzcct;
        zza(new AdOverlayInfoParcel(zzkf, zzaok, zzb, zzt, zzaof, z, i, str, zzaof.zztl()));
    }

    public final void zza(boolean z, int i, String str, String str2) {
        boolean zzud = this.zzcct.zzud();
        zzkf zzkf = (!zzud || this.zzcct.zzty().zzvl()) ? this.zzbgt : null;
        zzaok zzaok = zzud ? null : new zzaok(this.zzcct, this.zzccs);
        zzb zzb = this.zzcbc;
        zzt zzt = this.zzccr;
        zzaof zzaof = this.zzcct;
        zza(new AdOverlayInfoParcel(zzkf, zzaok, zzb, zzt, zzaof, z, i, str, str2, zzaof.zztl()));
    }

    public final void zzag(boolean z) {
        this.zzdpg = z;
    }

    public final void zzb(int i, int i2) {
        zzyd zzyd = this.zzccn;
        if (zzyd != null) {
            zzyd.zzb(i, i2);
        }
    }

    public final void zzb(String str, com.google.android.gms.ads.internal.gmsg.zzt<? super zzaof> zzt) {
        synchronized (this.mLock) {
            List list = this.zzcou.get(str);
            if (list != null) {
                list.remove(zzt);
            }
        }
    }

    public final boolean zzfz() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzavq;
        }
        return z;
    }

    public final void zzk(zzaof zzaof) {
        this.zzcct = zzaof;
    }

    public final void zznj() {
        synchronized (this.mLock) {
            this.zzdpg = false;
            this.zzavq = true;
            zzaly.zzdjt.execute(new zzaoh(this));
        }
    }

    public final zzw zzun() {
        return this.zzccm;
    }

    public final boolean zzuo() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzdph;
        }
        return z;
    }

    public final ViewTreeObserver.OnGlobalLayoutListener zzup() {
        ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener;
        synchronized (this.mLock) {
            onGlobalLayoutListener = this.zzdpi;
        }
        return onGlobalLayoutListener;
    }

    public final ViewTreeObserver.OnScrollChangedListener zzuq() {
        ViewTreeObserver.OnScrollChangedListener onScrollChangedListener;
        synchronized (this.mLock) {
            onScrollChangedListener = this.zzdpj;
        }
        return onScrollChangedListener;
    }

    public final boolean zzur() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzdpk;
        }
        return z;
    }

    public final void zzut() {
        zzagq zzagq = this.zzaop;
        if (zzagq != null) {
            WebView webView = this.zzcct.getWebView();
            if (ViewCompat.isAttachedToWindow(webView)) {
                zza((View) webView, zzagq, 10);
                return;
            }
            zzus();
            this.zzdpq = new zzaoj(this, zzagq);
            this.zzcct.getView().addOnAttachStateChangeListener(this.zzdpq);
        }
    }

    public final void zzuu() {
        synchronized (this.mLock) {
            this.zzdpk = true;
        }
        this.zzdpp++;
        zzux();
    }

    public final void zzuv() {
        this.zzdpp--;
        zzux();
    }

    public final void zzuw() {
        this.zzdpo = true;
        zzux();
    }

    public final zzapy zzuy() {
        return this.zzdpm;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzuz() {
        this.zzcct.zzui();
        com.google.android.gms.ads.internal.overlay.zzd zztw = this.zzcct.zztw();
        if (zztw != null) {
            zztw.zznj();
        }
        zzapx zzapx = this.zzdpf;
        if (zzapx != null) {
            zzapx.zzdc();
            this.zzdpf = null;
        }
    }
}
