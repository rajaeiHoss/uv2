package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import org.json.JSONArray;
import org.json.JSONException;

public final class zzdgc extends zzdcr {
    private final int zzenu;
    private final zzdbb zzkxp;

    public zzdgc(int i, zzdbb zzdbb) {
        this.zzenu = i;
        this.zzkxp = zzdbb;
    }

    public final zzdjq<?> zza(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        boolean z = true;
        zzbq.checkArgument(true);
        if (zzdjqArr.length != 1) {
            z = false;
        }
        zzbq.checkArgument(z);
        zzbq.checkArgument(zzdjqArr[0] instanceof zzdkc);
        try {
            zzdco zzas = zzdic.zzas(new JSONArray((String) zzdjqArr[0].value()).getJSONArray(0));
            zzas.zza(this.zzkxp);
            return this.zzenu == 0 ? zzdjw.zzlcz : zzas.zzb(zzdbb, new zzdjq[0]);
        } catch (JSONException e) {
            zzdal.zzb("Unable to convert Custom Pixie to instruction", e);
            return zzdjw.zzlcz;
        }
    }
}
