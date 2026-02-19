package com.google.android.gms.internal;

import android.content.IntentSender;
import android.os.RemoteException;
import com.google.android.gms.common.api.internal.zzde;
import com.google.android.gms.drive.CreateFileActivityOptions;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzbnz extends zzde<zzbnq, IntentSender> {
    private /* synthetic */ CreateFileActivityOptions zzguk;

    zzbnz(zzbnv zzbnv, CreateFileActivityOptions createFileActivityOptions) {
        this.zzguk = createFileActivityOptions;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbnq zzbnq, TaskCompletionSource<IntentSender> taskCompletionSource) throws RemoteException {
        try {
            this.zzguk.zzgsz.setContext(zzbnq.getContext());
            taskCompletionSource.setResult(((zzbrk) zzbnq.zzalw()).zza(new zzbmj(this.zzguk.zzgsz, this.zzguk.zzgta.intValue(), this.zzguk.title, this.zzguk.zzgqw, Integer.valueOf(this.zzguk.zzgtb))));
        } catch (RemoteException e) {
            taskCompletionSource.setException(e);
        }
    }
}
