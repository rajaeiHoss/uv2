package com.google.android.gms.ads.internal;

import android.content.Context;
import androidx.collection.SimpleArrayMap;
import com.google.android.gms.ads.formats.PublisherAdViewOptions;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzaij;
import com.google.android.gms.internal.zzala;
import com.google.android.gms.internal.zzkk;
import com.google.android.gms.internal.zzko;
import com.google.android.gms.internal.zzlc;
import com.google.android.gms.internal.zzli;
import com.google.android.gms.internal.zzlm;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzoi;
import com.google.android.gms.internal.zzqh;
import com.google.android.gms.internal.zzrs;
import com.google.android.gms.internal.zzrv;
import com.google.android.gms.internal.zzry;
import com.google.android.gms.internal.zzsb;
import com.google.android.gms.internal.zzse;
import com.google.android.gms.internal.zzsh;
import com.google.android.gms.internal.zzwf;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

@zzabh
public final class zzag extends zzlm {
    private final Context mContext;
    /* access modifiers changed from: private */
    public final Object mLock = new Object();
    private final zzv zzanp;
    private final zzwf zzanu;
    private final zzli zzapd;
    private final zzrs zzape;
    private final zzsh zzapf;
    private final zzrv zzapg;
    private final zzse zzaph;
    private final zzko zzapi;
    private final PublisherAdViewOptions zzapj;
    private final SimpleArrayMap<String, zzsb> zzapk;
    private final SimpleArrayMap<String, zzry> zzapl;
    private final zzqh zzapm;
    private final List<String> zzapn;
    private final zzme zzapo;
    private final String zzapp;
    private final zzala zzapq;
    private WeakReference<zzd> zzapr;

    zzag(Context context, String str, zzwf zzwf, zzala zzala, zzli zzli, zzrs zzrs, zzsh zzsh, zzrv zzrv, SimpleArrayMap<String, zzsb> simpleArrayMap, SimpleArrayMap<String, zzry> simpleArrayMap2, zzqh zzqh, zzme zzme, zzv zzv, zzse zzse, zzko zzko, PublisherAdViewOptions publisherAdViewOptions) {
        this.mContext = context;
        this.zzapp = str;
        this.zzanu = zzwf;
        this.zzapq = zzala;
        this.zzapd = zzli;
        this.zzapg = zzrv;
        this.zzape = zzrs;
        this.zzapf = zzsh;
        this.zzapk = simpleArrayMap;
        this.zzapl = simpleArrayMap2;
        this.zzapm = zzqh;
        this.zzapn = zzdh();
        this.zzapo = zzme;
        this.zzanp = zzv;
        this.zzaph = zzse;
        this.zzapi = zzko;
        this.zzapj = publisherAdViewOptions;
        zzoi.initialize(context);
    }

    private static void runOnUiThread(Runnable runnable) {
        zzaij.zzdfn.post(runnable);
    }

    /* access modifiers changed from: private */
    public final void zzb(zzkk zzkk, int i) {
        Context context = this.mContext;
        zzbb zzbb = new zzbb(context, this.zzanp, zzko.zzf(context), this.zzapp, this.zzanu, this.zzapq);
        this.zzapr = new WeakReference<>(zzbb);
        zzrs zzrs = this.zzape;
        zzbq.zzgn("setOnAppInstallAdLoadedListener must be called on the main UI thread.");
        zzbb.zzanm.zzaul = zzrs;
        zzsh zzsh = this.zzapf;
        zzbq.zzgn("setOnUnifiedNativeAdLoadedListener must be called on the main UI thread.");
        zzbb.zzanm.zzaun = zzsh;
        zzrv zzrv = this.zzapg;
        zzbq.zzgn("setOnContentAdLoadedListener must be called on the main UI thread.");
        zzbb.zzanm.zzaum = zzrv;
        SimpleArrayMap<String, zzsb> simpleArrayMap = this.zzapk;
        zzbq.zzgn("setOnCustomTemplateAdLoadedListeners must be called on the main UI thread.");
        zzbb.zzanm.zzaup = simpleArrayMap;
        zzbb.zza(this.zzapd);
        SimpleArrayMap<String, zzry> simpleArrayMap2 = this.zzapl;
        zzbq.zzgn("setOnCustomClickListener must be called on the main UI thread.");
        zzbb.zzanm.zzauo = simpleArrayMap2;
        zzbb.zzd(zzdh());
        zzqh zzqh = this.zzapm;
        zzbq.zzgn("setNativeAdOptions must be called on the main UI thread.");
        zzbb.zzanm.zzauq = zzqh;
        zzbb.zza(this.zzapo);
        zzbb.zzj(i);
        zzbb.zzb(zzkk);
    }

    /* access modifiers changed from: private */
    public final boolean zzdf() {
        return ((Boolean) zzlc.zzio().zzd(zzoi.zzbpg)).booleanValue() && this.zzaph != null;
    }

    private final boolean zzdg() {
        if (this.zzape != null || this.zzapg != null || this.zzapf != null) {
            return true;
        }
        SimpleArrayMap<String, zzsb> simpleArrayMap = this.zzapk;
        return simpleArrayMap != null && simpleArrayMap.size() > 0;
    }

