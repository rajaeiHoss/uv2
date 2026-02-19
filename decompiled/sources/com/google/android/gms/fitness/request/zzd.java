package com.google.android.gms.fitness.request;

import android.os.Looper;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.api.internal.zzck;
import com.google.android.gms.common.api.internal.zzcm;
import java.util.HashMap;
import java.util.Map;

public final class zzd {
    private static final zzd zzhnr = new zzd();
    private final Map<zzck<BleScanCallback>, zza> zzhns = new HashMap();

    private zzd() {
    }

    public static zzd zzasf() {
        return zzhnr;
    }

    private static zzci<BleScanCallback> zzc(BleScanCallback bleScanCallback) {
        return zzcm.zzb(bleScanCallback, Looper.getMainLooper(), BleScanCallback.class.getSimpleName());
    }

    public final zza zza(zzci<BleScanCallback> zzci) {
        zza zza;
        synchronized (this.zzhns) {
            zza = this.zzhns.get(zzci.zzakx());
            if (zza == null) {
                zza = new zza(zzci, (zzb) null);
                this.zzhns.put(zzci.zzakx(), zza);
            }
        }
        return zza;
    }

    public final zza zza(BleScanCallback bleScanCallback) {
        return zza(zzc(bleScanCallback));
    }

    public final zza zzb(zzci<BleScanCallback> zzci) {
        zza zza;
        synchronized (this.zzhns) {
            zza = this.zzhns.get(zzci.zzakx());
            if (zza != null) {
                zza.release();
            }
        }
        return zza;
    }

    public final zza zzb(BleScanCallback bleScanCallback) {
        return zzb(zzc(bleScanCallback));
    }
}
