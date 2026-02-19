package com.google.android.gms.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.games.video.VideoCapabilities;
import com.google.android.gms.games.video.Videos;

final class zzdc implements zzbo<Videos.CaptureCapabilitiesResult, VideoCapabilities> {
    zzdc() {
    }

    public final VideoCapabilities zzb(Videos.CaptureCapabilitiesResult result) {
        if (result == null) {
            return null;
        }
        return result.getCapabilities();
    }
}
