package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzbi;
import com.google.android.gms.internal.zzbt;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public abstract class zzeg extends zzbr {
    private static final String zzkpn = zzbi.ARG0.toString();
    private static final String zzkrk = zzbi.ARG1.toString();

    public zzeg(String str) {
        super(str, zzkpn, zzkrk);
    }

    /* access modifiers changed from: protected */
    public abstract boolean zza(zzbt zzbt, zzbt zzbt2, Map<String, zzbt> map);

    public final boolean zzbfh() {
        return true;
    }

    public final /* bridge */ /* synthetic */ String zzbgp() {
        return super.zzbgp();
    }

    public final /* bridge */ /* synthetic */ Set zzbgq() {
        return super.zzbgq();
    }

    public final zzbt zzx(Map<String, zzbt> map) {
        boolean z;
        Iterator<zzbt> it = map.values().iterator();
        while (true) {
            z = false;
            if (it.hasNext()) {
                if (it.next() == zzgk.zzbil()) {
                    break;
                }
            } else {
                zzbt zzbt = map.get(zzkpn);
                zzbt zzbt2 = map.get(zzkrk);
                if (zzbt != null && zzbt2 != null) {
                    z = zza(zzbt, zzbt2, map);
                }
            }
        }
        return zzgk.zzam(Boolean.valueOf(z));
    }
}
