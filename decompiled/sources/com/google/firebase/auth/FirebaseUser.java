package com.google.firebase.auth;

import android.net.Uri;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzebw;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import java.util.List;

public abstract class FirebaseUser extends zzbgl implements UserInfo {
    public Task<Void> delete() {
        return FirebaseAuth.getInstance(zzbtl()).zze(this);
    }

    public abstract String getDisplayName();

    public abstract String getEmail();

    public Task<GetTokenResult> getIdToken(boolean z) {
        return FirebaseAuth.getInstance(zzbtl()).zza(this, z);
    }

    public abstract FirebaseUserMetadata getMetadata();

    public abstract String getPhoneNumber();

    public abstract Uri getPhotoUrl();

    public abstract List<? extends UserInfo> getProviderData();

    public abstract String getProviderId();

    public abstract List<String> getProviders();

    @Deprecated
    public Task<GetTokenResult> getToken(boolean z) {
        return getIdToken(z);
    }

    public abstract String getUid();

    public abstract boolean isAnonymous();

    public Task<AuthResult> linkWithCredential(AuthCredential authCredential) {
        zzbq.checkNotNull(authCredential);
        return FirebaseAuth.getInstance(zzbtl()).zzc(this, authCredential);
    }

    public Task<Void> reauthenticate(AuthCredential authCredential) {
        zzbq.checkNotNull(authCredential);
        return FirebaseAuth.getInstance(zzbtl()).zza(this, authCredential);
    }

    public Task<AuthResult> reauthenticateAndRetrieveData(AuthCredential authCredential) {
        zzbq.checkNotNull(authCredential);
        return FirebaseAuth.getInstance(zzbtl()).zzb(this, authCredential);
    }

    public Task<Void> reload() {
        return FirebaseAuth.getInstance(zzbtl()).zzd(this);
    }

    public Task<Void> sendEmailVerification() {
        return FirebaseAuth.getInstance(zzbtl()).zza(this, false).continueWithTask(new zzo(this));
    }

    public Task<Void> sendEmailVerification(ActionCodeSettings actionCodeSettings) {
        return FirebaseAuth.getInstance(zzbtl()).zza(this, false).continueWithTask(new zzp(this, actionCodeSettings));
    }

    public Task<AuthResult> unlink(String str) {
        zzbq.zzgv(str);
        return FirebaseAuth.getInstance(zzbtl()).zza(this, str);
    }

    public Task<Void> updateEmail(String str) {
        zzbq.zzgv(str);
        return FirebaseAuth.getInstance(zzbtl()).zzb(this, str);
    }

    public Task<Void> updatePassword(String str) {
        zzbq.zzgv(str);
        return FirebaseAuth.getInstance(zzbtl()).zzc(this, str);
    }

    public Task<Void> updatePhoneNumber(PhoneAuthCredential phoneAuthCredential) {
        return FirebaseAuth.getInstance(zzbtl()).zza(this, phoneAuthCredential);
    }

    public Task<Void> updateProfile(UserProfileChangeRequest userProfileChangeRequest) {
        zzbq.checkNotNull(userProfileChangeRequest);
        return FirebaseAuth.getInstance(zzbtl()).zza(this, userProfileChangeRequest);
    }

    public abstract void zza(zzebw zzebw);

    public abstract FirebaseUser zzar(List<? extends UserInfo> list);

    public abstract FirebaseApp zzbtl();

    public abstract zzebw zzbtm();

    public abstract String zzbtn();

    public abstract String zzbto();

    public abstract FirebaseUser zzck(boolean z);
}
