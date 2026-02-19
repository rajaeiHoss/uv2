package com.google.android.gms.internal;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.concurrent.TimeUnit;

public final class zzbwm {
    public static <DP, DT> String zza(DP dp, zzbwj<DP, DT> zzbwj) {
        double d;
        zzbwk<DT> zzarn = zzbwj.zzarn();
        if (!zzarn.zzhm(zzbwj.zzab(dp))) {
            return null;
        }
        DT zzaa = zzbwj.zzaa(dp);
        for (int i = 0; i < zzarn.zzac(zzaa); i++) {
            String zzg = zzarn.zzg(zzaa, i);
            if (zzbwj.zzd(dp, i)) {
                double zze = (double) zzarn.zze(zzaa, i);
                if (zze == 1.0d) {
                    d = (double) zzbwj.zzc(dp, i);
                } else if (zze == 2.0d) {
                    d = zzbwj.zzb(dp, i);
                } else {
                    continue;
                }
                zzbwp zzhv = zzbwn.zzase().zzhv(zzg);
                if (zzhv != null && !zzhv.zzf(d)) {
                    return "Field out of range";
                }
                zzbwp zzz = zzbwn.zzase().zzz(zzarn.zzad(zzaa), zzg);
                if (zzz != null) {
                    long zza = zzbwj.zza(dp, TimeUnit.NANOSECONDS);
                    if (zza == 0) {
                        if (d == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                            return null;
                        }
                        return "DataPoint out of range";
                    } else if (!zzz.zzf(d / ((double) zza))) {
                        return "DataPoint out of range";
                    }
                } else {
                    continue;
                }
            } else if (!zzarn.zzf(zzaa, i) && !zzbwn.zzhlz.contains(zzg)) {
                return String.valueOf(zzg).concat(" not set");
            }
        }
        return null;
    }
}
