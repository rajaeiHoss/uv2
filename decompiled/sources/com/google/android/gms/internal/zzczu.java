package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.RemoteException;
import android.os.SystemClock;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.tagmanager.zzcn;
import java.util.Date;
import java.util.Map;

public final class zzczu implements zze {
    private final zzcn zzkvt;
    private final Bundle zzkwi;
    private final String zzkwj;
    private final Date zzkwk;
    private final String zzkwl;
    private Map<String, Object> zzkwm;
    private boolean zzkwn;

    public zzczu(String str, Bundle bundle, String str2, Date date, boolean z, zzcn zzcn) {
        this.zzkwj = str;
        this.zzkwi = bundle == null ? new Bundle() : bundle;
        this.zzkwk = date;
        this.zzkwl = str2;
        this.zzkwn = z;
        this.zzkvt = zzcn;
    }

    public final long currentTimeMillis() {
        return this.zzkwk.getTime();
    }

    public final long elapsedRealtime() {
        return SystemClock.elapsedRealtime();
    }

    public final long nanoTime() {
        return System.nanoTime();
    }

    public final Map<String, Object> zzbgl() {
        if (this.zzkwm == null) {
            try {
                this.zzkwm = this.zzkvt.zzbgl();
            } catch (RemoteException e) {
                String valueOf = String.valueOf(e.getMessage());
                zzdal.e(valueOf.length() != 0 ? "Error calling measurement proxy:".concat(valueOf) : new String("Error calling measurement proxy:"));
            }
        }
        return this.zzkwm;
    }

    public final String zzbio() {
        return this.zzkwj;
    }

    public final Bundle zzbip() {
        return this.zzkwi;
    }

    public final String zzbiq() {
        return this.zzkwl;
    }

    public final boolean zzbir() {
        return this.zzkwn;
    }

    public final void zzcb(boolean z) {
        this.zzkwn = false;
    }
}
