package com.google.android.gms.internal;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Transaction;

final class zzehd implements Runnable {
    private /* synthetic */ Transaction.Handler zzmwn;
    private /* synthetic */ DatabaseError zznft;
    private /* synthetic */ DataSnapshot zznfu;

    zzehd(zzegx zzegx, Transaction.Handler handler, DatabaseError databaseError, DataSnapshot dataSnapshot) {
        this.zzmwn = handler;
        this.zznft = databaseError;
        this.zznfu = dataSnapshot;
    }

    public final void run() {
        this.zzmwn.onComplete(this.zznft, false, this.zznfu);
    }
}
