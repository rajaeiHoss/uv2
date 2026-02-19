package com.google.android.gms.internal;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;

final class zzcvq extends zzcvk {
    private /* synthetic */ Context val$context;
    private /* synthetic */ Uri zzkff;
    private /* synthetic */ zzcvj zzkfg;

    zzcvq(Context context, Uri uri, zzcvj zzcvj) {
        this.val$context = context;
        this.zzkff = uri;
        this.zzkfg = zzcvj;
    }

    public final void zza(int i, Bundle bundle, int i2, Intent intent) throws RemoteException {
        zzcvn.zzb(this.val$context, this.zzkff);
        this.zzkfg.zza(i, bundle, i2, intent);
    }
}
