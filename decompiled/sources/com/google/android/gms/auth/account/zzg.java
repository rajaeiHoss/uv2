package com.google.android.gms.auth.account;

import android.accounts.Account;
import com.google.android.gms.auth.account.WorkAccountApi;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.zzbo;

final class zzg implements zzbo<WorkAccountApi.AddAccountResult, Account> {
    zzg(WorkAccountClient workAccountClient) {
    }

    public final Account zzb(WorkAccountApi.AddAccountResult addAccountResult) {
        return addAccountResult.getAccount();
    }
}
