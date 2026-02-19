package com.google.android.gms.internal;

import java.util.List;
import java.util.concurrent.Callable;

final class zzejb implements Callable<List<? extends zzell>> {
    private /* synthetic */ zzegu zzneb;
    private /* synthetic */ zzenn zznic;
    private /* synthetic */ zzeir zznie;

    zzejb(zzeir zzeir, zzegu zzegu, zzenn zzenn) {
        this.zznie = zzeir;
        this.zzneb = zzegu;
        this.zznic = zzenn;
    }

    public final List<? extends zzell> call() throws Exception {
        this.zznie.zznhs.zza(zzelu.zzam(this.zzneb), this.zznic);
        return this.zznie.zza((zzejy) new zzekc(zzeka.zznjx, this.zzneb, this.zznic));
    }
}
