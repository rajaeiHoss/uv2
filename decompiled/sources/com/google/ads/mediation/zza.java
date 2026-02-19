package com.google.ads.mediation;

import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

final class zza implements RewardedVideoAdListener {
    private /* synthetic */ AbstractAdViewAdapter zzgz;

    zza(AbstractAdViewAdapter abstractAdViewAdapter) {
        this.zzgz = abstractAdViewAdapter;
    }

    public final void onRewarded(RewardItem rewardItem) {
        this.zzgz.zzgx.onRewarded(this.zzgz, rewardItem);
    }

    public final void onRewardedVideoAdClosed() {
        this.zzgz.zzgx.onAdClosed(this.zzgz);
        InterstitialAd unused = this.zzgz.zzgw = null;
    }

    public final void onRewardedVideoAdFailedToLoad(int i) {
        this.zzgz.zzgx.onAdFailedToLoad(this.zzgz, i);
    }

    public final void onRewardedVideoAdLeftApplication() {
        this.zzgz.zzgx.onAdLeftApplication(this.zzgz);
    }

    public final void onRewardedVideoAdLoaded() {
        this.zzgz.zzgx.onAdLoaded(this.zzgz);
    }

    public final void onRewardedVideoAdOpened() {
        this.zzgz.zzgx.onAdOpened(this.zzgz);
    }

    public final void onRewardedVideoCompleted() {
        this.zzgz.zzgx.onVideoCompleted(this.zzgz);
    }

    public final void onRewardedVideoStarted() {
        this.zzgz.zzgx.onVideoStarted(this.zzgz);
    }
}
