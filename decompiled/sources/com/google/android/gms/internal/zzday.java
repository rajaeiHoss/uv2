package com.google.android.gms.internal;

import android.os.RemoteException;
import java.util.Map;

final class zzday implements zzdgb {
    private /* synthetic */ zzdav zzkxw;

    private zzday(zzdav zzdav) {
        this.zzkxw = zzdav;
    }

    /* synthetic */ zzday(zzdav zzdav, zzdaw zzdaw) {
        this(zzdav);
    }

    public final Object zze(String str, Map<String, Object> map) {
        try {
            return this.zzkxw.zzkwc.zzg(str, map);
        } catch (RemoteException e) {
            String valueOf = String.valueOf(e.getMessage());
            zzdal.e(valueOf.length() != 0 ? "Error calling customEvaluator proxy:".concat(valueOf) : new String("Error calling customEvaluator proxy:"));
            return null;
        }
    }
}
