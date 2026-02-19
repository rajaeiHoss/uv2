package com.google.android.gms.wearable;

import com.google.android.gms.wearable.WearableListenerService;
import com.google.android.gms.wearable.internal.zzl;

final class zzr implements Runnable {
    private /* synthetic */ WearableListenerService.zzd zzlrr;
    private /* synthetic */ zzl zzlrw;

    zzr(WearableListenerService.zzd zzd, zzl zzl) {
        this.zzlrr = zzd;
        this.zzlrw = zzl;
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.google.android.gms.wearable.internal.zzl, com.google.android.gms.wearable.zzd] */
    public final void run() {
        WearableListenerService service = this.zzlrr.zzbly();
        service.onNotificationReceived(this.zzlrw);
    }
}
