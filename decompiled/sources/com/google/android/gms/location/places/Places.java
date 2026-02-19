package com.google.android.gms.location.places;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.location.places.PlacesOptions;
import com.google.android.gms.location.places.internal.zzac;
import com.google.android.gms.location.places.internal.zzae;
import com.google.android.gms.location.places.internal.zzh;
import com.google.android.gms.location.places.internal.zzo;
import com.google.android.gms.location.places.internal.zzq;
import com.google.android.gms.location.places.internal.zzz;

public class Places {
    private static Api.zzf<zzo> zziwc = new Api.zzf<>();
    private static Api.zzf<zzac> zziwd = new Api.zzf<>();
    public static final Api<PlacesOptions> GEO_DATA_API = new Api<>("Places.GEO_DATA_API", new zzq(), zziwc);
    public static final GeoDataApi GeoDataApi = new zzh();
    public static final Api<PlacesOptions> PLACE_DETECTION_API = new Api<>("Places.PLACE_DETECTION_API", new zzae(), zziwd);
    public static final PlaceDetectionApi PlaceDetectionApi = new zzz();

    private Places() {
    }

    public static GeoDataClient getGeoDataClient(Activity activity) {
        return getGeoDataClient(activity, (PlacesOptions) null);
    }

    @Deprecated
    public static GeoDataClient getGeoDataClient(Activity activity, PlacesOptions placesOptions) {
        if (placesOptions == null) {
            placesOptions = new PlacesOptions.Builder().build();
        }
        return new GeoDataClient(activity, placesOptions);
    }

    public static GeoDataClient getGeoDataClient(Context context) {
        return getGeoDataClient(context, (PlacesOptions) null);
    }

    @Deprecated
    public static GeoDataClient getGeoDataClient(Context context, PlacesOptions placesOptions) {
        if (placesOptions == null) {
            placesOptions = new PlacesOptions.Builder().build();
        }
        return new GeoDataClient(context, placesOptions);
    }

    public static PlaceDetectionClient getPlaceDetectionClient(Activity activity) {
        return getPlaceDetectionClient(activity, (PlacesOptions) null);
    }

    @Deprecated
    public static PlaceDetectionClient getPlaceDetectionClient(Activity activity, PlacesOptions placesOptions) {
        if (placesOptions == null) {
            placesOptions = new PlacesOptions.Builder().build();
        }
        return new PlaceDetectionClient(activity, placesOptions);
    }

    public static PlaceDetectionClient getPlaceDetectionClient(Context context) {
        return getPlaceDetectionClient(context, (PlacesOptions) null);
    }

    @Deprecated
    public static PlaceDetectionClient getPlaceDetectionClient(Context context, PlacesOptions placesOptions) {
        if (placesOptions == null) {
            placesOptions = new PlacesOptions.Builder().build();
        }
        return new PlaceDetectionClient(context, placesOptions);
    }
}
