package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.Correlator;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.ads.doubleclick.OnCustomRenderedAdLoadedListener;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import java.util.concurrent.atomic.AtomicBoolean;

@zzabh
public final class zzmw {
    private final zzkn zzaln;
    private VideoOptions zzamj;
    private boolean zzamp;
    private AppEventListener zzamr;
    private String zzapp;
    private zzkf zzbgt;
    private AdListener zzbgu;
    private AdSize[] zzbig;
    private final zzwe zzbjr;
    private final AtomicBoolean zzbjs;
    /* access modifiers changed from: private */
    public final VideoController zzbjt;
    private zzle zzbju;
    private Correlator zzbjv;
    private zzlt zzbjw;
    private OnCustomRenderedAdLoadedListener zzbjx;
    private ViewGroup zzbjy;
    private int zzbjz;

    public zzmw(ViewGroup viewGroup) {
        this(viewGroup, (AttributeSet) null, false, zzkn.zzbhz, 0);
    }

    public zzmw(ViewGroup viewGroup, int i) {
        this(viewGroup, (AttributeSet) null, false, zzkn.zzbhz, i);
    }

    public zzmw(ViewGroup viewGroup, AttributeSet attributeSet, boolean z) {
        this(viewGroup, attributeSet, z, zzkn.zzbhz, 0);
    }

    public zzmw(ViewGroup viewGroup, AttributeSet attributeSet, boolean z, int i) {
        this(viewGroup, attributeSet, false, zzkn.zzbhz, i);
    }

    private zzmw(ViewGroup viewGroup, AttributeSet attributeSet, boolean z, zzkn zzkn, int i) {
        this(viewGroup, attributeSet, z, zzkn, (zzlt) null, i);
    }

    private zzmw(ViewGroup viewGroup, AttributeSet attributeSet, boolean z, zzkn zzkn, zzlt zzlt, int i) {
        this.zzbjr = new zzwe();
        this.zzbjt = new VideoController();
        this.zzbju = new zzmx(this);
        this.zzbjy = viewGroup;
        this.zzaln = zzkn;
        this.zzbjw = null;
        this.zzbjs = new AtomicBoolean(false);
        this.zzbjz = i;
        if (attributeSet != null) {
            Context context = viewGroup.getContext();
            try {
                zzkr zzkr = new zzkr(context, attributeSet);
                this.zzbig = zzkr.zzi(z);
                this.zzapp = zzkr.getAdUnitId();
                if (viewGroup.isInEditMode()) {
                    zzako zzij = zzlc.zzij();
                    AdSize adSize = this.zzbig[0];
                    int i2 = this.zzbjz;
                    zzko zzko = new zzko(context, adSize);
                    zzko.zzbif = zzs(i2);
                    zzij.zza(viewGroup, zzko, "Ads by Google");
                }
            } catch (IllegalArgumentException e) {
                zzlc.zzij().zza(viewGroup, new zzko(context, AdSize.BANNER), e.getMessage(), e.getMessage());
            }
        }
    }

    private static zzko zza(Context context, AdSize[] adSizeArr, int i) {
        zzko zzko = new zzko(context, adSizeArr);
        zzko.zzbif = zzs(i);
        return zzko;
    }

    private static boolean zzs(int i) {
        return i == 1;
    }

    public final void destroy() {
        try {
            zzlt zzlt = this.zzbjw;
            if (zzlt != null) {
                zzlt.destroy();
            }
        } catch (RemoteException e) {
            zzaky.zzc("Failed to destroy AdView.", e);
        }
    }

    public final AdListener getAdListener() {
        return this.zzbgu;
    }

    public final AdSize getAdSize() {
        zzko zzbq;
        try {
            zzlt zzlt = this.zzbjw;
            if (!(zzlt == null || (zzbq = zzlt.zzbq()) == null)) {
                return zzbq.zzic();
            }
        } catch (RemoteException e) {
            zzaky.zzc("Failed to get the current AdSize.", e);
        }
        AdSize[] adSizeArr = this.zzbig;
        if (adSizeArr != null) {
            return adSizeArr[0];
        }
        return null;
    }

    public final AdSize[] getAdSizes() {
        return this.zzbig;
    }

