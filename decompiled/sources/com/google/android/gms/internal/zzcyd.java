package com.google.android.gms.internal;

import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzm;
import com.google.android.gms.search.GoogleNowAuthState;
import com.google.android.gms.search.SearchAuth;
import com.google.android.gms.search.SearchAuthApi;

final class zzcyd extends zzm<SearchAuthApi.GoogleNowAuthResult, zzcxy> {
    private final String zzkle;
    /* access modifiers changed from: private */
    public final boolean zzklf = Log.isLoggable("SearchAuth", 3);
    private final String zzklh;

    protected zzcyd(GoogleApiClient googleApiClient, String str) {
        super((Api<?>) SearchAuth.API, googleApiClient);
        this.zzklh = str;
        this.zzkle = googleApiClient.getContext().getPackageName();
    }

    /* access modifiers changed from: protected */
    public final void zza(zzcxy zzcxy) throws RemoteException {
        if (this.zzklf) {
            Log.d("SearchAuth", "GetGoogleNowAuthImpl started");
        }
        ((zzcxw) zzcxy.zzalw()).zza(new zzcye(this), this.zzkle, this.zzklh);
    }

    /* access modifiers changed from: protected */
    public final SearchAuthApi.GoogleNowAuthResult zzb(Status status) {
        if (this.zzklf) {
            String valueOf = String.valueOf(status.getStatusMessage());
            Log.d("SearchAuth", valueOf.length() != 0 ? "GetGoogleNowAuthImpl received failure: ".concat(valueOf) : new String("GetGoogleNowAuthImpl received failure: "));
        }
        return new zzcyf(status, (GoogleNowAuthState) null);
    }
}
