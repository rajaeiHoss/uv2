package com.google.android.gms.auth.api;

import android.os.Bundle;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.auth.api.credentials.PasswordSpecification;
import com.google.android.gms.auth.api.proxy.ProxyApi;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.internal.zzd;
import com.google.android.gms.auth.api.signin.internal.zze;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.internal.zzawb;
import com.google.android.gms.internal.zzawc;
import com.google.android.gms.internal.zzawd;
import com.google.android.gms.internal.zzawz;
import com.google.android.gms.internal.zzaxi;
import com.google.android.gms.internal.zzayh;

public final class Auth {
    public static final Api<AuthCredentialsOptions> CREDENTIALS_API;
    public static final CredentialsApi CredentialsApi = new zzawz();
    public static final Api<GoogleSignInOptions> GOOGLE_SIGN_IN_API;
    public static final GoogleSignInApi GoogleSignInApi = new zzd();
    public static final Api<zzf> PROXY_API = com.google.android.gms.auth.api.zzd.API;
    public static final ProxyApi ProxyApi = new zzayh();
    public static final Api.zzf<zzaxi> zzeik;
    private static Api.zzf<zzawd> zzeil = new Api.zzf<>();
    public static final Api.zzf<zze> zzeim;
    private static final Api.zza<zzaxi, AuthCredentialsOptions> zzein;
    private static final Api.zza<zzawd, Api.ApiOptions.NoOptions> zzeio;
    private static final Api.zza<zze, GoogleSignInOptions> zzeip;
    private static Api<Api.ApiOptions.NoOptions> zzeiq;
    private static zzawb zzeir = new zzawc();

    @Deprecated
    public static class AuthCredentialsOptions implements Api.ApiOptions.Optional {
        private static AuthCredentialsOptions zzeis = new Builder().zzabx();
        private final String zzeit = null;
        private final PasswordSpecification zzeiu;
        private final boolean zzeiv;

        @Deprecated
        public static class Builder {
            protected PasswordSpecification zzeiu = PasswordSpecification.zzeli;
            protected Boolean zzeiw = false;

            public Builder forceEnableSaveDialog() {
                this.zzeiw = true;
                return this;
            }

            public AuthCredentialsOptions zzabx() {
                return new AuthCredentialsOptions(this);
            }
        }

        public AuthCredentialsOptions(Builder builder) {
            this.zzeiu = builder.zzeiu;
            this.zzeiv = builder.zzeiw.booleanValue();
        }

        public final Bundle toBundle() {
            Bundle bundle = new Bundle();
            bundle.putString("consumer_package", (String) null);
            bundle.putParcelable("password_specification", this.zzeiu);
            bundle.putBoolean("force_save_dialog", this.zzeiv);
            return bundle;
        }

        public final PasswordSpecification zzabw() {
            return this.zzeiu;
        }
    }

    /* JADX WARNING: type inference failed for: r0v5, types: [com.google.android.gms.internal.zzawb, com.google.android.gms.internal.zzawc] */
    static {
        Api.zzf<zzaxi> zzf = new Api.zzf<>();
        zzeik = zzf;
        Api.zzf<zze> zzf2 = new Api.zzf<>();
        zzeim = zzf2;
        zza zza = new zza();
        zzein = zza;
        zzb zzb = new zzb();
        zzeio = zzb;
        zzc zzc = new zzc();
        zzeip = zzc;
        CREDENTIALS_API = new Api<>("Auth.CREDENTIALS_API", zza, zzf);
        GOOGLE_SIGN_IN_API = new Api<>("Auth.GOOGLE_SIGN_IN_API", zzc, zzf2);
        zzeiq = new Api<>("Auth.ACCOUNT_STATUS_API", zzb, zzeil);
    }

    private Auth() {
    }
}
