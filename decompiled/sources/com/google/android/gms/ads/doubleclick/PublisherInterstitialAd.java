package com.google.android.gms.ads.doubleclick;

import android.content.Context;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.Correlator;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzmy;

public final class PublisherInterstitialAd {
    private final zzmy zzaly;

    public PublisherInterstitialAd(Context context) {
        this.zzaly = new zzmy(context, this);
        zzbq.checkNotNull(context, "Context cannot be null");
    }

    public final AdListener getAdListener() {
        return this.zzaly.getAdListener();
    }

    public final String getAdUnitId() {
        return this.zzaly.getAdUnitId();
    }

    public final AppEventListener getAppEventListener() {
        return this.zzaly.getAppEventListener();
    }

    public final String getMediationAdapterClassName() {
        return this.zzaly.getMediationAdapterClassName();
    }

    public final OnCustomRenderedAdLoadedListener getOnCustomRenderedAdLoadedListener() {
        return this.zzaly.getOnCustomRenderedAdLoadedListener();
    }

    public final boolean isLoaded() {
        return this.zzaly.isLoaded();
    }

    public final boolean isLoading() {
        return this.zzaly.isLoading();
    }

    public final void loadAd(PublisherAdRequest publisherAdRequest) {
        this.zzaly.zza(publisherAdRequest.zzbe());
    }

    public final void setAdListener(AdListener adListener) {
        this.zzaly.setAdListener(adListener);
    }

    public final void setAdUnitId(String str) {
        this.zzaly.setAdUnitId(str);
    }

    public final void setAppEventListener(AppEventListener appEventListener) {
        this.zzaly.setAppEventListener(appEventListener);
    }

    public final void setCorrelator(Correlator correlator) {
        this.zzaly.setCorrelator(correlator);
    }

    public final void setImmersiveMode(boolean z) {
        this.zzaly.setImmersiveMode(z);
    }

    public final void setOnCustomRenderedAdLoadedListener(OnCustomRenderedAdLoadedListener onCustomRenderedAdLoadedListener) {
        this.zzaly.setOnCustomRenderedAdLoadedListener(onCustomRenderedAdLoadedListener);
    }

    public final void show() {
        this.zzaly.show();
    }
}
