package com.google.firebase.auth;

import com.google.firebase.auth.FirebaseAuth;

final class zzi implements Runnable {
    private /* synthetic */ FirebaseAuth.IdTokenListener zzmpl;
    private /* synthetic */ FirebaseAuth zzmpm;

    zzi(FirebaseAuth firebaseAuth, FirebaseAuth.IdTokenListener idTokenListener) {
        this.zzmpm = firebaseAuth;
        this.zzmpl = idTokenListener;
    }

    public final void run() {
        this.zzmpl.onIdTokenChanged(this.zzmpm);
    }
}
