package com.google.firebase.auth.internal;

import com.google.firebase.auth.SignInMethodQueryResult;
import java.util.List;

public final class zzp implements SignInMethodQueryResult {
    private final List<String> zzmsj;

    public zzp(List<String> list) {
        this.zzmsj = list;
    }

    public final List<String> getSignInMethods() {
        return this.zzmsj;
    }
}
