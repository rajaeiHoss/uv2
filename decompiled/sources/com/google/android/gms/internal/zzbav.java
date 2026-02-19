package com.google.android.gms.internal;

import android.os.RemoteException;
import androidx.mediarouter.media.MediaRouter;
import com.google.android.gms.common.internal.zzbq;

public final class zzbav extends MediaRouter.Callback {
    private static final zzbei zzeui = new zzbei("MediaRouterCallback");
    private final zzbal zzfca;

    public zzbav(zzbal zzbal) {
        this.zzfca = (zzbal) zzbq.checkNotNull(zzbal);
    }

    public final void onRouteAdded(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
        try {
            this.zzfca.zzc(routeInfo.getId(), routeInfo.getExtras());
        } catch (RemoteException e) {
            zzeui.zzb(e, "Unable to call %s on %s.", "onRouteAdded", zzbal.class.getSimpleName());
        }
    }

    public final void onRouteChanged(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
        try {
            this.zzfca.zzd(routeInfo.getId(), routeInfo.getExtras());
        } catch (RemoteException e) {
            zzeui.zzb(e, "Unable to call %s on %s.", "onRouteChanged", zzbal.class.getSimpleName());
        }
    }

    public final void onRouteRemoved(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
        try {
            this.zzfca.zze(routeInfo.getId(), routeInfo.getExtras());
        } catch (RemoteException e) {
            zzeui.zzb(e, "Unable to call %s on %s.", "onRouteRemoved", zzbal.class.getSimpleName());
        }
    }

    public final void onRouteSelected(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
        try {
            this.zzfca.zzf(routeInfo.getId(), routeInfo.getExtras());
        } catch (RemoteException e) {
            zzeui.zzb(e, "Unable to call %s on %s.", "onRouteSelected", zzbal.class.getSimpleName());
        }
    }

    public final void onRouteUnselected(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo, int i) {
        try {
            this.zzfca.zza(routeInfo.getId(), routeInfo.getExtras(), i);
        } catch (RemoteException e) {
            zzeui.zzb(e, "Unable to call %s on %s.", "onRouteUnselected", zzbal.class.getSimpleName());
        }
    }
}
