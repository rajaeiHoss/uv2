package com.google.firebase.database;

import com.google.android.gms.internal.zzegr;

final class zzq implements Runnable {
    private /* synthetic */ Query zzmxh;
    private /* synthetic */ zzegr zzmxi;

    zzq(Query query, zzegr zzegr) {
        this.zzmxh = query;
        this.zzmxi = zzegr;
    }

    public final void run() {
        this.zzmxh.zzmwt.zze(this.zzmxi);
    }
}
