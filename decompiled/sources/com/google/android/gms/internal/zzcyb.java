package com.google.android.gms.internal;

import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzm;
import com.google.android.gms.search.SearchAuth;

final class zzcyb extends zzm<Status, zzcxy> {
    private final String zzklb;
    private final String zzkle;
    /* access modifiers changed from: private */
    public final boolean zzklf = Log.isLoggable("SearchAuth", 3);

    protected zzcyb(GoogleApiClient googleApiClient, String str) {
        super((Api<?>) SearchAuth.API, googleApiClient);
        this.zzklb = str;
        this.zzkle = googleApiClient.getContext().getPackageName();
    }

    /* access modifiers changed from: protected */
    public final void zza(zzcxy zzcxy) throws RemoteException {
        if (this.zzklf) {
            Log.d("SearchAuth", "ClearTokenImpl started");
        }
        ((zzcxw) zzcxy.zzalw()).zzb(new zzcyc(this), this.zzkle, this.zzklb);
    }

    /* access modifiers changed from: protected */
    public final Status zzb(Status status) {
        if (this.zzklf) {
            String valueOf = String.valueOf(status.getStatusMessage());
            Log.d("SearchAuth", valueOf.length() != 0 ? "ClearTokenImpl received failure: ".concat(valueOf) : new String("ClearTokenImpl received failure: "));
        }
        return status;
    }
}
