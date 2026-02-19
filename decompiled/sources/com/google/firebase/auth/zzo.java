package com.google.firebase.auth;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

final class zzo implements Continuation<GetTokenResult, Task<Void>> {
    private /* synthetic */ FirebaseUser zzmps;

    zzo(FirebaseUser firebaseUser) {
        this.zzmps = firebaseUser;
    }

    public final Task<Void> then(Task<GetTokenResult> task) throws Exception {
        return FirebaseAuth.getInstance(this.zzmps.zzbtl()).zza((ActionCodeSettings) null, task.getResult().getToken());
    }
}
