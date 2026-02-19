package com.google.firebase.auth;

import com.google.android.gms.internal.zzbgp;

public interface AuthResult extends zzbgp {
    AdditionalUserInfo getAdditionalUserInfo();

    FirebaseUser getUser();
}
