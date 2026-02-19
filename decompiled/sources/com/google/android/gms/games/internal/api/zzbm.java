package com.google.android.gms.games.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.quest.Quests;

final class zzbm extends zzbs {
    private /* synthetic */ boolean zziah;
    private /* synthetic */ int zzial;
    private /* synthetic */ int[] zziau;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbm(zzbj zzbj, GoogleApiClient googleApiClient, int[] iArr, int i, boolean z) {
        super(googleApiClient, (zzbk) null);
        this.zziau = iArr;
        this.zzial = i;
        this.zziah = z;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
        gamesClientImpl.zza((zzn<Quests.LoadQuestsResult>) this, this.zziau, this.zzial, this.zziah);
    }
}
