package com.google.android.gms.ads.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.RemoteException;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzafc;
import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzaid;
import com.google.android.gms.internal.zzako;
import com.google.android.gms.internal.zzala;
import com.google.android.gms.internal.zzcv;
import com.google.android.gms.internal.zzcw;
import com.google.android.gms.internal.zzkk;
import com.google.android.gms.internal.zzko;
import com.google.android.gms.internal.zzlc;
import com.google.android.gms.internal.zzlf;
import com.google.android.gms.internal.zzli;
import com.google.android.gms.internal.zzlu;
import com.google.android.gms.internal.zzly;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzmm;
import com.google.android.gms.internal.zzms;
import com.google.android.gms.internal.zzns;
import com.google.android.gms.internal.zzoi;
import com.google.android.gms.internal.zzpb;
import com.google.android.gms.internal.zzyx;
import com.google.android.gms.internal.zzzd;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@zzabh
public final class zzbn extends zzlu {
    /* access modifiers changed from: private */
    public final Context mContext;
    /* access modifiers changed from: private */
    public zzli zzapd;
    /* access modifiers changed from: private */
    public final zzala zzapq;
    private final zzko zzasd;
    /* access modifiers changed from: private */
    public final Future<zzcv> zzase = zzaid.zza((ExecutorService) zzaid.zzdfi, new com.google.android.gms.ads.internal.zzbq(this));
    private final zzbs zzasf;
    /* access modifiers changed from: private */
    public WebView zzasg;
    /* access modifiers changed from: private */
    public zzcv zzash;
    private AsyncTask<Void, Void, String> zzasi;

    public zzbn(Context context, zzko zzko, String str, zzala zzala) {
        this.mContext = context;
        this.zzapq = zzala;
        this.zzasd = zzko;
        this.zzasg = new WebView(context);
        this.zzasf = new zzbs(str);
        zzk(0);
        this.zzasg.setVerticalScrollBarEnabled(false);
        this.zzasg.getSettings().setJavaScriptEnabled(true);
        this.zzasg.setWebViewClient(new zzbo(this));
        this.zzasg.setOnTouchListener(new zzbp(this));
    }

    /* access modifiers changed from: private */
    public final String zzw(String str) {
        if (this.zzash == null) {
            return str;
        }
        Uri parse = Uri.parse(str);
        try {
            parse = this.zzash.zza(parse, this.mContext, (View) null, (Activity) null);
        } catch (zzcw e) {
            zzahw.zzc("Unable to process ad data", e);
        }
        return parse.toString();
    }

    /* access modifiers changed from: private */
    public final void zzx(String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(str));
        this.mContext.startActivity(intent);
    }

    public final void destroy() throws RemoteException {
        zzbq.zzgn("destroy must be called on the main UI thread.");
        this.zzasi.cancel(true);
        this.zzase.cancel(true);
        this.zzasg.destroy();
        this.zzasg = null;
    }

    public final String getAdUnitId() {
        throw new IllegalStateException("getAdUnitId not implemented");
    }

    public final String getMediationAdapterClassName() throws RemoteException {
        return null;
    }

    public final zzmm getVideoController() {
        return null;
    }

    public final boolean isLoading() throws RemoteException {
        return false;
    }

    public final boolean isReady() throws RemoteException {
        return false;
    }

    public final void pause() throws RemoteException {
        zzbq.zzgn("pause must be called on the main UI thread.");
    }

    public final void resume() throws RemoteException {
        zzbq.zzgn("resume must be called on the main UI thread.");
    }

    public final void setImmersiveMode(boolean z) {
        throw new IllegalStateException("Unused method");
    }

    public final void setManualImpressionsEnabled(boolean z) throws RemoteException {
    }

    public final void setUserId(String str) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public final void showInterstitial() throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public final void stopLoading() throws RemoteException {
    }

    public final void zza(zzafc zzafc) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public final void zza(zzko zzko) throws RemoteException {
        throw new IllegalStateException("AdSize must be set before initialization");
    }

    public final void zza(zzlf zzlf) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public final void zza(zzli zzli) throws RemoteException {
        this.zzapd = zzli;
    }

    public final void zza(zzly zzly) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public final void zza(zzme zzme) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public final void zza(zzms zzms) {
        throw new IllegalStateException("Unused method");
    }

    public final void zza(zzns zzns) {
        throw new IllegalStateException("Unused method");
    }

    public final void zza(zzpb zzpb) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public final void zza(zzyx zzyx) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public final void zza(zzzd zzzd, String str) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public final boolean zzb(zzkk zzkk) throws RemoteException {
        zzbq.checkNotNull(this.zzasg, "This Search Ad has already been torn down");
        this.zzasf.zza(zzkk, this.zzapq);
        this.zzasi = new zzbr(this, (zzbo) null).execute(new Void[0]);
        return true;
    }

    public final IObjectWrapper zzbp() throws RemoteException {
        zzbq.zzgn("getAdFrame must be called on the main UI thread.");
        return zzn.zzz(this.zzasg);
    }

    public final zzko zzbq() throws RemoteException {
        return this.zzasd;
    }

    public final void zzbs() throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public final zzly zzcc() {
        throw new IllegalStateException("getIAppEventListener not implemented");
    }

    public final zzli zzcd() {
        throw new IllegalStateException("getIAdListener not implemented");
    }

    public final String zzco() throws RemoteException {
        return null;
    }

    /* access modifiers changed from: package-private */
    public final String zzeb() {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https://").appendEncodedPath((String) zzlc.zzio().zzd(zzoi.zzbtp));
        builder.appendQueryParameter(SearchIntents.EXTRA_QUERY, this.zzasf.getQuery());
        builder.appendQueryParameter("pubId", this.zzasf.zzee());
        Map<String, String> zzef = this.zzasf.zzef();
        for (String next : zzef.keySet()) {
            builder.appendQueryParameter(next, zzef.get(next));
        }
        Uri build = builder.build();
        zzcv zzcv = this.zzash;
        if (zzcv != null) {
            try {
                build = zzcv.zza(build, this.mContext);
            } catch (zzcw e) {
                zzahw.zzc("Unable to process ad data", e);
            }
        }
        String zzec = zzec();
        String encodedQuery = build.getEncodedQuery();
        StringBuilder sb = new StringBuilder(String.valueOf(zzec).length() + 1 + String.valueOf(encodedQuery).length());
        sb.append(zzec);
        sb.append("#");
        sb.append(encodedQuery);
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public final String zzec() {
        String zzed = this.zzasf.zzed();
        if (TextUtils.isEmpty(zzed)) {
            zzed = "www.google.com";
        }
        String str = (String) zzlc.zzio().zzd(zzoi.zzbtp);
        StringBuilder sb = new StringBuilder(String.valueOf(zzed).length() + 8 + String.valueOf(str).length());
        sb.append("https://");
        sb.append(zzed);
        sb.append(str);
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public final void zzk(int i) {
        if (this.zzasg != null) {
            this.zzasg.setLayoutParams(new ViewGroup.LayoutParams(-1, i));
        }
    }

    /* access modifiers changed from: package-private */
    public final int zzv(String str) {
        String queryParameter = Uri.parse(str).getQueryParameter("height");
        if (TextUtils.isEmpty(queryParameter)) {
            return 0;
        }
        try {
            zzlc.zzij();
            return zzako.zza(this.mContext, Integer.parseInt(queryParameter));
        } catch (NumberFormatException unused) {
            return 0;
        }
    }
}
