package com.google.firebase.auth;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.internal.zzc;

final class zzk implements Runnable {
    private /* synthetic */ FirebaseAuth zzmpm;
    private /* synthetic */ zzc zzmpo;

    zzk(FirebaseAuth firebaseAuth, zzc zzc) {
        this.zzmpm = firebaseAuth;
        this.zzmpo = zzc;
    }

    public final void run() {
        this.zzmpm.zzmpb.zza(this.zzmpo);
        for (FirebaseAuth.IdTokenListener onIdTokenChanged : this.zzmpm.zzmmu) {
            onIdTokenChanged.onIdTokenChanged(this.zzmpm);
        }
    }
}
