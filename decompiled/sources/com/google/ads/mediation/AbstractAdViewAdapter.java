package com.google.ads.mediation;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.NativeAdView;
import com.google.android.gms.ads.formats.NativeAdViewHolder;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationBannerListener;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.mediation.MediationNativeListener;
import com.google.android.gms.ads.mediation.NativeAppInstallAdMapper;
import com.google.android.gms.ads.mediation.NativeContentAdMapper;
import com.google.android.gms.ads.mediation.NativeMediationAdRequest;
import com.google.android.gms.ads.mediation.OnImmersiveModeUpdatedListener;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdListener;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzako;
import com.google.android.gms.internal.zzaky;
import com.google.android.gms.internal.zzaqk;
import com.google.android.gms.internal.zzkf;
import com.google.android.gms.internal.zzlc;
import com.google.android.gms.internal.zzmm;
import java.util.Date;
import java.util.Map;
import java.util.Set;

@zzabh
public abstract class AbstractAdViewAdapter implements MediationBannerAdapter, MediationNativeAdapter, OnImmersiveModeUpdatedListener, com.google.android.gms.ads.mediation.zzb, MediationRewardedVideoAdAdapter, zzaqk {
    public static final String AD_UNIT_ID_PARAMETER = "pubid";
    private AdView zzgs;
    private InterstitialAd zzgt;
    private AdLoader zzgu;
    private Context zzgv;
    /* access modifiers changed from: private */
    public InterstitialAd zzgw;
    /* access modifiers changed from: private */
    public MediationRewardedVideoAdListener zzgx;
    private RewardedVideoAdListener zzgy = new RewardedVideoAdListener() {
        public void onRewardedVideoAdLoaded() {
            if (AbstractAdViewAdapter.this.zzgx != null) {
                AbstractAdViewAdapter.this.zzgx.onAdLoaded(AbstractAdViewAdapter.this);
            }
        }

        public void onRewardedVideoAdOpened() {
            if (AbstractAdViewAdapter.this.zzgx != null) {
                AbstractAdViewAdapter.this.zzgx.onAdOpened(AbstractAdViewAdapter.this);
            }
        }

        public void onRewardedVideoStarted() {
            if (AbstractAdViewAdapter.this.zzgx != null) {
                AbstractAdViewAdapter.this.zzgx.onVideoStarted(AbstractAdViewAdapter.this);
            }
        }

        public void onRewardedVideoAdClosed() {
            if (AbstractAdViewAdapter.this.zzgx != null) {
                AbstractAdViewAdapter.this.zzgx.onAdClosed(AbstractAdViewAdapter.this);
            }
        }

        public void onRewarded(RewardItem rewardItem) {
            if (AbstractAdViewAdapter.this.zzgx != null) {
                AbstractAdViewAdapter.this.zzgx.onRewarded(AbstractAdViewAdapter.this, rewardItem);
            }
        }

        public void onRewardedVideoAdLeftApplication() {
            if (AbstractAdViewAdapter.this.zzgx != null) {
                AbstractAdViewAdapter.this.zzgx.onAdLeftApplication(AbstractAdViewAdapter.this);
            }
        }

        public void onRewardedVideoAdFailedToLoad(int i) {
            if (AbstractAdViewAdapter.this.zzgx != null) {
                AbstractAdViewAdapter.this.zzgx.onAdFailedToLoad(AbstractAdViewAdapter.this, i);
            }
        }

        public void onRewardedVideoCompleted() {
            if (AbstractAdViewAdapter.this.zzgx != null) {
                AbstractAdViewAdapter.this.zzgx.onVideoCompleted(AbstractAdViewAdapter.this);
            }
        }
    };

    static class zza extends NativeAppInstallAdMapper {
        private final NativeAppInstallAd zzha;

        public zza(NativeAppInstallAd nativeAppInstallAd) {
            this.zzha = nativeAppInstallAd;
            setHeadline(nativeAppInstallAd.getHeadline().toString());
            setImages(nativeAppInstallAd.getImages());
            setBody(nativeAppInstallAd.getBody().toString());
            setIcon(nativeAppInstallAd.getIcon());
            setCallToAction(nativeAppInstallAd.getCallToAction().toString());
            if (nativeAppInstallAd.getStarRating() != null) {
                setStarRating(nativeAppInstallAd.getStarRating().doubleValue());
            }
            if (nativeAppInstallAd.getStore() != null) {
                setStore(nativeAppInstallAd.getStore().toString());
            }
            if (nativeAppInstallAd.getPrice() != null) {
                setPrice(nativeAppInstallAd.getPrice().toString());
            }
            setOverrideImpressionRecording(true);
            setOverrideClickHandling(true);
            zza(nativeAppInstallAd.getVideoController());
        }

