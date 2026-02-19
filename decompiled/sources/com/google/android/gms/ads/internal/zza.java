package com.google.android.gms.ads.internal;

import android.location.Location;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewParent;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.internal.gmsg.zzb;
import com.google.android.gms.ads.internal.overlay.zzt;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zzj;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzabj;
import com.google.android.gms.internal.zzaep;
import com.google.android.gms.internal.zzafc;
import com.google.android.gms.internal.zzagd;
import com.google.android.gms.internal.zzagx;
import com.google.android.gms.internal.zzahd;
import com.google.android.gms.internal.zzahe;
import com.google.android.gms.internal.zzahf;
import com.google.android.gms.internal.zzahn;
import com.google.android.gms.internal.zzahp;
import com.google.android.gms.internal.zzahu;
import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzaij;
import com.google.android.gms.internal.zzako;
import com.google.android.gms.internal.zzaqa;
import com.google.android.gms.internal.zzfu;
import com.google.android.gms.internal.zziu;
import com.google.android.gms.internal.zziv;
import com.google.android.gms.internal.zziw;
import com.google.android.gms.internal.zzkf;
import com.google.android.gms.internal.zzkk;
import com.google.android.gms.internal.zzkl;
import com.google.android.gms.internal.zzko;
import com.google.android.gms.internal.zzlc;
import com.google.android.gms.internal.zzlf;
import com.google.android.gms.internal.zzli;
import com.google.android.gms.internal.zzlu;
import com.google.android.gms.internal.zzly;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzmm;
import com.google.android.gms.internal.zzms;
import com.google.android.gms.internal.zznq;
import com.google.android.gms.internal.zzns;
import com.google.android.gms.internal.zzoi;
import com.google.android.gms.internal.zzot;
import com.google.android.gms.internal.zzov;
import com.google.android.gms.internal.zzpb;
import com.google.android.gms.internal.zzyx;
import com.google.android.gms.internal.zzzd;
import com.google.android.gms.internal.zzzn;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.CountDownLatch;

@zzabh
public abstract class zza extends zzlu implements zzb, zzt, zzabj, zzahn, zzkf, zzzn {
    protected zzov zzanh;
    protected zzot zzani;
    private zzot zzanj;
    protected boolean zzank = false;
    protected final zzbj zzanl;
    protected final zzbu zzanm;
    protected transient zzkk zzann;
    protected final zzfu zzano;
    protected final zzv zzanp;

    zza(zzbu zzbu, zzbj zzbj, zzv zzv) {
        this.zzanm = zzbu;
        this.zzanl = new zzbj(this);
        this.zzanp = zzv;
        zzbt.zzel().zzah(zzbu.zzaiq);
        zzbt.zzel().zzai(zzbu.zzaiq);
        zzahu.zzaf(zzbu.zzaiq);
        zzbt.zzfi().initialize(zzbu.zzaiq);
        zzbt.zzep().zzd(zzbu.zzaiq, zzbu.zzatz);
        zzbt.zzer().initialize(zzbu.zzaiq);
        this.zzano = zzbt.zzep().zzqa();
        zzbt.zzeo().initialize(zzbu.zzaiq);
        zzbt.zzfk().initialize(zzbu.zzaiq);
        if (((Boolean) zzlc.zzio().zzd(zzoi.zzbsy)).booleanValue()) {
            Timer timer = new Timer();
            timer.schedule(new com.google.android.gms.ads.internal.zzb(this, new CountDownLatch(((Integer) zzlc.zzio().zzd(zzoi.zzbta)).intValue()), timer), 0, ((Long) zzlc.zzio().zzd(zzoi.zzbsz)).longValue());
        }
    }

    protected static boolean zza(zzkk zzkk) {
        Bundle bundle = zzkk.zzbhf.getBundle("com.google.ads.mediation.admob.AdMobAdapter");
        return bundle == null || !bundle.containsKey("gw");
    }

