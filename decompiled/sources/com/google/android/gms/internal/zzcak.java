package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.request.GoalsReadRequest;
import com.google.android.gms.fitness.result.GoalsResult;
import java.util.Collections;

final class zzcak extends zzbxh<GoalsResult> {
    private /* synthetic */ GoalsReadRequest zzhmn;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcak(zzcaj zzcaj, GoogleApiClient googleApiClient, GoalsReadRequest goalsReadRequest) {
        super(googleApiClient);
        this.zzhmn = goalsReadRequest;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbxe zzbxe) throws RemoteException {
        ((zzbza) zzbxe.zzalw()).zza(new GoalsReadRequest(this.zzhmn, (zzbyt) new zzcal(this)));
    }

    /* access modifiers changed from: protected */
    public final GoalsResult zzb(Status status) {
        return new GoalsResult(status, Collections.emptyList());
    }
}
