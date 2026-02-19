package com.google.android.gms.ads.internal;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.RemoteException;
import android.view.View;
import android.view.ViewTreeObserver;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.internal.gmsg.zzt;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzagq;
import com.google.android.gms.internal.zzagt;
import com.google.android.gms.internal.zzahd;
import com.google.android.gms.internal.zzahe;
import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzaij;
import com.google.android.gms.internal.zzala;
import com.google.android.gms.internal.zzaof;
import com.google.android.gms.internal.zzaop;
import com.google.android.gms.internal.zzapy;
import com.google.android.gms.internal.zzaqa;
import com.google.android.gms.internal.zzgr;
import com.google.android.gms.internal.zzgv;
import com.google.android.gms.internal.zzkk;
import com.google.android.gms.internal.zzko;
import com.google.android.gms.internal.zzlc;
import com.google.android.gms.internal.zzmm;
import com.google.android.gms.internal.zzwf;
import com.google.android.gms.internal.zzwr;
import com.google.android.gms.internal.zzwu;
import java.lang.ref.WeakReference;
import java.util.List;

@zzabh
public final class zzx extends zzi implements ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener {
    private boolean zzamp;
    private WeakReference<Object> zzaor = new WeakReference<>((Object) null);

    public zzx(Context context, zzko zzko, String str, zzwf zzwf, zzala zzala, zzv zzv) {
        super(context, zzko, str, zzwf, zzala, zzv);
    }

