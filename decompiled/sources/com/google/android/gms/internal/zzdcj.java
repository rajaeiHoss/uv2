package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.stats.zza;
import com.google.android.gms.tagmanager.TagManagerService;

public final class zzdcj implements ServiceConnection {
    private final Context mContext;
    private final zza zzggx;
    private volatile boolean zzkzr;
    private volatile boolean zzkzs;
    private zzdah zzkzt;

    public zzdcj(Context context) {
        this(context, zza.zzanm());
    }

    private zzdcj(Context context, zza zza) {
        this.zzkzr = false;
        this.zzkzs = false;
        this.mContext = context;
        this.zzggx = zza;
    }

    private final boolean zzbji() {
        if (this.zzkzr) {
            return true;
        }
        synchronized (this) {
            if (this.zzkzr) {
                return true;
            }
            if (!this.zzkzs) {
                if (!this.zzggx.zza(this.mContext, new Intent(this.mContext, TagManagerService.class), this, 1)) {
                    return false;
                }
                this.zzkzs = true;
            }
            while (this.zzkzs) {
                try {
                    wait();
                    this.zzkzs = false;
                } catch (InterruptedException e) {
                    zzdal.zzc("Error connecting to TagManagerService", e);
                    this.zzkzs = false;
                }
            }
            boolean z = this.zzkzr;
            return z;
        }
    }

    public final void dispatch() {
        if (zzbji()) {
            try {
                this.zzkzt.dispatch();
            } catch (RemoteException e) {
                zzdal.zzc("Error calling service to dispatch pending events", e);
            }
        }
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        zzdah zzdah;
        synchronized (this) {
            if (iBinder == null) {
                zzdah = null;
            } else {
                IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.tagmanager.internal.ITagManagerService");
                zzdah = queryLocalInterface instanceof zzdah ? (zzdah) queryLocalInterface : new zzdaj(iBinder);
            }
            this.zzkzt = zzdah;
            this.zzkzr = true;
            this.zzkzs = false;
            notifyAll();
        }
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        synchronized (this) {
            this.zzkzt = null;
            this.zzkzr = false;
            this.zzkzs = false;
        }
    }

    public final void zza(String str, Bundle bundle, String str2, long j, boolean z) {
        if (zzbji()) {
            try {
                this.zzkzt.zza(str, bundle, str2, j, z);
            } catch (RemoteException e) {
                zzdal.zzc("Error calling service to emit event", e);
            }
        }
    }

    public final void zzb(String str, String str2, String str3, zzdae zzdae) {
        if (zzbji()) {
            try {
                this.zzkzt.zza(str, str2, str3, zzdae);
                return;
            } catch (RemoteException e) {
                zzdal.zzc("Error calling service to load container", e);
            }
        }
        if (zzdae != null) {
            try {
                zzdae.zza(false, str);
            } catch (RemoteException e2) {
                zzdal.zzb("Error - local callback should not throw RemoteException", e2);
            }
        }
    }

    public final boolean zzbjj() {
        if (!zzbji()) {
            return false;
        }
        try {
            this.zzkzt.zzbiv();
            return true;
        } catch (RemoteException e) {
            zzdal.zzc("Error in resetting service", e);
            return false;
        }
    }
}
