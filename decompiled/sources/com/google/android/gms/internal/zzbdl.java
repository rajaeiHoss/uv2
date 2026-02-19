package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.zzde;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.List;

final class zzbdl extends zzde<zzbdn, Void> {
    private /* synthetic */ List zzffw;
    private /* synthetic */ String[] zzflz;
    private /* synthetic */ String zzfma;

    zzbdl(zzbdj zzbdj, String[] strArr, String str, List list) {
        this.zzflz = strArr;
        this.zzfma = str;
        this.zzffw = list;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbdn zzbdn, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException {
        ((zzbef) zzbdn.zzalw()).zza(new zzbdm(this, taskCompletionSource), this.zzflz, this.zzfma, this.zzffw);
    }
}
