package com.google.android.gms.internal;

import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.os.RemoteException;
import android.view.Display;
import android.view.Surface;
import com.google.android.gms.common.api.Status;

public final class zzbew extends zzbeu {
    private final zzbfa zzfot;
    private /* synthetic */ zzbev zzfou;

    public zzbew(zzbev zzbev, zzbfa zzbfa) {
        this.zzfou = zzbev;
        this.zzfot = zzbfa;
    }

    public final void onError(int i) throws RemoteException {
        zzbeq.zzeus.zzb("onError: %d", Integer.valueOf(i));
        this.zzfou.zzfor.zzadn();
        this.zzfou.setResult(new zzbey(Status.zzfts));
    }

    public final void zza(int i, int i2, Surface surface) {
        zzbev zzbev;
        zzbey zzbey;
        zzbeq.zzeus.zzb("onConnected", new Object[0]);
        DisplayManager displayManager = (DisplayManager) this.zzfot.getContext().getSystemService("display");
        if (displayManager == null) {
            zzbeq.zzeus.zzc("Unable to get the display manager", new Object[0]);
            zzbev = this.zzfou;
            zzbey = new zzbey(Status.zzfts);
        } else {
            this.zzfou.zzfor.zzadn();
            VirtualDisplay unused = this.zzfou.zzfor.zzeuj = displayManager.createVirtualDisplay("private_display", i, i2, ((i < i2 ? i : i2) * 320) / 1080, surface, 2);
            if (this.zzfou.zzfor.zzeuj == null) {
                zzbeq.zzeus.zzc("Unable to create virtual display", new Object[0]);
                zzbev = this.zzfou;
                zzbey = new zzbey(Status.zzfts);
            } else if (this.zzfou.zzfor.zzeuj.getDisplay() == null) {
                zzbeq.zzeus.zzc("Virtual display does not have a display", new Object[0]);
                zzbev = this.zzfou;
                zzbey = new zzbey(Status.zzfts);
            } else {
                try {
                    ((zzbfe) this.zzfot.zzalw()).zza(this, this.zzfou.zzfor.zzeuj.getDisplay().getDisplayId());
                    return;
                } catch (RemoteException | IllegalStateException unused2) {
                    zzbeq.zzeus.zzc("Unable to provision the route's new virtual Display", new Object[0]);
                    zzbev = this.zzfou;
                    zzbey = new zzbey(Status.zzfts);
                }
            }
        }
        zzbev.setResult(zzbey);
    }

    public final void zzado() {
        zzbeq.zzeus.zzb("onConnectedWithDisplay", new Object[0]);
        if (this.zzfou.zzfor.zzeuj == null) {
            zzbeq.zzeus.zzc("There is no virtual display", new Object[0]);
            this.zzfou.setResult(new zzbey(Status.zzfts));
            return;
        }
        Display display = this.zzfou.zzfor.zzeuj.getDisplay();
        if (display != null) {
            this.zzfou.setResult(new zzbey(display));
            return;
        }
        zzbeq.zzeus.zzc("Virtual display no longer has a display", new Object[0]);
        this.zzfou.setResult(new zzbey(Status.zzfts));
    }
}
