package com.google.android.gms.cast.framework;

import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.zzbl;
import com.google.android.gms.internal.zzbdj;
import com.google.android.gms.internal.zzbei;
import java.util.List;

public class PrecacheManager {
    private final zzbei zzeui = new zzbei("PrecacheManager");
    private final SessionManager zzezv;
    private final CastOptions zzezz;
    private final zzbdj zzfbe;

    public PrecacheManager(CastOptions castOptions, SessionManager sessionManager, zzbdj zzbdj) {
        this.zzezz = castOptions;
        this.zzezv = sessionManager;
        this.zzfbe = zzbdj;
    }

    public void precache(String str) {
        Session currentSession = this.zzezv.getCurrentSession();
        if (str == null) {
            throw new IllegalArgumentException("No precache data found to be precached");
        } else if (currentSession == null) {
            this.zzfbe.zza(new String[]{this.zzezz.getReceiverApplicationId()}, str, (List<zzbl>) null);
        } else if (currentSession instanceof CastSession) {
            RemoteMediaClient remoteMediaClient = ((CastSession) currentSession).getRemoteMediaClient();
            if (remoteMediaClient != null) {
                remoteMediaClient.zza(str, (List<zzbl>) null);
            } else {
                this.zzeui.zzc("Failed to get RemoteMediaClient from current cast session.", new Object[0]);
            }
        } else {
            this.zzeui.zzc("Current session is not a CastSession. Precache is not supported.", new Object[0]);
        }
    }
}
