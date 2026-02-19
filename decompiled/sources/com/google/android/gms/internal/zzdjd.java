package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class zzdjd {
    private String zzfli = "";
    private final List<zzdjh> zzlcb = new ArrayList();
    private final Map<String, zzdje> zzlcc = new HashMap();
    private int zzlcd = 0;

    public final zzdjd zza(zzdjh zzdjh) {
        this.zzlcb.add(zzdjh);
        return this;
    }

    public final zzdjd zzb(zzdje zzdje) {
        this.zzlcc.put(zzdje.zzbkd().get("instance_name").toString(), zzdje);
        return this;
    }

    public final zzdjc zzbkc() {
        return new zzdjc(this.zzlcb, this.zzlcc, this.zzfli, 0);
    }

    public final zzdjd zzng(String str) {
        this.zzfli = str;
        return this;
    }
}
