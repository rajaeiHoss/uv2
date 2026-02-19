package com.google.android.gms.fido.u2f;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.zzda;
import com.google.android.gms.common.api.internal.zzg;
import com.google.android.gms.fido.u2f.api.common.RegisterRequestParams;
import com.google.android.gms.fido.u2f.api.common.SignRequestParams;
import com.google.android.gms.internal.zzbwa;
import com.google.android.gms.internal.zzbwb;
import com.google.android.gms.tasks.Task;

public class U2fApiClient extends GoogleApi<Api.ApiOptions.NoOptions> {
    private static final Api.zzf<zzbwa> zzhfx;
    private static final Api<Api.ApiOptions.NoOptions> zzhfy;

    static {
        Api.zzf<zzbwa> zzf = new Api.zzf<>();
        zzhfx = zzf;
        zzhfy = new Api<>("Fido.U2F_API", new zzbwb(), zzf);
    }

    public U2fApiClient(Activity activity) {
        super(activity, zzhfy, null, (zzda) new zzg());
    }

    public U2fApiClient(Context context) {
        super(context, zzhfy, null, (zzda) new zzg());
    }

    public Task<U2fPendingIntent> getRegisterIntent(RegisterRequestParams registerRequestParams) {
        return zza(new com.google.android.gms.fido.u2f.zza(this, registerRequestParams));
    }

    public Task<U2fPendingIntent> getSignIntent(SignRequestParams signRequestParams) {
        return zza(new com.google.android.gms.fido.u2f.zzc(this, signRequestParams));
    }
}
