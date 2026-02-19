package com.google.android.gms.internal;

import com.google.android.gms.awareness.snapshot.HeadphoneStateResult;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;

final class zzazl extends zzff<HeadphoneStateResult, zzazy> {
    zzazl(zzaze zzaze, PendingResult pendingResult) {
        super(pendingResult);
    }

    /* access modifiers changed from: protected */
    public final HeadphoneStateResult zza(zzazy zzazy) {
        return new zzazm(this, zzazy);
    }
}
