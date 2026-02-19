package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class zzemd {
    private final Map<zzemq, zzelj> zznnh = new HashMap();

    public final void zza(zzelj zzelj) {
        zzelm zzcan = zzelj.zzcan();
        zzemq zzcam = zzelj.zzcam();
        if (this.zznnh.containsKey(zzcam)) {
            zzelj zzelj2 = this.zznnh.get(zzcam);
            zzelm zzcan2 = zzelj2.zzcan();
            if (zzcan == zzelm.CHILD_ADDED && zzcan2 == zzelm.CHILD_REMOVED) {
                this.zznnh.put(zzelj.zzcam(), zzelj.zza(zzcam, zzelj.zzcak(), zzelj2.zzcak()));
            } else if (zzcan == zzelm.CHILD_REMOVED && zzcan2 == zzelm.CHILD_ADDED) {
                this.zznnh.remove(zzcam);
            } else if (zzcan == zzelm.CHILD_REMOVED && zzcan2 == zzelm.CHILD_CHANGED) {
                this.zznnh.put(zzcam, zzelj.zzb(zzcam, zzelj2.zzcap()));
            } else if (zzcan == zzelm.CHILD_CHANGED && zzcan2 == zzelm.CHILD_ADDED) {
                this.zznnh.put(zzcam, zzelj.zza(zzcam, zzelj.zzcak()));
            } else if (zzcan == zzelm.CHILD_CHANGED && zzcan2 == zzelm.CHILD_CHANGED) {
                this.zznnh.put(zzcam, zzelj.zza(zzcam, zzelj.zzcak(), zzelj2.zzcap()));
            } else {
                String valueOf = String.valueOf(zzelj);
                String valueOf2 = String.valueOf(zzelj2);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 48 + String.valueOf(valueOf2).length());
                sb.append("Illegal combination of changes: ");
                sb.append(valueOf);
                sb.append(" occurred after ");
                sb.append(valueOf2);
                throw new IllegalStateException(sb.toString());
            }
        } else {
            this.zznnh.put(zzelj.zzcam(), zzelj);
        }
    }

    public final List<zzelj> zzcbp() {
        return new ArrayList(this.zznnh.values());
    }
}
