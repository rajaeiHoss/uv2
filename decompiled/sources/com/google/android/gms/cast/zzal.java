package com.google.android.gms.cast;

import com.google.android.gms.internal.zzbek;

final class zzal implements zzbek {
    private /* synthetic */ RemoteMediaPlayer zzexz;

    zzal(RemoteMediaPlayer remoteMediaPlayer) {
        this.zzexz = remoteMediaPlayer;
    }

    public final void onAdBreakStatusUpdated() {
    }

    public final void onMetadataUpdated() {
        this.zzexz.onMetadataUpdated();
    }

    public final void onPreloadStatusUpdated() {
        this.zzexz.onPreloadStatusUpdated();
    }

    public final void onQueueStatusUpdated() {
        this.zzexz.onQueueStatusUpdated();
    }

    public final void onStatusUpdated() {
        this.zzexz.onStatusUpdated();
    }
}
