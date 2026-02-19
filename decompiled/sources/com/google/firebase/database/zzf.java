package com.google.firebase.database;

import com.google.firebase.database.Transaction;

final class zzf implements Runnable {
    private /* synthetic */ DatabaseReference zzmwj;
    private /* synthetic */ Transaction.Handler zzmwn;
    private /* synthetic */ boolean zzmwo;

    zzf(DatabaseReference databaseReference, Transaction.Handler handler, boolean z) {
        this.zzmwj = databaseReference;
        this.zzmwn = handler;
        this.zzmwo = z;
    }

    public final void run() {
        this.zzmwj.zzmwt.zza(this.zzmwj.zzmxa, this.zzmwn, this.zzmwo);
    }
}
