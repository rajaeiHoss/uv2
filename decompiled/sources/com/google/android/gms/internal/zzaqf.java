package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.ads.internal.gmsg.zzt;
import java.util.Map;

final class zzaqf implements zzt<zzaof> {
    private /* synthetic */ zzaqe zzdst;

    zzaqf(zzaqe zzaqe) {
        this.zzdst = zzaqe;
    }

    public final void zza(zzaof zzaof, Map<String, String> map) {
        if (map != null) {
            String str = (String) map.get("height");
            if (!TextUtils.isEmpty(str)) {
                try {
                    int parseInt = Integer.parseInt(str);
                    synchronized (this.zzdst) {
                        if (this.zzdst.zzdre != parseInt) {
                            int unused = this.zzdst.zzdre = parseInt;
                            this.zzdst.requestLayout();
                        }
                    }
                } catch (Exception e) {
                    zzahw.zzc("Exception occurred while getting webview content height", e);
                }
            }
        }
    }
}
