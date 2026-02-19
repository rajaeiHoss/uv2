package com.google.android.gms.internal;

import com.google.android.gms.ads.AdListener;

@zzabh
public class zzle extends AdListener {
    private final Object lock = new Object();
    private AdListener zzbjg;

    public void onAdClosed() {
        synchronized (this.lock) {
            AdListener adListener = this.zzbjg;
            if (adListener != null) {
                adListener.onAdClosed();
            }
        }
    }

    public void onAdFailedToLoad(int i) {
        synchronized (this.lock) {
            AdListener adListener = this.zzbjg;
            if (adListener != null) {
                adListener.onAdFailedToLoad(i);
            }
        }
    }

    public void onAdLeftApplication() {
        synchronized (this.lock) {
            AdListener adListener = this.zzbjg;
            if (adListener != null) {
                adListener.onAdLeftApplication();
            }
        }
    }

    public void onAdLoaded() {
        synchronized (this.lock) {
            AdListener adListener = this.zzbjg;
            if (adListener != null) {
                adListener.onAdLoaded();
            }
        }
    }

    public void onAdOpened() {
        synchronized (this.lock) {
            AdListener adListener = this.zzbjg;
            if (adListener != null) {
                adListener.onAdOpened();
            }
        }
    }

    public final void zza(AdListener adListener) {
        synchronized (this.lock) {
            this.zzbjg = adListener;
        }
    }
}
