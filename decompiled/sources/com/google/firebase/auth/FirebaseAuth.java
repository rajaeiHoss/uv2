package com.google.firebase.auth;

import android.app.Activity;
import android.util.Log;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzdzh;
import com.google.android.gms.internal.zzeaw;
import com.google.android.gms.internal.zzebb;
import com.google.android.gms.internal.zzebe;
import com.google.android.gms.internal.zzebf;
import com.google.android.gms.internal.zzebw;
import com.google.android.gms.internal.zzece;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.internal.zzaa;
import com.google.firebase.auth.internal.zzab;
import com.google.firebase.auth.internal.zzf;
import com.google.firebase.auth.internal.zzj;
import com.google.firebase.auth.internal.zzk;
import com.google.firebase.auth.internal.zzt;
import com.google.firebase.auth.internal.zzu;
import com.google.firebase.auth.internal.zzx;
import com.google.firebase.auth.internal.zzy;
import com.google.firebase.internal.InternalTokenProvider;
import com.google.firebase.internal.zzc;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

public class FirebaseAuth implements InternalTokenProvider {
    private static Map<String, FirebaseAuth> zzimu = new ArrayMap();
    private static FirebaseAuth zzmpk;
    /* access modifiers changed from: private */
    public List<IdTokenListener> zzmmu;
    /* access modifiers changed from: private */
    public FirebaseApp zzmpb;
    /* access modifiers changed from: private */
    public List<AuthStateListener> zzmpc;
    private zzdzh zzmpd;
    /* access modifiers changed from: private */
    public FirebaseUser zzmpe;
    private final Object zzmpf;
    private String zzmpg;
    private zzx zzmph;
    private zzy zzmpi;
    private zzaa zzmpj;

    public interface AuthStateListener {
        void onAuthStateChanged(FirebaseAuth firebaseAuth);
    }

    public interface IdTokenListener {
        void onIdTokenChanged(FirebaseAuth firebaseAuth);
    }

    class zza implements com.google.firebase.auth.internal.zza {
        zza() {
        }

        public final void zza(zzebw zzebw, FirebaseUser firebaseUser) {
            zzbq.checkNotNull(zzebw);
            zzbq.checkNotNull(firebaseUser);
            firebaseUser.zza(zzebw);
            FirebaseAuth.this.zza(firebaseUser, zzebw, true);
        }
    }

    class zzb extends zza implements zzab {
        zzb() {
            super();
        }

        public final void onError(Status status) {
            if (status.getStatusCode() == 17011 || status.getStatusCode() == 17021 || status.getStatusCode() == 17005) {
                FirebaseAuth.this.signOut();
            }
        }
    }

    public FirebaseAuth(FirebaseApp firebaseApp) {
        this(firebaseApp, zzebb.zza(firebaseApp.getApplicationContext(), new zzebe(firebaseApp.getOptions().getApiKey()).zzbtz()), new zzx(firebaseApp.getApplicationContext(), firebaseApp.zzbsv()));
    }

    private FirebaseAuth(FirebaseApp firebaseApp, zzdzh zzdzh, zzx zzx) {
        zzebw zzh;
        this.zzmpf = new Object();
        this.zzmpb = (FirebaseApp) zzbq.checkNotNull(firebaseApp);
        this.zzmpd = (zzdzh) zzbq.checkNotNull(zzdzh);
        this.zzmph = (zzx) zzbq.checkNotNull(zzx);
        this.zzmmu = new CopyOnWriteArrayList();
        this.zzmpc = new CopyOnWriteArrayList();
        this.zzmpj = zzaa.zzbus();
        FirebaseUser zzbuq = this.zzmph.zzbuq();
        this.zzmpe = zzbuq;
        if (zzbuq != null && (zzh = this.zzmph.zzh(zzbuq)) != null) {
            zza(this.zzmpe, zzh, false);
        }
    }

    public static FirebaseAuth getInstance() {
        return zzb(FirebaseApp.getInstance());
    }

    public static FirebaseAuth getInstance(FirebaseApp firebaseApp) {
        return zzb(firebaseApp);
    }

