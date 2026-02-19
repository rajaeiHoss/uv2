package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.RemoteException;

final class zzdbr implements Runnable {
    private /* synthetic */ String val$name;
    private /* synthetic */ String zzjpm;
    private boolean zzkyw = false;
    private /* synthetic */ Bundle zzkyx;
    private /* synthetic */ String zzkyy;
    private /* synthetic */ long zzkyz;
    private /* synthetic */ zzdbq zzkza;

    zzdbr(zzdbq zzdbq, String str, Bundle bundle, String str2, long j, String str3) {
        this.zzkza = zzdbq;
        this.val$name = str;
        this.zzkyx = bundle;
        this.zzkyy = str2;
        this.zzkyz = j;
        this.zzjpm = str3;
    }

    public final void run() {
        if (this.zzkza.zzkyv.zzkyr == 3) {
            this.zzkza.zzkyv.zzkym.zza(this.val$name, this.zzkyx, this.zzkyy, this.zzkyz, true);
        } else if (this.zzkza.zzkyv.zzkyr == 4) {
            zzdal.v(String.format("Container failed to load: skipping  event interceptor by logging event back to Firebase directly: name = %s, origin = %s, params = %s.", new Object[]{this.val$name, this.zzkyy, this.zzkyx}));
            try {
                this.zzkza.zzkyv.zzkvt.logEventInternalNoInterceptor(this.zzkyy, this.val$name, this.zzkyx, this.zzkyz);
            } catch (RemoteException e) {
                zzczq.zza("Error logging event on measurement proxy: ", e, this.zzkza.zzkyv.mContext);
            }
        } else if (this.zzkza.zzkyv.zzkyr != 1 && this.zzkza.zzkyv.zzkyr != 2) {
            int zza = this.zzkza.zzkyv.zzkyr;
            StringBuilder sb = new StringBuilder(28);
            sb.append("Unexpected state:");
            sb.append(zza);
            zzczq.zzd(sb.toString(), this.zzkza.zzkyv.mContext);
        } else if (!this.zzkyw) {
            zzdal.v(String.format("Container not loaded yet: deferring event interceptor by enqueuing the event: name = %s, origin = %s, params = %s.", new Object[]{this.val$name, this.zzjpm, this.zzkyx}));
            this.zzkyw = true;
            this.zzkza.zzkyv.zzkys.add(this);
        } else {
            zzczq.zzd("Invalid state - not expecting to see a deferredevent during container loading.", this.zzkza.zzkyv.mContext);
        }
    }
}
