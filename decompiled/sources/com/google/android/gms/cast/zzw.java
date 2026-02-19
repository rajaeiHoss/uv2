package com.google.android.gms.cast;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.google.android.gms.cast.CastRemoteDisplayLocalService;
import com.google.android.gms.common.api.Status;

final class zzw implements ServiceConnection {
    private /* synthetic */ String zzetg;
    private /* synthetic */ CastDevice zzevl;
    private /* synthetic */ CastRemoteDisplayLocalService.Options zzevm;
    private /* synthetic */ CastRemoteDisplayLocalService.NotificationSettings zzevn;
    private /* synthetic */ Context zzevo;
    private /* synthetic */ CastRemoteDisplayLocalService.Callbacks zzevp;

    zzw(String str, CastDevice castDevice, CastRemoteDisplayLocalService.Options options, CastRemoteDisplayLocalService.NotificationSettings notificationSettings, Context context, CastRemoteDisplayLocalService.Callbacks callbacks) {
        this.zzetg = str;
        this.zzevl = castDevice;
        this.zzevm = options;
        this.zzevn = notificationSettings;
        this.zzevo = context;
        this.zzevp = callbacks;
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        CastRemoteDisplayLocalService castRemoteDisplayLocalService = CastRemoteDisplayLocalService.getInstance();
        if (castRemoteDisplayLocalService != null) {
            if (castRemoteDisplayLocalService.zza(this.zzetg, this.zzevl, this.zzevm, this.zzevn, this.zzevo, this, this.zzevp)) {
                return;
            }
        }
        CastRemoteDisplayLocalService.zzeus.zzc("Connected but unable to get the service instance", new Object[0]);
        this.zzevp.onRemoteDisplaySessionError(new Status(CastStatusCodes.ERROR_SERVICE_CREATION_FAILED));
        CastRemoteDisplayLocalService.zzeuv.set(false);
        try {
            this.zzevo.unbindService(this);
        } catch (IllegalArgumentException unused) {
            CastRemoteDisplayLocalService.zzeus.zzb("No need to unbind service, already unbound", new Object[0]);
        }
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        CastRemoteDisplayLocalService.zzeus.zzb("onServiceDisconnected", new Object[0]);
        this.zzevp.onRemoteDisplaySessionError(new Status(CastStatusCodes.ERROR_SERVICE_DISCONNECTED, "Service Disconnected"));
        CastRemoteDisplayLocalService.zzeuv.set(false);
        try {
            this.zzevo.unbindService(this);
        } catch (IllegalArgumentException unused) {
            CastRemoteDisplayLocalService.zzeus.zzb("No need to unbind service, already unbound", new Object[0]);
        }
    }
}
