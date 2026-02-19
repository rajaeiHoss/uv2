package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.zzak;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.ads.internal.zzv;
import com.google.android.gms.dynamic.IObjectWrapper;

@zzabh
public final class zzut extends zzlu {
    private final String zzapp;
    private boolean zzaqf;
    private final zztk zzceb;
    private zzak zzceg;
    private final zzul zzcet;

    public zzut(Context context, String str, zzwf zzwf, zzala zzala, zzv zzv) {
        this(str, new zztk(context, zzwf, zzala, zzv));
    }

    private zzut(String str, zztk zztk) {
        this.zzapp = str;
        this.zzceb = zztk;
        this.zzcet = new zzul();
        zzbt.zzey().zza(zztk);
    }

    private final void abort() {
        if (this.zzceg == null) {
            zzak zzav = this.zzceb.zzav(this.zzapp);
            this.zzceg = zzav;
            this.zzcet.zzd(zzav);
        }
    }

    public final void destroy() throws RemoteException {
        zzak zzak = this.zzceg;
        if (zzak != null) {
            zzak.destroy();
        }
    }

    public final String getAdUnitId() {
        throw new IllegalStateException("getAdUnitId not implemented");
    }

    public final String getMediationAdapterClassName() throws RemoteException {
        zzak zzak = this.zzceg;
        if (zzak != null) {
            return zzak.getMediationAdapterClassName();
        }
        return null;
    }

    public final zzmm getVideoController() {
        throw new IllegalStateException("getVideoController not implemented for interstitials");
    }

    public final boolean isLoading() throws RemoteException {
        zzak zzak = this.zzceg;
        return zzak != null && zzak.isLoading();
    }

    public final boolean isReady() throws RemoteException {
        zzak zzak = this.zzceg;
        return zzak != null && zzak.isReady();
    }

    public final void pause() throws RemoteException {
        zzak zzak = this.zzceg;
        if (zzak != null) {
            zzak.pause();
        }
    }

    public final void resume() throws RemoteException {
        zzak zzak = this.zzceg;
        if (zzak != null) {
            zzak.resume();
        }
    }

    public final void setImmersiveMode(boolean z) {
        this.zzaqf = z;
    }

    public final void setManualImpressionsEnabled(boolean z) throws RemoteException {
        abort();
        zzak zzak = this.zzceg;
        if (zzak != null) {
            zzak.setManualImpressionsEnabled(z);
        }
    }

    public final void setUserId(String str) {
    }

    public final void showInterstitial() throws RemoteException {
        zzak zzak = this.zzceg;
        if (zzak != null) {
            zzak.setImmersiveMode(this.zzaqf);
            this.zzceg.showInterstitial();
            return;
        }
        zzahw.zzcz("Interstitial ad must be loaded before showInterstitial().");
    }

    public final void stopLoading() throws RemoteException {
        zzak zzak = this.zzceg;
        if (zzak != null) {
            zzak.stopLoading();
        }
    }

    public final void zza(zzafc zzafc) {
        this.zzcet.zzcdw = zzafc;
        zzak zzak = this.zzceg;
        if (zzak != null) {
            this.zzcet.zzd(zzak);
        }
    }

    public final void zza(zzko zzko) throws RemoteException {
        zzak zzak = this.zzceg;
        if (zzak != null) {
            zzak.zza(zzko);
        }
    }

    public final void zza(zzlf zzlf) throws RemoteException {
        this.zzcet.zzcdv = zzlf;
        zzak zzak = this.zzceg;
        if (zzak != null) {
            this.zzcet.zzd(zzak);
        }
    }

    public final void zza(zzli zzli) throws RemoteException {
        this.zzcet.zzapd = zzli;
        zzak zzak = this.zzceg;
        if (zzak != null) {
            this.zzcet.zzd(zzak);
        }
    }

    public final void zza(zzly zzly) throws RemoteException {
        this.zzcet.zzcdt = zzly;
        zzak zzak = this.zzceg;
        if (zzak != null) {
            this.zzcet.zzd(zzak);
        }
    }

    public final void zza(zzme zzme) throws RemoteException {
        abort();
        zzak zzak = this.zzceg;
        if (zzak != null) {
            zzak.zza(zzme);
        }
    }

    public final void zza(zzms zzms) {
        throw new IllegalStateException("Unused method");
    }

    public final void zza(zzns zzns) {
        throw new IllegalStateException("getVideoController not implemented for interstitials");
    }

    public final void zza(zzpb zzpb) throws RemoteException {
        this.zzcet.zzcdu = zzpb;
        zzak zzak = this.zzceg;
        if (zzak != null) {
            this.zzcet.zzd(zzak);
        }
    }

    public final void zza(zzyx zzyx) throws RemoteException {
        zzahw.zzcz("setInAppPurchaseListener is deprecated and should not be called.");
    }

    public final void zza(zzzd zzzd, String str) throws RemoteException {
        zzahw.zzcz("setPlayStorePurchaseParams is deprecated and should not be called.");
    }

    public final boolean zzb(zzkk zzkk) throws RemoteException {
        if (!zzuo.zzh(zzkk).contains("gw")) {
            abort();
        }
        if (zzuo.zzh(zzkk).contains("_skipMediation")) {
            abort();
        }
        if (zzkk.zzbhc != null) {
            abort();
        }
        zzak zzak = this.zzceg;
        if (zzak != null) {
            return zzak.zzb(zzkk);
        }
        zzuo zzey = zzbt.zzey();
        if (zzuo.zzh(zzkk).contains("_ad")) {
            zzey.zzb(zzkk, this.zzapp);
        }
        zzur zza = zzey.zza(zzkk, this.zzapp);
        if (zza != null) {
            if (!zza.zzcek) {
                zza.load();
                zzus.zzln().zzlr();
            } else {
                zzus.zzln().zzlq();
            }
            this.zzceg = zza.zzceg;
            zza.zzcei.zza(this.zzcet);
            this.zzcet.zzd(this.zzceg);
            return zza.zzcel;
        }
        abort();
        zzus.zzln().zzlr();
        return this.zzceg.zzb(zzkk);
    }

    public final IObjectWrapper zzbp() throws RemoteException {
        zzak zzak = this.zzceg;
        if (zzak != null) {
            return zzak.zzbp();
        }
        return null;
    }

    public final zzko zzbq() throws RemoteException {
        zzak zzak = this.zzceg;
        if (zzak != null) {
            return zzak.zzbq();
        }
        return null;
    }

    public final void zzbs() throws RemoteException {
        zzak zzak = this.zzceg;
        if (zzak != null) {
            zzak.zzbs();
        } else {
            zzahw.zzcz("Interstitial ad must be loaded before pingManualTrackingUrl().");
        }
    }

    public final zzly zzcc() {
        throw new IllegalStateException("getIAppEventListener not implemented");
    }

    public final zzli zzcd() {
        throw new IllegalStateException("getIAdListener not implemented");
    }

    public final String zzco() throws RemoteException {
        zzak zzak = this.zzceg;
        if (zzak != null) {
            return zzak.zzco();
        }
        return null;
    }
}
