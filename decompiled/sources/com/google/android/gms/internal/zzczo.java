package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zzi;
import com.google.android.gms.internal.zzdcf;
import com.google.android.gms.tagmanager.zzce;
import com.google.android.gms.tagmanager.zzcn;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;

public final class zzczo {
    private final Context mContext;
    private final ExecutorService zzbwg;
    private final zzdie zzkvq;
    private final zzcn zzkvt;
    private final ScheduledExecutorService zzkwb;
    private final zzce zzkwc;

    public zzczo(Context context, zzcn zzcn, zzce zzce) {
        this(context, zzcn, zzce, new zzdie(context), zzdcf.zza.zzes(context), zzdch.zzkzq);
    }

    private zzczo(Context context, zzcn zzcn, zzce zzce, zzdie zzdie, ExecutorService executorService, ScheduledExecutorService scheduledExecutorService) {
        this.mContext = ((Context) zzbq.checkNotNull(context)).getApplicationContext();
        this.zzkvt = (zzcn) zzbq.checkNotNull(zzcn);
        this.zzkwc = (zzce) zzbq.checkNotNull(zzce);
        this.zzkvq = (zzdie) zzbq.checkNotNull(zzdie);
        this.zzbwg = (ExecutorService) zzbq.checkNotNull(executorService);
        this.zzkwb = (ScheduledExecutorService) zzbq.checkNotNull(scheduledExecutorService);
    }

    public final zzczg zzm(String str, String str2, String str3) {
        zzdba zzdba = new zzdba(this.mContext, this.zzkvt, this.zzkwc, str);
        zzczp zzczp = new zzczp(this.mContext, str);
        return new zzczg(this.mContext, str, str2, str3, zzdba, this.zzkvq, this.zzbwg, this.zzkwb, this.zzkvt, zzi.zzanq(), zzczp);
    }
}
