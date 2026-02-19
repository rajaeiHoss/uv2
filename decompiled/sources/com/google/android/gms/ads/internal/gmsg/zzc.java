package com.google.android.gms.ads.internal.gmsg;

import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzaof;
import com.google.android.gms.internal.zzov;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.android.gms.plus.PlusShare;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Map;

@zzabh
public final class zzc implements zzt<zzaof> {
    public final void zza(zzaof zzaof, Map<String, String> map) {
        String str = (String) map.get("action");
        if ("tick".equals(str)) {
            String str2 = (String) map.get(PlusShare.KEY_CALL_TO_ACTION_LABEL);
            String str3 = (String) map.get("start_label");
            String str4 = (String) map.get(AppMeasurement.Param.TIMESTAMP);
            if (TextUtils.isEmpty(str2)) {
                zzahw.zzcz("No label given for CSI tick.");
            } else if (TextUtils.isEmpty(str4)) {
                zzahw.zzcz("No timestamp given for CSI tick.");
            } else {
                try {
                    long elapsedRealtime = zzbt.zzes().elapsedRealtime() + (Long.parseLong(str4) - zzbt.zzes().currentTimeMillis());
                    if (TextUtils.isEmpty(str3)) {
                        str3 = "native:view_load";
                    }
                    zzaof.zztk().zza(str2, str3, elapsedRealtime);
                } catch (NumberFormatException e) {
                    zzahw.zzc("Malformed timestamp for CSI tick.", e);
                }
            }
        } else if ("experiment".equals(str)) {
            String str5 = (String) map.get(FirebaseAnalytics.Param.VALUE);
            if (TextUtils.isEmpty(str5)) {
                zzahw.zzcz("No value given for CSI experiment.");
                return;
            }
            zzov zzjn = zzaof.zztk().zzjn();
            if (zzjn == null) {
                zzahw.zzcz("No ticker for WebView, dropping experiment ID.");
            } else {
                zzjn.zzf("e", str5);
            }
        } else if ("extra".equals(str)) {
            String str6 = (String) map.get("name");
            String str7 = (String) map.get(FirebaseAnalytics.Param.VALUE);
            if (TextUtils.isEmpty(str7)) {
                zzahw.zzcz("No value given for CSI extra.");
            } else if (TextUtils.isEmpty(str6)) {
                zzahw.zzcz("No name given for CSI extra.");
            } else {
                zzov zzjn2 = zzaof.zztk().zzjn();
                if (zzjn2 == null) {
                    zzahw.zzcz("No ticker for WebView, dropping extra parameter.");
                } else {
                    zzjn2.zzf(str6, str7);
                }
            }
        }
    }
}