    private static long zzr(String str) {
        String str2;
        int indexOf = str.indexOf("ufe");
        int indexOf2 = str.indexOf(44, indexOf);
        if (indexOf2 == -1) {
            indexOf2 = str.length();
        }
        try {
            return Long.parseLong(str.substring(indexOf + 4, indexOf2));
        } catch (IndexOutOfBoundsException unused) {
            str2 = "Invalid index for Url fetch time in CSI latency info.";
            zzahw.zzcz(str2);
            return -1;
        } catch (NumberFormatException unused2) {
            str2 = "Cannot find valid format of Url fetch time in CSI latency info.";
            zzahw.zzcz(str2);
            return -1;
        }
    }

    public void destroy() {
        zzbq.zzgn("destroy must be called on the main UI thread.");
        this.zzanl.cancel();
        this.zzano.zzi(this.zzanm.zzaue);
        zzbu zzbu = this.zzanm;
        if (zzbu.zzaua != null) {
            zzbu.zzaua.zzfs();
        }
        zzbu.zzaui = null;
        zzbu.zzauj = null;
        zzbu.zzauv = null;
        zzbu.zzauk = null;
        zzbu.zzg(false);
        if (zzbu.zzaua != null) {
            zzbu.zzaua.removeAllViews();
        }
        zzbu.zzfm();
        zzbu.zzfn();
        zzbu.zzaue = null;
    }

    public String getAdUnitId() {
        return this.zzanm.zzatx;
    }

    public zzmm getVideoController() {
        return null;
    }

    public final boolean isLoading() {
        return this.zzank;
    }

    public final boolean isReady() {
        zzbq.zzgn("isLoaded must be called on the main UI thread.");
        return this.zzanm.zzaub == null && this.zzanm.zzauc == null && this.zzanm.zzaue != null;
    }

    public void onAdClicked() {
        if (this.zzanm.zzaue == null) {
            zzahw.zzcz("Ad state was null when trying to ping click URLs.");
            return;
        }
        zzahw.zzby("Pinging click URLs.");
        if (this.zzanm.zzaug != null) {
            this.zzanm.zzaug.zzpl();
        }
        if (this.zzanm.zzaue.zzchw != null) {
            zzbt.zzel();
            zzaij.zza(this.zzanm.zzaiq, this.zzanm.zzatz.zzcu, zzc(this.zzanm.zzaue.zzchw));
        }
        if (this.zzanm.zzauh != null) {
            try {
                this.zzanm.zzauh.onAdClicked();
            } catch (RemoteException e) {
                zzahw.zzc("Could not notify onAdClicked event.", e);
            }
        }
    }

    public final void onAppEvent(String str, String str2) {
        if (this.zzanm.zzauj != null) {
            try {
                this.zzanm.zzauj.onAppEvent(str, str2);
            } catch (RemoteException e) {
                zzahw.zzc("Could not call the AppEventListener.", e);
            }
        }
    }

    public void pause() {
        zzbq.zzgn("pause must be called on the main UI thread.");
    }

    public void resume() {
        zzbq.zzgn("resume must be called on the main UI thread.");
    }

    public void setImmersiveMode(boolean z) {
        throw new IllegalStateException("onImmersiveModeUpdated is not supported for current ad type");
    }

    public void setManualImpressionsEnabled(boolean z) {
        zzahw.zzcz("Attempt to call setManualImpressionsEnabled for an unsupported ad type.");
    }

    public final void setUserId(String str) {
        zzbq.zzgn("setUserId must be called on the main UI thread.");
        this.zzanm.zzaux = str;
    }

    public final void stopLoading() {
        zzbq.zzgn("stopLoading must be called on the main UI thread.");
        this.zzank = false;
        this.zzanm.zzg(true);
    }

    public final void zza(zzafc zzafc) {
        zzbq.zzgn("setRewardedVideoAdListener can only be called from the UI thread.");
        this.zzanm.zzauw = zzafc;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzagd zzagd) {
        if (this.zzanm.zzauw != null) {
            String str = "";
            int i = 1;
            if (zzagd != null) {
                str = zzagd.type;
                i = zzagd.zzdax;
            }
            try {
                this.zzanm.zzauw.zza(new zzaep(str, i));
            } catch (RemoteException e) {
                zzahw.zzc("Could not call RewardedVideoAdListener.onRewarded().", e);
            }
        }
    }

