package com.google.android.gms.games.request;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzg;

@Deprecated
public final class GameRequestBuffer extends zzg<GameRequest> {
    public GameRequestBuffer(DataHolder dataHolder) {
        super(dataHolder);
    }

    /* access modifiers changed from: protected */
    public final String zzalj() {
        return "external_request_id";
    }

    /* access modifiers changed from: protected */
    public final GameRequest zzl(int i, int i2) {
        return new zzb(this.zzfxb, i, i2);
    }
}
