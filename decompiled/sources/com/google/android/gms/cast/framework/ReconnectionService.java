package com.google.android.gms.cast.framework;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.internal.zzbae;
import com.google.android.gms.internal.zzbei;

public class ReconnectionService extends Service {
    private static final zzbei zzeui = new zzbei("ReconnectionService");
    private zzr zzfbf;

    public IBinder onBind(Intent intent) {
        try {
            return this.zzfbf.onBind(intent);
        } catch (RemoteException e) {
            zzeui.zzb(e, "Unable to call %s on %s.", "onBind", zzr.class.getSimpleName());
            return null;
        }
    }

    public void onCreate() {
        CastContext sharedInstance = CastContext.getSharedInstance(this);
        zzr zza = zzbae.zza(this, sharedInstance.getSessionManager().zzaec(), sharedInstance.zzaeb().zzaec());
        this.zzfbf = zza;
        try {
            zza.onCreate();
        } catch (RemoteException e) {
            zzeui.zzb(e, "Unable to call %s on %s.", "onCreate", zzr.class.getSimpleName());
        }
        super.onCreate();
    }

    public void onDestroy() {
        try {
            this.zzfbf.onDestroy();
        } catch (RemoteException e) {
            zzeui.zzb(e, "Unable to call %s on %s.", "onDestroy", zzr.class.getSimpleName());
        }
        super.onDestroy();
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        try {
            return this.zzfbf.onStartCommand(intent, i, i2);
        } catch (RemoteException e) {
            zzeui.zzb(e, "Unable to call %s on %s.", "onStartCommand", zzr.class.getSimpleName());
            return 1;
        }
    }
}
