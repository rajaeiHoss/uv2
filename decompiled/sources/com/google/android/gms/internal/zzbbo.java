package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.CastMediaOptions;
import com.google.android.gms.cast.framework.media.ImageHints;
import com.google.android.gms.cast.framework.media.ImagePicker;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;

public final class zzbbo extends UIController {
    private final ImagePicker zzfdu;
    private final ImageHints zzfgq;
    /* access modifiers changed from: private */
    public final ImageView zzfhq;
    private final Bitmap zzfhr;
    private final zzbax zzfhs;

    public zzbbo(ImageView imageView, Context context, ImageHints imageHints, int i) {
        CastMediaOptions castMediaOptions;
        this.zzfhq = imageView;
        this.zzfgq = imageHints;
        this.zzfhr = BitmapFactory.decodeResource(context.getResources(), i);
        CastContext zzbu = CastContext.zzbu(context);
        ImagePicker imagePicker = null;
        if (!(zzbu == null || (castMediaOptions = zzbu.getCastOptions().getCastMediaOptions()) == null)) {
            imagePicker = castMediaOptions.getImagePicker();
        }
        this.zzfdu = imagePicker;
        this.zzfhs = new zzbax(context.getApplicationContext());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0020, code lost:
        r1 = r1.onPickImage(r0.getMetadata(), r4.zzfgq);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzafy() {
        /*
            r4 = this;
            com.google.android.gms.cast.framework.media.RemoteMediaClient r0 = r4.getRemoteMediaClient()
            if (r0 == 0) goto L_0x004c
            boolean r1 = r0.hasMediaSession()
            if (r1 != 0) goto L_0x000d
            goto L_0x004c
        L_0x000d:
            com.google.android.gms.cast.MediaQueueItem r0 = r0.getPreloadedItem()
            r1 = 0
            if (r0 != 0) goto L_0x0015
            goto L_0x003c
        L_0x0015:
            com.google.android.gms.cast.MediaInfo r0 = r0.getMedia()
            if (r0 != 0) goto L_0x001c
            goto L_0x003c
        L_0x001c:
            com.google.android.gms.cast.framework.media.ImagePicker r1 = r4.zzfdu
            if (r1 == 0) goto L_0x0037
            com.google.android.gms.cast.MediaMetadata r2 = r0.getMetadata()
            com.google.android.gms.cast.framework.media.ImageHints r3 = r4.zzfgq
            com.google.android.gms.common.images.WebImage r1 = r1.onPickImage((com.google.android.gms.cast.MediaMetadata) r2, (com.google.android.gms.cast.framework.media.ImageHints) r3)
            if (r1 == 0) goto L_0x0037
            android.net.Uri r2 = r1.getUrl()
            if (r2 == 0) goto L_0x0037
            android.net.Uri r1 = r1.getUrl()
            goto L_0x003c
        L_0x0037:
            r1 = 0
            android.net.Uri r1 = com.google.android.gms.cast.framework.media.MediaUtils.getImageUri(r0, r1)
        L_0x003c:
            if (r1 != 0) goto L_0x0046
            android.widget.ImageView r0 = r4.zzfhq
            android.graphics.Bitmap r1 = r4.zzfhr
            r0.setImageBitmap(r1)
            return
        L_0x0046:
            com.google.android.gms.internal.zzbax r0 = r4.zzfhs
            r0.zzl(r1)
            return
        L_0x004c:
            android.widget.ImageView r0 = r4.zzfhq
            android.graphics.Bitmap r1 = r4.zzfhr
            r0.setImageBitmap(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzbbo.zzafy():void");
    }

    public final void onMediaStatusUpdated() {
        zzafy();
    }

    public final void onSessionConnected(CastSession castSession) {
        super.onSessionConnected(castSession);
        this.zzfhs.zza(new zzbbp(this));
        this.zzfhq.setImageBitmap(this.zzfhr);
        zzafy();
    }

    public final void onSessionEnded() {
        this.zzfhs.clear();
        this.zzfhq.setImageBitmap(this.zzfhr);
        super.onSessionEnded();
    }
}
