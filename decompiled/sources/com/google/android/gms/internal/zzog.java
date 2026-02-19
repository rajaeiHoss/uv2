package com.google.android.gms.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.ConditionVariable;
import com.google.android.gms.common.zzs;

@zzabh
public final class zzog {
    private final Object mLock = new Object();
    private volatile boolean zzarf = false;
    private final ConditionVariable zzbkw = new ConditionVariable();
    /* access modifiers changed from: private */
    public SharedPreferences zzbkx = null;
    private Context zzbky;

    public final void initialize(Context context) {
        if (!this.zzarf) {
            synchronized (this.mLock) {
                if (!this.zzarf) {
                    this.zzbky = context.getApplicationContext() == null ? context : context.getApplicationContext();
                    try {
                        Context remoteContext = zzs.getRemoteContext(context);
                        if (remoteContext == null && context != null) {
                            Context applicationContext = context.getApplicationContext();
                            if (applicationContext != null) {
                                context = applicationContext;
                            }
                            remoteContext = context;
                        }
                        if (remoteContext != null) {
                            zzlc.zzim();
                            this.zzbkx = remoteContext.getSharedPreferences("google_ads_flags", 0);
                            this.zzarf = true;
                            this.zzbkw.open();
                        }
                    } finally {
                        this.zzbkw.open();
                    }
                }
            }
        }
    }

    public final <T> T zzd(zzny<T> zzny) {
        if (this.zzbkw.block(5000)) {
            if (!this.zzarf || this.zzbkx == null) {
                synchronized (this.mLock) {
                    if (this.zzarf) {
                        if (this.zzbkx == null) {
                        }
                    }
                    T zzje = zzny.zzje();
                    return zzje;
                }
            }
            return (T) zzakg.zza(this.zzbky, new zzoh(this, zzny));
        }
        throw new IllegalStateException("Flags.initialize() was not called!");
    }
}
