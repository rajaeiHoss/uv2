package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.mediation.NativeAdMapper;
import com.google.android.gms.ads.mediation.NativeAppInstallAdMapper;
import com.google.android.gms.ads.mediation.NativeContentAdMapper;
import com.google.android.gms.ads.mediation.OnContextChangedListener;
import com.google.android.gms.ads.mediation.OnImmersiveModeUpdatedListener;
import com.google.android.gms.ads.mediation.zza;
import com.google.android.gms.ads.mediation.zzb;
import com.google.android.gms.ads.reward.mediation.InitializableMediationRewardedVideoAdAdapter;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

@zzabh
public final class zzxc extends zzwj {
    private final MediationAdapter zzcjz;
    private zzxd zzcka;

    public zzxc(MediationAdapter mediationAdapter) {
        this.zzcjz = mediationAdapter;
    }

    private final Bundle zza(String str, zzkk zzkk, String str2) throws RemoteException {
        String valueOf = String.valueOf(str);
        zzaky.zzcz(valueOf.length() != 0 ? "Server parameters: ".concat(valueOf) : new String("Server parameters: "));
        try {
            Bundle bundle = new Bundle();
            if (str != null) {
                JSONObject jSONObject = new JSONObject(str);
                Bundle bundle2 = new Bundle();
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    bundle2.putString(next, jSONObject.getString(next));
                }
                bundle = bundle2;
            }
            if (this.zzcjz instanceof AdMobAdapter) {
                bundle.putString("adJson", str2);
                if (zzkk != null) {
                    bundle.putInt("tagForChildDirectedTreatment", zzkk.zzbgz);
                }
            }
            return bundle;
        } catch (Throwable th) {
            zzaky.zzc("Could not get Server Parameters Bundle.", th);
            throw new RemoteException();
        }
    }

    private static boolean zzm(zzkk zzkk) {
        if (zzkk.zzbgy) {
            return true;
        }
        zzlc.zzij();
        return zzako.zzrz();
    }

    public final void destroy() throws RemoteException {
        try {
            this.zzcjz.onDestroy();
        } catch (Throwable th) {
            zzaky.zzc("Could not destroy adapter.", th);
            throw new RemoteException();
        }
    }

    public final Bundle getInterstitialAdapterInfo() {
        MediationAdapter mediationAdapter = this.zzcjz;
        if (mediationAdapter instanceof zzaqk) {
            return ((zzaqk) mediationAdapter).getInterstitialAdapterInfo();
        }
        String valueOf = String.valueOf(mediationAdapter.getClass().getCanonicalName());
        zzaky.zzcz(valueOf.length() != 0 ? "MediationAdapter is not a v2 MediationInterstitialAdapter: ".concat(valueOf) : new String("MediationAdapter is not a v2 MediationInterstitialAdapter: "));
        return new Bundle();
    }

    public final zzmm getVideoController() {
        MediationAdapter mediationAdapter = this.zzcjz;
        if (!(mediationAdapter instanceof zzb)) {
            return null;
        }
        try {
            return ((zzb) mediationAdapter).getVideoController();
        } catch (Throwable th) {
            zzaky.zzc("Could not get video controller.", th);
            return null;
        }
    }

    public final IObjectWrapper getView() throws RemoteException {
        MediationAdapter mediationAdapter = this.zzcjz;
        if (!(mediationAdapter instanceof MediationBannerAdapter)) {
            String valueOf = String.valueOf(mediationAdapter.getClass().getCanonicalName());
            zzaky.zzcz(valueOf.length() != 0 ? "MediationAdapter is not a MediationBannerAdapter: ".concat(valueOf) : new String("MediationAdapter is not a MediationBannerAdapter: "));
            throw new RemoteException();
        }
        try {
            return zzn.zzz(((MediationBannerAdapter) mediationAdapter).getBannerView());
        } catch (Throwable th) {
            zzaky.zzc("Could not get banner view from adapter.", th);
            throw new RemoteException();
        }
    }

    public final boolean isInitialized() throws RemoteException {
        MediationAdapter mediationAdapter = this.zzcjz;
        if (!(mediationAdapter instanceof MediationRewardedVideoAdAdapter)) {
            String valueOf = String.valueOf(mediationAdapter.getClass().getCanonicalName());
            zzaky.zzcz(valueOf.length() != 0 ? "MediationAdapter is not a MediationRewardedVideoAdAdapter: ".concat(valueOf) : new String("MediationAdapter is not a MediationRewardedVideoAdAdapter: "));
            throw new RemoteException();
        }
        zzaky.zzby("Check if adapter is initialized.");
        try {
            return ((MediationRewardedVideoAdAdapter) this.zzcjz).isInitialized();
        } catch (Throwable th) {
            zzaky.zzc("Could not check if adapter is initialized.", th);
            throw new RemoteException();
        }
    }

    public final void pause() throws RemoteException {
        try {
            this.zzcjz.onPause();
        } catch (Throwable th) {
            zzaky.zzc("Could not pause adapter.", th);
            throw new RemoteException();
        }
    }

    public final void resume() throws RemoteException {
        try {
            this.zzcjz.onResume();
        } catch (Throwable th) {
            zzaky.zzc("Could not resume adapter.", th);
            throw new RemoteException();
        }
    }

    public final void setImmersiveMode(boolean z) throws RemoteException {
        MediationAdapter mediationAdapter = this.zzcjz;
        if (!(mediationAdapter instanceof OnImmersiveModeUpdatedListener)) {
            String valueOf = String.valueOf(mediationAdapter.getClass().getCanonicalName());
            zzaky.zzcy(valueOf.length() != 0 ? "MediationAdapter is not an OnImmersiveModeUpdatedListener: ".concat(valueOf) : new String("MediationAdapter is not an OnImmersiveModeUpdatedListener: "));
            return;
        }
        try {
            ((OnImmersiveModeUpdatedListener) mediationAdapter).onImmersiveModeUpdated(z);
        } catch (Throwable th) {
            zzaky.zzc("Could not set immersive mode.", th);
        }
    }

    public final void showInterstitial() throws RemoteException {
        MediationAdapter mediationAdapter = this.zzcjz;
        if (!(mediationAdapter instanceof MediationInterstitialAdapter)) {
            String valueOf = String.valueOf(mediationAdapter.getClass().getCanonicalName());
            zzaky.zzcz(valueOf.length() != 0 ? "MediationAdapter is not a MediationInterstitialAdapter: ".concat(valueOf) : new String("MediationAdapter is not a MediationInterstitialAdapter: "));
            throw new RemoteException();
        }
        zzaky.zzby("Showing interstitial from adapter.");
        try {
            ((MediationInterstitialAdapter) this.zzcjz).showInterstitial();
        } catch (Throwable th) {
            zzaky.zzc("Could not show interstitial from adapter.", th);
            throw new RemoteException();
        }
    }

    public final void showVideo() throws RemoteException {
        MediationAdapter mediationAdapter = this.zzcjz;
        if (!(mediationAdapter instanceof MediationRewardedVideoAdAdapter)) {
            String valueOf = String.valueOf(mediationAdapter.getClass().getCanonicalName());
            zzaky.zzcz(valueOf.length() != 0 ? "MediationAdapter is not a MediationRewardedVideoAdAdapter: ".concat(valueOf) : new String("MediationAdapter is not a MediationRewardedVideoAdAdapter: "));
            throw new RemoteException();
        }
        zzaky.zzby("Show rewarded video ad from adapter.");
        try {
            ((MediationRewardedVideoAdAdapter) this.zzcjz).showVideo();
        } catch (Throwable th) {
            zzaky.zzc("Could not show rewarded video ad from adapter.", th);
            throw new RemoteException();
        }
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzafz zzafz, List<String> list) throws RemoteException {
        MediationAdapter mediationAdapter = this.zzcjz;
        if (!(mediationAdapter instanceof InitializableMediationRewardedVideoAdAdapter)) {
            String valueOf = String.valueOf(mediationAdapter.getClass().getCanonicalName());
            zzaky.zzcz(valueOf.length() != 0 ? "MediationAdapter is not an InitializableMediationRewardedVideoAdAdapter: ".concat(valueOf) : new String("MediationAdapter is not an InitializableMediationRewardedVideoAdAdapter: "));
            throw new RemoteException();
        }
        zzaky.zzby("Initialize rewarded video adapter.");
        try {
            InitializableMediationRewardedVideoAdAdapter initializableMediationRewardedVideoAdAdapter = (InitializableMediationRewardedVideoAdAdapter) this.zzcjz;
            ArrayList arrayList = new ArrayList();
            for (String zza : list) {
                arrayList.add(zza(zza, (zzkk) null, (String) null));
            }
            initializableMediationRewardedVideoAdAdapter.initialize((Context) zzn.zzy(iObjectWrapper), new zzagc(zzafz), arrayList);
        } catch (Throwable th) {
            zzaky.zzc("Could not initialize rewarded video adapter.", th);
            throw new RemoteException();
        }
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzkk zzkk, String str, zzafz zzafz, String str2) throws RemoteException {
        Bundle bundle;
        zzxb zzxb;
        zzkk zzkk2 = zzkk;
        MediationAdapter mediationAdapter = this.zzcjz;
        if (!(mediationAdapter instanceof MediationRewardedVideoAdAdapter)) {
            String valueOf = String.valueOf(mediationAdapter.getClass().getCanonicalName());
            zzaky.zzcz(valueOf.length() != 0 ? "MediationAdapter is not a MediationRewardedVideoAdAdapter: ".concat(valueOf) : new String("MediationAdapter is not a MediationRewardedVideoAdAdapter: "));
            throw new RemoteException();
        }
        zzaky.zzby("Initialize rewarded video adapter.");
        try {
            MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter = (MediationRewardedVideoAdAdapter) this.zzcjz;
            Bundle zza = zza(str2, zzkk2, (String) null);
            if (zzkk2 != null) {
                zzxb zzxb2 = new zzxb(zzkk2.zzbgv == -1 ? null : new Date(zzkk2.zzbgv), zzkk2.zzbgw, zzkk2.zzbgx != null ? new HashSet(zzkk2.zzbgx) : null, zzkk2.zzbhd, zzm(zzkk), zzkk2.zzbgz, zzkk2.zzbhk);
                bundle = zzkk2.zzbhf != null ? zzkk2.zzbhf.getBundle(mediationRewardedVideoAdAdapter.getClass().getName()) : null;
                zzxb = zzxb2;
            } else {
                zzxb = null;
                bundle = null;
            }
            mediationRewardedVideoAdAdapter.initialize((Context) zzn.zzy(iObjectWrapper), zzxb, str, new zzagc(zzafz), zza, bundle);
        } catch (Throwable th) {
            zzaky.zzc("Could not initialize rewarded video adapter.", th);
            throw new RemoteException();
        }
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzkk zzkk, String str, zzwl zzwl) throws RemoteException {
        zza(iObjectWrapper, zzkk, str, (String) null, zzwl);
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzkk zzkk, String str, String str2, zzwl zzwl) throws RemoteException {
        zzkk zzkk2 = zzkk;
        MediationAdapter mediationAdapter = this.zzcjz;
        if (!(mediationAdapter instanceof MediationInterstitialAdapter)) {
            String valueOf = String.valueOf(mediationAdapter.getClass().getCanonicalName());
            zzaky.zzcz(valueOf.length() != 0 ? "MediationAdapter is not a MediationInterstitialAdapter: ".concat(valueOf) : new String("MediationAdapter is not a MediationInterstitialAdapter: "));
            throw new RemoteException();
        }
        zzaky.zzby("Requesting interstitial ad from adapter.");
        try {
            MediationInterstitialAdapter mediationInterstitialAdapter = (MediationInterstitialAdapter) this.zzcjz;
            Bundle bundle = null;
            zzxb zzxb = new zzxb(zzkk2.zzbgv == -1 ? null : new Date(zzkk2.zzbgv), zzkk2.zzbgw, zzkk2.zzbgx != null ? new HashSet(zzkk2.zzbgx) : null, zzkk2.zzbhd, zzm(zzkk), zzkk2.zzbgz, zzkk2.zzbhk);
            if (zzkk2.zzbhf != null) {
                bundle = zzkk2.zzbhf.getBundle(mediationInterstitialAdapter.getClass().getName());
            }
            mediationInterstitialAdapter.requestInterstitialAd((Context) zzn.zzy(iObjectWrapper), new zzxd(zzwl), zza(str, zzkk2, str2), zzxb, bundle);
        } catch (Throwable th) {
            zzaky.zzc("Could not request interstitial ad from adapter.", th);
            throw new RemoteException();
        }
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzkk zzkk, String str, String str2, zzwl zzwl, zzqh zzqh, List<String> list) throws RemoteException {
        zzkk zzkk2 = zzkk;
        MediationAdapter mediationAdapter = this.zzcjz;
        if (!(mediationAdapter instanceof MediationNativeAdapter)) {
            String valueOf = String.valueOf(mediationAdapter.getClass().getCanonicalName());
            zzaky.zzcz(valueOf.length() != 0 ? "MediationAdapter is not a MediationNativeAdapter: ".concat(valueOf) : new String("MediationAdapter is not a MediationNativeAdapter: "));
            throw new RemoteException();
        }
        try {
            MediationNativeAdapter mediationNativeAdapter = (MediationNativeAdapter) mediationAdapter;
            Bundle bundle = null;
            zzxg zzxg = new zzxg(zzkk2.zzbgv == -1 ? null : new Date(zzkk2.zzbgv), zzkk2.zzbgw, zzkk2.zzbgx != null ? new HashSet(zzkk2.zzbgx) : null, zzkk2.zzbhd, zzm(zzkk), zzkk2.zzbgz, zzqh, list, zzkk2.zzbhk);
            if (zzkk2.zzbhf != null) {
                bundle = zzkk2.zzbhf.getBundle(mediationNativeAdapter.getClass().getName());
            }
            this.zzcka = new zzxd(zzwl);
            mediationNativeAdapter.requestNativeAd((Context) zzn.zzy(iObjectWrapper), this.zzcka, zza(str, zzkk2, str2), zzxg, bundle);
        } catch (Throwable th) {
            zzaky.zzc("Could not request native ad from adapter.", th);
            throw new RemoteException();
        }
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzko zzko, zzkk zzkk, String str, zzwl zzwl) throws RemoteException {
        zza(iObjectWrapper, zzko, zzkk, str, (String) null, zzwl);
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzko zzko, zzkk zzkk, String str, String str2, zzwl zzwl) throws RemoteException {
        zzko zzko2 = zzko;
        zzkk zzkk2 = zzkk;
        MediationAdapter mediationAdapter = this.zzcjz;
        if (!(mediationAdapter instanceof MediationBannerAdapter)) {
            String valueOf = String.valueOf(mediationAdapter.getClass().getCanonicalName());
            zzaky.zzcz(valueOf.length() != 0 ? "MediationAdapter is not a MediationBannerAdapter: ".concat(valueOf) : new String("MediationAdapter is not a MediationBannerAdapter: "));
            throw new RemoteException();
        }
        zzaky.zzby("Requesting banner ad from adapter.");
        try {
            MediationBannerAdapter mediationBannerAdapter = (MediationBannerAdapter) this.zzcjz;
            Bundle bundle = null;
            zzxb zzxb = new zzxb(zzkk2.zzbgv == -1 ? null : new Date(zzkk2.zzbgv), zzkk2.zzbgw, zzkk2.zzbgx != null ? new HashSet(zzkk2.zzbgx) : null, zzkk2.zzbhd, zzm(zzkk), zzkk2.zzbgz, zzkk2.zzbhk);
            if (zzkk2.zzbhf != null) {
                bundle = zzkk2.zzbhf.getBundle(mediationBannerAdapter.getClass().getName());
            }
            mediationBannerAdapter.requestBannerAd((Context) zzn.zzy(iObjectWrapper), new zzxd(zzwl), zza(str, zzkk2, str2), com.google.android.gms.ads.zzb.zza(zzko2.width, zzko2.height, zzko2.zzbia), zzxb, bundle);
        } catch (Throwable th) {
            zzaky.zzc("Could not request banner ad from adapter.", th);
            throw new RemoteException();
        }
    }

    public final void zza(zzkk zzkk, String str, String str2) throws RemoteException {
        MediationAdapter mediationAdapter = this.zzcjz;
        if (!(mediationAdapter instanceof MediationRewardedVideoAdAdapter)) {
            String valueOf = String.valueOf(mediationAdapter.getClass().getCanonicalName());
            zzaky.zzcz(valueOf.length() != 0 ? "MediationAdapter is not a MediationRewardedVideoAdAdapter: ".concat(valueOf) : new String("MediationAdapter is not a MediationRewardedVideoAdAdapter: "));
            throw new RemoteException();
        }
        zzaky.zzby("Requesting rewarded video ad from adapter.");
        try {
            MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter = (MediationRewardedVideoAdAdapter) this.zzcjz;
            Bundle bundle = null;
            zzxb zzxb = new zzxb(zzkk.zzbgv == -1 ? null : new Date(zzkk.zzbgv), zzkk.zzbgw, zzkk.zzbgx != null ? new HashSet(zzkk.zzbgx) : null, zzkk.zzbhd, zzm(zzkk), zzkk.zzbgz, zzkk.zzbhk);
            if (zzkk.zzbhf != null) {
                bundle = zzkk.zzbhf.getBundle(mediationRewardedVideoAdAdapter.getClass().getName());
            }
            mediationRewardedVideoAdAdapter.loadAd(zzxb, zza(str, zzkk, str2), bundle);
        } catch (Throwable th) {
            zzaky.zzc("Could not load rewarded video ad from adapter.", th);
            throw new RemoteException();
        }
    }

    public final void zzc(zzkk zzkk, String str) throws RemoteException {
        zza(zzkk, str, (String) null);
    }

    public final void zzg(IObjectWrapper iObjectWrapper) throws RemoteException {
        try {
            ((OnContextChangedListener) this.zzcjz).onContextChanged((Context) zzn.zzy(iObjectWrapper));
        } catch (Throwable th) {
            zzaky.zza("Could not inform adapter of changed context", th);
        }
    }

    public final zzwr zzmp() {
        NativeAdMapper zzmy = this.zzcka.zzmy();
        if (zzmy instanceof NativeAppInstallAdMapper) {
            return new zzxe((NativeAppInstallAdMapper) zzmy);
        }
        return null;
    }

    public final zzwu zzmq() {
        NativeAdMapper zzmy = this.zzcka.zzmy();
        if (zzmy instanceof NativeContentAdMapper) {
            return new zzxf((NativeContentAdMapper) zzmy);
        }
        return null;
    }

    public final Bundle zzmr() {
        MediationAdapter mediationAdapter = this.zzcjz;
        if (mediationAdapter instanceof zzaqj) {
            return ((zzaqj) mediationAdapter).zzmr();
        }
        String valueOf = String.valueOf(mediationAdapter.getClass().getCanonicalName());
        zzaky.zzcz(valueOf.length() != 0 ? "MediationAdapter is not a v2 MediationBannerAdapter: ".concat(valueOf) : new String("MediationAdapter is not a v2 MediationBannerAdapter: "));
        return new Bundle();
    }

    public final Bundle zzms() {
        return new Bundle();
    }

    public final boolean zzmt() {
        return this.zzcjz instanceof InitializableMediationRewardedVideoAdAdapter;
    }

    public final zzro zzmu() {
        NativeCustomTemplateAd zzna = this.zzcka.zzna();
        if (zzna instanceof zzrr) {
            return ((zzrr) zzna).zzkx();
        }
        return null;
    }

    public final zzwx zzmv() {
        zza zzmz = this.zzcka.zzmz();
        if (zzmz != null) {
            return new zzxw(zzmz);
        }
        return null;
    }
}
