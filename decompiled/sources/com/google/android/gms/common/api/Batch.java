package com.google.android.gms.common.api;

import com.google.android.gms.common.api.internal.BasePendingResult;
import java.util.ArrayList;
import java.util.List;

public final class Batch extends BasePendingResult<BatchResult> {
    /* access modifiers changed from: private */
    public final Object mLock;
    /* access modifiers changed from: private */
    public int zzfsf;
    /* access modifiers changed from: private */
    public boolean zzfsg;
    /* access modifiers changed from: private */
    public boolean zzfsh;
    /* access modifiers changed from: private */
    public final PendingResult<?>[] zzfsi;

    public static final class Builder {
        private GoogleApiClient zzfap;
        private List<PendingResult<?>> zzfsk = new ArrayList();

        public Builder(GoogleApiClient googleApiClient) {
            this.zzfap = googleApiClient;
        }

        public final <R extends Result> BatchResultToken<R> add(PendingResult<R> pendingResult) {
            BatchResultToken<R> batchResultToken = new BatchResultToken<>(this.zzfsk.size());
            this.zzfsk.add(pendingResult);
            return batchResultToken;
        }

        public final Batch build() {
            return new Batch(this.zzfsk, this.zzfap, (zza) null);
        }
    }

    private Batch(List<PendingResult<?>> list, GoogleApiClient googleApiClient) {
        super(googleApiClient);
        this.mLock = new Object();
        int size = list.size();
        this.zzfsf = size;
        PendingResult<?>[] pendingResultArr = new PendingResult[size];
        this.zzfsi = pendingResultArr;
        if (list.isEmpty()) {
            setResult(new BatchResult(Status.zzftq, pendingResultArr));
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            PendingResult<?> pendingResult = list.get(i);
            this.zzfsi[i] = pendingResult;
            pendingResult.zza(new com.google.android.gms.common.api.zza(this));
        }
    }

    /* synthetic */ Batch(List list, GoogleApiClient googleApiClient, zza zza) {
        this(list, googleApiClient);
    }

    static /* synthetic */ int zzb(Batch batch) {
        int i = batch.zzfsf;
        batch.zzfsf = i - 1;
        return i;
    }

    public final void cancel() {
        super.cancel();
        for (PendingResult<?> cancel : this.zzfsi) {
            cancel.cancel();
        }
    }

    /* renamed from: createFailedResult */
    public final BatchResult zzb(Status status) {
        return new BatchResult(status, this.zzfsi);
    }
}
