package com.google.firebase.storage;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zze implements OnSuccessListener<StorageMetadata> {
    private /* synthetic */ TaskCompletionSource zzouz;

    zze(StorageReference storageReference, TaskCompletionSource taskCompletionSource) {
        this.zzouz = taskCompletionSource;
    }

    public final void onSuccess(StorageMetadata storageMetadata) {
        this.zzouz.setResult(storageMetadata.getDownloadUrl());
    }
}
