package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultCallbacks;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.TransformedResult;
import com.google.android.gms.common.internal.zzbq;
import java.lang.ref.WeakReference;

public final class zzdh<R extends Result> extends TransformedResult<R> implements ResultCallback<R> {
    /* access modifiers changed from: private */
    public final Object zzfvc = new Object();
    /* access modifiers changed from: private */
    public final WeakReference<GoogleApiClient> zzfve;
    /* access modifiers changed from: private */
    public ResultTransform<? super R, ? extends Result> zzgbh = null;
    /* access modifiers changed from: private */
    public zzdh<? extends Result> zzgbi = null;
    private volatile ResultCallbacks<? super R> zzgbj = null;
    private PendingResult<R> zzgbk = null;
    private Status zzgbl = null;
    /* access modifiers changed from: private */
    public final zzdj zzgbm;
    private boolean zzgbn = false;

    public zzdh(WeakReference<GoogleApiClient> weakReference) {
        zzbq.checkNotNull(weakReference, "GoogleApiClient reference must not be null");
        this.zzfve = weakReference;
        GoogleApiClient googleApiClient = (GoogleApiClient) weakReference.get();
        this.zzgbm = new zzdj(this, googleApiClient != null ? googleApiClient.getLooper() : Looper.getMainLooper());
    }

    private final void zzala() {
        if (this.zzgbh != null || this.zzgbj != null) {
            GoogleApiClient googleApiClient = (GoogleApiClient) this.zzfve.get();
            if (!(this.zzgbn || this.zzgbh == null || googleApiClient == null)) {
                googleApiClient.zza(this);
                this.zzgbn = true;
            }
            Status status = this.zzgbl;
            if (status != null) {
                zzx(status);
                return;
            }
            PendingResult<R> pendingResult = this.zzgbk;
            if (pendingResult != null) {
                pendingResult.setResultCallback(this);
            }
        }
    }

    private final boolean zzalc() {
        return (this.zzgbj == null || ((GoogleApiClient) this.zzfve.get()) == null) ? false : true;
    }

    /* access modifiers changed from: private */
    public static void zzd(Result result) {
        if (result instanceof Releasable) {
            try {
                ((Releasable) result).release();
            } catch (RuntimeException e) {
                String valueOf = String.valueOf(result);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 18);
                sb.append("Unable to release ");
                sb.append(valueOf);
                Log.w("TransformedResultImpl", sb.toString(), e);
            }
        }
    }

    /* access modifiers changed from: private */
    public final void zzd(Status status) {
        synchronized (this.zzfvc) {
            this.zzgbl = status;
            zzx(status);
        }
    }

    private final void zzx(Status status) {
        synchronized (this.zzfvc) {
            ResultTransform<? super R, ? extends Result> resultTransform = this.zzgbh;
            if (resultTransform != null) {
                Status onFailure = resultTransform.onFailure(status);
                zzbq.checkNotNull(onFailure, "onFailure must not return null");
                this.zzgbi.zzd(onFailure);
            } else if (zzalc()) {
                this.zzgbj.onFailure(status);
            }
        }
    }

    public final void andFinally(ResultCallbacks<? super R> resultCallbacks) {
        synchronized (this.zzfvc) {
            boolean z = true;
            zzbq.zza(this.zzgbj == null, (Object) "Cannot call andFinally() twice.");
            if (this.zzgbh != null) {
                z = false;
            }
            zzbq.zza(z, (Object) "Cannot call then() and andFinally() on the same TransformedResult.");
            this.zzgbj = resultCallbacks;
            zzala();
        }
    }

    public final void onResult(R r) {
        synchronized (this.zzfvc) {
            if (!r.getStatus().isSuccess()) {
                zzd(r.getStatus());
                zzd((Result) r);
            } else if (this.zzgbh != null) {
                zzcs.zzajx().submit(new zzdi(this, r));
            } else if (zzalc()) {
                this.zzgbj.onSuccess(r);
            }
        }
    }

    public final <S extends Result> TransformedResult<S> then(ResultTransform<? super R, ? extends S> resultTransform) {
        zzdh<S> next;
        synchronized (this.zzfvc) {
            boolean z = true;
            zzbq.zza(this.zzgbh == null, (Object) "Cannot call then() twice.");
            if (this.zzgbj != null) {
                z = false;
            }
            zzbq.zza(z, (Object) "Cannot call then() and andFinally() on the same TransformedResult.");
            this.zzgbh = resultTransform;
            next = new zzdh<>(this.zzfve);
            this.zzgbi = next;
            zzala();
        }
        return next;
    }

    public final void zza(PendingResult<?> pendingResult) {
        synchronized (this.zzfvc) {
            this.zzgbk = (PendingResult<R>) pendingResult;
            zzala();
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzalb() {
        this.zzgbj = null;
    }
}
