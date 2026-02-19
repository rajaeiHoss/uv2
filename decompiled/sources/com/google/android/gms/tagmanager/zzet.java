package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zzbs;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

final class zzet implements zzag {
    private boolean mClosed;
    /* access modifiers changed from: private */
    public final Context mContext;
    /* access modifiers changed from: private */
    public final String zzknc;
    private String zzkoa;
    private zzdi<zzbs> zzksb;
    private zzal zzksc;
    private final ScheduledExecutorService zzkse;
    private final zzew zzksf;
    private ScheduledFuture<?> zzksg;

    public zzet(Context context, String str, zzal zzal) {
        this(context, str, zzal, (zzex) null, (zzew) null);
    }

    private zzet(Context context, String str, zzal zzal, zzex zzex, zzew zzew) {
        this.zzksc = zzal;
        this.mContext = context;
        this.zzknc = str;
        this.zzkse = new zzeu(this).zzbhl();
        this.zzksf = new zzev(this);
    }

    private final synchronized void zzbhk() {
        if (this.mClosed) {
            throw new IllegalStateException("called method after closed");
        }
    }

    public final synchronized void release() {
        zzbhk();
        ScheduledFuture<?> scheduledFuture = this.zzksg;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
        this.zzkse.shutdown();
        this.mClosed = true;
    }

    public final synchronized void zza(long j, String str) {
        String str2 = this.zzknc;
        StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 55);
        sb.append("loadAfterDelay: containerId=");
        sb.append(str2);
        sb.append(" delay=");
        sb.append(j);
        zzdj.v(sb.toString());
        zzbhk();
        if (this.zzksb != null) {
            ScheduledFuture<?> scheduledFuture = this.zzksg;
            if (scheduledFuture != null) {
                scheduledFuture.cancel(false);
            }
            ScheduledExecutorService scheduledExecutorService = this.zzkse;
            zzes zza = this.zzksf.zza(this.zzksc);
            zza.zza(this.zzksb);
            zza.zzll(this.zzkoa);
            zza.zzmb(str);
            this.zzksg = scheduledExecutorService.schedule(zza, j, TimeUnit.MILLISECONDS);
        } else {
            throw new IllegalStateException("callback must be set before loadAfterDelay() is called.");
        }
    }

    public final synchronized void zza(zzdi<zzbs> zzdi) {
        zzbhk();
        this.zzksb = zzdi;
    }

    public final synchronized void zzll(String str) {
        zzbhk();
        this.zzkoa = str;
    }
}
