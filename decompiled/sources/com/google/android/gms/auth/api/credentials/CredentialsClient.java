package com.google.android.gms.auth.api.credentials;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.zzda;
import com.google.android.gms.common.api.internal.zzg;
import com.google.android.gms.common.internal.zzbj;
import com.google.android.gms.internal.zzaxh;
import com.google.android.gms.tasks.Task;

public class CredentialsClient extends GoogleApi<Auth.AuthCredentialsOptions> {
    CredentialsClient(Activity activity, Auth.AuthCredentialsOptions authCredentialsOptions) {
        super(activity, Auth.CREDENTIALS_API, authCredentialsOptions, (zzda) new zzg());
    }

    CredentialsClient(Context context, Auth.AuthCredentialsOptions authCredentialsOptions) {
        super(context, Auth.CREDENTIALS_API, authCredentialsOptions, (zzda) new zzg());
    }

    public Task<Void> delete(Credential credential) {
        return zzbj.zzb(Auth.CredentialsApi.delete(zzahw(), credential));
    }

    public Task<Void> disableAutoSignIn() {
        return zzbj.zzb(Auth.CredentialsApi.disableAutoSignIn(zzahw()));
    }

    public PendingIntent getHintPickerIntent(HintRequest hintRequest) {
        return zzaxh.zza(getApplicationContext(), (Auth.AuthCredentialsOptions) zzahu(), hintRequest);
    }

    public Task<CredentialRequestResponse> request(CredentialRequest credentialRequest) {
        return zzbj.zza(Auth.CredentialsApi.request(zzahw(), credentialRequest), new CredentialRequestResponse());
    }

    public Task<Void> save(Credential credential) {
        return zzbj.zzb(Auth.CredentialsApi.save(zzahw(), credential));
    }
}
