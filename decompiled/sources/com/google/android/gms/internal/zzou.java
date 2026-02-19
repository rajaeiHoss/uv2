package com.google.android.gms.internal;

import java.util.HashMap;
import java.util.Map;

@zzabh
public final class zzou {
    private final zzov zzanh;
    private final Map<String, zzot> zzbws = new HashMap();

    public zzou(zzov zzov) {
        this.zzanh = zzov;
    }

    public final void zza(String str, zzot zzot) {
        this.zzbws.put(str, zzot);
    }

    public final void zza(String str, String str2, long j) {
        zzov zzov = this.zzanh;
        zzot zzot = this.zzbws.get(str2);
        String[] strArr = {str};
        if (!(zzov == null || zzot == null)) {
            zzov.zza(zzot, j, strArr);
        }
        Map<String, zzot> map = this.zzbws;
        zzov zzov2 = this.zzanh;
        map.put(str, zzov2 == null ? null : zzov2.zzd(j));
    }

    public final zzov zzjn() {
        return this.zzanh;
    }
}
