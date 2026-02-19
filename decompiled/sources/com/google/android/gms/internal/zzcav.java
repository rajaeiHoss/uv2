package com.google.android.gms.internal;

import android.util.Log;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.fitness.result.DataReadResult;

final class zzcav extends zzbyl {
    private final zzn<DataReadResult> zzhmf;
    private int zzhmz;
    private DataReadResult zzhna;

    private zzcav(zzn<DataReadResult> zzn) {
        this.zzhmz = 0;
        this.zzhna = null;
        this.zzhmf = zzn;
    }

    /* synthetic */ zzcav(zzn zzn, zzcan zzcan) {
        this(zzn);
    }

    public final void zza(DataReadResult dataReadResult) {
        synchronized (this) {
            if (Log.isLoggable("Fitness", 2)) {
                int i = this.zzhmz;
                StringBuilder sb = new StringBuilder(33);
                sb.append("Received batch result ");
                sb.append(i);
                Log.v("Fitness", sb.toString());
            }
            DataReadResult dataReadResult2 = this.zzhna;
            if (dataReadResult2 == null) {
                this.zzhna = dataReadResult;
            } else {
                dataReadResult2.zzb(dataReadResult);
            }
            int i2 = this.zzhmz + 1;
            this.zzhmz = i2;
            if (i2 == this.zzhna.zzasj()) {
                this.zzhmf.setResult(this.zzhna);
            }
        }
    }
}
