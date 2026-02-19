package com.google.android.gms.internal;

import com.google.firebase.FirebaseApp;
import com.google.firebase.internal.zzc;

final class zzedd implements FirebaseApp.IdTokenListener {
    final /* synthetic */ zzegf zzmxq;
    private /* synthetic */ zzeda zzmxr;

    zzedd(zzeda zzeda, zzegf zzegf) {
        this.zzmxr = zzeda;
        this.zzmxq = zzegf;
    }

    public final void zzb(zzc zzc) {
        this.zzmxr.zzmxn.execute(new zzede(this, zzc));
    }
}
