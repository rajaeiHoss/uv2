package com.google.android.gms.ads.internal.gmsg;

import com.google.android.gms.internal.zzaof;
import com.google.android.gms.internal.zzlc;
import com.google.android.gms.internal.zzoi;
import java.util.Map;

final class zzp implements zzt<zzaof> {
    zzp() {
    }

    public final void zza(zzaof zzaof, Map<String, String> map) {
        if (((Boolean) zzlc.zzio().zzd(zzoi.zzbqw)).booleanValue()) {
            zzaof.zzaj(!Boolean.parseBoolean((String) map.get("disabled")));
        }
    }
}
