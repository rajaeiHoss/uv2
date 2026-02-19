package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.dynamic.zzn;

@zzabh
public final class zzmz {
    private static final Object sLock = new Object();
    private static zzmz zzbkd;
    private zzmh zzbke;
    private RewardedVideoAd zzbkf;

    private zzmz() {
    }

    public static zzmz zziz() {
        zzmz zzmz;
        synchronized (sLock) {
            if (zzbkd == null) {
                zzbkd = new zzmz();
            }
            zzmz = zzbkd;
        }
        return zzmz;
    }

    public final RewardedVideoAd getRewardedVideoAdInstance(Context context) {
        synchronized (sLock) {
            RewardedVideoAd rewardedVideoAd = this.zzbkf;
            if (rewardedVideoAd != null) {
                return rewardedVideoAd;
            }
            zzafk zzafk = new zzafk(context, (zzaex) zzks.zza(context, false, new zzla(zzlc.zzik(), context, new zzwe())));
            this.zzbkf = zzafk;
            return zzafk;
        }
    }

    public final void openDebugMenu(Context context, String str) {
        zzbq.zza(this.zzbke != null, (Object) "MobileAds.initialize() must be called prior to opening debug menu.");
        try {
            this.zzbke.zzb(zzn.zzz(context), str);
        } catch (RemoteException e) {
            zzaky.zzb("Unable to open debug menu.", e);
        }
    }

    public final void setAppMuted(boolean z) {
        zzbq.zza(this.zzbke != null, (Object) "MobileAds.initialize() must be called prior to setting app muted state.");
        try {
            this.zzbke.setAppMuted(z);
        } catch (RemoteException e) {
            zzaky.zzb("Unable to set app mute state.", e);
        }
    }

    public final void setAppVolume(float f) {
        boolean z = true;
        zzbq.checkArgument(0.0f <= f && f <= 1.0f, "The app volume must be a value between 0 and 1 inclusive.");
        if (this.zzbke == null) {
            z = false;
        }
        zzbq.zza(z, (Object) "MobileAds.initialize() must be called prior to setting the app volume.");
        try {
            this.zzbke.setAppVolume(f);
        } catch (RemoteException e) {
            zzaky.zzb("Unable to set app volume.", e);
        }
    }

    public final void zza(Context context, String str, zznb zznb) {
        synchronized (sLock) {
            if (this.zzbke == null) {
                if (context != null) {
                    try {
                        zzmh zzmh = (zzmh) zzks.zza(context, false, new zzkx(zzlc.zzik(), context));
                        this.zzbke = zzmh;
                        zzmh.initialize();
                        if (str != null) {
                            this.zzbke.zza(str, zzn.zzz(new zzna(this, context)));
                        }
                    } catch (RemoteException e) {
                        zzaky.zzc("MobileAdsSettingManager initialization failed", e);
                    }
                } else {
                    throw new IllegalArgumentException("Context cannot be null.");
                }
            }
        }
    }

    public final float zzdp() {
        zzmh zzmh = this.zzbke;
        if (zzmh == null) {
            return 1.0f;
        }
        try {
            return zzmh.zzdp();
        } catch (RemoteException e) {
            zzaky.zzb("Unable to get app volume.", e);
            return 1.0f;
        }
    }

    public final boolean zzdq() {
        zzmh zzmh = this.zzbke;
        if (zzmh == null) {
            return false;
        }
        try {
            return zzmh.zzdq();
        } catch (RemoteException e) {
            zzaky.zzb("Unable to get app mute state.", e);
            return false;
        }
    }
}
