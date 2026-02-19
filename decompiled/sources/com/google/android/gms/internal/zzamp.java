package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.view.Surface;
import android.view.TextureView;
import androidx.media2.MediaPlayer2;
import com.google.android.gms.ads.internal.zzbt;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@zzabh
public final class zzamp extends zzamz implements MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnInfoListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnVideoSizeChangedListener, TextureView.SurfaceTextureListener {
    private static final Map<Integer, String> zzdkj;
    private final zzano zzdkk;
    private final boolean zzdkl;
    private int zzdkm = 0;
    private int zzdkn = 0;
    private MediaPlayer zzdko;
    private Uri zzdkp;
    private int zzdkq;
    private int zzdkr;
    private int zzdks;
    private int zzdkt;
    private int zzdku;
    private zzanl zzdkv;
    private boolean zzdkw;
    private int zzdkx;
    /* access modifiers changed from: private */
    public zzamy zzdky;

    static {
        HashMap hashMap = new HashMap();
        zzdkj = hashMap;
        if (Build.VERSION.SDK_INT >= 17) {
            hashMap.put(Integer.valueOf(MediaPlayer2.MEDIA_ERROR_IO), "MEDIA_ERROR_IO");
            hashMap.put(Integer.valueOf(MediaPlayer2.MEDIA_ERROR_MALFORMED), "MEDIA_ERROR_MALFORMED");
            hashMap.put(Integer.valueOf(MediaPlayer2.MEDIA_ERROR_UNSUPPORTED), "MEDIA_ERROR_UNSUPPORTED");
            hashMap.put(Integer.valueOf(MediaPlayer2.MEDIA_ERROR_TIMED_OUT), "MEDIA_ERROR_TIMED_OUT");
            hashMap.put(3, "MEDIA_INFO_VIDEO_RENDERING_START");
        }
        hashMap.put(100, "MEDIA_ERROR_SERVER_DIED");
        hashMap.put(1, "MEDIA_ERROR_UNKNOWN");
        hashMap.put(1, "MEDIA_INFO_UNKNOWN");
        hashMap.put(Integer.valueOf(MediaPlayer2.MEDIA_INFO_VIDEO_TRACK_LAGGING), "MEDIA_INFO_VIDEO_TRACK_LAGGING");
        hashMap.put(Integer.valueOf(MediaPlayer2.MEDIA_INFO_BUFFERING_START), "MEDIA_INFO_BUFFERING_START");
        hashMap.put(Integer.valueOf(MediaPlayer2.MEDIA_INFO_BUFFERING_END), "MEDIA_INFO_BUFFERING_END");
        hashMap.put(Integer.valueOf(MediaPlayer2.MEDIA_INFO_BAD_INTERLEAVING), "MEDIA_INFO_BAD_INTERLEAVING");
        hashMap.put(Integer.valueOf(MediaPlayer2.MEDIA_INFO_NOT_SEEKABLE), "MEDIA_INFO_NOT_SEEKABLE");
        hashMap.put(Integer.valueOf(MediaPlayer2.MEDIA_INFO_METADATA_UPDATE), "MEDIA_INFO_METADATA_UPDATE");
        if (Build.VERSION.SDK_INT >= 19) {
            hashMap.put(Integer.valueOf(MediaPlayer2.MEDIA_INFO_UNSUPPORTED_SUBTITLE), "MEDIA_INFO_UNSUPPORTED_SUBTITLE");
            hashMap.put(Integer.valueOf(MediaPlayer2.MEDIA_INFO_SUBTITLE_TIMED_OUT), "MEDIA_INFO_SUBTITLE_TIMED_OUT");
        }
    }

    public zzamp(Context context, boolean z, boolean z2, zzanm zzanm, zzano zzano) {
        super(context);
        setSurfaceTextureListener(this);
        this.zzdkk = zzano;
        this.zzdkw = z;
        this.zzdkl = z2;
        zzano.zza(this);
    }

