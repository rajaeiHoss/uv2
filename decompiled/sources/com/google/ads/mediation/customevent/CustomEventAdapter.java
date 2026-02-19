package com.google.ads.mediation.customevent;

import android.app.Activity;
import android.view.View;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.mediation.MediationAdRequest;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationBannerListener;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.ads.mediation.customevent.CustomEventExtras;
import com.google.android.gms.internal.zzaky;

public final class CustomEventAdapter implements MediationBannerAdapter<CustomEventExtras, CustomEventServerParameters>, MediationInterstitialAdapter<CustomEventExtras, CustomEventServerParameters> {
    private View zzhm;
    private CustomEventBanner zzhn;
    private CustomEventInterstitial zzho;

    static final class zza implements CustomEventBannerListener {
        private final CustomEventAdapter zzhp;
        private final MediationBannerListener zzhq;

        public zza(CustomEventAdapter customEventAdapter, MediationBannerListener mediationBannerListener) {
            this.zzhp = customEventAdapter;
            this.zzhq = mediationBannerListener;
        }

        public final void onClick() {
            zzaky.zzby("Custom event adapter called onFailedToReceiveAd.");
            this.zzhq.onClick(this.zzhp);
        }

        public final void onDismissScreen() {
            zzaky.zzby("Custom event adapter called onFailedToReceiveAd.");
            this.zzhq.onDismissScreen(this.zzhp);
        }

        public final void onFailedToReceiveAd() {
            zzaky.zzby("Custom event adapter called onFailedToReceiveAd.");
            this.zzhq.onFailedToReceiveAd(this.zzhp, AdRequest.ErrorCode.NO_FILL);
        }

        public final void onLeaveApplication() {
            zzaky.zzby("Custom event adapter called onFailedToReceiveAd.");
            this.zzhq.onLeaveApplication(this.zzhp);
        }

        public final void onPresentScreen() {
            zzaky.zzby("Custom event adapter called onFailedToReceiveAd.");
            this.zzhq.onPresentScreen(this.zzhp);
        }

        public final void onReceivedAd(View view) {
            zzaky.zzby("Custom event adapter called onReceivedAd.");
            this.zzhp.zza(view);
            this.zzhq.onReceivedAd(this.zzhp);
        }
    }

    class zzb implements CustomEventInterstitialListener {
        private final CustomEventAdapter zzhp;
        private final MediationInterstitialListener zzhr;

        public zzb(CustomEventAdapter customEventAdapter, MediationInterstitialListener mediationInterstitialListener) {
            this.zzhp = customEventAdapter;
            this.zzhr = mediationInterstitialListener;
        }

        public final void onDismissScreen() {
            zzaky.zzby("Custom event adapter called onDismissScreen.");
            this.zzhr.onDismissScreen(this.zzhp);
        }

        public final void onFailedToReceiveAd() {
            zzaky.zzby("Custom event adapter called onFailedToReceiveAd.");
            this.zzhr.onFailedToReceiveAd(this.zzhp, AdRequest.ErrorCode.NO_FILL);
        }

        public final void onLeaveApplication() {
            zzaky.zzby("Custom event adapter called onLeaveApplication.");
            this.zzhr.onLeaveApplication(this.zzhp);
        }

        public final void onPresentScreen() {
            zzaky.zzby("Custom event adapter called onPresentScreen.");
            this.zzhr.onPresentScreen(this.zzhp);
        }

        public final void onReceivedAd() {
            zzaky.zzby("Custom event adapter called onReceivedAd.");
            this.zzhr.onReceivedAd(CustomEventAdapter.this);
        }
    }

    /* access modifiers changed from: private */
    public final void zza(View view) {
        this.zzhm = view;
    }

    private static <T> T zzi(String str) {
        try {
            return (T) Class.forName(str).newInstance();
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

    public final void destroy() {
        CustomEventBanner customEventBanner = this.zzhn;
        if (customEventBanner != null) {
            customEventBanner.destroy();
        }
        CustomEventInterstitial customEventInterstitial = this.zzho;
        if (customEventInterstitial != null) {
            customEventInterstitial.destroy();
        }
    }

    public final Class<CustomEventExtras> getAdditionalParametersType() {
        return CustomEventExtras.class;
    }

    public final View getBannerView() {
        return this.zzhm;
    }

    public final Class<CustomEventServerParameters> getServerParametersType() {
        return CustomEventServerParameters.class;
    }

    public final void requestBannerAd(MediationBannerListener mediationBannerListener, Activity activity, CustomEventServerParameters customEventServerParameters, AdSize adSize, MediationAdRequest mediationAdRequest, CustomEventExtras customEventExtras) {
        CustomEventBanner customEventBanner = (CustomEventBanner) zzi(customEventServerParameters.className);
        this.zzhn = customEventBanner;
        if (customEventBanner == null) {
            mediationBannerListener.onFailedToReceiveAd(this, AdRequest.ErrorCode.INTERNAL_ERROR);
            return;
        }
        this.zzhn.requestBannerAd(new zza(this, mediationBannerListener), activity, customEventServerParameters.label, customEventServerParameters.parameter, adSize, mediationAdRequest, customEventExtras == null ? null : customEventExtras.getExtra(customEventServerParameters.label));
    }

    public final void requestInterstitialAd(MediationInterstitialListener mediationInterstitialListener, Activity activity, CustomEventServerParameters customEventServerParameters, MediationAdRequest mediationAdRequest, CustomEventExtras customEventExtras) {
        CustomEventInterstitial customEventInterstitial = (CustomEventInterstitial) zzi(customEventServerParameters.className);
        this.zzho = customEventInterstitial;
        if (customEventInterstitial == null) {
            mediationInterstitialListener.onFailedToReceiveAd(this, AdRequest.ErrorCode.INTERNAL_ERROR);
            return;
        }
        this.zzho.requestInterstitialAd(new zzb(this, mediationInterstitialListener), activity, customEventServerParameters.label, customEventServerParameters.parameter, mediationAdRequest, customEventExtras == null ? null : customEventExtras.getExtra(customEventServerParameters.label));
    }

    public final void showInterstitial() {
        this.zzho.showInterstitial();
    }
}
