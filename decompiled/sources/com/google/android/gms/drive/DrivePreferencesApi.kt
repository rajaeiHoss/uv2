package com.google.android.gms.drive

import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.PendingResult
import com.google.android.gms.common.api.Result
import com.google.android.gms.common.api.Status

@java.lang.Deprecated
interface DrivePreferencesApi {
    @java.lang.Deprecated
    interface FileUploadPreferencesResult : Result {
        val fileUploadPreferences: FileUploadPreferences
    }

    @java.lang.Deprecated
    fun getFileUploadPreferences(
        googleApiClient: GoogleApiClient,
    ): PendingResult<FileUploadPreferencesResult>

    @java.lang.Deprecated
    fun setFileUploadPreferences(
        googleApiClient: GoogleApiClient,
        fileUploadPreferences: FileUploadPreferences,
    ): PendingResult<Status>
}
