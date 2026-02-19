package com.google.android.gms.internal;

import android.os.Bundle;
import android.support.v4.media.session.MediaSessionCompat;
import androidx.mediarouter.media.MediaRouteSelector;
import androidx.mediarouter.media.MediaRouter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class zzbaw extends zzbak {
    private final MediaRouter zzeve;
    private final Map<MediaRouteSelector, Set<MediaRouter.Callback>> zzfcb = new HashMap();

    public zzbaw(MediaRouter mediaRouter) {
        this.zzeve = mediaRouter;
    }

    public final void setMediaSessionCompat(MediaSessionCompat mediaSessionCompat) {
        this.zzeve.setMediaSessionCompat(mediaSessionCompat);
    }

    public final void zza(Bundle bundle, int i) {
        MediaRouteSelector fromBundle = MediaRouteSelector.fromBundle(bundle);
        for (MediaRouter.Callback addCallback : this.zzfcb.get(fromBundle)) {
            this.zzeve.addCallback(fromBundle, addCallback, i);
        }
    }

    public final void zza(Bundle bundle, zzbal zzbal) {
        MediaRouteSelector fromBundle = MediaRouteSelector.fromBundle(bundle);
        if (!this.zzfcb.containsKey(fromBundle)) {
            this.zzfcb.put(fromBundle, new HashSet());
        }
        this.zzfcb.get(fromBundle).add(new zzbav(zzbal));
    }

    public final int zzadx() {
        return 12211278;
    }

    public final void zzaeu() {
        MediaRouter mediaRouter = this.zzeve;
        mediaRouter.selectRoute(mediaRouter.getDefaultRoute());
    }

    public final boolean zzaev() {
        return this.zzeve.getSelectedRoute().getId().equals(this.zzeve.getDefaultRoute().getId());
    }

    public final String zzaew() {
        return this.zzeve.getSelectedRoute().getId();
    }

    public final void zzaex() {
        for (Set<MediaRouter.Callback> it : this.zzfcb.values()) {
            for (MediaRouter.Callback removeCallback : it) {
                this.zzeve.removeCallback(removeCallback);
            }
        }
        this.zzfcb.clear();
    }

    public final boolean zzb(Bundle bundle, int i) {
        return this.zzeve.isRouteAvailable(MediaRouteSelector.fromBundle(bundle), i);
    }

    public final void zzfr(String str) {
        for (MediaRouter.RouteInfo next : this.zzeve.getRoutes()) {
            if (next.getId().equals(str)) {
                this.zzeve.selectRoute(next);
                return;
            }
        }
    }

    public final Bundle zzfs(String str) {
        for (MediaRouter.RouteInfo next : this.zzeve.getRoutes()) {
            if (next.getId().equals(str)) {
                return next.getExtras();
            }
        }
        return null;
    }

    public final void zzi(Bundle bundle) {
        for (MediaRouter.Callback removeCallback : this.zzfcb.get(MediaRouteSelector.fromBundle(bundle))) {
            this.zzeve.removeCallback(removeCallback);
        }
    }
}
