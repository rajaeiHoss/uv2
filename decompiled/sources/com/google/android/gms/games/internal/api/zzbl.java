package com.google.android.gms.games.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.quest.Quests;

final class zzbl extends zzbq {
    private /* synthetic */ String zzias;
    private /* synthetic */ String zziat;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbl(zzbj zzbj, GoogleApiClient googleApiClient, String str, String str2) {
        super(googleApiClient, (zzbk) null);
        this.zzias = str;
        this.zziat = str2;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
        gamesClientImpl.zzb((zzn<Quests.ClaimMilestoneResult>) this, this.zzias, this.zziat);
    }
}
