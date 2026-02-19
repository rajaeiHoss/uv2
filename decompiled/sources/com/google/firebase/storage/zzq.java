package com.google.firebase.storage;

import com.google.android.gms.tasks.OnSuccessListener;

final class zzq<TContinuationResult> implements OnSuccessListener<TContinuationResult> {
    private /* synthetic */ zzp<?, TContinuationResult> zzovm;

    zzq(zzp<?, TContinuationResult> zzp) {
        this.zzovm = zzp;
    }

    public final void onSuccess(TContinuationResult tcontinuationresult) {
        this.zzovm.zzghr.setResult(tcontinuationresult);
    }
}
