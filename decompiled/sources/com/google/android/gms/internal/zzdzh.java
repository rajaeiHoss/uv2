package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.ActionCodeResult;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.FirebaseAuthProvider;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.ProviderQueryResult;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.auth.internal.zza;
import com.google.firebase.auth.internal.zzab;
import com.google.firebase.auth.internal.zzh;
import com.google.firebase.auth.internal.zzk;
import com.google.firebase.auth.internal.zzm;
import com.google.firebase.auth.internal.zzt;
import com.google.firebase.auth.internal.zzu;
import com.google.firebase.zzb;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

public final class zzdzh extends zzdza {
    private final Context zzaiq;
    private final zzebd zzmqo;

    zzdzh(Context context, zzebd zzebd) {
        this.zzaiq = context;
        this.zzmqo = zzebd;
    }

    private static <ResultT, CallbackT> zzdzu<ResultT, CallbackT> zza(zzebh<ResultT, CallbackT> zzebh, String str) {
        return new zzdzu<>(zzebh, str);
    }

    /* access modifiers changed from: private */
    public static zzk zza(FirebaseApp firebaseApp, zzebu zzebu) {
        return zza(firebaseApp, zzebu, false);
    }

    /* access modifiers changed from: private */
    public static zzk zza(FirebaseApp firebaseApp, zzebu zzebu, boolean z) {
        zzbq.checkNotNull(firebaseApp);
        zzbq.checkNotNull(zzebu);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new zzh(zzebu, FirebaseAuthProvider.PROVIDER_ID));
        List<zzeby> zzbuc = zzebu.zzbuc();
        if (zzbuc != null && !zzbuc.isEmpty()) {
            for (int i = 0; i < zzbuc.size(); i++) {
                arrayList.add(new zzh(zzbuc.get(i)));
            }
        }
        zzk zzk = new zzk(firebaseApp, arrayList);
        zzk.zzck(z);
        zzk.zza(new zzm(zzebu.getLastSignInTimestamp(), zzebu.getCreationTimestamp()));
        zzk.zzco(zzebu.isNewUser());
        zzk.zzb(zzebu.zzbud());
        return zzk;
    }

    private final GoogleApi<zzebd> zzcm(boolean z) {
        zzebd zzebd = (zzebd) this.zzmqo.clone();
        zzebd.zzmqj = z;
        return new zzdze(this.zzaiq, zzebb.zzmrf, zzebd, new zzb());
    }

    public final Task<Void> setFirebaseUIVersion(String str) {
        return zzb(zza(new zzeaf(str), "setFirebaseUIVersion"));
    }

    public final Task<Void> zza(FirebaseApp firebaseApp, ActionCodeSettings actionCodeSettings, String str) {
        return zzb(zza(new zzead(str, actionCodeSettings).zzc(firebaseApp), "sendEmailVerification"));
    }

    public final Task<AuthResult> zza(FirebaseApp firebaseApp, AuthCredential authCredential, zza zza) {
        return zzb(zza(new zzeah(authCredential).zzc(firebaseApp).zzbg(zza), "signInWithCredential"));
    }

    public final Task<AuthResult> zza(FirebaseApp firebaseApp, EmailAuthCredential emailAuthCredential, zza zza) {
        return zzb(zza(new zzeak(emailAuthCredential).zzc(firebaseApp).zzbg(zza), "sendSignInLinkToEmail"));
    }

    public final Task<Void> zza(FirebaseApp firebaseApp, FirebaseUser firebaseUser, AuthCredential authCredential, zzab zzab) {
        return zzb(zza(new zzdzv(authCredential).zzc(firebaseApp).zzf(firebaseUser).zzbg(zzab).zza((zzu) zzab), "reauthenticateWithCredential"));
    }

    public final Task<Void> zza(FirebaseApp firebaseApp, FirebaseUser firebaseUser, EmailAuthCredential emailAuthCredential, zzab zzab) {
        return zzb(zza(new zzdzx(emailAuthCredential).zzc(firebaseApp).zzf(firebaseUser).zzbg(zzab).zza((zzu) zzab), "reauthenticateWithEmailLink"));
    }

    public final Task<Void> zza(FirebaseApp firebaseApp, FirebaseUser firebaseUser, PhoneAuthCredential phoneAuthCredential, zzab zzab) {
        return zzb(zza(new zzeaq(phoneAuthCredential).zzc(firebaseApp).zzf(firebaseUser).zzbg(zzab).zza((zzu) zzab), "updatePhoneNumber"));
    }

    public final Task<Void> zza(FirebaseApp firebaseApp, FirebaseUser firebaseUser, UserProfileChangeRequest userProfileChangeRequest, zzab zzab) {
        return zzb(zza(new zzear(userProfileChangeRequest).zzc(firebaseApp).zzf(firebaseUser).zzbg(zzab).zza((zzu) zzab), "updateProfile"));
    }

    public final Task<Void> zza(FirebaseApp firebaseApp, FirebaseUser firebaseUser, zzab zzab) {
        return zza(zza(new zzeac().zzc(firebaseApp).zzf(firebaseUser).zzbg(zzab).zza((zzu) zzab), "reload"));
    }

    public final Task<GetTokenResult> zza(FirebaseApp firebaseApp, FirebaseUser firebaseUser, String str, zzab zzab) {
        return zza(zza(new zzdzp(str).zzc(firebaseApp).zzf(firebaseUser).zzbg(zzab).zza((zzu) zzab), "getAccessToken"));
    }

    public final Task<Void> zza(FirebaseApp firebaseApp, FirebaseUser firebaseUser, String str, String str2, zzab zzab) {
        return zzb(zza(new zzdzz(str, str2).zzc(firebaseApp).zzf(firebaseUser).zzbg(zzab).zza((zzu) zzab), "reauthenticateWithEmailPassword"));
    }

    public final Task<AuthResult> zza(FirebaseApp firebaseApp, PhoneAuthCredential phoneAuthCredential, zza zza) {
        return zzb(zza(new zzeal(phoneAuthCredential).zzc(firebaseApp).zzbg(zza), "signInWithPhoneNumber"));
    }

    public final Task<AuthResult> zza(FirebaseApp firebaseApp, zza zza) {
        return zzb(zza(new zzeag().zzc(firebaseApp).zzbg(zza), "signInAnonymously"));
    }

    public final Task<ProviderQueryResult> zza(FirebaseApp firebaseApp, String str) {
        return zza(zza(new zzdzn(str).zzc(firebaseApp), "fetchProvidersForEmail"));
    }

    public final Task<Void> zza(FirebaseApp firebaseApp, String str, ActionCodeSettings actionCodeSettings) {
        actionCodeSettings.zzhc(1);
        return zzb(zza(new zzeae(str, actionCodeSettings).zzc(firebaseApp), "sendPasswordResetEmail"));
    }

    public final Task<AuthResult> zza(FirebaseApp firebaseApp, String str, zza zza) {
        return zzb(zza(new zzeai(str).zzc(firebaseApp).zzbg(zza), "signInWithCustomToken"));
    }

    public final Task<Void> zza(FirebaseApp firebaseApp, String str, String str2) {
        return zzb(zza(new zzdzk(str, str2).zzc(firebaseApp), "confirmPasswordReset"));
    }

    public final Task<AuthResult> zza(FirebaseApp firebaseApp, String str, String str2, zza zza) {
        return zzb(zza(new zzdzl(str, str2).zzc(firebaseApp).zzbg(zza), "createUserWithEmailAndPassword"));
    }

    public final Task<Void> zza(FirebaseUser firebaseUser, zzt zzt) {
        return zzb(zza(new zzdzm().zzf(firebaseUser).zzbg(zzt).zza((zzu) zzt), "delete"));
    }

    public final void zza(FirebaseApp firebaseApp, zzece zzece, PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, Activity activity, Executor executor) {
        zzb(zza(new zzeat(zzece).zzc(firebaseApp).zza(onVerificationStateChangedCallbacks, activity, executor), "verifyPhoneNumber"));
    }

    public final Task<AuthResult> zzb(FirebaseApp firebaseApp, FirebaseUser firebaseUser, AuthCredential authCredential, zzab zzab) {
        return zzb(zza(new zzdzw(authCredential).zzc(firebaseApp).zzf(firebaseUser).zzbg(zzab).zza((zzu) zzab), "reauthenticateWithCredentialWithData"));
    }

    public final Task<AuthResult> zzb(FirebaseApp firebaseApp, FirebaseUser firebaseUser, EmailAuthCredential emailAuthCredential, zzab zzab) {
        return zzb(zza(new zzdzy(emailAuthCredential).zzc(firebaseApp).zzf(firebaseUser).zzbg(zzab).zza((zzu) zzab), "reauthenticateWithEmailLinkWithData"));
    }

    public final Task<Void> zzb(FirebaseApp firebaseApp, FirebaseUser firebaseUser, PhoneAuthCredential phoneAuthCredential, zzab zzab) {
        return zzb(zza(new zzeab(phoneAuthCredential).zzc(firebaseApp).zzf(firebaseUser).zzbg(zzab).zza((zzu) zzab), "reauthenticateWithPhoneCredential"));
    }

    public final Task<Void> zzb(FirebaseApp firebaseApp, FirebaseUser firebaseUser, String str, zzab zzab) {
        return zzb(zza(new zzeao(str).zzc(firebaseApp).zzf(firebaseUser).zzbg(zzab).zza((zzu) zzab), "updateEmail"));
    }

    public final Task<AuthResult> zzb(FirebaseApp firebaseApp, FirebaseUser firebaseUser, String str, String str2, zzab zzab) {
        return zzb(zza(new zzeaa(str, str2).zzc(firebaseApp).zzf(firebaseUser).zzbg(zzab).zza((zzu) zzab), "reauthenticateWithEmailPasswordWithData"));
    }

    public final Task<SignInMethodQueryResult> zzb(FirebaseApp firebaseApp, String str) {
        return zza(zza(new zzdzo(str).zzc(firebaseApp), "fetchSignInMethodsForEmail"));
    }

    public final Task<Void> zzb(FirebaseApp firebaseApp, String str, ActionCodeSettings actionCodeSettings) {
        actionCodeSettings.zzhc(6);
        return zzb(zza(new zzeae(str, actionCodeSettings).zzc(firebaseApp), "sendSignInLinkToEmail"));
    }

    public final Task<AuthResult> zzb(FirebaseApp firebaseApp, String str, String str2, zza zza) {
        return zzb(zza(new zzeaj(str, str2).zzc(firebaseApp).zzbg(zza), "signInWithEmailAndPassword"));
    }

    /* access modifiers changed from: package-private */
    public final zzdzb zzbtq() {
        int zzy = DynamiteModule.zzy(this.zzaiq, "com.google.android.gms.firebase_auth");
        boolean z = false;
        GoogleApi<zzebd> zzcm = zzcm(false);
        int zzx = DynamiteModule.zzx(this.zzaiq, "com.google.firebase.auth");
        GoogleApi<zzebd> zzcm2 = zzx != 0 ? zzcm(true) : null;
        Map emptyMap = Collections.emptyMap();
        if (zzy != 0) {
            z = true;
        }
        return new zzdzb(zzcm, zzcm2, new zzdzd(zzy, zzx, emptyMap, z));
    }

    public final Task<AuthResult> zzc(FirebaseApp firebaseApp, FirebaseUser firebaseUser, AuthCredential authCredential, zzab zzab) {
        return zzb(zza(new zzdzw(authCredential).zzc(firebaseApp).zzf(firebaseUser).zzbg(zzab).zza((zzu) zzab), "reauthenticateWithPhoneCredentialWithData"));
    }

    public final Task<Void> zzc(FirebaseApp firebaseApp, FirebaseUser firebaseUser, String str, zzab zzab) {
        return zzb(zza(new zzeap(str).zzc(firebaseApp).zzf(firebaseUser).zzbg(zzab).zza((zzu) zzab), "updatePassword"));
    }

    public final Task<ActionCodeResult> zzc(FirebaseApp firebaseApp, String str) {
        return zzb(zza(new zzdzj(str).zzc(firebaseApp), "checkActionCode"));
    }

    public final Task<AuthResult> zzd(FirebaseApp firebaseApp, FirebaseUser firebaseUser, AuthCredential authCredential, zzab zzab) {
        zzbq.checkNotNull(firebaseApp);
        zzbq.checkNotNull(authCredential);
        zzbq.checkNotNull(firebaseUser);
        zzbq.checkNotNull(zzab);
        List<String> providers = firebaseUser.getProviders();
        if (providers != null && providers.contains(authCredential.getProvider())) {
            return Tasks.forException(zzeaw.zzaw(new Status(17015)));
        }
        if (authCredential instanceof EmailAuthCredential) {
            EmailAuthCredential emailAuthCredential = (EmailAuthCredential) authCredential;
            return !emailAuthCredential.zzbth() ? zzb(zza(new zzdzq(emailAuthCredential).zzc(firebaseApp).zzf(firebaseUser).zzbg(zzab).zza((zzu) zzab), "linkEmailAuthCredential")) : zzb(zza(new zzdzt(emailAuthCredential).zzc(firebaseApp).zzf(firebaseUser).zzbg(zzab).zza((zzu) zzab), "linkEmailAuthCredential"));
        } else if (authCredential instanceof PhoneAuthCredential) {
            return zzb(zza(new zzdzs((PhoneAuthCredential) authCredential).zzc(firebaseApp).zzf(firebaseUser).zzbg(zzab).zza((zzu) zzab), "linkPhoneAuthCredential"));
        } else {
            zzbq.checkNotNull(firebaseApp);
            zzbq.checkNotNull(authCredential);
            zzbq.checkNotNull(firebaseUser);
            zzbq.checkNotNull(zzab);
            return zzb(zza(new zzdzr(authCredential).zzc(firebaseApp).zzf(firebaseUser).zzbg(zzab).zza((zzu) zzab), "linkFederatedCredential"));
        }
    }

    public final Task<AuthResult> zzd(FirebaseApp firebaseApp, FirebaseUser firebaseUser, String str, zzab zzab) {
        zzbq.checkNotNull(firebaseApp);
        zzbq.zzgv(str);
        zzbq.checkNotNull(firebaseUser);
        zzbq.checkNotNull(zzab);
        List<String> providers = firebaseUser.getProviders();
        if ((providers != null && !providers.contains(str)) || firebaseUser.isAnonymous()) {
            return Tasks.forException(zzeaw.zzaw(new Status(17016, str)));
        }
        str.hashCode();
        return !str.equals("password") ? zzb(zza(new zzean(str).zzc(firebaseApp).zzf(firebaseUser).zzbg(zzab).zza((zzu) zzab), "unlinkFederatedCredential")) : zzb(zza(new zzeam().zzc(firebaseApp).zzf(firebaseUser).zzbg(zzab).zza((zzu) zzab), "unlinkEmailCredential"));
    }

    public final Task<Void> zzd(FirebaseApp firebaseApp, String str) {
        return zzb(zza(new zzdzi(str).zzc(firebaseApp), "applyActionCode"));
    }

    public final Task<String> zze(FirebaseApp firebaseApp, String str) {
        return zzb(zza(new zzeas(str).zzc(firebaseApp), "verifyPasswordResetCode"));
    }
}
