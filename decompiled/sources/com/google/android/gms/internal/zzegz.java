package com.google.android.gms.internal;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import java.util.Map;

final class zzegz implements zzefo {
    private /* synthetic */ zzegu zzneb;
    private /* synthetic */ zzegx zznfo;
    private /* synthetic */ Map<zzegu, zzenn> zznfp;
    private /* synthetic */ DatabaseReference.CompletionListener zznfq;

    zzegz(zzegx zzegx, zzegu zzegu, Map<zzegu, zzenn> map, DatabaseReference.CompletionListener completionListener) {
        this.zznfo = zzegx;
        this.zzneb = zzegu;
        this.zznfp = map;
        this.zznfq = completionListener;
    }

    public final void zzbc(String str, String str2) {
        DatabaseError zzbe = zzegx.zzbd(str, str2);
        this.zznfo.zza("onDisconnect().updateChildren", this.zzneb, zzbe);
        if (zzbe == null) {
            for (Map.Entry<zzegu, zzenn> entry : this.zznfp.entrySet()) {
                this.zznfo.zznez.zzh(this.zzneb.zzh(entry.getKey()), entry.getValue());
            }
        }
        this.zznfo.zza(this.zznfq, zzbe, this.zzneb);
    }
}
