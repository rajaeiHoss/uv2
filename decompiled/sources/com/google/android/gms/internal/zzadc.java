package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.js.zzaa;
import org.json.JSONObject;

final class zzadc implements Runnable {
    private /* synthetic */ zzada zzcve;
    final /* synthetic */ JSONObject zzcvf;
    final /* synthetic */ String zzcvg;

    zzadc(zzada zzada, JSONObject jSONObject, String str) {
        this.zzcve = zzada;
        this.zzcvf = jSONObject;
        this.zzcvg = str;
    }

    public final void run() {
        zzaa unused = this.zzcve.zzcvd = zzada.zzcql.zzb((zzcv) null);
        this.zzcve.zzcvd.zza(new zzadd(this), new zzade(this));
    }
}
