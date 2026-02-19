package com.google.android.gms.wearable.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzck;
import com.google.android.gms.common.api.internal.zzdo;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.MessageClient;

final class zzfd extends zzdo<zzhg, MessageClient.OnMessageReceivedListener> {
    private final MessageClient.OnMessageReceivedListener zzluz;

    zzfd(MessageClient.OnMessageReceivedListener onMessageReceivedListener, zzck<MessageClient.OnMessageReceivedListener> zzck) {
        super(zzck);
        this.zzluz = onMessageReceivedListener;
    }

    /* access modifiers changed from: protected */
    public final void zzc(zzhg zzhg, TaskCompletionSource<Boolean> taskCompletionSource) throws RemoteException {
        zzhg.zza((zzn<Status>) new zzgg(taskCompletionSource), (MessageApi.MessageListener) this.zzluz);
    }
}
