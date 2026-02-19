package com.google.android.gms.location.places;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.zzda;
import com.google.android.gms.common.api.internal.zzg;
import com.google.android.gms.common.internal.zzbj;
import com.google.android.gms.location.places.internal.zzh;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.tasks.Task;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class GeoDataClient extends GoogleApi<PlacesOptions> {

    @Retention(RetentionPolicy.SOURCE)
    public @interface BoundsMode {
        public static final int BIAS = 1;
        public static final int STRICT = 2;
    }

    GeoDataClient(Activity activity, PlacesOptions placesOptions) {
        super(activity, Places.GEO_DATA_API, placesOptions, (zzda) new zzg());
    }

    GeoDataClient(Context context, PlacesOptions placesOptions) {
        super(context, Places.GEO_DATA_API, placesOptions, (zzda) new zzg());
    }

    @Deprecated
    public Task<PlaceBufferResponse> addPlace(AddPlaceRequest addPlaceRequest) {
        return zzbj.zza(Places.GeoDataApi.addPlace(zzahw(), addPlaceRequest), new PlaceBufferResponse());
    }

    public Task<AutocompletePredictionBufferResponse> getAutocompletePredictions(String str, LatLngBounds latLngBounds, int i, AutocompleteFilter autocompleteFilter) {
        return zzbj.zza(((zzh) Places.GeoDataApi).zza(zzahw(), str, latLngBounds, i, autocompleteFilter), new AutocompletePredictionBufferResponse());
    }

    public Task<AutocompletePredictionBufferResponse> getAutocompletePredictions(String str, LatLngBounds latLngBounds, AutocompleteFilter autocompleteFilter) {
        return getAutocompletePredictions(str, latLngBounds, 1, autocompleteFilter);
    }

    public Task<PlacePhotoResponse> getPhoto(PlacePhotoMetadata placePhotoMetadata) {
        return getScaledPhoto(placePhotoMetadata, placePhotoMetadata.getMaxWidth(), placePhotoMetadata.getMaxHeight());
    }

    public Task<PlaceBufferResponse> getPlaceById(String... strArr) {
        return zzbj.zza(Places.GeoDataApi.getPlaceById(zzahw(), strArr), new PlaceBufferResponse());
    }

    public Task<PlacePhotoMetadataResponse> getPlacePhotos(String str) {
        return zzbj.zza(Places.GeoDataApi.getPlacePhotos(zzahw(), str), new PlacePhotoMetadataResponse());
    }

    public Task<PlacePhotoResponse> getScaledPhoto(PlacePhotoMetadata placePhotoMetadata, int i, int i2) {
        return zzbj.zza(((zzh) Places.GeoDataApi).zza(zzahw(), placePhotoMetadata, i, i2), new PlacePhotoResponse());
    }
}
