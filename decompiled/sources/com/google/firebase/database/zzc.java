package com.google.firebase.database;

import com.google.android.gms.internal.zzenn;
import com.google.android.gms.internal.zzepa;
import com.google.firebase.database.DatabaseReference;

final class zzc implements Runnable {
    private /* synthetic */ zzenn zzmwh;
    private /* synthetic */ zzepa zzmwi;
    private /* synthetic */ DatabaseReference zzmwj;

    zzc(DatabaseReference databaseReference, zzenn zzenn, zzepa zzepa) {
        this.zzmwj = databaseReference;
        this.zzmwh = zzenn;
        this.zzmwi = zzepa;
    }

    public final void run() {
        this.zzmwj.zzmwt.zza(this.zzmwj.zzmxa, this.zzmwh, (DatabaseReference.CompletionListener) this.zzmwi.zzcdp());
    }
}
