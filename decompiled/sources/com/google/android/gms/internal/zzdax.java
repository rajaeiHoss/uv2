package com.google.android.gms.internal;

import android.os.RemoteException;
import java.util.Map;

final class zzdax implements zzdgb {
    private /* synthetic */ zzdav zzkxw;

    private zzdax(zzdav zzdav) {
        this.zzkxw = zzdav;
    }

    /* synthetic */ zzdax(zzdav zzdav, zzdaw zzdaw) {
        this(zzdav);
    }

    public final Object zze(String str, Map<String, Object> map) {
        try {
            this.zzkxw.zzkwc.zzf(str, map);
            return null;
        } catch (RemoteException e) {
            String valueOf = String.valueOf(e.getMessage());
            zzdal.e(valueOf.length() != 0 ? "Error calling customEvaluator proxy:".concat(valueOf) : new String("Error calling customEvaluator proxy:"));
            return null;
        }
    }
}
