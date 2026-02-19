package com.google.android.gms.nearby.messages.internal;

import com.google.android.gms.internal.zzcvf;
import com.google.android.gms.nearby.messages.SubscribeCallback;

final class zzbx extends zzcvf<SubscribeCallback> {
    zzbx() {
    }

    public final void zzu(SubscribeCallback subscribeCallback) {
        subscribeCallback.onExpired();
    }
}
