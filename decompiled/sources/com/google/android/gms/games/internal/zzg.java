package com.google.android.gms.games.internal;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzb;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.games.AnnotatedData;
import com.google.android.gms.games.GamesClientStatusCodes;
import com.google.android.gms.games.GamesStatusCodes;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.TimeUnit;

public final class zzg {
    private static zzp zzhzm = zzm.zzhzz;

    public static <R, PendingR extends Result> Task<R> zza(PendingResult<PendingR> pendingResult, zzbo<PendingR, R> zzbo) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        pendingResult.zza(new zzi(pendingResult, taskCompletionSource, zzbo));
        return taskCompletionSource.getTask();
    }

    public static <R, PendingR extends Result> Task<AnnotatedData<R>> zza(PendingResult<PendingR> pendingResult, zzbo<PendingR, R> zzbo, zzo<PendingR> zzo) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        pendingResult.zza(new zzj(pendingResult, taskCompletionSource, zzbo, zzo));
        return taskCompletionSource.getTask();
    }

    public static <R, PendingR extends Result> Task<R> zza(PendingResult<PendingR> pendingResult, zzp zzp, zzbo<PendingR, R> zzbo) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        pendingResult.zza(new zzl(zzp, pendingResult, taskCompletionSource, zzbo));
        return taskCompletionSource.getTask();
    }

    public static <R, PendingR extends Result, ExceptionData> Task<R> zza(PendingResult<PendingR> pendingResult, zzp zzp, zzbo<PendingR, R> zzbo, zzbo<PendingR, ExceptionData> zzbo2, zzn<ExceptionData> zzn) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        pendingResult.zza(new zzh(pendingResult, zzp, taskCompletionSource, zzbo, zzbo2, zzn));
        return taskCompletionSource.getTask();
    }

    static final /* synthetic */ void zza(PendingResult pendingResult, zzp zzp, TaskCompletionSource taskCompletionSource, zzbo zzbo, zzbo zzbo2, zzn zzn, Status status) {
        Result await = pendingResult.await(0, TimeUnit.MILLISECONDS);
        if (zzp.zzai(status)) {
            taskCompletionSource.setResult(zzbo.zzb(await));
            return;
        }
        Object exceptionData = zzbo2.zzb(await);
        if (exceptionData != null) {
            taskCompletionSource.setException(zzn.zza(zzan(status), exceptionData));
        } else {
            taskCompletionSource.setException(zzb.zzy(zzan(status)));
        }
    }

    static final /* synthetic */ void zza(PendingResult pendingResult, TaskCompletionSource taskCompletionSource, zzbo zzbo, Status status) {
        Result await = pendingResult.await(0, TimeUnit.MILLISECONDS);
        if (status.isSuccess()) {
            taskCompletionSource.setResult(zzbo.zzb(await));
        } else {
            taskCompletionSource.setException(zzb.zzy(zzan(status)));
        }
    }

    static final /* synthetic */ void zza(PendingResult pendingResult, TaskCompletionSource taskCompletionSource, zzbo zzbo, zzo zzo, Status status) {
        boolean z = status.getStatusCode() == 3;
        Result await = pendingResult.await(0, TimeUnit.MILLISECONDS);
        if (status.isSuccess() || z) {
            taskCompletionSource.setResult(new AnnotatedData(zzbo.zzb(await), z));
            return;
        }
        if (!(await == null || zzo == null)) {
            zzo.release(await);
        }
        taskCompletionSource.setException(zzb.zzy(zzan(status)));
    }

    static final /* synthetic */ void zza(zzbo zzbo, PendingResult pendingResult, TaskCompletionSource taskCompletionSource, Status status) {
        boolean z = status.getStatusCode() == 3;
        Releasable releasable = (Releasable) zzbo.zzb(pendingResult.await(0, TimeUnit.MILLISECONDS));
        if (status.isSuccess() || z) {
            taskCompletionSource.setResult(new AnnotatedData(releasable, z));
            return;
        }
        if (releasable != null) {
            releasable.release();
        }
        taskCompletionSource.setException(zzb.zzy(zzan(status)));
    }

    static final /* synthetic */ void zza(zzp zzp, PendingResult pendingResult, TaskCompletionSource taskCompletionSource, zzbo zzbo, Status status) {
        if (zzp.zzai(status)) {
            taskCompletionSource.setResult(zzbo.zzb(pendingResult.await(0, TimeUnit.MILLISECONDS)));
        } else {
            taskCompletionSource.setException(zzb.zzy(zzan(status)));
        }
    }

    private static Status zzan(Status status) {
        int zzdh = GamesClientStatusCodes.zzdh(status.getStatusCode());
        return zzdh != status.getStatusCode() ? GamesStatusCodes.getStatusString(status.getStatusCode()).equals(status.getStatusMessage()) ? GamesClientStatusCodes.zzdg(zzdh) : new Status(zzdh, status.getStatusMessage()) : status;
    }

    public static <R, PendingR extends Result> Task<AnnotatedData<R>> zzb(PendingResult<PendingR> pendingResult, zzbo<PendingR, R> zzbo) {
        return zza(pendingResult, zzbo, (zzo) null);
    }

    public static <R extends Releasable, PendingR extends Result> Task<AnnotatedData<R>> zzc(PendingResult<PendingR> pendingResult, zzbo<PendingR, R> zzbo) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        pendingResult.zza(new zzk(zzbo, pendingResult, taskCompletionSource));
        return taskCompletionSource.getTask();
    }
}
