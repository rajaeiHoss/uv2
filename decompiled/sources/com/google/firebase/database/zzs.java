package com.google.firebase.database;

final class zzs implements Runnable {
    private /* synthetic */ Query zzmxh;
    private /* synthetic */ boolean zzmxk;

    zzs(Query query, boolean z) {
        this.zzmxh = query;
        this.zzmxk = z;
    }

    public final void run() {
        this.zzmxh.zzmwt.zza(this.zzmxh.zzbvi(), this.zzmxk);
    }
}
