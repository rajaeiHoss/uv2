package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.AdBreakInfo;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.internal.zzbek;
import java.util.List;

final class zzn implements zzbek {
    private /* synthetic */ RemoteMediaClient zzfft;

    zzn(RemoteMediaClient remoteMediaClient) {
        this.zzfft = remoteMediaClient;
    }

    private final void zzafp() {
        MediaStatus mediaStatus;
        if (this.zzfft.zzffs != null && (mediaStatus = this.zzfft.getMediaStatus()) != null) {
            mediaStatus.zzbb(this.zzfft.zzffs.parseIsPlayingAdFromMediaStatus(mediaStatus));
            List<AdBreakInfo> parseAdBreaksFromMediaStatus = this.zzfft.zzffs.parseAdBreaksFromMediaStatus(mediaStatus);
            MediaInfo mediaInfo = this.zzfft.getMediaInfo();
            if (mediaInfo != null) {
                mediaInfo.zzaa(parseAdBreaksFromMediaStatus);
            }
        }
    }

    public final void onAdBreakStatusUpdated() {
        for (RemoteMediaClient.Listener onAdBreakStatusUpdated : this.zzfft.zzffp) {
            onAdBreakStatusUpdated.onAdBreakStatusUpdated();
        }
    }

    public final void onMetadataUpdated() {
        zzafp();
        for (RemoteMediaClient.Listener onMetadataUpdated : this.zzfft.zzffp) {
            onMetadataUpdated.onMetadataUpdated();
        }
    }

    public final void onPreloadStatusUpdated() {
        for (RemoteMediaClient.Listener onPreloadStatusUpdated : this.zzfft.zzffp) {
            onPreloadStatusUpdated.onPreloadStatusUpdated();
        }
    }

    public final void onQueueStatusUpdated() {
        for (RemoteMediaClient.Listener onQueueStatusUpdated : this.zzfft.zzffp) {
            onQueueStatusUpdated.onQueueStatusUpdated();
        }
    }

    public final void onStatusUpdated() {
        zzafp();
        this.zzfft.zzafo();
        for (RemoteMediaClient.Listener onStatusUpdated : this.zzfft.zzffp) {
            onStatusUpdated.onStatusUpdated();
        }
    }
}
