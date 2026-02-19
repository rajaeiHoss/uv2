package com.google.firebase.auth;

import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.internal.zzt;

final class zzn implements zzt {
    private /* synthetic */ FirebaseAuth zzmpm;
    private /* synthetic */ FirebaseUser zzmpp;

    zzn(FirebaseAuth firebaseAuth, FirebaseUser firebaseUser) {
        this.zzmpm = firebaseAuth;
        this.zzmpp = firebaseUser;
    }

    public final void onError(Status status) {
        if (status.getStatusCode() == 17011 || status.getStatusCode() == 17021 || status.getStatusCode() == 17005) {
            this.zzmpm.signOut();
        }
    }

    public final void zzbtk() {
        if (this.zzmpm.zzmpe.getUid().equalsIgnoreCase(this.zzmpp.getUid())) {
            this.zzmpm.zzbti();
        }
    }
}