    private final synchronized void zza(zzy zzy) {
        this.zzmpi = zzy;
        this.zzmpb.zza((FirebaseApp.zzb) zzy);
    }

    private static synchronized FirebaseAuth zzb(FirebaseApp firebaseApp) {
        synchronized (FirebaseAuth.class) {
            String zzbsv = firebaseApp.zzbsv();
            FirebaseAuth firebaseAuth = zzimu.get(zzbsv);
            if (firebaseAuth != null) {
                return firebaseAuth;
            }
            zzj zzj = new zzj(firebaseApp);
            firebaseApp.zza((InternalTokenProvider) zzj);
            if (zzmpk == null) {
                zzmpk = zzj;
            }
            zzimu.put(zzbsv, zzj);
            return zzj;
        }
    }

    private final void zzb(FirebaseUser firebaseUser) {
        String str;
        if (firebaseUser != null) {
            String uid = firebaseUser.getUid();
            StringBuilder sb = new StringBuilder(String.valueOf(uid).length() + 45);
            sb.append("Notifying id token listeners about user ( ");
            sb.append(uid);
            sb.append(" ).");
            str = sb.toString();
        } else {
            str = "Notifying id token listeners about a sign-out event.";
        }
        Log.d("FirebaseAuth", str);
        this.zzmpj.execute(new com.google.firebase.auth.zzk(this, new com.google.firebase.internal.zzc(firebaseUser != null ? firebaseUser.zzbto() : null)));
    }

    private final synchronized zzy zzbtj() {
        if (this.zzmpi == null) {
            zza(new zzy(this.zzmpb));
        }
        return this.zzmpi;
    }

    private final void zzc(FirebaseUser firebaseUser) {
        String str;
        if (firebaseUser != null) {
            String uid = firebaseUser.getUid();
            StringBuilder sb = new StringBuilder(String.valueOf(uid).length() + 47);
            sb.append("Notifying auth state listeners about user ( ");
            sb.append(uid);
            sb.append(" ).");
            str = sb.toString();
        } else {
            str = "Notifying auth state listeners about a sign-out event.";
        }
        Log.d("FirebaseAuth", str);
        this.zzmpj.execute(new zzl(this));
    }

    public void addAuthStateListener(AuthStateListener authStateListener) {
        this.zzmpc.add(authStateListener);
        this.zzmpj.execute(new com.google.firebase.auth.zzj(this, authStateListener));
    }

    public void addIdTokenListener(IdTokenListener idTokenListener) {
        this.zzmmu.add(idTokenListener);
        this.zzmpj.execute(new zzi(this, idTokenListener));
    }

    public Task<Void> applyActionCode(String str) {
        zzbq.zzgv(str);
        return this.zzmpd.zzd(this.zzmpb, str);
    }

    public Task<ActionCodeResult> checkActionCode(String str) {
        zzbq.zzgv(str);
        return this.zzmpd.zzc(this.zzmpb, str);
    }

    public Task<Void> confirmPasswordReset(String str, String str2) {
        zzbq.zzgv(str);
        zzbq.zzgv(str2);
        return this.zzmpd.zza(this.zzmpb, str, str2);
    }

    public Task<AuthResult> createUserWithEmailAndPassword(String str, String str2) {
        zzbq.zzgv(str);
        zzbq.zzgv(str2);
        return this.zzmpd.zza(this.zzmpb, str, str2, (com.google.firebase.auth.internal.zza) new zza());
    }

    @Deprecated
    public Task<ProviderQueryResult> fetchProvidersForEmail(String str) {
        zzbq.zzgv(str);
        return this.zzmpd.zza(this.zzmpb, str);
    }

    public Task<SignInMethodQueryResult> fetchSignInMethodsForEmail(String str) {
        zzbq.zzgv(str);
        return this.zzmpd.zzb(this.zzmpb, str);
    }

    public FirebaseApp getApp() {
        return this.zzmpb;
    }

    public FirebaseUser getCurrentUser() {
        return this.zzmpe;
    }

    public String getLanguageCode() {
        String str;
        synchronized (this.zzmpf) {
            str = this.zzmpg;
        }
        return str;
    }

    public final String getUid() {
        FirebaseUser firebaseUser = this.zzmpe;
        if (firebaseUser == null) {
            return null;
        }
        return firebaseUser.getUid();
    }

