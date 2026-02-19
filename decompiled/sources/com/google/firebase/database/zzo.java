package com.google.firebase.database;

import com.google.android.gms.internal.zzepa;
import com.google.firebase.database.DatabaseReference;

final class zzo implements Runnable {
    private /* synthetic */ zzepa zzmwi;
    private /* synthetic */ OnDisconnect zzmxb;

    zzo(OnDisconnect onDisconnect, zzepa zzepa) {
        this.zzmxb = onDisconnect;
        this.zzmwi = zzepa;
    }

    public final void run() {
        this.zzmxb.zzmwt.zza(this.zzmxb.zzmxa, (DatabaseReference.CompletionListener) this.zzmwi.zzcdp());
    }
}
