package com.google.android.gms.measurement;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.legacy.content.WakefulBroadcastReceiver;
import com.google.android.gms.internal.zzcka;
import com.google.android.gms.internal.zzckc;

public final class AppMeasurementReceiver extends WakefulBroadcastReceiver implements zzckc {
    private zzcka zzjfc;

    public final BroadcastReceiver.PendingResult doGoAsync() {
        return goAsync();
    }

    public final void doStartService(Context context, Intent intent) {
        startWakefulService(context, intent);
    }

    public final void onReceive(Context context, Intent intent) {
        if (this.zzjfc == null) {
            this.zzjfc = new zzcka(this);
        }
        this.zzjfc.onReceive(context, intent);
    }
}
