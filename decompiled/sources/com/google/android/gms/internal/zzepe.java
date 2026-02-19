package com.google.android.gms.internal;

import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

final class zzepe implements DatabaseReference.CompletionListener {
    private /* synthetic */ TaskCompletionSource zzghr;

    zzepe(TaskCompletionSource taskCompletionSource) {
        this.zzghr = taskCompletionSource;
    }

    public final void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
        if (databaseError != null) {
            this.zzghr.setException(databaseError.toException());
        } else {
            this.zzghr.setResult(null);
        }
    }
}
