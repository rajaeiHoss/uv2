package com.google.android.gms.internal;

import com.google.android.gms.ads.doubleclick.PublisherAdView;

final class zzsv implements Runnable {
    private /* synthetic */ PublisherAdView zzcay;
    private /* synthetic */ zzlt zzcaz;
    private /* synthetic */ zzsu zzcba;

    zzsv(zzsu zzsu, PublisherAdView publisherAdView, zzlt zzlt) {
        this.zzcba = zzsu;
        this.zzcay = publisherAdView;
        this.zzcaz = zzlt;
    }

    public final void run() {
        if (this.zzcay.zza(this.zzcaz)) {
            this.zzcba.zzcax.onPublisherAdViewLoaded(this.zzcay);
        } else {
            zzaky.zzcz("Could not bind ad manager");
        }
    }
}
