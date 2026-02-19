package com.google.android.gms.internal;

import java.util.Map;

final class zzeff implements zzefh {
    private /* synthetic */ zzeey zznaz;

    zzeff(zzeey zzeey) {
        this.zznaz = zzeey;
    }

    public final void zzal(Map<String, Object> map) {
        String str = (String) map.get("s");
        if (!str.equals("ok")) {
            String str2 = (String) map.get("d");
            if (this.zznaz.zzmxz.zzcbu()) {
                zzemm zza = this.zznaz.zzmxz;
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 34 + String.valueOf(str2).length());
                sb.append("Failed to send stats: ");
                sb.append(str);
                sb.append(" (message: ");
                sb.append(str2);
                sb.append(")");
                zza.zzb(sb.toString(), (Throwable) null, new Object[0]);
            }
        }
    }
}
