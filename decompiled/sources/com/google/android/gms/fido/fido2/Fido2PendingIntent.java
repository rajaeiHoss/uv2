package com.google.android.gms.fido.fido2;

import android.app.Activity;
import android.content.IntentSender;

public interface Fido2PendingIntent {
    boolean hasPendingIntent();

    void launchPendingIntent(Activity activity, int i) throws IntentSender.SendIntentException;
}
