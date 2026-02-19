package com.google.firebase.auth;

import com.google.firebase.auth.FirebaseAuth;

final class zzl implements Runnable {
    private /* synthetic */ FirebaseAuth zzmpm;

    zzl(FirebaseAuth firebaseAuth) {
        this.zzmpm = firebaseAuth;
    }

    public final void run() {
        for (FirebaseAuth.AuthStateListener onAuthStateChanged : this.zzmpm.zzmpc) {
            onAuthStateChanged.onAuthStateChanged(this.zzmpm);
        }
    }
}
