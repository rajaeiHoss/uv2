package com.google.firebase.storage;

import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzfbc;
import com.google.android.gms.internal.zzfbm;
import com.google.android.gms.internal.zzfbn;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.storage.StorageMetadata;
import org.json.JSONException;

final class zzc implements Runnable {
    private StorageReference zzotm;
    private TaskCompletionSource<StorageMetadata> zzotn;
    private zzfbc zzoto = new zzfbc(this.zzotm.getStorage().getApp(), this.zzotm.getStorage().getMaxOperationRetryTimeMillis());
    private StorageMetadata zzoua;

    public zzc(StorageReference storageReference, TaskCompletionSource<StorageMetadata> taskCompletionSource) {
        zzbq.checkNotNull(storageReference);
        zzbq.checkNotNull(taskCompletionSource);
        this.zzotm = storageReference;
        this.zzotn = taskCompletionSource;
    }

    public final void run() {
        TaskCompletionSource<StorageMetadata> taskCompletionSource;
        StorageException fromException;
        try {
            zzfbn zzw = zzfbm.zzi(this.zzotm.getStorage().getApp()).zzw(this.zzotm.zzcnx());
            this.zzoto.zza(zzw, true);
            if (zzw.zzcos()) {
                try {
                    this.zzoua = new StorageMetadata.Builder(zzw.zzcov(), this.zzotm).build();
                } catch (RemoteException | JSONException e) {
                    String valueOf = String.valueOf(zzw.zzcoq());
                    Log.e("GetMetadataTask", valueOf.length() != 0 ? "Unable to parse resulting metadata. ".concat(valueOf) : new String("Unable to parse resulting metadata. "), e);
                    taskCompletionSource = this.zzotn;
                    fromException = StorageException.fromException(e);
                }
            }
            TaskCompletionSource<StorageMetadata> taskCompletionSource2 = this.zzotn;
            if (taskCompletionSource2 != null) {
                zzw.zza(taskCompletionSource2, this.zzoua);
            }
        } catch (RemoteException e2) {
            Log.e("GetMetadataTask", "Unable to create firebase storage network request.", e2);
            taskCompletionSource = this.zzotn;
            fromException = StorageException.fromException(e2);
            taskCompletionSource.setException(fromException);
        }
    }
}
