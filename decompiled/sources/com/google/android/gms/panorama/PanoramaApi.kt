package com.google.android.gms.panorama

import android.content.Intent
import android.net.Uri
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.PendingResult
import com.google.android.gms.common.api.Result

interface PanoramaApi {
    interface PanoramaResult : Result {
        val viewerIntent: Intent
    }

    fun loadPanoramaInfo(
        googleApiClient: GoogleApiClient,
        uri: Uri,
    ): PendingResult<PanoramaResult>

    fun loadPanoramaInfoAndGrantAccess(
        googleApiClient: GoogleApiClient,
        uri: Uri,
    ): PendingResult<PanoramaResult>
}
