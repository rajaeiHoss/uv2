package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.awareness.fence.FenceQueryResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.common.data.DataHolder;

public final class zzblj extends zzblo {
    private final zzblm zzgon;
    private zzn<Status> zzgoo;
    private zzn<Object> zzgop = null;
    private zzn<Object> zzgoq = null;
    private zzn<Object> zzgor = null;
    private zzn<zzazy> zzgos;
    private zzn<FenceQueryResult> zzgot;
    private zzn<Object> zzgou;

    private zzblj(zzn<Status> zzn, zzn<Object> zzn2, zzn<Object> zzn3, zzn<Object> zzn4, zzn<zzazy> zzn5, zzn<FenceQueryResult> zzn6, zzn<Object> zzn7, zzblm zzblm) {
        this.zzgoo = zzn;
        this.zzgos = zzn5;
        this.zzgot = zzn6;
        this.zzgou = null;
        this.zzgon = null;
    }

    public static zzblj zza(zzn<Status> zzn, zzblm zzblm) {
        return new zzblj(zzn, (zzn<Object>) null, (zzn<Object>) null, (zzn<Object>) null, (zzn<zzazy>) null, (zzn<FenceQueryResult>) null, (zzn<Object>) null, (zzblm) null);
    }

    private final void zzab(Status status) {
        zzblm zzblm = this.zzgon;
        if (zzblm != null) {
            zzblm.zzac(status);
        }
    }

    public static zzblj zzd(zzn<zzazy> zzn) {
        return new zzblj((zzn<Status>) null, (zzn<Object>) null, (zzn<Object>) null, (zzn<Object>) null, zzn, (zzn<FenceQueryResult>) null, (zzn<Object>) null, (zzblm) null);
    }

    public static zzblj zze(zzn<FenceQueryResult> zzn) {
        return new zzblj((zzn<Status>) null, (zzn<Object>) null, (zzn<Object>) null, (zzn<Object>) null, (zzn<zzazy>) null, zzn, (zzn<Object>) null, (zzblm) null);
    }

    public final void zza(Status status, DataHolder dataHolder) throws RemoteException {
        zzfi.zzb("ContextManagerPendingResult", "Unexpected callback to onStateResult");
    }

    public final void zza(Status status, DataHolder dataHolder, DataHolder dataHolder2) throws RemoteException {
        zzfi.zzb("ContextManagerPendingResult", "Unexpected callback to onReadResult.");
    }

    public final void zza(Status status, zzazd zzazd) throws RemoteException {
        zzn<zzazy> zzn = this.zzgos;
        if (zzn == null) {
            zzfi.zzb("ContextManagerPendingResult", "Unexpected callback to onSnapshotResult");
            return;
        }
        zzn.setResult(new zzblk(this, status, zzazd));
        this.zzgos = null;
        zzab(status);
    }

    public final void zza(Status status, zzbkj zzbkj) {
        zzfi.zzb("ContextManagerPendingResult", "Unexpected callback to onFenceEvaluateResult");
    }

    public final void zza(Status status, zzbkl zzbkl) {
        zzn<FenceQueryResult> zzn = this.zzgot;
        if (zzn == null) {
            zzfi.zzb("ContextManagerPendingResult", "Unexpected callback to onFenceQueryResult");
            return;
        }
        zzn.setResult(new zzbll(this, zzbkl, status));
        this.zzgot = null;
        zzab(status);
    }

    public final void zza(Status status, zzblr zzblr) throws RemoteException {
        zzfi.zzb("ContextManagerPendingResult", "Unexpected callback to onWriteBatchResult");
    }

    public final void zze(Status status) throws RemoteException {
        zzn<Status> zzn = this.zzgoo;
        if (zzn == null) {
            zzfi.zzb("ContextManagerPendingResult", "Unexpected callback to onStatusResult.");
            return;
        }
        zzn.setResult(status);
        this.zzgoo = null;
        zzab(status);
    }
}
