package com.google.android.gms.wearable.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.internal.zzbq;

final class zzb<T> extends zzn<Status> {
    private T zzgat;
    private zzci<T> zzgbb;
    private zzc<T> zzlrz;

    private zzb(GoogleApiClient googleApiClient, T t, zzci<T> zzci, zzc<T> zzc) {
        super(googleApiClient);
        this.zzgat = zzbq.checkNotNull(t);
        this.zzgbb = (zzci) zzbq.checkNotNull(zzci);
        this.zzlrz = (zzc) zzbq.checkNotNull(zzc);
    }

    static <T> PendingResult<Status> zza(GoogleApiClient googleApiClient, zzc<T> zzc, T t) {
        return googleApiClient.zzd(new zzb(googleApiClient, t, googleApiClient.zzt(t), zzc));
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void zza(zzhg zzhg) throws RemoteException {
        this.zzlrz.zza(zzhg, this, this.zzgat, this.zzgbb);
        this.zzgat = null;
        this.zzgbb = null;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Status zzb(Status status) {
        this.zzgat = null;
        this.zzgbb = null;
        return status;
    }
}
