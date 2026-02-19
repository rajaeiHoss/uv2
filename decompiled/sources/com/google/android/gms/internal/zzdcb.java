package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzdcf;
import com.google.android.gms.tagmanager.zzce;
import com.google.android.gms.tagmanager.zzcn;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;

public final class zzdcb extends zzdai {
    /* access modifiers changed from: private */
    public final Context mContext;
    private final ExecutorService zzkvr;
    private final zzcn zzkvt;
    /* access modifiers changed from: private */
    public final Map<String, zzczg> zzkzk;
    /* access modifiers changed from: private */
    public final zzczo zzkzl;

    private zzdcb(Context context, zzcn zzcn, zzczo zzczo, ExecutorService executorService) {
        this.zzkzk = new HashMap(1);
        zzbq.checkNotNull(zzcn);
        this.zzkvt = zzcn;
        this.zzkzl = zzczo;
        this.zzkvr = executorService;
        this.mContext = context;
    }

    public zzdcb(Context context, zzcn zzcn, zzce zzce) {
        this(context, zzcn, new zzczo(context, zzcn, zzce), zzdcf.zza.zzes(context));
    }

    public final void dispatch() {
        this.zzkvr.execute(new zzdce(this));
    }

    public final void zza(String str, Bundle bundle, String str2, long j, boolean z) throws RemoteException {
        this.zzkvr.execute(new zzdcd(this, new zzczu(str, bundle, str2, new Date(j), z, this.zzkvt)));
    }

    public final void zza(String str, String str2, String str3, zzdae zzdae) throws RemoteException {
        this.zzkvr.execute(new zzdcc(this, str, str2, str3, zzdae));
    }

    public final void zzbiv() throws RemoteException {
        this.zzkzk.clear();
    }

    public final void zzn(String str, String str2, String str3) throws RemoteException {
        zza(str, str2, str3, (zzdae) null);
    }
}
