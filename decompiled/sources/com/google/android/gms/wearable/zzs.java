package com.google.android.gms.wearable;

import com.google.android.gms.wearable.WearableListenerService;
import com.google.android.gms.wearable.internal.zzi;

final class zzs implements Runnable {
    private /* synthetic */ WearableListenerService.zzd zzlrr;
    private /* synthetic */ zzi zzlrx;

    zzs(WearableListenerService.zzd zzd, zzi zzi) {
        this.zzlrr = zzd;
        this.zzlrx = zzi;
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.google.android.gms.wearable.internal.zzi, com.google.android.gms.wearable.zzb] */
    public final void run() {
        WearableListenerService service = this.zzlrr.zzbly();
        service.onEntityUpdate(this.zzlrx);
    }
}
