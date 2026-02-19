package com.google.android.gms.cast.framework.media.widget;

import android.graphics.Bitmap;
import com.google.android.gms.internal.zzbay;

final class zza implements zzbay {
    private /* synthetic */ ExpandedControllerActivity zzfjv;

    zza(ExpandedControllerActivity expandedControllerActivity) {
        this.zzfjv = expandedControllerActivity;
    }

    public final void zzc(Bitmap bitmap) {
        if (bitmap != null) {
            if (this.zzfjv.zzfjq != null) {
                this.zzfjv.zzfjq.setVisibility(8);
            }
            if (this.zzfjv.zzfjp != null) {
                this.zzfjv.zzfjp.setVisibility(0);
                this.zzfjv.zzfjp.setImageBitmap(bitmap);
            }
        }
    }
}
