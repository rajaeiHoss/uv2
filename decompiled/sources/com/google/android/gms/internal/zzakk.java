package com.google.android.gms.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import java.util.Map;
import java.util.WeakHashMap;

@zzabh
public final class zzakk {
    private boolean zzaje = false;
    private Context zzajv;
    private final BroadcastReceiver zzdhw = new zzakl(this);
    private final Map<BroadcastReceiver, IntentFilter> zzdhx = new WeakHashMap();
    private boolean zzdhy;

    /* access modifiers changed from: private */
    public final synchronized void zzc(Context context, Intent intent) {
        for (Map.Entry next : this.zzdhx.entrySet()) {
            if (((IntentFilter) next.getValue()).hasAction(intent.getAction())) {
                ((BroadcastReceiver) next.getKey()).onReceive(context, intent);
            }
        }
    }

    public final synchronized void initialize(Context context) {
        if (!this.zzaje) {
            Context applicationContext = context.getApplicationContext();
            this.zzajv = applicationContext;
            if (applicationContext == null) {
                this.zzajv = context;
            }
            zzoi.initialize(this.zzajv);
            this.zzdhy = ((Boolean) zzlc.zzio().zzd(zzoi.zzbsu)).booleanValue();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.SCREEN_ON");
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            intentFilter.addAction("android.intent.action.USER_PRESENT");
            this.zzajv.registerReceiver(this.zzdhw, intentFilter);
            this.zzaje = true;
        }
    }

    public final synchronized void zza(Context context, BroadcastReceiver broadcastReceiver) {
        if (this.zzdhy) {
            this.zzdhx.remove(broadcastReceiver);
        } else {
            context.unregisterReceiver(broadcastReceiver);
        }
    }

    public final synchronized void zza(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        if (this.zzdhy) {
            this.zzdhx.put(broadcastReceiver, intentFilter);
        } else {
            context.registerReceiver(broadcastReceiver, intentFilter);
        }
    }
}
