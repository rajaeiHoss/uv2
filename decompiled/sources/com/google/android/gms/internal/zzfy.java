package com.google.android.gms.internal;

import android.database.ContentObserver;
import android.os.Handler;

final class zzfy extends ContentObserver {
    private /* synthetic */ zzfv zzawp;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzfy(zzfv zzfv, Handler handler) {
        super(handler);
        this.zzawp = zzfv;
    }

    public final void onChange(boolean z) {
        super.onChange(z);
        this.zzawp.zzgb();
    }
}
