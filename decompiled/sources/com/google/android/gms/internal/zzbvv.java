package com.google.android.gms.internal;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import com.google.android.gms.fido.u2f.U2fPendingIntent;

public final class zzbvv implements U2fPendingIntent {
    private final PendingIntent zzekd;

    public zzbvv(PendingIntent pendingIntent) {
        this.zzekd = pendingIntent;
    }

    public final boolean hasPendingIntent() {
        return this.zzekd != null;
    }

    public final void launchPendingIntent(Activity activity, int i) throws IntentSender.SendIntentException {
        if (hasPendingIntent()) {
            activity.startIntentSenderForResult(this.zzekd.getIntentSender(), i, (Intent) null, 0, 0, 0);
            return;
        }
        throw new IllegalStateException("No PendingIntent available");
    }
}
