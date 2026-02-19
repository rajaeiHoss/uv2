package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.api.internal.zzcq;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.MessageClient;

final class zzfc extends zzcq<zzhg, MessageClient.OnMessageReceivedListener> {
    private zzci<MessageClient.OnMessageReceivedListener> zzgwf;
    private IntentFilter[] zzlsb;
    private MessageClient.OnMessageReceivedListener zzluz;

    zzfc(MessageClient.OnMessageReceivedListener onMessageReceivedListener, IntentFilter[] intentFilterArr, zzci<MessageClient.OnMessageReceivedListener> zzci) {
        super(zzci);
        this.zzluz = onMessageReceivedListener;
        this.zzlsb = intentFilterArr;
        this.zzgwf = zzci;
    }

    /* access modifiers changed from: protected */
    public final void zzb(zzhg zzhg, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException {
        zzhg.zza((zzn<Status>) new zzgh(taskCompletionSource), (MessageApi.MessageListener) this.zzluz, (zzci) this.zzgwf, this.zzlsb);
    }
}
