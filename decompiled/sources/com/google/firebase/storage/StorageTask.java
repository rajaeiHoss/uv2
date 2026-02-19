package com.google.firebase.storage;

import android.app.Activity;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.RuntimeExecutionException;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.storage.StorageTask.ProvideError;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.Executor;

public abstract class StorageTask<TResult extends ProvideError> extends ControllableTask<TResult> {
    private static final HashMap<Integer, HashSet<Integer>> zzovc;
    private static final HashMap<Integer, HashSet<Integer>> zzovd;
    protected final Object mSyncObject = new Object();
    private volatile int zzdkm = 1;
    private zzx<OnSuccessListener<? super TResult>, TResult> zzove = new zzx<>(this, 128, new zzj(this));
    private zzx<OnFailureListener, TResult> zzovf = new zzx<>(this, 320, new zzk(this));
    private zzx<OnCompleteListener<TResult>, TResult> zzovg = new zzx<>(this, 448, new zzl(this));
    private zzx<OnProgressListener<? super TResult>, TResult> zzovh = new zzx<>(this, -465, new zzm(this));
    private zzx<OnPausedListener<? super TResult>, TResult> zzovi = new zzx<>(this, 16, new zzn(this));
    private TResult zzovj;

    public interface ProvideError {
        Exception getError();
    }

    public class SnapshotBase implements ProvideError {
        private final Exception zzovn;

        public SnapshotBase(Exception exc) {
            StorageException storageException;
            Status status;
            if (exc == null) {
                if (StorageTask.this.isCanceled()) {
                    status = Status.zzftu;
                } else if (StorageTask.this.zzcnz() == 64) {
                    status = Status.zzfts;
                } else {
                    storageException = null;
                    this.zzovn = storageException;
                    return;
                }
                storageException = StorageException.fromErrorStatus(status);
                this.zzovn = storageException;
                return;
            }
            this.zzovn = exc;
        }

        public Exception getError() {
            return this.zzovn;
        }

        public StorageReference getStorage() {
            return getTask().getStorage();
        }

        public StorageTask<TResult> getTask() {
            return StorageTask.this;
        }
    }

    static {
        HashMap<Integer, HashSet<Integer>> hashMap = new HashMap<>();
        zzovc = hashMap;
        HashMap<Integer, HashSet<Integer>> hashMap2 = new HashMap<>();
        zzovd = hashMap2;
        hashMap.put(1, new HashSet(Arrays.asList(new Integer[]{16, 256})));
        hashMap.put(2, new HashSet(Arrays.asList(new Integer[]{8, 32})));
        hashMap.put(4, new HashSet(Arrays.asList(new Integer[]{8, 32})));
        hashMap.put(16, new HashSet(Arrays.asList(new Integer[]{2, 256})));
        hashMap.put(64, new HashSet(Arrays.asList(new Integer[]{2, 256})));
        hashMap2.put(1, new HashSet(Arrays.asList(new Integer[]{2, 64})));
        hashMap2.put(2, new HashSet(Arrays.asList(new Integer[]{4, 64, 128})));
        hashMap2.put(4, new HashSet(Arrays.asList(new Integer[]{4, 64, 128})));
        hashMap2.put(8, new HashSet(Arrays.asList(new Integer[]{16, 64, 128})));
        hashMap2.put(32, new HashSet(Arrays.asList(new Integer[]{256, 64, 128})));
    }

    protected StorageTask() {
    }

