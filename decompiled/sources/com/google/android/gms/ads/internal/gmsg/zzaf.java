package com.google.android.gms.ads.internal.gmsg;

import android.text.TextUtils;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzagd;
import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzlc;
import com.google.android.gms.internal.zzoi;
import com.google.android.gms.measurement.AppMeasurement;
import java.util.Map;

@zzabh
public final class zzaf implements zzt<Object> {
    private final zzag zzccw;

    public zzaf(zzag zzag) {
        this.zzccw = zzag;
    }

    public final void zza(Object obj, Map<String, String> map) {
        String str = map.get("action");
        if ("grant".equals(str)) {
            zzagd zzagd = null;
            try {
                int parseInt = Integer.parseInt(map.get("amount"));
                String str2 = map.get(AppMeasurement.Param.TYPE);
                if (!TextUtils.isEmpty(str2)) {
                    zzagd = new zzagd(str2, parseInt);
                }
            } catch (NumberFormatException e) {
                zzahw.zzc("Unable to parse reward amount.", e);
            }
            this.zzccw.zzb(zzagd);
        } else if ("video_start".equals(str)) {
            this.zzccw.zzdl();
        } else if ("video_complete".equals(str)) {
            if (((Boolean) zzlc.zzio().zzd(zzoi.zzbos)).booleanValue()) {
                if (((Boolean) zzlc.zzio().zzd(zzoi.zzbos)).booleanValue()) {
                    this.zzccw.zzdm();
                }
            }
        }
    }
}
