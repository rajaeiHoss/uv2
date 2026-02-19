package com.google.android.gms.games.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.video.Videos;

final class zzdw extends zzdx {
    private /* synthetic */ int zzibo;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzdw(zzdt zzdt, GoogleApiClient googleApiClient, int i) {
        super(googleApiClient, (zzdu) null);
        this.zzibo = i;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
        gamesClientImpl.zzb((zzn<Videos.CaptureAvailableResult>) this, this.zzibo);
    }
}
