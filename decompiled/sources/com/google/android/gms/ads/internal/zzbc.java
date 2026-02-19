package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.zzahd;
import com.google.android.gms.internal.zzahe;
import com.google.android.gms.internal.zzaof;
import com.google.android.gms.internal.zzpx;
import com.google.android.gms.internal.zzvp;
import com.google.android.gms.internal.zzvs;
import com.google.android.gms.internal.zzwi;

final class zzbc implements Runnable {
    private /* synthetic */ zzahe zzant;
    private /* synthetic */ zzbb zzaro;

    zzbc(zzbb zzbb, zzahe zzahe) {
        this.zzaro = zzbb;
        this.zzant = zzahe;
    }

    public final void run() {
        this.zzaro.zzb(new zzahd(this.zzant, (zzaof) null, (zzvp) null, (zzwi) null, (String) null, (zzvs) null, (zzpx) null, (String) null));
    }
}