    private final void zza(float f) {
        MediaPlayer mediaPlayer = this.zzdko;
        if (mediaPlayer != null) {
            try {
                mediaPlayer.setVolume(f, f);
            } catch (IllegalStateException unused) {
            }
        } else {
            zzahw.zzcz("AdMediaPlayerView setMediaPlayerVolume() called before onPrepared().");
        }
    }

    private final void zzaf(int i) {
        if (i == 3) {
            this.zzdkk.zzto();
            this.zzdlf.zzto();
        } else if (this.zzdkm == 3) {
            this.zzdkk.zztp();
            this.zzdlf.zztp();
        }
        this.zzdkm = i;
    }

    private final void zzaf(boolean z) {
        zzahw.v("AdMediaPlayerView release");
        zzanl zzanl = this.zzdkv;
        if (zzanl != null) {
            zzanl.zztc();
            this.zzdkv = null;
        }
        MediaPlayer mediaPlayer = this.zzdko;
        if (mediaPlayer != null) {
            mediaPlayer.reset();
            this.zzdko.release();
            this.zzdko = null;
            zzaf(0);
            if (z) {
                this.zzdkn = 0;
                this.zzdkn = 0;
            }
        }
    }

    private final void zzsk() {
        zzahw.v("AdMediaPlayerView init MediaPlayer");
        SurfaceTexture surfaceTexture = getSurfaceTexture();
        if (this.zzdkp != null && surfaceTexture != null) {
            zzaf(false);
            try {
                zzbt.zzfb();
                MediaPlayer mediaPlayer = new MediaPlayer();
                this.zzdko = mediaPlayer;
                mediaPlayer.setOnBufferingUpdateListener(this);
                this.zzdko.setOnCompletionListener(this);
                this.zzdko.setOnErrorListener(this);
                this.zzdko.setOnInfoListener(this);
                this.zzdko.setOnPreparedListener(this);
                this.zzdko.setOnVideoSizeChangedListener(this);
                this.zzdks = 0;
                if (this.zzdkw) {
                    zzanl zzanl = new zzanl(getContext());
                    this.zzdkv = zzanl;
                    zzanl.zza(surfaceTexture, getWidth(), getHeight());
                    this.zzdkv.start();
                    SurfaceTexture zztd = this.zzdkv.zztd();
                    if (zztd != null) {
                        surfaceTexture = zztd;
                    } else {
                        this.zzdkv.zztc();
                        this.zzdkv = null;
                    }
                }
                this.zzdko.setDataSource(getContext(), this.zzdkp);
                zzbt.zzfc();
                this.zzdko.setSurface(new Surface(surfaceTexture));
                this.zzdko.setAudioStreamType(3);
                this.zzdko.setScreenOnWhilePlaying(true);
                this.zzdko.prepareAsync();
                zzaf(1);
            } catch (IOException | IllegalArgumentException | IllegalStateException e) {
                String valueOf = String.valueOf(this.zzdkp);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 36);
                sb.append("Failed to initialize MediaPlayer at ");
                sb.append(valueOf);
                zzahw.zzc(sb.toString(), e);
                onError(this.zzdko, 1, 0);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0034 A[LOOP:0: B:10:0x0034->B:15:0x004f, LOOP_START] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzsl() {
        /*
            r8 = this;
            boolean r0 = r8.zzdkl
            if (r0 != 0) goto L_0x0005
            return
        L_0x0005:
            boolean r0 = r8.zzsm()
            if (r0 == 0) goto L_0x0059
            android.media.MediaPlayer r0 = r8.zzdko
            int r0 = r0.getCurrentPosition()
            if (r0 <= 0) goto L_0x0059
            int r0 = r8.zzdkn
            r1 = 3
            if (r0 == r1) goto L_0x0059
            java.lang.String r0 = "AdMediaPlayerView nudging MediaPlayer"
            com.google.android.gms.internal.zzahw.v(r0)
            r0 = 0
            r8.zza((float) r0)
            android.media.MediaPlayer r0 = r8.zzdko
            r0.start()
            android.media.MediaPlayer r0 = r8.zzdko
            int r0 = r0.getCurrentPosition()
            com.google.android.gms.common.util.zze r1 = com.google.android.gms.ads.internal.zzbt.zzes()
            long r1 = r1.currentTimeMillis()
        L_0x0034:
            boolean r3 = r8.zzsm()
            if (r3 == 0) goto L_0x0051
            android.media.MediaPlayer r3 = r8.zzdko
            int r3 = r3.getCurrentPosition()
            if (r3 != r0) goto L_0x0051
            com.google.android.gms.common.util.zze r3 = com.google.android.gms.ads.internal.zzbt.zzes()
            long r3 = r3.currentTimeMillis()
            long r3 = r3 - r1
            r5 = 250(0xfa, double:1.235E-321)
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 <= 0) goto L_0x0034
        L_0x0051:
            android.media.MediaPlayer r0 = r8.zzdko
            r0.pause()
            r8.zzsn()
        L_0x0059:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzamp.zzsl():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r0 = r2.zzdkm;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean zzsm() {
        /*
            r2 = this;
            android.media.MediaPlayer r0 = r2.zzdko
            if (r0 == 0) goto L_0x000f
            int r0 = r2.zzdkm
            r1 = -1
            if (r0 == r1) goto L_0x000f
            if (r0 == 0) goto L_0x000f
            r1 = 1
            if (r0 == r1) goto L_0x000f
            return r1
        L_0x000f:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzamp.zzsm():boolean");
    }

    public final int getCurrentPosition() {
        if (zzsm()) {
            return this.zzdko.getCurrentPosition();
        }
        return 0;
    }

    public final int getDuration() {
        if (zzsm()) {
            return this.zzdko.getDuration();
        }
        return -1;
    }

    public final int getVideoHeight() {
        MediaPlayer mediaPlayer = this.zzdko;
        if (mediaPlayer != null) {
            return mediaPlayer.getVideoHeight();
        }
        return 0;
    }

    public final int getVideoWidth() {
        MediaPlayer mediaPlayer = this.zzdko;
        if (mediaPlayer != null) {
            return mediaPlayer.getVideoWidth();
        }
        return 0;
    }

    public final void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
        this.zzdks = i;
    }

    public final void onCompletion(MediaPlayer mediaPlayer) {
        zzahw.v("AdMediaPlayerView completion");
        zzaf(5);
        this.zzdkn = 5;
        zzaij.zzdfn.post(new zzamr(this));
    }

    public final boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        Map<Integer, String> map = zzdkj;
        String str = map.get(Integer.valueOf(i));
        String str2 = map.get(Integer.valueOf(i2));
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 38 + String.valueOf(str2).length());
        sb.append("AdMediaPlayerView MediaPlayer error: ");
        sb.append(str);
        sb.append(":");
        sb.append(str2);
        zzahw.zzcz(sb.toString());
        zzaf(-1);
        this.zzdkn = -1;
        zzaij.zzdfn.post(new zzams(this, str, str2));
        return true;
    }

    public final boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
        Map<Integer, String> map = zzdkj;
        String str = map.get(Integer.valueOf(i));
        String str2 = map.get(Integer.valueOf(i2));
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 37 + String.valueOf(str2).length());
        sb.append("AdMediaPlayerView MediaPlayer info: ");
        sb.append(str);
        sb.append(":");
        sb.append(str2);
        zzahw.v(sb.toString());
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0065, code lost:
        if (r1 > r6) goto L_0x0067;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onMeasure(int r6, int r7) {
        /*
            r5 = this;
            int r0 = r5.zzdkq
            int r0 = getDefaultSize(r0, r6)
            int r1 = r5.zzdkr
            int r1 = getDefaultSize(r1, r7)
            int r2 = r5.zzdkq
            if (r2 <= 0) goto L_0x0083
            int r2 = r5.zzdkr
            if (r2 <= 0) goto L_0x0083
            com.google.android.gms.internal.zzanl r2 = r5.zzdkv
            if (r2 != 0) goto L_0x0083
            int r0 = android.view.View.MeasureSpec.getMode(r6)
            int r6 = android.view.View.MeasureSpec.getSize(r6)
            int r1 = android.view.View.MeasureSpec.getMode(r7)
            int r7 = android.view.View.MeasureSpec.getSize(r7)
            r2 = 1073741824(0x40000000, float:2.0)
            if (r0 != r2) goto L_0x0047
            if (r1 != r2) goto L_0x0047
            int r0 = r5.zzdkq
            int r1 = r0 * r7
            int r2 = r5.zzdkr
            int r3 = r6 * r2
            if (r1 >= r3) goto L_0x003c
            int r0 = r0 * r7
            int r0 = r0 / r2
            goto L_0x006a
        L_0x003c:
            int r1 = r0 * r7
            int r3 = r6 * r2
            if (r1 <= r3) goto L_0x0067
            int r2 = r2 * r6
            int r1 = r2 / r0
            goto L_0x0058
        L_0x0047:
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r0 != r2) goto L_0x005a
            int r0 = r5.zzdkr
            int r0 = r0 * r6
            int r2 = r5.zzdkq
            int r0 = r0 / r2
            if (r1 != r3) goto L_0x0057
            if (r0 <= r7) goto L_0x0057
            goto L_0x0067
        L_0x0057:
            r1 = r0
        L_0x0058:
            r0 = r6
            goto L_0x0083
        L_0x005a:
            if (r1 != r2) goto L_0x006c
            int r1 = r5.zzdkq
            int r1 = r1 * r7
            int r2 = r5.zzdkr
            int r1 = r1 / r2
            if (r0 != r3) goto L_0x0069
            if (r1 <= r6) goto L_0x0069
        L_0x0067:
            r0 = r6
            goto L_0x006a
        L_0x0069:
            r0 = r1
        L_0x006a:
            r1 = r7
            goto L_0x0083
        L_0x006c:
            int r2 = r5.zzdkq
            int r4 = r5.zzdkr
            if (r1 != r3) goto L_0x0078
            if (r4 <= r7) goto L_0x0078
            int r1 = r7 * r2
            int r1 = r1 / r4
            goto L_0x007a
        L_0x0078:
            r1 = r2
            r7 = r4
        L_0x007a:
            if (r0 != r3) goto L_0x0069
            if (r1 <= r6) goto L_0x0069
            int r4 = r4 * r6
            int r1 = r4 / r2
            goto L_0x0058
        L_0x0083:
            r5.setMeasuredDimension(r0, r1)
            com.google.android.gms.internal.zzanl r6 = r5.zzdkv
            if (r6 == 0) goto L_0x008d
            r6.zzh(r0, r1)
        L_0x008d:
            int r6 = android.os.Build.VERSION.SDK_INT
            r7 = 16
            if (r6 != r7) goto L_0x00a6
            int r6 = r5.zzdkt
            if (r6 <= 0) goto L_0x0099
            if (r6 != r0) goto L_0x009f
        L_0x0099:
            int r6 = r5.zzdku
            if (r6 <= 0) goto L_0x00a2
            if (r6 == r1) goto L_0x00a2
        L_0x009f:
            r5.zzsl()
        L_0x00a2:
            r5.zzdkt = r0
            r5.zzdku = r1
        L_0x00a6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzamp.onMeasure(int, int):void");
    }

    public final void onPrepared(MediaPlayer mediaPlayer) {
        zzahw.v("AdMediaPlayerView prepared");
        zzaf(2);
        this.zzdkk.zzsp();
        zzaij.zzdfn.post(new zzamq(this));
        this.zzdkq = mediaPlayer.getVideoWidth();
        this.zzdkr = mediaPlayer.getVideoHeight();
        int i = this.zzdkx;
        if (i != 0) {
            seekTo(i);
        }
        zzsl();
        int i2 = this.zzdkq;
        int i3 = this.zzdkr;
        StringBuilder sb = new StringBuilder(62);
        sb.append("AdMediaPlayerView stream dimensions: ");
        sb.append(i2);
        sb.append(" x ");
        sb.append(i3);
        zzahw.zzcy(sb.toString());
        if (this.zzdkn == 3) {
            play();
        }
        zzsn();
    }

    public final void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        zzahw.v("AdMediaPlayerView surface created");
        zzsk();
        zzaij.zzdfn.post(new zzamt(this));
    }

    public final boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        zzahw.v("AdMediaPlayerView surface destroyed");
        MediaPlayer mediaPlayer = this.zzdko;
        if (mediaPlayer != null && this.zzdkx == 0) {
            this.zzdkx = mediaPlayer.getCurrentPosition();
        }
        zzanl zzanl = this.zzdkv;
        if (zzanl != null) {
            zzanl.zztc();
        }
        zzaij.zzdfn.post(new zzamv(this));
        zzaf(true);
        return true;
    }

    public final void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        zzahw.v("AdMediaPlayerView surface changed");
        boolean z = true;
        boolean z2 = this.zzdkn == 3;
        if (!(this.zzdkq == i && this.zzdkr == i2)) {
            z = false;
        }
        if (this.zzdko != null && z2 && z) {
            int i3 = this.zzdkx;
            if (i3 != 0) {
                seekTo(i3);
            }
            play();
        }
        zzanl zzanl = this.zzdkv;
        if (zzanl != null) {
            zzanl.zzh(i, i2);
        }
        zzaij.zzdfn.post(new zzamu(this, i, i2));
    }

    public final void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        this.zzdkk.zzb(this);
        this.zzdle.zza(surfaceTexture, this.zzdky);
    }

    public final void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
        StringBuilder sb = new StringBuilder(57);
        sb.append("AdMediaPlayerView size changed: ");
        sb.append(i);
        sb.append(" x ");
        sb.append(i2);
        zzahw.v(sb.toString());
        this.zzdkq = mediaPlayer.getVideoWidth();
        int videoHeight = mediaPlayer.getVideoHeight();
        this.zzdkr = videoHeight;
        if (this.zzdkq != 0 && videoHeight != 0) {
            requestLayout();
        }
    }

    public final void pause() {
        zzahw.v("AdMediaPlayerView pause");
        if (zzsm() && this.zzdko.isPlaying()) {
            this.zzdko.pause();
            zzaf(4);
            zzaij.zzdfn.post(new zzamx(this));
        }
        this.zzdkn = 4;
    }

    public final void play() {
        zzahw.v("AdMediaPlayerView play");
        if (zzsm()) {
            this.zzdko.start();
            zzaf(3);
            this.zzdle.zzsq();
            zzaij.zzdfn.post(new zzamw(this));
        }
        this.zzdkn = 3;
    }

    public final void seekTo(int i) {
        StringBuilder sb = new StringBuilder(34);
        sb.append("AdMediaPlayerView seek ");
        sb.append(i);
        zzahw.v(sb.toString());
        if (zzsm()) {
            this.zzdko.seekTo(i);
            this.zzdkx = 0;
            return;
        }
        this.zzdkx = i;
    }

    public final void setVideoPath(String str) {
        Uri parse = Uri.parse(str);
        zzin zzd = zzin.zzd(parse);
        if (zzd != null) {
            parse = Uri.parse(zzd.url);
        }
        this.zzdkp = parse;
        this.zzdkx = 0;
        zzsk();
        requestLayout();
        invalidate();
    }

    public final void stop() {
        zzahw.v("AdMediaPlayerView stop");
        MediaPlayer mediaPlayer = this.zzdko;
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            this.zzdko.release();
            this.zzdko = null;
            zzaf(0);
            this.zzdkn = 0;
        }
        this.zzdkk.onStop();
    }

    public final String toString() {
        String name = getClass().getName();
        String hexString = Integer.toHexString(hashCode());
        StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 1 + String.valueOf(hexString).length());
        sb.append(name);
        sb.append("@");
        sb.append(hexString);
        return sb.toString();
    }

    public final void zza(float f, float f2) {
        zzanl zzanl = this.zzdkv;
        if (zzanl != null) {
            zzanl.zzb(f, f2);
        }
    }

    public final void zza(zzamy zzamy) {
        this.zzdky = zzamy;
    }

    public final String zzsj() {
        String str = this.zzdkw ? " spherical" : "";
        return str.length() != 0 ? "MediaPlayer".concat(str) : new String("MediaPlayer");
    }

    public final void zzsn() {
        zza(this.zzdlf.getVolume());
    }
}