        public final void trackView(View view) {
            if (view instanceof NativeAdView) {
                ((NativeAdView) view).setNativeAd(this.zzha);
            }
            NativeAdViewHolder nativeAdViewHolder = NativeAdViewHolder.zzamn.get(view);
            if (nativeAdViewHolder != null) {
                nativeAdViewHolder.setNativeAd(this.zzha);
            }
        }
    }

    static class zzb extends NativeContentAdMapper {
        private final NativeContentAd zzhb;

        public zzb(NativeContentAd nativeContentAd) {
            this.zzhb = nativeContentAd;
            setHeadline(nativeContentAd.getHeadline().toString());
            setImages(nativeContentAd.getImages());
            setBody(nativeContentAd.getBody().toString());
            if (nativeContentAd.getLogo() != null) {
                setLogo(nativeContentAd.getLogo());
            }
            setCallToAction(nativeContentAd.getCallToAction().toString());
            setAdvertiser(nativeContentAd.getAdvertiser().toString());
            setOverrideImpressionRecording(true);
            setOverrideClickHandling(true);
            zza(nativeContentAd.getVideoController());
        }

        public final void trackView(View view) {
            if (view instanceof NativeAdView) {
                ((NativeAdView) view).setNativeAd(this.zzhb);
            }
            NativeAdViewHolder nativeAdViewHolder = NativeAdViewHolder.zzamn.get(view);
            if (nativeAdViewHolder != null) {
                nativeAdViewHolder.setNativeAd(this.zzhb);
            }
        }
    }

    static class zzc extends com.google.android.gms.ads.mediation.zza {
        private final UnifiedNativeAd zzhc;

        public zzc(UnifiedNativeAd unifiedNativeAd) {
            this.zzhc = unifiedNativeAd;
            setHeadline(unifiedNativeAd.getHeadline());
            setImages(unifiedNativeAd.getImages());
            setBody(unifiedNativeAd.getBody());
            setIcon(unifiedNativeAd.getIcon());
            setCallToAction(unifiedNativeAd.getCallToAction());
            setAdvertiser(unifiedNativeAd.getAdvertiser());
            zza(unifiedNativeAd.getStarRating());
            setStore(unifiedNativeAd.getStore());
            setPrice(unifiedNativeAd.getPrice());
            zzk(unifiedNativeAd.zzbl());
            setOverrideImpressionRecording(true);
            setOverrideClickHandling(true);
            zza(unifiedNativeAd.getVideoController());
        }

        public final void trackViews(View view, Map<String, View> map, Map<String, View> map2) {
            if (view instanceof com.google.android.gms.ads.formats.zzd) {
                com.google.android.gms.ads.formats.zzd.zzb(this.zzhc);
            }
        }
    }

    static final class zzd extends AdListener implements AppEventListener, zzkf {
        private AbstractAdViewAdapter zzhd;
        private MediationBannerListener zzhe;

        public zzd(AbstractAdViewAdapter abstractAdViewAdapter, MediationBannerListener mediationBannerListener) {
            this.zzhd = abstractAdViewAdapter;
            this.zzhe = mediationBannerListener;
        }

        public final void onAdClicked() {
            this.zzhe.onAdClicked(this.zzhd);
        }

        public final void onAdClosed() {
            this.zzhe.onAdClosed(this.zzhd);
        }

        public final void onAdFailedToLoad(int i) {
            this.zzhe.onAdFailedToLoad(this.zzhd, i);
        }

        public final void onAdLeftApplication() {
            this.zzhe.onAdLeftApplication(this.zzhd);
        }

        public final void onAdLoaded() {
            this.zzhe.onAdLoaded(this.zzhd);
        }

        public final void onAdOpened() {
            this.zzhe.onAdOpened(this.zzhd);
        }

        public final void onAppEvent(String str, String str2) {
            this.zzhe.zza(this.zzhd, str, str2);
        }
    }

    static final class zze extends AdListener implements zzkf {
        private AbstractAdViewAdapter zzhd;
        private MediationInterstitialListener zzhf;

        public zze(AbstractAdViewAdapter abstractAdViewAdapter, MediationInterstitialListener mediationInterstitialListener) {
            this.zzhd = abstractAdViewAdapter;
            this.zzhf = mediationInterstitialListener;
        }

        public final void onAdClicked() {
            this.zzhf.onAdClicked(this.zzhd);
        }

        public final void onAdClosed() {
            this.zzhf.onAdClosed(this.zzhd);
        }

        public final void onAdFailedToLoad(int i) {
            this.zzhf.onAdFailedToLoad(this.zzhd, i);
        }

        public final void onAdLeftApplication() {
            this.zzhf.onAdLeftApplication(this.zzhd);
        }

