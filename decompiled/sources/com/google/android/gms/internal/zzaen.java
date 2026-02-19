package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbt;
import org.json.JSONObject;

@zzabh
public final class zzaen implements zzadr {
    private zzux<JSONObject, JSONObject> zzczf;
    private zzux<JSONObject, JSONObject> zzczk;

    public zzaen(Context context) {
        this.zzczk = zzbt.zzez().zzb(context, zzala.zzse()).zza("google.afma.request.getAdDictionary", zzvc.zzcgm, zzvc.zzcgm);
        this.zzczf = zzbt.zzez().zzb(context, zzala.zzse()).zza("google.afma.sdkConstants.getSdkConstants", zzvc.zzcgm, zzvc.zzcgm);
    }

    public final zzux<JSONObject, JSONObject> zzoe() {
        return this.zzczk;
    }

    public final zzux<JSONObject, JSONObject> zzof() {
        return this.zzczf;
    }
}
