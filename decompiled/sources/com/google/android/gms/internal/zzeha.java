package com.google.android.gms.internal;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

final class zzeha implements zzefo {
    private /* synthetic */ zzegu zzneb;
    private /* synthetic */ zzegx zznfo;
    private /* synthetic */ DatabaseReference.CompletionListener zznfr;

    zzeha(zzegx zzegx, zzegu zzegu, DatabaseReference.CompletionListener completionListener) {
        this.zznfo = zzegx;
        this.zzneb = zzegu;
        this.zznfr = completionListener;
    }

    public final void zzbc(String str, String str2) {
        DatabaseError zzbe = zzegx.zzbd(str, str2);
        if (zzbe == null) {
            this.zznfo.zznez.zzq(this.zzneb);
        }
        this.zznfo.zza(this.zznfr, zzbe, this.zzneb);
    }
}
