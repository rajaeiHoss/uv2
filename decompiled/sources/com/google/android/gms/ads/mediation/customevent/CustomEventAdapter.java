package com.google.android.gms.ads.mediation.customevent;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationBannerListener;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.mediation.MediationNativeListener;
import com.google.android.gms.ads.mediation.NativeAdMapper;
import com.google.android.gms.ads.mediation.NativeMediationAdRequest;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import com.google.android.gms.internal.zzaky;

public final class CustomEventAdapter implements MediationBannerAdapter, MediationInterstitialAdapter, MediationNativeAdapter {
    private CustomEventBanner zzdtn;
    private CustomEventInterstitial zzdto;
    private CustomEventNative zzdtp;
    private View zzhm;

    static final class zza implements CustomEventBannerListener {
        private final CustomEventAdapter zzdtq;
        private final MediationBannerListener zzhe;

        public zza(CustomEventAdapter customEventAdapter, MediationBannerListener mediationBannerListener) {
            this.zzdtq = customEventAdapter;
            this.zzhe = mediationBannerListener;
        }

        public final void onAdClicked() {
            zzaky.zzby("Custom event adapter called onAdClicked.");
            this.zzhe.onAdClicked(this.zzdtq);
        }

        public final void onAdClosed() {
            zzaky.zzby("Custom event adapter called onAdClosed.");
            this.zzhe.onAdClosed(this.zzdtq);
        }

        public final void onAdFailedToLoad(int i) {
            zzaky.zzby("Custom event adapter called onAdFailedToLoad.");
            this.zzhe.onAdFailedToLoad(this.zzdtq, i);
        }

        public final void onAdLeftApplication() {
            zzaky.zzby("Custom event adapter called onAdLeftApplication.");
            this.zzhe.onAdLeftApplication(this.zzdtq);
        }

        public final void onAdLoaded(View view) {
            zzaky.zzby("Custom event adapter called onAdLoaded.");
            this.zzdtq.zza(view);
            this.zzhe.onAdLoaded(this.zzdtq);
        }

        public final void onAdOpened() {
            zzaky.zzby("Custom event adapter called onAdOpened.");
            this.zzhe.onAdOpened(this.zzdtq);
        }
    }

    class zzb implements CustomEventInterstitialListener {
        private final CustomEventAdapter zzdtq;
        private final MediationInterstitialListener zzhf;

        public zzb(CustomEventAdapter customEventAdapter, MediationInterstitialListener mediationInterstitialListener) {
            this.zzdtq = customEventAdapter;
            this.zzhf = mediationInterstitialListener;
        }

        public final void onAdClicked() {
            zzaky.zzby("Custom event adapter called onAdClicked.");
            this.zzhf.onAdClicked(this.zzdtq);
        }

        public final void onAdClosed() {
            zzaky.zzby("Custom event adapter called onAdClosed.");
            this.zzhf.onAdClosed(this.zzdtq);
        }

        public final void onAdFailedToLoad(int i) {
            zzaky.zzby("Custom event adapter called onFailedToReceiveAd.");
            this.zzhf.onAdFailedToLoad(this.zzdtq, i);
        }

        public final void onAdLeftApplication() {
            zzaky.zzby("Custom event adapter called onAdLeftApplication.");
            this.zzhf.onAdLeftApplication(this.zzdtq);
        }

        public final void onAdLoaded() {
            zzaky.zzby("Custom event adapter called onReceivedAd.");
            this.zzhf.onAdLoaded(CustomEventAdapter.this);
        }

        public final void onAdOpened() {
            zzaky.zzby("Custom event adapter called onAdOpened.");
            this.zzhf.onAdOpened(this.zzdtq);
        }
    }

    static class zzc implements CustomEventNativeListener {
        private final CustomEventAdapter zzdtq;
        private final MediationNativeListener zzhg;

        public zzc(CustomEventAdapter customEventAdapter, MediationNativeListener mediationNativeListener) {
            this.zzdtq = customEventAdapter;
            this.zzhg = mediationNativeListener;
        }

        public final void onAdClicked() {
            zzaky.zzby("Custom event adapter called onAdClicked.");
            this.zzhg.onAdClicked(this.zzdtq);
        }

        public final void onAdClosed() {
            zzaky.zzby("Custom event adapter called onAdClosed.");
            this.zzhg.onAdClosed(this.zzdtq);
        }

        public final void onAdFailedToLoad(int i) {
            zzaky.zzby("Custom event adapter called onAdFailedToLoad.");
            this.zzhg.onAdFailedToLoad(this.zzdtq, i);
        }

        public final void onAdImpression() {
            zzaky.zzby("Custom event adapter called onAdImpression.");
            this.zzhg.onAdImpression(this.zzdtq);
        }

        public final void onAdLeftApplication() {
            zzaky.zzby("Custom event adapter called onAdLeftApplication.");
            this.zzhg.onAdLeftApplication(this.zzdtq);
        }

