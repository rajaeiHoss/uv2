package com.google.android.gms.internal;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Looper;
import android.os.SystemClock;
import com.google.android.gms.common.util.zzs;
import java.io.InputStream;

final class zzaac implements zzajx<zzpj> {
    private /* synthetic */ String zzclo;
    private /* synthetic */ zzzy zzcpf;
    private /* synthetic */ boolean zzcpp;
    private /* synthetic */ double zzcpq;
    private /* synthetic */ boolean zzcpr;

    zzaac(zzzy zzzy, boolean z, double d, boolean z2, String str) {
        this.zzcpf = zzzy;
        this.zzcpp = z;
        this.zzcpq = d;
        this.zzcpr = z2;
        this.zzclo = str;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzd */
    public final zzpj zze(InputStream inputStream) {
        Bitmap bitmap;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inDensity = (int) (this.zzcpq * 160.0d);
        if (!this.zzcpr) {
            options.inPreferredConfig = Bitmap.Config.RGB_565;
        }
        long uptimeMillis = SystemClock.uptimeMillis();
        try {
            bitmap = BitmapFactory.decodeStream(inputStream, (Rect) null, options);
        } catch (Exception e) {
            zzahw.zzb("Error grabbing image.", e);
            bitmap = null;
        }
        if (bitmap == null) {
            this.zzcpf.zzd(2, this.zzcpp);
            return null;
        }
        long uptimeMillis2 = SystemClock.uptimeMillis();
        if (zzs.zzanv() && zzahw.zzqk()) {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            int allocationByteCount = bitmap.getAllocationByteCount();
            long j = uptimeMillis2 - uptimeMillis;
            boolean z = Looper.getMainLooper().getThread() == Thread.currentThread();
            StringBuilder sb = new StringBuilder(108);
            sb.append("Decoded image w: ");
            sb.append(width);
            sb.append(" h:");
            sb.append(height);
            sb.append(" bytes: ");
            sb.append(allocationByteCount);
            sb.append(" time: ");
            sb.append(j);
            sb.append(" on ui thread: ");
            sb.append(z);
            zzahw.v(sb.toString());
        }
        return new zzpj(new BitmapDrawable(Resources.getSystem(), bitmap), Uri.parse(this.zzclo), this.zzcpq);
    }

    public final zzpj zznx() {
        this.zzcpf.zzd(2, this.zzcpp);
        return null;
    }
}
