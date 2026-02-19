package com.google.android.gms.internal;

import android.os.Handler;
import java.util.LinkedList;
import java.util.List;

@zzabh
final class zztl {
    /* access modifiers changed from: private */
    public final List<zzuk> zzaoz = new LinkedList();

    zztl() {
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzul zzul) {
        Handler handler = zzaij.zzdfn;
        for (zzuk zzuj : this.zzaoz) {
            handler.post(new zzuj(this, zzuj, zzul));
        }
        this.zzaoz.clear();
    }
}
