package com.google.android.gms.games.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.common.internal.zzp;

final class zze implements zzn<Status> {
    private /* synthetic */ zzp zzhxd;

    zze(GamesClientImpl gamesClientImpl, zzp zzp) {
        this.zzhxd = zzp;
    }

    public final void setResult(Status status) {
        this.zzhxd.zzako();
    }

    public final void zzu(Status status) {
        this.zzhxd.zzako();
    }
}
