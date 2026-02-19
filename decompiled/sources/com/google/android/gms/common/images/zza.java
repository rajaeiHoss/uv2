package com.google.android.gms.common.images;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.google.android.gms.common.internal.zzc;
import com.google.android.gms.internal.zzbgk;

public abstract class zza {
    final zzb zzgdp;
    private int zzgdq = 0;
    protected int zzgdr = 0;
    private boolean zzgds = false;
    private boolean zzgdt = true;
    private boolean zzgdu = false;
    private boolean zzgdv = true;

    public zza(Uri uri, int i) {
        this.zzgdp = new zzb(uri);
        this.zzgdr = i;
    }

    /* access modifiers changed from: package-private */
    public final void zza(Context context, Bitmap bitmap, boolean z) {
        zzc.zzv(bitmap);
        zza(new BitmapDrawable(context.getResources(), bitmap), z, false, true);
    }

    /* access modifiers changed from: package-private */
    public final void zza(Context context, zzbgk zzbgk) {
        if (this.zzgdv) {
            zza((Drawable) null, false, true, false);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(Context context, zzbgk zzbgk, boolean z) {
        int i = this.zzgdr;
        zza(i != 0 ? context.getResources().getDrawable(i) : null, z, false, false);
    }

    /* access modifiers changed from: protected */
    public abstract void zza(Drawable drawable, boolean z, boolean z2, boolean z3);

    /* access modifiers changed from: protected */
    public final boolean zzc(boolean z, boolean z2) {
        return this.zzgdt && !z2 && !z;
    }
}
