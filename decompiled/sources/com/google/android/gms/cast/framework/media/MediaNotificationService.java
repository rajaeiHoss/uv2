package com.google.android.gms.cast.framework.media;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.internal.zzbae;
import com.google.android.gms.internal.zzbei;

public class MediaNotificationService extends Service {
    public static final String ACTION_UPDATE_NOTIFICATION = "com.google.android.gms.cast.framework.action.UPDATE_NOTIFICATION";
    private static final zzbei zzeui = new zzbei("MediaNotificationService");
    private zzd zzfdz;

    public IBinder onBind(Intent intent) {
        try {
            return this.zzfdz.onBind(intent);
        } catch (RemoteException e) {
            zzeui.zzb(e, "Unable to call %s on %s.", "onBind", zzd.class.getSimpleName());
            return null;
        }
    }

    public void onCreate() {
        zzd zza = zzbae.zza((Service) this, CastContext.getSharedInstance(this).zzaec(), zzn.zzz(null), CastContext.getSharedInstance(this).getCastOptions().getCastMediaOptions());
        this.zzfdz = zza;
        try {
            zza.onCreate();
        } catch (RemoteException e) {
            zzeui.zzb(e, "Unable to call %s on %s.", "onCreate", zzd.class.getSimpleName());
        }
        super.onCreate();
    }

    public void onDestroy() {
        try {
            this.zzfdz.onDestroy();
        } catch (RemoteException e) {
            zzeui.zzb(e, "Unable to call %s on %s.", "onDestroy", zzd.class.getSimpleName());
        }
        super.onDestroy();
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        try {
            return this.zzfdz.onStartCommand(intent, i, i2);
        } catch (RemoteException e) {
            zzeui.zzb(e, "Unable to call %s on %s.", "onStartCommand", zzd.class.getSimpleName());
            return 1;
        }
    }
}
