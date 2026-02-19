package com.google.android.gms.internal;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.internal.api.FirebaseNoSignedInUserException;

final class zzedb implements OnFailureListener {
    private /* synthetic */ zzege zzmxp;

    zzedb(zzeda zzeda, zzege zzege) {
        this.zzmxp = zzege;
    }

    public final void onFailure(Exception exc) {
        if ((exc instanceof FirebaseApiNotAvailableException) || (exc instanceof FirebaseNoSignedInUserException)) {
            this.zzmxp.zzpr((String) null);
        } else {
            this.zzmxp.onError(exc.getMessage());
        }
    }
}
