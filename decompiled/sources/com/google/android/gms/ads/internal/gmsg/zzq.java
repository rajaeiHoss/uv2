package com.google.android.gms.ads.internal.gmsg;

import com.google.android.gms.internal.zzaof;
import java.util.Map;

final class zzq implements zzt<zzaof> {
    zzq() {
    }

    public final void zza(zzaof zzaof, Map<String, String> map) {
        String str = (String) map.get("action");
        if ("pause".equals(str)) {
            zzaof.zzcp();
        } else if ("resume".equals(str)) {
            zzaof.zzcq();
        }
    }
}
