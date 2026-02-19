package com.google.android.gms.location.places.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.location.places.AddPlaceRequest;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.AutocompletePredictionBuffer;
import com.google.android.gms.location.places.GeoDataApi;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.PlacePhotoMetadata;
import com.google.android.gms.location.places.PlacePhotoMetadataResult;
import com.google.android.gms.location.places.PlacePhotoResult;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLngBounds;

public final class zzh implements GeoDataApi {
    public final PendingResult<PlaceBuffer> addPlace(GoogleApiClient googleApiClient, AddPlaceRequest addPlaceRequest) {
        zzbq.checkNotNull(addPlaceRequest, "userAddedPlace == null");
        return googleApiClient.zze(new zzi(this, Places.GEO_DATA_API, googleApiClient, addPlaceRequest));
    }

    public final PendingResult<AutocompletePredictionBuffer> getAutocompletePredictions(GoogleApiClient googleApiClient, String str, LatLngBounds latLngBounds, AutocompleteFilter autocompleteFilter) {
        return googleApiClient.zzd(new zzn(this, Places.GEO_DATA_API, googleApiClient, str, latLngBounds, autocompleteFilter));
    }

    public final PendingResult<PlaceBuffer> getPlaceById(GoogleApiClient googleApiClient, String... strArr) {
        zzbq.checkArgument(strArr != null, "placeIds == null");
        zzbq.checkArgument(strArr.length > 0, "placeIds is empty");
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            String str = strArr[i];
            zzbq.checkArgument(str != null, "placeId == null");
            zzbq.checkArgument(!str.isEmpty(), "placeId is empty");
        }
        return googleApiClient.zzd(new zzl(this, Places.GEO_DATA_API, googleApiClient, strArr));
    }

    public final PendingResult<PlacePhotoMetadataResult> getPlacePhotos(GoogleApiClient googleApiClient, String str) {
        zzbq.checkNotNull(str, "placeId == null");
        zzbq.checkArgument(!str.isEmpty(), "placeId is empty");
        return googleApiClient.zzd(new zzj(this, Places.GEO_DATA_API, googleApiClient, str));
    }

    public final PendingResult<PlacePhotoResult> zza(GoogleApiClient googleApiClient, PlacePhotoMetadata placePhotoMetadata, int i, int i2) {
        zzbq.checkNotNull(placePhotoMetadata, "photo == null");
        boolean z = true;
        zzbq.checkArgument(i > 0, "width <= 0");
        if (i2 <= 0) {
            z = false;
        }
        zzbq.checkArgument(z, "height <= 0");
        zzar zzar = (zzar) placePhotoMetadata.freeze();
        String zzaxl = zzar.zzaxl();
        int index = zzar.getIndex();
        zzbq.checkNotNull(zzaxl, "fifeUrl == null");
        return googleApiClient.zzd(new zzk(this, Places.GEO_DATA_API, googleApiClient, zzaxl, i, i2, index));
    }

    public final PendingResult<AutocompletePredictionBuffer> zza(GoogleApiClient googleApiClient, String str, LatLngBounds latLngBounds, int i, AutocompleteFilter autocompleteFilter) {
        return googleApiClient.zzd(new zzm(this, Places.GEO_DATA_API, googleApiClient, str, latLngBounds, i, autocompleteFilter));
    }
}
