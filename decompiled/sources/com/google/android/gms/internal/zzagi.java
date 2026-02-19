package com.google.android.gms.internal;

import android.graphics.Bitmap;
import java.io.ByteArrayOutputStream;

final class zzagi implements Runnable {
    private /* synthetic */ Bitmap val$bitmap;
    private /* synthetic */ zzagf zzdbk;

    zzagi(zzagf zzagf, Bitmap bitmap) {
        this.zzdbk = zzagf;
        this.val$bitmap = bitmap;
    }

    public final void run() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        this.val$bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
        synchronized (this.zzdbk.mLock) {
            this.zzdbk.zzdba.zzpwl = new zzfmd();
            this.zzdbk.zzdba.zzpwl.zzpxg = byteArrayOutputStream.toByteArray();
            this.zzdbk.zzdba.zzpwl.mimeType = "image/png";
            this.zzdbk.zzdba.zzpwl.zzbdh = 1;
        }
    }
}
