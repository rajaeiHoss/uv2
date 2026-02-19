package com.google.android.gms.fitness.request;

import android.os.Looper;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.api.internal.zzck;
import com.google.android.gms.common.api.internal.zzcm;
import java.util.HashMap;
import java.util.Map;

public final class zzan {
    private static final zzan zzhox = new zzan();
    private final Map<zzck<OnDataPointListener>, zzal> zzhoy = new HashMap();

    private zzan() {
    }

    public static zzan zzash() {
        return zzhox;
    }

    private static zzci<OnDataPointListener> zzc(OnDataPointListener onDataPointListener) {
        return zzcm.zzb(onDataPointListener, Looper.getMainLooper(), OnDataPointListener.class.getSimpleName());
    }

    public final zzal zza(OnDataPointListener onDataPointListener) {
        return zzc(zzc(onDataPointListener));
    }

    public final zzal zzb(OnDataPointListener onDataPointListener) {
        return zzd(zzc(onDataPointListener));
    }

    public final zzal zzc(zzci<OnDataPointListener> zzci) {
        zzal zzal;
        synchronized (this.zzhoy) {
            zzal = this.zzhoy.get(zzci.zzakx());
            if (zzal == null) {
                zzal = new zzal(zzci, (zzam) null);
                this.zzhoy.put(zzci.zzakx(), zzal);
            }
        }
        return zzal;
    }

    public final zzal zzd(zzci<OnDataPointListener> zzci) {
        zzal remove;
        synchronized (this.zzhoy) {
            remove = this.zzhoy.remove(zzci.zzakx());
            if (remove != null) {
                remove.release();
            }
        }
        return remove;
    }
}
