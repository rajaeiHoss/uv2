package com.google.android.gms.iid;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.tasks.Task;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public final class zzm {
    /* access modifiers changed from: private */
    public final Context zzaiq;
    /* access modifiers changed from: private */
    public final ScheduledExecutorService zzind;
    private zzo zzine;
    private int zzinf;

    public zzm(Context context) {
        this(context, Executors.newSingleThreadScheduledExecutor());
    }

    private zzm(Context context, ScheduledExecutorService scheduledExecutorService) {
        this.zzine = new zzo(this);
        this.zzinf = 1;
        this.zzaiq = context.getApplicationContext();
        this.zzind = scheduledExecutorService;
    }

    private final synchronized <T> Task<T> zza(zzu<T> zzu) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String valueOf = String.valueOf(zzu);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 9);
            sb.append("Queueing ");
            sb.append(valueOf);
            Log.d("MessengerIpcClient", sb.toString());
        }
        if (!this.zzine.zzb(zzu)) {
            zzo zzo = new zzo(this);
            this.zzine = zzo;
            zzo.zzb(zzu);
        }
        return zzu.zzgyc.getTask();
    }

    private final synchronized int zzaws() {
        int i;
        i = this.zzinf;
        this.zzinf = i + 1;
        return i;
    }

    public final Task<Bundle> zzj(int i, Bundle bundle) {
        return zza(new zzw(zzaws(), 1, bundle));
    }
}
