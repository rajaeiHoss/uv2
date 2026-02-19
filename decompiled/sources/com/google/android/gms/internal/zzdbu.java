package com.google.android.gms.internal;

import com.google.android.gms.internal.zzdbo;

final class zzdbu implements Runnable {
    private /* synthetic */ zzdbo zzkyv;
    private /* synthetic */ String zzkzc;
    private /* synthetic */ String zzkzd;
    private /* synthetic */ String zzkze = null;

    zzdbu(zzdbo zzdbo, String str, String str2, String str3) {
        this.zzkyv = zzdbo;
        this.zzkzc = str;
        this.zzkzd = str2;
    }

    public final void run() {
        String str = this.zzkzc;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 28);
        sb.append("Starting to load container ");
        sb.append(str);
        sb.append(".");
        zzdal.v(sb.toString());
        if (this.zzkyv.zzkyr != 1) {
            zzczq.zzd("Unexpected state - container loading already initiated.", this.zzkyv.mContext);
            return;
        }
        int unused = this.zzkyv.zzkyr = 2;
        this.zzkyv.zzkym.zzb(this.zzkzc, this.zzkzd, this.zzkze, this.zzkyv.new zzb());
    }
}
