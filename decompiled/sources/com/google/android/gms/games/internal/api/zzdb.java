package com.google.android.gms.games.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.multiplayer.ParticipantResult;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;

final class zzdb extends zzdr {
    private /* synthetic */ String zzhvz;
    private /* synthetic */ byte[] zzibi;
    private /* synthetic */ String zzibj;
    private /* synthetic */ ParticipantResult[] zzibk;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzdb(zzcw zzcw, GoogleApiClient googleApiClient, String str, byte[] bArr, String str2, ParticipantResult[] participantResultArr) {
        super(googleApiClient, (zzcx) null);
        this.zzhvz = str;
        this.zzibi = bArr;
        this.zzibj = str2;
        this.zzibk = participantResultArr;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
        gamesClientImpl.zza((zzn<TurnBasedMultiplayer.UpdateMatchResult>) this, this.zzhvz, this.zzibi, this.zzibj, this.zzibk);
    }
}
