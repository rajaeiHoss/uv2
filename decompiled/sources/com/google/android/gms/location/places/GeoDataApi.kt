package com.google.android.gms.location.places

import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.PendingResult
import com.google.android.gms.maps.model.LatLngBounds

interface GeoDataApi {
    @java.lang.Deprecated
    fun addPlace(
        googleApiClient: GoogleApiClient,
        request: AddPlaceRequest,
    ): PendingResult<PlaceBuffer>

    fun getAutocompletePredictions(
        googleApiClient: GoogleApiClient,
        query: String,
        bounds: LatLngBounds,
        filter: AutocompleteFilter,
    ): PendingResult<AutocompletePredictionBuffer>

    fun getPlaceById(
        googleApiClient: GoogleApiClient,
        vararg placeIds: String,
    ): PendingResult<PlaceBuffer>

    fun getPlacePhotos(
        googleApiClient: GoogleApiClient,
        placeId: String,
    ): PendingResult<PlacePhotoMetadataResult>
}
