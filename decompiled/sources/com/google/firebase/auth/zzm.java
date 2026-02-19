package com.google.firebase.auth;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzebw;
import com.google.firebase.auth.internal.zzab;

final class zzm implements zzab {
    private /* synthetic */ FirebaseAuth zzmpm;

    zzm(FirebaseAuth firebaseAuth) {
        this.zzmpm = firebaseAuth;
    }

    public final void onError(Status status) {
        int statusCode = status.getStatusCode();
        if (statusCode == 17011 || statusCode == 17021 || statusCode == 17005) {
            this.zzmpm.signOut();
        }
    }

    public final void zza(zzebw zzebw, FirebaseUser firebaseUser) {
        this.zzmpm.zza(firebaseUser, zzebw, true);
    }
}
