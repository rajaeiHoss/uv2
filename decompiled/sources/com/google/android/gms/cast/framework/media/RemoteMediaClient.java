package com.google.android.gms.cast.framework.media;

import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.cast.AdBreakInfo;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaLoadOptions;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.TextTrackStyle;
import com.google.android.gms.cast.zzbl;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BasePendingResult;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbdf;
import com.google.android.gms.internal.zzbdp;
import com.google.android.gms.internal.zzbej;
import com.google.android.gms.internal.zzbek;
import com.google.android.gms.internal.zzbem;
import com.google.android.gms.internal.zzben;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONObject;

public class RemoteMediaClient implements Cast.MessageReceivedCallback {
    public static final String NAMESPACE = zzbej.NAMESPACE;
    public static final int RESUME_STATE_PAUSE = 2;
    public static final int RESUME_STATE_PLAY = 1;
    public static final int RESUME_STATE_UNCHANGED = 0;
    public static final int STATUS_FAILED = 2100;
    public static final int STATUS_REPLACED = 2103;
    public static final int STATUS_SUCCEEDED = 0;
    final Handler mHandler = new Handler(Looper.getMainLooper());
    /* access modifiers changed from: private */
    public final Object mLock = new Object();
    final zzbej zzext;
    /* access modifiers changed from: private */
    public final Cast.CastApi zzfam;
    private final zza zzffn;
    private GoogleApiClient zzffo;
    /* access modifiers changed from: private */
    public final List<Listener> zzffp = new CopyOnWriteArrayList();
    private final Map<ProgressListener, zze> zzffq = new ConcurrentHashMap();
    private final Map<Long, zze> zzffr = new ConcurrentHashMap();
    /* access modifiers changed from: private */
    public ParseAdsInfoCallback zzffs;

    public interface Listener {
        void onAdBreakStatusUpdated();

        void onMetadataUpdated();

        void onPreloadStatusUpdated();

        void onQueueStatusUpdated();

        void onSendingRemoteMediaRequest();

        void onStatusUpdated();
    }

    public interface MediaChannelResult extends Result {
        JSONObject getCustomData();
    }

    public interface ParseAdsInfoCallback {
        List<AdBreakInfo> parseAdBreaksFromMediaStatus(MediaStatus mediaStatus);

        boolean parseIsPlayingAdFromMediaStatus(MediaStatus mediaStatus);
    }

    public interface ProgressListener {
        void onProgressUpdated(long j, long j2);
    }

    class zza implements zzbem {
        private GoogleApiClient zzeyx;
        private long zzeyy = 0;

        public zza() {
        }

        public final void zza(String str, String str2, long j, String str3) {
            if (this.zzeyx != null) {
                RemoteMediaClient.this.zzfam.sendMessage(this.zzeyx, str, str2).setResultCallback(new zzam(RemoteMediaClient.this, j));
                return;
            }
            throw new IllegalStateException("No GoogleApiClient available");
        }

        public final long zzadw() {
            long j = this.zzeyy + 1;
            this.zzeyy = j;
            return j;
        }

        public final void zzb(GoogleApiClient googleApiClient) {
            this.zzeyx = googleApiClient;
        }
    }

    abstract static class zzb extends zzbdf<MediaChannelResult> {
        zzben zzezb;
        private final boolean zzfga;

        zzb(RemoteMediaClient remoteMediaClient, GoogleApiClient googleApiClient) {
            this(remoteMediaClient, googleApiClient, false);
        }

        zzb(RemoteMediaClient remoteMediaClient, GoogleApiClient googleApiClient, boolean z) {
            super(googleApiClient);
            this.zzfga = z;
            this.zzezb = new zzan(this, remoteMediaClient);
        }

        /* access modifiers changed from: protected */
        public void zza(zzbdp zzbdp) {
        }

        public final MediaChannelResult zzb(Status status) {
            return new zzao(this, status);
        }
    }

    static final class zzc implements MediaChannelResult {
        private final Status mStatus;
        private final JSONObject zzess;

        zzc(Status status, JSONObject jSONObject) {
            this.mStatus = status;
            this.zzess = jSONObject;
        }

