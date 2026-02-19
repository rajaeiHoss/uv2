package com.google.firebase.auth;

import android.app.Activity;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzbhf;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

public class PhoneAuthProvider {
    public static final String PHONE_SIGN_IN_METHOD = "phone";
    public static final String PROVIDER_ID = "phone";
    private FirebaseAuth zzmpz;

    public static class ForceResendingToken extends zzbgl {
        public static final Parcelable.Creator<ForceResendingToken> CREATOR = new zzc();

        ForceResendingToken() {
        }

        public static ForceResendingToken zzbtp() {
            return new ForceResendingToken();
        }

        public void writeToParcel(Parcel parcel, int i) {
            zzbgo.zzai(parcel, zzbgo.zze(parcel));
        }
    }

    public static abstract class OnVerificationStateChangedCallbacks {
        private static final zzbhf zzenl = new zzbhf("PhoneAuthProvider", new String[0]);

        public void onCodeAutoRetrievalTimeOut(String str) {
            zzenl.zze("Sms auto retrieval timed-out.", new Object[0]);
        }

        public void onCodeSent(String str, ForceResendingToken forceResendingToken) {
        }

        public abstract void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential);

        public abstract void onVerificationFailed(FirebaseException firebaseException);
    }

    private PhoneAuthProvider(FirebaseAuth firebaseAuth) {
        this.zzmpz = firebaseAuth;
    }

    public static PhoneAuthCredential getCredential(String str, String str2) {
        return new PhoneAuthCredential(str, str2, false, (String) null, true, (String) null);
    }

    public static PhoneAuthProvider getInstance() {
        return new PhoneAuthProvider(FirebaseAuth.getInstance(FirebaseApp.getInstance()));
    }

    public static PhoneAuthProvider getInstance(FirebaseAuth firebaseAuth) {
        return new PhoneAuthProvider(firebaseAuth);
    }

    private final void zza(String str, long j, TimeUnit timeUnit, Activity activity, Executor executor, OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, ForceResendingToken forceResendingToken) {
        this.zzmpz.zza(str, j, timeUnit, onVerificationStateChangedCallbacks, activity, executor, forceResendingToken != null);
    }

    public void verifyPhoneNumber(String str, long j, TimeUnit timeUnit, Activity activity, OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks) {
        zza(zzbq.zzgv(str), j, timeUnit, (Activity) zzbq.checkNotNull(activity), TaskExecutors.MAIN_THREAD, (OnVerificationStateChangedCallbacks) zzbq.checkNotNull(onVerificationStateChangedCallbacks), (ForceResendingToken) null);
    }

    public void verifyPhoneNumber(String str, long j, TimeUnit timeUnit, Activity activity, OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, ForceResendingToken forceResendingToken) {
        zza(zzbq.zzgv(str), j, timeUnit, (Activity) zzbq.checkNotNull(activity), TaskExecutors.MAIN_THREAD, (OnVerificationStateChangedCallbacks) zzbq.checkNotNull(onVerificationStateChangedCallbacks), forceResendingToken);
    }

    public void verifyPhoneNumber(String str, long j, TimeUnit timeUnit, Executor executor, OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks) {
        zza(zzbq.zzgv(str), j, timeUnit, (Activity) null, (Executor) zzbq.checkNotNull(executor), (OnVerificationStateChangedCallbacks) zzbq.checkNotNull(onVerificationStateChangedCallbacks), (ForceResendingToken) null);
    }

    public void verifyPhoneNumber(String str, long j, TimeUnit timeUnit, Executor executor, OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, ForceResendingToken forceResendingToken) {
        zza(zzbq.zzgv(str), j, timeUnit, (Activity) null, (Executor) zzbq.checkNotNull(executor), (OnVerificationStateChangedCallbacks) zzbq.checkNotNull(onVerificationStateChangedCallbacks), forceResendingToken);
    }
}
