package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.zzde;
import com.google.android.gms.internal.zzbez;
import com.google.android.gms.internal.zzbfe;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzs extends zzde<zzbez, Void> {
    final /* synthetic */ CastRemoteDisplayClient zzeun;

    zzs(CastRemoteDisplayClient castRemoteDisplayClient) {
        this.zzeun = castRemoteDisplayClient;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbez zzbez, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException {
        ((zzbfe) zzbez.zzalw()).zza(new zzt(this, taskCompletionSource));
    }
}
