package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamic.zzn;

public final class zzdlr extends zzdlc<zzdlh> {
    private final zzdls zzlie;

    public zzdlr(Context context, zzdls zzdls) {
        super(context, "TextNativeHandle");
        this.zzlie = zzdls;
        zzblo();
    }

    /* access modifiers changed from: protected */
    public final zzdlh zza(DynamiteModule dynamiteModule, Context context) throws RemoteException, DynamiteModule.zzc {
        IBinder zzhk = dynamiteModule.zzhk("com.google.android.gms.vision.text.ChimeraNativeTextRecognizerCreator");
        zzdlj zzdlj = null;
        if (zzhk != null) {
            IInterface queryLocalInterface = zzhk.queryLocalInterface("com.google.android.gms.vision.text.internal.client.INativeTextRecognizerCreator");
            zzdlj = queryLocalInterface instanceof zzdlj ? (zzdlj) queryLocalInterface : new zzdlk(zzhk);
        }
        if (zzdlj == null) {
            return null;
        }
        return zzdlj.zza(zzn.zzz(context), this.zzlie);
    }

    public final zzdll[] zza(Bitmap bitmap, zzdld zzdld, zzdln zzdln) {
        if (!isOperational()) {
            return new zzdll[0];
        }
        try {
            return ((zzdlh) zzblo()).zza(zzn.zzz(bitmap), zzdld, zzdln);
        } catch (RemoteException e) {
            Log.e("TextNativeHandle", "Error calling native text recognizer", e);
            return new zzdll[0];
        }
    }

    /* access modifiers changed from: protected */
    public final void zzbll() throws RemoteException {
        ((zzdlh) zzblo()).zzblp();
    }
}
