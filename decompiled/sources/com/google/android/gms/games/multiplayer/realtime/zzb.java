package com.google.android.gms.games.multiplayer.realtime;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzg;

public final class zzb extends zzg<Room> {
    public zzb(DataHolder dataHolder) {
        super(dataHolder);
    }

    /* access modifiers changed from: protected */
    public final String zzalj() {
        return "external_match_id";
    }

    /* access modifiers changed from: protected */
    public final Room zzl(int i, int i2) {
        return new zzf(this.zzfxb, i, i2);
    }
}
