package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.CastRemoteDisplay;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.internal.zzr;

@Deprecated
public final class zzbfa extends zzab<zzbfe> implements IBinder.DeathRecipient {
    /* access modifiers changed from: private */
    public static final zzbei zzeus = new zzbei("CastRemoteDisplayClientImpl");
    private CastDevice zzetm;
    /* access modifiers changed from: private */
    public CastRemoteDisplay.CastRemoteDisplaySessionCallbacks zzfov;
    private Bundle zzfow;

    public zzbfa(Context context, Looper looper, zzr zzr, CastDevice castDevice, Bundle bundle, CastRemoteDisplay.CastRemoteDisplaySessionCallbacks castRemoteDisplaySessionCallbacks, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 83, zzr, connectionCallbacks, onConnectionFailedListener);
        zzeus.zzb("instance created", new Object[0]);
        this.zzfov = castRemoteDisplaySessionCallbacks;
        this.zzetm = castDevice;
        this.zzfow = bundle;
    }

    public final void binderDied() {
    }

    public final void disconnect() {
        zzeus.zzb("disconnect", new Object[0]);
        this.zzfov = null;
        this.zzetm = null;
        try {
            ((zzbfe) zzalw()).disconnect();
        } catch (RemoteException | IllegalStateException unused) {
        } finally {
            super.disconnect();
        }
    }

    public final void zza(zzbfc zzbfc) throws RemoteException {
        zzeus.zzb("stopRemoteDisplay", new Object[0]);
        ((zzbfe) zzalw()).zza(zzbfc);
    }

    public final void zza(zzbfc zzbfc, zzbfg zzbfg, String str) throws RemoteException {
        zzeus.zzb("startRemoteDisplay", new Object[0]);
        zzbfc zzbfc2 = zzbfc;
        ((zzbfe) zzalw()).zza(zzbfc2, (zzbfg) new zzbfb(this, zzbfg), this.zzetm.getDeviceId(), str, this.zzfow);
    }

    public final /* synthetic */ zzbfe zzd(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.cast.remote_display.ICastRemoteDisplayService");
        return queryLocalInterface instanceof zzbfe ? (zzbfe) queryLocalInterface : new zzbff(iBinder);
    }

    /* access modifiers changed from: protected */
    public final String zzhm() {
        return "com.google.android.gms.cast.remote_display.service.START";
    }

    /* access modifiers changed from: protected */
    public final String zzhn() {
        return "com.google.android.gms.cast.remote_display.ICastRemoteDisplayService";
    }
}
