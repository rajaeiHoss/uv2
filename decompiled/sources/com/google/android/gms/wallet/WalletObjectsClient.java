package com.google.android.gms.wallet;

import android.app.Activity;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.wallet.Wallet;

public class WalletObjectsClient extends GoogleApi<Wallet.WalletOptions> {
    WalletObjectsClient(Activity activity, Wallet.WalletOptions walletOptions) {
        super(activity, Wallet.API, walletOptions, GoogleApi.zza.zzfsr);
    }

    public Task<AutoResolvableVoidResult> createWalletObjects(CreateWalletObjectsRequest createWalletObjectsRequest) {
        return zzb(new zzaq(this, createWalletObjectsRequest));
    }
}
