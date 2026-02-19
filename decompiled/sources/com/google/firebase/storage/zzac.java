package com.google.firebase.storage;

import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.internal.zzfbc;
import com.google.android.gms.internal.zzfbn;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.storage.StorageMetadata;
import org.json.JSONException;

final class zzac implements Runnable {
    private final StorageReference zzotm;
    private final TaskCompletionSource<StorageMetadata> zzotn;
    private zzfbc zzoto;
    private StorageMetadata zzoua = null;
    private final StorageMetadata zzowu;

    public zzac(StorageReference storageReference, TaskCompletionSource<StorageMetadata> taskCompletionSource, StorageMetadata storageMetadata) {
        this.zzotm = storageReference;
        this.zzotn = taskCompletionSource;
        this.zzowu = storageMetadata;
        this.zzoto = new zzfbc(storageReference.getStorage().getApp(), storageReference.getStorage().getMaxOperationRetryTimeMillis());
    }

    public final void run() {
        TaskCompletionSource<StorageMetadata> taskCompletionSource;
        StorageException fromException;
        try {
            zzfbn zza = this.zzotm.zzcnw().zza(this.zzotm.zzcnx(), this.zzowu.zzcnu());
            this.zzoto.zza(zza, true);
            if (zza.zzcos()) {
                try {
                    this.zzoua = new StorageMetadata.Builder(zza.zzcov(), this.zzotm).build();
                } catch (RemoteException | JSONException e) {
                    String valueOf = String.valueOf(zza.zzcoq());
                    Log.e("UpdateMetadataTask", valueOf.length() != 0 ? "Unable to parse a valid JSON object from resulting metadata:".concat(valueOf) : new String("Unable to parse a valid JSON object from resulting metadata:"), e);
                    taskCompletionSource = this.zzotn;
                    fromException = StorageException.fromException(e);
                }
            }
            TaskCompletionSource<StorageMetadata> taskCompletionSource2 = this.zzotn;
            if (taskCompletionSource2 != null) {
                zza.zza(taskCompletionSource2, this.zzoua);
            }
        } catch (RemoteException | JSONException e2) {
            Log.e("UpdateMetadataTask", "Unable to create the request from metadata.", e2);
            taskCompletionSource = this.zzotn;
            fromException = StorageException.fromException(e2);
            taskCompletionSource.setException(fromException);
        }
    }
}
