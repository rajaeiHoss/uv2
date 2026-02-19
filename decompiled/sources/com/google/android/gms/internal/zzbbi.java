package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.net.Uri;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import com.google.android.gms.R;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.framework.CastOptions;
import com.google.android.gms.cast.framework.ReconnectionService;
import com.google.android.gms.cast.framework.media.MediaNotificationService;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.common.util.zzs;

public final class zzbbi implements RemoteMediaClient.Listener {
    private final Context zzbky;
    private CastDevice zzetm;
    /* access modifiers changed from: private */
    public RemoteMediaClient zzfaq;
    private final zzbaw zzfbo;
    private final CastOptions zzfgz;
    private final ComponentName zzfha;
    private final zzbax zzfhb;
    private final zzbax zzfhc;
    private MediaSessionCompat zzfhd;
    private MediaSessionCompat.Callback zzfhe;
    private boolean zzfhf;

    public zzbbi(Context context, CastOptions castOptions, zzbaw zzbaw) {
        this.zzbky = context;
        this.zzfgz = castOptions;
        this.zzfbo = zzbaw;
        if (castOptions.getCastMediaOptions() == null || TextUtils.isEmpty(castOptions.getCastMediaOptions().getExpandedControllerActivityClassName())) {
            this.zzfha = null;
        } else {
            this.zzfha = new ComponentName(context, castOptions.getCastMediaOptions().getExpandedControllerActivityClassName());
        }
        zzbax zzbax = new zzbax(context);
        this.zzfhb = zzbax;
        zzbax.zza(new zzbbj(this));
        zzbax zzbax2 = new zzbax(context);
        this.zzfhc = zzbax2;
        zzbax2.zza(new zzbbk(this));
    }

    private final Uri zza(MediaMetadata mediaMetadata, int i) {
        WebImage onPickImage = this.zzfgz.getCastMediaOptions().getImagePicker() != null ? this.zzfgz.getCastMediaOptions().getImagePicker().onPickImage(mediaMetadata, i) : mediaMetadata.hasImages() ? mediaMetadata.getImages().get(0) : null;
        if (onPickImage == null) {
            return null;
        }
        return onPickImage.getUrl();
    }

    private final void zza(int i, MediaInfo mediaInfo) {
        PendingIntent pendingIntent;
        if (i == 0) {
            this.zzfhd.setPlaybackState(new PlaybackStateCompat.Builder().setState(0, 0, 1.0f).build());
            this.zzfhd.setMetadata(new MediaMetadataCompat.Builder().build());
            return;
        }
        this.zzfhd.setPlaybackState(new PlaybackStateCompat.Builder().setState(i, 0, 1.0f).setActions(mediaInfo.getStreamType() == 2 ? 5 : 512).build());
        MediaSessionCompat mediaSessionCompat = this.zzfhd;
        if (this.zzfha == null) {
            pendingIntent = null;
        } else {
            Intent intent = new Intent();
            intent.setComponent(this.zzfha);
            pendingIntent = PendingIntent.getActivity(this.zzbky, 0, intent, 134217728);
        }
        mediaSessionCompat.setSessionActivity(pendingIntent);
        MediaMetadata metadata = mediaInfo.getMetadata();
        this.zzfhd.setMetadata(zzaft().putString("android.media.metadata.TITLE", metadata.getString(MediaMetadata.KEY_TITLE)).putString("android.media.metadata.DISPLAY_TITLE", metadata.getString(MediaMetadata.KEY_TITLE)).putString("android.media.metadata.DISPLAY_SUBTITLE", metadata.getString(MediaMetadata.KEY_SUBTITLE)).putLong("android.media.metadata.DURATION", mediaInfo.getStreamDuration()).build());
        Uri zza = zza(metadata, 0);
        if (zza != null) {
            this.zzfhb.zzl(zza);
        } else {
            zza((Bitmap) null, 0);
        }
        Uri zza2 = zza(metadata, 3);
        if (zza2 != null) {
            this.zzfhc.zzl(zza2);
        } else {
            zza((Bitmap) null, 3);
        }
    }

