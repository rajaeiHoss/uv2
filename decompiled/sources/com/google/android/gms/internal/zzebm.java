package com.google.android.gms.internal;

import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

final class zzebm implements zzebq {
    private /* synthetic */ PhoneAuthCredential zzmsd;

    zzebm(zzebk zzebk, PhoneAuthCredential phoneAuthCredential) {
        this.zzmsd = phoneAuthCredential;
    }

    public final void zza(PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, Object... objArr) {
        onVerificationStateChangedCallbacks.onVerificationCompleted(this.zzmsd);
    }
}
