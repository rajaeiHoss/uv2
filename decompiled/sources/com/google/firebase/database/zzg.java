package com.google.firebase.database;

final class zzg implements Runnable {
    private /* synthetic */ FirebaseDatabase zzmwu;

    zzg(FirebaseDatabase firebaseDatabase) {
        this.zzmwu = firebaseDatabase;
    }

    public final void run() {
        this.zzmwu.zzmwt.purgeOutstandingWrites();
    }
}
