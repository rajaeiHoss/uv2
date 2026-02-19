package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.video.VideoCapabilities;
import com.google.android.gms.games.video.Videos;

final class zzea implements Videos.CaptureCapabilitiesResult {
    private /* synthetic */ Status zzetp;

    zzea(zzdz zzdz, Status status) {
        this.zzetp = status;
    }

    public final VideoCapabilities getCapabilities() {
        return null;
    }

    public final Status getStatus() {
        return this.zzetp;
    }
}
