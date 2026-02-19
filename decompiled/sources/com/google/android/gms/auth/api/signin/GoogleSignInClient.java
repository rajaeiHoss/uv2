package com.google.android.gms.auth.api.signin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.zzda;
import com.google.android.gms.common.api.internal.zzg;
import com.google.android.gms.common.internal.zzbj;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.zzf;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.tasks.Task;

public class GoogleSignInClient extends GoogleApi<GoogleSignInOptions> {
    private static final zza zzemp = new zza((zzc) null);
    private static int zzemq = zzb.zzems;

    static class zza implements zzbo<GoogleSignInResult, GoogleSignInAccount> {
        private zza() {
        }

        /* synthetic */ zza(zzc zzc) {
            this();
        }

        public final GoogleSignInAccount zzb(GoogleSignInResult googleSignInResult) {
            return googleSignInResult.getSignInAccount();
        }
    }

    /* 'enum' modifier removed */
    static final class zzb {
        public static final int zzems = 1;
        public static final int zzemt = 2;
        public static final int zzemu = 3;
        public static final int zzemv = 4;
        private static final /* synthetic */ int[] zzemw = {1, 2, 3, 4};

        public static int[] values$50KLMJ33DTMIUPRFDTJMOP9FC5N68SJFD5I2UPRDECNM2TBKD0NM2S395TPMIPRED5N2UHRFDTJMOPAJD5JMSIBE8DM6IPBEEGI4IRBGDHIMQPBEEHGN8QBFDOTG____0() {
            return (int[]) zzemw.clone();
        }
    }

    GoogleSignInClient(Activity activity, GoogleSignInOptions googleSignInOptions) {
        super(activity, Auth.GOOGLE_SIGN_IN_API, googleSignInOptions, (zzda) new zzg());
    }

    GoogleSignInClient(Context context, GoogleSignInOptions googleSignInOptions) {
        super(context, Auth.GOOGLE_SIGN_IN_API, googleSignInOptions, (zzda) new zzg());
    }

    private final synchronized int zzach() {
        if (zzemq == zzb.zzems) {
            Context applicationContext = getApplicationContext();
            int isGooglePlayServicesAvailable = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(applicationContext);
            zzemq = isGooglePlayServicesAvailable == 0 ? zzb.zzemv : (zzf.zza(applicationContext, isGooglePlayServicesAvailable, (String) null) != null || DynamiteModule.zzx(applicationContext, "com.google.android.gms.auth.api.fallback") == 0) ? zzb.zzemt : zzb.zzemu;
        }
        return zzemq;
    }

    public Intent getSignInIntent() {
        Context applicationContext = getApplicationContext();
        int i = zzc.zzemr[zzach() - 1];
        return i != 1 ? i != 2 ? com.google.android.gms.auth.api.signin.internal.zzf.zzc(applicationContext, (GoogleSignInOptions) zzahu()) : com.google.android.gms.auth.api.signin.internal.zzf.zza(applicationContext, (GoogleSignInOptions) zzahu()) : com.google.android.gms.auth.api.signin.internal.zzf.zzb(applicationContext, (GoogleSignInOptions) zzahu());
    }

    public Task<Void> revokeAccess() {
        return zzbj.zzb(com.google.android.gms.auth.api.signin.internal.zzf.zzb(zzahw(), getApplicationContext(), zzach() == zzb.zzemu));
    }

    public Task<Void> signOut() {
        return zzbj.zzb(com.google.android.gms.auth.api.signin.internal.zzf.zza(zzahw(), getApplicationContext(), zzach() == zzb.zzemu));
    }

    public Task<GoogleSignInAccount> silentSignIn() {
        return zzbj.zza(com.google.android.gms.auth.api.signin.internal.zzf.zza(zzahw(), getApplicationContext(), (GoogleSignInOptions) zzahu(), zzach() == zzb.zzemu), zzemp);
    }
}
