package com.google.android.gms.internal;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

final class zzehx implements zzefo {
    private /* synthetic */ zzegu zzneb;
    private /* synthetic */ zzegx zznfo;
    private /* synthetic */ DatabaseReference.CompletionListener zznfr;
    private /* synthetic */ zzenn zzngl;

    zzehx(zzegx zzegx, zzegu zzegu, zzenn zzenn, DatabaseReference.CompletionListener completionListener) {
        this.zznfo = zzegx;
        this.zzneb = zzegu;
        this.zzngl = zzenn;
        this.zznfr = completionListener;
    }

    public final void zzbc(String str, String str2) {
        DatabaseError zzbe = zzegx.zzbd(str, str2);
        this.zznfo.zza("onDisconnect().setValue", this.zzneb, zzbe);
        if (zzbe == null) {
            this.zznfo.zznez.zzh(this.zzneb, this.zzngl);
        }
        this.zznfo.zza(this.zznfr, zzbe, this.zzneb);
    }
}
