package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.drive.MetadataBuffer;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zzbuc extends zzbtx<MetadataBuffer> {
    public zzbuc(TaskCompletionSource<MetadataBuffer> taskCompletionSource) {
        super(taskCompletionSource);
    }

    public final void zza(zzbsl zzbsl) throws RemoteException {
        zzaqt().setResult(new MetadataBuffer(zzbsl.zzaqr()));
    }
}
