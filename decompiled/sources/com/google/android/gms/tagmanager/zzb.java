package com.google.android.gms.tagmanager;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;

final class zzb implements zzd {
    private /* synthetic */ zza zzkms;

    zzb(zza zza) {
        this.zzkms = zza;
    }

    public final AdvertisingIdClient.Info zzbfg() {
        String str;
        try {
            return AdvertisingIdClient.getAdvertisingIdInfo(this.zzkms.mContext);
        } catch (IllegalStateException e) {
            str = "IllegalStateException getting Advertising Id Info";
            zzdj.zzc(str, e);
            return null;
        } catch (GooglePlayServicesRepairableException e2) {
            str = "GooglePlayServicesRepairableException getting Advertising Id Info";
            zzdj.zzc(str, e2);
            return null;
        } catch (IOException e3) {
            str = "IOException getting Ad Id Info";
            zzdj.zzc(str, e3);
            return null;
        } catch (GooglePlayServicesNotAvailableException e4) {
            this.zzkms.close();
            str = "GooglePlayServicesNotAvailableException getting Advertising Id Info";
            zzdj.zzc(str, e4);
            return null;
        } catch (Exception e5) {
            str = "Unknown exception. Could not get the Advertising Id Info.";
            zzdj.zzc(str, e5);
            return null;
        }
    }
}
