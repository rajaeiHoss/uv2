package com.google.android.gms.internal;

import android.content.IntentSender;
import android.os.RemoteException;
import com.google.android.gms.common.api.internal.zzde;
import com.google.android.gms.drive.OpenFileActivityOptions;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzbny extends zzde<zzbnq, IntentSender> {
    private /* synthetic */ OpenFileActivityOptions zzguj;

    zzbny(zzbnv zzbnv, OpenFileActivityOptions openFileActivityOptions) {
        this.zzguj = openFileActivityOptions;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbnq zzbnq, TaskCompletionSource<IntentSender> taskCompletionSource) throws RemoteException {
        try {
            taskCompletionSource.setResult(((zzbrk) zzbnq.zzalw()).zza(new zzbtc(this.zzguj.title, this.zzguj.zzgqu, this.zzguj.zzgqw, this.zzguj.zzgqv)));
        } catch (RemoteException e) {
            taskCompletionSource.setException(e);
        }
    }
}
