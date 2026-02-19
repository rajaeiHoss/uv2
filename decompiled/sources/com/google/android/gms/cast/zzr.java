package com.google.android.gms.cast;

import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.os.RemoteException;
import android.view.Display;
import android.view.Surface;
import com.google.android.gms.cast.CastRemoteDisplayClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzdf;
import com.google.android.gms.internal.zzbez;
import com.google.android.gms.internal.zzbfe;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzr extends CastRemoteDisplayClient.zza {
    private /* synthetic */ TaskCompletionSource zzeuo;
    private /* synthetic */ zzbez zzeup;
    private /* synthetic */ zzq zzeuq;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzr(zzq zzq, TaskCompletionSource taskCompletionSource, zzbez zzbez) {
        super((zzp) null);
        this.zzeuq = zzq;
        this.zzeuo = taskCompletionSource;
        this.zzeup = zzbez;
    }

    public final void onError(int i) throws RemoteException {
        this.zzeuq.zzeun.zzeui.zzb("onError: %d", Integer.valueOf(i));
        this.zzeuq.zzeun.zzadn();
        zzdf.zza(Status.zzfts, null, this.zzeuo);
    }

    public final void zza(int i, int i2, Surface surface) throws RemoteException {
        this.zzeuq.zzeun.zzeui.zzb("onConnected", new Object[0]);
        DisplayManager displayManager = (DisplayManager) this.zzeuq.zzeun.getApplicationContext().getSystemService("display");
        if (displayManager == null) {
            this.zzeuq.zzeun.zzeui.zzc("Unable to get the display manager", new Object[0]);
        } else {
            this.zzeuq.zzeun.zzadn();
            VirtualDisplay unused = this.zzeuq.zzeun.zzeuj = displayManager.createVirtualDisplay("private_display", i, i2, CastRemoteDisplayClient.zzk(i, i2), surface, 2);
            if (this.zzeuq.zzeun.zzeuj == null) {
                this.zzeuq.zzeun.zzeui.zzc("Unable to create virtual display", new Object[0]);
            } else {
                Display display = this.zzeuq.zzeun.zzeuj.getDisplay();
                if (display == null) {
                    this.zzeuq.zzeun.zzeui.zzc("Virtual display does not have a display", new Object[0]);
                } else {
                    try {
                        ((zzbfe) this.zzeup.zzalw()).zza(this, display.getDisplayId());
                        return;
                    } catch (RemoteException | IllegalStateException unused2) {
                        this.zzeuq.zzeun.zzeui.zzc("Unable to provision the route's new virtual Display", new Object[0]);
                    }
                }
            }
        }
        zzdf.zza(Status.zzfts, null, this.zzeuo);
    }

    public final void zzado() {
        this.zzeuq.zzeun.zzeui.zzb("onConnectedWithDisplay", new Object[0]);
        if (this.zzeuq.zzeun.zzeuj == null) {
            this.zzeuq.zzeun.zzeui.zzc("There is no virtual display", new Object[0]);
            zzdf.zza(Status.zzfts, null, this.zzeuo);
            return;
        }
        Display display = this.zzeuq.zzeun.zzeuj.getDisplay();
        if (display != null) {
            zzdf.zza(Status.zzftq, display, this.zzeuo);
            return;
        }
        this.zzeuq.zzeun.zzeui.zzc("Virtual display no longer has a display", new Object[0]);
        zzdf.zza(Status.zzfts, null, this.zzeuo);
    }
}
