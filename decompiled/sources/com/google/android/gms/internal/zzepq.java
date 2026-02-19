package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.api.internal.zzde;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.dynamiclinks.ShortDynamicLink;

final class zzepq extends zzde<zzepm, ShortDynamicLink> {
    private final Bundle zznsh;

    zzepq(Bundle bundle) {
        this.zznsh = bundle;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzepm zzepm, TaskCompletionSource<ShortDynamicLink> taskCompletionSource) throws RemoteException {
        try {
            ((zzepv) zzepm.zzalw()).zza((zzept) new zzepp(taskCompletionSource), this.zznsh);
        } catch (RemoteException unused) {
        }
    }
}
