package com.google.android.gms.internal;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

final class zzehu implements Runnable {
    private /* synthetic */ DatabaseReference.CompletionListener zznfr;
    private /* synthetic */ DatabaseError zzngi;
    private /* synthetic */ DatabaseReference zzngj;

    zzehu(zzegx zzegx, DatabaseReference.CompletionListener completionListener, DatabaseError databaseError, DatabaseReference databaseReference) {
        this.zznfr = completionListener;
        this.zzngi = databaseError;
        this.zzngj = databaseReference;
    }

    public final void run() {
        this.zznfr.onComplete(this.zzngi, this.zzngj);
    }
}
