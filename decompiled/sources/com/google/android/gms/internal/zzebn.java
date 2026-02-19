package com.google.android.gms.internal;

import com.google.firebase.auth.PhoneAuthProvider;

final class zzebn implements zzebq {
    private /* synthetic */ String zzmsc;

    zzebn(zzebk zzebk, String str) {
        this.zzmsc = str;
    }

    public final void zza(PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, Object... objArr) {
        onVerificationStateChangedCallbacks.onCodeAutoRetrievalTimeOut(this.zzmsc);
    }
}
