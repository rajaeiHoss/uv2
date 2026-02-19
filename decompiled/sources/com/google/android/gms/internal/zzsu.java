package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.ads.formats.OnPublisherAdViewLoadedListener;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;

public final class zzsu extends zzsf {
    /* access modifiers changed from: private */
    public final OnPublisherAdViewLoadedListener zzcax;

    public zzsu(OnPublisherAdViewLoadedListener onPublisherAdViewLoadedListener) {
        this.zzcax = onPublisherAdViewLoadedListener;
    }

    public final void zza(zzlt zzlt, IObjectWrapper iObjectWrapper) {
        if (zzlt != null && iObjectWrapper != null) {
            PublisherAdView publisherAdView = new PublisherAdView((Context) zzn.zzy(iObjectWrapper));
            AppEventListener appEventListener = null;
            try {
                if (zzlt.zzcd() instanceof zzkh) {
                    zzkh zzkh = (zzkh) zzlt.zzcd();
                    publisherAdView.setAdListener(zzkh != null ? zzkh.getAdListener() : null);
                }
            } catch (RemoteException e) {
                zzaky.zzc("Failed to get ad listener.", e);
            }
            try {
                if (zzlt.zzcc() instanceof zzkq) {
                    zzkq zzkq = (zzkq) zzlt.zzcc();
                    if (zzkq != null) {
                        appEventListener = zzkq.getAppEventListener();
                    }
                    publisherAdView.setAppEventListener(appEventListener);
                }
            } catch (RemoteException e2) {
                zzaky.zzc("Failed to get app event listener.", e2);
            }
            zzako.zzaju.post(new zzsv(this, publisherAdView, zzlt));
        }
    }
}
