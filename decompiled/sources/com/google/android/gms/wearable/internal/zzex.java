package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.wearable.MessageApi;

final class zzex extends zzn<Status> {
    private zzci<MessageApi.MessageListener> zzgbb;
    private MessageApi.MessageListener zzluw;
    private IntentFilter[] zzlux;

    private zzex(GoogleApiClient googleApiClient, MessageApi.MessageListener messageListener, zzci<MessageApi.MessageListener> zzci, IntentFilter[] intentFilterArr) {
        super(googleApiClient);
        this.zzluw = (MessageApi.MessageListener) zzbq.checkNotNull(messageListener);
        this.zzgbb = (zzci) zzbq.checkNotNull(zzci);
        this.zzlux = (IntentFilter[]) zzbq.checkNotNull(intentFilterArr);
    }

    /* synthetic */ zzex(GoogleApiClient googleApiClient, MessageApi.MessageListener messageListener, zzci zzci, IntentFilter[] intentFilterArr, zzev zzev) {
        this(googleApiClient, messageListener, zzci, intentFilterArr);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void zza(zzhg zzhg) throws RemoteException {
        zzhg.zza((com.google.android.gms.common.api.internal.zzn<Status>) this, this.zzluw, this.zzgbb, this.zzlux);
        this.zzluw = null;
        this.zzgbb = null;
        this.zzlux = null;
    }

    public final /* synthetic */ Status zzb(Status status) {
        this.zzluw = null;
        this.zzgbb = null;
        this.zzlux = null;
        return status;
    }
}
