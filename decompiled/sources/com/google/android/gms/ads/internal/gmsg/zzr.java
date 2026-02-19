package com.google.android.gms.ads.internal.gmsg;

import com.google.android.gms.internal.zzaof;
import java.util.Map;
import org.jivesoftware.smackx.Form;

final class zzr implements zzt<zzaof> {
    zzr() {
    }

    public final void zza(zzaof zzaof, Map<String, String> map) {
        if (map.keySet().contains("start")) {
            zzaof.zzua().zzuu();
        } else if (map.keySet().contains("stop")) {
            zzaof.zzua().zzuv();
        } else if (map.keySet().contains(Form.TYPE_CANCEL)) {
            zzaof.zzua().zzuw();
        }
    }
}
