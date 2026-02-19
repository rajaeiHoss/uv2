package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.gms.dynamic.zzn;

@zzabh
public final class zzafk implements RewardedVideoAd {
    private final Context mContext;
    private final Object mLock = new Object();
    private String zzaux;
    private final zzaex zzczz;
    private RewardedVideoAdListener zzgy;

    public zzafk(Context context, zzaex zzaex) {
        this.zzczz = zzaex;
        this.mContext = context;
    }

    private final void zza(String str, zzmu zzmu) {
        synchronized (this.mLock) {
            zzaex zzaex = this.zzczz;
            if (zzaex != null) {
                try {
                    zzaex.zza(new zzafi(zzkn.zza(this.mContext, zzmu), str));
                } catch (RemoteException e) {
                    zzaky.zzc("Could not forward loadAd to RewardedVideoAd", e);
                }
            }
        }
    }

    public final void destroy() {
        destroy((Context) null);
    }

    public final void destroy(Context context) {
        synchronized (this.mLock) {
            zzaex zzaex = this.zzczz;
            if (zzaex != null) {
                try {
                    zzaex.zzd(zzn.zzz(context));
                } catch (RemoteException e) {
                    zzaky.zzc("Could not forward destroy to RewardedVideoAd", e);
                }
            }
        }
    }

    public final String getMediationAdapterClassName() {
        try {
            zzaex zzaex = this.zzczz;
            if (zzaex != null) {
                return zzaex.getMediationAdapterClassName();
            }
            return null;
        } catch (RemoteException e) {
            zzaky.zzc("Failed to get the mediation adapter class name.", e);
            return null;
        }
    }

    public final RewardedVideoAdListener getRewardedVideoAdListener() {
        RewardedVideoAdListener rewardedVideoAdListener;
        synchronized (this.mLock) {
            rewardedVideoAdListener = this.zzgy;
        }
        return rewardedVideoAdListener;
    }

    public final String getUserId() {
        String str;
        synchronized (this.mLock) {
            str = this.zzaux;
        }
        return str;
    }

    public final boolean isLoaded() {
        synchronized (this.mLock) {
            zzaex zzaex = this.zzczz;
            if (zzaex == null) {
                return false;
            }
            try {
                boolean isLoaded = zzaex.isLoaded();
                return isLoaded;
            } catch (RemoteException e) {
                zzaky.zzc("Could not forward isLoaded to RewardedVideoAd", e);
                return false;
            }
        }
    }

    public final void loadAd(String str, AdRequest adRequest) {
        zza(str, adRequest.zzbe());
    }

    public final void loadAd(String str, PublisherAdRequest publisherAdRequest) {
        zza(str, publisherAdRequest.zzbe());
    }

    public final void pause() {
        pause((Context) null);
    }

    public final void pause(Context context) {
        synchronized (this.mLock) {
            zzaex zzaex = this.zzczz;
            if (zzaex != null) {
                try {
                    zzaex.zzb(zzn.zzz(context));
                } catch (RemoteException e) {
                    zzaky.zzc("Could not forward pause to RewardedVideoAd", e);
                }
            }
        }
    }

    public final void resume() {
        resume((Context) null);
    }

    public final void resume(Context context) {
        synchronized (this.mLock) {
            zzaex zzaex = this.zzczz;
            if (zzaex != null) {
                try {
                    zzaex.zzc(zzn.zzz(context));
                } catch (RemoteException e) {
                    zzaky.zzc("Could not forward resume to RewardedVideoAd", e);
                }
            }
        }
    }

    public final void setImmersiveMode(boolean z) {
        synchronized (this.mLock) {
            zzaex zzaex = this.zzczz;
            if (zzaex != null) {
                try {
                    zzaex.setImmersiveMode(z);
                } catch (RemoteException e) {
                    zzaky.zzc("Could not forward setImmersiveMode to RewardedVideoAd", e);
                }
            }
        }
    }

    public final void setRewardedVideoAdListener(RewardedVideoAdListener rewardedVideoAdListener) {
        synchronized (this.mLock) {
            this.zzgy = rewardedVideoAdListener;
            zzaex zzaex = this.zzczz;
            if (zzaex != null) {
                try {
                    zzaex.zza((zzafc) new zzafh(rewardedVideoAdListener));
                } catch (RemoteException e) {
                    zzaky.zzc("Could not forward setRewardedVideoAdListener to RewardedVideoAd", e);
                }
            }
        }
    }

    public final void setUserId(String str) {
        synchronized (this.mLock) {
            this.zzaux = str;
            zzaex zzaex = this.zzczz;
            if (zzaex != null) {
                try {
                    zzaex.setUserId(str);
                } catch (RemoteException e) {
                    zzaky.zzc("Could not forward setUserId to RewardedVideoAd", e);
                }
            }
        }
    }

    public final void show() {
        synchronized (this.mLock) {
            zzaex zzaex = this.zzczz;
            if (zzaex != null) {
                try {
                    zzaex.show();
                } catch (RemoteException e) {
                    zzaky.zzc("Could not forward show to RewardedVideoAd", e);
                }
            }
        }
    }
}
