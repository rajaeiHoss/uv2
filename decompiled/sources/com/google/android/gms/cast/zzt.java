package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.cast.CastRemoteDisplayClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzdf;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzt extends CastRemoteDisplayClient.zza {
    private /* synthetic */ TaskCompletionSource zzeuo;
    private /* synthetic */ zzs zzeur;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzt(zzs zzs, TaskCompletionSource taskCompletionSource) {
        super((zzp) null);
        this.zzeur = zzs;
        this.zzeuo = taskCompletionSource;
    }

    public final void onDisconnected() throws RemoteException {
        this.zzeur.zzeun.zzeui.zzb("onDisconnected", new Object[0]);
        this.zzeur.zzeun.zzadn();
        zzdf.zza(Status.zzftq, null, this.zzeuo);
    }

    public final void onError(int i) throws RemoteException {
        this.zzeur.zzeun.zzeui.zzb("onError: %d", Integer.valueOf(i));
        this.zzeur.zzeun.zzadn();
        zzdf.zza(Status.zzfts, null, this.zzeuo);
    }
}
