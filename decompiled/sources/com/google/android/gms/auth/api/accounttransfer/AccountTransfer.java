package com.google.android.gms.auth.api.accounttransfer;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.internal.zzawh;
import com.google.android.gms.internal.zzawi;

public final class AccountTransfer {
    public static final String ACTION_ACCOUNT_EXPORT_DATA_AVAILABLE = "com.google.android.gms.auth.ACCOUNT_EXPORT_DATA_AVAILABLE";
    public static final String ACTION_ACCOUNT_IMPORT_DATA_AVAILABLE = "com.google.android.gms.auth.ACCOUNT_IMPORT_DATA_AVAILABLE";
    public static final String ACTION_START_ACCOUNT_EXPORT = "com.google.android.gms.auth.START_ACCOUNT_EXPORT";
    private static final Api.zzf<zzawi> zzeiz;
    private static final Api.zza<zzawi, zzn> zzeja;
    private static Api<zzn> zzejb;
    @Deprecated
    private static zzb zzejc = new zzawh();
    private static zzq zzejd = new zzawh();

    /* JADX WARNING: type inference failed for: r0v1, types: [com.google.android.gms.internal.zzawh, com.google.android.gms.auth.api.accounttransfer.zzb] */
    /* JADX WARNING: type inference failed for: r0v2, types: [com.google.android.gms.internal.zzawh, com.google.android.gms.auth.api.accounttransfer.zzq] */
    static {
        Api.zzf<zzawi> zzf = new Api.zzf<>();
        zzeiz = zzf;
        zza zza = new zza();
        zzeja = zza;
        zzejb = new Api<>("AccountTransfer.ACCOUNT_TRANSFER_API", zza, zzf);
    }

    private AccountTransfer() {
    }

    public static AccountTransferClient getAccountTransferClient(Activity activity) {
        return new AccountTransferClient(activity);
    }

    public static AccountTransferClient getAccountTransferClient(Context context) {
        return new AccountTransferClient(context);
    }
}
