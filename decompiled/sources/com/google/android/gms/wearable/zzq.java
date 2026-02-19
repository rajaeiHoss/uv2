package com.google.android.gms.wearable;

import com.google.android.gms.wearable.WearableListenerService;
import com.google.android.gms.wearable.internal.zzah;

final class zzq implements Runnable {
    private /* synthetic */ WearableListenerService.zzd zzlrr;
    private /* synthetic */ zzah zzlrv;

    zzq(WearableListenerService.zzd zzd, zzah zzah) {
        this.zzlrr = zzd;
        this.zzlrv = zzah;
    }

    public final void run() {
        WearableListenerService service = this.zzlrr.zzbly();
        service.onCapabilityChanged(this.zzlrv);
    }
}
