package com.google.android.gms.cast;

import com.google.android.gms.cast.CastRemoteDisplayLocalService;

final class zzy implements Runnable {
    private /* synthetic */ CastRemoteDisplayLocalService zzevk;
    private /* synthetic */ CastRemoteDisplayLocalService.NotificationSettings zzevn;

    zzy(CastRemoteDisplayLocalService castRemoteDisplayLocalService, CastRemoteDisplayLocalService.NotificationSettings notificationSettings) {
        this.zzevk = castRemoteDisplayLocalService;
        this.zzevn = notificationSettings;
    }

    public final void run() {
        this.zzevk.zza(this.zzevn);
    }
}