        public final JSONObject getCustomData() {
            return this.zzess;
        }

        public final Status getStatus() {
            return this.mStatus;
        }
    }

    static class zzd extends BasePendingResult<MediaChannelResult> {
        zzd() {
            super((GoogleApiClient) null);
        }

        /* access modifiers changed from: protected */
        /* renamed from: zzk */
        public final MediaChannelResult zzb(Status status) {
            return new zzap(this, status);
        }
    }

    class zze {
        /* access modifiers changed from: private */
        public final Set<ProgressListener> zzfgd = new HashSet();
        /* access modifiers changed from: private */
        public final long zzfge;
        private final Runnable zzfgf;
        private boolean zzfgg;

        public zze(long j) {
            this.zzfge = j;
            this.zzfgf = new zzaq(this, RemoteMediaClient.this);
        }

        public final boolean isStarted() {
            return this.zzfgg;
        }

        public final void start() {
            RemoteMediaClient.this.mHandler.removeCallbacks(this.zzfgf);
            this.zzfgg = true;
            RemoteMediaClient.this.mHandler.postDelayed(this.zzfgf, this.zzfge);
        }

        public final void stop() {
            RemoteMediaClient.this.mHandler.removeCallbacks(this.zzfgf);
            this.zzfgg = false;
        }

        public final void zza(ProgressListener progressListener) {
            this.zzfgd.add(progressListener);
        }

        public final long zzafq() {
            return this.zzfge;
        }

        public final boolean zzafr() {
            return !this.zzfgd.isEmpty();
        }

        public final void zzb(ProgressListener progressListener) {
            this.zzfgd.remove(progressListener);
        }
    }

