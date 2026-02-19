package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.PhoneAuthProvider;

final class zzebo implements zzebq {
    private /* synthetic */ Status zzetp;

    zzebo(zzebk zzebk, Status status) {
        this.zzetp = status;
    }

    public final void zza(PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, Object... objArr) {
        onVerificationStateChangedCallbacks.onVerificationFailed(zzeaw.zzaw(this.zzetp));
    }
}
