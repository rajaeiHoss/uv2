package com.google.android.gms.internal;

final class zzemu extends zzeeb<zzemq, zzenn> {
    private boolean zznod = false;
    private /* synthetic */ zzemv zznoe;
    private /* synthetic */ zzems zznof;

    zzemu(zzems zzems, zzemv zzemv) {
        this.zznof = zzems;
        this.zznoe = zzemv;
    }

    public final void zzh(zzemq zzemq, zzenn zzenn) {
        if (!this.zznod && zzemq.compareTo(zzemq.zzcby()) > 0) {
            this.zznod = true;
            this.zznoe.zzh(zzemq.zzcby(), this.zznof.zzcce());
        }
        this.zznoe.zzh(zzemq, zzenn);
    }
}