    private final List<String> zzdh() {
        ArrayList arrayList = new ArrayList();
        if (this.zzapg != null) {
            arrayList.add("1");
        }
        if (this.zzape != null) {
            arrayList.add("2");
        }
        if (this.zzapf != null) {
            arrayList.add("6");
        }
        if (this.zzapk.size() > 0) {
            arrayList.add("3");
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public final void zze(zzkk zzkk) {
        zzq zzq = new zzq(this.mContext, this.zzanp, this.zzapi, this.zzapp, this.zzanu, this.zzapq);
        this.zzapr = new WeakReference<>(zzq);
        zzse zzse = this.zzaph;
        zzbq.zzgn("setOnPublisherAdViewLoadedListener must be called on the main UI thread.");
        zzq.zzanm.zzaut = zzse;
        PublisherAdViewOptions publisherAdViewOptions = this.zzapj;
        if (publisherAdViewOptions != null) {
            if (publisherAdViewOptions.zzbk() != null) {
                zzq.zza(this.zzapj.zzbk());
            }
            zzq.setManualImpressionsEnabled(this.zzapj.getManualImpressionsEnabled());
        }
        zzrs zzrs = this.zzape;
        zzbq.zzgn("setOnAppInstallAdLoadedListener must be called on the main UI thread.");
        zzq.zzanm.zzaul = zzrs;
        zzrv zzrv = this.zzapg;
        zzbq.zzgn("setOnContentAdLoadedListener must be called on the main UI thread.");
        zzq.zzanm.zzaum = zzrv;
        SimpleArrayMap<String, zzsb> simpleArrayMap = this.zzapk;
        zzbq.zzgn("setOnCustomTemplateAdLoadedListeners must be called on the main UI thread.");
        zzq.zzanm.zzaup = simpleArrayMap;
        SimpleArrayMap<String, zzry> simpleArrayMap2 = this.zzapl;
        zzbq.zzgn("setOnCustomClickListener must be called on the main UI thread.");
        zzq.zzanm.zzauo = simpleArrayMap2;
        zzqh zzqh = this.zzapm;
        zzbq.zzgn("setNativeAdOptions must be called on the main UI thread.");
        zzq.zzanm.zzauq = zzqh;
        zzq.zzd(zzdh());
        zzq.zza(this.zzapd);
        zzq.zza(this.zzapo);
        ArrayList arrayList = new ArrayList();
        if (zzdg()) {
            arrayList.add(1);
        }
        if (this.zzaph != null) {
            arrayList.add(2);
        }
        zzq.zze(arrayList);
        if (zzdg()) {
            zzkk.extras.putBoolean("ina", true);
        }
        if (this.zzaph != null) {
            zzkk.extras.putBoolean("iba", true);
        }
        zzq.zzb(zzkk);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0015, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String getMediationAdapterClassName() {
        /*
            r3 = this;
            java.lang.Object r0 = r3.mLock
            monitor-enter(r0)
            java.lang.ref.WeakReference<com.google.android.gms.ads.internal.zzd> r1 = r3.zzapr     // Catch:{ all -> 0x0018 }
            r2 = 0
            if (r1 == 0) goto L_0x0016
            java.lang.Object r1 = r1.get()     // Catch:{ all -> 0x0018 }
            com.google.android.gms.ads.internal.zzd r1 = (com.google.android.gms.ads.internal.zzd) r1     // Catch:{ all -> 0x0018 }
            if (r1 == 0) goto L_0x0014
            java.lang.String r2 = r1.getMediationAdapterClassName()     // Catch:{ all -> 0x0018 }
        L_0x0014:
            monitor-exit(r0)     // Catch:{ all -> 0x0018 }
            return r2
        L_0x0016:
            monitor-exit(r0)     // Catch:{ all -> 0x0018 }
            return r2
        L_0x0018:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0018 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.zzag.getMediationAdapterClassName():java.lang.String");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0015, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean isLoading() {
        /*
            r3 = this;
            java.lang.Object r0 = r3.mLock
            monitor-enter(r0)
            java.lang.ref.WeakReference<com.google.android.gms.ads.internal.zzd> r1 = r3.zzapr     // Catch:{ all -> 0x0018 }
            r2 = 0
            if (r1 == 0) goto L_0x0016
            java.lang.Object r1 = r1.get()     // Catch:{ all -> 0x0018 }
            com.google.android.gms.ads.internal.zzd r1 = (com.google.android.gms.ads.internal.zzd) r1     // Catch:{ all -> 0x0018 }
            if (r1 == 0) goto L_0x0014
            boolean r2 = r1.isLoading()     // Catch:{ all -> 0x0018 }
        L_0x0014:
            monitor-exit(r0)     // Catch:{ all -> 0x0018 }
            return r2
        L_0x0016:
            monitor-exit(r0)     // Catch:{ all -> 0x0018 }
            return r2
        L_0x0018:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0018 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.zzag.isLoading():boolean");
    }

    public final void zza(zzkk zzkk, int i) {
        if (i > 0) {
            runOnUiThread(new zzai(this, zzkk, i));
            return;
        }
        throw new IllegalArgumentException("Number of ads has to be more than 0");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0015, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String zzco() {
        /*
            r3 = this;
            java.lang.Object r0 = r3.mLock
            monitor-enter(r0)
            java.lang.ref.WeakReference<com.google.android.gms.ads.internal.zzd> r1 = r3.zzapr     // Catch:{ all -> 0x0018 }
            r2 = 0
            if (r1 == 0) goto L_0x0016
            java.lang.Object r1 = r1.get()     // Catch:{ all -> 0x0018 }
            com.google.android.gms.ads.internal.zzd r1 = (com.google.android.gms.ads.internal.zzd) r1     // Catch:{ all -> 0x0018 }
            if (r1 == 0) goto L_0x0014
            java.lang.String r2 = r1.zzco()     // Catch:{ all -> 0x0018 }
        L_0x0014:
            monitor-exit(r0)     // Catch:{ all -> 0x0018 }
            return r2
        L_0x0016:
            monitor-exit(r0)     // Catch:{ all -> 0x0018 }
            return r2
        L_0x0018:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0018 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.zzag.zzco():java.lang.String");
    }

    public final void zzd(zzkk zzkk) {
        runOnUiThread(new zzah(this, zzkk));
    }
}
