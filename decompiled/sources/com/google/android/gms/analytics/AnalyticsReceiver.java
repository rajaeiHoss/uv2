package com.google.android.gms.analytics;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.internal.zzatk;

public final class AnalyticsReceiver extends BroadcastReceiver {
    private zzatk zzdtz;

    public final void onReceive(Context context, Intent intent) {
        if (this.zzdtz == null) {
            this.zzdtz = new zzatk();
        }
        zzatk.onReceive(context, intent);
    }
}
