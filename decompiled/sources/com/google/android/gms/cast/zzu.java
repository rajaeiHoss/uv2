package com.google.android.gms.cast;

import androidx.mediarouter.media.MediaRouter;

final class zzu extends MediaRouter.Callback {
    private /* synthetic */ CastRemoteDisplayLocalService zzevk;

    zzu(CastRemoteDisplayLocalService castRemoteDisplayLocalService) {
        this.zzevk = castRemoteDisplayLocalService;
    }

    public final void onRouteUnselected(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
        CastRemoteDisplayLocalService castRemoteDisplayLocalService;
        String str;
        this.zzevk.zzeb("onRouteUnselected");
        if (this.zzevk.zzevb == null) {
            castRemoteDisplayLocalService = this.zzevk;
            str = "onRouteUnselected, no device was selected";
        } else if (!CastDevice.getFromBundle(routeInfo.getExtras()).getDeviceId().equals(this.zzevk.zzevb.getDeviceId())) {
            castRemoteDisplayLocalService = this.zzevk;
            str = "onRouteUnselected, device does not match";
        } else {
            CastRemoteDisplayLocalService.stopService();
            return;
        }
        castRemoteDisplayLocalService.zzeb(str);
    }
}
