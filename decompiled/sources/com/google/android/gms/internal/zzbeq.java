package com.google.android.gms.internal;

import android.hardware.display.VirtualDisplay;
import com.google.android.gms.cast.CastRemoteDisplay;
import com.google.android.gms.cast.CastRemoteDisplayApi;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;

@Deprecated
public final class zzbeq implements CastRemoteDisplayApi {
    /* access modifiers changed from: private */
    public static final zzbei zzeus = new zzbei("CastRemoteDisplayApiImpl");
    /* access modifiers changed from: private */
    public VirtualDisplay zzeuj;
    /* access modifiers changed from: private */
    public Api<?> zzfop;
    /* access modifiers changed from: private */
    public final zzbfg zzfoq = new zzber(this);

    public zzbeq(Api api) {
        this.zzfop = api;
    }

    /* access modifiers changed from: private */
    public final void zzadn() {
        VirtualDisplay virtualDisplay = this.zzeuj;
        if (virtualDisplay != null) {
            if (virtualDisplay.getDisplay() != null) {
                zzbei zzbei = zzeus;
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

    public final PendingResult<CastRemoteDisplay.CastRemoteDisplaySessionResult> startRemoteDisplay(GoogleApiClient googleApiClient, String str) {
        zzeus.zzb("startRemoteDisplay", new Object[0]);
        return googleApiClient.zze(new zzbes(this, googleApiClient, str));
    }

    public final PendingResult<CastRemoteDisplay.CastRemoteDisplaySessionResult> stopRemoteDisplay(GoogleApiClient googleApiClient) {
        zzeus.zzb("stopRemoteDisplay", new Object[0]);
        return googleApiClient.zze(new zzbet(this, googleApiClient));
    }
}
