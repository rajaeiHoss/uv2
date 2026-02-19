package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.ads.internal.gmsg.zzt;
import java.util.Map;

final class zzaos implements zzt<zzaof> {
    private /* synthetic */ zzaor zzdrm;

    zzaos(zzaor zzaor) {
        this.zzdrm = zzaor;
    }

    public final void zza(zzaof zzaof, Map<String, String> map) {
        if (map != null) {
            String str = (String) map.get("height");
            if (!TextUtils.isEmpty(str)) {
                try {
                    int parseInt = Integer.parseInt(str);
                    synchronized (this.zzdrm) {
                        if (this.zzdrm.zzdre != parseInt) {
                            int unused = this.zzdrm.zzdre = parseInt;
                            this.zzdrm.requestLayout();
                        }
                    }
                } catch (Exception e) {
                    zzahw.zzc("Exception occurred while getting webview content height", e);
                }
            }
        }
    }
}