    /* access modifiers changed from: private */
    public final void zza(Bitmap bitmap, int i) {
        if (i == 0) {
            if (bitmap == null) {
                bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
                bitmap.eraseColor(0);
            }
            this.zzfhd.setMetadata(zzaft().putBitmap("android.media.metadata.DISPLAY_ICON", bitmap).build());
        } else if (i == 3) {
            this.zzfhd.setMetadata(zzaft().putBitmap("android.media.metadata.ALBUM_ART", bitmap).build());
        }
    }

    private final MediaMetadataCompat.Builder zzaft() {
        MediaMetadataCompat metadata = this.zzfhd.getController().getMetadata();
        return metadata == null ? new MediaMetadataCompat.Builder() : new MediaMetadataCompat.Builder(metadata);
    }

    private final void zzafu() {
        if (this.zzfgz.getCastMediaOptions().getNotificationOptions() != null) {
            Intent intent = new Intent(this.zzbky, MediaNotificationService.class);
            intent.setPackage(this.zzbky.getPackageName());
            intent.setAction(MediaNotificationService.ACTION_UPDATE_NOTIFICATION);
            this.zzbky.stopService(intent);
        }
    }

    private final void zzafv() {
        if (this.zzfgz.getEnableReconnectionService()) {
            Intent intent = new Intent(this.zzbky, ReconnectionService.class);
            intent.setPackage(this.zzbky.getPackageName());
            this.zzbky.stopService(intent);
        }
    }

    public final void onAdBreakStatusUpdated() {
        zzbc(false);
    }

    public final void onMetadataUpdated() {
        zzbc(false);
    }

    public final void onPreloadStatusUpdated() {
        zzbc(false);
    }

    public final void onQueueStatusUpdated() {
        zzbc(false);
    }

    public final void onSendingRemoteMediaRequest() {
    }

    public final void onStatusUpdated() {
        zzbc(false);
    }

