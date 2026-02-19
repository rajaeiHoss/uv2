package com.google.android.gms.safetynet;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.zzda;
import com.google.android.gms.common.api.internal.zzg;
import com.google.android.gms.common.internal.zzbj;
import com.google.android.gms.internal.zzcxf;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.Task;

public class SafetyNetClient extends GoogleApi<Api.ApiOptions.NoOptions> {
    SafetyNetClient(Activity activity) {
        super(activity, SafetyNet.API, null, (zzda) new zzg());
    }

    SafetyNetClient(Context context) {
        super(context, SafetyNet.API, null, (zzda) new zzg());
    }

    public Task<SafetyNetApi.AttestationResponse> attest(byte[] bArr, String str) {
        return zzbj.zza(zzcxf.zza(zzahw(), bArr, str), new SafetyNetApi.AttestationResponse());
    }

    public Task<SafetyNetApi.VerifyAppsUserResponse> enableVerifyApps() {
        return zzbj.zza(SafetyNet.SafetyNetApi.enableVerifyApps(zzahw()), new SafetyNetApi.VerifyAppsUserResponse());
    }

    public Task<Void> initSafeBrowsing() {
        return zza(new zzl(this));
    }

    public Task<SafetyNetApi.VerifyAppsUserResponse> isVerifyAppsEnabled() {
        return zzbj.zza(SafetyNet.SafetyNetApi.isVerifyAppsEnabled(zzahw()), new SafetyNetApi.VerifyAppsUserResponse());
    }

    public Task<SafetyNetApi.HarmfulAppsResponse> listHarmfulApps() {
        return zzbj.zza(SafetyNet.SafetyNetApi.listHarmfulApps(zzahw()), new SafetyNetApi.HarmfulAppsResponse());
    }

    public Task<SafetyNetApi.SafeBrowsingResponse> lookupUri(String str, String str2, int... iArr) {
        return zzbj.zza(zzcxf.zza(zzahw(), str, 3, str2, iArr), new SafetyNetApi.SafeBrowsingResponse());
    }

    public Task<Void> shutdownSafeBrowsing() {
        return zza(new zzn(this));
    }

    public Task<SafetyNetApi.RecaptchaTokenResponse> verifyWithRecaptcha(String str) {
        return zzbj.zza(SafetyNet.SafetyNetApi.verifyWithRecaptcha(zzahw(), str), new SafetyNetApi.RecaptchaTokenResponse());
    }
}
