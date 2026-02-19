package com.google.firebase.storage;

import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.storage.StreamDownloadTask;

final class zzh implements OnSuccessListener<StreamDownloadTask.TaskSnapshot> {
    private /* synthetic */ TaskCompletionSource zzova;

    zzh(StorageReference storageReference, TaskCompletionSource taskCompletionSource) {
        this.zzova = taskCompletionSource;
    }

    public final void onSuccess(StreamDownloadTask.TaskSnapshot taskSnapshot) {
        if (!this.zzova.getTask().isComplete()) {
            Log.e("StorageReference", "getBytes 'succeeded', but failed to set a Result.");
            this.zzova.setException(StorageException.fromErrorStatus(Status.zzfts));
        }
    }
}