    private final <TContinuationResult> Task<TContinuationResult> zza(Executor executor, Continuation<TResult, TContinuationResult> continuation) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.zzovg.zza((Activity) null, executor, new zzo(this, continuation, taskCompletionSource));
        return taskCompletionSource.getTask();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00c8, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean zza(int[] r9, boolean r10) {
        /*
            r8 = this;
            if (r10 == 0) goto L_0x0005
            java.util.HashMap<java.lang.Integer, java.util.HashSet<java.lang.Integer>> r0 = zzovc
            goto L_0x0007
        L_0x0005:
            java.util.HashMap<java.lang.Integer, java.util.HashSet<java.lang.Integer>> r0 = zzovd
        L_0x0007:
            java.lang.Object r1 = r8.mSyncObject
            monitor-enter(r1)
            int r2 = r9.length     // Catch:{ all -> 0x0113 }
            r3 = 0
            r4 = 0
        L_0x000d:
            if (r4 >= r2) goto L_0x00ce
            r5 = r9[r4]     // Catch:{ all -> 0x0113 }
            int r6 = r8.zzdkm     // Catch:{ all -> 0x0113 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x0113 }
            java.lang.Object r6 = r0.get(r6)     // Catch:{ all -> 0x0113 }
            java.util.HashSet r6 = (java.util.HashSet) r6     // Catch:{ all -> 0x0113 }
            if (r6 == 0) goto L_0x00ca
            java.lang.Integer r7 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x0113 }
            boolean r6 = r6.contains(r7)     // Catch:{ all -> 0x0113 }
            if (r6 == 0) goto L_0x00ca
            r8.zzdkm = r5     // Catch:{ all -> 0x0113 }
            int r9 = r8.zzdkm     // Catch:{ all -> 0x0113 }
            r0 = 2
            if (r9 == r0) goto L_0x0058
            r0 = 4
            if (r9 == r0) goto L_0x0054
            r0 = 16
            if (r9 == r0) goto L_0x0050
            r0 = 64
            if (r9 == r0) goto L_0x004c
            r0 = 128(0x80, float:1.794E-43)
            if (r9 == r0) goto L_0x0048
            r0 = 256(0x100, float:3.59E-43)
            if (r9 == r0) goto L_0x0044
            goto L_0x0062
        L_0x0044:
            r8.onCanceled()     // Catch:{ all -> 0x0113 }
            goto L_0x0062
        L_0x0048:
            r8.onSuccess()     // Catch:{ all -> 0x0113 }
            goto L_0x0062
        L_0x004c:
            r8.onFailure()     // Catch:{ all -> 0x0113 }
            goto L_0x0062
        L_0x0050:
            r8.onPaused()     // Catch:{ all -> 0x0113 }
            goto L_0x0062
        L_0x0054:
            r8.onProgress()     // Catch:{ all -> 0x0113 }
            goto L_0x0062
        L_0x0058:
            com.google.firebase.storage.zzt r9 = com.google.firebase.storage.zzt.zzcod()     // Catch:{ all -> 0x0113 }
            r9.zzb((com.google.firebase.storage.StorageTask) r8)     // Catch:{ all -> 0x0113 }
            r8.onQueued()     // Catch:{ all -> 0x0113 }
        L_0x0062:
            com.google.firebase.storage.zzx<com.google.android.gms.tasks.OnSuccessListener<? super TResult>, TResult> r9 = r8.zzove     // Catch:{ all -> 0x0113 }
            r9.zzcoh()     // Catch:{ all -> 0x0113 }
            com.google.firebase.storage.zzx<com.google.android.gms.tasks.OnFailureListener, TResult> r9 = r8.zzovf     // Catch:{ all -> 0x0113 }
            r9.zzcoh()     // Catch:{ all -> 0x0113 }
            com.google.firebase.storage.zzx<com.google.android.gms.tasks.OnCompleteListener<TResult>, TResult> r9 = r8.zzovg     // Catch:{ all -> 0x0113 }
            r9.zzcoh()     // Catch:{ all -> 0x0113 }
            com.google.firebase.storage.zzx<com.google.firebase.storage.OnPausedListener<? super TResult>, TResult> r9 = r8.zzovi     // Catch:{ all -> 0x0113 }
            r9.zzcoh()     // Catch:{ all -> 0x0113 }
            com.google.firebase.storage.zzx<com.google.firebase.storage.OnProgressListener<? super TResult>, TResult> r9 = r8.zzovh     // Catch:{ all -> 0x0113 }
            r9.zzcoh()     // Catch:{ all -> 0x0113 }
            java.lang.String r9 = "StorageTask"
            r0 = 3
            boolean r9 = android.util.Log.isLoggable(r9, r0)     // Catch:{ all -> 0x0113 }
            if (r9 == 0) goto L_0x00c7
            java.lang.String r9 = "StorageTask"
            java.lang.String r0 = zziz(r5)     // Catch:{ all -> 0x0113 }
            int r2 = r8.zzdkm     // Catch:{ all -> 0x0113 }
            java.lang.String r2 = zziz(r2)     // Catch:{ all -> 0x0113 }
            java.lang.String r3 = java.lang.String.valueOf(r0)     // Catch:{ all -> 0x0113 }
            int r3 = r3.length()     // Catch:{ all -> 0x0113 }
            int r3 = r3 + 53
            java.lang.String r4 = java.lang.String.valueOf(r2)     // Catch:{ all -> 0x0113 }
            int r4 = r4.length()     // Catch:{ all -> 0x0113 }
            int r3 = r3 + r4
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0113 }
            r4.<init>(r3)     // Catch:{ all -> 0x0113 }
            java.lang.String r3 = "changed internal state to: "
            r4.append(r3)     // Catch:{ all -> 0x0113 }
            r4.append(r0)     // Catch:{ all -> 0x0113 }
            java.lang.String r0 = " isUser: "
            r4.append(r0)     // Catch:{ all -> 0x0113 }
            r4.append(r10)     // Catch:{ all -> 0x0113 }
            java.lang.String r10 = " from state:"
            r4.append(r10)     // Catch:{ all -> 0x0113 }
            r4.append(r2)     // Catch:{ all -> 0x0113 }
            java.lang.String r10 = r4.toString()     // Catch:{ all -> 0x0113 }
            android.util.Log.d(r9, r10)     // Catch:{ all -> 0x0113 }
        L_0x00c7:
            monitor-exit(r1)     // Catch:{ all -> 0x0113 }
            r9 = 1
            return r9
        L_0x00ca:
            int r4 = r4 + 1
            goto L_0x000d
        L_0x00ce:
            java.lang.String r0 = "StorageTask"
            java.lang.String r9 = zze(r9)     // Catch:{ all -> 0x0113 }
            int r2 = r8.zzdkm     // Catch:{ all -> 0x0113 }
            java.lang.String r2 = zziz(r2)     // Catch:{ all -> 0x0113 }
            java.lang.String r4 = java.lang.String.valueOf(r9)     // Catch:{ all -> 0x0113 }
            int r4 = r4.length()     // Catch:{ all -> 0x0113 }
            int r4 = r4 + 62
            java.lang.String r5 = java.lang.String.valueOf(r2)     // Catch:{ all -> 0x0113 }
            int r5 = r5.length()     // Catch:{ all -> 0x0113 }
            int r4 = r4 + r5
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0113 }
            r5.<init>(r4)     // Catch:{ all -> 0x0113 }
            java.lang.String r4 = "unable to change internal state to: "
            r5.append(r4)     // Catch:{ all -> 0x0113 }
            r5.append(r9)     // Catch:{ all -> 0x0113 }
            java.lang.String r9 = " isUser: "
            r5.append(r9)     // Catch:{ all -> 0x0113 }
            r5.append(r10)     // Catch:{ all -> 0x0113 }
            java.lang.String r9 = " from state:"
            r5.append(r9)     // Catch:{ all -> 0x0113 }
            r5.append(r2)     // Catch:{ all -> 0x0113 }
            java.lang.String r9 = r5.toString()     // Catch:{ all -> 0x0113 }
            android.util.Log.w(r0, r9)     // Catch:{ all -> 0x0113 }
            monitor-exit(r1)     // Catch:{ all -> 0x0113 }
            return r3
        L_0x0113:
            r9 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0113 }
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.storage.StorageTask.zza(int[], boolean):boolean");
    }

    private final <TContinuationResult> Task<TContinuationResult> zzb(Executor executor, Continuation<TResult, Task<TContinuationResult>> continuation) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.zzovg.zza((Activity) null, executor, new zzp(this, continuation, taskCompletionSource));
        return taskCompletionSource.getTask();
    }

    private final TResult zzcob() {
        TResult tresult = this.zzovj;
        if (tresult != null) {
            return tresult;
        }
        if (!isComplete()) {
            return null;
        }
        if (this.zzovj == null) {
            this.zzovj = zzcoa();
        }
        return this.zzovj;
    }

    /* access modifiers changed from: private */
    public final void zzcoc() {
        if (!isComplete() && !isPaused() && this.zzdkm != 2 && !zzk(256, false)) {
            zzk(64, false);
        }
    }

    private static String zze(int[] iArr) {
        if (iArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int zziz : iArr) {
            sb.append(zziz(zziz));
            sb.append(", ");
        }
        return sb.substring(0, sb.length() - 2);
    }

    private static String zziz(int i) {
        return i != 1 ? i != 2 ? i != 4 ? i != 8 ? i != 16 ? i != 32 ? i != 64 ? i != 128 ? i != 256 ? "Unknown Internal State!" : "INTERNAL_STATE_CANCELED" : "INTERNAL_STATE_SUCCESS" : "INTERNAL_STATE_FAILURE" : "INTERNAL_STATE_CANCELING" : "INTERNAL_STATE_PAUSED" : "INTERNAL_STATE_PAUSING" : "INTERNAL_STATE_IN_PROGRESS" : "INTERNAL_STATE_QUEUED" : "INTERNAL_STATE_NOT_STARTED";
    }

    public StorageTask<TResult> addOnCompleteListener(Activity activity, OnCompleteListener<TResult> onCompleteListener) {
        zzbq.checkNotNull(onCompleteListener);
        zzbq.checkNotNull(activity);
        this.zzovg.zza(activity, (Executor) null, onCompleteListener);
        return this;
    }

    public StorageTask<TResult> addOnCompleteListener(OnCompleteListener<TResult> onCompleteListener) {
        zzbq.checkNotNull(onCompleteListener);
        this.zzovg.zza((Activity) null, (Executor) null, onCompleteListener);
        return this;
    }

    public StorageTask<TResult> addOnCompleteListener(Executor executor, OnCompleteListener<TResult> onCompleteListener) {
        zzbq.checkNotNull(onCompleteListener);
        zzbq.checkNotNull(executor);
        this.zzovg.zza((Activity) null, executor, onCompleteListener);
        return this;
    }

    public StorageTask<TResult> addOnFailureListener(Activity activity, OnFailureListener onFailureListener) {
        zzbq.checkNotNull(onFailureListener);
        zzbq.checkNotNull(activity);
        this.zzovf.zza(activity, (Executor) null, onFailureListener);
        return this;
    }

    public StorageTask<TResult> addOnFailureListener(OnFailureListener onFailureListener) {
        zzbq.checkNotNull(onFailureListener);
        this.zzovf.zza((Activity) null, (Executor) null, onFailureListener);
        return this;
    }

    public StorageTask<TResult> addOnFailureListener(Executor executor, OnFailureListener onFailureListener) {
        zzbq.checkNotNull(onFailureListener);
        zzbq.checkNotNull(executor);
        this.zzovf.zza((Activity) null, executor, onFailureListener);
        return this;
    }

    public StorageTask<TResult> addOnPausedListener(Activity activity, OnPausedListener<? super TResult> onPausedListener) {
        zzbq.checkNotNull(onPausedListener);
        zzbq.checkNotNull(activity);
        this.zzovi.zza(activity, (Executor) null, onPausedListener);
        return this;
    }

    public StorageTask<TResult> addOnPausedListener(OnPausedListener<? super TResult> onPausedListener) {
        zzbq.checkNotNull(onPausedListener);
        this.zzovi.zza((Activity) null, (Executor) null, onPausedListener);
        return this;
    }

    public StorageTask<TResult> addOnPausedListener(Executor executor, OnPausedListener<? super TResult> onPausedListener) {
        zzbq.checkNotNull(onPausedListener);
        zzbq.checkNotNull(executor);
        this.zzovi.zza((Activity) null, executor, onPausedListener);
        return this;
    }

    public StorageTask<TResult> addOnProgressListener(Activity activity, OnProgressListener<? super TResult> onProgressListener) {
        zzbq.checkNotNull(onProgressListener);
        zzbq.checkNotNull(activity);
        this.zzovh.zza(activity, (Executor) null, onProgressListener);
        return this;
    }

    public StorageTask<TResult> addOnProgressListener(OnProgressListener<? super TResult> onProgressListener) {
        zzbq.checkNotNull(onProgressListener);
        this.zzovh.zza((Activity) null, (Executor) null, onProgressListener);
        return this;
    }

    public StorageTask<TResult> addOnProgressListener(Executor executor, OnProgressListener<? super TResult> onProgressListener) {
        zzbq.checkNotNull(onProgressListener);
        zzbq.checkNotNull(executor);
        this.zzovh.zza((Activity) null, executor, onProgressListener);
        return this;
    }

    public StorageTask<TResult> addOnSuccessListener(Activity activity, OnSuccessListener<? super TResult> onSuccessListener) {
        zzbq.checkNotNull(activity);
        zzbq.checkNotNull(onSuccessListener);
        this.zzove.zza(activity, (Executor) null, onSuccessListener);
        return this;
    }

    public StorageTask<TResult> addOnSuccessListener(OnSuccessListener<? super TResult> onSuccessListener) {
        zzbq.checkNotNull(onSuccessListener);
        this.zzove.zza((Activity) null, (Executor) null, onSuccessListener);
        return this;
    }

    public StorageTask<TResult> addOnSuccessListener(Executor executor, OnSuccessListener<? super TResult> onSuccessListener) {
        zzbq.checkNotNull(executor);
        zzbq.checkNotNull(onSuccessListener);
        this.zzove.zza((Activity) null, executor, onSuccessListener);
        return this;
    }

    public boolean cancel() {
        return zza(new int[]{256, 32}, true);
    }

    public <TContinuationResult> Task<TContinuationResult> continueWith(Continuation<TResult, TContinuationResult> continuation) {
        return zza((Executor) null, continuation);
    }

    public <TContinuationResult> Task<TContinuationResult> continueWith(Executor executor, Continuation<TResult, TContinuationResult> continuation) {
        return zza(executor, continuation);
    }

    public <TContinuationResult> Task<TContinuationResult> continueWithTask(Continuation<TResult, Task<TContinuationResult>> continuation) {
        return zzb((Executor) null, continuation);
    }

    public <TContinuationResult> Task<TContinuationResult> continueWithTask(Executor executor, Continuation<TResult, Task<TContinuationResult>> continuation) {
        return zzb(executor, continuation);
    }

    public Exception getException() {
        if (zzcob() == null) {
            return null;
        }
        return zzcob().getError();
    }

    public TResult getResult() {
        if (zzcob() != null) {
            Exception error = zzcob().getError();
            if (error == null) {
                return zzcob();
            }
            throw new RuntimeExecutionException(error);
        }
        throw new IllegalStateException();
    }

    public <X extends Throwable> TResult getResult(Class<X> cls) throws Throwable {
        if (zzcob() == null) {
            throw new IllegalStateException();
        } else if (!cls.isInstance(zzcob().getError())) {
            Exception error = zzcob().getError();
            if (error == null) {
                return zzcob();
            }
            throw new RuntimeExecutionException(error);
        } else {
            throw ((Throwable) cls.cast(zzcob().getError()));
        }
    }

    public TResult getSnapshot() {
        return zzcoa();
    }

    /* access modifiers changed from: package-private */
    public abstract StorageReference getStorage();

    public boolean isCanceled() {
        return this.zzdkm == 256;
    }

    public boolean isComplete() {
        return ((this.zzdkm & 128) == 0 && (this.zzdkm & 320) == 0) ? false : true;
    }

    public boolean isInProgress() {
        return (this.zzdkm & -465) != 0;
    }

    public boolean isPaused() {
        return (this.zzdkm & 16) != 0;
    }

    public boolean isSuccessful() {
        return (this.zzdkm & 128) != 0;
    }

    /* access modifiers changed from: protected */
    public void onCanceled() {
    }

    /* access modifiers changed from: protected */
    public void onFailure() {
    }

    /* access modifiers changed from: protected */
    public void onPaused() {
    }

    /* access modifiers changed from: protected */
    public void onProgress() {
    }

    /* access modifiers changed from: protected */
    public void onQueued() {
    }

    /* access modifiers changed from: protected */
    public void onSuccess() {
    }

    public boolean pause() {
        return zza(new int[]{16, 8}, true);
    }

    public StorageTask<TResult> removeOnCompleteListener(OnCompleteListener<TResult> onCompleteListener) {
        zzbq.checkNotNull(onCompleteListener);
        this.zzovg.zzcp(onCompleteListener);
        return this;
    }

    public StorageTask<TResult> removeOnFailureListener(OnFailureListener onFailureListener) {
        zzbq.checkNotNull(onFailureListener);
        this.zzovf.zzcp(onFailureListener);
        return this;
    }

    public StorageTask<TResult> removeOnPausedListener(OnPausedListener<? super TResult> onPausedListener) {
        zzbq.checkNotNull(onPausedListener);
        this.zzovi.zzcp(onPausedListener);
        return this;
    }

    public StorageTask<TResult> removeOnProgressListener(OnProgressListener<? super TResult> onProgressListener) {
        zzbq.checkNotNull(onProgressListener);
        this.zzovh.zzcp(onProgressListener);
        return this;
    }

    public StorageTask<TResult> removeOnSuccessListener(OnSuccessListener<? super TResult> onSuccessListener) {
        zzbq.checkNotNull(onSuccessListener);
        this.zzove.zzcp(onSuccessListener);
        return this;
    }

    /* access modifiers changed from: package-private */
    public void resetState() {
    }

    public boolean resume() {
        if (!zzk(2, true)) {
            return false;
        }
        resetState();
        schedule();
        return true;
    }

    /* access modifiers changed from: package-private */
    public abstract void run();

    /* access modifiers changed from: package-private */
    public abstract void schedule();

    /* access modifiers changed from: package-private */
    public final Runnable zzbmh() {
        return new zzs(this);
    }

    /* access modifiers changed from: package-private */
    public abstract TResult zzcnt();

    /* access modifiers changed from: package-private */
    public final boolean zzcny() {
        if (!zzk(2, false)) {
            return false;
        }
        schedule();
        return true;
    }

    /* access modifiers changed from: package-private */
    public final int zzcnz() {
        return this.zzdkm;
    }

    /* access modifiers changed from: package-private */
    public final TResult zzcoa() {
        TResult zzcnt;
        synchronized (this.mSyncObject) {
            zzcnt = zzcnt();
        }
        return zzcnt;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzk(int i, boolean z) {
        return zza(new int[]{i}, z);
    }
}
