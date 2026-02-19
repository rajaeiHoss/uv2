package com.google.firebase.storage;

import com.google.android.gms.tasks.OnFailureListener;

final class zzr implements OnFailureListener {
    private /* synthetic */ zzp<?, ?> zzovm;

    zzr(zzp<?, ?> zzp) {
        this.zzovm = zzp;
    }

    public final void onFailure(Exception exc) {
        this.zzovm.zzghr.setException(exc);
    }
}
