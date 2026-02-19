package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.zzahd;
import com.google.android.gms.internal.zzahe;
import com.google.android.gms.internal.zzaof;
import com.google.android.gms.internal.zzpx;
import com.google.android.gms.internal.zzvp;
import com.google.android.gms.internal.zzvs;
import com.google.android.gms.internal.zzwi;

final class zzk implements Runnable {
    private /* synthetic */ zzahe zzant;
    private /* synthetic */ zzi zzanz;

    zzk(zzi zzi, zzahe zzahe) {
        this.zzanz = zzi;
        this.zzant = zzahe;
    }

    public final void run() {
        this.zzanz.zzb(new zzahd(this.zzant, (zzaof) null, (zzvp) null, (zzwi) null, (String) null, (zzvs) null, (zzpx) null, (String) null));
    }
}