    public final void zza(zzahe zzahe) {
        if (zzahe.zzdcw.zzcts != -1 && !TextUtils.isEmpty(zzahe.zzdcw.zzcub)) {
            long zzr = zzr(zzahe.zzdcw.zzcub);
            if (zzr != -1) {
                this.zzanh.zza(this.zzanh.zzd(zzahe.zzdcw.zzcts + zzr), "stc");
            }
        }
        this.zzanh.zzao(zzahe.zzdcw.zzcub);
        this.zzanh.zza(this.zzani, "arf");
        this.zzanj = this.zzanh.zzjo();
        this.zzanh.zzf("gqi", zzahe.zzdcw.zzbdl);
        this.zzanm.zzaub = null;
        this.zzanm.zzauf = zzahe;
        zzahe.zzdcu.zza((zziv) new zzc(this, zzahe));
        zzahe.zzdcu.zza(zziw.zza.zzb.AD_LOADED);
        zza(zzahe, this.zzanh);
    }

    /* access modifiers changed from: protected */
    public abstract void zza(zzahe zzahe, zzov zzov);

    public final void zza(zzko zzko) {
        zzbq.zzgn("setAdSize must be called on the main UI thread.");
        this.zzanm.zzaud = zzko;
        if (!(this.zzanm.zzaue == null || this.zzanm.zzaue.zzcnm == null || this.zzanm.zzavb != 0)) {
            this.zzanm.zzaue.zzcnm.zza(zzaqa.zzc(zzko));
        }
        if (this.zzanm.zzaua != null) {
            if (this.zzanm.zzaua.getChildCount() > 1) {
                this.zzanm.zzaua.removeView(this.zzanm.zzaua.getNextView());
            }
            this.zzanm.zzaua.setMinimumWidth(zzko.widthPixels);
            this.zzanm.zzaua.setMinimumHeight(zzko.heightPixels);
            this.zzanm.zzaua.requestLayout();
        }
    }

    public final void zza(zzlf zzlf) {
        zzbq.zzgn("setAdListener must be called on the main UI thread.");
        this.zzanm.zzauh = zzlf;
    }

    public final void zza(zzli zzli) {
        zzbq.zzgn("setAdListener must be called on the main UI thread.");
        this.zzanm.zzaui = zzli;
    }

    public final void zza(zzly zzly) {
        zzbq.zzgn("setAppEventListener must be called on the main UI thread.");
        this.zzanm.zzauj = zzly;
    }

    public final void zza(zzme zzme) {
        zzbq.zzgn("setCorrelationIdProvider must be called on the main UI thread");
        this.zzanm.zzauk = zzme;
    }

    public final void zza(zzms zzms) {
        zzbq.zzgn("setIconAdOptions must be called on the main UI thread.");
        this.zzanm.zzaus = zzms;
    }

    public final void zza(zzns zzns) {
        zzbq.zzgn("setVideoOptions must be called on the main UI thread.");
        this.zzanm.zzaur = zzns;
    }

    public final void zza(zzot zzot) {
        this.zzanh = new zzov(((Boolean) zzlc.zzio().zzd(zzoi.zzbne)).booleanValue(), "load_ad", this.zzanm.zzaud.zzbia);
        this.zzanj = new zzot(-1, (String) null, (zzot) null);
        if (zzot == null) {
            this.zzani = new zzot(-1, (String) null, (zzot) null);
        } else {
            this.zzani = new zzot(zzot.getTime(), zzot.zzjl(), zzot.zzjm());
        }
    }

    public void zza(zzpb zzpb) {
        throw new IllegalStateException("setOnCustomRenderedAdLoadedListener is not supported for current ad type");
    }

    public void zza(zzyx zzyx) {
        zzahw.zzcz("setInAppPurchaseListener is deprecated and should not be called.");
    }

    public final void zza(zzzd zzzd, String str) {
        zzahw.zzcz("setPlayStorePurchaseParams is deprecated and should not be called.");
    }

    public final void zza(HashSet<zzahf> hashSet) {
        this.zzanm.zza(hashSet);
    }

    /* access modifiers changed from: package-private */
    public boolean zza(zzahd zzahd) {
        return false;
    }

    /* access modifiers changed from: protected */
    public abstract boolean zza(zzahd zzahd, zzahd zzahd2);

    /* access modifiers changed from: protected */
    public abstract boolean zza(zzkk zzkk, zzov zzov);

