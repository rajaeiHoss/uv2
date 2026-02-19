package com.google.android.gms.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.games.video.Videos;

final class zzda implements zzbo<Videos.CaptureAvailableResult, Boolean> {
    zzda() {
    }

    public final Boolean zzb(Videos.CaptureAvailableResult result) {
        return Boolean.valueOf(result != null && result.isAvailable());
    }
}
