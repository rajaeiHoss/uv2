package com.google.firebase.auth;

import com.google.firebase.auth.FirebaseAuth;

final class zzj implements Runnable {
    private /* synthetic */ FirebaseAuth zzmpm;
    private /* synthetic */ FirebaseAuth.AuthStateListener zzmpn;

    zzj(FirebaseAuth firebaseAuth, FirebaseAuth.AuthStateListener authStateListener) {
        this.zzmpm = firebaseAuth;
        this.zzmpn = authStateListener;
    }

    public final void run() {
        this.zzmpn.onAuthStateChanged(this.zzmpm);
    }
}