    public boolean isSignInWithEmailLink(String str) {
        return EmailAuthCredential.isSignInWithEmailLink(str);
    }

    public void removeAuthStateListener(AuthStateListener authStateListener) {
        this.zzmpc.remove(authStateListener);
    }

    public void removeIdTokenListener(IdTokenListener idTokenListener) {
        this.zzmmu.remove(idTokenListener);
    }

    public Task<Void> sendPasswordResetEmail(String str) {
        zzbq.zzgv(str);
        return sendPasswordResetEmail(str, (ActionCodeSettings) null);
    }

    public Task<Void> sendPasswordResetEmail(String str, ActionCodeSettings actionCodeSettings) {
        zzbq.zzgv(str);
        if (actionCodeSettings == null) {
            actionCodeSettings = ActionCodeSettings.newBuilder().build();
        }
        String str2 = this.zzmpg;
        if (str2 != null) {
            actionCodeSettings.zzpa(str2);
        }
        actionCodeSettings.zzhc(1);
        return this.zzmpd.zza(this.zzmpb, str, actionCodeSettings);
    }

    public Task<Void> sendSignInLinkToEmail(String str, ActionCodeSettings actionCodeSettings) {
        zzbq.zzgv(str);
        zzbq.checkNotNull(actionCodeSettings);
        String str2 = this.zzmpg;
        if (str2 != null) {
            actionCodeSettings.zzpa(str2);
        }
        return this.zzmpd.zzb(this.zzmpb, str, actionCodeSettings);
    }

    public Task<Void> setFirebaseUIVersion(String str) {
        return this.zzmpd.setFirebaseUIVersion(str);
    }

    public void setLanguageCode(String str) {
        zzbq.zzgv(str);
        synchronized (this.zzmpf) {
            this.zzmpg = str;
        }
    }

    public Task<AuthResult> signInAnonymously() {
        FirebaseUser firebaseUser = this.zzmpe;
        if (firebaseUser == null || !firebaseUser.isAnonymous()) {
            return this.zzmpd.zza(this.zzmpb, (com.google.firebase.auth.internal.zza) new zza());
        }
        zzk zzk = (zzk) this.zzmpe;
        zzk.zzco(false);
        return Tasks.forResult(new zzf(zzk));
    }

    public Task<AuthResult> signInWithCredential(AuthCredential authCredential) {
        zzbq.checkNotNull(authCredential);
        if (authCredential instanceof EmailAuthCredential) {
            EmailAuthCredential emailAuthCredential = (EmailAuthCredential) authCredential;
            return !emailAuthCredential.zzbth() ? this.zzmpd.zzb(this.zzmpb, emailAuthCredential.getEmail(), emailAuthCredential.getPassword(), (com.google.firebase.auth.internal.zza) new zza()) : this.zzmpd.zza(this.zzmpb, emailAuthCredential, (com.google.firebase.auth.internal.zza) new zza());
        } else if (!(authCredential instanceof PhoneAuthCredential)) {
            return this.zzmpd.zza(this.zzmpb, authCredential, (com.google.firebase.auth.internal.zza) new zza());
        } else {
            return this.zzmpd.zza(this.zzmpb, (PhoneAuthCredential) authCredential, (com.google.firebase.auth.internal.zza) new zza());
        }
    }

    public Task<AuthResult> signInWithCustomToken(String str) {
        zzbq.zzgv(str);
        return this.zzmpd.zza(this.zzmpb, str, (com.google.firebase.auth.internal.zza) new zza());
    }

    public Task<AuthResult> signInWithEmailAndPassword(String str, String str2) {
        zzbq.zzgv(str);
        zzbq.zzgv(str2);
        return this.zzmpd.zzb(this.zzmpb, str, str2, (com.google.firebase.auth.internal.zza) new zza());
    }

    public Task<AuthResult> signInWithEmailLink(String str, String str2) {
        return signInWithCredential(EmailAuthProvider.getCredentialWithLink(str, str2));
    }

    public void signOut() {
        zzbti();
        zzy zzy = this.zzmpi;
        if (zzy != null) {
            zzy.cancel();
        }
    }

