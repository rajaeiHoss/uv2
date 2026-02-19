package com.google.android.gms.wearable;

import com.google.android.gms.wearable.WearableListenerService;
import java.util.List;

final class zzp implements Runnable {
    private /* synthetic */ WearableListenerService.zzd zzlrr;
    private /* synthetic */ List zzlru;

    zzp(WearableListenerService.zzd zzd, List list) {
        this.zzlrr = zzd;
        this.zzlru = list;
    }

    public final void run() {
        WearableListenerService service = this.zzlrr.zzbly();
        service.onConnectedNodes(this.zzlru);
    }
}
