package com.google.android.gms.internal;

import java.util.List;
import java.util.Map;

final class zzefe implements zzefh {
    private /* synthetic */ zzeey zznaz;
    private /* synthetic */ zzefl zznbg;

    zzefe(zzeey zzeey, zzefl zzefl) {
        this.zznaz = zzeey;
        this.zznbg = zzefl;
    }

    public final void zzal(Map<String, Object> map) {
        String str = (String) map.get("s");
        if (str.equals("ok")) {
            Map map2 = (Map) map.get("d");
            if (map2.containsKey("w")) {
                this.zznaz.zza((List<String>) (List) map2.get("w"), this.zznbg.zznbs);
            }
        }
        if (((zzefl) this.zznaz.zznan.get(this.zznbg.zzbxd())) != this.zznbg) {
            return;
        }
        if (!str.equals("ok")) {
            zzefl unused = this.zznaz.zza(this.zznbg.zzbxd());
            this.zznbg.zznbr.zzbc(str, (String) map.get("d"));
            return;
        }
        this.zznbg.zznbr.zzbc((String) null, (String) null);
    }
}
