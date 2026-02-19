package com.google.firebase.auth;

import com.google.android.gms.internal.zzbgl;

public abstract class AuthCredential extends zzbgl {
    AuthCredential() {
    }

    public abstract String getProvider();

    public abstract String getSignInMethod();
}
