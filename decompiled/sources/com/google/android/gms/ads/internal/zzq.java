package com.google.android.gms.ads.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzahd;
import com.google.android.gms.internal.zzahe;
import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzaij;
import com.google.android.gms.internal.zzala;
import com.google.android.gms.internal.zzaof;
import com.google.android.gms.internal.zzkk;
import com.google.android.gms.internal.zzko;
import com.google.android.gms.internal.zzmm;
import com.google.android.gms.internal.zzpb;
import com.google.android.gms.internal.zzph;
import com.google.android.gms.internal.zzpk;
import com.google.android.gms.internal.zzpm;
import com.google.android.gms.internal.zzpt;
import com.google.android.gms.internal.zzpu;
import com.google.android.gms.internal.zzpv;
import com.google.android.gms.internal.zzpw;
import com.google.android.gms.internal.zzpx;
import com.google.android.gms.internal.zzqs;
import com.google.android.gms.internal.zzro;
import com.google.android.gms.internal.zzry;
import com.google.android.gms.internal.zzvp;
import com.google.android.gms.internal.zzvq;
import com.google.android.gms.internal.zzvs;
import com.google.android.gms.internal.zzwf;
import com.google.android.gms.internal.zzwi;
import com.google.android.gms.internal.zzwr;
import com.google.android.gms.internal.zzwu;
import java.util.List;

@zzabh
public final class zzq extends zzd implements zzpw {
    private boolean zzamp;
    /* access modifiers changed from: private */
    public zzahd zzaoe;
    private boolean zzaof = false;

    public zzq(Context context, zzv zzv, zzko zzko, String str, zzwf zzwf, zzala zzala) {
        super(context, zzko, str, zzwf, zzala, zzv);
    }

    private static zzahd zza(zzahe zzahe, int i) {
        zzahe zzahe2 = zzahe;
        return new zzahd(zzahe2.zzcvm.zzcrv, (zzaof) null, zzahe2.zzdcw.zzchw, i, zzahe2.zzdcw.zzchx, zzahe2.zzdcw.zzctq, zzahe2.zzdcw.orientation, zzahe2.zzdcw.zzcic, zzahe2.zzcvm.zzcry, zzahe2.zzdcw.zzcto, (zzvp) null, (zzwi) null, (String) null, zzahe2.zzdcj, (zzvs) null, zzahe2.zzdcw.zzctp, zzahe2.zzaud, zzahe2.zzdcw.zzctn, zzahe2.zzdcn, zzahe2.zzdco, zzahe2.zzdcw.zzctt, zzahe2.zzdch, (zzpx) null, zzahe2.zzdcw.zzcuc, zzahe2.zzdcw.zzcud, zzahe2.zzdcw.zzcud, zzahe2.zzdcw.zzcuf, zzahe2.zzdcw.zzcug, (String) null, zzahe2.zzdcw.zzchz, zzahe2.zzdcw.zzcuj, zzahe2.zzdcu, zzahe2.zzdcw.zzaqw, zzahe2.zzdcv);
    }

