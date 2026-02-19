package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzbt;
import java.util.Map;

@zzabh
public final class zzyf {
    private final zzaof zzcct;
    private final boolean zzclm;
    private final String zzcln;

    public zzyf(zzaof zzaof, Map<String, String> map) {
        this.zzcct = zzaof;
        this.zzcln = map.get("forceOrientation");
        this.zzclm = map.containsKey("allowOrientationChange") ? Boolean.parseBoolean(map.get("allowOrientationChange")) : true;
    }

    public final void execute() {
        if (this.zzcct == null) {
            zzahw.zzcz("AdWebView is null");
        } else {
            this.zzcct.setRequestedOrientation("portrait".equalsIgnoreCase(this.zzcln) ? zzbt.zzen().zzrh() : "landscape".equalsIgnoreCase(this.zzcln) ? zzbt.zzen().zzrg() : this.zzclm ? -1 : zzbt.zzen().zzri());
        }
    }
}
