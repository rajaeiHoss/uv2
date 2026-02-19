package com.google.android.gms.wallet;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.internal.zzdmv;
import com.google.android.gms.wallet.Wallet;

final class zzap extends Api.zza<zzdmv, Wallet.WalletOptions> {
    zzap() {
    }

    public final zzdmv zza(Context context, Looper looper, zzr zzr, Wallet.WalletOptions walletOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        if (walletOptions == null) {
            walletOptions = new Wallet.WalletOptions((zzap) null);
        }
        return new zzdmv(context, looper, zzr, connectionCallbacks, onConnectionFailedListener, walletOptions.environment, walletOptions.theme, walletOptions.zzloc);
    }
}
