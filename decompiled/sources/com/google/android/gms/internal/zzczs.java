package com.google.android.gms.internal;

import android.content.Context;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class zzczs implements zzczy {
    private static final Object zzkmq = new Object();
    private static zzczs zzkwe;
    private static final Set<String> zzkwh = new HashSet(Arrays.asList(new String[]{"GET", "HEAD", "POST", "PUT"}));
    private zzdau zzkwf;
    private zzczz zzkwg;

    private zzczs(Context context) {
        this(zzdaa.zzer(context), new zzdbc());
    }

    private zzczs(zzczz zzczz, zzdau zzdau) {
        this.zzkwg = zzczz;
        this.zzkwf = zzdau;
    }

    public static zzczy zzeq(Context context) {
        zzczs zzczs;
        synchronized (zzkmq) {
            if (zzkwe == null) {
                zzkwe = new zzczs(context);
            }
            zzczs = zzkwe;
        }
        return zzczs;
    }

    public final void dispatch() {
        zzdbe.zzbje().dispatch();
    }

    public final boolean zza(String str, String str2, String str3, Map<String, String> map, String str4) {
        String str5;
        if (str2 != null && !zzkwh.contains(str2)) {
            str5 = String.format("Unsupport http method %s. Drop the hit.", new Object[]{str2});
        } else if (zzdat.zzbja().isPreview() || this.zzkwf.zzaas()) {
            this.zzkwg.zzb(str, str2, str3, map, str4);
            return true;
        } else {
            str5 = "Too many hits sent too quickly (rate throttled).";
        }
        zzdal.zzcz(str5);
        return false;
    }

    public final boolean zzax(String str, String str2) {
        return zza(str, (String) null, str2, (Map<String, String>) null, (String) null);
    }

    public final boolean zzlr(String str) {
        return zza(str, (String) null, (String) null, (Map<String, String>) null, (String) null);
    }
}
