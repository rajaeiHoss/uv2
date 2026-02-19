package com.google.firebase.database;

import com.google.android.gms.internal.zzegi;
import com.google.android.gms.internal.zzepa;
import com.google.firebase.database.DatabaseReference;
import java.util.Map;

final class zze implements Runnable {
    private /* synthetic */ zzepa zzmwi;
    private /* synthetic */ DatabaseReference zzmwj;
    private /* synthetic */ zzegi zzmwl;
    private /* synthetic */ Map zzmwm;

    zze(DatabaseReference databaseReference, zzegi zzegi, zzepa zzepa, Map map) {
        this.zzmwj = databaseReference;
        this.zzmwl = zzegi;
        this.zzmwi = zzepa;
        this.zzmwm = map;
    }

    public final void run() {
        this.zzmwj.zzmwt.zza(this.zzmwj.zzmxa, this.zzmwl, (DatabaseReference.CompletionListener) this.zzmwi.zzcdp(), (Map<String, Object>) this.zzmwm);
    }
}
