package com.google.android.gms.cast;

final class zzv implements Runnable {
    private /* synthetic */ CastRemoteDisplayLocalService zzevk;

    zzv(CastRemoteDisplayLocalService castRemoteDisplayLocalService) {
        this.zzevk = castRemoteDisplayLocalService;
    }

    public final void run() {
        CastRemoteDisplayLocalService castRemoteDisplayLocalService = this.zzevk;
        boolean zzb = castRemoteDisplayLocalService.zzevf;
        StringBuilder sb = new StringBuilder(59);
        sb.append("onCreate after delay. The local service been started: ");
        sb.append(zzb);
        castRemoteDisplayLocalService.zzeb(sb.toString());
        if (!this.zzevk.zzevf) {
            this.zzevk.zzee("The local service has not been been started, stopping it");
            this.zzevk.stopSelf();
        }
    }
}