    public void zzb(zzahd zzahd) {
        zziw.zza.zzb zzb;
        zziu zziu;
        this.zzanh.zza(this.zzanj, "awr");
        this.zzanm.zzauc = null;
        if (!(zzahd.errorCode == -2 || zzahd.errorCode == 3 || this.zzanm.zzfl() == null)) {
            zzbt.zzeq().zzb(this.zzanm.zzfl());
        }
        if (zzahd.errorCode == -1) {
            this.zzank = false;
            return;
        }
        if (zza(zzahd)) {
            zzahw.zzby("Ad refresh scheduled.");
        }
        if (zzahd.errorCode != -2) {
            if (zzahd.errorCode == 3) {
                zziu = zzahd.zzdcu;
                zzb = zziw.zza.zzb.AD_FAILED_TO_LOAD_NO_FILL;
            } else {
                zziu = zzahd.zzdcu;
                zzb = zziw.zza.zzb.AD_FAILED_TO_LOAD;
            }
            zziu.zza(zzb);
            zzi(zzahd.errorCode);
            return;
        }
        if (this.zzanm.zzauz == null) {
            zzbu zzbu = this.zzanm;
            zzbu.zzauz = new zzahp(zzbu.zzatx);
        }
        if (this.zzanm.zzaua != null) {
            this.zzanm.zzaua.zzfr().zzcs(zzahd.zzcuj);
        }
        this.zzano.zzh(this.zzanm.zzaue);
        if (zza(this.zzanm.zzaue, zzahd)) {
            this.zzanm.zzaue = zzahd;
            zzbu zzbu2 = this.zzanm;
            if (zzbu2.zzaug != null) {
                if (zzbu2.zzaue != null) {
                    zzbu2.zzaug.zzh(zzbu2.zzaue.zzdcn);
                    zzbu2.zzaug.zzi(zzbu2.zzaue.zzdco);
                    zzbu2.zzaug.zzy(zzbu2.zzaue.zzcto);
                }
                zzbu2.zzaug.zzx(zzbu2.zzaud.zzbib);
            }
            String str = "1";
            this.zzanh.zzf("is_mraid", this.zzanm.zzaue.zzfz() ? str : "0");
            this.zzanh.zzf("is_mediation", this.zzanm.zzaue.zzcto ? str : "0");
            if (!(this.zzanm.zzaue.zzcnm == null || this.zzanm.zzaue.zzcnm.zzua() == null)) {
                zzov zzov = this.zzanh;
                if (!this.zzanm.zzaue.zzcnm.zzua().zzur()) {
                    str = "0";
                }
                zzov.zzf("is_delay_pl", str);
            }
            this.zzanh.zza(this.zzani, "ttc");
            if (zzbt.zzep().zzpv() != null) {
                zzbt.zzep().zzpv().zza(this.zzanh);
            }
            zzcb();
            if (this.zzanm.zzfo()) {
                zzbw();
            }
        }
        if (zzahd.zzchz != null) {
            zzbt.zzel().zza(this.zzanm.zzaiq, zzahd.zzchz);
        }
    }

    public boolean zzb(zzkk zzkk) {
        String str;
        zzbq.zzgn("loadAd must be called on the main UI thread.");
        zzbt.zzer().zzhi();
        if (((Boolean) zzlc.zzio().zzd(zzoi.zzbpi)).booleanValue()) {
            zzkk = zzkk.zzhz();
            if (((Boolean) zzlc.zzio().zzd(zzoi.zzbpj)).booleanValue()) {
                zzkk.extras.putBoolean(AdMobAdapter.NEW_BUNDLE, true);
            }
        }
        if (zzj.zzcw(this.zzanm.zzaiq) && zzkk.zzbhd != null) {
            zzkk = new zzkl(zzkk).zza((Location) null).zzia();
        }
        if (this.zzanm.zzaub == null && this.zzanm.zzauc == null) {
            zzahw.zzcy("Starting ad request.");
            zza((zzot) null);
            this.zzani = this.zzanh.zzjo();
            if (zzkk.zzbgy) {
                str = "This request is sent from a test device.";
            } else {
                zzlc.zzij();
                String zzaz = zzako.zzaz(this.zzanm.zzaiq);
                StringBuilder sb = new StringBuilder(String.valueOf(zzaz).length() + 71);
                sb.append("Use AdRequest.Builder.addTestDevice(\"");
                sb.append(zzaz);
                sb.append("\") to get test ads on this device.");
                str = sb.toString();
            }
            zzahw.zzcy(str);
            this.zzanl.zzf(zzkk);
            boolean zza = zza(zzkk, this.zzanh);
            this.zzank = zza;
            return zza;
        }
        zzahw.zzcz(this.zzann != null ? "Aborting last ad request since another ad request is already in progress. The current request object will still be cached for future refreshes." : "Loading already in progress, saving this object for future refreshes.");
        this.zzann = zzkk;
        return false;
    }

