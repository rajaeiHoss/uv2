package com.google.android.gms.awareness.snapshot

import com.google.android.gms.common.api.Result
import com.google.android.gms.location.ActivityRecognitionResult

interface DetectedActivityResult : Result {
    val activityRecognitionResult: ActivityRecognitionResult
}
