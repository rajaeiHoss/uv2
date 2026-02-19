package com.google.android.gms.fido.fido2;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.zzda;
import com.google.android.gms.common.api.internal.zzg;
import com.google.android.gms.fido.fido2.api.common.MakeCredentialOptions;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialRequestOptions;
import com.google.android.gms.internal.zzbvp;
import com.google.android.gms.internal.zzbvq;
import com.google.android.gms.tasks.Task;

public class Fido2ApiClient extends GoogleApi<Api.ApiOptions.NoOptions> {
    private static final Api.zzf<zzbvp> zzhdz;
    private static final Api<Api.ApiOptions.NoOptions> zzhea;

    static {
        Api.zzf<zzbvp> zzf = new Api.zzf<>();
        zzhdz = zzf;
        zzhea = new Api<>("Fido.FIDO2_API", new zzbvq(), zzf);
    }

    public Fido2ApiClient(Activity activity) {
        super(activity, zzhea, null, (zzda) new zzg());
    }

    public Fido2ApiClient(Context context) {
        super(context, zzhea, null, (zzda) new zzg());
    }

    public Task<Fido2PendingIntent> getRegisterIntent(MakeCredentialOptions makeCredentialOptions) {
        return zza(new com.google.android.gms.fido.fido2.zza(this, makeCredentialOptions));
    }

    public Task<Fido2PendingIntent> getSignIntent(PublicKeyCredentialRequestOptions publicKeyCredentialRequestOptions) {
        return zza(new com.google.android.gms.fido.fido2.zzc(this, publicKeyCredentialRequestOptions));
    }
}
