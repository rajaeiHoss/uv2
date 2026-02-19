package com.google.android.gms.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

final class zzaio extends BroadcastReceiver {
    private /* synthetic */ zzaij zzdfv;

    private zzaio(zzaij zzaij) {
        this.zzdfv = zzaij;
    }

    /* synthetic */ zzaio(zzaij zzaij, zzaik zzaik) {
        this(zzaij);
    }

    public final void onReceive(Context context, Intent intent) {
        if ("android.intent.action.USER_PRESENT".equals(intent.getAction())) {
            boolean unused = this.zzdfv.zzdfo = true;
        } else if ("android.intent.action.SCREEN_OFF".equals(intent.getAction())) {
            boolean unused2 = this.zzdfv.zzdfo = false;
        }
    }
}
