package com.google.android.gms.fido.u2f;

import android.app.Activity;
import android.content.IntentSender;

public interface U2fPendingIntent {
    boolean hasPendingIntent();

    void launchPendingIntent(Activity activity, int i) throws IntentSender.SendIntentException;
}
