package com.google.android.gms.wearable;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.wearable.WearableListenerService;

final class zzl implements Runnable {
    private /* synthetic */ DataHolder zzlrq;
    private /* synthetic */ WearableListenerService.zzd zzlrr;

    zzl(WearableListenerService.zzd zzd, DataHolder dataHolder) {
        this.zzlrr = zzd;
        this.zzlrq = dataHolder;
    }

    public final void run() {
        DataEventBuffer dataEventBuffer = new DataEventBuffer(this.zzlrq);
        try {
            WearableListenerService service = this.zzlrr.zzbly();
            service.onDataChanged(dataEventBuffer);
        } finally {
            dataEventBuffer.release();
        }
    }
}
