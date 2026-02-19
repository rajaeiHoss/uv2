package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.ads.internal.gmsg.zzt;
import com.google.android.gms.ads.internal.zzbt;
import java.util.Map;

@zzabh
public final class zzanu implements zzt<zzann> {
    public final void zza(zzann zzann, Map<String, String> map) {
        if (((Boolean) zzlc.zzio().zzd(zzoi.zzbqx)).booleanValue()) {
            zzaou zzth = zzann.zzth();
            if (zzth == null) {
                try {
                    zzaou zzaou = new zzaou(zzann, Float.parseFloat(map.get("duration")), "1".equals(map.get("customControlsAllowed")), "1".equals(map.get("clickToExpandAllowed")));
                    zzann.zza(zzaou);
                    zzth = zzaou;
                } catch (NullPointerException | NumberFormatException e) {
                    zzahw.zzb("Unable to parse videoMeta message.", e);
                    zzbt.zzep().zza(e, "VideoMetaGmsgHandler.onGmsg");
                    return;
                }
            }
            boolean equals = "1".equals(map.get("muted"));
            float parseFloat = Float.parseFloat(map.get("currentTime"));
            int parseInt = Integer.parseInt(map.get("playbackState"));
            if (parseInt < 0 || 3 < parseInt) {
                parseInt = 0;
            }
            String str = (String) map.get("aspectRatio");
            float parseFloat2 = TextUtils.isEmpty(str) ? 0.0f : Float.parseFloat(str);
            if (zzahw.zzae(3)) {
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 79);
                sb.append("Video Meta GMSG: isMuted : ");
                sb.append(equals);
                sb.append(" , playbackState : ");
                sb.append(parseInt);
                sb.append(" , aspectRatio : ");
                sb.append(str);
                zzahw.zzby(sb.toString());
            }
            zzth.zza(parseFloat, parseInt, equals, parseFloat2);
        }
    }
}
