package com.google.android.gms.internal;

import java.util.Collections;
import java.util.List;

public final class zzemx {
    private final List<zzegu> zzmzh;
    private final List<String> zzmzi;

    private zzemx(List<zzegu> list, List<String> list2) {
        if (list.size() == list2.size() - 1) {
            this.zzmzh = list;
            this.zzmzi = list2;
            return;
        }
        throw new IllegalArgumentException("Number of posts need to be n-1 for n hashes in CompoundHash");
    }

    /* access modifiers changed from: private */
    public static void zza(zzenn zzenn, zzemz zzemz) {
        if (zzenn.zzccd()) {
            zzemz.zzb((zzeni<?>) (zzeni) zzenn);
        } else if (zzenn.isEmpty()) {
            throw new IllegalArgumentException("Can't calculate hash on empty node!");
        } else if (zzenn instanceof zzems) {
            ((zzems) zzenn).zza(new zzemy(zzemz), true);
        } else {
            String valueOf = String.valueOf(zzenn);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 33);
            sb.append("Expected children node, but got: ");
            sb.append(valueOf);
            throw new IllegalStateException(sb.toString());
        }
    }

    public static zzemx zzh(zzenn zzenn) {
        zzena zzena = new zzena(zzenn);
        if (zzenn.isEmpty()) {
            return new zzemx(Collections.emptyList(), Collections.singletonList(""));
        }
        zzemz zzemz = new zzemz(zzena);
        zza(zzenn, zzemz);
        zzemz.zzccm();
        return new zzemx(zzemz.zznom, zzemz.zznon);
    }

    public final List<zzegu> zzbwi() {
        return Collections.unmodifiableList(this.zzmzh);
    }

    public final List<String> zzbwj() {
        return Collections.unmodifiableList(this.zzmzi);
    }
}