    public void useAppLanguage() {
        synchronized (this.zzmpf) {
            this.zzmpg = zzebf.zzbua();
        }
    }

    public Task<String> verifyPasswordResetCode(String str) {
        zzbq.zzgv(str);
        return this.zzmpd.zze(this.zzmpb, str);
    }

    public final Task<Void> zza(ActionCodeSettings actionCodeSettings, String str) {
        zzbq.zzgv(str);
        if (this.zzmpg != null) {
            if (actionCodeSettings == null) {
                actionCodeSettings = ActionCodeSettings.newBuilder().build();
            }
            actionCodeSettings.zzpa(this.zzmpg);
        }
        return this.zzmpd.zza(this.zzmpb, actionCodeSettings, str);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.google.firebase.auth.internal.zzab, com.google.firebase.auth.FirebaseAuth$zzb] */
    /* JADX WARNING: type inference failed for: r2v1, types: [com.google.firebase.auth.internal.zzab, com.google.firebase.auth.FirebaseAuth$zzb] */
    /* JADX WARNING: type inference failed for: r2v2, types: [com.google.firebase.auth.internal.zzab, com.google.firebase.auth.FirebaseAuth$zzb] */
    /* JADX WARNING: type inference failed for: r6v0, types: [com.google.firebase.auth.internal.zzab, com.google.firebase.auth.FirebaseAuth$zzb] */
    public final Task<Void> zza(FirebaseUser firebaseUser, AuthCredential authCredential) {
        zzbq.checkNotNull(firebaseUser);
        zzbq.checkNotNull(authCredential);
        if (!EmailAuthCredential.class.isAssignableFrom(authCredential.getClass())) {
            return authCredential instanceof PhoneAuthCredential ? this.zzmpd.zzb(this.zzmpb, firebaseUser, (PhoneAuthCredential) authCredential, (zzab) new zzb()) : this.zzmpd.zza(this.zzmpb, firebaseUser, authCredential, (zzab) new zzb());
        }
        EmailAuthCredential emailAuthCredential = (EmailAuthCredential) authCredential;
        if (!"password".equals(emailAuthCredential.getSignInMethod())) {
            return this.zzmpd.zza(this.zzmpb, firebaseUser, emailAuthCredential, (zzab) new zzb());
        }
        return this.zzmpd.zza(this.zzmpb, firebaseUser, emailAuthCredential.getEmail(), emailAuthCredential.getPassword(), (zzab) new zzb());
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.google.firebase.auth.internal.zzab, com.google.firebase.auth.FirebaseAuth$zzb] */
    public final Task<Void> zza(FirebaseUser firebaseUser, PhoneAuthCredential phoneAuthCredential) {
        zzbq.checkNotNull(firebaseUser);
        zzbq.checkNotNull(phoneAuthCredential);
        return this.zzmpd.zza(this.zzmpb, firebaseUser, phoneAuthCredential, (zzab) new zzb());
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.google.firebase.auth.internal.zzab, com.google.firebase.auth.FirebaseAuth$zzb] */
    public final Task<Void> zza(FirebaseUser firebaseUser, UserProfileChangeRequest userProfileChangeRequest) {
        zzbq.checkNotNull(firebaseUser);
        zzbq.checkNotNull(userProfileChangeRequest);
        return this.zzmpd.zza(this.zzmpb, firebaseUser, userProfileChangeRequest, (zzab) new zzb());
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.google.firebase.auth.internal.zzab, com.google.firebase.auth.FirebaseAuth$zzb] */
    public final Task<AuthResult> zza(FirebaseUser firebaseUser, String str) {
        zzbq.zzgv(str);
        zzbq.checkNotNull(firebaseUser);
        return this.zzmpd.zzd(this.zzmpb, firebaseUser, str, (zzab) new zzb());
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.google.firebase.auth.internal.zzab, com.google.firebase.auth.zzm] */
    public final Task<GetTokenResult> zza(FirebaseUser firebaseUser, boolean z) {
        if (firebaseUser == null) {
            return Tasks.forException(zzeaw.zzaw(new Status(17495)));
        }
        zzebw zzbtm = this.zzmpe.zzbtm();
        return (!zzbtm.isValid() || z) ? this.zzmpd.zza(this.zzmpb, firebaseUser, zzbtm.zzbue(), (zzab) new zzm(this)) : Tasks.forResult(new GetTokenResult(zzbtm.getAccessToken()));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0035, code lost:
        if (r3 == false) goto L_0x000c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.firebase.auth.FirebaseUser r6, com.google.android.gms.internal.zzebw r7, boolean r8) {
        /*
            r5 = this;
            com.google.android.gms.common.internal.zzbq.checkNotNull(r6)
            com.google.android.gms.common.internal.zzbq.checkNotNull(r7)
            com.google.firebase.auth.FirebaseUser r0 = r5.zzmpe
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L_0x000e
        L_0x000c:
            r1 = 1
            goto L_0x0038
        L_0x000e:
            com.google.android.gms.internal.zzebw r0 = r0.zzbtm()
            java.lang.String r0 = r0.getAccessToken()
            java.lang.String r3 = r7.getAccessToken()
            boolean r0 = r0.equals(r3)
            r0 = r0 ^ r2
            com.google.firebase.auth.FirebaseUser r3 = r5.zzmpe
            java.lang.String r3 = r3.getUid()
            java.lang.String r4 = r6.getUid()
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0033
            if (r0 != 0) goto L_0x0033
            r0 = 0
            goto L_0x0034
        L_0x0033:
            r0 = 1
        L_0x0034:
            r2 = r0
            if (r3 != 0) goto L_0x0038
            goto L_0x000c
        L_0x0038:
            com.google.android.gms.common.internal.zzbq.checkNotNull(r6)
            com.google.firebase.auth.FirebaseUser r0 = r5.zzmpe
            if (r0 != 0) goto L_0x0042
            r5.zzmpe = r6
            goto L_0x0052
        L_0x0042:
            boolean r3 = r6.isAnonymous()
            r0.zzck(r3)
            com.google.firebase.auth.FirebaseUser r0 = r5.zzmpe
            java.util.List r3 = r6.getProviderData()
            r0.zzar(r3)
        L_0x0052:
            if (r8 == 0) goto L_0x005b
            com.google.firebase.auth.internal.zzx r0 = r5.zzmph
            com.google.firebase.auth.FirebaseUser r3 = r5.zzmpe
            r0.zzg(r3)
        L_0x005b:
            if (r2 == 0) goto L_0x0069
            com.google.firebase.auth.FirebaseUser r0 = r5.zzmpe
            if (r0 == 0) goto L_0x0064
            r0.zza(r7)
        L_0x0064:
            com.google.firebase.auth.FirebaseUser r0 = r5.zzmpe
            r5.zzb((com.google.firebase.auth.FirebaseUser) r0)
        L_0x0069:
            if (r1 == 0) goto L_0x0070
            com.google.firebase.auth.FirebaseUser r0 = r5.zzmpe
            r5.zzc((com.google.firebase.auth.FirebaseUser) r0)
        L_0x0070:
            if (r8 == 0) goto L_0x0077
            com.google.firebase.auth.internal.zzx r8 = r5.zzmph
            r8.zza(r6, r7)
        L_0x0077:
            com.google.firebase.auth.internal.zzy r6 = r5.zzbtj()
            com.google.firebase.auth.FirebaseUser r7 = r5.zzmpe
            com.google.android.gms.internal.zzebw r7 = r7.zzbtm()
            r6.zzc((com.google.android.gms.internal.zzebw) r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.auth.FirebaseAuth.zza(com.google.firebase.auth.FirebaseUser, com.google.android.gms.internal.zzebw, boolean):void");
    }

    public final void zza(String str, long j, TimeUnit timeUnit, PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, Activity activity, Executor executor, boolean z) {
        long j2 = j;
        long convert = TimeUnit.SECONDS.convert(j, timeUnit);
        if (convert < 0 || convert > 120) {
            throw new IllegalArgumentException("We only support 0-120 seconds for sms-auto-retrieval timeout");
        }
        this.zzmpd.zza(this.zzmpb, new zzece(str, convert, z, this.zzmpg), onVerificationStateChangedCallbacks, activity, executor);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.google.firebase.auth.internal.zzab, com.google.firebase.auth.FirebaseAuth$zzb] */
    /* JADX WARNING: type inference failed for: r2v1, types: [com.google.firebase.auth.internal.zzab, com.google.firebase.auth.FirebaseAuth$zzb] */
    /* JADX WARNING: type inference failed for: r2v2, types: [com.google.firebase.auth.internal.zzab, com.google.firebase.auth.FirebaseAuth$zzb] */
    /* JADX WARNING: type inference failed for: r6v0, types: [com.google.firebase.auth.internal.zzab, com.google.firebase.auth.FirebaseAuth$zzb] */
    public final Task<AuthResult> zzb(FirebaseUser firebaseUser, AuthCredential authCredential) {
        zzbq.checkNotNull(firebaseUser);
        zzbq.checkNotNull(authCredential);
        if (!EmailAuthCredential.class.isAssignableFrom(authCredential.getClass())) {
            return authCredential instanceof PhoneAuthCredential ? this.zzmpd.zzc(this.zzmpb, firebaseUser, authCredential, (zzab) new zzb()) : this.zzmpd.zzb(this.zzmpb, firebaseUser, authCredential, (zzab) new zzb());
        }
        EmailAuthCredential emailAuthCredential = (EmailAuthCredential) authCredential;
        if (!"password".equals(emailAuthCredential.getSignInMethod())) {
            return this.zzmpd.zzb(this.zzmpb, firebaseUser, emailAuthCredential, (zzab) new zzb());
        }
        return this.zzmpd.zzb(this.zzmpb, firebaseUser, emailAuthCredential.getEmail(), emailAuthCredential.getPassword(), new zzb());
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.google.firebase.auth.internal.zzab, com.google.firebase.auth.FirebaseAuth$zzb] */
    public final Task<Void> zzb(FirebaseUser firebaseUser, String str) {
        zzbq.checkNotNull(firebaseUser);
        zzbq.zzgv(str);
        return this.zzmpd.zzb(this.zzmpb, firebaseUser, str, (zzab) new zzb());
    }

    public final void zzbti() {
        FirebaseUser firebaseUser = this.zzmpe;
        if (firebaseUser != null) {
            zzx zzx = this.zzmph;
            zzbq.checkNotNull(firebaseUser);
            zzx.clear(String.format("com.google.firebase.auth.GET_TOKEN_RESPONSE.%s", new Object[]{firebaseUser.getUid()}));
            this.zzmpe = null;
        }
        this.zzmph.clear("com.google.firebase.auth.FIREBASE_USER");
        zzb((FirebaseUser) null);
        zzc((FirebaseUser) null);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.google.firebase.auth.internal.zzab, com.google.firebase.auth.FirebaseAuth$zzb] */
    public final Task<AuthResult> zzc(FirebaseUser firebaseUser, AuthCredential authCredential) {
        zzbq.checkNotNull(authCredential);
        zzbq.checkNotNull(firebaseUser);
        return this.zzmpd.zzd(this.zzmpb, firebaseUser, authCredential, (zzab) new zzb());
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.google.firebase.auth.internal.zzab, com.google.firebase.auth.FirebaseAuth$zzb] */
    public final Task<Void> zzc(FirebaseUser firebaseUser, String str) {
        zzbq.checkNotNull(firebaseUser);
        zzbq.zzgv(str);
        return this.zzmpd.zzc(this.zzmpb, firebaseUser, str, (zzab) new zzb());
    }

    public final Task<GetTokenResult> zzcj(boolean z) {
        return zza(this.zzmpe, z);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.google.firebase.auth.internal.zzab, com.google.firebase.auth.FirebaseAuth$zzb] */
    public final Task<Void> zzd(FirebaseUser firebaseUser) {
        zzbq.checkNotNull(firebaseUser);
        return this.zzmpd.zza(this.zzmpb, firebaseUser, (zzab) new zzb());
    }

    public final Task<Void> zze(FirebaseUser firebaseUser) {
        zzbq.checkNotNull(firebaseUser);
        return this.zzmpd.zza(firebaseUser, (zzt) new zzn(this, firebaseUser));
    }
}
