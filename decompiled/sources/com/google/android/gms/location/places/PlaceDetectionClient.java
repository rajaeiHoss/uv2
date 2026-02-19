package com.google.android.gms.location.places;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.internal.zzbj;
import com.google.android.gms.tasks.Task;

public class PlaceDetectionClient extends GoogleApi<PlacesOptions> {
    PlaceDetectionClient(Activity activity, PlacesOptions placesOptions) {
        super(activity, Places.PLACE_DETECTION_API, placesOptions, GoogleApi.zza.zzfsr);
    }

    private PlaceDetectionClient(Context context, Api<PlacesOptions> api, PlacesOptions placesOptions) {
        super(context, api, placesOptions, GoogleApi.zza.zzfsr);
    }

    PlaceDetectionClient(Context context, PlacesOptions placesOptions) {
        this(context, Places.PLACE_DETECTION_API, placesOptions);
    }

    public Task<PlaceLikelihoodBufferResponse> getCurrentPlace(PlaceFilter placeFilter) {
        return zzbj.zza(Places.PlaceDetectionApi.getCurrentPlace(zzahw(), placeFilter), new PlaceLikelihoodBufferResponse());
    }

    public Task<Void> reportDeviceAtPlace(PlaceReport placeReport) {
        return zzbj.zzb(Places.PlaceDetectionApi.reportDeviceAtPlace(zzahw(), placeReport));
    }
}
