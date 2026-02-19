package com.google.android.gms.drive;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbmu;
import com.google.android.gms.internal.zzbnq;
import com.google.android.gms.internal.zzbnv;
import com.google.android.gms.internal.zzboj;
import com.google.android.gms.internal.zzbot;
import com.google.android.gms.internal.zzboz;
import com.google.android.gms.internal.zzbqx;
import java.util.Arrays;
import java.util.Set;

public final class Drive {
    @Deprecated
    public static final Api<Api.ApiOptions.NoOptions> API;
    @Deprecated
    public static final DriveApi DriveApi = new zzbmu();
    @Deprecated
    public static final DrivePreferencesApi DrivePreferencesApi = new zzbot();
    public static final Scope SCOPE_APPFOLDER = new Scope(Scopes.DRIVE_APPFOLDER);
    public static final Scope SCOPE_FILE = new Scope(Scopes.DRIVE_FILE);
    public static final Api.zzf<zzbnq> zzegu;
    private static final Api.zza<zzbnq, Api.ApiOptions.NoOptions> zzegv;
    private static final Api.zza<zzbnq, zzb> zzgpj;
    private static final Api.zza<zzbnq, zza> zzgpk;
    private static Scope zzgpl = new Scope("https://www.googleapis.com/auth/drive");
    private static Scope zzgpm = new Scope("https://www.googleapis.com/auth/drive.apps");
    private static Api<zzb> zzgpn;
    public static final Api<zza> zzgpo;
    private static zzk zzgpp = new zzboj();
    private static zzm zzgpq = new zzbqx();

    public static class zza implements Api.ApiOptions.HasGoogleSignInAccountOptions {
        private final Bundle zzgpr = new Bundle();
        private final GoogleSignInAccount zzgps;

        public zza(GoogleSignInAccount googleSignInAccount) {
            this.zzgps = googleSignInAccount;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj != null && obj.getClass() == getClass()) {
                zza zza = (zza) obj;
                if (!zzbg.equal(this.zzgps, zza.getGoogleSignInAccount())) {
                    return false;
                }
                String string = this.zzgpr.getString("method_trace_filename");
                String string2 = zza.zzgpr.getString("method_trace_filename");
                return ((string == null && string2 == null) || !(string == null || string2 == null || !string.equals(string2))) && this.zzgpr.getBoolean("bypass_initial_sync") == zza.zzgpr.getBoolean("bypass_initial_sync") && this.zzgpr.getInt("proxy_type") == zza.zzgpr.getInt("proxy_type");
            }
            return false;
        }

        public final GoogleSignInAccount getGoogleSignInAccount() {
            return this.zzgps;
        }

        public final int hashCode() {
            return Arrays.hashCode(new Object[]{this.zzgps, this.zzgpr.getString("method_trace_filename", ""), Integer.valueOf(this.zzgpr.getInt("proxy_type")), Boolean.valueOf(this.zzgpr.getBoolean("bypass_initial_sync"))});
        }

        public final Bundle zzapj() {
            return this.zzgpr;
        }
    }

    public static class zzb implements Api.ApiOptions.Optional {
    }

    /* JADX WARNING: type inference failed for: r0v2, types: [com.google.android.gms.drive.zzk, com.google.android.gms.internal.zzboj] */
    /* JADX WARNING: type inference failed for: r0v3, types: [com.google.android.gms.internal.zzbqx, com.google.android.gms.drive.zzm] */
    static {
        Api.zzf<zzbnq> zzf = new Api.zzf<>();
        zzegu = zzf;
        zzf zzf2 = new zzf();
        zzegv = zzf2;
        zzg zzg = new zzg();
        zzgpj = zzg;
        zzh zzh = new zzh();
        zzgpk = zzh;
        API = new Api<>("Drive.API", zzf2, zzf);
        zzgpn = new Api<>("Drive.INTERNAL_API", zzg, zzf);
        zzgpo = new Api<>("Drive.API_CONNECTIONLESS", zzh, zzf);
    }

    private Drive() {
    }

    public static DriveClient getDriveClient(Activity activity, GoogleSignInAccount googleSignInAccount) {
        zza(googleSignInAccount);
        return new zzbnv(activity, new zza(googleSignInAccount));
    }

    public static DriveClient getDriveClient(Context context, GoogleSignInAccount googleSignInAccount) {
        zza(googleSignInAccount);
        return new zzbnv(context, new zza(googleSignInAccount));
    }

    public static DriveResourceClient getDriveResourceClient(Activity activity, GoogleSignInAccount googleSignInAccount) {
        zza(googleSignInAccount);
        return new zzboz(activity, new zza(googleSignInAccount));
    }

    public static DriveResourceClient getDriveResourceClient(Context context, GoogleSignInAccount googleSignInAccount) {
        zza(googleSignInAccount);
        return new zzboz(context, new zza(googleSignInAccount));
    }

    private static void zza(GoogleSignInAccount googleSignInAccount) {
        zzbq.checkNotNull(googleSignInAccount);
        Set<Scope> zzacf = googleSignInAccount.zzacf();
        zzbq.checkArgument(zzacf.contains(SCOPE_FILE) || zzacf.contains(SCOPE_APPFOLDER) || zzacf.contains(zzgpl) || zzacf.contains(zzgpm), "You must request a Drive scope in order to interact with the Drive API.");
    }
}
