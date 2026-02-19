package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.video.CaptureState;
import com.google.android.gms.games.video.Videos;

final class zzec implements Videos.CaptureStateResult {
    private /* synthetic */ Status zzetp;

    zzec(zzeb zzeb, Status status) {
        this.zzetp = status;
    }

    public final CaptureState getCaptureState() {
        return null;
    }

    public final Status getStatus() {
        return this.zzetp;
    }
}
