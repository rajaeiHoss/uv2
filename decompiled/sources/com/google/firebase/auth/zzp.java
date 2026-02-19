package com.google.firebase.auth;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

final class zzp implements Continuation<GetTokenResult, Task<Void>> {
    private /* synthetic */ FirebaseUser zzmps;
    private /* synthetic */ ActionCodeSettings zzmpt;

    zzp(FirebaseUser firebaseUser, ActionCodeSettings actionCodeSettings) {
        this.zzmps = firebaseUser;
        this.zzmpt = actionCodeSettings;
    }

    public final Task<Void> then(Task<GetTokenResult> task) throws Exception {
        return FirebaseAuth.getInstance(this.zzmps.zzbtl()).zza(this.zzmpt, task.getResult().getToken());
    }
}
