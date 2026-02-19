package com.google.android.gms.internal;

final class zzcnk extends zzcip {
    private /* synthetic */ zzckj zzjhl;
    private /* synthetic */ zzcnj zzjsh;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcnk(zzcnj zzcnj, zzckj zzckj, zzckj zzckj2) {
        super(zzckj);
        this.zzjsh = zzcnj;
        this.zzjhl = zzckj2;
    }

    public final void run() {
        this.zzjsh.cancel();
        this.zzjsh.zzayp().zzbba().log("Starting upload from DelayedRunnable");
        this.zzjhl.zzbby();
    }
}