        public final void onAdLoaded() {
            this.zzhf.onAdLoaded(this.zzhd);
        }

        public final void onAdOpened() {
            this.zzhf.onAdOpened(this.zzhd);
        }
    }

    static final class zzf extends AdListener implements NativeAppInstallAd.OnAppInstallAdLoadedListener, NativeContentAd.OnContentAdLoadedListener, NativeCustomTemplateAd.OnCustomClickListener, NativeCustomTemplateAd.OnCustomTemplateAdLoadedListener, UnifiedNativeAd.zza {
        private AbstractAdViewAdapter zzhd;
        private MediationNativeListener zzhg;

        public zzf(AbstractAdViewAdapter abstractAdViewAdapter, MediationNativeListener mediationNativeListener) {
            this.zzhd = abstractAdViewAdapter;
            this.zzhg = mediationNativeListener;
        }

        public final void onAdClicked() {
            this.zzhg.onAdClicked(this.zzhd);
        }

        public final void onAdClosed() {
            this.zzhg.onAdClosed(this.zzhd);
        }

        public final void onAdFailedToLoad(int i) {
            this.zzhg.onAdFailedToLoad(this.zzhd, i);
        }

        public final void onAdImpression() {
            this.zzhg.onAdImpression(this.zzhd);
        }

        public final void onAdLeftApplication() {
            this.zzhg.onAdLeftApplication(this.zzhd);
        }

        public final void onAdLoaded() {
        }

        public final void onAdOpened() {
            this.zzhg.onAdOpened(this.zzhd);
        }

        public final void onAppInstallAdLoaded(NativeAppInstallAd nativeAppInstallAd) {
            this.zzhg.onAdLoaded(this.zzhd, new zza(nativeAppInstallAd));
        }

        public final void onContentAdLoaded(NativeContentAd nativeContentAd) {
            this.zzhg.onAdLoaded(this.zzhd, new zzb(nativeContentAd));
        }

        public final void onCustomClick(NativeCustomTemplateAd nativeCustomTemplateAd, String str) {
            this.zzhg.zza(this.zzhd, nativeCustomTemplateAd, str);
        }

        public final void onCustomTemplateAdLoaded(NativeCustomTemplateAd nativeCustomTemplateAd) {
            this.zzhg.zza((MediationNativeAdapter) this.zzhd, nativeCustomTemplateAd);
        }

        public final void zza(UnifiedNativeAd unifiedNativeAd) {
            this.zzhg.zza((MediationNativeAdapter) this.zzhd, (com.google.android.gms.ads.mediation.zza) new zzc(unifiedNativeAd));
        }
    }

    private final AdRequest zza(Context context, MediationAdRequest mediationAdRequest, Bundle bundle, Bundle bundle2) {
        AdRequest.Builder builder = new AdRequest.Builder();
        Date birthday = mediationAdRequest.getBirthday();
        if (birthday != null) {
            builder.setBirthday(birthday);
        }
        int gender = mediationAdRequest.getGender();
        if (gender != 0) {
            builder.setGender(gender);
        }
        Set<String> keywords = mediationAdRequest.getKeywords();
        if (keywords != null) {
            for (String addKeyword : keywords) {
                builder.addKeyword(addKeyword);
            }
        }
        Location location = mediationAdRequest.getLocation();
        if (location != null) {
            builder.setLocation(location);
        }
        if (mediationAdRequest.isTesting()) {
            zzlc.zzij();
            builder.addTestDevice(zzako.zzaz(context));
        }
        if (mediationAdRequest.taggedForChildDirectedTreatment() != -1) {
            boolean z = true;
            if (mediationAdRequest.taggedForChildDirectedTreatment() != 1) {
                z = false;
            }
            builder.tagForChildDirectedTreatment(z);
        }
        builder.setIsDesignedForFamilies(mediationAdRequest.isDesignedForFamilies());
        builder.addNetworkExtrasBundle(AdMobAdapter.class, zza(bundle, bundle2));
        return builder.build();
    }

    public String getAdUnitId(Bundle bundle) {
        return bundle.getString(AD_UNIT_ID_PARAMETER);
    }

    public View getBannerView() {
        return this.zzgs;
    }

    public Bundle getInterstitialAdapterInfo() {
        return new MediationAdapter.zza().zzah(1).zzvp();
    }

    public zzmm getVideoController() {
        VideoController videoController;
        AdView adView = this.zzgs;
        if (adView == null || (videoController = adView.getVideoController()) == null) {
            return null;
        }
        return videoController.zzbh();
    }

    public void initialize(Context context, MediationAdRequest mediationAdRequest, String str, MediationRewardedVideoAdListener mediationRewardedVideoAdListener, Bundle bundle, Bundle bundle2) {
        this.zzgv = context.getApplicationContext();
        this.zzgx = mediationRewardedVideoAdListener;
        mediationRewardedVideoAdListener.onInitializationSucceeded(this);
    }

