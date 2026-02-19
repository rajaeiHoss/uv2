package com.google.android.gms.auth;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.SystemClock;
import android.text.TextUtils;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.internal.zzag;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.zza;
import com.google.android.gms.common.zzs;
import com.google.android.gms.internal.zzbhf;
import java.io.IOException;
import java.util.List;

public class zzd {
    public static final int CHANGE_TYPE_ACCOUNT_ADDED = 1;
    public static final int CHANGE_TYPE_ACCOUNT_REMOVED = 2;
    public static final int CHANGE_TYPE_ACCOUNT_RENAMED_FROM = 3;
    public static final int CHANGE_TYPE_ACCOUNT_RENAMED_TO = 4;
    public static final String GOOGLE_ACCOUNT_TYPE = "com.google";
    public static final String KEY_ANDROID_PACKAGE_NAME = "androidPackageName";
    public static final String KEY_CALLER_UID = "callerUid";
    public static final String KEY_SUPPRESS_PROGRESS_SCREEN = "suppressProgressScreen";
    public static final String WORK_ACCOUNT_TYPE = "com.google.work";
    private static String[] zzehp = {"com.google", "com.google.work", "cn.google"};
    private static final ComponentName zzehq = new ComponentName("com.google.android.gms", "com.google.android.gms.auth.GetToken");
    /* access modifiers changed from: private */
    public static final zzbhf zzehr = new zzbhf("Auth", "GoogleAuthUtil");

    zzd() {
    }

    public static void clearToken(Context context, String str) throws GooglePlayServicesAvailabilityException, GoogleAuthException, IOException {
        zzbq.zzgw("Calling this from your main thread can lead to deadlock");
        zzbo(context);
        Bundle bundle = new Bundle();
        String str2 = context.getApplicationInfo().packageName;
        bundle.putString("clientPackageName", str2);
        String str3 = KEY_ANDROID_PACKAGE_NAME;
        if (!bundle.containsKey(str3)) {
            bundle.putString(str3, str2);
        }
        zza(context, zzehq, new zzf(str, bundle));
    }

    public static List<AccountChangeEvent> getAccountChangeEvents(Context context, int i, String str) throws GoogleAuthException, IOException {
        zzbq.zzh(str, "accountName must be provided");
        zzbq.zzgw("Calling this from your main thread can lead to deadlock");
        zzbo(context);
        return (List) zza(context, zzehq, new zzg(str, i));
    }

    public static String getAccountId(Context context, String str) throws GoogleAuthException, IOException {
        zzbq.zzh(str, "accountName must be provided");
        zzbq.zzgw("Calling this from your main thread can lead to deadlock");
        zzbo(context);
        return getToken(context, str, "^^_account_id_^^", new Bundle());
    }

    public static String getToken(Context context, Account account, String str) throws IOException, UserRecoverableAuthException, GoogleAuthException {
        return getToken(context, account, str, new Bundle());
    }

    public static String getToken(Context context, Account account, String str, Bundle bundle) throws IOException, UserRecoverableAuthException, GoogleAuthException {
        zzc(account);
        return zzb(context, account, str, bundle).getToken();
    }

    @Deprecated
    public static String getToken(Context context, String str, String str2) throws IOException, UserRecoverableAuthException, GoogleAuthException {
        return getToken(context, new Account(str, "com.google"), str2);
    }

    @Deprecated
    public static String getToken(Context context, String str, String str2, Bundle bundle) throws IOException, UserRecoverableAuthException, GoogleAuthException {
        return getToken(context, new Account(str, "com.google"), str2, bundle);
    }

    @Deprecated
    public static void invalidateToken(Context context, String str) {
        AccountManager.get(context).invalidateAuthToken("com.google", str);
    }

    public static Bundle removeAccount(Context context, Account account) throws GoogleAuthException, IOException {
        zzbq.checkNotNull(context);
        zzc(account);
        zzbo(context);
        return (Bundle) zza(context, zzehq, new zzh(account));
    }

    public static Boolean requestGoogleAccountsAccess(Context context) throws GoogleAuthException, IOException {
        zzbq.checkNotNull(context);
        zzbo(context);
        return (Boolean) zza(context, zzehq, new zzi(context.getApplicationInfo().packageName));
    }

    private static <T> T zza(Context context, ComponentName componentName, zzj<T> zzj) throws IOException, GoogleAuthException {
        zza zza = new zza();
        zzag zzcp = zzag.zzcp(context);
        if (zzcp.zza(componentName, (ServiceConnection) zza, "GoogleAuthUtil")) {
            try {
                T zzac = zzj.zzac(zza.zzahd());
                zzcp.zzb(componentName, (ServiceConnection) zza, "GoogleAuthUtil");
                return zzac;
            } catch (RemoteException | InterruptedException e) {
                zzehr.zze("GoogleAuthUtil", "Error on service connection.", e);
                throw new IOException("Error on service connection.", e);
            } catch (Throwable th) {
                zzcp.zzb(componentName, (ServiceConnection) zza, "GoogleAuthUtil");
                throw th;
            }
        } else {
            throw new IOException("Could not bind to service.");
        }
    }

    public static TokenData zzb(Context context, Account account, String str, Bundle bundle) throws IOException, UserRecoverableAuthException, GoogleAuthException {
        zzbq.zzgw("Calling this from your main thread can lead to deadlock");
        zzbq.zzh(str, "Scope cannot be empty or null.");
        zzc(account);
        zzbo(context);
        Bundle bundle2 = bundle == null ? new Bundle() : new Bundle(bundle);
        String str2 = context.getApplicationInfo().packageName;
        bundle2.putString("clientPackageName", str2);
        String str3 = KEY_ANDROID_PACKAGE_NAME;
        if (TextUtils.isEmpty(bundle2.getString(str3))) {
            bundle2.putString(str3, str2);
        }
        bundle2.putLong("service_connection_start_time_millis", SystemClock.elapsedRealtime());
        return (TokenData) zza(context, zzehq, new zze(account, str, bundle2));
    }

    private static void zzbo(Context context) throws GoogleAuthException {
        try {
            zzs.zzbo(context.getApplicationContext());
        } catch (GooglePlayServicesRepairableException e) {
            throw new GooglePlayServicesAvailabilityException(e.getConnectionStatusCode(), e.getMessage(), e.getIntent());
        } catch (GooglePlayServicesNotAvailableException e2) {
            throw new GoogleAuthException(e2.getMessage());
        }
    }

    private static void zzc(Account account) {
        if (account == null) {
            throw new IllegalArgumentException("Account cannot be null");
        } else if (!TextUtils.isEmpty(account.name)) {
            String[] strArr = zzehp;
            int length = strArr.length;
            int i = 0;
            while (i < length) {
                if (!strArr[i].equals(account.type)) {
                    i++;
                } else {
                    return;
                }
            }
            throw new IllegalArgumentException("Account type not supported");
        } else {
            throw new IllegalArgumentException("Account name cannot be empty!");
        }
    }

    /* access modifiers changed from: private */
    public static <T> T zzp(T t) throws IOException {
        if (t != null) {
            return t;
        }
        zzehr.zzf("GoogleAuthUtil", "Binder call returned null.");
        throw new IOException("Service unavailable.");
    }
}
