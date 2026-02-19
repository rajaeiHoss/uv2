package com.google.firebase.database;

import com.google.android.gms.internal.zzeng;
import com.google.android.gms.internal.zzenm;
import java.util.Iterator;

final class zzb implements Iterator<DataSnapshot> {
    private /* synthetic */ zza zzmwc;

    zzb(zza zza) {
        this.zzmwc = zza;
    }

    public final boolean hasNext() {
        return this.zzmwc.zzmwa.hasNext();
    }

    public final DataSnapshot next() {
        zzenm zzenm = (zzenm) this.zzmwc.zzmwa.next();
        return new DataSnapshot(this.zzmwc.zzmwb.zzmvz.child(zzenm.zzccx().asString()), zzeng.zzj(zzenm.zzbve()));
    }

    public final void remove() {
        throw new UnsupportedOperationException("remove called on immutable collection");
    }
}
