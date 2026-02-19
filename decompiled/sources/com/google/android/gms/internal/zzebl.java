package com.google.android.gms.internal;

import com.google.firebase.auth.PhoneAuthProvider;

final class zzebl implements zzebq {
    private /* synthetic */ String zzmsc;

    zzebl(zzebk zzebk, String str) {
        this.zzmsc = str;
    }

    public final void zza(PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, Object... objArr) {
        onVerificationStateChangedCallbacks.onCodeSent(this.zzmsc, PhoneAuthProvider.ForceResendingToken.zzbtp());
    }
}
