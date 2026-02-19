package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.internal.zzdal;
import com.google.android.gms.internal.zzdar;
import com.google.android.gms.internal.zzdbo;

public class TagManagerApiImpl extends zzcr {
    private zzdbo zzkuf;

    public void initialize(IObjectWrapper iObjectWrapper, zzcn zzcn, zzce zzce) throws RemoteException {
        zzdbo zza = zzdbo.zza((Context) zzn.zzy(iObjectWrapper), zzcn, zzce);
        this.zzkuf = zza;
        zza.zzf((String[]) null);
    }

    @Deprecated
    public void preview(Intent intent, IObjectWrapper iObjectWrapper) {
        zzdal.zzcz("Deprecated. Please use previewIntent instead.");
    }

    public void previewIntent(Intent intent, IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, zzcn zzcn, zzce zzce) {
        Context context = (Context) zzn.zzy(iObjectWrapper);
        zzdbo zza = zzdbo.zza(context, zzcn, zzce);
        this.zzkuf = zza;
        new zzdar(intent, context, (Context) zzn.zzy(iObjectWrapper2), zza).zzbiz();
    }
}
