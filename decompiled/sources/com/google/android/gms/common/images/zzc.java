package com.google.android.gms.common.images;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.internal.zzbge;
import com.google.android.gms.internal.zzbgj;
import java.lang.ref.WeakReference;

public final class zzc extends zza {
    private WeakReference<ImageView> zzgdw;

    public zzc(ImageView imageView, int i) {
        super((Uri) null, i);
        com.google.android.gms.common.internal.zzc.zzv(imageView);
        this.zzgdw = new WeakReference<>(imageView);
    }

    public zzc(ImageView imageView, Uri uri) {
        super(uri, 0);
        com.google.android.gms.common.internal.zzc.zzv(imageView);
        this.zzgdw = new WeakReference<>(imageView);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzc)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        ImageView imageView = (ImageView) this.zzgdw.get();
        ImageView imageView2 = (ImageView) ((zzc) obj).zzgdw.get();
        return (imageView2 == null || imageView == null || !zzbg.equal(imageView2, imageView)) ? false : true;
    }

    public final int hashCode() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public final void zza(Drawable drawable, boolean z, boolean z2, boolean z3) {
        ImageView imageView = (ImageView) this.zzgdw.get();
        if (imageView != null) {
            int i = 0;
            boolean z4 = !z2 && !z3;
            if (z4 && (imageView instanceof zzbgj)) {
                int zzalp = zzbgj.zzalp();
                if (this.zzgdr != 0 && zzalp == this.zzgdr) {
                    return;
                }
            }
            boolean zzc = zzc(z, z2);
            Uri uri = null;
            if (zzc) {
                Drawable drawable2 = imageView.getDrawable();
                if (drawable2 == null) {
                    drawable2 = null;
                } else if (drawable2 instanceof zzbge) {
                    drawable2 = ((zzbge) drawable2).zzaln();
                }
                drawable = new zzbge(drawable2, drawable);
            }
            imageView.setImageDrawable(drawable);
            if (imageView instanceof zzbgj) {
                if (z3) {
                    uri = this.zzgdp.uri;
                }
                zzbgj.zzn(uri);
                if (z4) {
                    i = this.zzgdr;
                }
                zzbgj.zzcc(i);
            }
            if (zzc) {
                ((zzbge) drawable).startTransition(250);
            }
        }
    }
}
