package com.google.android.gms.internal;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

final class zzehw implements zzefo {
    private /* synthetic */ zzegu zzneb;
    private /* synthetic */ zzegx zznfo;
    private /* synthetic */ DatabaseReference.CompletionListener zznfr;
    private /* synthetic */ long zzngk;

    zzehw(zzegx zzegx, zzegu zzegu, long j, DatabaseReference.CompletionListener completionListener) {
        this.zznfo = zzegx;
        this.zzneb = zzegu;
        this.zzngk = j;
        this.zznfr = completionListener;
    }

    public final void zzbc(String str, String str2) {
        DatabaseError zzbe = zzegx.zzbd(str, str2);
        this.zznfo.zza("updateChildren", this.zzneb, zzbe);
        this.zznfo.zza(this.zzngk, this.zzneb, zzbe);
        this.zznfo.zza(this.zznfr, zzbe, this.zzneb);
    }
}
