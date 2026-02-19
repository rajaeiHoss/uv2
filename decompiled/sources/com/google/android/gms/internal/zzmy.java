package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.Correlator;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.ads.doubleclick.OnCustomRenderedAdLoadedListener;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

@zzabh
public final class zzmy {
    private final Context mContext;
    private final zzkn zzaln;
    private AppEventListener zzamr;
    private String zzapp;
    private boolean zzaqf;
    private zzkf zzbgt;
    private AdListener zzbgu;
    private final zzwe zzbjr;
    private Correlator zzbjv;
    private zzlt zzbjw;
    private OnCustomRenderedAdLoadedListener zzbjx;
    private PublisherInterstitialAd zzbkb;
    private boolean zzbkc;
    private RewardedVideoAdListener zzgy;

    public zzmy(Context context) {
        this(context, zzkn.zzbhz, (PublisherInterstitialAd) null);
    }

    public zzmy(Context context, PublisherInterstitialAd publisherInterstitialAd) {
        this(context, zzkn.zzbhz, publisherInterstitialAd);
    }

    private zzmy(Context context, zzkn zzkn, PublisherInterstitialAd publisherInterstitialAd) {
        this.zzbjr = new zzwe();
        this.mContext = context;
        this.zzaln = zzkn;
        this.zzbkb = publisherInterstitialAd;
    }

    private final void zzak(String str) {
        if (this.zzbjw == null) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 63);
            sb.append("The ad unit ID must be set on InterstitialAd before ");
            sb.append(str);
            sb.append(" is called.");
            throw new IllegalStateException(sb.toString());
        }
    }

    public final AdListener getAdListener() {
        return this.zzbgu;
    }

    public final String getAdUnitId() {
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

    public final boolean isLoaded() {
        try {
            zzlt zzlt = this.zzbjw;
            if (zzlt == null) {
                return false;
            }
            return zzlt.isReady();
        } catch (RemoteException e) {
            zzaky.zzc("Failed to check if ad is ready.", e);
            return false;
        }
    }

    public final boolean isLoading() {
        try {
            zzlt zzlt = this.zzbjw;
            if (zzlt == null) {
                return false;
            }
            return zzlt.isLoading();
        } catch (RemoteException e) {
            zzaky.zzc("Failed to check if ad is loading.", e);
            return false;
        }
    }

    public final void setAdListener(AdListener adListener) {
        try {
            this.zzbgu = adListener;
            zzlt zzlt = this.zzbjw;
            if (zzlt != null) {
                zzlt.zza((zzli) adListener != null ? new zzkh(adListener) : null);
            }
        } catch (RemoteException e) {
            zzaky.zzc("Failed to set the AdListener.", e);
        }
    }

    public final void setAdUnitId(String str) {
        if (this.zzapp == null) {
            this.zzapp = str;
            return;
        }
        throw new IllegalStateException("The ad unit ID can only be set once on InterstitialAd.");
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

    public final void setImmersiveMode(boolean z) {
        try {
            this.zzaqf = z;
            zzlt zzlt = this.zzbjw;
            if (zzlt != null) {
                zzlt.setImmersiveMode(z);
            }
        } catch (RemoteException e) {
            zzaky.zzc("Failed to set immersive mode", e);
        }
    }

    public final void setOnCustomRenderedAdLoadedListener(OnCustomRenderedAdLoadedListener onCustomRenderedAdLoadedListener) {
        try {
            this.zzbjx = onCustomRenderedAdLoadedListener;
            zzlt zzlt = this.zzbjw;
            if (zzlt != null) {
                zzlt.zza((zzpb) onCustomRenderedAdLoadedListener != null ? new zzpe(onCustomRenderedAdLoadedListener) : null);
            }
        } catch (RemoteException e) {
            zzaky.zzc("Failed to set the OnCustomRenderedAdLoadedListener.", e);
        }
    }

    public final void setRewardedVideoAdListener(RewardedVideoAdListener rewardedVideoAdListener) {
        try {
            this.zzgy = rewardedVideoAdListener;
            zzlt zzlt = this.zzbjw;
            if (zzlt != null) {
                zzlt.zza((zzafc) rewardedVideoAdListener != null ? new zzafh(rewardedVideoAdListener) : null);
            }
        } catch (RemoteException e) {
            zzaky.zzc("Failed to set the AdListener.", e);
        }
    }

    public final void show() {
        try {
            zzak("show");
            this.zzbjw.showInterstitial();
        } catch (RemoteException e) {
            zzaky.zzc("Failed to show interstitial.", e);
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
        try {
            if (this.zzbjw == null) {
                if (this.zzapp == null) {
                    zzak("loadAd");
                }
                zzko zzib = this.zzbkc ? zzko.zzib() : new zzko();
                zzks zzik = zzlc.zzik();
                Context context = this.mContext;
                zzlt zzlt = (zzlt) zzks.zza(context, false, new zzkv(zzik, context, zzib, this.zzapp, this.zzbjr));
                this.zzbjw = zzlt;
                if (this.zzbgu != null) {
                    zzlt.zza((zzli) new zzkh(this.zzbgu));
                }
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
                if (this.zzgy != null) {
                    this.zzbjw.zza((zzafc) new zzafh(this.zzgy));
                }
                this.zzbjw.setImmersiveMode(this.zzaqf);
            }
            if (this.zzbjw.zzb(zzkn.zza(this.mContext, zzmu))) {
                this.zzbjr.zzn(zzmu.zziv());
            }
        } catch (RemoteException e) {
            zzaky.zzc("Failed to load ad.", e);
        }
    }

    public final void zza(boolean z) {
        this.zzbkc = true;
    }
}
