package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.browser.customtabs.CustomTabsIntent;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.overlay.zzc;
import com.google.android.gms.ads.internal.overlay.zzt;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.common.util.zzs;

@zzabh
public final class zzxx implements MediationInterstitialAdapter {
    private Uri mUri;
    /* access modifiers changed from: private */
    public Activity zzckp;
    /* access modifiers changed from: private */
    public MediationInterstitialListener zzckq;

    public final void onDestroy() {
        zzaky.zzby("Destroying AdMobCustomTabsAdapter adapter.");
    }

    public final void onPause() {
        zzaky.zzby("Pausing AdMobCustomTabsAdapter adapter.");
    }

    public final void onResume() {
        zzaky.zzby("Resuming AdMobCustomTabsAdapter adapter.");
    }

    public final void requestInterstitialAd(Context context, MediationInterstitialListener mediationInterstitialListener, Bundle bundle, MediationAdRequest mediationAdRequest, Bundle bundle2) {
        this.zzckq = mediationInterstitialListener;
        if (mediationInterstitialListener == null) {
            zzaky.zzcz("Listener not set for mediation. Returning.");
        } else if (!(context instanceof Activity)) {
            zzaky.zzcz("AdMobCustomTabs can only work with Activity context. Bailing out.");
            this.zzckq.onAdFailedToLoad(this, 0);
        } else {
            if (!(zzs.zzanr() && zzpf.zzh(context))) {
                zzaky.zzcz("Default browser does not support custom tabs. Bailing out.");
                this.zzckq.onAdFailedToLoad(this, 0);
                return;
            }
            String string = bundle.getString("tab_url");
            if (TextUtils.isEmpty(string)) {
                zzaky.zzcz("The tab_url retrieved from mediation metadata is empty. Bailing out.");
                this.zzckq.onAdFailedToLoad(this, 0);
                return;
            }
            this.zzckp = (Activity) context;
            this.mUri = Uri.parse(string);
            this.zzckq.onAdLoaded(this);
        }
    }

    public final void showInterstitial() {
        CustomTabsIntent build = new CustomTabsIntent.Builder().build();
        build.intent.setData(this.mUri);
        zzaij.zzdfn.post(new zzxz(this, new AdOverlayInfoParcel(new zzc(build.intent), (zzkf) null, new zzxy(this), (zzt) null, new zzala(0, 0, false))));
        zzbt.zzep().zzpz();
    }
}
