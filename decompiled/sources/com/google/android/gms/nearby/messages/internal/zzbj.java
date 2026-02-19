package com.google.android.gms.nearby.messages.internal;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.nearby.messages.MessagesOptions;

final class zzbj extends Api.zza<zzah, MessagesOptions> {
    zzbj() {
    }

    public final int getPriority() {
        return Integer.MAX_VALUE;
    }

    public final zzah zza(Context context, Looper looper, zzr zzr, MessagesOptions messagesOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        return new zzah(context, looper, connectionCallbacks, onConnectionFailedListener, zzr, messagesOptions);
    }
}
