package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.CastMediaOptions;
import com.google.android.gms.cast.framework.media.ImageHints;
import com.google.android.gms.cast.framework.media.ImagePicker;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;

public final class zzbbq extends UIController {
    private final ImagePicker zzfdu;
    private final ImageHints zzfgq;
    /* access modifiers changed from: private */
    public final ImageView zzfhq;
    private final zzbax zzfhs;
    private final Bitmap zzfhu;
    /* access modifiers changed from: private */
    public final View zzfhv;

    public zzbbq(ImageView imageView, Context context, ImageHints imageHints, int i, View view) {
        CastMediaOptions castMediaOptions;
        this.zzfhq = imageView;
        this.zzfgq = imageHints;
        ImagePicker imagePicker = null;
        this.zzfhu = i != 0 ? BitmapFactory.decodeResource(context.getResources(), i) : null;
        this.zzfhv = view;
        CastContext zzbu = CastContext.zzbu(context);
        if (!(zzbu == null || (castMediaOptions = zzbu.getCastOptions().getCastMediaOptions()) == null)) {
            imagePicker = castMediaOptions.getImagePicker();
        }
        this.zzfdu = imagePicker;
        this.zzfhs = new zzbax(context.getApplicationContext());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0019, code lost:
        r1 = r1.onPickImage(r0.getMetadata(), r4.zzfgq);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzafy() {
        /*
            r4 = this;
            com.google.android.gms.cast.framework.media.RemoteMediaClient r0 = r4.getRemoteMediaClient()
            if (r0 == 0) goto L_0x0041
            boolean r1 = r0.hasMediaSession()
            if (r1 != 0) goto L_0x000d
            goto L_0x0041
        L_0x000d:
            com.google.android.gms.cast.MediaInfo r0 = r0.getMediaInfo()
            if (r0 != 0) goto L_0x0015
            r0 = 0
            goto L_0x0035
        L_0x0015:
            com.google.android.gms.cast.framework.media.ImagePicker r1 = r4.zzfdu
            if (r1 == 0) goto L_0x0030
            com.google.android.gms.cast.MediaMetadata r2 = r0.getMetadata()
            com.google.android.gms.cast.framework.media.ImageHints r3 = r4.zzfgq
            com.google.android.gms.common.images.WebImage r1 = r1.onPickImage((com.google.android.gms.cast.MediaMetadata) r2, (com.google.android.gms.cast.framework.media.ImageHints) r3)
            if (r1 == 0) goto L_0x0030
            android.net.Uri r2 = r1.getUrl()
            if (r2 == 0) goto L_0x0030
            android.net.Uri r0 = r1.getUrl()
            goto L_0x0035
        L_0x0030:
            r1 = 0
            android.net.Uri r0 = com.google.android.gms.cast.framework.media.MediaUtils.getImageUri(r0, r1)
        L_0x0035:
            if (r0 != 0) goto L_0x003b
            r4.zzafz()
            return
        L_0x003b:
            com.google.android.gms.internal.zzbax r1 = r4.zzfhs
            r1.zzl(r0)
            return
        L_0x0041:
            r4.zzafz()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzbbq.zzafy():void");
    }

    private final void zzafz() {
        View view = this.zzfhv;
        if (view != null) {
            view.setVisibility(0);
            this.zzfhq.setVisibility(4);
        }
        Bitmap bitmap = this.zzfhu;
        if (bitmap != null) {
            this.zzfhq.setImageBitmap(bitmap);
        }
    }

    public final void onMediaStatusUpdated() {
        zzafy();
    }

    public final void onSessionConnected(CastSession castSession) {
        super.onSessionConnected(castSession);
        this.zzfhs.zza(new zzbbr(this));
        zzafz();
        zzafy();
    }

    public final void onSessionEnded() {
        this.zzfhs.clear();
        zzafz();
        super.onSessionEnded();
    }
}
