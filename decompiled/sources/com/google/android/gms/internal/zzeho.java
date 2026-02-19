package com.google.android.gms.internal;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

final class zzeho implements Runnable {
    private /* synthetic */ zzehy zznfy;
    private /* synthetic */ DatabaseError zzngb;

    zzeho(zzegx zzegx, zzehy zzehy, DatabaseError databaseError) {
        this.zznfy = zzehy;
        this.zzngb = databaseError;
    }

    public final void run() {
        this.zznfy.zzngm.onComplete(this.zzngb, false, (DataSnapshot) null);
    }
}
