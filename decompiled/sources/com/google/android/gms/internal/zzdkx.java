package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.vision.barcode.Barcode;
import java.nio.ByteBuffer;

public final class zzdkx extends zzdlc<zzdky> {
    private final zzdkv zzlgp;

    public zzdkx(Context context, zzdkv zzdkv) {
        super(context, "BarcodeNativeHandle");
        this.zzlgp = zzdkv;
        zzblo();
    }

    /* access modifiers changed from: protected */
    public final zzdky zza(DynamiteModule dynamiteModule, Context context) throws RemoteException, DynamiteModule.zzc {
        zzdla zzdla;
        IBinder zzhk = dynamiteModule.zzhk("com.google.android.gms.vision.barcode.ChimeraNativeBarcodeDetectorCreator");
        if (zzhk == null) {
            zzdla = null;
        } else {
            IInterface queryLocalInterface = zzhk.queryLocalInterface("com.google.android.gms.vision.barcode.internal.client.INativeBarcodeDetectorCreator");
            zzdla = queryLocalInterface instanceof zzdla ? (zzdla) queryLocalInterface : new zzdlb(zzhk);
        }
        IObjectWrapper zzz = zzn.zzz(context);
        if (zzdla == null) {
            return null;
        }
        return zzdla.zza(zzz, this.zzlgp);
    }

    public final Barcode[] zza(Bitmap bitmap, zzdld zzdld) {
        if (!isOperational()) {
            return new Barcode[0];
        }
        try {
            return ((zzdky) zzblo()).zzb(zzn.zzz(bitmap), zzdld);
        } catch (RemoteException e) {
            Log.e("BarcodeNativeHandle", "Error calling native barcode detector", e);
            return new Barcode[0];
        }
    }

    public final Barcode[] zza(ByteBuffer byteBuffer, zzdld zzdld) {
        if (!isOperational()) {
            return new Barcode[0];
        }
        try {
            return ((zzdky) zzblo()).zza(zzn.zzz(byteBuffer), zzdld);
        } catch (RemoteException e) {
            Log.e("BarcodeNativeHandle", "Error calling native barcode detector", e);
            return new Barcode[0];
        }
    }

    /* access modifiers changed from: protected */
    public final void zzbll() throws RemoteException {
        if (isOperational()) {
            ((zzdky) zzblo()).zzblm();
        }
    }
}
