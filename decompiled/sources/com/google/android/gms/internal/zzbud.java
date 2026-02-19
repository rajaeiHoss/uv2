package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.drive.Metadata;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zzbud extends zzbtx<Metadata> {
    public zzbud(TaskCompletionSource<Metadata> taskCompletionSource) {
        super(taskCompletionSource);
    }

    public final void zza(zzbso zzbso) throws RemoteException {
        zzaqt().setResult(new zzbmp(zzbso.zzaqs()));
    }
}
