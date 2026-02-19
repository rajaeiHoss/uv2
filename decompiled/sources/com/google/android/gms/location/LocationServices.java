package com.google.android.gms.location;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.zzm;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzcfy;
import com.google.android.gms.internal.zzcgn;
import com.google.android.gms.internal.zzchh;
import com.google.android.gms.internal.zzchs;

public class LocationServices {
    public static final Api<Api.ApiOptions.NoOptions> API;
    @Deprecated
    public static final FusedLocationProviderApi FusedLocationApi = new zzcfy();
    @Deprecated
    public static final GeofencingApi GeofencingApi = new zzcgn();
    @Deprecated
    public static final SettingsApi SettingsApi = new zzchs();
    private static final Api.zzf<zzchh> zzegu;
    private static final Api.zza<zzchh, Api.ApiOptions.NoOptions> zzegv;

    public static abstract class zza<R extends Result> extends zzm<R, zzchh> {
        public zza(GoogleApiClient googleApiClient) {
            super((Api<?>) LocationServices.API, googleApiClient);
        }
    }

    static {
        Api.zzf<zzchh> zzf = new Api.zzf<>();
        zzegu = zzf;
        zzad zzad = new zzad();
        zzegv = zzad;
        API = new Api<>("LocationServices.API", zzad, zzf);
    }

    private LocationServices() {
    }

    public static FusedLocationProviderClient getFusedLocationProviderClient(Activity activity) {
        return new FusedLocationProviderClient(activity);
    }

    public static FusedLocationProviderClient getFusedLocationProviderClient(Context context) {
        return new FusedLocationProviderClient(context);
    }

    public static GeofencingClient getGeofencingClient(Activity activity) {
        return new GeofencingClient(activity);
    }

    public static GeofencingClient getGeofencingClient(Context context) {
        return new GeofencingClient(context);
    }

    public static SettingsClient getSettingsClient(Activity activity) {
        return new SettingsClient(activity);
    }

    public static SettingsClient getSettingsClient(Context context) {
        return new SettingsClient(context);
    }

    public static zzchh zzi(GoogleApiClient googleApiClient) {
        boolean z = true;
        zzbq.checkArgument(googleApiClient != null, "GoogleApiClient parameter is required.");
        zzchh zzchh = (zzchh) googleApiClient.zza(zzegu);
        if (zzchh == null) {
            z = false;
        }
        zzbq.zza(z, (Object) "GoogleApiClient is not configured to use the LocationServices.API Api. Pass thisinto GoogleApiClient.Builder#addApi() to use this feature.");
        return zzchh;
    }
}
