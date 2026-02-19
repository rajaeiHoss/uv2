package com.google.firebase.auth;

public final class FirebaseAuthUserCollisionException extends FirebaseAuthException {
    private AuthCredential zzmpr;

    public FirebaseAuthUserCollisionException(String str, String str2) {
        super(str, str2);
        this.zzmpr = null;
    }

    public FirebaseAuthUserCollisionException(String str, String str2, AuthCredential authCredential) {
        super(str, str2);
        this.zzmpr = authCredential;
    }

    public final AuthCredential getUpdatedCredential() {
        return this.zzmpr;
    }
}
