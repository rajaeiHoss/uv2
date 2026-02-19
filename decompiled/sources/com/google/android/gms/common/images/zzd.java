package com.google.android.gms.common.images;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.google.android.gms.common.images.ImageManager;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzc;
import java.lang.ref.WeakReference;
import java.util.Arrays;

public final class zzd extends zza {
    private WeakReference<ImageManager.OnImageLoadedListener> zzgdx;

    public zzd(ImageManager.OnImageLoadedListener onImageLoadedListener, Uri uri) {
        super(uri, 0);
        zzc.zzv(onImageLoadedListener);
        this.zzgdx = new WeakReference<>(onImageLoadedListener);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzd)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        zzd zzd = (zzd) obj;
        ImageManager.OnImageLoadedListener onImageLoadedListener = (ImageManager.OnImageLoadedListener) this.zzgdx.get();
        ImageManager.OnImageLoadedListener onImageLoadedListener2 = (ImageManager.OnImageLoadedListener) zzd.zzgdx.get();
        return onImageLoadedListener2 != null && onImageLoadedListener != null && zzbg.equal(onImageLoadedListener2, onImageLoadedListener) && zzbg.equal(zzd.zzgdp, this.zzgdp);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzgdp});
    }

    /* access modifiers changed from: protected */
    public final void zza(Drawable drawable, boolean z, boolean z2, boolean z3) {
        ImageManager.OnImageLoadedListener onImageLoadedListener;
        if (!z2 && (onImageLoadedListener = (ImageManager.OnImageLoadedListener) this.zzgdx.get()) != null) {
            onImageLoadedListener.onImageLoaded(this.zzgdp.uri, drawable, z3);
        }
    }
}
