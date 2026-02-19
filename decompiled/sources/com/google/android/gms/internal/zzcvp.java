package com.google.android.gms.internal;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;

final class zzcvp extends zzcvr {
    private /* synthetic */ Uri zzkff;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcvp(zzcvn zzcvn, GoogleApiClient googleApiClient, Uri uri) {
        super(googleApiClient);
        this.zzkff = uri;
    }

    /* access modifiers changed from: protected */
    public final void zza(Context context, zzcvl zzcvl) throws RemoteException {
        zzcvn.zza(context, zzcvl, new zzcvs(this), this.zzkff, (Bundle) null);
    }
}
