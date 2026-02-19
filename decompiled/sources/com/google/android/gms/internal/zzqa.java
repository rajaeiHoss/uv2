package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.ads.internal.gmsg.zzt;
import java.lang.ref.WeakReference;
import java.util.Map;

public final class zzqa implements zzt<Object> {
    private final WeakReference<zzpw> zzapr;
    private final String zzarn;

    public zzqa(zzpw zzpw, String str) {
        this.zzapr = new WeakReference<>(zzpw);
        this.zzarn = str;
    }

    public final void zza(Object obj, Map<String, String> map) {
        zzpw zzpw;
        String str = map.get("ads_id");
        String str2 = map.get("eventName");
        if (!TextUtils.isEmpty(str) && this.zzarn.equals(str)) {
            try {
                Integer.parseInt(map.get("eventType"));
            } catch (Exception e) {
                zzahw.zzb("Parse Scion log event type error", e);
            }
            if ("_ai".equals(str2)) {
                zzpw zzpw2 = (zzpw) this.zzapr.get();
                if (zzpw2 != null) {
                    zzpw2.zzbx();
                }
            } else if ("_ac".equals(str2) && (zzpw = (zzpw) this.zzapr.get()) != null) {
                zzpw.zzby();
            }
        }
    }
}
