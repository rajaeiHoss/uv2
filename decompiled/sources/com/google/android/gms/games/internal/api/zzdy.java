package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.video.Videos;

final class zzdy implements Videos.CaptureAvailableResult {
    private /* synthetic */ Status zzetp;

    zzdy(zzdx zzdx, Status status) {
        this.zzetp = status;
    }

    public final Status getStatus() {
        return this.zzetp;
    }

    public final boolean isAvailable() {
        return false;
    }
}
