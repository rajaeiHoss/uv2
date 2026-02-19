package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.zzc;
import java.util.Set;

public abstract class zzab<T extends IInterface> extends zzd<T> implements Api.zze, zzaf {
    private final Account zzeho;
    private final Set<Scope> zzenh;
    private final zzr zzfwf;

    protected zzab(Context context, Looper looper, int i, zzr zzr, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        this(context, looper, zzag.zzcp(context), GoogleApiAvailability.getInstance(), i, zzr, (GoogleApiClient.ConnectionCallbacks) zzbq.checkNotNull(connectionCallbacks), (GoogleApiClient.OnConnectionFailedListener) zzbq.checkNotNull(onConnectionFailedListener));
    }

    private zzab(Context context, Looper looper, zzag zzag, GoogleApiAvailability googleApiAvailability, int i, zzr zzr, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, zzag, googleApiAvailability, i, connectionCallbacks == null ? null : new zzac(connectionCallbacks), onConnectionFailedListener == null ? null : new zzad(onConnectionFailedListener), zzr.zzamj());
        this.zzfwf = zzr;
        this.zzeho = zzr.getAccount();
        Set<Scope> zzamg = zzr.zzamg();
        Set<Scope> zzb = zzb(zzamg);
        for (Scope scope : zzb) {
            if (!zzamg.contains(scope)) {
                throw new IllegalStateException("Expanding scopes is not permitted, use implied scopes instead");
            }
        }
        this.zzenh = zzb;
    }

    public final Account getAccount() {
        return this.zzeho;
    }

    public final int zzahq() {
        return -1;
    }

    public zzc[] zzalu() {
        return new zzc[0];
    }

    /* access modifiers changed from: protected */
    public final Set<Scope> zzaly() {
        return this.zzenh;
    }

    /* access modifiers changed from: protected */
    public final zzr zzamr() {
        return this.zzfwf;
    }

    /* access modifiers changed from: protected */
    public Set<Scope> zzb(Set<Scope> set) {
        return set;
    }
}
