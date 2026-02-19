package com.google.firebase.database;

import com.google.android.gms.internal.zzenn;
import com.google.android.gms.internal.zzepa;
import com.google.firebase.database.DatabaseReference;

final class zzm implements Runnable {
    private /* synthetic */ zzenn zzmwh;
    private /* synthetic */ zzepa zzmwi;
    private /* synthetic */ OnDisconnect zzmxb;

    zzm(OnDisconnect onDisconnect, zzenn zzenn, zzepa zzepa) {
        this.zzmxb = onDisconnect;
        this.zzmwh = zzenn;
        this.zzmwi = zzepa;
    }

    public final void run() {
        this.zzmxb.zzmwt.zzb(this.zzmxb.zzmxa, this.zzmwh, (DatabaseReference.CompletionListener) this.zzmwi.zzcdp());
    }
}
