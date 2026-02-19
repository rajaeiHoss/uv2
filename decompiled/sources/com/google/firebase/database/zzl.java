package com.google.firebase.database;

import com.google.android.gms.internal.zzenm;
import java.util.Iterator;

final class zzl implements Iterator<MutableData> {
    private /* synthetic */ zzk zzmwz;

    zzl(zzk zzk) {
        this.zzmwz = zzk;
    }

    public final boolean hasNext() {
        return this.zzmwz.zzmwa.hasNext();
    }

    public final MutableData next() {
        return new MutableData(this.zzmwz.zzmwy.zzmww, this.zzmwz.zzmwy.zzmwx.zza(((zzenm) this.zzmwz.zzmwa.next()).zzccx()), (zzi) null);
    }

    public final void remove() {
        throw new UnsupportedOperationException("remove called on immutable collection");
    }
}
