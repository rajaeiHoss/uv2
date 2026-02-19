package com.google.android.gms.ads.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import androidx.collection.SimpleArrayMap;
import com.google.ads.AdRequest;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.internal.zzaan;
import com.google.android.gms.internal.zzaar;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzahd;
import com.google.android.gms.internal.zzahe;
import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzaid;
import com.google.android.gms.internal.zzaij;
import com.google.android.gms.internal.zzaky;
import com.google.android.gms.internal.zzala;
import com.google.android.gms.internal.zzalt;
import com.google.android.gms.internal.zzamd;
import com.google.android.gms.internal.zzaof;
import com.google.android.gms.internal.zzaop;
import com.google.android.gms.internal.zzfx;
import com.google.android.gms.internal.zzhf;
import com.google.android.gms.internal.zzkk;
import com.google.android.gms.internal.zzko;
import com.google.android.gms.internal.zzlc;
import com.google.android.gms.internal.zzmm;
import com.google.android.gms.internal.zzmp;
import com.google.android.gms.internal.zzoi;
import com.google.android.gms.internal.zzov;
import com.google.android.gms.internal.zzpb;
import com.google.android.gms.internal.zzph;
import com.google.android.gms.internal.zzpk;
import com.google.android.gms.internal.zzpm;
import com.google.android.gms.internal.zzpo;
import com.google.android.gms.internal.zzpr;
import com.google.android.gms.internal.zzpt;
import com.google.android.gms.internal.zzpu;
import com.google.android.gms.internal.zzpv;
import com.google.android.gms.internal.zzpw;
import com.google.android.gms.internal.zzpx;
import com.google.android.gms.internal.zzqs;
import com.google.android.gms.internal.zzro;
import com.google.android.gms.internal.zzry;
import com.google.android.gms.internal.zzsb;
import com.google.android.gms.internal.zzvq;
import com.google.android.gms.internal.zzwf;
import com.google.android.gms.internal.zzwi;
import com.google.android.gms.internal.zzwr;
import com.google.android.gms.internal.zzwu;
import com.google.android.gms.internal.zzyx;
import com.google.android.gms.internal.zzzm;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzabh
public final class zzbb extends zzd implements zzpw {
    private final Object mLock;
    private boolean zzany;
    private boolean zzari;
    private zzamd<zzpx> zzarj;
    private zzaof zzark;
    private int zzarl;
    private zzaan zzarm;
    private final String zzarn;

    public zzbb(Context context, zzv zzv, zzko zzko, String str, zzwf zzwf, zzala zzala) {
        this(context, zzv, zzko, str, zzwf, zzala, false);
    }

