package com.google.android.gms.internal;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.ads.mediation.MediationAdapter;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.NetworkExtras;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

@zzabh
public final class zzxh<NETWORK_EXTRAS extends NetworkExtras, SERVER_PARAMETERS extends MediationServerParameters> extends zzwj {
    private final MediationAdapter<NETWORK_EXTRAS, SERVER_PARAMETERS> zzcki;
    private final NETWORK_EXTRAS zzckj;

    public zzxh(MediationAdapter<NETWORK_EXTRAS, SERVER_PARAMETERS> mediationAdapter, NETWORK_EXTRAS network_extras) {
        this.zzcki = mediationAdapter;
        this.zzckj = network_extras;
    }

    private final SERVER_PARAMETERS zza(String str, int i, String str2) throws RemoteException {
        HashMap hashMap;
        if (str != null) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                hashMap = new HashMap(jSONObject.length());
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    hashMap.put(next, jSONObject.getString(next));
                }
            } catch (Throwable th) {
                zzaky.zzc("Could not get MediationServerParameters.", th);
                throw new RemoteException();
            }
        } else {
            hashMap = new HashMap(0);
        }
        Class<SERVER_PARAMETERS> serverParametersType = this.zzcki.getServerParametersType();
        if (serverParametersType == null) {
            return null;
        }
        try {
            SERVER_PARAMETERS server_parameters = serverParametersType.newInstance();
            server_parameters.load(hashMap);
            return server_parameters;
        } catch (IllegalAccessException | InstantiationException | MediationServerParameters.MappingException e) {
            zzaky.zzc("Could not get MediationServerParameters.", e);
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
            this.zzcki.destroy();
        } catch (Throwable th) {
            zzaky.zzc("Could not destroy adapter.", th);
            throw new RemoteException();
        }
    }

    public final Bundle getInterstitialAdapterInfo() {
        return new Bundle();
    }

    public final zzmm getVideoController() {
        return null;
    }

    public final IObjectWrapper getView() throws RemoteException {
        MediationAdapter<NETWORK_EXTRAS, SERVER_PARAMETERS> mediationAdapter = this.zzcki;
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

    public final boolean isInitialized() {
        return true;
    }

    public final void pause() throws RemoteException {
        throw new RemoteException();
    }

    public final void resume() throws RemoteException {
        throw new RemoteException();
    }

    public final void setImmersiveMode(boolean z) {
    }

    public final void showInterstitial() throws RemoteException {
        MediationAdapter<NETWORK_EXTRAS, SERVER_PARAMETERS> mediationAdapter = this.zzcki;
        if (!(mediationAdapter instanceof MediationInterstitialAdapter)) {
            String valueOf = String.valueOf(mediationAdapter.getClass().getCanonicalName());
            zzaky.zzcz(valueOf.length() != 0 ? "MediationAdapter is not a MediationInterstitialAdapter: ".concat(valueOf) : new String("MediationAdapter is not a MediationInterstitialAdapter: "));
            throw new RemoteException();
        }
        zzaky.zzby("Showing interstitial from adapter.");
        try {
            ((MediationInterstitialAdapter) this.zzcki).showInterstitial();
        } catch (Throwable th) {
            zzaky.zzc("Could not show interstitial from adapter.", th);
            throw new RemoteException();
        }
    }

    public final void showVideo() {
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzafz zzafz, List<String> list) {
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzkk zzkk, String str, zzafz zzafz, String str2) throws RemoteException {
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzkk zzkk, String str, zzwl zzwl) throws RemoteException {
        zza(iObjectWrapper, zzkk, str, (String) null, zzwl);
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzkk zzkk, String str, String str2, zzwl zzwl) throws RemoteException {
        MediationAdapter<NETWORK_EXTRAS, SERVER_PARAMETERS> mediationAdapter = this.zzcki;
        if (!(mediationAdapter instanceof MediationInterstitialAdapter)) {
            String valueOf = String.valueOf(mediationAdapter.getClass().getCanonicalName());
            zzaky.zzcz(valueOf.length() != 0 ? "MediationAdapter is not a MediationInterstitialAdapter: ".concat(valueOf) : new String("MediationAdapter is not a MediationInterstitialAdapter: "));
            throw new RemoteException();
        }
        zzaky.zzby("Requesting interstitial ad from adapter.");
        try {
            ((MediationInterstitialAdapter) this.zzcki).requestInterstitialAd(new zzxi(zzwl), (Activity) zzn.zzy(iObjectWrapper), zza(str, zzkk.zzbgz, str2), zzxu.zza(zzkk, zzm(zzkk)), this.zzckj);
        } catch (Throwable th) {
            zzaky.zzc("Could not request interstitial ad from adapter.", th);
            throw new RemoteException();
        }
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzkk zzkk, String str, String str2, zzwl zzwl, zzqh zzqh, List<String> list) {
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzko zzko, zzkk zzkk, String str, zzwl zzwl) throws RemoteException {
        zza(iObjectWrapper, zzko, zzkk, str, (String) null, zzwl);
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzko zzko, zzkk zzkk, String str, String str2, zzwl zzwl) throws RemoteException {
        MediationAdapter<NETWORK_EXTRAS, SERVER_PARAMETERS> mediationAdapter = this.zzcki;
        if (!(mediationAdapter instanceof MediationBannerAdapter)) {
            String valueOf = String.valueOf(mediationAdapter.getClass().getCanonicalName());
            zzaky.zzcz(valueOf.length() != 0 ? "MediationAdapter is not a MediationBannerAdapter: ".concat(valueOf) : new String("MediationAdapter is not a MediationBannerAdapter: "));
            throw new RemoteException();
        }
        zzaky.zzby("Requesting banner ad from adapter.");
        try {
            ((MediationBannerAdapter) this.zzcki).requestBannerAd(new zzxi(zzwl), (Activity) zzn.zzy(iObjectWrapper), zza(str, zzkk.zzbgz, str2), zzxu.zzb(zzko), zzxu.zza(zzkk, zzm(zzkk)), this.zzckj);
        } catch (Throwable th) {
            zzaky.zzc("Could not request banner ad from adapter.", th);
            throw new RemoteException();
        }
    }

    public final void zza(zzkk zzkk, String str, String str2) {
    }

    public final void zzc(zzkk zzkk, String str) {
    }

    public final void zzg(IObjectWrapper iObjectWrapper) throws RemoteException {
    }

    public final zzwr zzmp() {
        return null;
    }

    public final zzwu zzmq() {
        return null;
    }

    public final Bundle zzmr() {
        return new Bundle();
    }

    public final Bundle zzms() {
        return new Bundle();
    }

    public final boolean zzmt() {
        return false;
    }

    public final zzro zzmu() {
        return null;
    }

    public final zzwx zzmv() {
        return null;
    }
}
