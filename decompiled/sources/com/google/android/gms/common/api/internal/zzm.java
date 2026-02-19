package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.internal.zzbz;

public abstract class zzm<R extends Result, A extends Api.zzb> extends BasePendingResult<R> implements zzn<R> {
    private final Api<?> zzfop;
    private final Api.zzc<A> zzfus;

    @Deprecated
    protected zzm(Api.zzc<A> zzc, GoogleApiClient googleApiClient) {
        super((GoogleApiClient) zzbq.checkNotNull(googleApiClient, "GoogleApiClient must not be null"));
        this.zzfus = (Api.zzc) zzbq.checkNotNull(zzc);
        this.zzfop = null;
    }

    protected zzm(Api<?> api, GoogleApiClient googleApiClient) {
        super((GoogleApiClient) zzbq.checkNotNull(googleApiClient, "GoogleApiClient must not be null"));
        zzbq.checkNotNull(api, "Api must not be null");
        this.zzfus = (Api.zzc<A>) api.zzahm();
        this.zzfop = api;
    }

    private final void zzc(RemoteException remoteException) {
        zzu(new Status(8, remoteException.getLocalizedMessage(), (PendingIntent) null));
    }

    /* access modifiers changed from: protected */
    public abstract void zza(A a) throws RemoteException;

    public final Api.zzc<A> zzahm() {
        return this.zzfus;
    }

    public final Api<?> zzaht() {
        return this.zzfop;
    }

    public final void zzb(A a) throws DeadObjectException {
        if (a instanceof zzbz) {
            a = (A) zzbz.zzanb();
        }
        try {
            zza(a);
        } catch (DeadObjectException e) {
            zzc(e);
            throw e;
        } catch (RemoteException e2) {
            zzc(e2);
        }
    }

    public final void zzu(Status status) {
        zzbq.checkArgument(!status.isSuccess(), "Failed result must not be success");
        setResult(zzb(status));
    }
}
