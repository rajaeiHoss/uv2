package com.google.firebase.storage;

import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzfbc;
import com.google.android.gms.internal.zzfbm;
import com.google.android.gms.internal.zzfbn;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzb implements Runnable {
    private StorageReference zzotm;
    private TaskCompletionSource<Void> zzotn;
    private zzfbc zzoto = new zzfbc(this.zzotm.getStorage().getApp(), this.zzotm.getStorage().getMaxOperationRetryTimeMillis());

    public zzb(StorageReference storageReference, TaskCompletionSource<Void> taskCompletionSource) {
        zzbq.checkNotNull(storageReference);
        zzbq.checkNotNull(taskCompletionSource);
        this.zzotm = storageReference;
        this.zzotn = taskCompletionSource;
    }

    public final void run() {
        try {
            zzfbn zzv = zzfbm.zzi(this.zzotm.getStorage().getApp()).zzv(this.zzotm.zzcnx());
            this.zzoto.zza(zzv, true);
            zzv.zza(this.zzotn, null);
        } catch (RemoteException e) {
            Log.e("DeleteStorageTask", "Unable to create Firebase Storage network request.", e);
            this.zzotn.setException(StorageException.fromException(e));
        }
    }
}
