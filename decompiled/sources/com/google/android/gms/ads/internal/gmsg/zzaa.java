package com.google.android.gms.ads.internal.gmsg;

import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.common.util.zzf;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzaof;
import com.google.android.gms.internal.zzlc;
import com.google.android.gms.internal.zzoi;
import com.google.android.gms.internal.zzya;
import com.google.android.gms.internal.zzyd;
import com.google.android.gms.internal.zzyf;
import com.google.android.gms.internal.zzyg;
import com.google.android.gms.internal.zzyo;
import java.util.Map;

@zzabh
public final class zzaa implements zzt<zzaof> {
    private static Map<String, Integer> zzccp = zzf.zzb(new String[]{"resize", "playVideo", "storePicture", "createCalendarEvent", "setOrientationProperties", "closeResizedAd", "unload"}, new Integer[]{1, 2, 3, 4, 5, 6, 7});
    private final zzw zzccm;
    private final zzyd zzccn;
    private final zzyo zzcco;

    public zzaa(zzw zzw, zzyd zzyd, zzyo zzyo) {
        this.zzccm = zzw;
        this.zzccn = zzyd;
        this.zzcco = zzyo;
    }

    public final void zza(zzaof zzaof, Map<String, String> map) {
        zzw zzw;
        int intValue = zzccp.get((String) map.get("a")).intValue();
        if (intValue != 5 && intValue != 7 && (zzw = this.zzccm) != null && !zzw.zzcz()) {
            this.zzccm.zzt((String) null);
        } else if (intValue == 1) {
            this.zzccn.execute(map);
        } else if (intValue == 3) {
            new zzyg(zzaof, map).execute();
        } else if (intValue == 4) {
            new zzya(zzaof, map).execute();
        } else if (intValue == 5) {
            new zzyf(zzaof, map).execute();
        } else if (intValue == 6) {
            this.zzccn.zzm(true);
        } else if (intValue != 7) {
            zzahw.zzcy("Unknown MRAID command called.");
        } else {
            if (((Boolean) zzlc.zzio().zzd(zzoi.zzbnd)).booleanValue()) {
                this.zzcco.zzda();
            }
        }
    }
}
