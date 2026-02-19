package com.google.android.gms.location;

import java.util.Comparator;

final class zzh implements Comparator<DetectedActivity> {
    zzh() {
    }

    public final int compare(DetectedActivity detectedActivity, DetectedActivity detectedActivity2) {
        int compareTo = Integer.valueOf(detectedActivity2.getConfidence()).compareTo(Integer.valueOf(detectedActivity.getConfidence()));
        return compareTo == 0 ? Integer.valueOf(detectedActivity.getType()).compareTo(Integer.valueOf(detectedActivity2.getType())) : compareTo;
    }
}