    public zzbb(Context context, zzv zzv, zzko zzko, String str, zzwf zzwf, zzala zzala, boolean z) {
        super(context, zzko, str, zzwf, zzala, zzv);
        this.mLock = new Object();
        this.zzarj = new zzamd<>();
        this.zzarl = 1;
        this.zzarn = UUID.randomUUID().toString();
        this.zzari = z;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x00a0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.internal.zzpr zza(com.google.android.gms.internal.zzpx r19) {
        /*
            r0 = r19
            boolean r1 = r0 instanceof com.google.android.gms.internal.zzpm
            r2 = 0
            if (r1 == 0) goto L_0x004f
            com.google.android.gms.internal.zzpm r0 = (com.google.android.gms.internal.zzpm) r0
            com.google.android.gms.internal.zzpr r1 = new com.google.android.gms.internal.zzpr
            java.lang.String r4 = r0.getHeadline()
            java.util.List r5 = r0.getImages()
            java.lang.String r6 = r0.getBody()
            com.google.android.gms.internal.zzqs r7 = r0.zzkj()
            java.lang.String r8 = r0.getCallToAction()
            java.lang.String r9 = r0.getAdvertiser()
            r10 = -4616189618054758400(0xbff0000000000000, double:-1.0)
            r12 = 0
            r13 = 0
            com.google.android.gms.internal.zzph r14 = r0.zzkf()
            com.google.android.gms.internal.zzmm r15 = r0.getVideoController()
            android.view.View r16 = r0.zzkg()
            com.google.android.gms.dynamic.IObjectWrapper r17 = r0.zzkh()
            java.lang.String r18 = r0.getMediationAdapterClassName()
            r3 = r1
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r12, r13, r14, r15, r16, r17, r18)
            com.google.android.gms.dynamic.IObjectWrapper r3 = r0.zzkd()
            if (r3 == 0) goto L_0x009c
            com.google.android.gms.dynamic.IObjectWrapper r0 = r0.zzkd()
        L_0x0049:
            java.lang.Object r0 = com.google.android.gms.dynamic.zzn.zzy(r0)
            r2 = r0
            goto L_0x009c
        L_0x004f:
            boolean r1 = r0 instanceof com.google.android.gms.internal.zzpk
            if (r1 == 0) goto L_0x009b
            com.google.android.gms.internal.zzpk r0 = (com.google.android.gms.internal.zzpk) r0
            com.google.android.gms.internal.zzpr r1 = new com.google.android.gms.internal.zzpr
            java.lang.String r4 = r0.getHeadline()
            java.util.List r5 = r0.getImages()
            java.lang.String r6 = r0.getBody()
            com.google.android.gms.internal.zzqs r7 = r0.zzkc()
            java.lang.String r8 = r0.getCallToAction()
            r9 = 0
            double r10 = r0.getStarRating()
            java.lang.String r12 = r0.getStore()
            java.lang.String r13 = r0.getPrice()
            com.google.android.gms.internal.zzph r14 = r0.zzkf()
            com.google.android.gms.internal.zzmm r15 = r0.getVideoController()
            android.view.View r16 = r0.zzkg()
            com.google.android.gms.dynamic.IObjectWrapper r17 = r0.zzkh()
            java.lang.String r18 = r0.getMediationAdapterClassName()
            r3 = r1
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r12, r13, r14, r15, r16, r17, r18)
            com.google.android.gms.dynamic.IObjectWrapper r3 = r0.zzkd()
            if (r3 == 0) goto L_0x009c
            com.google.android.gms.dynamic.IObjectWrapper r0 = r0.zzkd()
            goto L_0x0049
        L_0x009b:
            r1 = r2
        L_0x009c:
            boolean r0 = r2 instanceof com.google.android.gms.internal.zzpz
            if (r0 == 0) goto L_0x00a5
            com.google.android.gms.internal.zzpz r2 = (com.google.android.gms.internal.zzpz) r2
            r1.zzb((com.google.android.gms.internal.zzpv) r2)
        L_0x00a5:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.zzbb.zza(com.google.android.gms.internal.zzpx):com.google.android.gms.internal.zzpr");
    }

    /* access modifiers changed from: private */
    public static void zza(zzbu zzbu, zzbu zzbu2) {
        if (zzbu2.zzaul == null) {
            zzbu2.zzaul = zzbu.zzaul;
        }
        if (zzbu2.zzaum == null) {
            zzbu2.zzaum = zzbu.zzaum;
        }
        if (zzbu2.zzauo == null) {
            zzbu2.zzauo = zzbu.zzauo;
        }
        if (zzbu2.zzaup == null) {
            zzbu2.zzaup = zzbu.zzaup;
        }
        if (zzbu2.zzaur == null) {
            zzbu2.zzaur = zzbu.zzaur;
        }
        if (zzbu2.zzauq == null) {
            zzbu2.zzauq = zzbu.zzauq;
        }
        if (zzbu2.zzauy == null) {
            zzbu2.zzauy = zzbu.zzauy;
        }
        if (zzbu2.zzaug == null) {
            zzbu2.zzaug = zzbu.zzaug;
        }
        if (zzbu2.zzauz == null) {
            zzbu2.zzauz = zzbu.zzauz;
        }
        if (zzbu2.zzauh == null) {
            zzbu2.zzauh = zzbu.zzauh;
        }
        if (zzbu2.zzaui == null) {
            zzbu2.zzaui = zzbu.zzaui;
        }
        if (zzbu2.zzaud == null) {
            zzbu2.zzaud = zzbu.zzaud;
        }
        if (zzbu2.zzaue == null) {
            zzbu2.zzaue = zzbu.zzaue;
        }
        if (zzbu2.zzauf == null) {
            zzbu2.zzauf = zzbu.zzauf;
        }
    }

    private final void zza(zzpk zzpk) {
        zzaij.zzdfn.post(new zzbf(this, zzpk));
    }

    private final void zza(zzpm zzpm) {
        zzaij.zzdfn.post(new zzbg(this, zzpm));
    }

    private final void zza(zzpr zzpr) {
        try {
            if (this.zzanm.zzaun != null) {
                this.zzanm.zzaun.zza(zzpr);
            }
        } catch (RemoteException e) {
            zzahw.zzc("Could not call onUnifiedNativeAdLoadedListener.onUnifiedNativeAdLoaded().", e);
        }
    }

    private final zzvq zzcx() {
        if (this.zzanm.zzaue == null || !this.zzanm.zzaue.zzcto) {
            return null;
        }
        return this.zzanm.zzaue.zzdcj;
    }

    private final void zzdy() {
        zzaan zzds = zzds();
        if (zzds != null) {
            zzds.zzmd();
        }
    }

    public final String getAdUnitId() {
        return this.zzanm.zzatx;
    }

    public final String getUuid() {
        return this.zzarn;
    }

    public final void pause() {
        throw new IllegalStateException("Native Ad DOES NOT support pause().");
    }

    public final void resume() {
        throw new IllegalStateException("Native Ad DOES NOT support resume().");
    }

    public final void showInterstitial() {
        throw new IllegalStateException("Interstitial is NOT supported by NativeAdManager.");
    }

    public final void zza(zzahe zzahe, zzov zzov) {
        if (zzahe.zzaud != null) {
            this.zzanm.zzaud = zzahe.zzaud;
        }
        if (zzahe.errorCode != -2) {
            zzaij.zzdfn.post(new zzbc(this, zzahe));
            return;
        }
        int i = zzahe.zzcvm.zzctg;
        if (i == 1) {
            this.zzanm.zzavb = 0;
            zzbu zzbu = this.zzanm;
            zzbt.zzek();
            zzbu.zzauc = zzzm.zza(this.zzanm.zzaiq, this, zzahe, this.zzanm.zzaty, (zzaof) null, this.zzanu, this, zzov);
            String valueOf = String.valueOf(this.zzanm.zzauc.getClass().getName());
            zzahw.zzby(valueOf.length() != 0 ? "AdRenderer: ".concat(valueOf) : new String("AdRenderer: "));
            return;
        }
        JSONArray jSONArray = new JSONArray();
        try {
            JSONArray jSONArray2 = new JSONObject(zzahe.zzdcw.body).getJSONArray("slots");
            for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                JSONArray jSONArray3 = jSONArray2.getJSONObject(i2).getJSONArray("ads");
                for (int i3 = 0; i3 < jSONArray3.length(); i3++) {
                    jSONArray.put(jSONArray3.get(i3));
                }
            }
            zzdy();
            ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(i);
            ArrayList arrayList = new ArrayList();
            for (int i4 = 0; i4 < i; i4++) {
                arrayList.add(zzaid.zza(newFixedThreadPool, new zzbd(this, i4, jSONArray, i, zzahe)));
            }
            for (int i5 = 0; i5 < arrayList.size(); i5++) {
                try {
                    zzaij.zzdfn.post(new zzbe(this, (zzpx) ((zzalt) arrayList.get(i5)).get(((Long) zzlc.zzio().zzd(zzoi.zzbrg)).longValue(), TimeUnit.MILLISECONDS), i5, arrayList));
                } catch (InterruptedException e) {
                    zzahw.zzc("Exception occurred while getting an ad response", e);
                    Thread.currentThread().interrupt();
                } catch (CancellationException | ExecutionException | TimeoutException e2) {
                    zzahw.zzc("Exception occurred while getting an ad response", e2);
                }
            }
        } catch (JSONException e3) {
            zzahw.zzc("Malformed native ad response", e3);
            zzi(0);
        }
    }

    public final void zza(zzpb zzpb) {
        throw new IllegalStateException("CustomRendering is NOT supported by NativeAdManager.");
    }

    public final void zza(zzpt zzpt) {
        zzaof zzaof = this.zzark;
        if (zzaof != null) {
            zzaof.zzb(zzpt);
        }
    }

    public final void zza(zzpv zzpv) {
        if (this.zzanm.zzaue.zzdch != null) {
            zzbt.zzep().zzqa().zza(this.zzanm.zzaud, this.zzanm.zzaue, (zzhf) new zzfx(zzpv), (zzaof) null);
        }
    }

    public final void zza(zzyx zzyx) {
        throw new IllegalStateException("In App Purchase is NOT supported by NativeAdManager.");
    }

    /* access modifiers changed from: protected */
    public final boolean zza(zzahd zzahd, zzahd zzahd2) {
        zzahd zzahd3 = zzahd2;
        View view = null;
        zzd((List<String>) null);
        if (this.zzanm.zzfo()) {
            if (zzahd3.zzcto) {
                zzdy();
                try {
                    zzwr zzmp = zzahd3.zzcjf != null ? zzahd3.zzcjf.zzmp() : null;
                    zzwu zzmq = zzahd3.zzcjf != null ? zzahd3.zzcjf.zzmq() : null;
                    zzro zzmu = zzahd3.zzcjf != null ? zzahd3.zzcjf.zzmu() : null;
                    String zzc = zzc(zzahd2);
                    if (zzmp != null && this.zzanm.zzaul != null) {
                        String headline = zzmp.getHeadline();
                        List images = zzmp.getImages();
                        String body = zzmp.getBody();
                        zzqs zzkc = zzmp.zzkc() != null ? zzmp.zzkc() : null;
                        String callToAction = zzmp.getCallToAction();
                        double starRating = zzmp.getStarRating();
                        String store = zzmp.getStore();
                        String price = zzmp.getPrice();
                        Bundle extras = zzmp.getExtras();
                        zzmm videoController = zzmp.getVideoController();
                        if (zzmp.zzmx() != null) {
                            view = (View) zzn.zzy(zzmp.zzmx());
                        }
                        zzpk zzpk = new zzpk(headline, images, body, zzkc, callToAction, starRating, store, price, (zzph) null, extras, videoController, view, zzmp.zzkh(), zzc);
                        zzpk zzpk2 = zzpk;
                        zzpk2.zzb((zzpv) new zzpu(this.zzanm.zzaiq, (zzpw) this, this.zzanm.zzaty, zzmp, (zzpx) zzpk));
                        zza(zzpk2);
                    } else if (zzmq != null && this.zzanm.zzaum != null) {
                        String headline2 = zzmq.getHeadline();
                        List images2 = zzmq.getImages();
                        String body2 = zzmq.getBody();
                        zzqs zzkj = zzmq.zzkj() != null ? zzmq.zzkj() : null;
                        String callToAction2 = zzmq.getCallToAction();
                        String advertiser = zzmq.getAdvertiser();
                        Bundle extras2 = zzmq.getExtras();
                        zzmm videoController2 = zzmq.getVideoController();
                        if (zzmq.zzmx() != null) {
                            view = (View) zzn.zzy(zzmq.zzmx());
                        }
                        zzpm zzpm = new zzpm(headline2, images2, body2, zzkj, callToAction2, advertiser, (zzph) null, extras2, videoController2, view, zzmq.zzkh(), zzc);
                        zzpm.zzb((zzpv) new zzpu(this.zzanm.zzaiq, (zzpw) this, this.zzanm.zzaty, zzmq, (zzpx) zzpm));
                        zza(zzpm);
                    } else if (zzmu == null || this.zzanm.zzaup == null || this.zzanm.zzaup.get(zzmu.getCustomTemplateId()) == null) {
                        zzahw.zzcz("No matching mapper/listener for retrieved native ad template.");
                        zzi(0);
                        return false;
                    } else {
                        zzaij.zzdfn.post(new zzbi(this, zzmu));
                    }
                } catch (RemoteException e) {
                    zzahw.zzc("Failed to get native ad mapper", e);
                }
            } else {
                zzpx zzpx = zzahd3.zzdcp;
                if (this.zzari) {
                    this.zzarj.set(zzpx);
                } else {
                    boolean z = zzpx instanceof zzpm;
                    if (!z || this.zzanm.zzaun == null) {
                        if (!z || this.zzanm.zzaum == null) {
                            boolean z2 = zzpx instanceof zzpk;
                            if (!z2 || this.zzanm.zzaun == null) {
                                if (!z2 || this.zzanm.zzaul == null) {
                                    if ((zzpx instanceof zzpo) && this.zzanm.zzaup != null) {
                                        zzpo zzpo = (zzpo) zzpx;
                                        if (this.zzanm.zzaup.get(zzpo.getCustomTemplateId()) != null) {
                                            zzaij.zzdfn.post(new zzbh(this, zzpo.getCustomTemplateId(), zzahd3));
                                        }
                                    }
                                    zzahw.zzcz("No matching listener for retrieved native ad template.");
                                    zzi(0);
                                    return false;
                                }
                                zza((zzpk) zzahd3.zzdcp);
                            }
                        } else {
                            zza((zzpm) zzahd3.zzdcp);
                        }
                    }
                    zza(zza(zzahd3.zzdcp));
                }
            }
            return super.zza(zzahd, zzahd2);
        }
        throw new IllegalStateException("Native ad DOES NOT have custom rendering mode.");
    }

    /* access modifiers changed from: protected */
    public final boolean zza(zzkk zzkk, zzahd zzahd, boolean z) {
        return this.zzanl.zzea();
    }

    public final boolean zza(zzkk zzkk, zzov zzov) {
        try {
            zzdr();
            return super.zza(zzkk, zzov, this.zzarl);
        } catch (Exception e) {
            if (!zzaky.zzae(4)) {
                return false;
            }
            Log.i(AdRequest.LOGTAG, "Error initializing webview.", e);
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public final void zzbw() {
        zzc(false);
    }

    /* access modifiers changed from: protected */
    public final void zzc(int i, boolean z) {
        zzdy();
        super.zzc(i, z);
    }

    /* access modifiers changed from: protected */
    public final void zzc(boolean z) {
        super.zzc(z);
        if (this.zzany) {
            if (((Boolean) zzlc.zzio().zzd(zzoi.zzbst)).booleanValue()) {
                zzdu();
            }
        }
    }

    public final void zzch() {
        zzmp zzis;
        zzahd zzahd = this.zzanm.zzaue;
        if (zzahd.zzcjf == null) {
            super.zzch();
            return;
        }
        try {
            zzwi zzwi = zzahd.zzcjf;
            zzmm zzmm = null;
            zzwr zzmp = zzwi.zzmp();
            if (zzmp != null) {
                zzmm = zzmp.getVideoController();
            } else {
                zzwu zzmq = zzwi.zzmq();
                if (zzmq != null) {
                    zzmm = zzmq.getVideoController();
                } else {
                    zzro zzmu = zzwi.zzmu();
                    if (zzmu != null) {
                        zzmm = zzmu.getVideoController();
                    }
                }
            }
            if (zzmm != null && (zzis = zzmm.zzis()) != null) {
                zzis.onVideoEnd();
            }
        } catch (RemoteException e) {
            zzahw.zzc("Unable to call onVideoEnd()", e);
        }
    }

    public final void zzci() {
        if (this.zzanm.zzaue == null || !"com.google.ads.mediation.admob.AdMobAdapter".equals(this.zzanm.zzaue.zzcjg)) {
            super.zzci();
        } else {
            zzby();
        }
    }

    public final void zzcn() {
        if (this.zzanm.zzaue == null || !"com.google.ads.mediation.admob.AdMobAdapter".equals(this.zzanm.zzaue.zzcjg)) {
            super.zzcn();
        } else {
            zzbx();
        }
    }

    public final void zzcu() {
        zzaof zzaof = this.zzark;
        if (zzaof != null) {
            zzaof.destroy();
            this.zzark = null;
        }
    }

    public final boolean zzcv() {
        if (zzcx() != null) {
            return zzcx().zzcii;
        }
        return false;
    }

    public final boolean zzcw() {
        if (zzcx() != null) {
            return zzcx().zzcij;
        }
        return false;
    }

    public final void zzd(List<String> list) {
        zzbq.zzgn("setNativeTemplates must be called on the main UI thread.");
        this.zzanm.zzauy = list;
    }

    /* access modifiers changed from: package-private */
    public final void zzdr() throws zzaop {
        synchronized (this.mLock) {
            zzahw.v("Initializing webview native ads utills");
            this.zzarm = new zzaar(this.zzanm.zzaiq, this, this.zzarn, this.zzanm.zzaty, this.zzanm.zzatz);
        }
    }

    public final zzaan zzds() {
        zzaan zzaan;
        synchronized (this.mLock) {
            zzaan = this.zzarm;
        }
        return zzaan;
    }

    /* access modifiers changed from: protected */
    public final Future<zzpx> zzdt() {
        return this.zzarj;
    }

    public final void zzdu() {
        if (this.zzanm.zzaue == null || this.zzark == null) {
            this.zzany = true;
            zzahw.zzcz("Request to enable ActiveView before adState is available.");
            return;
        }
        zzbt.zzep().zzqa().zza(this.zzanm.zzaud, this.zzanm.zzaue, this.zzark.getView(), this.zzark);
        this.zzany = false;
    }

    public final void zzdv() {
        this.zzany = false;
        if (this.zzanm.zzaue == null || this.zzark == null) {
            zzahw.zzcz("Request to enable ActiveView before adState is available.");
        } else {
            zzbt.zzep().zzqa().zzh(this.zzanm.zzaue);
        }
    }

    public final SimpleArrayMap<String, zzsb> zzdw() {
        zzbq.zzgn("getOnCustomTemplateAdLoadedListeners must be called on the main UI thread.");
        return this.zzanm.zzaup;
    }

    public final void zzdx() {
        zzaof zzaof = this.zzark;
        if (zzaof != null && zzaof.zzth() != null && this.zzanm.zzauq != null && this.zzanm.zzauq.zzbzn != null) {
            this.zzark.zzth().zzb(this.zzanm.zzauq.zzbzn);
        }
    }

    public final void zze(zzaof zzaof) {
        this.zzark = zzaof;
    }

    /* access modifiers changed from: protected */
    public final void zzi(int i) {
        zzc(i, false);
    }

    public final void zzj(int i) {
        zzbq.zzgn("setMaxNumberOfAds must be called on the main UI thread.");
        this.zzarl = i;
    }

    public final zzry zzs(String str) {
        zzbq.zzgn("getOnCustomClickListener must be called on the main UI thread.");
        if (this.zzanm.zzauo == null) {
            return null;
        }
        return this.zzanm.zzauo.get(str);
    }
}
