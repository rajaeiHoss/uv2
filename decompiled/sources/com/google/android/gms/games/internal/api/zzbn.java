package com.google.android.gms.games.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.quest.Quests;

final class zzbn extends zzbs {
    private /* synthetic */ boolean zziah;
    private /* synthetic */ String[] zziav;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbn(zzbj zzbj, GoogleApiClient googleApiClient, boolean z, String[] strArr) {
        super(googleApiClient, (zzbk) null);
        this.zziah = z;
        this.zziav = strArr;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
        gamesClientImpl.zzb((zzn<Quests.LoadQuestsResult>) this, this.zziah, this.zziav);
    }
}
