package com.google.android.gms.ads.internal.gmsg;

import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzahw;
import java.util.Map;

@zzabh
public final class zzw implements zzt<Object> {
    private final zzx zzcck;

    public zzw(zzx zzx) {
        this.zzcck = zzx;
    }

    public final void zza(Object obj, Map<String, String> map) {
        boolean equals = "1".equals(map.get("transparentBackground"));
        boolean equals2 = "1".equals(map.get("blur"));
        float f = 0.0f;
        try {
            if (map.get("blurRadius") != null) {
                f = Float.parseFloat(map.get("blurRadius"));
            }
        } catch (NumberFormatException e) {
            zzahw.zzb("Fail to parse float", e);
        }
        this.zzcck.zze(equals);
        this.zzcck.zza(equals2, f);
    }
}
