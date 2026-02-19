package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.tagmanager.zzcn;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public final class zzczg {
    /* access modifiers changed from: private */
    public final Context mContext;
    /* access modifiers changed from: private */
    public volatile int mState = 1;
    /* access modifiers changed from: private */
    public final zze zzata;
    /* access modifiers changed from: private */
    public final String zzknc;
    private ScheduledFuture<?> zzksg = null;
    /* access modifiers changed from: private */
    public final String zzkvn;
    /* access modifiers changed from: private */
    public final String zzkvo;
    /* access modifiers changed from: private */
    public final zzdba zzkvp;
    /* access modifiers changed from: private */
    public final zzdie zzkvq;
    /* access modifiers changed from: private */
    public final ExecutorService zzkvr;
    private final ScheduledExecutorService zzkvs;
    /* access modifiers changed from: private */
    public final zzcn zzkvt;
    /* access modifiers changed from: private */
    public final zzczp zzkvu;
    /* access modifiers changed from: private */
    public zzdav zzkvv;
    /* access modifiers changed from: private */
    public List<zzczu> zzkvw = new ArrayList();
    /* access modifiers changed from: private */
    public boolean zzkvx = false;

    zzczg(Context context, String str, String str2, String str3, zzdba zzdba, zzdie zzdie, ExecutorService executorService, ScheduledExecutorService scheduledExecutorService, zzcn zzcn, zze zze, zzczp zzczp) {
        this.mContext = context;
        String str4 = (String) zzbq.checkNotNull(str);
        this.zzknc = str4;
        this.zzkvp = (zzdba) zzbq.checkNotNull(zzdba);
        this.zzkvq = (zzdie) zzbq.checkNotNull(zzdie);
        ExecutorService executorService2 = (ExecutorService) zzbq.checkNotNull(executorService);
        this.zzkvr = executorService2;
        this.zzkvs = (ScheduledExecutorService) zzbq.checkNotNull(scheduledExecutorService);
        zzcn zzcn2 = (zzcn) zzbq.checkNotNull(zzcn);
        this.zzkvt = zzcn2;
        this.zzata = (zze) zzbq.checkNotNull(zze);
        this.zzkvu = (zzczp) zzbq.checkNotNull(zzczp);
        this.zzkvn = str3;
        this.zzkvo = str2;
        this.zzkvw.add(new zzczu("gtm.load", new Bundle(), "gtm", new Date(), false, zzcn2));
        StringBuilder sb = new StringBuilder(String.valueOf(str4).length() + 35);
        sb.append("Container ");
        sb.append(str4);
        sb.append("is scheduled for loading.");
        zzdal.v(sb.toString());
        executorService2.execute(new zzczk(this, (zzczh) null));
    }

    /* access modifiers changed from: private */
    public final void zzbj(long j) {
        ScheduledFuture<?> scheduledFuture = this.zzksg;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
        String str = this.zzknc;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 45);
        sb.append("Refresh container ");
        sb.append(str);
        sb.append(" in ");
        sb.append(j);
        sb.append("ms.");
        zzdal.v(sb.toString());
        this.zzksg = this.zzkvs.schedule(new zzczi(this), j, TimeUnit.MILLISECONDS);
    }

    public final void dispatch() {
        this.zzkvr.execute(new zzczh(this));
    }

    public final void zza(zzczu zzczu) {
        this.zzkvr.execute(new zzczl(this, zzczu));
    }
}
