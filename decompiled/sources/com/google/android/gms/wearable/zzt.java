package com.google.android.gms.wearable;

import com.google.android.gms.wearable.WearableListenerService;
import com.google.android.gms.wearable.internal.zzaw;

final class zzt implements Runnable {
    private /* synthetic */ WearableListenerService.zzd zzlrr;
    private /* synthetic */ zzaw zzlry;

    zzt(WearableListenerService.zzd zzd, zzaw zzaw) {
        this.zzlrr = zzd;
        this.zzlry = zzaw;
    }

    public final void run() {
        WearableListenerService service = this.zzlrr.zzbly();
        this.zzlry.zza(service);
        this.zzlry.zza(this.zzlrr.zzblz());
    }
}