    public boolean isInitialized() {
        return this.zzgx != null;
    }

    public void loadAd(MediationAdRequest mediationAdRequest, Bundle bundle, Bundle bundle2) {
        Context context = this.zzgv;
        if (context == null || this.zzgx == null) {
            zzaky.e("AdMobAdapter.loadAd called before initialize.");
            return;
        }
        InterstitialAd interstitialAd = new InterstitialAd(context);
        this.zzgw = interstitialAd;
        interstitialAd.zza(true);
        this.zzgw.setAdUnitId(getAdUnitId(bundle));
        this.zzgw.setRewardedVideoAdListener(this.zzgy);
        this.zzgw.loadAd(zza(this.zzgv, mediationAdRequest, bundle2, bundle));
    }

    public void onDestroy() {
        AdView adView = this.zzgs;
        if (adView != null) {
            adView.destroy();
            this.zzgs = null;
        }
        if (this.zzgt != null) {
            this.zzgt = null;
        }
        if (this.zzgu != null) {
            this.zzgu = null;
        }
        if (this.zzgw != null) {
            this.zzgw = null;
        }
    }

    public void onImmersiveModeUpdated(boolean z) {
        InterstitialAd interstitialAd = this.zzgt;
        if (interstitialAd != null) {
            interstitialAd.setImmersiveMode(z);
        }
        InterstitialAd interstitialAd2 = this.zzgw;
        if (interstitialAd2 != null) {
            interstitialAd2.setImmersiveMode(z);
        }
    }

    public void onPause() {
        AdView adView = this.zzgs;
        if (adView != null) {
            adView.pause();
        }
    }

    public void onResume() {
        AdView adView = this.zzgs;
        if (adView != null) {
            adView.resume();
        }
    }

    public void requestBannerAd(Context context, MediationBannerListener mediationBannerListener, Bundle bundle, AdSize adSize, MediationAdRequest mediationAdRequest, Bundle bundle2) {
        AdView adView = new AdView(context);
        this.zzgs = adView;
        adView.setAdSize(new AdSize(adSize.getWidth(), adSize.getHeight()));
        this.zzgs.setAdUnitId(getAdUnitId(bundle));
        this.zzgs.setAdListener(new zzd(this, mediationBannerListener));
        this.zzgs.loadAd(zza(context, mediationAdRequest, bundle2, bundle));
    }

    public void requestInterstitialAd(Context context, MediationInterstitialListener mediationInterstitialListener, Bundle bundle, MediationAdRequest mediationAdRequest, Bundle bundle2) {
        InterstitialAd interstitialAd = new InterstitialAd(context);
        this.zzgt = interstitialAd;
        interstitialAd.setAdUnitId(getAdUnitId(bundle));
        this.zzgt.setAdListener(new zze(this, mediationInterstitialListener));
        this.zzgt.loadAd(zza(context, mediationAdRequest, bundle2, bundle));
    }

    public void requestNativeAd(Context context, MediationNativeListener mediationNativeListener, Bundle bundle, NativeMediationAdRequest nativeMediationAdRequest, Bundle bundle2) {
        zzf zzf2 = new zzf(this, mediationNativeListener);
        AdLoader.Builder withAdListener = new AdLoader.Builder(context, bundle.getString(AD_UNIT_ID_PARAMETER)).withAdListener(zzf2);
        NativeAdOptions nativeAdOptions = nativeMediationAdRequest.getNativeAdOptions();
        if (nativeAdOptions != null) {
            withAdListener.withNativeAdOptions(nativeAdOptions);
        }
        if (nativeMediationAdRequest.zznb()) {
            withAdListener.zza(zzf2);
        }
        if (nativeMediationAdRequest.isAppInstallAdRequested()) {
            withAdListener.forAppInstallAd(zzf2);
        }
        if (nativeMediationAdRequest.isContentAdRequested()) {
            withAdListener.forContentAd(zzf2);
        }
        if (nativeMediationAdRequest.zznc()) {
            for (String next : nativeMediationAdRequest.zznd().keySet()) {
                withAdListener.forCustomTemplateAd(next, zzf2, nativeMediationAdRequest.zznd().get(next).booleanValue() ? zzf2 : null);
            }
        }
        AdLoader build = withAdListener.build();
        this.zzgu = build;
        build.loadAd(zza(context, nativeMediationAdRequest, bundle2, bundle));
    }

    public void showInterstitial() {
        this.zzgt.show();
    }

    public void showVideo() {
        this.zzgw.show();
    }

    /* access modifiers changed from: protected */
    public abstract Bundle zza(Bundle bundle, Bundle bundle2);
}
