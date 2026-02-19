package com.google.android.gms.internal;

import android.os.IInterface;
import android.os.Looper;
import java.util.HashMap;

public final class zzfk<L, W extends IInterface> {
    private final Looper zzalj;
    private final zzfl<L, W> zzalk;
    private final HashMap<L, W> zzall = new HashMap<>();

    public zzfk(Looper looper, zzfl<L, W> zzfl) {
        this.zzalj = looper;
        this.zzalk = zzfl;
    }
}
