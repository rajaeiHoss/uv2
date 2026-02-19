package com.google.android.gms.internal;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

final class zzehj implements Runnable {
    private /* synthetic */ zzehy zznfy;
    private /* synthetic */ DatabaseError zznfz;
    private /* synthetic */ DataSnapshot zznga;

    zzehj(zzegx zzegx, zzehy zzehy, DatabaseError databaseError, DataSnapshot dataSnapshot) {
        this.zznfy = zzehy;
        this.zznfz = databaseError;
        this.zznga = dataSnapshot;
    }

    public final void run() {
        this.zznfy.zzngm.onComplete(this.zznfz, false, this.zznga);
    }
}
