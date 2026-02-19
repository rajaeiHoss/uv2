package com.google.android.gms.internal;

import android.graphics.Bitmap;

final class zzbbr implements zzbay {
    private /* synthetic */ zzbbq zzfhw;

    zzbbr(zzbbq zzbbq) {
        this.zzfhw = zzbbq;
    }

    public final void zzc(Bitmap bitmap) {
        if (bitmap != null) {
            if (this.zzfhw.zzfhv != null) {
                this.zzfhw.zzfhv.setVisibility(4);
            }
            this.zzfhw.zzfhq.setVisibility(0);
            this.zzfhw.zzfhq.setImageBitmap(bitmap);
        }
    }
}
