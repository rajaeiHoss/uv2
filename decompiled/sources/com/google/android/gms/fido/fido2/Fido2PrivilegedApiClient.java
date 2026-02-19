package com.google.android.gms.fido.fido2;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.zzda;
import com.google.android.gms.common.api.internal.zzg;
import com.google.android.gms.fido.fido2.api.common.BrowserMakeCredentialOptions;
import com.google.android.gms.fido.fido2.api.common.BrowserPublicKeyCredentialRequestOptions;
import com.google.android.gms.internal.zzbvj;
import com.google.android.gms.internal.zzbvk;
import com.google.android.gms.tasks.Task;

public class Fido2PrivilegedApiClient extends GoogleApi<Api.ApiOptions.NoOptions> {
    private static final Api.zzf<zzbvj> zzhee;
    private static final Api<Api.ApiOptions.NoOptions> zzhef;

    static {
        Api.zzf<zzbvj> zzf = new Api.zzf<>();
        zzhee = zzf;
        zzhef = new Api<>("Fido.FIDO2_PRIVILEGED_API", new zzbvk(), zzf);
    }

    public Fido2PrivilegedApiClient(Activity activity) {
        super(activity, zzhef, null, (zzda) new zzg());
    }

    public Fido2PrivilegedApiClient(Context context) {
        super(context, zzhef, null, (zzda) new zzg());
    }

    public Task<Fido2PendingIntent> getRegisterIntent(BrowserMakeCredentialOptions browserMakeCredentialOptions) {
        return zza(new com.google.android.gms.fido.fido2.zze(this, browserMakeCredentialOptions));
    }

    public Task<Fido2PendingIntent> getSignIntent(BrowserPublicKeyCredentialRequestOptions browserPublicKeyCredentialRequestOptions) {
        return zza(new com.google.android.gms.fido.fido2.zzg(this, browserPublicKeyCredentialRequestOptions));
    }
}
