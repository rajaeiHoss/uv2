package com.google.android.gms.internal;

import java.util.Map;

final class zzefb implements zzefh {
    private /* synthetic */ zzefo zznbc;

    zzefb(zzeey zzeey, zzefo zzefo) {
        this.zznbc = zzefo;
    }

    public final void zzal(Map<String, Object> map) {
        String str = (String) map.get("s");
        String str2 = null;
        if (!str.equals("ok")) {
            str2 = (String) map.get("d");
        } else {
            str = null;
        }
        zzefo zzefo = this.zznbc;
        if (zzefo != null) {
            zzefo.zzbc(str, str2);
        }
    }
}