    public final void zza(RemoteMediaClient remoteMediaClient, CastDevice castDevice) {
        CastOptions castOptions;
        if (!this.zzfhf && (castOptions = this.zzfgz) != null && castOptions.getCastMediaOptions() != null && remoteMediaClient != null && castDevice != null) {
            this.zzfaq = remoteMediaClient;
            remoteMediaClient.addListener(this);
            this.zzetm = castDevice;
            if (!zzs.zzanx()) {
                ((AudioManager) this.zzbky.getSystemService("audio")).requestAudioFocus((AudioManager.OnAudioFocusChangeListener) null, 3, 3);
            }
            ComponentName componentName = new ComponentName(this.zzbky, this.zzfgz.getCastMediaOptions().getMediaIntentReceiverClassName());
            Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
            intent.setComponent(componentName);
            MediaSessionCompat mediaSessionCompat = new MediaSessionCompat(this.zzbky, "CastMediaSession", componentName, PendingIntent.getBroadcast(this.zzbky, 0, intent, 0));
            this.zzfhd = mediaSessionCompat;
            mediaSessionCompat.setFlags(3);
            zza(0, (MediaInfo) null);
            CastDevice castDevice2 = this.zzetm;
            if (castDevice2 != null && !TextUtils.isEmpty(castDevice2.getFriendlyName())) {
                this.zzfhd.setMetadata(new MediaMetadataCompat.Builder().putString("android.media.metadata.ALBUM_ARTIST", this.zzbky.getResources().getString(R.string.cast_casting_to_device, new Object[]{this.zzetm.getFriendlyName()})).build());
            }
            zzbbl zzbbl = new zzbbl(this);
            this.zzfhe = zzbbl;
            this.zzfhd.setCallback(zzbbl);
            this.zzfhd.setActive(true);
            this.zzfbo.setMediaSessionCompat(this.zzfhd);
            this.zzfhf = true;
            zzbc(false);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:65:0x00fd, code lost:
        if (r1.intValue() < (r12.getQueueItemCount() - 1)) goto L_0x0103;
     */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0071  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0078  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzbc(boolean r12) {
        /*
            r11 = this;
            com.google.android.gms.cast.framework.media.RemoteMediaClient r0 = r11.zzfaq
            if (r0 != 0) goto L_0x0005
            return
        L_0x0005:
            com.google.android.gms.cast.MediaStatus r0 = r0.getMediaStatus()
            r1 = 0
            if (r0 != 0) goto L_0x000e
            r2 = r1
            goto L_0x0012
        L_0x000e:
            com.google.android.gms.cast.MediaInfo r2 = r0.getMediaInfo()
        L_0x0012:
            if (r2 != 0) goto L_0x0016
            r3 = r1
            goto L_0x001a
        L_0x0016:
            com.google.android.gms.cast.MediaMetadata r3 = r2.getMetadata()
        L_0x001a:
            r4 = 6
            r5 = 3
            r6 = 2
            r7 = 0
            r8 = 1
            if (r0 == 0) goto L_0x006a
            if (r2 == 0) goto L_0x006a
            if (r3 != 0) goto L_0x0026
            goto L_0x006a
        L_0x0026:
            com.google.android.gms.cast.framework.media.RemoteMediaClient r3 = r11.zzfaq
            int r3 = r3.getPlayerState()
            if (r3 == r8) goto L_0x003e
            if (r3 == r6) goto L_0x003b
            if (r3 == r5) goto L_0x0038
            r0 = 4
            if (r3 == r0) goto L_0x0036
            goto L_0x006a
        L_0x0036:
            r3 = 0
            goto L_0x006c
        L_0x0038:
            r3 = 0
        L_0x0039:
            r4 = 2
            goto L_0x006c
        L_0x003b:
            r3 = 0
            r4 = 3
            goto L_0x006c
        L_0x003e:
            int r3 = r0.getIdleReason()
            com.google.android.gms.cast.framework.media.RemoteMediaClient r9 = r11.zzfaq
            boolean r9 = r9.isLiveStream()
            if (r9 == 0) goto L_0x004e
            if (r3 != r6) goto L_0x004e
            r9 = 1
            goto L_0x004f
        L_0x004e:
            r9 = 0
        L_0x004f:
            int r10 = r0.getLoadingItemId()
            if (r10 == 0) goto L_0x005b
            if (r3 == r8) goto L_0x0059
            if (r3 != r5) goto L_0x005b
        L_0x0059:
            r3 = 1
            goto L_0x005c
        L_0x005b:
            r3 = 0
        L_0x005c:
            if (r9 == 0) goto L_0x005f
            goto L_0x0039
        L_0x005f:
            com.google.android.gms.cast.MediaQueueItem r0 = r0.getQueueItemById(r10)
            if (r0 == 0) goto L_0x006b
            com.google.android.gms.cast.MediaInfo r2 = r0.getMedia()
            goto L_0x006c
        L_0x006a:
            r3 = 0
        L_0x006b:
            r4 = 0
        L_0x006c:
            r11.zza((int) r4, (com.google.android.gms.cast.MediaInfo) r2)
            if (r4 != 0) goto L_0x0078
            r11.zzafu()
            r11.zzafv()
            return
        L_0x0078:
            com.google.android.gms.cast.framework.CastOptions r0 = r11.zzfgz
            com.google.android.gms.cast.framework.media.CastMediaOptions r0 = r0.getCastMediaOptions()
            com.google.android.gms.cast.framework.media.NotificationOptions r0 = r0.getNotificationOptions()
            if (r0 == 0) goto L_0x0113
            com.google.android.gms.cast.framework.media.RemoteMediaClient r0 = r11.zzfaq
            if (r0 == 0) goto L_0x0113
            android.content.Intent r0 = new android.content.Intent
            android.content.Context r2 = r11.zzbky
            java.lang.Class<com.google.android.gms.cast.framework.media.MediaNotificationService> r4 = com.google.android.gms.cast.framework.media.MediaNotificationService.class
            r0.<init>(r2, r4)
            java.lang.String r2 = "extra_media_notification_force_update"
            r0.putExtra(r2, r12)
            android.content.Context r12 = r11.zzbky
            java.lang.String r12 = r12.getPackageName()
            r0.setPackage(r12)
            java.lang.String r12 = "com.google.android.gms.cast.framework.action.UPDATE_NOTIFICATION"
            r0.setAction(r12)
            com.google.android.gms.cast.framework.media.RemoteMediaClient r12 = r11.zzfaq
            com.google.android.gms.cast.MediaInfo r12 = r12.getMediaInfo()
            java.lang.String r2 = "extra_media_info"
            r0.putExtra(r2, r12)
            com.google.android.gms.cast.framework.media.RemoteMediaClient r12 = r11.zzfaq
            int r12 = r12.getPlayerState()
            java.lang.String r2 = "extra_remote_media_client_player_state"
            r0.putExtra(r2, r12)
            com.google.android.gms.cast.CastDevice r12 = r11.zzetm
            java.lang.String r2 = "extra_cast_device"
            r0.putExtra(r2, r12)
            android.support.v4.media.session.MediaSessionCompat r12 = r11.zzfhd
            if (r12 != 0) goto L_0x00c6
            goto L_0x00ca
        L_0x00c6:
            android.support.v4.media.session.MediaSessionCompat$Token r1 = r12.getSessionToken()
        L_0x00ca:
            java.lang.String r12 = "extra_media_session_token"
            r0.putExtra(r12, r1)
            com.google.android.gms.cast.framework.media.RemoteMediaClient r12 = r11.zzfaq
            com.google.android.gms.cast.MediaStatus r12 = r12.getMediaStatus()
            if (r12 == 0) goto L_0x010e
            int r1 = r12.getQueueRepeatMode()
            if (r1 == r8) goto L_0x0102
            if (r1 == r6) goto L_0x0102
            if (r1 == r5) goto L_0x0102
            int r1 = r12.getCurrentItemId()
            java.lang.Integer r1 = r12.getIndexById(r1)
            if (r1 == 0) goto L_0x0100
            int r2 = r1.intValue()
            if (r2 <= 0) goto L_0x00f3
            r2 = 1
            goto L_0x00f4
        L_0x00f3:
            r2 = 0
        L_0x00f4:
            int r1 = r1.intValue()
            int r12 = r12.getQueueItemCount()
            int r12 = r12 - r8
            if (r1 >= r12) goto L_0x0104
            goto L_0x0103
        L_0x0100:
            r2 = 0
            goto L_0x0104
        L_0x0102:
            r2 = 1
        L_0x0103:
            r7 = 1
        L_0x0104:
            java.lang.String r12 = "extra_can_skip_next"
            r0.putExtra(r12, r7)
            java.lang.String r12 = "extra_can_skip_prev"
            r0.putExtra(r12, r2)
        L_0x010e:
            android.content.Context r12 = r11.zzbky
            r12.startService(r0)
        L_0x0113:
            if (r3 != 0) goto L_0x0134
            com.google.android.gms.cast.framework.CastOptions r12 = r11.zzfgz
            boolean r12 = r12.getEnableReconnectionService()
            if (r12 == 0) goto L_0x0134
            android.content.Intent r12 = new android.content.Intent
            android.content.Context r0 = r11.zzbky
            java.lang.Class<com.google.android.gms.cast.framework.ReconnectionService> r1 = com.google.android.gms.cast.framework.ReconnectionService.class
            r12.<init>(r0, r1)
            android.content.Context r0 = r11.zzbky
            java.lang.String r0 = r0.getPackageName()
            r12.setPackage(r0)
            android.content.Context r0 = r11.zzbky
            r0.startService(r12)
        L_0x0134:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzbbi.zzbc(boolean):void");
    }

    public final void zzbh(int i) {
        if (this.zzfhf) {
            this.zzfhf = false;
            RemoteMediaClient remoteMediaClient = this.zzfaq;
            if (remoteMediaClient != null) {
                remoteMediaClient.removeListener(this);
            }
            if (!zzs.zzanx()) {
                ((AudioManager) this.zzbky.getSystemService("audio")).abandonAudioFocus((AudioManager.OnAudioFocusChangeListener) null);
            }
            this.zzfbo.setMediaSessionCompat((MediaSessionCompat) null);
            zzbax zzbax = this.zzfhb;
            if (zzbax != null) {
                zzbax.clear();
            }
            zzbax zzbax2 = this.zzfhc;
            if (zzbax2 != null) {
                zzbax2.clear();
            }
            MediaSessionCompat mediaSessionCompat = this.zzfhd;
            if (mediaSessionCompat != null) {
                mediaSessionCompat.setSessionActivity((PendingIntent) null);
                this.zzfhd.setCallback((MediaSessionCompat.Callback) null);
                this.zzfhd.setMetadata(new MediaMetadataCompat.Builder().build());
                zza(0, (MediaInfo) null);
                this.zzfhd.setActive(false);
                this.zzfhd.release();
                this.zzfhd = null;
            }
            this.zzfaq = null;
            this.zzetm = null;
            this.zzfhe = null;
            zzafu();
            if (i == 0) {
                zzafv();
            }
        }
    }
}