    public final String getAdUnitId() {
        zzlt zzlt;
        if (this.zzapp == null && (zzlt = this.zzbjw) != null) {
            try {
                this.zzapp = zzlt.getAdUnitId();
            } catch (RemoteException e) {
                zzaky.zzc("Failed to get ad unit id.", e);
            }
        }
        return this.zzapp;
    }

    public final AppEventListener getAppEventListener() {
        return this.zzamr;
    }

    public final String getMediationAdapterClassName() {
        try {
            zzlt zzlt = this.zzbjw;
            if (zzlt != null) {
                return zzlt.zzco();
            }
            return null;
        } catch (RemoteException e) {
            zzaky.zzc("Failed to get the mediation adapter class name.", e);
            return null;
        }
    }

    public final OnCustomRenderedAdLoadedListener getOnCustomRenderedAdLoadedListener() {
        return this.zzbjx;
    }

    public final VideoController getVideoController() {
        return this.zzbjt;
    }

    public final VideoOptions getVideoOptions() {
        return this.zzamj;
    }

    public final boolean isLoading() {
        try {
            zzlt zzlt = this.zzbjw;
            if (zzlt != null) {
                return zzlt.isLoading();
            }
            return false;
        } catch (RemoteException e) {
            zzaky.zzc("Failed to check if ad is loading.", e);
            return false;
        }
    }

    public final void pause() {
        try {
            zzlt zzlt = this.zzbjw;
            if (zzlt != null) {
                zzlt.pause();
            }
        } catch (RemoteException e) {
            zzaky.zzc("Failed to call pause.", e);
        }
    }

    public final void recordManualImpression() {
        if (!this.zzbjs.getAndSet(true)) {
            try {
                zzlt zzlt = this.zzbjw;
                if (zzlt != null) {
                    zzlt.zzbs();
                }
            } catch (RemoteException e) {
                zzaky.zzc("Failed to record impression.", e);
            }
        }
    }

    public final void resume() {
        try {
            zzlt zzlt = this.zzbjw;
            if (zzlt != null) {
                zzlt.resume();
            }
        } catch (RemoteException e) {
            zzaky.zzc("Failed to call resume.", e);
        }
    }

    public final void setAdListener(AdListener adListener) {
        this.zzbgu = adListener;
        this.zzbju.zza(adListener);
    }

    public final void setAdSizes(AdSize... adSizeArr) {
        if (this.zzbig == null) {
            zza(adSizeArr);
            return;
        }
        throw new IllegalStateException("The ad size can only be set once on AdView.");
    }

    public final void setAdUnitId(String str) {
        if (this.zzapp == null) {
            this.zzapp = str;
            return;
        }
        throw new IllegalStateException("The ad unit ID can only be set once on AdView.");
    }

    public final void setAppEventListener(AppEventListener appEventListener) {
        try {
            this.zzamr = appEventListener;
            zzlt zzlt = this.zzbjw;
            if (zzlt != null) {
                zzlt.zza((zzly) appEventListener != null ? new zzkq(appEventListener) : null);
            }
        } catch (RemoteException e) {
            zzaky.zzc("Failed to set the AppEventListener.", e);
        }
    }

    public final void setCorrelator(Correlator correlator) {
        this.zzbjv = correlator;
        try {
            zzlt zzlt = this.zzbjw;
            if (zzlt != null) {
                zzlt.zza(correlator == null ? null : correlator.zzbf());
            }
        } catch (RemoteException e) {
            zzaky.zzc("Failed to set correlator.", e);
        }
    }

    public final void setManualImpressionsEnabled(boolean z) {
        this.zzamp = z;
        try {
            zzlt zzlt = this.zzbjw;
            if (zzlt != null) {
                zzlt.setManualImpressionsEnabled(z);
            }
        } catch (RemoteException e) {
            zzaky.zzc("Failed to set manual impressions.", e);
        }
    }

    public final void setOnCustomRenderedAdLoadedListener(OnCustomRenderedAdLoadedListener onCustomRenderedAdLoadedListener) {
        this.zzbjx = onCustomRenderedAdLoadedListener;
        try {
            zzlt zzlt = this.zzbjw;
            if (zzlt != null) {
                zzlt.zza((zzpb) onCustomRenderedAdLoadedListener != null ? new zzpe(onCustomRenderedAdLoadedListener) : null);
            }
        } catch (RemoteException e) {
            zzaky.zzc("Failed to set the onCustomRenderedAdLoadedListener.", e);
        }
    }

