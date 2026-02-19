package com.google.android.gms.games;

import com.google.android.gms.common.api.internal.zzcl;
import com.google.android.gms.games.multiplayer.realtime.zzh;

final class zzbp implements zzcl<zzh> {
    private /* synthetic */ zzbo zzhuw;

    zzbp(zzbo zzbo) {
        this.zzhuw = zzbo;
    }

    public final void zzajh() {
        this.zzhuw.zzhuv.zzhus.getRoomUpdateCallback().onLeftRoom(0, this.zzhuw.zzhuv.zzhul);
    }

    public final void zzu(zzh zzh) {
        zzh.onLeftRoom(0, this.zzhuw.zzhuv.zzhul);
    }
}
