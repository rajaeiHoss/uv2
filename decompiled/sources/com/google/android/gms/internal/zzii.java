package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.DeadObjectException;
import com.google.android.gms.common.internal.zzf;

final class zzii implements zzf {
    private /* synthetic */ zzif zzbay;

    zzii(zzif zzif) {
        this.zzbay = zzif;
    }

    public final void onConnected(Bundle bundle) {
        synchronized (this.zzbay.mLock) {
            try {
                if (this.zzbay.zzbaw != null) {
                    zzif zzif = this.zzbay;
                    zziq unused = zzif.zzbax = zzif.zzbaw.zzho();
                }
            } catch (DeadObjectException e) {
                zzahw.zzb("Unable to obtain a cache service instance.", e);
                this.zzbay.disconnect();
            }
            this.zzbay.mLock.notifyAll();
        }
    }

    public final void onConnectionSuspended(int i) {
        synchronized (this.zzbay.mLock) {
            zziq unused = this.zzbay.zzbax = null;
            this.zzbay.mLock.notifyAll();
        }
    }
}