    private final boolean zzb(zzahd zzahd, zzahd zzahd2) {
        Handler handler;
        Runnable zzt;
        zzahd zzahd3 = zzahd2;
        View view = null;
        zzd((List<String>) null);
        if (!this.zzanm.zzfo()) {
            zzahw.zzcz("Native ad does not have custom rendering mode.");
        } else {
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
                    handler = zzaij.zzdfn;
                    zzt = new zzs(this, zzpk2);
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
                    handler = zzaij.zzdfn;
                    zzt = new zzt(this, zzpm);
                } else if (zzmu == null || this.zzanm.zzaup == null || this.zzanm.zzaup.get(zzmu.getCustomTemplateId()) == null) {
                    zzahw.zzcz("No matching mapper/listener for retrieved native ad template.");
                    zzi(0);
                    return false;
                } else {
                    zzaij.zzdfn.post(new zzu(this, zzmu));
                    return super.zza(zzahd, zzahd2);
                }
                handler.post(zzt);
                return super.zza(zzahd, zzahd2);
            } catch (RemoteException e) {
                zzahw.zzc("Failed to get native ad mapper", e);
            }
        }
        zzi(0);
        return false;
    }

    private final boolean zzc(zzahd zzahd, zzahd zzahd2) {
        View zze = zzar.zze(zzahd2);
        if (zze == null) {
            return false;
        }
        View nextView = this.zzanm.zzaua.getNextView();
        if (nextView != null) {
            if (nextView instanceof zzaof) {
                ((zzaof) nextView).destroy();
            }
            this.zzanm.zzaua.removeView(nextView);
        }
        if (!zzar.zzf(zzahd2)) {
            try {
                zzg(zze);
            } catch (Throwable th) {
                zzbt.zzep().zza(th, "AdLoaderManager.swapBannerViews");
                zzahw.zzc("Could not add mediation view to view hierarchy.", th);
                return false;
            }
        }
        if (this.zzanm.zzaua.getChildCount() > 1) {
            this.zzanm.zzaua.showNext();
        }
        if (zzahd != null) {
            View nextView2 = this.zzanm.zzaua.getNextView();
            if (nextView2 != null) {
                this.zzanm.zzaua.removeView(nextView2);
            }
            this.zzanm.zzfn();
        }
        this.zzanm.zzaua.setMinimumWidth(zzbq().widthPixels);
        this.zzanm.zzaua.setMinimumHeight(zzbq().heightPixels);
        this.zzanm.zzaua.requestLayout();
        this.zzanm.zzaua.setVisibility(0);
        return true;
    }

    private final zzvq zzcx() {
        if (this.zzanm.zzaue == null || !this.zzanm.zzaue.zzcto) {
            return null;
        }
        return this.zzanm.zzaue.zzdcj;
    }

    public final zzmm getVideoController() {
        return null;
    }

    public final void pause() {
        if (this.zzaof) {
            super.pause();
            return;
        }
        throw new IllegalStateException("Native Ad does not support pause().");
    }

    public final void resume() {
        if (this.zzaof) {
            super.resume();
            return;
        }
        throw new IllegalStateException("Native Ad does not support resume().");
    }

    public final void setManualImpressionsEnabled(boolean z) {
        zzbq.zzgn("setManualImpressionsEnabled must be called from the main thread.");
        this.zzamp = z;
    }

    public final void showInterstitial() {
        throw new IllegalStateException("Interstitial is not supported by AdLoaderManager.");
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0031  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.gms.internal.zzahe r11, com.google.android.gms.internal.zzov r12) {
        /*
            r10 = this;
            r0 = 0
            r10.zzaoe = r0
            int r0 = r11.errorCode
            r1 = 0
            r2 = -2
            if (r0 == r2) goto L_0x0012
            int r0 = r11.errorCode
            com.google.android.gms.internal.zzahd r0 = zza((com.google.android.gms.internal.zzahe) r11, (int) r0)
        L_0x000f:
            r10.zzaoe = r0
            goto L_0x0022
        L_0x0012:
            com.google.android.gms.internal.zzacj r0 = r11.zzdcw
            boolean r0 = r0.zzcto
            if (r0 != 0) goto L_0x0022
            java.lang.String r0 = "partialAdState is not mediation"
            com.google.android.gms.internal.zzahw.zzcz(r0)
            com.google.android.gms.internal.zzahd r0 = zza((com.google.android.gms.internal.zzahe) r11, (int) r1)
            goto L_0x000f
        L_0x0022:
            com.google.android.gms.internal.zzahd r0 = r10.zzaoe
            if (r0 == 0) goto L_0x0031
            android.os.Handler r11 = com.google.android.gms.internal.zzaij.zzdfn
            com.google.android.gms.ads.internal.zzr r12 = new com.google.android.gms.ads.internal.zzr
            r12.<init>(r10)
            r11.post(r12)
            return
        L_0x0031:
            com.google.android.gms.internal.zzko r0 = r11.zzaud
            if (r0 == 0) goto L_0x003b
            com.google.android.gms.ads.internal.zzbu r0 = r10.zzanm
            com.google.android.gms.internal.zzko r2 = r11.zzaud
            r0.zzaud = r2
        L_0x003b:
            com.google.android.gms.ads.internal.zzbu r0 = r10.zzanm
            r0.zzavb = r1
            com.google.android.gms.ads.internal.zzbu r0 = r10.zzanm
            com.google.android.gms.ads.internal.zzbt.zzek()
            com.google.android.gms.ads.internal.zzbu r1 = r10.zzanm
            android.content.Context r2 = r1.zzaiq
            com.google.android.gms.ads.internal.zzbu r1 = r10.zzanm
            com.google.android.gms.internal.zzcv r5 = r1.zzaty
            r6 = 0
            com.google.android.gms.internal.zzwf r7 = r10.zzanu
            r3 = r10
            r4 = r11
            r8 = r10
            r9 = r12
            com.google.android.gms.internal.zzajb r11 = com.google.android.gms.internal.zzzm.zza(r2, r3, r4, r5, r6, r7, r8, r9)
            r0.zzauc = r11
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.zzq.zza(com.google.android.gms.internal.zzahe, com.google.android.gms.internal.zzov):void");
    }

    public final void zza(zzpb zzpb) {
        throw new IllegalStateException("CustomRendering is not supported by AdLoaderManager.");
    }

    public final void zza(zzpt zzpt) {
        zzahw.zzcz("Unexpected call to AdLoaderManager method");
    }

    public final void zza(zzpv zzpv) {
        zzahw.zzcz("Unexpected call to AdLoaderManager method");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0066 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0067  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zza(com.google.android.gms.internal.zzahd r5, com.google.android.gms.internal.zzahd r6) {
        /*
            r4 = this;
            com.google.android.gms.ads.internal.zzbu r0 = r4.zzanm
            boolean r0 = r0.zzfo()
            if (r0 == 0) goto L_0x009a
            boolean r0 = r6.zzcto
            r1 = 0
            if (r0 != 0) goto L_0x0016
            r4.zzi(r1)
            java.lang.String r5 = "newState is not mediation."
        L_0x0012:
            com.google.android.gms.internal.zzahw.zzcz(r5)
            return r1
        L_0x0016:
            com.google.android.gms.internal.zzvp r0 = r6.zzcje
            r2 = 1
            if (r0 == 0) goto L_0x006a
            com.google.android.gms.internal.zzvp r0 = r6.zzcje
            boolean r0 = r0.zzmg()
            if (r0 == 0) goto L_0x006a
            com.google.android.gms.ads.internal.zzbu r0 = r4.zzanm
            boolean r0 = r0.zzfo()
            if (r0 == 0) goto L_0x003e
            com.google.android.gms.ads.internal.zzbu r0 = r4.zzanm
            com.google.android.gms.ads.internal.zzbv r0 = r0.zzaua
            if (r0 == 0) goto L_0x003e
            com.google.android.gms.ads.internal.zzbu r0 = r4.zzanm
            com.google.android.gms.ads.internal.zzbv r0 = r0.zzaua
            com.google.android.gms.internal.zzajc r0 = r0.zzfr()
            java.lang.String r3 = r6.zzctt
            r0.zzcr(r3)
        L_0x003e:
            boolean r0 = super.zza((com.google.android.gms.internal.zzahd) r5, (com.google.android.gms.internal.zzahd) r6)
            if (r0 != 0) goto L_0x0046
        L_0x0044:
            r5 = 0
            goto L_0x0064
        L_0x0046:
            com.google.android.gms.ads.internal.zzbu r0 = r4.zzanm
            boolean r0 = r0.zzfo()
            if (r0 == 0) goto L_0x0058
            boolean r5 = r4.zzc(r5, r6)
            if (r5 != 0) goto L_0x0058
            r4.zzi(r1)
            goto L_0x0044
        L_0x0058:
            com.google.android.gms.ads.internal.zzbu r5 = r4.zzanm
            boolean r5 = r5.zzfp()
            if (r5 != 0) goto L_0x0063
            super.zza((com.google.android.gms.internal.zzahd) r6, (boolean) r1)
        L_0x0063:
            r5 = 1
        L_0x0064:
            if (r5 != 0) goto L_0x0067
            return r1
        L_0x0067:
            r4.zzaof = r2
            goto L_0x007d
        L_0x006a:
            com.google.android.gms.internal.zzvp r0 = r6.zzcje
            if (r0 == 0) goto L_0x0093
            com.google.android.gms.internal.zzvp r0 = r6.zzcje
            boolean r0 = r0.zzmh()
            if (r0 == 0) goto L_0x0093
            boolean r5 = r4.zzb(r5, r6)
            if (r5 != 0) goto L_0x007d
            return r1
        L_0x007d:
            java.util.ArrayList r5 = new java.util.ArrayList
            java.lang.Integer[] r6 = new java.lang.Integer[r2]
            r0 = 2
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r6[r1] = r0
            java.util.List r6 = java.util.Arrays.asList(r6)
            r5.<init>(r6)
            r4.zze(r5)
            return r2
        L_0x0093:
            r4.zzi(r1)
            java.lang.String r5 = "Response is neither banner nor native."
            goto L_0x0012
        L_0x009a:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "AdLoader API does not support custom rendering."
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.zzq.zza(com.google.android.gms.internal.zzahd, com.google.android.gms.internal.zzahd):boolean");
    }

    /* access modifiers changed from: protected */
    public final boolean zza(zzkk zzkk, zzahd zzahd, boolean z) {
        return false;
    }

    public final boolean zzb(zzkk zzkk) {
        zzkk zzkk2 = zzkk;
        if (this.zzanm.zzauu != null && this.zzanm.zzauu.size() == 1 && this.zzanm.zzauu.get(0).intValue() == 2) {
            zzahw.e("Requesting only banner Ad from AdLoader or calling loadAd on returned banner is not yet supported");
            zzi(0);
            return false;
        } else if (this.zzanm.zzaut == null) {
            return super.zzb(zzkk);
        } else {
            if (zzkk2.zzbha != this.zzamp) {
                zzkk2 = new zzkk(zzkk2.versionCode, zzkk2.zzbgv, zzkk2.extras, zzkk2.zzbgw, zzkk2.zzbgx, zzkk2.zzbgy, zzkk2.zzbgz, zzkk2.zzbha || this.zzamp, zzkk2.zzbhb, zzkk2.zzbhc, zzkk2.zzbhd, zzkk2.zzbhe, zzkk2.zzbhf, zzkk2.zzbhg, zzkk2.zzbhh, zzkk2.zzbhi, zzkk2.zzbhj, zzkk2.zzbhk);
            }
            return super.zzb(zzkk2);
        }
    }

    /* access modifiers changed from: protected */
    public final void zzbw() {
        super.zzbw();
        zzahd zzahd = this.zzanm.zzaue;
        if (zzahd != null && zzahd.zzcje != null && zzahd.zzcje.zzmg() && this.zzanm.zzaut != null) {
            try {
                this.zzanm.zzaut.zza(this, zzn.zzz(this.zzanm.zzaiq));
            } catch (RemoteException e) {
                zzahw.zzc("Could not call PublisherAdViewLoadedListener.onPublisherAdViewLoaded().", e);
            }
        }
    }

    public final void zzci() {
        if (this.zzanm.zzaue == null || !"com.google.ads.mediation.admob.AdMobAdapter".equals(this.zzanm.zzaue.zzcjg) || this.zzanm.zzaue.zzcje == null || !this.zzanm.zzaue.zzcje.zzmh()) {
            super.zzci();
        } else {
            zzby();
        }
    }

    public final void zzcn() {
        if (this.zzanm.zzaue == null || !"com.google.ads.mediation.admob.AdMobAdapter".equals(this.zzanm.zzaue.zzcjg) || this.zzanm.zzaue.zzcje == null || !this.zzanm.zzaue.zzcje.zzmh()) {
            super.zzcn();
        } else {
            zzbx();
        }
    }

    public final void zzcu() {
        zzahw.zzcz("Unexpected call to AdLoaderManager method");
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

    public final void zze(List<Integer> list) {
        zzbq.zzgn("setAllowedAdTypes must be called on the main UI thread.");
        this.zzanm.zzauu = list;
    }

    public final zzry zzs(String str) {
        zzbq.zzgn("getOnCustomClickListener must be called on the main UI thread.");
        return this.zzanm.zzauo.get(str);
    }
}
