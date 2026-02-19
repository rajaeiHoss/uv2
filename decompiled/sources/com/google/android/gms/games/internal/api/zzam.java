package com.google.android.gms.games.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.internal.GamesClientImpl;

final class zzam extends zzat {
    private /* synthetic */ String zzhsx;
    private /* synthetic */ boolean zziah;
    private /* synthetic */ int zziam;
    private /* synthetic */ int zzian;
    private /* synthetic */ int zziao;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzam(zzah zzah, GoogleApiClient googleApiClient, String str, int i, int i2, int i3, boolean z) {
        super(googleApiClient, (zzai) null);
        this.zzhsx = str;
        this.zziam = i;
        this.zzian = i2;
        this.zziao = i3;
        this.zziah = z;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
        gamesClientImpl.zzb(this, this.zzhsx, this.zziam, this.zzian, this.zziao, this.zziah);
    }
}
