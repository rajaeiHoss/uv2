package com.google.android.gms.internal;

import android.os.Bundle;

final class zzdbt implements Runnable {
    private /* synthetic */ String val$name;
    private /* synthetic */ String zzjpm;
    private boolean zzkyw = false;
    private /* synthetic */ Bundle zzkyx;
    private /* synthetic */ String zzkyy;
    private /* synthetic */ long zzkyz;
    private /* synthetic */ zzdbs zzkzb;

    zzdbt(zzdbs zzdbs, String str, Bundle bundle, String str2, long j, String str3) {
        this.zzkzb = zzdbs;
        this.val$name = str;
        this.zzkyx = bundle;
        this.zzkyy = str2;
        this.zzkyz = j;
        this.zzjpm = str3;
    }

    public final void run() {
        if (this.zzkzb.zzkyv.zzkyr == 3) {
            this.zzkzb.zzkyv.zzkym.zza(this.val$name, this.zzkyx, this.zzkyy, this.zzkyz, false);
        } else if (this.zzkzb.zzkyv.zzkyr == 1 || this.zzkzb.zzkyv.zzkyr == 2) {
            if (!this.zzkyw) {
                zzdal.v(String.format("Container not loaded yet: deferring event listener by enqueuing the event: name = %s, origin = %s, params = %s.", new Object[]{this.val$name, this.zzjpm, this.zzkyx}));
                this.zzkyw = true;
                this.zzkzb.zzkyv.zzkys.add(this);
                return;
            }
            zzdal.zzcz("Invalid state - not expecting to see a deferred event during container loading.");
        } else if (this.zzkzb.zzkyv.zzkyr == 4) {
            zzdal.v(String.format("Container failed to load: skipping event listener by ignoring the event: name = %s, origin = %s, params = %s.", new Object[]{this.val$name, this.zzjpm, this.zzkyx}));
        } else {
            int zza = this.zzkzb.zzkyv.zzkyr;
            StringBuilder sb = new StringBuilder(28);
            sb.append("Unexpected state:");
            sb.append(zza);
            zzczq.zzd(sb.toString(), this.zzkzb.zzkyv.mContext);
        }
    }
}
