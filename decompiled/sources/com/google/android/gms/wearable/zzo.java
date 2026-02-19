package com.google.android.gms.wearable;

import com.google.android.gms.wearable.WearableListenerService;
import com.google.android.gms.wearable.internal.zzfo;

final class zzo implements Runnable {
    private /* synthetic */ WearableListenerService.zzd zzlrr;
    private /* synthetic */ zzfo zzlrt;

    zzo(WearableListenerService.zzd zzd, zzfo zzfo) {
        this.zzlrr = zzd;
        this.zzlrt = zzfo;
    }

    public final void run() {
        WearableListenerService service = this.zzlrr.zzbly();
        service.onPeerDisconnected(this.zzlrt);
    }
}
