package com.google.android.gms.wearable;

import com.google.android.gms.wearable.WearableListenerService;
import com.google.android.gms.wearable.internal.zzfe;

final class zzm implements Runnable {
    private /* synthetic */ WearableListenerService.zzd zzlrr;
    private /* synthetic */ zzfe zzlrs;

    zzm(WearableListenerService.zzd zzd, zzfe zzfe) {
        this.zzlrr = zzd;
        this.zzlrs = zzfe;
    }

    public final void run() {
        WearableListenerService service = this.zzlrr.zzbly();
        service.onMessageReceived(this.zzlrs);
    }
}
