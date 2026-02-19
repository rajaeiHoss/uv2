package com.google.android.gms.auth.account;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.internal.zzavq;
import com.google.android.gms.internal.zzawa;

public class WorkAccount {
    public static final Api<Api.ApiOptions.NoOptions> API;
    @Deprecated
    public static final WorkAccountApi WorkAccountApi = new zzavq();
    private static final Api.zzf<zzawa> zzegu;
    private static final Api.zza<zzawa, Api.ApiOptions.NoOptions> zzegv;

    static {
        Api.zzf<zzawa> zzf = new Api.zzf<>();
        zzegu = zzf;
        zzf zzf2 = new zzf();
        zzegv = zzf2;
        API = new Api<>("WorkAccount.API", zzf2, zzf);
    }

    private WorkAccount() {
    }

    public static WorkAccountClient getClient(Activity activity) {
        return new WorkAccountClient(activity);
    }

    public static WorkAccountClient getClient(Context context) {
        return new WorkAccountClient(context);
    }
}