    public final void setVideoOptions(VideoOptions videoOptions) {
        this.zzamj = videoOptions;
        try {
            zzlt zzlt = this.zzbjw;
            if (zzlt != null) {
                zzlt.zza(videoOptions == null ? null : new zzns(videoOptions));
            }
        } catch (RemoteException e) {
            zzaky.zzc("Failed to set video options.", e);
        }
    }

    public final void zza(zzkf zzkf) {
        try {
            this.zzbgt = zzkf;
            zzlt zzlt = this.zzbjw;
            if (zzlt != null) {
                zzlt.zza((zzlf) zzkf != null ? new zzkg(zzkf) : null);
            }
        } catch (RemoteException e) {
            zzaky.zzc("Failed to set the AdClickListener.", e);
        }
    }

    public final void zza(zzmu zzmu) {
        Object zza;
        try {
            zzlt zzlt = this.zzbjw;
            if (zzlt == null) {
                if ((this.zzbig == null || this.zzapp == null) && zzlt == null) {
                    throw new IllegalStateException("The ad size and ad unit ID must be set before loadAd is called.");
                }
                Context context = this.zzbjy.getContext();
                zzko zza2 = zza(context, this.zzbig, this.zzbjz);
                if ("search_v2".equals(zza2.zzbia)) {
                    zza = zzks.zza(context, false, new zzku(zzlc.zzik(), context, zza2, this.zzapp));
                } else {
                    zza = zzks.zza(context, false, new zzkt(zzlc.zzik(), context, zza2, this.zzapp, this.zzbjr));
                }
                zzlt zzlt2 = (zzlt) zza;
                this.zzbjw = zzlt2;
                zzlt2.zza((zzli) new zzkh(this.zzbju));
                if (this.zzbgt != null) {
                    this.zzbjw.zza((zzlf) new zzkg(this.zzbgt));
                }
                if (this.zzamr != null) {
                    this.zzbjw.zza((zzly) new zzkq(this.zzamr));
                }
                if (this.zzbjx != null) {
                    this.zzbjw.zza((zzpb) new zzpe(this.zzbjx));
                }
                Correlator correlator = this.zzbjv;
                if (correlator != null) {
                    this.zzbjw.zza((zzme) correlator.zzbf());
                }
                if (this.zzamj != null) {
                    this.zzbjw.zza(new zzns(this.zzamj));
                }
                this.zzbjw.setManualImpressionsEnabled(this.zzamp);
                try {
                    IObjectWrapper zzbp = this.zzbjw.zzbp();
                    if (zzbp != null) {
                        this.zzbjy.addView((View) zzn.zzy(zzbp));
                    }
                } catch (RemoteException e) {
                    zzaky.zzc("Failed to get an ad frame.", e);
                }
            }
            if (this.zzbjw.zzb(zzkn.zza(this.zzbjy.getContext(), zzmu))) {
                this.zzbjr.zzn(zzmu.zziv());
            }
        } catch (RemoteException e2) {
            zzaky.zzc("Failed to load ad.", e2);
        }
    }

    public final void zza(AdSize... adSizeArr) {
        this.zzbig = adSizeArr;
        try {
            zzlt zzlt = this.zzbjw;
            if (zzlt != null) {
                zzlt.zza(zza(this.zzbjy.getContext(), this.zzbig, this.zzbjz));
            }
        } catch (RemoteException e) {
            zzaky.zzc("Failed to set the ad size.", e);
        }
        this.zzbjy.requestLayout();
    }

    public final boolean zza(zzlt zzlt) {
        if (zzlt == null) {
            return false;
        }
        try {
            IObjectWrapper zzbp = zzlt.zzbp();
            if (zzbp == null || ((View) zzn.zzy(zzbp)).getParent() != null) {
                return false;
            }
            this.zzbjy.addView((View) zzn.zzy(zzbp));
            this.zzbjw = zzlt;
            return true;
        } catch (RemoteException e) {
            zzaky.zzc("Failed to get an ad frame.", e);
            return false;
        }
    }

    public final zzmm zzbh() {
        zzlt zzlt = this.zzbjw;
        if (zzlt == null) {
            return null;
        }
        try {
            return zzlt.getVideoController();
        } catch (RemoteException e) {
            zzaky.zzc("Failed to retrieve VideoController.", e);
            return null;
        }
    }
}
