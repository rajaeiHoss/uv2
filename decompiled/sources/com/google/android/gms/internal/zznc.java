package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.zzs;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.dynamic.zzp;
import com.google.android.gms.dynamic.zzq;

@zzabh
public final class zznc extends zzp<zzmk> {
    public zznc() {
        super("com.google.android.gms.ads.MobileAdsSettingManagerCreatorImpl");
    }

    /* access modifiers changed from: protected */
    public final zzmk zze(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IMobileAdsSettingManagerCreator");
        return queryLocalInterface instanceof zzmk ? (zzmk) queryLocalInterface : new zzml(iBinder);
    }

    public final zzmh zzg(Context context) {
        try {
            IBinder zza = ((zzmk) zzdg(context)).zza(zzn.zzz(context), zzs.GOOGLE_PLAY_SERVICES_VERSION_CODE);
            if (zza == null) {
                return null;
            }
            IInterface queryLocalInterface = zza.queryLocalInterface("com.google.android.gms.ads.internal.client.IMobileAdsSettingManager");
            return queryLocalInterface instanceof zzmh ? (zzmh) queryLocalInterface : new zzmj(zza);
        } catch (RemoteException e) {
            zzaky.zzc("Could not get remote MobileAdsSettingManager.", e);
            return null;
        } catch (zzq e2) {
            zzaky.zzc("Could not get remote MobileAdsSettingManager.", e2);
            return null;
        }
    }
}
