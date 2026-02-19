package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.api.internal.zzde;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;

final class zzeps extends zzde<zzepm, PendingDynamicLinkData> {
    private final Context mApplicationContext;
    private final String zznsb;

    zzeps(Context context, String str) {
        this.mApplicationContext = context;
        this.zznsb = str;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzepm zzepm, TaskCompletionSource<PendingDynamicLinkData> taskCompletionSource) throws RemoteException {
        try {
            ((zzepv) zzepm.zzalw()).zza((zzept) new zzepr(this.mApplicationContext, taskCompletionSource), this.zznsb);
        } catch (RemoteException unused) {
        }
    }
}
