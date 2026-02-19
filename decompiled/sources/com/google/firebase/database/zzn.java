package com.google.firebase.database;

import com.google.android.gms.internal.zzegu;
import com.google.android.gms.internal.zzenn;
import com.google.android.gms.internal.zzepa;
import com.google.firebase.database.DatabaseReference;
import java.util.Map;

final class zzn implements Runnable {
    private /* synthetic */ zzepa zzmwi;
    private /* synthetic */ OnDisconnect zzmxb;
    private /* synthetic */ Map zzmxc;
    private /* synthetic */ Map zzmxd;

    zzn(OnDisconnect onDisconnect, Map map, zzepa zzepa, Map map2) {
        this.zzmxb = onDisconnect;
        this.zzmxc = map;
        this.zzmwi = zzepa;
        this.zzmxd = map2;
    }

    public final void run() {
        this.zzmxb.zzmwt.zza(this.zzmxb.zzmxa, (Map<zzegu, zzenn>) this.zzmxc, (DatabaseReference.CompletionListener) this.zzmwi.zzcdp(), (Map<String, Object>) this.zzmxd);
    }
}
