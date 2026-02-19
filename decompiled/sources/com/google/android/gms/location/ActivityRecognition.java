package com.google.android.gms.location;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.zzm;
import com.google.android.gms.internal.zzcfk;
import com.google.android.gms.internal.zzchh;

public class ActivityRecognition {
    public static final Api<Api.ApiOptions.NoOptions> API;
    @Deprecated
    public static final ActivityRecognitionApi ActivityRecognitionApi = new zzcfk();
    public static final String CLIENT_NAME = "activity_recognition";
    private static final Api.zzf<zzchh> zzegu;
    private static final Api.zza<zzchh, Api.ApiOptions.NoOptions> zzegv;

    public static abstract class zza<R extends Result> extends zzm<R, zzchh> {
        public zza(GoogleApiClient googleApiClient) {
            super((Api<?>) ActivityRecognition.API, googleApiClient);
        }
    }

    static {
        Api.zzf<zzchh> zzf = new Api.zzf<>();
        zzegu = zzf;
        com.google.android.gms.location.zza zza2 = new com.google.android.gms.location.zza();
        zzegv = zza2;
        API = new Api<>("ActivityRecognition.API", zza2, zzf);
    }

    private ActivityRecognition() {
    }

    public static ActivityRecognitionClient getClient(Activity activity) {
        return new ActivityRecognitionClient(activity);
    }

    public static ActivityRecognitionClient getClient(Context context) {
        return new ActivityRecognitionClient(context);
    }
}
