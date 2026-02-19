package com.google.android.gms.auth.api.phone;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.zzda;
import com.google.android.gms.common.api.internal.zzg;
import com.google.android.gms.internal.zzayb;
import com.google.android.gms.tasks.Task;

public abstract class SmsRetrieverClient extends GoogleApi<Api.ApiOptions.NoOptions> implements SmsRetrieverApi {
    private static final Api<Api.ApiOptions.NoOptions> API;
    private static final Api.zzf<zzayb> zzegu;
    private static final Api.zza<zzayb, Api.ApiOptions.NoOptions> zzegv;

    static {
        Api.zzf<zzayb> zzf = new Api.zzf<>();
        zzegu = zzf;
        com.google.android.gms.auth.api.phone.zza clientBuilder = new com.google.android.gms.auth.api.phone.zza();
        zzegv = clientBuilder;
        API = new Api<>("SmsRetriever.API", clientBuilder, zzf);
    }

    public SmsRetrieverClient(Activity activity) {
        super(activity, API, null, (zzda) new zzg());
    }

    public SmsRetrieverClient(Context context) {
        super(context, API, null, (zzda) new zzg());
    }

    public abstract Task<Void> startSmsRetriever();
}
