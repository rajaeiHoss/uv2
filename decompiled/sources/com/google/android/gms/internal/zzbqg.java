package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.internal.zzb;
import com.google.android.gms.drive.events.ListenerToken;
import com.google.android.gms.drive.events.OpenFileCallback;

final class zzbqg extends zzbma {
    private /* synthetic */ zzboz zzgvo;
    private final ListenerToken zzgwe;
    private final zzci<OpenFileCallback> zzgwf;

    zzbqg(zzboz zzboz, ListenerToken listenerToken, zzci<OpenFileCallback> zzci) {
        this.zzgvo = zzboz;
        this.zzgwe = listenerToken;
        this.zzgwf = zzci;
    }

    private final void zza(zzbqc<OpenFileCallback> zzbqc) {
        this.zzgwf.zza(new zzbqk(this, zzbqc));
    }

    public final void onError(Status status) throws RemoteException {
        zza((zzbqc<OpenFileCallback>) new zzbqh(this, status));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(Status status, OpenFileCallback openFileCallback) {
        openFileCallback.onError(zzb.zzy(status));
        this.zzgvo.cancelOpenFileCallback(this.zzgwe);
    }

    public final void zza(zzbrx zzbrx) throws RemoteException {
        zza((zzbqc<OpenFileCallback>) new zzbqj(this, zzbrx));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(zzbrx zzbrx, OpenFileCallback openFileCallback) {
        openFileCallback.onContents(new zzboa(zzbrx.zzgul));
        this.zzgvo.cancelOpenFileCallback(this.zzgwe);
    }

    public final void zza(zzbsb zzbsb) throws RemoteException {
        zza((zzbqc<OpenFileCallback>) new zzbqi(zzbsb));
    }
}
