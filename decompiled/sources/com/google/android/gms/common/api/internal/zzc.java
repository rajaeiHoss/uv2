package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzm;

public final class zzc<A extends zzm<? extends Result, Api.zzb>> extends zza {
    private A zzftx;

    public zzc(int i, A a) {
        super(i);
        this.zzftx = a;
    }

    public final void zza(zzae zzae, boolean z) {
        zzae.zza((BasePendingResult<? extends Result>) this.zzftx, z);
    }

    public final void zza(zzbo<?> zzbo) throws DeadObjectException {
        try {
            this.zzftx.zzb(zzbo.zzaix());
        } catch (RuntimeException e) {
            String simpleName = e.getClass().getSimpleName();
            String localizedMessage = e.getLocalizedMessage();
            StringBuilder sb = new StringBuilder(String.valueOf(simpleName).length() + 2 + String.valueOf(localizedMessage).length());
            sb.append(simpleName);
            sb.append(": ");
            sb.append(localizedMessage);
            this.zzftx.zzu(new Status(10, sb.toString()));
        }
    }

    public final void zzs(Status status) {
        this.zzftx.zzu(status);
    }
}
