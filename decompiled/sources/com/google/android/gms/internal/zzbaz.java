package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.RemoteException;
import android.support.v4.media.session.PlaybackStateCompat;
import com.google.android.gms.search.SearchAuth;

public final class zzbaz extends AsyncTask<Uri, Long, Bitmap> {
    private static final zzbei zzeui = new zzbei("FetchBitmapTask");
    private final zzbbe zzfgw;
    private final zzbbb zzfgx;

    private zzbaz(Context context, int i, int i2, boolean z, long j, int i3, int i4, int i5, zzbbb zzbbb) {
        this.zzfgw = zzbae.zza(context.getApplicationContext(), this, new zzbbd(this), i, i2, z, PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE, 5, 333, SearchAuth.StatusCodes.AUTH_DISABLED);
        this.zzfgx = zzbbb;
    }

    public zzbaz(Context context, int i, int i2, boolean z, zzbbb zzbbb) {
        this(context, i, i2, false, PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE, 5, 333, SearchAuth.StatusCodes.AUTH_DISABLED, zzbbb);
    }

    public zzbaz(Context context, zzbbb zzbbb) {
        this(context, 0, 0, false, PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE, 5, 333, SearchAuth.StatusCodes.AUTH_DISABLED, zzbbb);
    }

    /* access modifiers changed from: private */
    public final Bitmap doInBackground(Uri... uriArr) {
        if (uriArr.length == 1 && uriArr[0] != null) {
            try {
                return this.zzfgw.zzm(uriArr[0]);
            } catch (RemoteException e) {
                zzeui.zzb(e, "Unable to call %s on %s.", "doFetch", zzbbe.class.getSimpleName());
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public final void onPostExecute(Bitmap bitmap) {
        zzbbb zzbbb = this.zzfgx;
        if (zzbbb != null) {
            zzbbb.onPostExecute(bitmap);
        }
    }

    final void zzb(long j, long j2) {
        publishProgress(Long.valueOf(j), Long.valueOf(j2));
    }
}
