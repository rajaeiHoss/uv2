package com.google.android.gms.fido;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.fido.fido2.Fido2ApiClient;
import com.google.android.gms.fido.fido2.Fido2PrivilegedApiClient;
import com.google.android.gms.fido.u2f.U2fApiClient;
import com.google.android.gms.fido.u2f.zze;
import com.google.android.gms.internal.zzbwe;
import com.google.android.gms.internal.zzbwf;
import com.google.android.gms.internal.zzbwg;

public class Fido {
    public static final String FIDO2_KEY_ERROR_EXTRA = "FIDO2_ERROR_EXTRA";
    public static final String FIDO2_KEY_RESPONSE_EXTRA = "FIDO2_RESPONSE_EXTRA";
    public static final String KEY_RESPONSE_EXTRA = "RESPONSE_EXTRA";
    private static Api.zzf<zzbwf> zzhdv = new Api.zzf<>();
    private static Api<Api.ApiOptions.NoOptions> zzhdw = new Api<>("Fido.U2F_ZERO_PARTY_API", new zzbwg(), zzhdv);
    private static zze zzhdx = new zzbwe();

    private Fido() {
    }

    public static Fido2ApiClient getFido2ApiClient(Activity activity) {
        return new Fido2ApiClient(activity);
    }

    public static Fido2ApiClient getFido2ApiClient(Context context) {
        return new Fido2ApiClient(context);
    }

    public static Fido2PrivilegedApiClient getFido2PrivilegedApiClient(Activity activity) {
        return new Fido2PrivilegedApiClient(activity);
    }

    public static Fido2PrivilegedApiClient getFido2PrivilegedApiClient(Context context) {
        return new Fido2PrivilegedApiClient(context);
    }

    public static U2fApiClient getU2fApiClient(Activity activity) {
        return new U2fApiClient(activity);
    }

    public static U2fApiClient getU2fApiClient(Context context) {
        return new U2fApiClient(context);
    }
}
