package com.google.android.gms.cast;

import android.app.PendingIntent;
import android.content.Context;
import android.hardware.display.VirtualDisplay;
import android.os.RemoteException;
import android.view.Display;
import android.view.Surface;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.internal.zzbeh;
import com.google.android.gms.internal.zzbei;
import com.google.android.gms.internal.zzbez;
import com.google.android.gms.internal.zzbfd;
import com.google.android.gms.tasks.Task;

public class CastRemoteDisplayClient extends GoogleApi<Api.ApiOptions.NoOptions> {
    private static final Api<Api.ApiOptions.NoOptions> API;
    private static final Api.zza<zzbez, Api.ApiOptions.NoOptions> zzegv;
    /* access modifiers changed from: private */
    public final zzbei zzeui = new zzbei("CastRemoteDisplay");
    /* access modifiers changed from: private */
    public VirtualDisplay zzeuj;

    static class zza extends zzbfd {
        private zza() {
        }

        /* synthetic */ zza(zzp zzp) {
            this();
        }

        public void onDisconnected() throws RemoteException {
            throw new UnsupportedOperationException();
        }

        public void onError(int i) throws RemoteException {
            throw new UnsupportedOperationException();
        }

        public void zza(int i, int i2, Surface surface) throws RemoteException {
            throw new UnsupportedOperationException();
        }

        public void zzado() throws RemoteException {
            throw new UnsupportedOperationException();
        }
    }

    static {
        zzp zzp = new zzp();
        zzegv = zzp;
        API = new Api<>("CastRemoteDisplay.API", zzp, zzbeh.zzfni);
    }

    CastRemoteDisplayClient(Context context) {
        super(context, API, null, GoogleApi.zza.zzfsr);
    }

    /* access modifiers changed from: private */
    public final void zzadn() {
        VirtualDisplay virtualDisplay = this.zzeuj;
        if (virtualDisplay != null) {
            if (virtualDisplay.getDisplay() != null) {
                zzbei zzbei = this.zzeui;
                int displayId = this.zzeuj.getDisplay().getDisplayId();
                StringBuilder sb = new StringBuilder(38);
                sb.append("releasing virtual display: ");
                sb.append(displayId);
                zzbei.zzb(sb.toString(), new Object[0]);
            }
            this.zzeuj.release();
            this.zzeuj = null;
        }
    }

    /* access modifiers changed from: private */
    public static int zzk(int i, int i2) {
        return (Math.min(i, i2) * 320) / 1080;
    }

    public Task<Display> startRemoteDisplay(CastDevice castDevice, String str, int i, PendingIntent pendingIntent) {
        return zzb(new zzq(this, i, pendingIntent, castDevice, str));
    }

    public Task<Void> stopRemoteDisplay() {
        return zzb(new zzs(this));
    }
}
