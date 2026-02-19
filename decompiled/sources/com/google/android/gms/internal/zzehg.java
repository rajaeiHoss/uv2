package com.google.android.gms.internal;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

final class zzehg implements Runnable {
    private /* synthetic */ DataSnapshot zznfu;
    private /* synthetic */ zzehy zznfx;

    zzehg(zzehf zzehf, zzehy zzehy, DataSnapshot dataSnapshot) {
        this.zznfx = zzehy;
        this.zznfu = dataSnapshot;
    }

    public final void run() {
        this.zznfx.zzngm.onComplete((DatabaseError) null, true, this.zznfu);
    }
}
