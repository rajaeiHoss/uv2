package com.google.firebase.database;

import com.google.android.gms.internal.zzegr;

final class zzr implements Runnable {
    private /* synthetic */ Query zzmxh;
    private /* synthetic */ zzegr zzmxj;

    zzr(Query query, zzegr zzegr) {
        this.zzmxh = query;
        this.zzmxj = zzegr;
    }

    public final void run() {
        this.zzmxh.zzmwt.zzf(this.zzmxj);
    }
}
