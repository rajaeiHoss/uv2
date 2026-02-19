package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import java.util.Map;

final class zzdzd implements zzdzg {
    private final int zzmqk;
    private final int zzmql;
    private final Map<String, Integer> zzmqm;
    private final boolean zzmqn;

    public zzdzd(int i, int i2, Map<String, Integer> map, boolean z) {
        this.zzmqk = i;
        this.zzmql = i2;
        this.zzmqm = (Map) zzbq.checkNotNull(map);
        this.zzmqn = z;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x000e, code lost:
        r5 = r4.zzmqm.get(r5.zzbtt());
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzd(com.google.android.gms.internal.zzdzf r5) {
        /*
            r4 = this;
            boolean r0 = r4.zzmqn
            r1 = 1
            if (r0 != 0) goto L_0x0006
            return r1
        L_0x0006:
            int r0 = r4.zzmql
            int r2 = r4.zzmqk
            r3 = 0
            if (r0 > r2) goto L_0x000e
            return r3
        L_0x000e:
            java.util.Map<java.lang.String, java.lang.Integer> r0 = r4.zzmqm
            java.lang.String r5 = r5.zzbtt()
            java.lang.Object r5 = r0.get(r5)
            java.lang.Integer r5 = (java.lang.Integer) r5
            if (r5 != 0) goto L_0x001d
            return r3
        L_0x001d:
            int r5 = r5.intValue()
            int r0 = r4.zzmqk
            if (r5 <= r0) goto L_0x0026
            return r1
        L_0x0026:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzdzd.zzd(com.google.android.gms.internal.zzdzf):boolean");
    }
}
