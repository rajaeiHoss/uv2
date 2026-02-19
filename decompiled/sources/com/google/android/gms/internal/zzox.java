package com.google.android.gms.internal;

import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.ads.doubleclick.CustomRenderedAd;
import com.google.android.gms.dynamic.zzn;

@zzabh
public final class zzox implements CustomRenderedAd {
    private final zzoy zzbxa;

    public zzox(zzoy zzoy) {
        this.zzbxa = zzoy;
    }

    public final String getBaseUrl() {
        try {
            return this.zzbxa.zzjs();
        } catch (RemoteException e) {
            zzaky.zzc("Could not delegate getBaseURL to CustomRenderedAd", e);
            return null;
        }
    }

    public final String getContent() {
        try {
            return this.zzbxa.getContent();
        } catch (RemoteException e) {
            zzaky.zzc("Could not delegate getContent to CustomRenderedAd", e);
            return null;
        }
    }

    public final void onAdRendered(View view) {
        try {
            this.zzbxa.zze(view != null ? zzn.zzz(view) : null);
        } catch (RemoteException e) {
            zzaky.zzc("Could not delegate onAdRendered to CustomRenderedAd", e);
        }
    }

    public final void recordClick() {
        try {
            this.zzbxa.recordClick();
        } catch (RemoteException e) {
            zzaky.zzc("Could not delegate recordClick to CustomRenderedAd", e);
        }
    }

    public final void recordImpression() {
        try {
            this.zzbxa.recordImpression();
        } catch (RemoteException e) {
            zzaky.zzc("Could not delegate recordImpression to CustomRenderedAd", e);
        }
    }
}
