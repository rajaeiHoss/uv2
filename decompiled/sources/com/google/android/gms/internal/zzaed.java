package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbt;
import java.util.concurrent.Callable;

final class zzaed implements Callable<zzaea> {
    private /* synthetic */ Context val$context;
    private /* synthetic */ zzaec zzczb;

    zzaed(zzaec zzaec, Context context) {
        this.zzczb = zzaec;
        this.val$context = context;
    }

    public final zzaea call() throws Exception {
        zzaea zzaea;
        zzaee zzaee = (zzaee) this.zzczb.zzcza.get(this.val$context);
        if (zzaee != null) {
            if (!(zzaee.zzczc + ((Long) zzlc.zzio().zzd(zzoi.zzbqp)).longValue() < zzbt.zzes().currentTimeMillis())) {
                if (((Boolean) zzlc.zzio().zzd(zzoi.zzbqo)).booleanValue()) {
                    zzaea = new zzaeb(this.val$context, zzaee.zzczd).zzom();
                    this.zzczb.zzcza.put(this.val$context, new zzaee(this.zzczb, zzaea));
                    return zzaea;
                }
            }
        }
        zzaea = new zzaeb(this.val$context).zzom();
        this.zzczb.zzcza.put(this.val$context, new zzaee(this.zzczb, zzaea));
        return zzaea;
    }
}