        public final void onAdLoaded(NativeAdMapper nativeAdMapper) {
            zzaky.zzby("Custom event adapter called onAdLoaded.");
            this.zzhg.onAdLoaded(this.zzdtq, nativeAdMapper);
        }

        public final void onAdOpened() {
            zzaky.zzby("Custom event adapter called onAdOpened.");
            this.zzhg.onAdOpened(this.zzdtq);
        }
    }

    /* access modifiers changed from: private */
    public final void zza(View view) {
        this.zzhm = view;
    }

    private static <T> T zzi(String str) {
        try {
            @SuppressWarnings("unchecked")
            T instance = (T) Class.forName(str).newInstance();
            return instance;
        } catch (Throwable th) {
            String message = th.getMessage();
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 46 + String.valueOf(message).length());
            sb.append("Could not instantiate custom event adapter: ");
            sb.append(str);
            sb.append(". ");
            sb.append(message);
            zzaky.zzcz(sb.toString());
            return null;
        }
    }

    public final View getBannerView() {
        return this.zzhm;
    }

    public final void onDestroy() {
        CustomEventBanner customEventBanner = this.zzdtn;
        if (customEventBanner != null) {
            customEventBanner.onDestroy();
        }
        CustomEventInterstitial customEventInterstitial = this.zzdto;
        if (customEventInterstitial != null) {
            customEventInterstitial.onDestroy();
        }
        CustomEventNative customEventNative = this.zzdtp;
        if (customEventNative != null) {
            customEventNative.onDestroy();
        }
    }

    public final void onPause() {
        CustomEventBanner customEventBanner = this.zzdtn;
        if (customEventBanner != null) {
            customEventBanner.onPause();
        }
        CustomEventInterstitial customEventInterstitial = this.zzdto;
        if (customEventInterstitial != null) {
            customEventInterstitial.onPause();
        }
        CustomEventNative customEventNative = this.zzdtp;
        if (customEventNative != null) {
            customEventNative.onPause();
        }
    }

    public final void onResume() {
        CustomEventBanner customEventBanner = this.zzdtn;
        if (customEventBanner != null) {
            customEventBanner.onResume();
        }
        CustomEventInterstitial customEventInterstitial = this.zzdto;
        if (customEventInterstitial != null) {
            customEventInterstitial.onResume();
        }
        CustomEventNative customEventNative = this.zzdtp;
        if (customEventNative != null) {
            customEventNative.onResume();
        }
    }

    public final void requestBannerAd(Context context, MediationBannerListener mediationBannerListener, Bundle bundle, AdSize adSize, MediationAdRequest mediationAdRequest, Bundle bundle2) {
        CustomEventBanner customEventBanner = (CustomEventBanner) zzi(bundle.getString("class_name"));
        this.zzdtn = customEventBanner;
        if (customEventBanner == null) {
            mediationBannerListener.onAdFailedToLoad(this, 0);
            return;
        }
        this.zzdtn.requestBannerAd(context, new zza(this, mediationBannerListener), bundle.getString(MediationRewardedVideoAdAdapter.CUSTOM_EVENT_SERVER_PARAMETER_FIELD), adSize, mediationAdRequest, bundle2 == null ? null : bundle2.getBundle(bundle.getString("class_name")));
    }

    public final void requestInterstitialAd(Context context, MediationInterstitialListener mediationInterstitialListener, Bundle bundle, MediationAdRequest mediationAdRequest, Bundle bundle2) {
        CustomEventInterstitial customEventInterstitial = (CustomEventInterstitial) zzi(bundle.getString("class_name"));
        this.zzdto = customEventInterstitial;
        if (customEventInterstitial == null) {
            mediationInterstitialListener.onAdFailedToLoad(this, 0);
            return;
        }
        this.zzdto.requestInterstitialAd(context, new zzb(this, mediationInterstitialListener), bundle.getString(MediationRewardedVideoAdAdapter.CUSTOM_EVENT_SERVER_PARAMETER_FIELD), mediationAdRequest, bundle2 == null ? null : bundle2.getBundle(bundle.getString("class_name")));
    }

    public final void requestNativeAd(Context context, MediationNativeListener mediationNativeListener, Bundle bundle, NativeMediationAdRequest nativeMediationAdRequest, Bundle bundle2) {
        CustomEventNative customEventNative = (CustomEventNative) zzi(bundle.getString("class_name"));
        this.zzdtp = customEventNative;
        if (customEventNative == null) {
            mediationNativeListener.onAdFailedToLoad(this, 0);
            return;
        }
        this.zzdtp.requestNativeAd(context, new zzc(this, mediationNativeListener), bundle.getString(MediationRewardedVideoAdAdapter.CUSTOM_EVENT_SERVER_PARAMETER_FIELD), nativeMediationAdRequest, bundle2 == null ? null : bundle2.getBundle(bundle.getString("class_name")));
    }

    public final void showInterstitial() {
        this.zzdto.showInterstitial();
    }
}