    public final zzv zzbo() {
        return this.zzanp;
    }

    public final IObjectWrapper zzbp() {
        zzbq.zzgn("getAdFrame must be called on the main UI thread.");
        return zzn.zzz(this.zzanm.zzaua);
    }

    public final zzko zzbq() {
        zzbq.zzgn("getAdSize must be called on the main UI thread.");
        if (this.zzanm.zzaud == null) {
            return null;
        }
        return new zznq(this.zzanm.zzaud);
    }

    public final void zzbr() {
        zzbu();
    }

    public final void zzbs() {
        zzbq.zzgn("recordManualImpression must be called on the main UI thread.");
        if (this.zzanm.zzaue == null) {
            zzahw.zzcz("Ad state was null when trying to ping manual tracking URLs.");
            return;
        }
        zzahw.zzby("Pinging manual tracking URLs.");
        if (!this.zzanm.zzaue.zzdcs) {
            ArrayList arrayList = new ArrayList();
            if (this.zzanm.zzaue.zzctq != null) {
                arrayList.addAll(this.zzanm.zzaue.zzctq);
            }
            if (!(this.zzanm.zzaue.zzcje == null || this.zzanm.zzaue.zzcje.zzchi == null)) {
                arrayList.addAll(this.zzanm.zzaue.zzcje.zzchi);
            }
            if (!arrayList.isEmpty()) {
                zzbt.zzel();
                zzaij.zza(this.zzanm.zzaiq, this.zzanm.zzatz.zzcu, (List<String>) arrayList);
                this.zzanm.zzaue.zzdcs = true;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void zzbt() {
        zzahw.zzcy("Ad closing.");
        if (this.zzanm.zzaui != null) {
            try {
                this.zzanm.zzaui.onAdClosed();
            } catch (RemoteException e) {
                zzahw.zzc("Could not call AdListener.onAdClosed().", e);
            }
        }
        if (this.zzanm.zzauw != null) {
            try {
                this.zzanm.zzauw.onRewardedVideoAdClosed();
            } catch (RemoteException e2) {
                zzahw.zzc("Could not call RewardedVideoAdListener.onRewardedVideoAdClosed().", e2);
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void zzbu() {
        zzahw.zzcy("Ad leaving application.");
        if (this.zzanm.zzaui != null) {
            try {
                this.zzanm.zzaui.onAdLeftApplication();
            } catch (RemoteException e) {
                zzahw.zzc("Could not call AdListener.onAdLeftApplication().", e);
            }
        }
        if (this.zzanm.zzauw != null) {
            try {
                this.zzanm.zzauw.onRewardedVideoAdLeftApplication();
            } catch (RemoteException e2) {
                zzahw.zzc("Could not call  RewardedVideoAdListener.onRewardedVideoAdLeftApplication().", e2);
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void zzbv() {
        zzahw.zzcy("Ad opening.");
        if (this.zzanm.zzaui != null) {
            try {
                this.zzanm.zzaui.onAdOpened();
            } catch (RemoteException e) {
                zzahw.zzc("Could not call AdListener.onAdOpened().", e);
            }
        }
        if (this.zzanm.zzauw != null) {
            try {
                this.zzanm.zzauw.onRewardedVideoAdOpened();
            } catch (RemoteException e2) {
                zzahw.zzc("Could not call RewardedVideoAdListener.onRewardedVideoAdOpened().", e2);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void zzbw() {
        zzc(false);
    }

    public final void zzbx() {
        zzahw.zzcy("Ad impression.");
        if (this.zzanm.zzaui != null) {
            try {
                this.zzanm.zzaui.onAdImpression();
            } catch (RemoteException e) {
                zzahw.zzc("Could not call AdListener.onAdImpression().", e);
            }
        }
    }

    public final void zzby() {
        zzahw.zzcy("Ad clicked.");
        if (this.zzanm.zzaui != null) {
            try {
                this.zzanm.zzaui.onAdClicked();
            } catch (RemoteException e) {
                zzahw.zzc("Could not call AdListener.onAdClicked().", e);
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void zzbz() {
        if (this.zzanm.zzauw != null) {
            try {
                this.zzanm.zzauw.onRewardedVideoStarted();
            } catch (RemoteException e) {
                zzahw.zzc("Could not call RewardedVideoAdListener.onVideoStarted().", e);
            }
        }
    }

    /* access modifiers changed from: protected */
    public final List<String> zzc(List<String> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (String zzb : list) {
            arrayList.add(zzagx.zzb(zzb, this.zzanm.zzaiq));
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public void zzc(int i, boolean z) {
        StringBuilder sb = new StringBuilder(30);
        sb.append("Failed to load ad: ");
        sb.append(i);
        zzahw.zzcz(sb.toString());
        this.zzank = z;
        if (this.zzanm.zzaui != null) {
            try {
                this.zzanm.zzaui.onAdFailedToLoad(i);
            } catch (RemoteException e) {
                zzahw.zzc("Could not call AdListener.onAdFailedToLoad().", e);
            }
        }
        if (this.zzanm.zzauw != null) {
            try {
                this.zzanm.zzauw.onRewardedVideoAdFailedToLoad(i);
            } catch (RemoteException e2) {
                zzahw.zzc("Could not call RewardedVideoAdListener.onRewardedVideoAdFailedToLoad().", e2);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void zzc(boolean z) {
        zzahw.zzcy("Ad finished loading.");
        this.zzank = z;
        if (this.zzanm.zzaui != null) {
            try {
                this.zzanm.zzaui.onAdLoaded();
            } catch (RemoteException e) {
                zzahw.zzc("Could not call AdListener.onAdLoaded().", e);
            }
        }
        if (this.zzanm.zzauw != null) {
            try {
                this.zzanm.zzauw.onRewardedVideoAdLoaded();
            } catch (RemoteException e2) {
                zzahw.zzc("Could not call RewardedVideoAdListener.onRewardedVideoAdLoaded().", e2);
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean zzc(zzkk zzkk) {
        if (this.zzanm.zzaua == null) {
            return false;
        }
        ViewParent parent = this.zzanm.zzaua.getParent();
        if (!(parent instanceof View)) {
            return false;
        }
        View view = (View) parent;
        return zzbt.zzel().zza(view, view.getContext());
    }

    /* access modifiers changed from: protected */
    public final void zzca() {
        if (this.zzanm.zzauw != null) {
            try {
                this.zzanm.zzauw.onRewardedVideoCompleted();
            } catch (RemoteException e) {
                zzahw.zzc("Could not call RewardedVideoAdListener.onRewardedVideoCompleted().", e);
            }
        }
    }

    public final void zzcb() {
        zzahd zzahd = this.zzanm.zzaue;
        if (zzahd != null && !TextUtils.isEmpty(zzahd.zzcuj) && !zzahd.zzdct && zzbt.zzev().zzrr()) {
            zzahw.zzby("Sending troubleshooting signals to the server.");
            zzbt.zzev().zzb(this.zzanm.zzaiq, this.zzanm.zzatz.zzcu, zzahd.zzcuj, this.zzanm.zzatx);
            zzahd.zzdct = true;
        }
    }

    public final zzly zzcc() {
        return this.zzanm.zzauj;
    }

    public final zzli zzcd() {
        return this.zzanm.zzaui;
    }

    /* access modifiers changed from: protected */
    public final void zzg(View view) {
        zzbv zzbv = this.zzanm.zzaua;
        if (zzbv != null) {
            zzbv.addView(view, zzbt.zzen().zzrj());
        }
    }

    /* access modifiers changed from: protected */
    public void zzi(int i) {
        zzc(i, false);
    }
}
