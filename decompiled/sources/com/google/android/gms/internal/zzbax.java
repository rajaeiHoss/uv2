package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import com.google.android.gms.cast.framework.media.ImageHints;

public final class zzbax implements zzbbb {
    private Bitmap mBitmap;
    private final Context zzbky;
    private final ImageHints zzfgq;
    private Uri zzfgr;
    private zzbaz zzfgs;
    private zzbbc zzfgt;
    private boolean zzfgu;
    private zzbay zzfgv;

    public zzbax(Context context) {
        this(context, new ImageHints(-1, 0, 0));
    }

    public zzbax(Context context, ImageHints imageHints) {
        this.zzbky = context;
        this.zzfgq = imageHints;
        this.zzfgt = new zzbbc();
        reset();
    }

    private final void reset() {
        zzbaz zzbaz = this.zzfgs;
        if (zzbaz != null) {
            zzbaz.cancel(true);
            this.zzfgs = null;
        }
        this.zzfgr = null;
        this.mBitmap = null;
        this.zzfgu = false;
    }

    public final void clear() {
        reset();
        this.zzfgv = null;
    }

    public final void onPostExecute(Bitmap bitmap) {
        this.mBitmap = bitmap;
        this.zzfgu = true;
        zzbay zzbay = this.zzfgv;
        if (zzbay != null) {
            zzbay.zzc(bitmap);
        }
        this.zzfgs = null;
    }

    public final void zza(zzbay zzbay) {
        this.zzfgv = zzbay;
    }

    public final boolean zzl(Uri uri) {
        if (uri == null) {
            reset();
            return true;
        } else if (uri.equals(this.zzfgr)) {
            return this.zzfgu;
        } else {
            reset();
            this.zzfgr = uri;
            if (this.zzfgq.getWidthInPixels() == 0 || this.zzfgq.getHeightInPixels() == 0) {
                this.zzfgs = new zzbaz(this.zzbky, this);
            } else {
                this.zzfgs = new zzbaz(this.zzbky, this.zzfgq.getWidthInPixels(), this.zzfgq.getHeightInPixels(), false, this);
            }
            this.zzfgs.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Uri[]{this.zzfgr});
            return false;
        }
    }
}
