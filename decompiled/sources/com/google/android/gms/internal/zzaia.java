package com.google.android.gms.internal;

import android.os.Bundle;
import java.util.Iterator;

final class zzaia extends zzaib {
    private /* synthetic */ zzahy zzdfg;
    private /* synthetic */ Bundle zzdfh;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzaia(zzahy zzahy, Bundle bundle) {
        super((zzahz) null);
        this.zzdfg = zzahy;
        this.zzdfh = bundle;
    }

    public final void zzdo() {
        Iterator it = this.zzdfg.zzdeu.iterator();
        while (it.hasNext()) {
            ((zzaic) it.next()).zzb(this.zzdfh);
        }
    }
}
