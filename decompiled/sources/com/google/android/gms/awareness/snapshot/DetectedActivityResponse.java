package com.google.android.gms.awareness.snapshot;

import com.google.android.gms.common.api.Response;
import com.google.android.gms.location.ActivityRecognitionResult;

public class DetectedActivityResponse extends Response<DetectedActivityResult> {
    public ActivityRecognitionResult getActivityRecognitionResult() {
        return ((DetectedActivityResult) getResult()).getActivityRecognitionResult();
    }
}
