package com.google.android.gms.dynamic;

import android.os.Bundle;
import java.util.Iterator;

final class zzb<T extends LifecycleDelegate> implements zzo<T> {
    private /* synthetic */ zza<T> zzhct;

    zzb(zza<T> zza) {
        this.zzhct = zza;
    }

    public final void zza(T t) {
        LifecycleDelegate unused = this.zzhct.zzhcp = t;
        Iterator it = this.zzhct.zzhcr.iterator();
        while (it.hasNext()) {
            ((zzi) it.next()).zzb(this.zzhct.zzhcp);
        }
        this.zzhct.zzhcr.clear();
        Bundle unused2 = this.zzhct.zzhcq = null;
    }
}