    public RemoteMediaClient(zzbej zzbej, Cast.CastApi castApi) {
        zza zza2 = new zza();
        this.zzffn = zza2;
        this.zzfam = castApi;
        zzbej zzbej2 = (zzbej) zzbq.checkNotNull(zzbej);
        this.zzext = zzbej2;
        zzbej2.zza((zzbek) new zzn(this));
        zzbej2.zza(zza2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:?, code lost:
        r3.setResult((com.google.android.gms.cast.framework.media.RemoteMediaClient.MediaChannelResult) r3.zzb(new com.google.android.gms.common.api.Status(2100)));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0016, code lost:
        return r3;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0006 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.android.gms.cast.framework.media.RemoteMediaClient.zzb zza(com.google.android.gms.cast.framework.media.RemoteMediaClient.zzb r3) {
        /*
            r2 = this;
            com.google.android.gms.common.api.GoogleApiClient r0 = r2.zzffo     // Catch:{ IllegalStateException -> 0x0006 }
            r0.zze(r3)     // Catch:{ IllegalStateException -> 0x0006 }
            return r3
        L_0x0006:
            com.google.android.gms.common.api.Status r0 = new com.google.android.gms.common.api.Status     // Catch:{ all -> 0x0016 }
            r1 = 2100(0x834, float:2.943E-42)
            r0.<init>(r1)     // Catch:{ all -> 0x0016 }
            com.google.android.gms.common.api.Result r0 = r3.zzb((com.google.android.gms.common.api.Status) r0)     // Catch:{ all -> 0x0016 }
            com.google.android.gms.cast.framework.media.RemoteMediaClient$MediaChannelResult r0 = (com.google.android.gms.cast.framework.media.RemoteMediaClient.MediaChannelResult) r0     // Catch:{ all -> 0x0016 }
            r3.setResult(r0)     // Catch:{ all -> 0x0016 }
        L_0x0016:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.framework.media.RemoteMediaClient.zza(com.google.android.gms.cast.framework.media.RemoteMediaClient$zzb):com.google.android.gms.cast.framework.media.RemoteMediaClient$zzb");
    }

    final void zza(Set<ProgressListener> set) {
        if (!isBuffering() && !isPaused()) {
            HashSet<ProgressListener> hashSet = new HashSet<>(set);
            if (isPlaying()) {
                for (ProgressListener onProgressUpdated : hashSet) {
                    onProgressUpdated.onProgressUpdated(getApproximateStreamPosition(), getStreamDuration());
                }
            } else if (isLoadingNextItem()) {
                MediaQueueItem loadingItem = getLoadingItem();
                if (loadingItem != null && loadingItem.getMedia() != null) {
                    for (ProgressListener onProgressUpdated2 : hashSet) {
                        onProgressUpdated2.onProgressUpdated(0, loadingItem.getMedia().getStreamDuration());
                    }
                }
            } else {
                for (ProgressListener onProgressUpdated3 : hashSet) {
                    onProgressUpdated3.onProgressUpdated(0, 0);
                }
            }
        }
    }

    private final boolean zzafm() {
        return this.zzffo != null;
    }

    private static PendingResult<MediaChannelResult> zzafn() {
        zzd zzd2 = new zzd();
        zzd2.setResult(zzd2.zzb(new Status(17)));
        return zzd2;
    }

    /* access modifiers changed from: private */
    public final void zzafo() {
        for (zze next : this.zzffr.values()) {
            if (hasMediaSession() && !next.isStarted()) {
                next.start();
            } else if (!hasMediaSession() && next.isStarted()) {
                next.stop();
            }
            if (next.isStarted() && (isBuffering() || isPaused() || isLoadingNextItem())) {
                zza((Set<ProgressListener>) next.zzfgd);
            }
        }
    }

    /* access modifiers changed from: private */
    public final int zzbc(int i) {
        zzbq.zzgn("Must be called from the main thread.");
        MediaStatus mediaStatus = getMediaStatus();
        for (int i2 = 0; i2 < mediaStatus.getQueueItemCount(); i2++) {
            if (mediaStatus.getQueueItem(i2).getItemId() == i) {
                return i2;
            }
        }
        return -1;
    }

    public void addListener(Listener listener) {
        zzbq.zzgn("Must be called from the main thread.");
        if (listener != null) {
            this.zzffp.add(listener);
        }
    }

    public boolean addProgressListener(ProgressListener progressListener, long j) {
        zzbq.zzgn("Must be called from the main thread.");
        if (progressListener == null || this.zzffq.containsKey(progressListener)) {
            return false;
        }
        zze zze2 = this.zzffr.get(Long.valueOf(j));
        if (zze2 == null) {
            zze2 = new zze(j);
            this.zzffr.put(Long.valueOf(j), zze2);
        }
        zze2.zza(progressListener);
        this.zzffq.put(progressListener, zze2);
        if (!hasMediaSession()) {
            return true;
        }
        zze2.start();
        return true;
    }

    public long getApproximateStreamPosition() {
        long approximateStreamPosition;
        synchronized (this.mLock) {
            zzbq.zzgn("Must be called from the main thread.");
            approximateStreamPosition = this.zzext.getApproximateStreamPosition();
        }
        return approximateStreamPosition;
    }

    public MediaQueueItem getCurrentItem() {
        zzbq.zzgn("Must be called from the main thread.");
        MediaStatus mediaStatus = getMediaStatus();
        if (mediaStatus == null) {
            return null;
        }
        return mediaStatus.getQueueItemById(mediaStatus.getCurrentItemId());
    }

    public int getIdleReason() {
        int idleReason;
        synchronized (this.mLock) {
            zzbq.zzgn("Must be called from the main thread.");
            MediaStatus mediaStatus = getMediaStatus();
            idleReason = mediaStatus != null ? mediaStatus.getIdleReason() : 0;
        }
        return idleReason;
    }

    public MediaQueueItem getLoadingItem() {
        zzbq.zzgn("Must be called from the main thread.");
        MediaStatus mediaStatus = getMediaStatus();
        if (mediaStatus == null) {
            return null;
        }
        return mediaStatus.getQueueItemById(mediaStatus.getLoadingItemId());
    }

    public MediaInfo getMediaInfo() {
        MediaInfo mediaInfo;
        synchronized (this.mLock) {
            zzbq.zzgn("Must be called from the main thread.");
            mediaInfo = this.zzext.getMediaInfo();
        }
        return mediaInfo;
    }

    public MediaStatus getMediaStatus() {
        MediaStatus mediaStatus;
        synchronized (this.mLock) {
            zzbq.zzgn("Must be called from the main thread.");
            mediaStatus = this.zzext.getMediaStatus();
        }
        return mediaStatus;
    }

    public String getNamespace() {
        zzbq.zzgn("Must be called from the main thread.");
        return this.zzext.getNamespace();
    }

    public int getPlayerState() {
        int playerState;
        synchronized (this.mLock) {
            zzbq.zzgn("Must be called from the main thread.");
            MediaStatus mediaStatus = getMediaStatus();
            playerState = mediaStatus != null ? mediaStatus.getPlayerState() : 1;
        }
        return playerState;
    }

    public MediaQueueItem getPreloadedItem() {
        zzbq.zzgn("Must be called from the main thread.");
        MediaStatus mediaStatus = getMediaStatus();
        if (mediaStatus == null) {
            return null;
        }
        return mediaStatus.getQueueItemById(mediaStatus.getPreloadedItemId());
    }

    public long getStreamDuration() {
        long streamDuration;
        synchronized (this.mLock) {
            zzbq.zzgn("Must be called from the main thread.");
            streamDuration = this.zzext.getStreamDuration();
        }
        return streamDuration;
    }

    public boolean hasMediaSession() {
        zzbq.zzgn("Must be called from the main thread.");
        return isBuffering() || isPlaying() || isPaused() || isLoadingNextItem();
    }

    public boolean isBuffering() {
        zzbq.zzgn("Must be called from the main thread.");
        MediaStatus mediaStatus = getMediaStatus();
        return mediaStatus != null && mediaStatus.getPlayerState() == 4;
    }

    public boolean isLiveStream() {
        zzbq.zzgn("Must be called from the main thread.");
        MediaInfo mediaInfo = getMediaInfo();
        return mediaInfo != null && mediaInfo.getStreamType() == 2;
    }

    public boolean isLoadingNextItem() {
        zzbq.zzgn("Must be called from the main thread.");
        MediaStatus mediaStatus = getMediaStatus();
        return (mediaStatus == null || mediaStatus.getLoadingItemId() == 0) ? false : true;
    }

    public boolean isPaused() {
        zzbq.zzgn("Must be called from the main thread.");
        MediaStatus mediaStatus = getMediaStatus();
        if (mediaStatus == null) {
            return false;
        }
        if (mediaStatus.getPlayerState() != 3) {
            return isLiveStream() && getIdleReason() == 2;
        }
        return true;
    }

    public boolean isPlaying() {
        zzbq.zzgn("Must be called from the main thread.");
        MediaStatus mediaStatus = getMediaStatus();
        return mediaStatus != null && mediaStatus.getPlayerState() == 2;
    }

    public boolean isPlayingAd() {
        zzbq.zzgn("Must be called from the main thread.");
        MediaStatus mediaStatus = getMediaStatus();
        return mediaStatus != null && mediaStatus.isPlayingAd();
    }

    @Deprecated
    public PendingResult<MediaChannelResult> load(MediaInfo mediaInfo) {
        return load(mediaInfo, new MediaLoadOptions.Builder().build());
    }

    public PendingResult<MediaChannelResult> load(MediaInfo mediaInfo, MediaLoadOptions mediaLoadOptions) {
        zzbq.zzgn("Must be called from the main thread.");
        return !zzafm() ? zzafn() : zza((zzb) new zzy(this, this.zzffo, mediaInfo, mediaLoadOptions));
    }

    @Deprecated
    public PendingResult<MediaChannelResult> load(MediaInfo mediaInfo, boolean z) {
        return load(mediaInfo, new MediaLoadOptions.Builder().setAutoplay(z).build());
    }

    @Deprecated
    public PendingResult<MediaChannelResult> load(MediaInfo mediaInfo, boolean z, long j) {
        return load(mediaInfo, new MediaLoadOptions.Builder().setAutoplay(z).setPlayPosition(j).build());
    }

    @Deprecated
    public PendingResult<MediaChannelResult> load(MediaInfo mediaInfo, boolean z, long j, JSONObject jSONObject) {
        return load(mediaInfo, new MediaLoadOptions.Builder().setAutoplay(z).setPlayPosition(j).setCustomData(jSONObject).build());
    }

    @Deprecated
    public PendingResult<MediaChannelResult> load(MediaInfo mediaInfo, boolean z, long j, long[] jArr, JSONObject jSONObject) {
        return load(mediaInfo, new MediaLoadOptions.Builder().setAutoplay(z).setPlayPosition(j).setActiveTrackIds(jArr).setCustomData(jSONObject).build());
    }

    public void onMessageReceived(CastDevice castDevice, String str, String str2) {
        this.zzext.zzfu(str2);
    }

    public PendingResult<MediaChannelResult> pause() {
        return pause((JSONObject) null);
    }

    public PendingResult<MediaChannelResult> pause(JSONObject jSONObject) {
        zzbq.zzgn("Must be called from the main thread.");
        return !zzafm() ? zzafn() : zza((zzb) new zzaf(this, this.zzffo, jSONObject));
    }

    public PendingResult<MediaChannelResult> play() {
        return play((JSONObject) null);
    }

    public PendingResult<MediaChannelResult> play(JSONObject jSONObject) {
        zzbq.zzgn("Must be called from the main thread.");
        return !zzafm() ? zzafn() : zza((zzb) new zzah(this, this.zzffo, jSONObject));
    }

    public PendingResult<MediaChannelResult> queueAppendItem(MediaQueueItem mediaQueueItem, JSONObject jSONObject) throws IllegalArgumentException {
        return queueInsertItems(new MediaQueueItem[]{mediaQueueItem}, 0, jSONObject);
    }

    public PendingResult<MediaChannelResult> queueInsertAndPlayItem(MediaQueueItem mediaQueueItem, int i, long j, JSONObject jSONObject) {
        zzbq.zzgn("Must be called from the main thread.");
        if (!zzafm()) {
            return zzafn();
        }
        return zza((zzb) new zzt(this, this.zzffo, mediaQueueItem, i, j, jSONObject));
    }

    public PendingResult<MediaChannelResult> queueInsertAndPlayItem(MediaQueueItem mediaQueueItem, int i, JSONObject jSONObject) {
        return queueInsertAndPlayItem(mediaQueueItem, i, -1, jSONObject);
    }

    public PendingResult<MediaChannelResult> queueInsertItems(MediaQueueItem[] mediaQueueItemArr, int i, JSONObject jSONObject) throws IllegalArgumentException {
        zzbq.zzgn("Must be called from the main thread.");
        if (!zzafm()) {
            return zzafn();
        }
        return zza((zzb) new zzs(this, this.zzffo, mediaQueueItemArr, i, jSONObject));
    }

    public PendingResult<MediaChannelResult> queueJumpToItem(int i, long j, JSONObject jSONObject) {
        zzbq.zzgn("Must be called from the main thread.");
        if (!zzafm()) {
            return zzafn();
        }
        return zza((zzb) new zzac(this, this.zzffo, i, j, jSONObject));
    }

    public PendingResult<MediaChannelResult> queueJumpToItem(int i, JSONObject jSONObject) {
        return queueJumpToItem(i, -1, jSONObject);
    }

    public PendingResult<MediaChannelResult> queueLoad(MediaQueueItem[] mediaQueueItemArr, int i, int i2, long j, JSONObject jSONObject) throws IllegalArgumentException {
        zzbq.zzgn("Must be called from the main thread.");
        if (!zzafm()) {
            return zzafn();
        }
        return zza((zzb) new zzr(this, this.zzffo, mediaQueueItemArr, i, i2, j, jSONObject));
    }

    public PendingResult<MediaChannelResult> queueLoad(MediaQueueItem[] mediaQueueItemArr, int i, int i2, JSONObject jSONObject) throws IllegalArgumentException {
        return queueLoad(mediaQueueItemArr, i, i2, -1, jSONObject);
    }

    public PendingResult<MediaChannelResult> queueMoveItemToNewIndex(int i, int i2, JSONObject jSONObject) {
        zzbq.zzgn("Must be called from the main thread.");
        if (!zzafm()) {
            return zzafn();
        }
        return zza((zzb) new zzad(this, this.zzffo, i, i2, jSONObject));
    }

    public PendingResult<MediaChannelResult> queueNext(JSONObject jSONObject) {
        zzbq.zzgn("Must be called from the main thread.");
        return !zzafm() ? zzafn() : zza((zzb) new zzz(this, this.zzffo, jSONObject));
    }

    public PendingResult<MediaChannelResult> queuePrev(JSONObject jSONObject) {
        zzbq.zzgn("Must be called from the main thread.");
        return !zzafm() ? zzafn() : zza((zzb) new zzx(this, this.zzffo, jSONObject));
    }

    public PendingResult<MediaChannelResult> queueRemoveItem(int i, JSONObject jSONObject) {
        zzbq.zzgn("Must be called from the main thread.");
        return !zzafm() ? zzafn() : zza((zzb) new zzab(this, this.zzffo, i, jSONObject));
    }

    public PendingResult<MediaChannelResult> queueRemoveItems(int[] iArr, JSONObject jSONObject) throws IllegalArgumentException {
        zzbq.zzgn("Must be called from the main thread.");
        return !zzafm() ? zzafn() : zza((zzb) new zzv(this, this.zzffo, iArr, jSONObject));
    }

    public PendingResult<MediaChannelResult> queueReorderItems(int[] iArr, int i, JSONObject jSONObject) throws IllegalArgumentException {
        zzbq.zzgn("Must be called from the main thread.");
        if (!zzafm()) {
            return zzafn();
        }
        return zza((zzb) new zzw(this, this.zzffo, iArr, i, jSONObject));
    }

    public PendingResult<MediaChannelResult> queueSetRepeatMode(int i, JSONObject jSONObject) {
        zzbq.zzgn("Must be called from the main thread.");
        return !zzafm() ? zzafn() : zza((zzb) new zzaa(this, this.zzffo, i, jSONObject));
    }

    public PendingResult<MediaChannelResult> queueUpdateItems(MediaQueueItem[] mediaQueueItemArr, JSONObject jSONObject) {
        zzbq.zzgn("Must be called from the main thread.");
        return !zzafm() ? zzafn() : zza((zzb) new zzu(this, this.zzffo, mediaQueueItemArr, jSONObject));
    }

    public void removeListener(Listener listener) {
        zzbq.zzgn("Must be called from the main thread.");
        if (listener != null) {
            this.zzffp.remove(listener);
        }
    }

    public void removeProgressListener(ProgressListener progressListener) {
        zzbq.zzgn("Must be called from the main thread.");
        zze remove = this.zzffq.remove(progressListener);
        if (remove != null) {
            remove.zzb(progressListener);
            if (!remove.zzafr()) {
                this.zzffr.remove(Long.valueOf(remove.zzafq()));
                remove.stop();
            }
        }
    }

    public PendingResult<MediaChannelResult> requestStatus() {
        zzbq.zzgn("Must be called from the main thread.");
        return !zzafm() ? zzafn() : zza((zzb) new zzo(this, this.zzffo));
    }

    public PendingResult<MediaChannelResult> seek(long j) {
        return seek(j, 0, (JSONObject) null);
    }

    public PendingResult<MediaChannelResult> seek(long j, int i) {
        return seek(j, i, (JSONObject) null);
    }

    public PendingResult<MediaChannelResult> seek(long j, int i, JSONObject jSONObject) {
        zzbq.zzgn("Must be called from the main thread.");
        if (!zzafm()) {
            return zzafn();
        }
        return zza((zzb) new zzai(this, this.zzffo, j, i, jSONObject));
    }

    public PendingResult<MediaChannelResult> setActiveMediaTracks(long[] jArr) {
        zzbq.zzgn("Must be called from the main thread.");
        if (!zzafm()) {
            return zzafn();
        }
        if (jArr != null) {
            return zza((zzb) new zzp(this, this.zzffo, jArr));
        }
        throw new IllegalArgumentException("trackIds cannot be null");
    }

    public void setParseAdsInfoCallback(ParseAdsInfoCallback parseAdsInfoCallback) {
        zzbq.zzgn("Must be called from the main thread.");
        this.zzffs = parseAdsInfoCallback;
    }

    public PendingResult<MediaChannelResult> setPlaybackRate(double d) {
        return setPlaybackRate(d, (JSONObject) null);
    }

    public PendingResult<MediaChannelResult> setPlaybackRate(double d, JSONObject jSONObject) {
        zzbq.zzgn("Must be called from the main thread.");
        if (!zzafm()) {
            return zzafn();
        }
        if (Double.compare(d, 2.0d) > 0 || Double.compare(d, 0.5d) < 0) {
            throw new IllegalArgumentException("playbackRate must be between PLAYBACK_RATE_MIN and PLAYBACK_RATE_MAX");
        }
        return zza((zzb) new zzal(this, this.zzffo, d, jSONObject));
    }

    public PendingResult<MediaChannelResult> setStreamMute(boolean z) {
        return setStreamMute(z, (JSONObject) null);
    }

    public PendingResult<MediaChannelResult> setStreamMute(boolean z, JSONObject jSONObject) {
        zzbq.zzgn("Must be called from the main thread.");
        return !zzafm() ? zzafn() : zza((zzb) new zzak(this, this.zzffo, z, jSONObject));
    }

    public PendingResult<MediaChannelResult> setStreamVolume(double d) throws IllegalArgumentException {
        return setStreamVolume(d, (JSONObject) null);
    }

    public PendingResult<MediaChannelResult> setStreamVolume(double d, JSONObject jSONObject) throws IllegalArgumentException {
        zzbq.zzgn("Must be called from the main thread.");
        if (!zzafm()) {
            return zzafn();
        }
        if (Double.isInfinite(d) || Double.isNaN(d)) {
            StringBuilder sb = new StringBuilder(41);
            sb.append("Volume cannot be ");
            sb.append(d);
            throw new IllegalArgumentException(sb.toString());
        }
        return zza((zzb) new zzaj(this, this.zzffo, d, jSONObject));
    }

    public PendingResult<MediaChannelResult> setTextTrackStyle(TextTrackStyle textTrackStyle) {
        zzbq.zzgn("Must be called from the main thread.");
        if (!zzafm()) {
            return zzafn();
        }
        if (textTrackStyle != null) {
            return zza((zzb) new zzq(this, this.zzffo, textTrackStyle));
        }
        throw new IllegalArgumentException("trackStyle cannot be null");
    }

    public PendingResult<MediaChannelResult> stop() {
        return stop((JSONObject) null);
    }

    public PendingResult<MediaChannelResult> stop(JSONObject jSONObject) {
        zzbq.zzgn("Must be called from the main thread.");
        return !zzafm() ? zzafn() : zza((zzb) new zzag(this, this.zzffo, jSONObject));
    }

    public void togglePlayback() {
        zzbq.zzgn("Must be called from the main thread.");
        int playerState = getPlayerState();
        if (playerState == 4 || playerState == 2) {
            pause();
        } else {
            play();
        }
    }

    public final PendingResult<MediaChannelResult> zza(String str, List<zzbl> list) {
        zzbq.zzgn("Must be called from the main thread.");
        if (!zzafm()) {
            return zzafn();
        }
        return zza((zzb) new zzae(this, this.zzffo, true, str, (List) null));
    }

    public final void zzafl() throws IOException {
        GoogleApiClient googleApiClient = this.zzffo;
        if (googleApiClient != null) {
            this.zzfam.setMessageReceivedCallbacks(googleApiClient, getNamespace(), this);
        }
    }

    public final void zzc(GoogleApiClient googleApiClient) {
        GoogleApiClient googleApiClient2 = this.zzffo;
        if (googleApiClient2 != googleApiClient) {
            if (googleApiClient2 != null) {
                this.zzext.zzagm();
                try {
                    this.zzfam.removeMessageReceivedCallbacks(this.zzffo, getNamespace());
                } catch (IOException unused) {
                }
                this.zzffn.zzb((GoogleApiClient) null);
                this.mHandler.removeCallbacksAndMessages((Object) null);
            }
            this.zzffo = googleApiClient;
            if (googleApiClient != null) {
                this.zzffn.zzb(googleApiClient);
            }
        }
    }
}
