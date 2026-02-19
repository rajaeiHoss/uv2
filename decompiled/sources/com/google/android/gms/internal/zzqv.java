package com.google.android.gms.internal;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.RemoteException;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;

@zzabh
public final class zzqv extends NativeAd.Image {
    private final Drawable mDrawable;
    private final Uri mUri;
    private final double zzbxu;
    private final zzqs zzcai;

    public zzqv(zzqs zzqs) {
        this.zzcai = zzqs;
        Drawable drawable = null;
        Uri uri = null;
        double d = 1.0d;
        try {
            IObjectWrapper zzkb = zzqs.zzkb();
            if (zzkb != null) {
                drawable = (Drawable) zzn.zzy(zzkb);
                uri = this.zzcai.getUri();
                d = this.zzcai.getScale();
            }
        } catch (RemoteException e) {
            zzaky.zzb("Failed to get drawable.", e);
        }
        this.mDrawable = drawable;
        this.mUri = uri;
        this.zzbxu = d;
    }

    public final Drawable getDrawable() {
        return this.mDrawable;
    }

    public final double getScale() {
        return this.zzbxu;
    }

    public final Uri getUri() {
        return this.mUri;
    }
}
