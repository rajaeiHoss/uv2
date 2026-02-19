package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.internal.zzcl;
import com.google.android.gms.wearable.MessageApi;

final class zzhm implements zzcl<MessageApi.MessageListener> {
    private /* synthetic */ zzfe zzlrs;

    zzhm(zzfe zzfe) {
        this.zzlrs = zzfe;
    }

    public final void zzajh() {
    }

    public final void zzu(MessageApi.MessageListener messageListener) {
        messageListener.onMessageReceived(this.zzlrs);
    }
}
