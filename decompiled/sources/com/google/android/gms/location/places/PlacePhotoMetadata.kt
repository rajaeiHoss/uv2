package com.google.android.gms.location.places

import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.PendingResult
import com.google.android.gms.common.data.Freezable

interface PlacePhotoMetadata : Freezable<PlacePhotoMetadata> {
    val attributions: CharSequence?

    val maxHeight: Int

    val maxWidth: Int

    fun getPhoto(googleApiClient: GoogleApiClient): PendingResult<PlacePhotoResult>

    fun getScaledPhoto(
        googleApiClient: GoogleApiClient,
        width: Int,
        height: Int,
    ): PendingResult<PlacePhotoResult>
}
