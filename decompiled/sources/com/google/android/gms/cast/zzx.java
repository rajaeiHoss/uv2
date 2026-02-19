package com.google.android.gms.cast;

final class zzx implements Runnable {
    private /* synthetic */ CastRemoteDisplayLocalService zzevk;
    private /* synthetic */ boolean zzevq;

    zzx(CastRemoteDisplayLocalService castRemoteDisplayLocalService, boolean z) {
        this.zzevk = castRemoteDisplayLocalService;
        this.zzevq = z;
    }

    public final void run() {
        this.zzevk.zzaw(this.zzevq);
    }
}
