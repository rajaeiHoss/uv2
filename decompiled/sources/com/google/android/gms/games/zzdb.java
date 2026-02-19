package com.google.android.gms.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.games.video.CaptureState;
import com.google.android.gms.games.video.Videos;

final class zzdb implements zzbo<Videos.CaptureStateResult, CaptureState> {
    zzdb() {
    }

    public final CaptureState zzb(Videos.CaptureStateResult result) {
        if (result == null) {
            return null;
        }
        return result.getCaptureState();
    }
}