    private final boolean zzd(zzahd zzahd, zzahd zzahd2) {
        if (zzahd2.zzcto) {
            View zze = zzar.zze(zzahd2);
            if (zze == null) {
                zzahw.zzcz("Could not get mediation view");
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
                    if (zzbt.zzfh().zzr(this.zzanm.zzaiq)) {
                        new zzgr(this.zzanm.zzaiq, zze).zza((zzgv) new zzagt(this.zzanm.zzaiq, this.zzanm.zzatx));
                    }
                    if (zzahd2.zzdcl != null) {
                        this.zzanm.zzaua.setMinimumWidth(zzahd2.zzdcl.widthPixels);
                        this.zzanm.zzaua.setMinimumHeight(zzahd2.zzdcl.heightPixels);
                    }
                    zzg(zze);
                } catch (Exception e) {
                    zzbt.zzep().zza(e, "BannerAdManager.swapViews");
                    zzahw.zzc("Could not add mediation view to view hierarchy.", e);
                    return false;
                }
            }
        } else if (!(zzahd2.zzdcl == null || zzahd2.zzcnm == null)) {
            zzahd2.zzcnm.zza(zzaqa.zzc(zzahd2.zzdcl));
            this.zzanm.zzaua.removeAllViews();
            this.zzanm.zzaua.setMinimumWidth(zzahd2.zzdcl.widthPixels);
            this.zzanm.zzaua.setMinimumHeight(zzahd2.zzdcl.heightPixels);
            zzg(zzahd2.zzcnm.getView());
        }
        if (this.zzanm.zzaua.getChildCount() > 1) {
            this.zzanm.zzaua.showNext();
        }
        if (zzahd != null) {
            View nextView2 = this.zzanm.zzaua.getNextView();
            if (nextView2 instanceof zzaof) {
                ((zzaof) nextView2).destroy();
            } else if (nextView2 != null) {
                this.zzanm.zzaua.removeView(nextView2);
            }
            this.zzanm.zzfn();
        }
        this.zzanm.zzaua.setVisibility(0);
        return true;
    }

    public final zzmm getVideoController() {
        zzbq.zzgn("getVideoController must be called from the main thread.");
        if (this.zzanm.zzaue == null || this.zzanm.zzaue.zzcnm == null) {
            return null;
        }
        return this.zzanm.zzaue.zzcnm.zzth();
    }

    public final void onGlobalLayout() {
        zzd(this.zzanm.zzaue);
    }

    public final void onScrollChanged() {
        zzd(this.zzanm.zzaue);
    }

    public final void setManualImpressionsEnabled(boolean z) {
        zzbq.zzgn("setManualImpressionsEnabled must be called from the main thread.");
        this.zzamp = z;
    }

    public final void showInterstitial() {
        throw new IllegalStateException("Interstitial is NOT supported by BannerAdManager.");
    }

    /* access modifiers changed from: protected */
    public final zzaof zza(zzahe zzahe, zzw zzw, zzagq zzagq) throws zzaop {
        zzko zzko;
        AdSize adSize;
        if (this.zzanm.zzaud.zzbic == null && this.zzanm.zzaud.zzbie) {
            zzbu zzbu = this.zzanm;
            if (zzahe.zzdcw.zzbie) {
                zzko = this.zzanm.zzaud;
            } else {
                String str = zzahe.zzdcw.zzctr;
                if (str != null) {
                    String[] split = str.split("[xX]");
                    split[0] = split[0].trim();
                    split[1] = split[1].trim();
                    adSize = new AdSize(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
                } else {
                    adSize = this.zzanm.zzaud.zzic();
                }
                zzko = new zzko(this.zzanm.zzaiq, adSize);
            }
            zzbu.zzaud = zzko;
        }
        return super.zza(zzahe, zzw, zzagq);
    }

    /* access modifiers changed from: protected */
    public final void zza(zzahd zzahd, boolean z) {
        super.zza(zzahd, z);
        if (zzar.zzf(zzahd)) {
            zzab zzab = new zzab(this);
            if (zzahd != null && zzar.zzf(zzahd)) {
                zzaof zzaof = zzahd.zzcnm;
                View view = zzaof != null ? zzaof.getView() : null;
                if (view == null) {
                    zzahw.zzcz("AdWebView is null");
                    return;
                }
                try {
                    List<String> list = zzahd.zzcje != null ? zzahd.zzcje.zzchr : null;
                    if (list != null) {
                        if (!list.isEmpty()) {
                            zzwr zzmp = zzahd.zzcjf != null ? zzahd.zzcjf.zzmp() : null;
                            zzwu zzmq = zzahd.zzcjf != null ? zzahd.zzcjf.zzmq() : null;
                            if (list.contains("2") && zzmp != null) {
                                zzmp.zzi(zzn.zzz(view));
                                if (!zzmp.getOverrideImpressionRecording()) {
                                    zzmp.recordImpression();
                                }
                                zzaof.zzua().zza("/nativeExpressViewClicked", (zzt<? super zzaof>) zzar.zza(zzmp, (zzwu) null, zzab));
                                return;
                            } else if (!list.contains("1") || zzmq == null) {
                                zzahw.zzcz("No matching template id and mapper");
                                return;
                            } else {
                                zzmq.zzi(zzn.zzz(view));
                                if (!zzmq.getOverrideImpressionRecording()) {
                                    zzmq.recordImpression();
                                }
                                zzaof.zzua().zza("/nativeExpressViewClicked", (zzt<? super zzaof>) zzar.zza((zzwr) null, zzmq, zzab));
                                return;
                            }
                        }
                    }
                    zzahw.zzcz("No template ids present in mediation response");
                } catch (RemoteException e) {
                    zzahw.zzc("Error occurred while recording impression and registering for clicks", e);
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0079, code lost:
        if (((java.lang.Boolean) com.google.android.gms.internal.zzlc.zzio().zzd(com.google.android.gms.internal.zzoi.zzbsh)).booleanValue() != false) goto L_0x007b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zza(com.google.android.gms.internal.zzahd r5, com.google.android.gms.internal.zzahd r6) {
        /*
            r4 = this;
            boolean r0 = super.zza((com.google.android.gms.internal.zzahd) r5, (com.google.android.gms.internal.zzahd) r6)
            r1 = 0
            if (r0 != 0) goto L_0x0008
            return r1
        L_0x0008:
            com.google.android.gms.ads.internal.zzbu r0 = r4.zzanm
            boolean r0 = r0.zzfo()
            if (r0 == 0) goto L_0x0025
            boolean r5 = r4.zzd(r5, r6)
            if (r5 != 0) goto L_0x0025
            com.google.android.gms.internal.zziu r5 = r6.zzdcu
            if (r5 == 0) goto L_0x0021
            com.google.android.gms.internal.zziu r5 = r6.zzdcu
            com.google.android.gms.internal.zziw$zza$zzb r6 = com.google.android.gms.internal.zziw.zza.zzb.AD_FAILED_TO_LOAD
            r5.zza((com.google.android.gms.internal.zziw.zza.zzb) r6)
        L_0x0021:
            r4.zzi(r1)
            return r1
        L_0x0025:
            boolean r5 = r6.zzcuf
            r0 = 0
            if (r5 == 0) goto L_0x0061
            r4.zzd(r6)
            com.google.android.gms.ads.internal.zzbt.zzfg()
            com.google.android.gms.ads.internal.zzbu r5 = r4.zzanm
            com.google.android.gms.ads.internal.zzbv r5 = r5.zzaua
            com.google.android.gms.internal.zzaml.zza((android.view.View) r5, (android.view.ViewTreeObserver.OnGlobalLayoutListener) r4)
            com.google.android.gms.ads.internal.zzbt.zzfg()
            com.google.android.gms.ads.internal.zzbu r5 = r4.zzanm
            com.google.android.gms.ads.internal.zzbv r5 = r5.zzaua
            com.google.android.gms.internal.zzaml.zza((android.view.View) r5, (android.view.ViewTreeObserver.OnScrollChangedListener) r4)
            boolean r5 = r6.zzdci
            if (r5 != 0) goto L_0x007e
            com.google.android.gms.ads.internal.zzaa r5 = new com.google.android.gms.ads.internal.zzaa
            r5.<init>(r4)
            com.google.android.gms.internal.zzaof r1 = r6.zzcnm
            if (r1 == 0) goto L_0x0055
            com.google.android.gms.internal.zzaof r1 = r6.zzcnm
            com.google.android.gms.internal.zzapu r1 = r1.zzua()
            goto L_0x0056
        L_0x0055:
            r1 = r0
        L_0x0056:
            if (r1 == 0) goto L_0x007e
            com.google.android.gms.ads.internal.zzy r2 = new com.google.android.gms.ads.internal.zzy
            r2.<init>(r6, r5)
            r1.zza((com.google.android.gms.internal.zzapy) r2)
            goto L_0x007e
        L_0x0061:
            com.google.android.gms.ads.internal.zzbu r5 = r4.zzanm
            boolean r5 = r5.zzfp()
            if (r5 == 0) goto L_0x007b
            com.google.android.gms.internal.zzny<java.lang.Boolean> r5 = com.google.android.gms.internal.zzoi.zzbsh
            com.google.android.gms.internal.zzog r2 = com.google.android.gms.internal.zzlc.zzio()
            java.lang.Object r5 = r2.zzd(r5)
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r5 = r5.booleanValue()
            if (r5 == 0) goto L_0x007e
        L_0x007b:
            r4.zza((com.google.android.gms.internal.zzahd) r6, (boolean) r1)
        L_0x007e:
            com.google.android.gms.internal.zzaof r5 = r6.zzcnm
            if (r5 == 0) goto L_0x00a2
            com.google.android.gms.internal.zzaof r5 = r6.zzcnm
            com.google.android.gms.internal.zzaou r5 = r5.zzth()
            com.google.android.gms.internal.zzaof r1 = r6.zzcnm
            com.google.android.gms.internal.zzapu r1 = r1.zzua()
            if (r1 == 0) goto L_0x0093
            r1.zzut()
        L_0x0093:
            com.google.android.gms.ads.internal.zzbu r1 = r4.zzanm
            com.google.android.gms.internal.zzns r1 = r1.zzaur
            if (r1 == 0) goto L_0x00a2
            if (r5 == 0) goto L_0x00a2
            com.google.android.gms.ads.internal.zzbu r1 = r4.zzanm
            com.google.android.gms.internal.zzns r1 = r1.zzaur
            r5.zzb(r1)
        L_0x00a2:
            com.google.android.gms.ads.internal.zzbu r5 = r4.zzanm
            boolean r5 = r5.zzfo()
            if (r5 == 0) goto L_0x0115
            com.google.android.gms.internal.zzaof r5 = r6.zzcnm
            if (r5 == 0) goto L_0x0130
            org.json.JSONObject r5 = r6.zzdch
            if (r5 == 0) goto L_0x00bb
            com.google.android.gms.internal.zzfu r5 = r4.zzano
            com.google.android.gms.ads.internal.zzbu r0 = r4.zzanm
            com.google.android.gms.internal.zzko r0 = r0.zzaud
            r5.zza(r0, r6)
        L_0x00bb:
            com.google.android.gms.internal.zzaof r5 = r6.zzcnm
            android.view.View r0 = r5.getView()
            com.google.android.gms.internal.zzgr r5 = new com.google.android.gms.internal.zzgr
            com.google.android.gms.ads.internal.zzbu r1 = r4.zzanm
            android.content.Context r1 = r1.zzaiq
            r5.<init>(r1, r0)
            com.google.android.gms.internal.zzagu r1 = com.google.android.gms.ads.internal.zzbt.zzfh()
            com.google.android.gms.ads.internal.zzbu r2 = r4.zzanm
            android.content.Context r2 = r2.zzaiq
            boolean r1 = r1.zzr(r2)
            if (r1 == 0) goto L_0x00fa
            com.google.android.gms.internal.zzkk r1 = r6.zzcrv
            boolean r1 = zza((com.google.android.gms.internal.zzkk) r1)
            if (r1 == 0) goto L_0x00fa
            com.google.android.gms.ads.internal.zzbu r1 = r4.zzanm
            java.lang.String r1 = r1.zzatx
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 != 0) goto L_0x00fa
            com.google.android.gms.internal.zzagt r1 = new com.google.android.gms.internal.zzagt
            com.google.android.gms.ads.internal.zzbu r2 = r4.zzanm
            android.content.Context r2 = r2.zzaiq
            com.google.android.gms.ads.internal.zzbu r3 = r4.zzanm
            java.lang.String r3 = r3.zzatx
            r1.<init>(r2, r3)
            r5.zza((com.google.android.gms.internal.zzgv) r1)
        L_0x00fa:
            boolean r1 = r6.zzfz()
            if (r1 == 0) goto L_0x0106
            com.google.android.gms.internal.zzaof r1 = r6.zzcnm
            r5.zza((com.google.android.gms.internal.zzgv) r1)
            goto L_0x0130
        L_0x0106:
            com.google.android.gms.internal.zzaof r1 = r6.zzcnm
            com.google.android.gms.internal.zzapu r1 = r1.zzua()
            com.google.android.gms.ads.internal.zzz r2 = new com.google.android.gms.ads.internal.zzz
            r2.<init>(r5, r6)
            r1.zza((com.google.android.gms.internal.zzapx) r2)
            goto L_0x0130
        L_0x0115:
            com.google.android.gms.ads.internal.zzbu r5 = r4.zzanm
            android.view.View r5 = r5.zzava
            if (r5 == 0) goto L_0x0130
            org.json.JSONObject r5 = r6.zzdch
            if (r5 == 0) goto L_0x0130
            com.google.android.gms.internal.zzfu r5 = r4.zzano
            com.google.android.gms.ads.internal.zzbu r0 = r4.zzanm
            com.google.android.gms.internal.zzko r0 = r0.zzaud
            com.google.android.gms.ads.internal.zzbu r1 = r4.zzanm
            android.view.View r1 = r1.zzava
            r5.zza(r0, r6, r1)
            com.google.android.gms.ads.internal.zzbu r5 = r4.zzanm
            android.view.View r0 = r5.zzava
        L_0x0130:
            boolean r5 = r6.zzcto
            if (r5 != 0) goto L_0x0139
            com.google.android.gms.ads.internal.zzbu r5 = r4.zzanm
            r5.zzi(r0)
        L_0x0139:
            r5 = 1
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.zzx.zza(com.google.android.gms.internal.zzahd, com.google.android.gms.internal.zzahd):boolean");
    }

    public final boolean zzb(zzkk zzkk) {
        zzkk zzkk2 = zzkk;
        if (zzkk2.zzbha != this.zzamp) {
            zzkk2 = new zzkk(zzkk2.versionCode, zzkk2.zzbgv, zzkk2.extras, zzkk2.zzbgw, zzkk2.zzbgx, zzkk2.zzbgy, zzkk2.zzbgz, zzkk2.zzbha || this.zzamp, zzkk2.zzbhb, zzkk2.zzbhc, zzkk2.zzbhd, zzkk2.zzbhe, zzkk2.zzbhf, zzkk2.zzbhg, zzkk2.zzbhh, zzkk2.zzbhi, zzkk2.zzbhj, zzkk2.zzbhk);
        }
        return super.zzb(zzkk2);
    }

    /* access modifiers changed from: protected */
    public final boolean zzce() {
        boolean z;
        zzbt.zzel();
        if (!zzaij.zzd(this.zzanm.zzaiq, this.zzanm.zzaiq.getPackageName(), "android.permission.INTERNET")) {
            zzlc.zzij().zza(this.zzanm.zzaua, this.zzanm.zzaud, "Missing internet permission in AndroidManifest.xml.", "Missing internet permission in AndroidManifest.xml. You must have the following declaration: <uses-permission android:name=\"android.permission.INTERNET\" />");
            z = false;
        } else {
            z = true;
        }
        zzbt.zzel();
        if (!zzaij.zzag(this.zzanm.zzaiq)) {
            zzlc.zzij().zza(this.zzanm.zzaua, this.zzanm.zzaud, "Missing AdActivity with android:configChanges in AndroidManifest.xml.", "Missing AdActivity with android:configChanges in AndroidManifest.xml. You must have the following declaration within the <application> element: <activity android:name=\"com.google.android.gms.ads.AdActivity\" android:configChanges=\"keyboard|keyboardHidden|orientation|screenLayout|uiMode|screenSize|smallestScreenSize\" />");
            z = false;
        }
        if (!z && this.zzanm.zzaua != null) {
            this.zzanm.zzaua.setVisibility(0);
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    public final void zzd(zzahd zzahd) {
        if (zzahd != null && !zzahd.zzdci && this.zzanm.zzaua != null && zzbt.zzel().zza((View) this.zzanm.zzaua, this.zzanm.zzaiq) && this.zzanm.zzaua.getGlobalVisibleRect(new Rect(), (Point) null)) {
            if (!(zzahd == null || zzahd.zzcnm == null || zzahd.zzcnm.zzua() == null)) {
                zzahd.zzcnm.zzua().zza((zzapy) null);
            }
            zza(zzahd, false);
            zzahd.zzdci = true;
        }
    }

    public final void zzda() {
        this.zzanl.zzdz();
    }
}
