package com.google.android.gms.games.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.video.Videos;

final class zzdu extends zzdz {
    zzdu(zzdt zzdt, GoogleApiClient googleApiClient) {
        super(googleApiClient, (zzdu) null);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
        gamesClientImpl.zzh((zzn<Videos.CaptureCapabilitiesResult>) this);
    }
}
