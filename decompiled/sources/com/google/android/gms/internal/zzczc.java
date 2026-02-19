package com.google.android.gms.internal;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;

final class zzczc implements zzcze {
    private /* synthetic */ zzczb zzkvm;

    zzczc(zzczb zzczb) {
        this.zzkvm = zzczb;
    }

    public final AdvertisingIdClient.Info zzbfg() {
        try {
            return AdvertisingIdClient.getAdvertisingIdInfo(this.zzkvm.mContext);
        } catch (IllegalStateException e) {
            zzdal.zzc("IllegalStateException getting Advertising Id Info", e);
            return null;
        } catch (GooglePlayServicesRepairableException e2) {
            zzdal.zzc("GooglePlayServicesRepairableException getting Advertising Id Info", e2);
            return null;
        } catch (IOException e3) {
            zzdal.zzc("IOException getting Ad Id Info", e3);
            return null;
        } catch (GooglePlayServicesNotAvailableException e4) {
            boolean unused = this.zzkvm.zzkvj = false;
            zzdal.zzc("GooglePlayServicesNotAvailableException getting Advertising Id Info", e4);
            return null;
        } catch (Exception e5) {
            zzdal.zzc("Unknown exception. Could not get the Advertising Id Info.", e5);
            return null;
        }
    }
}
