package com.google.android.gms.nearby.messages.internal;

import com.google.android.gms.internal.zzcvf;
import com.google.android.gms.nearby.messages.PublishCallback;

final class zzbu extends zzcvf<PublishCallback> {
    zzbu() {
    }

    public final void zzu(PublishCallback publishCallback) {
        publishCallback.onExpired();
    }
}
