package com.google.firebase.auth.internal;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.FirebaseNetworkException;

final class zzs implements OnFailureListener {
    private /* synthetic */ zzr zzmub;

    zzs(zzr zzr) {
        this.zzmub = zzr;
    }

    public final void onFailure(Exception exc) {
        if (exc instanceof FirebaseNetworkException) {
            zzq.zzehr.zza("Failure to refresh token; scheduling refresh after failure", new Object[0]);
            this.zzmub.zzmua.zzbuo();
        }
    }
}
