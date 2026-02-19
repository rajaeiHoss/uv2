package com.google.firebase.database;

import com.google.android.gms.internal.zzemq;
import com.google.android.gms.internal.zzenn;
import com.google.android.gms.internal.zzepa;
import com.google.firebase.database.DatabaseReference;

final class zzd implements Runnable {
    private /* synthetic */ zzepa zzmwi;
    private /* synthetic */ DatabaseReference zzmwj;
    private /* synthetic */ zzenn zzmwk;

    zzd(DatabaseReference databaseReference, zzenn zzenn, zzepa zzepa) {
        this.zzmwj = databaseReference;
        this.zzmwk = zzenn;
        this.zzmwi = zzepa;
    }

    public final void run() {
        this.zzmwj.zzmwt.zza(this.zzmwj.zzmxa.zza(zzemq.zzcby()), this.zzmwk, (DatabaseReference.CompletionListener) this.zzmwi.zzcdp());
    }
}
